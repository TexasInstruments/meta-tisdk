PR:append = "_tisdk_0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://jailhouse_root_oob.sh"

do_install:append () {
	sed -i '2i\start_linux_demo() {\' ${D}${JH_DATADIR}/linux-demo.sh
	echo -e "}\n" >> ${D}${JH_DATADIR}/linux-demo.sh
	cat ${WORKDIR}/jailhouse_root_oob.sh >> ${D}${JH_DATADIR}/linux-demo.sh

} 
