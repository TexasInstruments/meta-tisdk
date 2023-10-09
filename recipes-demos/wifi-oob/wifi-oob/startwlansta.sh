#!/bin/sh

get_cfg ()
{
configfile=$1
section=$2
param=$3

python3 -c "
import configparser;
config = configparser.ConfigParser();
config.read('$configfile');
print (config.get('$section','$param'));
"
}

filename=/usr/share/intel9260/wificfg
defapname=J7SK-AP

interfaceState=$(ip a s | grep -nr "w*p1s0")

if [ -z "$interfaceState" ]; then
 exit
fi

cd /usr/share/intel9260

./load_wlcore.sh
sleep 1

if [ ! -f $filename ]; then
    echo "Wi-Fi user configuration file missing, exit"
    exit
else
ENABLE=`get_cfg $filename Wifi-STA sta_enable`
WIFINAME=`get_cfg $filename Wifi-STA ssid`
WIFIPASSWORD=`get_cfg $filename Wifi-STA password`

if [ $ENABLE != "yes" ]; then
    exit
fi

if [ -z "$WIFINAME" ] ; then
    echo "Client WiFi network NOT configured..exit"
    exit
fi

sleep 5

### add WLAN interface, if not present
WLAN=wlp1s0
if [ ! -d /sys/class/net/$WLAN ]
then
  echo "adding $WLAN interface"
  iw phy `ls /sys/class/ieee80211/` interface add $WLAN type managed
fi

ifconfig wlp1s0 up

sleep 1
./sta_start.sh
sleep 1

if [ -z "$WIFIPASSWORD" ] ; then
    ./sta_connect-ex.sh  ${WIFINAME} NONE
else
    ./sta_connect-ex.sh  ${WIFINAME} WPA-PSK ${WIFIPASSWORD}
fi

sleep 1

# get the IP address
udhcpc -i wlp1s0

fi
