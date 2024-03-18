FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:${THISDIR}/${PN}/${MACHINE}:"

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

SRCREV:tie-jailhouse = "7c0084f3017b4df54dff27f8742cff05bb960e6b"

PR:append = "_tisdk_5"

