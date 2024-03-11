run_crashapp=''
run_background=''
interface="enP1p0s1"
inmate_ipv4_address="192.168.0.3"
inmate_boot=''
interface_detected=''
crash_test_command="crash-test -q -c 4 -s 1 -i $inmate_ipv4_address"

while getopts ':cb' flag; do
	case "${flag}" in
		c) run_crashapp='true' ;;
		b) run_background='&' ;;
		*) echo "Usage: $0 [-c start crash-test app] [-b run app in background]"
			exit 1 ;;
	esac
done

start_linux_demo

if [ "$run_crashapp" = "true" ]; then
	# Wait for network interface
	for n in {1..20}; do
		if ifconfig "$interface" &> /dev/null 2>&1; then
			echo "Interface $interface detected"
			ifconfig $interface 192.168.0.2
			interface_detected="true"
			break
		else
            sleep 1
        fi
	done
	
	if [ "$interface_detected" != "true" ]; then
		echo "Interface $interface not detected"
		exit 1
	fi
	
	echo "Waiting for inmate to boot"
	# Wait 20 sec for inmate to boot
	for n in {1..20}; do
		ping -c 1 $inmate_ipv4_address > /dev/null
		if [ $? -eq 0 ]; then
			echo "Ping to inmate successful."
			inmate_boot="true"
			break
		else
			sleep 1
		fi
	done
		
	if [ "$inmate_boot" != "true" ]; then
		echo "Inmate boot failed"
		exit 1
	fi
	
	echo "Starting Crash test application"
	eval "$crash_test_command $run_background"
else
    echo "Skipping crash test application"
fi
