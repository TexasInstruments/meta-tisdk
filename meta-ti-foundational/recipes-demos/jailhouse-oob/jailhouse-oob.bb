SUMMARY = "Out of box demo application for jailhouse inmate"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

PR = "r0"

SRC_URI = " \
    file://jailhouse-oob.service \
    file://jailhouse-oob.sh \
"

S = "${UNPACKDIR}"
RDEPENDS:${PN} += "bash"
SYSTEMD_SERVICE:${PN} = "jailhouse-oob.service"

inherit systemd

do_install() {
	# Install the systemd unit and demo script
	install -d ${D}${systemd_system_unitdir}
	install -m 0755 ${UNPACKDIR}/jailhouse-oob.service ${D}${systemd_system_unitdir}

	install -d ${D}${datadir}/demo
	install -m 0755 ${UNPACKDIR}/jailhouse-oob.sh ${D}${datadir}/demo
}

FILES:${PN} += " \
	${datadir}/demo \
"
