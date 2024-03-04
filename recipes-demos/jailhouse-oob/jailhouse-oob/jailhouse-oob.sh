#!/bin/bash

echo "Starting script..." >> /var/log/jailhouse-oob.log

interface="enp0s1"

# Wait for interface to be up
sleep 2

while true; do
	if ifconfig "$interface" &> /dev/null 2>&1; then
		if ifconfig $interface 192.168.0.3; then
			echo "Assigned IP address to $interface" >> /var/log/jailhouse-oob.log
            break
	    else
			echo "Failed to assign IP address to $interface" >> /var/log/jailhouse-oob.error.log
		fi
    else
        sleep 1
	    echo "Sleeping 1 sec" >> /var/log/jailhouse-oob.log
    fi
done

echo "IP assigned to $interface." >> /var/log/jailhouse-oob.log
