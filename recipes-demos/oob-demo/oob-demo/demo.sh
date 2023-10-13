#!/bin/bash

for sh in /etc/profile.d/*.sh ; do
    [ -r "$sh" ] && . "$sh"
done

# Use this to launch something as silent process in background as follows
# launch_bg_silent weston-simple-egl -o -s
launch_bg_silent() {
	"$@" >/dev/null 2>&1 &
}

# Use this to wait for weston to be ready to start weston clients
wait_for_weston() {
	for i in `seq 1 50`; do
		weston-info
		if [ $? -eq 0 ]; then
			break
		fi
		sleep 0.1
	done
}

echo "Running auto launch demo from " $(readlink -f "$0")
# Add any custom script to be launched here
# Do not delete anything above this line
#####################################################################


