PR = "r6"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4|j722s"

PV = "v1.0.6"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62p-aarch64;name=am62p_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am67-aarch64;name=am67_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
    file://seva-launcher.service \
"

SRC_URI[am62_launcher.sha256sum] = "703fb9d78e0af4e04d0bab2a2ad3e4771f360c3429217edfaf11a8bbbe961dad"
SRC_URI[am62p_launcher.sha256sum] = "1edbb3fe512dca80147c7f3b5bd86216b0ee43cd77a7a850bbf522abb6481c37"
SRC_URI[am67_launcher.sha256sum] = "3bb870d75f3bb7d57702d6786c762ca9667a0c44ec197919d1cfa279d847a97b"
SRC_URI[am68_launcher.sha256sum] = "bfbffe52d036d4f8e5396c6342327bcd1aa3a48b8adc6a76ba6cb56a3401e292"
SRC_URI[am69_launcher.sha256sum] = "34acda42803cf6e2aa5c2ee76a6f4f58511030d57ccb754e1cc628c29cde2a31"

LAUNCHER_SOC = "unknown"
LAUNCHER_SOC:am62xx = "am62"
LAUNCHER_SOC:am62pxx = "am62p"
LAUNCHER_SOC:j722s = "am67"
LAUNCHER_SOC:j721s2 = "am68"
LAUNCHER_SOC:j784s4 = "am69"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "seva-launcher.service"

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/seva-launcher-${LAUNCHER_SOC}-aarch64 ${D}/usr/bin/seva-launcher-aarch64

    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/seva-launcher.service ${D}${systemd_system_unitdir}/seva-launcher.service
}

FILES:${PN} = "/usr/bin/seva-launcher-aarch64"
