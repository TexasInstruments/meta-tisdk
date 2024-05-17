FILESEXTRAPATHS:prepend := "${THISDIR}/linux-ti-staging:${THISDIR}/linux-ti-staging/${MACHINE}:"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
    file://0001-arm64-dts-ti-k3-j721e-sk-Enable-wkup-i2c0.patch \
"

SRCREV:tie-jailhouse = "3c0d73833e537ba18f63ba91f384349a1d76f340"

PR:append = "_tisdk_6"

