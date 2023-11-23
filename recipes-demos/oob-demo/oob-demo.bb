SUMMARY = "Out of box demo application"

LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://demo.service;md5=267f86208e40c3debf19cea6e5837bbe"

FILESEXTRAPATHS:append := ":${THISDIR}/${PN}"

SRC_URI = " \
    file://demo.service \
    file://autolaunch-demo \
    file://demo.sh \
"

RDEPENDS:${PN} += "bash"
SYSTEMD_SERVICE:${PN} = "demo.service"

S = "${WORKDIR}"

inherit systemd

do_install() {
	# Install the systemd unit, initscript and demo script
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/demo.service ${D}${systemd_system_unitdir}

	install -d ${D}${datadir}/demo
	install -m 0755 ${WORKDIR}/demo.sh ${D}${datadir}/demo
	install -m 0755 ${WORKDIR}/autolaunch-demo ${D}${datadir}/demo
}

FILES:${PN} += " \
	${datadir}/demo \
"

PR = "r1"
