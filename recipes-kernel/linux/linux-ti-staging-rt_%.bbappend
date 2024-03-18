FILESEXTRAPATHS:prepend := "${THISDIR}/linux-ti-staging:${THISDIR}/linux-ti-staging/${MACHINE}:"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
    file://0001-arm64-dts-ti-k3-j721e-sk-Enable-wkup-i2c0.patch \
"

SRC_URI:append:am57xx = " \
    file://0001-support-TI-GC320-drivers.patch \
    file://0001-disable-gcc-plugin-config.patch \
    file://0001-arm-dts-am57xx-beagle-x15-Disable-the-PRU-cores.patch \
    file://0001-rpmsg-introduce-rpmsg_ns_msg_ext-structure.patch \
    file://0002-remoteproc-add-api-for-retrieving-a-rproc-unique-id.patch \
    file://0003-net-rpmsg-add-support-for-new-rpmsg-sockets.patch \
    file://0004-net-rpmsg-add-support-to-handle-a-remote-processor-e.patch \
    file://0005-net-rpmsg-return-ESHUTDOWN-upon-Tx-on-errored-socket.patch \
    file://0006-net-rpmsg-return-ENOLINK-upon-Rx-on-errored-sockets.patch \
    file://0007-net-rpmsg-unblock-reader-threads-operating-on-errore.patch \
    file://0008-remoteproc-Fix-multiple-back-to-back-error-recoverie.patch \
    file://0009-arm-multi_v7_defconfig-enable-rpmsg-proto-driver.patch \
"

# This hack to pick a different branch & SRCREV is needed for 
# 9.1 RT release for SITARA platforms (i.e am62xx, am64xx, am62pxx)
BRANCH:am62xx = "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "ti-rt-linux-6.1.y-jailhouse", "ti-rt-linux-6.1.y-cicd", d)}"
SRCREV:am62xx = "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "a63a1b1be1443add3bdae62cfcb7101ac483e190", "b871cdee8c31b877177e4e9d626d1d424052e32e", d)}"

BRANCH:am62pxx = "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "ti-rt-linux-6.1.y-jailhouse", "ti-rt-linux-6.1.y-cicd", d)}"
SRCREV:am62pxx = "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "a63a1b1be1443add3bdae62cfcb7101ac483e190", "b871cdee8c31b877177e4e9d626d1d424052e32e", d)}"

BRANCH:am64xx = "ti-rt-linux-6.1.y-cicd"
SRCREV:am64xx = "b871cdee8c31b877177e4e9d626d1d424052e32e"

SRCREV:tie-jailhouse = "a63a1b1be1443add3bdae62cfcb7101ac483e190"

PR:append = "_tisdk_6"

