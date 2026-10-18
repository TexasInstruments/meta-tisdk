#!/bin/bash

get_cfg()
{
    configfile=$1
    section=$2
    param=$3

    python3 -c "
import configparser;
config = configparser.ConfigParser();
config.read('$configfile');
print(config.get('$section','$param'));
"
}

board_model=$(tr -d '\0' </proc/device-tree/model)
filename=/media/mmcblk1p1/wificfg

cd /usr/share/wl18xx

if [ ! -d "/media/mmcblk1p1" ]; then
    mkdir /media/mmcblk1p1
fi

mount /dev/mmcblk1p1 /media/mmcblk1p1

./load_wlcore.sh
sleep 1

if [ ! -f "$filename" ]; then
    echo "Wi-Fi user configuration file missing, exit"
    exit
fi

ENABLE=$(get_cfg "$filename" Wifi-STA demo_enable)
WIFINAME=$(get_cfg "$filename" Wifi-STA ssid)
WIFIPASSWORD=$(get_cfg "$filename" Wifi-STA password)

if [ "$ENABLE" != "yes" ]; then
    exit
fi

if [ -z "$WIFINAME" ]; then
    echo "Client WiFi network NOT configured..exit"
    exit
fi

ifconfig wlan0 up

sleep 1
./sta_start.sh
sleep 1

if [ -z "$WIFIPASSWORD" ]; then
    ./sta_connect-ex.sh "${WIFINAME}" NONE
else
    ./sta_connect-ex.sh "${WIFINAME}" WPA-PSK "${WIFIPASSWORD}"
fi

sleep 1
