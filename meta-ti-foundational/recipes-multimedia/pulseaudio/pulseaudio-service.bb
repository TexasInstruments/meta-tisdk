DESCRIPTION = "Pulseaudio systemd service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

DEPENDS = "pulseaudio"

SRC_URI += "\
    file://pulseaudio.service \
"

S = "${UNPACKDIR}"
SYSTEMD_SERVICE:${PN} = "pulseaudio.service"

FILES:${PN} = " \
    ${systemd_unitdir} \
"

RDEPENDS:${PN} += "shadow"

pkg_postinst_ontarget:${PN}() {
    usermod -a -G audio root
}

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${UNPACKDIR}/pulseaudio.service ${D}${systemd_system_unitdir}/
    fi
}

PR = "r0"
