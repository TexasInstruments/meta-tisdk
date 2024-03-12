FILESEXTRAPATHS:prepend := "${THISDIR}/linux-ti-staging:${THISDIR}/linux-ti-staging/${MACHINE}:"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
    file://0001-arm64-dts-ti-k3-j721e-sk-Enable-wkup-i2c0.patch \
"

SRCREV:tie-jailhouse = "c143b94d28fb42536f50c345fa0e0050f8b881dd"

PR:append = "_tisdk_6"

