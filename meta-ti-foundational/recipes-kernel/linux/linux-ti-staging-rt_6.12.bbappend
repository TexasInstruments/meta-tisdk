FILESEXTRAPATHS:prepend := "${THISDIR}/linux-ti-staging:${THISDIR}/${PN}/${MACHINE}:"

SRC_URI:append:am57xx = " \
    file://v2-0001-TI-HACK-rpmsg-introduce-rpmsg_ns_msg_ext-structur.patch \
    file://v2-0002-TI-HACK-remoteproc-add-api-for-retrieving-a-rproc.patch \
    file://v2-0003-TI-HACK-net-rpmsg-add-support-for-new-rpmsg-socke.patch \
    file://v2-0004-TI-HACK-net-rpmsg-add-support-to-handle-a-remote-.patch \
    file://v2-0005-TI-HACK-net-rpmsg-return-ESHUTDOWN-upon-Tx-on-err.patch \
    file://v2-0006-TI-HACK-net-rpmsg-return-ENOLINK-upon-Rx-on-error.patch \
    file://v2-0007-TI-HACK-net-rpmsg-unblock-reader-threads-operatin.patch \
    file://v2-0008-TI-HACK-remoteproc-Fix-multiple-back-to-back-erro.patch \
    file://v2-0009-TI-HACK-arm-multi_v7_defconfig-enable-rpmsg-proto.patch \
"

SRCREV:tie-jailhouse = "aae1ac9e6d62c11ad49915504b7f0a9ce0dbe4aa"

PR:append = "_tisdk_8"

