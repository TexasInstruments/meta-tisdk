#!/bin/sh

########## variables ##########

WLAN=wlp1s0
HOSTAPD_PROC=/var/run/hostapd
HOSTAPD_CONF=/usr/share/intel9260/hostapd.conf
HOSTAPD_BIN_DIR=/usr/sbin
IP_ADDR=192.168.43.1
DHCP_CONF=udhcpd.conf
DHCP_CONF_PROC=udhcpd.conf

########## body ##########

### check for configuration file
if [ ! -f $HOSTAPD_CONF ]; then
 if [ ! -f /etc/hostapd.conf ]
 then
  echo "error - no default hostapd.conf file"
  exit 1
 fi
 cp /etc/hostapd.conf $HOSTAPD_CONF
 chmod 777 $HOSTAPD_CONF
fi

### configure ip forewarding
echo 1 > /proc/sys/net/ipv4/ip_forward

### add WLAN interface, if not present
if [ ! -d /sys/class/net/$WLAN ]
then
  echo "adding $WLAN interface"
  iw phy `ls /sys/class/ieee80211/` interface add $WLAN type managed
fi

### start a hostapd interface, if not present
if [ ! -r $HOSTAPD_PROC ]
then
 $HOSTAPD_BIN_DIR/hostapd $HOSTAPD_CONF &
 sleep 1
fi

### configure ip
ifconfig $WLAN $IP_ADDR netmask 255.255.255.0 up

### start udhcpd server, if not started
output=`ps | grep /usr/share/intel9260\$DHCP_CONF_PROC`
set -- $output
echo $output
if [ -z "$output" ]; then
  udhcpd $DHCP_CONF
fi


### configure nat
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE

