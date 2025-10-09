#!/bin/bash
# cpld_ctl.sh
# Script to program CPLD channels via i2c-tools
# Usage:
#   ./cpld_ctl.sh -b <CPLD I2C bus> -s <cpld_i2c_switch_addr> -i <cpld_i2c_io_exp_addr> -c "CH:CODE,CH:CODE ..."
# Example:
#   ./cpld_ctl.sh -b 0 -s 0x70 -i 0x71 -c "0:0x0c,4:0x0d"

USAGE='Usage: ./cpld_ctl.sh [OPTIONS]

DESCRIPTION:
	Programs CPLD routing channels through I2C switch and I2C IO expander.

OPTIONS:
	-h <help>			Show help message
	-b <bus>			CPLD I2C bus number (required)
	-s <cpld_i2c_switch_addr>	CPLD I2C switch address (required)
	-i <cpld_i2c_io_exp_addr>	CPLD I2C IO expander address (required)
	-c <channel mappings>		Channel:code mappings "channel:code,channel:code,.." (required)
	-r <config>			CPLD I2C IO expander config register value (hex, e.g. 0x00) (optional)

EXAMPLES:
	# Specify custom I2C bus and addresses
	./cpld_ctl.sh -b 1 -s 0x72 -i 0x73 -c "2:0x1f"

	# Program multiple channels
	./cpld_ctl.sh -b 1 -s 0x71 -i 0x72 -c "0:0x0c,1:0x0d,4:0x1f,7:0x33"

REQUIREMENTS:
	• i2c-tools package (i2cset, i2cget, i2cdetect)
	• I2C kernel modules loaded

EXIT CODES:
	0 : Success
	1 : Invalid arguments
	2 : Missing i2c-tools
	3 : Invalid I2C address
	4 : I2C device not detected
	5 : Failed to set CONFIG register
	6 : Failed to set single/multiple channel(s).

NOTES:
	The I2C IO expander is accessed through the I2C switch channels.
	Each channel selection allows communication with the expander to
	program the CPLD routing configuration.
'

CHANNELS=()

CONFIG_REG_VAL=0x00
OUTPUT_PORT_REG=0x01
CONFIG_REG=0x03
EXIT_VAL=0

# Validate I2C address format and range
validate_i2c_addr() {
	local addr=$1
	local name=$2

	# Check hex format
	if [[ ! "$addr" =~ ^0x[0-9A-Fa-f]{2}$ ]]; then
		echo "ERROR: Invalid $name address format '$addr'. Use 0xXX format."
		return 1
	fi

	# Convert to decimal for range check
	local dec_addr=$((addr))

	# Valid I2C 7-bit address range (excluding reserved addresses)
	if [ "$dec_addr" -lt 8 ] || [ "$dec_addr" -gt 119 ]; then
		echo "ERROR: $name address $addr (decimal $dec_addr) outside valid range 0x08-0x77"
		return 1
	fi

	return 0
}

# Write register on expander
write_reg() {
	local bus=$1 addr=$2 reg=$3 val=$4
	i2cset -y "$bus" "$addr" "$reg" "$val" >/dev/null 2>&1
	return $?
}

# Read register
read_reg() {
	local bus=$1 addr=$2 reg=$3
	i2cget -y "$bus" "$addr" "$reg" 2>/dev/null
}

# Raw single-byte write to switch (no register)
raw_switch_write() {
	local bus=$1 addr=$2 byte=$3
	i2cset -y "$bus" "$addr" "$byte" >/dev/null 2>&1
	return $?
}

while getopts ":hb:s:i:c:r:" opt; do
	case $opt in
		h)
			echo "$USAGE"
			exit 0
			;;
		b)
			BUS="$OPTARG"
			;;
		s)
			SW="$OPTARG"
			;;
		i)
			IO="$OPTARG"
			;;
		c)
			IFS=, read -r -a CHANNELS <<< "$OPTARG"
			;;
		r)
			CONFIG_REG_VAL=$OPTARG
			;;
		\?)
			echo "Invalid option: -$OPTARG" >&2
			echo "$USAGE" >&2
			exit 1
			;;
		:)
			echo "Option -$OPTARG requires an argument." >&2
			echo "$USAGE" >&2
			exit 1
			;;
	esac
done

