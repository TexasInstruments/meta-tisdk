PR = "r2"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TSPA;md5=bb6bc27cd44417c389a180bd62f552a0"

COMPATIBLE_MACHINE = "am62xx|j721s2|j784s4"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "v1.0.2"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
"

SRC_URI[am62_launcher.sha256sum] = "a923a0c6d6aa099a15eea227530f0306bdac2f5ef4681d0cd23cdde09dc87209"
SRC_URI[am68_launcher.sha256sum] = "15daa4e2a467ee6476a687632edcaaf8f23a159e1d6ea9f82e60351b1b1a8105"
SRC_URI[am69_launcher.sha256sum] = "f319d1053e99fdc7cc796b90715f5f74aa609b2c43f50e2fdd98f4d28f4cda11"

LAUNCHER_SOC = "unknown"
LAUNCHER_SOC:am62xx = "am62"
LAUNCHER_SOC:j721s2 = "am68"
LAUNCHER_SOC:j784s4 = "am69"

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/seva-launcher-${LAUNCHER_SOC}-aarch64 ${D}/usr/bin/seva-launcher-aarch64
}

FILES:${PN} = "/usr/bin/seva-launcher-aarch64"
