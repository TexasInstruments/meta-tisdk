PR:append = ".ti-security0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://security.cfg"

do_configure:append() {
    cat ${WORKDIR}/security.cfg >> ${B}/.config
}

INITRAMFS_IMAGE = "tisdk-tiny-initramfs"
INITRAMFS_IMAGE_NAME = "tisdk-tiny-initramfs-${MACHINE}.rootfs"
