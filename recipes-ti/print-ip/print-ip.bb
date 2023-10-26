SUMMARY = "Service to print IP address to the OLED display"

LICENSE = "MIT"

COMPATIBLE_MACHINE = "ti33x|ti43x|am64xx"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:append := ":${THISDIR}/print_ip:"

SRC_URI = " \
    file://print-ip.service \
    file://print-ip.timer \
    file://print-ip.sh \
"

SYSTEMD_SERVICE:${PN} = "print-ip.timer print-ip.service"

inherit systemd

do_install() {
    install -d ${D}${systemd_system_unitdir}

    install -m 0644 ${WORKDIR}/print-ip.service ${D}${systemd_system_unitdir}/print-ip.service
    install -m 0644 ${WORKDIR}/print-ip.timer ${D}${systemd_system_unitdir}/print-ip.timer

    install -d ${D}/usr/bin/

    install -m 0755 ${WORKDIR}/print-ip.sh ${D}/usr/bin/print-ip.sh
}

PR = "r0"
