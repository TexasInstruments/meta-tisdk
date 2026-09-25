SUMMARY = "WiFi out-of-box experience launcher"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r1"

COMPATIBLE_MACHINE = "am64xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://startwlanap.sh \
    file://startwlanap.service \
    file://01-wlan1-static.network \
    file://startwlansta.service \
    file://startwlansta.sh \
    file://wificfg \
"

RDEPENDS:${PN} += " bash python3 iw iperf3 iproute2 wl18xx-fw wl18xx-target-scripts"

S = "${UNPACKDIR}"

inherit systemd deploy

SYSTEMD_SERVICE:${PN} = "startwlanap.service startwlansta.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${UNPACKDIR}/01-wlan1-static.network ${D}${sysconfdir}/systemd/network

    install -d ${D}${datadir}/wl18xx
    install -m 0755 ${UNPACKDIR}/startwlanap.sh ${D}${datadir}/wl18xx
    install -m 0755 ${UNPACKDIR}/startwlansta.sh ${D}${datadir}/wl18xx

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/startwlanap.service ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/startwlansta.service ${D}${systemd_system_unitdir}
}

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0644 ${UNPACKDIR}/wificfg ${DEPLOY_DIR_IMAGE}
}
addtask deploy after do_install before do_build

FILES:${PN} += " \
    ${sysconfdir}/systemd/network/01-wlan1-static.network \
    ${systemd_system_unitdir}/startwlanap.service \
    ${systemd_system_unitdir}/startwlansta.service \
    ${datadir}/wl18xx/ \
"
