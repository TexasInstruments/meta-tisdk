#!/bin/sh

NETID=0
WPA_CLI='wpa_cli -i wlp1s0'
if [ $# -eq 0 ] || [ "$1" == "--help" ] || [ "$1" == "-h" ]; then
    echo ""
    echo "You must specify at least the network ssid"
    echo "format: $0 <ssid> [key_mgmt] [psk]"
    echo ""
    echo "supported key-mgmt: NONE, WPA-PSK"
    exit
fi

NETID=`$WPA_CLI add_network | grep -v Using | grep -v Selected`

echo "netid="$NETID
echo "========================="
echo $WPA_CLI set_network $NETID ssid \'\""$1"\"\' > /usr/share/intel9260/temp.txt

if [ $# -gt 1 ]; then
    if [ "$2" == "WPA-PSK" ] || [ "$2" == "NONE" ] ; then
       echo $WPA_CLI set_network $NETID key_mgmt "$2" >> /usr/share/intel9260/temp.txt
    else
       echo "Sorry, but only WPA-PSK and NONE key_mgmt is supported"
       exit
    fi
else
    echo $WPA_CLI set_network $NETID key_mgmt NONE >> /usr/share/intel9260/temp.txt
fi

if [ $# -gt 2 ]; then
    echo $WPA_CLI set_network $NETID psk \'\""$3"\"\' >> /usr/share/intel9260/temp.txt
fi


echo $WPA_CLI select_network $NETID >> /usr/share/intel9260/temp.txt
chmod 777 /usr/share/intel9260/temp.txt
sh /usr/share/intel9260/temp.txt
rm /usr/share/intel9260/temp.txt

sleep 2
udhcpc -i wlp1s0 &
