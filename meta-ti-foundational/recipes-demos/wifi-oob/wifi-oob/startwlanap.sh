#!/bin/bash

set -e

WL18XX_DIR=/usr/share/wl18xx

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

board_model=$(tr -d '\0' </proc/device-tree/model)

if [[ $board_model == *"AM642 SK"* || $board_model == *"AM625 SK"* ]]
then
    if [[ $board_model == *"AM642 SK"* ]]
    then
        defapname=AM64xSK-AP
    else
        defapname=AM62xSK-AP
    fi

    cd "$WL18XX_DIR" || { echo "$WL18XX_DIR not found, exit"; exit 1; }

    ./load_wlcore.sh || { echo "Failed to load wlcore, exit"; exit 1; }
    wait_for "ip link show wlan0" || { echo "wlan0 did not appear, exit"; exit 1; }

    a3=$(iw wlan0 info | grep "addr" | awk '{split($0,a,":"); print a[3]}')
    a4=$(iw wlan0 info | grep "addr" | awk '{split($0,a,":"); print a[4]}')
    a5=$(iw wlan0 info | grep "addr" | awk '{split($0,a,":"); print a[5]}')

    apname="${defapname}_${a3}${a4}${a5}"

    sed -i "s/ssid=.*/ssid=${apname}/g" "$WL18XX_DIR/hostapd.conf"

    ./ap_start.sh || { echo "Failed to start AP, exit"; exit 1; }
    wait_for "pgrep -x hostapd" || { echo "hostapd did not start, exit"; exit 1; }

    iperf3 -B 192.168.43.1 -p 6001 -s -i 5 &
fi
