FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://asound.state \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CARD_NAME = ""
CARD_NAME:am62xx = "AM62xSKEVM"
CARD_NAME:am62pxx = "AM62xSKEVM"
CARD_NAME:am62axx = "AM62AxSKEVM"
CARD_NAME:am62lxx = "AM62LSKEVM"

do_install:append() {
    if [ -n "${CARD_NAME}" ]; then
	sed -i "s/state.example/state.${CARD_NAME}/"  ${WORKDIR}/asound.state
	install -d ${D}/var/lib/alsa/
	install -m 0755 ${WORKDIR}/asound.state ${D}/var/lib/alsa/asound.state
    fi
}

PR:append = "_tisdk_1"
