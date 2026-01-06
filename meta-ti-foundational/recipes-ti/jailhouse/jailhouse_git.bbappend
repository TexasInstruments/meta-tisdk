PR:append = "_tisdk_1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://jailhouse_root_oob.sh"

SRCREV = "ef512f9591febed854d8f07ad8c1fa731c2d3c69"

do_install:append () {
	sed -i '2i\start_linux_demo() {\' ${D}${JH_DATADIR}/linux-demo.sh
	echo -e "}\n" >> ${D}${JH_DATADIR}/linux-demo.sh
	cat ${UNPACKDIR}/jailhouse_root_oob.sh >> ${D}${JH_DATADIR}/linux-demo.sh

} 
