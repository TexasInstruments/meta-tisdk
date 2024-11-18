PR:append = "_tisdk_1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://jailhouse_root_oob.sh"

SRCREV = "42055c4e023f239b3f550940c59fb83024784843"

do_install:append () {
	sed -i '2i\start_linux_demo() {\' ${D}${JH_DATADIR}/linux-demo.sh
	echo -e "}\n" >> ${D}${JH_DATADIR}/linux-demo.sh
	cat ${WORKDIR}/jailhouse_root_oob.sh >> ${D}${JH_DATADIR}/linux-demo.sh

} 
