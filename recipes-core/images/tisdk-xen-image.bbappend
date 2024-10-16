PR:append = "_tisdk_0"

IMAGE_INSTALL:append = " xen-oob u-boot-xen-scr"
IMAGE_BOOT_FILES:append = " boot.xen.scr;boot.scr"

ROOTFS_POSTINSTALL_COMMAND:append = "install_ramdisk; "
install_ramdisk () {
    # Install the ramdisk which domu will use as root
    if [ -n "${XEN_RAMFS_IMAGE}" ]; then
        if [ -f ${DEPLOY_DIR_IMAGE}/${XEN_RAMFS_IMAGE}*-${MACHINE}.rootfs.cpio ]; then
            install -m 0644 ${DEPLOY_DIR_IMAGE}/${XEN_RAMFS_IMAGE}*-${MACHINE}.rootfs.cpio ${IMAGE_ROOTFS}/boot
        else
            bberror "Could not find XEN_RAMFS_IMAGE (${XEN_RAMFS_IMAGE}*-${MACHINE}.rootfs.cpio)!"
            bberror "Please make sure that \"cpio\" is in IMAGE_FSTYPES."
        fi
    fi
}

python __anonymous () {
    if d.getVar('XEN_RAMFS_IMAGE', True):
        d.appendVarFlag('do_rootfs', 'depends', ' ${XEN_RAMFS_IMAGE}:do_image_complete')
}
