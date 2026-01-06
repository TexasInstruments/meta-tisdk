FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://asound.state \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CARD_NAME = ""
CARD_NAME:am62xx-evm = "AM62xSKEVM"
CARD_NAME:am62pxx-evm = "AM62xSKEVM"
CARD_NAME:am62axx-evm = "AM62AxSKEVM"
CARD_NAME:am62lxx-evm = "AM62LSKEVM"
CARD_NAME:am62dxx-evm = "AM62D2EVM"

do_install:append() {
    if [ -n "${CARD_NAME}" ]; then
	sed -i "s/state.example/state.${CARD_NAME}/"  ${UNPACKDIR}/asound.state
	install -d ${D}${localstatedir}/lib/alsa/
	install -m 0755 ${UNPACKDIR}/asound.state ${D}${localstatedir}/lib/alsa/asound.state
    fi
}

PR:append = "_tisdk_2"
