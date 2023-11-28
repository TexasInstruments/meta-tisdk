PR = "r4"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4"

PV = "v1.0.5"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62p-aarch64;name=am62p_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
"

SRC_URI[am62_launcher.sha256sum] = "49ca65cf6a626eecefd3a8498ef932d0558505ee9af7ef7be5b285aa327db6fd"
SRC_URI[am62p_launcher.sha256sum] = "4974181abce8b9404fb320df0befae345b43f2f19a6edc6e4cb0c9af946acd62"
SRC_URI[am68_launcher.sha256sum] = "274eb2d2b027c5b95e405acc4a7dbbf609416df4250c63b4fe692223205bb44e"
SRC_URI[am69_launcher.sha256sum] = "c0f76fa9ac1ba3bafd2e8b5eecf5326c616fa2df2d6101cde014357fb781cceb"

RDEPENDS:${PN} = " seva-browser"

LAUNCHER_SOC = "unknown"
LAUNCHER_SOC:am62xx = "am62"
LAUNCHER_SOC:am62pxx = "am62p"
LAUNCHER_SOC:j721s2 = "am68"
LAUNCHER_SOC:j784s4 = "am69"

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/seva-launcher-${LAUNCHER_SOC}-aarch64 ${D}/usr/bin/seva-launcher-aarch64
}

FILES:${PN} = "/usr/bin/seva-launcher-aarch64"
