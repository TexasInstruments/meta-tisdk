# Since do_configure and do_install do not work in an initramfs setting,
# call required functionality from packagegroup

PACKAGE_INSTALL += " cryptsetup lvm2 e2fsprogs-mke2fs packagegroup-ti-security"
