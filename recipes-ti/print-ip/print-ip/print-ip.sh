#!/bin/sh

oled_display_dev=/dev/tty0
oled_display_dev_dir=/sys/devices/platform/bus@f4000/20010000.i2c/i2c-1/1-003c

# Check if OLED display device exists
if [[ -c $oled_display_dev && -d $oled_display_dev_dir ]]; then
    var=`ifconfig eth0 | awk '/inet / {print $2}'`

    if [ -z "$var" ];
    then
        printf "\n\n%-24s" "Not         connected" > $oled_display_dev
    else
        printf "\n\n%-24s" $var  > $oled_display_dev
    fi
fi

