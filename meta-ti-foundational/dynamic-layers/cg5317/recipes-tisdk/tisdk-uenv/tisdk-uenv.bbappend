FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/${MACHINE}:"

SRC_URI:append:am62lxx = "\
    file://uEnv-am62l-sk-evse.txt \
"

do_deploy:am62lxx() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/uEnv-am62l-sk-evse.txt ${DEPLOYDIR}/uEnv.txt
}

PR:append = "_tisdk_0"
