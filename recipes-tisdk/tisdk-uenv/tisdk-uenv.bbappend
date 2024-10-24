FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/${MACHINE}:${THISDIR}/${PN}:"

SRC_URI:append:j722s = "\
    file://uEnv-sk.txt \
"

SRC_URI:append:am62pxx = "\
    file://uEnv-am62p-display-cluster.txt \
"

do_deploy:j722s:foundational() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/uEnv-sk.txt ${DEPLOYDIR}/uEnv.txt
}

do_deploy:am62pxx() {
    install -d ${DEPLOYDIR}
    if [ "${DISPLAY_CLUSTER_ENABLE}" == "1" ]; then
        install -m 0644 ${S}/uEnv-am62p-display-cluster.txt ${DEPLOYDIR}/uEnv.txt
    else
        install -m 0644 ${S}/uEnv.txt ${DEPLOYDIR}
    fi
}

PR:append = "_tisdk_1"

