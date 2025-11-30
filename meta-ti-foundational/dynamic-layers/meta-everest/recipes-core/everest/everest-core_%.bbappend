FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://config-tida-010939-ac-hlc.yaml \
    file://config-tida-010939-ac-pwm.yaml \
    file://99-tida-010939.rules \
"
do_install:append() {
    install -d ${D}${sysconfdir}/everest/
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${UNPACKDIR}/config-tida-010939-ac-hlc.yaml ${D}${sysconfdir}/everest/
    install -m 0644 ${UNPACKDIR}/config-tida-010939-ac-pwm.yaml ${D}${sysconfdir}/everest/
    install -m 0644 ${UNPACKDIR}/99-tida-010939.rules ${D}${sysconfdir}/udev/rules.d/

    # link the hlc config as default to start
    ln -sf config-tida-010939-ac-hlc.yaml ${D}${sysconfdir}/everest/config.yaml
}

FILES:${PN}:append = "\
    ${sysconfdir}/everest/config-tida-010939-ac-hlc.yaml \
    ${sysconfdir}/everest/config-tida-010939-ac-pwm.yaml \
    ${sysconfdir}/udev/rules.d/99-tida-010939.rules \
"

PR:append = "_tisdk_1"
