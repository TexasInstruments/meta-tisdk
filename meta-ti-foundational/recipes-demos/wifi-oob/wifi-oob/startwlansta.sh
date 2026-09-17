#!/bin/bash

set -e

WL18XX_DIR=/usr/share/wl18xx
# Partition holding the user's wificfg: looked up by label, with a fallback
# to the default SD card boot partition.
CFG_PART_LABEL=boot
CFG_PART_DEFAULT=/dev/mmcblk1p1

# Wait up to $2 seconds (default 10) for command $1 to succeed
wait_for ()
{
    local cmd=$1
    local timeout=${2:-10}
    local i

    for ((i = 0; i < timeout * 10; i++)); do
        if eval "$cmd" >/dev/null 2>&1; then
            return 0
        fi
        sleep 0.1
    done
    return 1
}

get_cfg ()
{
    python3 -c "
import configparser
import sys
config = configparser.ConfigParser()
config.read(sys.argv[1])
print(config.get(sys.argv[2], sys.argv[3], fallback=''))
" "$1" "$2" "$3"
}

cfg_part=$(blkid -L "$CFG_PART_LABEL" 2>/dev/null || true)
cfg_part=${cfg_part:-$CFG_PART_DEFAULT}
mnt=/media/$(basename "$cfg_part")
filename=$mnt/wificfg

cd "$WL18XX_DIR" || { echo "$WL18XX_DIR not found, exit"; exit 1; }

mkdir -p "$mnt"
if ! mountpoint -q "$mnt"; then
    mount "$cfg_part" "$mnt" || { echo "Failed to mount $cfg_part on $mnt, exit"; exit 1; }
fi

./load_wlcore.sh || { echo "Failed to load wlcore, exit"; exit 1; }
wait_for "ip link show wlan0" || { echo "wlan0 did not appear, exit"; exit 1; }

if [ ! -f "$filename" ]; then
    echo "Wi-Fi user configuration file missing, exit"
    exit 0
fi

ENABLE=$(get_cfg "$filename" Wifi-STA demo_enable)
WIFINAME=$(get_cfg "$filename" Wifi-STA ssid)
WIFIPASSWORD=$(get_cfg "$filename" Wifi-STA password)

if [ "$ENABLE" != "yes" ]; then
    exit 0
fi

if [ -z "$WIFINAME" ]; then
    echo "Client WiFi network NOT configured..exit"
    exit 0
fi

ifconfig wlan0 up || { echo "Failed to bring up wlan0, exit"; exit 1; }

./sta_start.sh || { echo "Failed to start station mode, exit"; exit 1; }
wait_for "pgrep -x wpa_supplicant" || { echo "wpa_supplicant did not start, exit"; exit 1; }

if [ -z "$WIFIPASSWORD" ]; then
    ./sta_connect-ex.sh "${WIFINAME}" NONE || { echo "Failed to connect to ${WIFINAME}, exit"; exit 1; }
else
    ./sta_connect-ex.sh "${WIFINAME}" WPA-PSK "${WIFIPASSWORD}" || { echo "Failed to connect to ${WIFINAME}, exit"; exit 1; }
fi
