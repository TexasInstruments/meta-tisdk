#!/bin/bash

board_model=$(tr -d '\0' </proc/device-tree/model)

if [[ $board_model == *"AM642 SK"* || $board_model == *"AM625 SK"* ]]; then
    if [[ $board_model == *"AM642 SK"* ]]; then
        defapname=AM64xSK-AP
    else
        defapname=AM62xSK-AP
    fi

    cd /usr/share/wl18xx

    ./load_wlcore.sh
    sleep 1

    mac_suffix=$(iw wlan0 info | awk '/addr/ {n=split($2,a,":"); printf "%s%s%s",a[3],a[4],a[5]}')
    apname="${defapname}_${mac_suffix}"

    sed -i "s/ssid=.*/ssid=${apname}/g" /usr/share/wl18xx/hostapd.conf

    ./ap_start.sh
    sleep 1

    iperf3 -B 192.168.43.1 -p 6001 -s -i 5 &
fi
