FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PR:append = "_tisdk_0"

SRC_URI += " \
    file://sudoers-weston \
"

do_install:append() {
    install -d ${D}${sysconfdir}/sudoers.d/
    install  ${UNPACKDIR}/sudoers-weston ${D}${sysconfdir}/sudoers.d/
}

FILES:${PN} += " ${sysconfdir}/sudoers.d/sudoers-weston"
