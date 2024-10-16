SUMMARY = "Out of box demo application for Xen Dom0 and DomU"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

PR = "r0"

SRC_URI = " \
    file://xen-oob.service.in \
    file://xen-oob.sh \
"

RDEPENDS:${PN} += "bash net-tools iputils"
SYSTEMD_SERVICE:${PN} = "xen-oob.service"

inherit systemd

XEN_NET_BRIDGE ??= "xenbr0"
XEN_DOMU_IFACE ??= "enX0"
XEN_BOOT_DOM0LESS ??= ""
DOM0_IP ??= "192.168.44.1"
DOMU_IP ??= "192.168.44.2"
RAMDISK_PATH ??= "${@'\/boot\/${XEN_RAMFS_IMAGE}-${MACHINE}.rootfs.cpio' if d.getVar('XEN_RAMFS_IMAGE') else '' }"

do_install() {
    sed -e 's/@@XEN_NET_BRIDGE@@/${XEN_NET_BRIDGE}/' \
        -e 's/@@XEN_DOMU_IFACE@@/${XEN_DOMU_IFACE}/' \
        -e 's/@@XEN_BOOT_DOM0LESS@@/${XEN_BOOT_DOM0LESS}/' \
        -e 's/@@RAMDISK_PATH@@/${RAMDISK_PATH}/' \
        -e 's/@@DOM0_IP@@/${DOM0_IP}/' \
        -e 's/@@DOMU_IP@@/${DOMU_IP}/' \
        "${WORKDIR}/xen-oob.service.in" > "${WORKDIR}/xen-oob.service"

    # Install the service
    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/xen-oob.service ${D}${systemd_system_unitdir}

    # Install the oneshot script
    install -d ${D}${datadir}/demo
    install -m 0755 ${WORKDIR}/xen-oob.sh ${D}${datadir}/demo
}

FILES:${PN} += " \
    ${datadir}/demo \
"
