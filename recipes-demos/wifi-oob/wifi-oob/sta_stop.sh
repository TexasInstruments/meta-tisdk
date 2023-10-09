#!/bin/sh

wpa_cli -iwlp1s0 terminate

### stop udhcp client, if not started
output=`ps | grep -v grep | grep udhcpc`
set -- $output
echo $output
if [ -n "$output" ]; then
   killall udhcpc
fi
