PR = "r6"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4|j722s"

PV = "v1.0.7"

S = "${UNPACKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62p-aarch64;name=am62p_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am67-aarch64;name=am67_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
    file://seva-launcher.service \
"

SRC_URI[am62_launcher.sha256sum] = "98d6c511028e13c22cb10b84742be10020b8b6449b53e680ef5224196d35f9ee"
SRC_URI[am62p_launcher.sha256sum] = "73ad9e52c5e3b8584f9617a681fd25285a2e46fb0605c390822956d9326a034f"
SRC_URI[am67_launcher.sha256sum] = "b41f3569e2d1e79186a7c1f50a076c2fa8803d9946c5ee7e10084e376ea862d5"
SRC_URI[am68_launcher.sha256sum] = "be65a3a3cf1f8acd95b576db8c7f84c714867ebb1422779107d1bf921b33f0e8"
SRC_URI[am69_launcher.sha256sum] = "9a7970d7cdecd5624232a3ecfbc62ffd5cabe1bf35f9350f04040462e6e843ac"

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
    install -m 0755 ${UNPACKDIR}/seva-launcher.service ${D}${systemd_system_unitdir}/seva-launcher.service
}

FILES:${PN} = "/usr/bin/seva-launcher-aarch64"
