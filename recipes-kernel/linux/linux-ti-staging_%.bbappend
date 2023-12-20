FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:${THISDIR}/${PN}/${MACHINE}:"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
    file://0001-arm64-dts-ti-k3-j721e-sk-Enable-wkup-i2c0.patch \
"
SRC_URI:append:am57xx = " \
    file://0001-support-TI-GC320-drivers.patch \
    file://0001-disable-gcc-plugin-config.patch \
    file://0001-arm-dts-am57xx-beagle-x15-Disable-the-PRU-cores.patch \
    file://0001-arm-configs-omap5-Enable-support-for-QSPI-controller.patch \
    file://0002-arm-configs-omap5-Enable-support-for-DCAN-devices.patch \
"

SRCREV:tie-jailhouse = "7c0084f3017b4df54dff27f8742cff05bb960e6b"

PR:append = "_tisdk_6"

