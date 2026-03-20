DESCRIPTION = "TIDA-010939 specific Everest configs"
HOMEPAGE = "https://github.com/TexasInstruments/tida-010939-everest-config"
BUGTRACKER = "https://github.com/TexasInstruments/tida-010939-everest-config/issues"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/TexasInstruments/tida-010939-everest-config.git;protocol=https;branch=main"
SRCREV = "4c49fd780b926605831eb733b592560f49fdc8c1"

S = "${WORKDIR}/git"

FILES:${PN} += "${sysconfdir}/everest/*"

RDEPENDS:${PN} = "everest-core"

do_install() {
    install -d ${D}${sysconfdir}/everest/
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${S}/everest/config-tida-010939-ac-hlc.yaml ${D}${sysconfdir}/everest/
    install -m 0644 ${S}/everest/config-tida-010939-ac-pwm.yaml ${D}${sysconfdir}/everest/
    install -m 0644 ${S}/udev-rules/99-tida-010939.rules ${D}${sysconfdir}/udev/rules.d/

    # link the hlc config as default to start
    ln -sf config-tida-010939-ac-hlc.yaml ${D}${sysconfdir}/everest/config.yaml
}
