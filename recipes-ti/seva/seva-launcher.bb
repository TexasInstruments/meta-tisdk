PR = "r3"
SUMMARY = "Seva Launcher Golang Binary"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4"

PV = "v1.0.3"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am62-aarch64;name=am62_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am68-aarch64;name=am68_launcher \
    https://github.com/TexasInstruments/seva/releases/download/${PV}/seva-launcher-am69-aarch64;name=am69_launcher \
"

SRC_URI[am62_launcher.sha256sum] = "5a5ca6d10c75aa289bb87d3e210152fea6dc679500d9f4c7453e4887ff550fe4"
SRC_URI[am68_launcher.sha256sum] = "15bfced2315752da0345f59a43d8cc1974914a26bec7ed2cbe9f82b6314e3b3d"
SRC_URI[am69_launcher.sha256sum] = "11f7862eb83fee6ceaa5920cb571c11a153558b2bfa9c56dac9d30c99567aa33"

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
