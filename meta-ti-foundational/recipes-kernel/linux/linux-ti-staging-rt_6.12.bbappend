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
    file://v3-0001-PENDING-HACK-arm-dts-dra7-am57-Enable-late-attach.patch \
    file://v3-0002-PENDING-HACK-arm-dts-dra7-ipu-dsp-common-Remove-r.patch \
    file://v3-0003-PENDING-HACK-ARM-OMAP2-powerdomain-Do-not-registe.patch \
    file://v3-0004-PENDING-HACK-ARM-OMAP2-clockdomain-Do-not-initial.patch \
    file://v3-0005-PENDING-HACK-dma-coherent-Introduce-non-zeroing-a.patch \
    file://v3-0006-PENDING-HACK-remoteproc-core-Add-late_attach-supp.patch \
    file://v3-0007-PENDING-HACK-remoteproc-omap-add-late-attach-supp.patch \
    file://v3-0008-PENDING-HACK-iommu-omap-iommu-Add-support-for-per.patch \
    file://v3-0009-PENDING-HACK-clocksource-timer-ti-dm-Add-support-.patch \
    file://0001-ARM-dts-Fix-suspend-issue-for-vip.patch \
"

SRCREV:tie-jailhouse = "aae1ac9e6d62c11ad49915504b7f0a9ce0dbe4aa"

PR:append = "_tisdk_8"

