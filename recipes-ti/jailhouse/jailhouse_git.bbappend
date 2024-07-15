PR:append = "_tisdk_1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://jailhouse_root_oob.sh"

SRCREV = "c95e329cef2ef3123e4a5f40511d49640f34495a"

do_install:append () {
	sed -i '2i\start_linux_demo() {\' ${D}${JH_DATADIR}/linux-demo.sh
	echo -e "}\n" >> ${D}${JH_DATADIR}/linux-demo.sh
	cat ${WORKDIR}/jailhouse_root_oob.sh >> ${D}${JH_DATADIR}/linux-demo.sh

} 
