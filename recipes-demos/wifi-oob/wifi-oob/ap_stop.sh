#!/bin/sh

########## variables ##########

WLAN=wlp1s0
DHCP_CONF=udhcpd.conf

########## body ##########

echo "Terminating DHCP"
if [ -d /sys/class/net/$WLAN ]
then
  output=`ps | grep $DHCP_CONF`
  set -- $output
  if [ -n "$output" ]; then
    kill $1
  fi
fi

echo "Terminating hostapd"
killall hostapd
