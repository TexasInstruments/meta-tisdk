SUMMARY = "Reconfigure TAD5212 ADC"
DESCRIPTION = "Script to reconfigure TAD5212 registers for playback"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "am62dxx"

SRC_URI = "file://tad5212_reconfig.sh"

S = "${WORKDIR}"

RDEPENDS:${PN} = "i2c-tools bash"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/tad5212_reconfig.sh ${D}${bindir}/tad5212_reconfig.sh
}

FILES:${PN} = " \
	${bindir} \
"

PR = "r0"
