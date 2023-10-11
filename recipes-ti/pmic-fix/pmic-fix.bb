SUMMARY = "Update pmic registers for tda4vm-sk to disable safety feature on power rails"

LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI= " \
    file://pmic-fix \
    file://pmic-fix.service \
"

RDEPENDS:${PN} += "bash"

SYSTEMD_SERVICE:${PN} = "pmic-fix.service"

inherit systemd

do_install() {
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/pmic-fix.service ${D}${sysconfdir}/systemd/system

    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/pmic-fix ${D}/${sysconfdir}/init.d
}

PR:append = "_tisdk_0"
