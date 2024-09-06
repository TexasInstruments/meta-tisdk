#!/usr/bin/env bash

XEN_NET_BRIDGE="${XEN_NET_BRIDGE:-xenbr0}"
XEN_DOMU_IFACE="${XEN_DOMU_IFACE:-enX0}"
RAMDISK_PATH="${RAMDISK_PATH:-/boot/initramfs.cpio}"

DOM0_IP="${DOM0_IP:-192.168.44.1}"
DOMU_IP="${DOMU_IP:-192.168.44.2}"

DOM0LESS="${XEN_BOOT_DOM0LESS}"
INIT_DOM0LESS="${INIT_DOM0LESS:-/usr/lib/xen/bin/init-dom0less}"

is_dom0() {
	local cap_file=/proc/xen/capabilities
	local dom0_str=control_d

	[ -f ${cap_file} ] && grep -q "${dom0_str}" ${cap_file}
}

create_bridge() {
	brctl addbr ${XEN_NET_BRIDGE}
	ifconfig ${XEN_NET_BRIDGE} ${DOM0_IP}
}

spawn_domU() {
	# interfaces with pattern veth* are ignored by systemd-networkd
	local vifname=veth-demo
	local config=$(mktemp)

	cat >${config} <<EOF
name = "ramdisk domu"
memory = 1024
vcpus = 4
vif = [ 'bridge=${XEN_NET_BRIDGE},vifname=${vifname}' ]
ramdisk = '${RAMDISK_PATH}'
kernel="/boot/Image"
cmdline="console=ttyAMA0"
EOF

	xl create ${config}
}

init_dom0less() {
	local vifname=veth-demo
	local domu_id=1

	${INIT_DOM0LESS} && xl network-attach ${domu_id} vifname="${vifname}"
}

dom0less_wait_domu_iface() {
	local tries=0

	echo "waiting for interface ${XEN_DOMU_IFACE} to be hotplugged"
	sleep 5

	while ! ifconfig ${XEN_DOMU_IFACE} >/dev/null 2>&1; do
		tries=$((tries + 1))

		if [ $tries -eq 5 ]; then
			return 1
		fi

		echo "could not detect the interface ${XEN_DOMU_IFACE}, wait - 5s"
		sleep 5
	done

	return 0
}

check_conn_with_other_vm() {
	local tries=0
	local ip=$1

	# wait for the domain to boot
	echo "waiting for VM with ip ${ip} to boot up - 5s"
	sleep 5

	# check if the other domain's interface is pingable
	while ! ping -w 1 -c 1 ${ip} >/dev/null 2>&1; do
		tries=$((tries + 1))

		if [ $tries -eq 5 ]; then
			return 1
		fi

		echo "could not connect to VM with ip ${ip}, wait - 5s"
		sleep 5
	done

	return 0
}

ping_n_times() {
	local n=$1
	local ip=$2

	# test connection by pinging n times
	for ping in $(eval echo {1..$n}); do
		if ping -w 1 -c 1 $ip >/dev/null; then
			echo "ping $ping to $ip successful"
		else
			echo "ping $ping to $ip unsuccessful"
		fi
	done
}

# dom0
if is_dom0; then
	# create bridge
	if ! create_bridge; then
		echo "failed to create bridge ${XEN_NET_BRIDGE}"
		exit 1
	fi

	# if not dom0less
	if [ -z "$DOM0LESS" ]; then
		# spawn domU
		if ! spawn_domU; then
			echo "failed to spawn domU"
			exit 1
		fi

	elif ! init_dom0less; then
		echo "failed to initialize xenstore for dom0less domU"
		exit 1
	fi

	# check if domU is up or not
	if ! check_conn_with_other_vm ${DOMU_IP}; then
		echo "failed to establish connection with domU"
		exit 1
	fi

	echo "pinging domU 10 times"
	ping_n_times 10 ${DOMU_IP}

# domU
else
	# check if the interface is up yet or not
	if [ -n "${DOM0LESS}" ] && ! dom0less_wait_domu_iface; then
		echo "failed to detect the interface ${XEN_DOMU_IFACE}"
		exit 1
	fi

	# assign an ip to the interface
	ifconfig ${XEN_DOMU_IFACE} ${DOMU_IP}

	# check if the dom0 is up or not
	if [ -n "${DOM0LESS}" ] && ! check_conn_with_other_vm ${DOM0_IP}; then
		echo "failed to establish connection with dom0"
		exit 1
	fi

	echo "pinging dom0 10 times"
	ping_n_times 10 ${DOM0_IP}
fi
