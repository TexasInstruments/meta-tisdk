PR:append = "_tisdk_0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://systemd-networkd-wait-online.service \
"

SRC_URI:append:omap-a15 = " \
    file://sys-clock-drift.service \
"

do_install:append() {

    install -d ${D}${base_libdir}
    install -m 0644 ${WORKDIR}/systemd-networkd-wait-online.service ${D}${base_libdir}/systemd/system/
}

do_install:append:omap-a15() {
    install -m 0644 ${WORKDIR}/sys-clock-drift.service ${D}${sysconfdir}/systemd/system/
    ln -sf ../sys-clock-drift.service ${D}${sysconfdir}/systemd/system/sysinit.target.wants/sys-clock-drift.service

}
