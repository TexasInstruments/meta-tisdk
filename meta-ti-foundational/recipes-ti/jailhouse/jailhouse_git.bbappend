PR:append = "_tisdk_1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://jailhouse_root_oob.sh"

SRCREV = "b44c3548252bb3818b0dd0815728ca210d8fac2b"

do_install:append () {
	sed -i '2i\start_linux_demo() {\' ${D}${JH_DATADIR}/linux-demo.sh
	echo -e "}\n" >> ${D}${JH_DATADIR}/linux-demo.sh
	cat ${UNPACKDIR}/jailhouse_root_oob.sh >> ${D}${JH_DATADIR}/linux-demo.sh

}
