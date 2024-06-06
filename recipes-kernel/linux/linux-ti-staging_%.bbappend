FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:${THISDIR}/${PN}/${MACHINE}:"

SRC_URI:append:j721e = " \
    file://0001-arm64-dts-k3-j721e-common-proc-board-Add-INA2xx-supp.patch \
"

SRCREV:tie-jailhouse = "51972f45459404c035940796ef148d2fbc6020fd"

PR:append = "_tisdk_3"

