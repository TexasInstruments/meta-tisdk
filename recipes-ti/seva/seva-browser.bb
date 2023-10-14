PR = "r1"
SUMMARY = "Seva Browser Docker Image Tarball"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "v1.0.0"

S = "${WORKDIR}"

SRC_URI = " \
    https://github.com/TexasInstruments/seva-browser/releases/download/${PV}/seva-browser.tar.gz;unpack=0;name=browser \
"

SRC_URI[browser.sha256sum] = "a812c4cb53647417e1c3a1580589dd7e7a9ae00612413d7e327c18a87c101bce"

do_install() {
    install -d ${D}/opt
    install -m 0755 ${S}/seva-browser.tar.gz ${D}/opt/
}

FILES:${PN} = "/opt/seva-browser.tar.gz"

