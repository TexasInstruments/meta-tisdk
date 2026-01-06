PR:append = "_tisdk_0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://systemd-networkd-wait-online.service \
"

do_install:append() {
    install -d ${D}${base_libdir}
    install -m 0644 ${UNPACKDIR}/systemd-networkd-wait-online.service ${D}${systemd_system_unitdir}/
}
