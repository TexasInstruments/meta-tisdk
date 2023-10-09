#!/bin/sh

if ps -w | grep -v grep | grep wpa_supplicant | grep wlp1s0 > /dev/null
then
    echo "wpa_supplicant is already running"
    exit 0
fi

if [ ! -f /usr/share/intel9260/wpa_supplicant.conf ]
then
 if [ ! -f /etc/wpa_supplicant.conf ]
 then
  echo "error - no default wpa_supplicant.conf"
  exit 1
 fi
 cp /etc/wpa_supplicant.conf /usr/share/intel9260/wpa_supplicant.conf
fi

wpa_supplicant -B -D nl80211,wext -i wlp1s0 -c/usr/share/intel9260/wpa_supplicant.conf &
