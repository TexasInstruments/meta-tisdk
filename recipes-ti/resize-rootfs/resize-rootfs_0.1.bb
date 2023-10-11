DESCRIPTION = "Startup script to resize rootfs to full size"
SUMMARY = "This startup script expands the rootfs partition to full size of the boot device."
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=b427432730a914095e04e12c25413c41"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI = " \
    file://resize_rootfs.sh \
    file://resize_rootfs.service \
    file://LICENSE \
"

RDEPENDS:${PN} += "bash e2fsprogs-resize2fs"

SYSTEMD_SERVICE:${PN} = "resize_rootfs.service"

inherit systemd

do_install() {
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/resize_rootfs.service ${D}${sysconfdir}/systemd/system

    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/resize_rootfs.sh ${D}/${sysconfdir}/init.d
}

PR = "r0"
