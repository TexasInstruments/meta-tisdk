PR = "r3"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4"

PV = "v1.0.4"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
"

SRC_URI[am62_launcher.sha256sum] = "4fc98fed13a67ea57455e0605da634c40d4e12b3a22ca5572d7f98fae8f4ee8a"
SRC_URI[am68_launcher.sha256sum] = "8aa5a697526ea416e5c905ba65ad1b9812a8bd5b6b97067e06736360006185ce"
SRC_URI[am69_launcher.sha256sum] = "fbb4a8ffdfbe5989a45bfa382264b53c1899c1feaff513ccb3463979e082e36b"

RDEPENDS:${PN} = " seva-browser"

LAUNCHER_SOC = "unknown"
LAUNCHER_SOC:am62xx = "am62"
LAUNCHER_SOC:am62pxx = "am62"
LAUNCHER_SOC:j721s2 = "am68"
LAUNCHER_SOC:j784s4 = "am69"

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/seva-launcher-${LAUNCHER_SOC}-aarch64 ${D}/usr/bin/seva-launcher-aarch64
}

FILES:${PN} = "/usr/bin/seva-launcher-aarch64"
