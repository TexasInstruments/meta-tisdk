PR = "r10"

DESCRIPTION = "ti-apps-launcher service"
HOMEPAGE = "https://github.com/TexasInstruments/ti-apps-launcher"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=5c3a7f5f6886ba6f33ec3d214dc7ab4c"

DEPENDS = "qtbase qtquick3d qtmultimedia"
RDEPENDS:${PN} = "qtquick3d qtmultimedia bash seva-launcher pulseaudio-service"
RDEPENDS:${PN}:remove:am62xxsip-evm = "seva-launcher"
RDEPENDS:${PN}:append:am62xx = " powervr-graphics"

BRANCH = "master"
SRCREV = "cbec12637278c76991dc721fa672f989196be94b"

SRC_URI = " \
    git://github.com/TexasInstruments/ti-apps-launcher.git;protocol=https;branch=${BRANCH} \
    file://ti-apps-launcher.service \
    file://ti-apps-launcher-eglfs.service \
    file://ti-apps-launcher-analytics.service \
"

S = "${WORKDIR}/git"

APPS_DEFINES = ""
APPS_DEFINES:am62xx = "SOC_AM62"
APPS_DEFINES:am62xx-lp-evm = "SOC_AM62_LP"
APPS_DEFINES:am62pxx-evm = "SOC_AM62_LP"
APPS_DEFINES:am62xxsip-evm = "SOC_AM62_LP"
APPS_DEFINES:j721s2 = "SOC_J721S2"
APPS_DEFINES:j784s4 = "SOC_J784S4"

CONFIG_FILE = "generic"
CONFIG_FILE:am62xx = "am62xx-evm"
CONFIG_FILE:am62xx-lp-evm = "am62xx-lp-evm"
CONFIG_FILE:am62pxx-evm = "am62pxx-evm"
CONFIG_FILE:am62xxsip-evm = "am62xxsip-evm"
CONFIG_FILE:j721s2 = "am68-sk"
CONFIG_FILE:j784s4 = "am69-sk"

SERVICE_SUFFIX = ""
SERVICE_SUFFIX:am62xx = "-analytics"
SERVICE_SUFFIX:am62xxsip-evm = "-eglfs"

inherit qmake5 deploy systemd

SYSTEMD_PACKAGES = "${PN}"

SYSTEMD_SERVICE:${PN} = "ti-apps-launcher.service"

do_compile:prepend() {
    echo "SOURCES += configs/${CONFIG_FILE}.cpp" >> ${S}/ti-apps-launcher.pro
    echo >> ${S}/ti-apps-launcher.pro
    echo "DEFINES += ${APPS_DEFINES}" >> ${S}/ti-apps-launcher.pro
}

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ti-apps-launcher ${D}${bindir}/ti-apps-launcher

    install -d ${D}/opt/ti-apps-launcher
    install -m 0755 ${S}/scripts/* ${D}/opt/ti-apps-launcher/

    install -d ${D}/opt/ti-apps-launcher/assets
    install -m 0755 ${S}/images/* ${D}/opt/ti-apps-launcher/assets/
    install -m 0755 ${S}/audios/* ${D}/opt/ti-apps-launcher/assets/

    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/ti-apps-launcher${SERVICE_SUFFIX}.service ${D}${systemd_system_unitdir}/ti-apps-launcher.service
}

FILES:${PN} += " \
    ${bindir}/ti-apps-launcher \
    /opt/ti-apps-launcher/ \
"
