SUMMARY = "CPLD programmer via I2C"
DESCRIPTION = "Script to program CPLD routing channels using i2c-tools"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "am62dxx"

SRC_URI = "file://cpld_ctl.sh"

S = "${UNPACKDIR}"

RDEPENDS:${PN} = "i2c-tools bash"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/cpld_ctl.sh ${D}${bindir}/cpld_ctl.sh
}

FILES:${PN} = " \
	${bindir} \
"

PR = "r0"
