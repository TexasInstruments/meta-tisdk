FILESEXTRAPATHS:prepend := "${THISDIR}/xen:"

SRC_URI:append = " \
        file://drivers-xen-improve-the-late-xenstore-init-protocol.patch \
        file://fix-rpmsg-xen.patch \
        file://xen.cfg \
        "

KERNEL_CONFIG_FRAGMENTS:append = " ${WORKDIR}/xen.cfg"
