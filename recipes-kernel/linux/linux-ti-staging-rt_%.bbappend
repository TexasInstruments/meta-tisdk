FILESEXTRAPATHS:prepend := "${THISDIR}/linux-ti-staging:${THISDIR}/linux-ti-staging/${MACHINE}:"

SRC_URI:append = " \
    file://0001-net-ethernet-ti-j721e-cpsw-virt-mac-Exit-notificatio.patch \
"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
    file://0001-arm64-dts-ti-k3-j721e-sk-Enable-wkup-i2c0.patch \
"

PR:append = "_tisdk_0"

