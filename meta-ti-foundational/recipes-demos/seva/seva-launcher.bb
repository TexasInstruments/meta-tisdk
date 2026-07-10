PR = "r6"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4|j722s"

PV = "v1.0.8"

S = "${UNPACKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62p-aarch64;name=am62p_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am67-aarch64;name=am67_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
    file://seva-launcher.service \
"

SRC_URI[am62_launcher.sha256sum] = "c4e12a31c64f5261d0d52ae84c8309054ac5c007170b9dacf2c519434c8d2047"
SRC_URI[am62p_launcher.sha256sum] = "476257af7d57275ea22661fd4fac771953c31c923756cae26ee5cd454e4d2b86"
SRC_URI[am67_launcher.sha256sum] = "2f39f24622cdc055fd9591c267b3bb9fe19fd793830e559dd6926ddbe40b3e25"
SRC_URI[am68_launcher.sha256sum] = "46c0087fc15cdc8e88a0f0e72c9ea7bdaeefc6fcd826bb0913260986a76b1a25"
SRC_URI[am69_launcher.sha256sum] = "2a223e21a1a801646ca8ea7c9c34fe19392ac7145eb005ec2c8e999d8aacfdf0"

LAUNCHER_SOC = "unknown"
LAUNCHER_SOC:am62xx = "am62"
LAUNCHER_SOC:am62pxx = "am62p"
LAUNCHER_SOC:j722s = "am67"
LAUNCHER_SOC:j721s2 = "am68"
LAUNCHER_SOC:j784s4 = "am69"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "seva-launcher.service"

RDEPENDS:${PN} += " docker-compose"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/seva-launcher-${LAUNCHER_SOC}-aarch64 ${D}${bindir}/seva-launcher-aarch64

    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${UNPACKDIR}/seva-launcher.service ${D}${systemd_system_unitdir}/seva-launcher.service
}

FILES:${PN} = "${bindir}/seva-launcher-aarch64"