# Check if all required options are provided
if [ -z "$BUS" ] || [ -z "$SW" ] || [ -z "$IO" ] || [ ${#CHANNELS[@]} -eq 0 ]; then
	echo "Error: All options (-b, -s, -i, and -c) are required"
	echo "$USAGE"
	exit 1
fi

echo "BUS: $BUS"
echo "SW: $SW"
echo "IO: $IO"
echo "CHANNELS: ${CHANNELS[*]}"

# Require i2c-tools
if ! command -v i2cset >/dev/null 2>&1 || ! command -v i2cget >/dev/null 2>&1; then
	echo "Error: i2cset/i2cget not found. Install i2c-tools."
	exit 2
fi

# Validate addresses
if ! validate_i2c_addr "$SW" "switch"; then
	exit 3
fi

if ! validate_i2c_addr "$IO" "GPIO expander"; then
	exit 3
fi

# Test I2C connectivity
echo "Testing I2C connectivity on bus $BUS..."

# Check if i2cdetect is available
if ! command -v i2cdetect >/dev/null 2>&1; then
	echo "Warning: i2cdetect not found, skipping connectivity test"
else
	# Extract numeric part of addresses for i2cdetect comparison
	SW_NUM=$(printf "%02x" $((SW)))
	IO_NUM=$(printf "%02x" $((IO)))

	# Test I2C switch connectivity
	if i2cdetect -r -y "$BUS" 2>/dev/null | grep -q "$SW_NUM"; then
		echo "I2C Switch detected at bus $BUS"
	else
		echo "ERROR: Switch at $SW not detected on I2C bus $BUS"
		echo "Available devices:"
		i2cdetect -r -y "$BUS" 2>/dev/null || echo "Failed to scan bus"
		exit 4
	fi

	# Test IO expander connectivity (using channel 0)
	if raw_switch_write "$BUS" "$SW" 0x01; then
		sleep 0.01
		if i2cdetect -r -y "$BUS" 2>/dev/null | grep -q "$IO_NUM"; then
			echo "IO expander detected at $IO"
			# Set Config Register for IO expander
			echo "Setting expander config $CONFIG_REG=$CONFIG_REG_VAL on ${IO}..."
			if ! write_reg "$BUS" "$IO" "$CONFIG_REG" "$CONFIG_REG_VAL"; then
				echo "ERROR: failed to set CONFIG register"
				exit 5
			fi
		else
			echo "ERROR: IO expander at $IO not found using channel 0"
			raw_switch_write "$BUS" "$SW" 0x00  # Cleanup
			exit 4
		fi
	else
		echo "ERROR: Failed to select test channel 0"
		exit 4
	fi
	raw_switch_write "$BUS" "$SW" 0x00  # Cleanup
fi

for m in "${CHANNELS[@]}"; do
	if ! [[ "$m" =~ : ]]; then
		echo "Invalid input format: $m"
		EXIT_VAL=6
		continue
	fi

	CH=${m%%:*}
	CODE=${m#*:}

	# Validate
	if ! [[ "$CH" =~ ^[0-9]+$ ]] || [ "$CH" -lt 0 ] || [ "$CH" -gt 7 ]; then
		echo "Invalid channel: $CH"
		EXIT_VAL=6
		continue
	fi

	if ! [[ "$CODE" =~ ^0x[0-9A-Fa-f]+$ ]]; then
		echo "Invalid code format, use hex like 0x0C: $CODE"
		EXIT_VAL=6
		continue
	fi

	MASK=$((1 << CH))
	printf -v MASKHEX "0x%02X" "$MASK"

	echo "Selecting channel $CH (mask $MASKHEX) ..."
	if ! raw_switch_write "$BUS" "$SW" "$MASKHEX"; then
		echo "ERROR: failed to select channel $CH on $SW"
		EXIT_VAL=6
		continue
	fi

	sleep 0.001

	# Try writing OUTPUT_PORT_REG up to 3 times
	SUCCESS=1
	for attempt in 1 2 3; do
		echo "Writing OUTPUT=$CODE (attempt $attempt) ..."
		if write_reg "$BUS" "$IO" "$OUTPUT_PORT_REG" "$CODE"; then
			sleep 0.001
			READ_VAL=$(read_reg "$BUS" "$IO" "$OUTPUT_PORT_REG")
			if [ -n "$READ_VAL" ] && { [ "$READ_VAL" = "$CODE" ] || [ "${READ_VAL,,}" = "${CODE,,}" ]; }; then
				echo "Write Success: ch$CH -> $CODE"
				SUCCESS=0
				break
			else
				echo "Read value mismatch: Got $READ_VAL but expected $CODE."
				echo "Write failed: ch$CH -> $CODE"
			fi
		else
			echo "Write failed: ch$CH -> $CODE"
		fi
		sleep 0.05
	done

	if [ $SUCCESS -ne 0 ]; then
		echo "Failed to program channel $CH -> $CODE"
		EXIT_VAL=6
	fi
done

# Deselect everything
echo "Deselecting switch..."
raw_switch_write "$BUS" "$SW" 0x00 || echo "Warning: failed to deselect"

echo "Done"
exit $EXIT_VAL
