SUMMARY = "Webserver Demo"
LICENSE = "BSD-3-Clause & MIT & ISC"

COMPATIBLE_MACHINE = "ti33x|am62xx|am62pxx|am62lxx|am62dxx|am64xx"

# Maps to repo devices/<DEVICE_ID>/ directory.
# Use SOC_FAMILY overrides where one override covers multiple machines.
DEVICE_ID = "unknown"
DEVICE_ID:ti33x = "am335x"
DEVICE_ID:am62xx = "am62xx"
DEVICE_ID:am62pxx = "am62pxx"
DEVICE_ID:am62lxx = "am62lxx"
DEVICE_ID:am62dxx = "am62dxx"
DEVICE_ID:am64xx = "am64xx"

# NPM pulls in many "independent" packages into a single app package
LIC_FILES_CHKSUM = "\
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
    file://node_modules/express/LICENSE;md5=5513c00a5c36cd361da863dd9aa8875d \
    file://node_modules/ws/LICENSE;md5=7a4bd929a6c0e6951846d75e53fc9f51 \
    file://node_modules/inherits/LICENSE;md5=5b2ef2247af6d355ae9d9f988092d470 \
    file://node_modules/setprototypeof/LICENSE;md5=4846f1626304c2c0f806a539bbc7d54a \
"

SRC_URI = " \
    git://github.com/TexasInstruments/webserver-oob-demo.git;protocol=https;branch=main \
    git://git.ti.com/git/gui-composer-components/ti-gc-components.git;protocol=https;branch=master;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/webserver_app/app/components;name=guicomposer \
    npmsw://${NPM_SHRINKWRAP};destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/webserver_app/webserver \
"
SRCREV = "8b5947ff2c3bbe6b01d7e6c968c24815a3ef1086"
SRCREV_guicomposer = "18115d266ba9f1956d06258ce2c8997fd1ef2efe"
SRCREV_FORMAT = "default"
PV = "1.0.0"

RDEPENDS:${PN} = "nodejs tensorflow-lite nnstreamer analytics-demo-data"
RDEPENDS:${PN}:append:am64xx = " benchmark-demo-firmware"

RDEPENDS:${PN} = "tensorflow-lite nnstreamer analytics-demo-data"

inherit npm systemd

# Must be set after inherit npm since that itself sets S

WEBSERVER_ROOT = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}/webserver_app"

# Set this for npm.bbclass
S = "${WEBSERVER_ROOT}/webserver"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install:append() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    # Build Linux utilities
    oe_runmake -C ${WEBSERVER_ROOT}/linux_app

    # Install Linux utilities to /usr/bin (webserver uses these)
    install -d ${D}${bindir}
    chmod +x ${D}${nonarch_libdir}/node_modules/${BPN}/server.js
    ln -s ${nonarch_libdir}/node_modules/${BPN}/server.js ${D}${bindir}/webserver-oob

    install -m 0755 ${WEBSERVER_ROOT}/common/linux_app/cpu_stats ${D}${bindir}/cpu_stats
    if [ -f ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/audio_utils ]; then
        install -m 0755 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/audio_utils ${D}${bindir}/audio_utils
    fi

    # Install demos
    install -d ${D}${datadir}/${BPN}/demos
    cp $CP_ARGS ${WEBSERVER_ROOT}/demos/. ${D}${datadir}/${BPN}/demos/

    # Install GUI Composer app to /usr/share/webserver-oob/app
    install -d ${D}${datadir}/${BPN}/app
    cp $CP_ARGS ${WEBSERVER_ROOT}/app/* ${D}${datadir}/${BPN}/app/

    # Remove git directory if present
    rm -rf ${D}${datadir}/${BPN}/app/components/.git*

    # GUI composer provides some examples, remove the binaries for now
    find ${D}${datadir}/${BPN}/app/components -name '*\.out' -exec rm {} \;
    find ${D}${datadir}/${BPN}/app/components -name '*\.exe' -exec rm {} \;

    # Install systemd service file to proper location
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/webserver-oob.service ${D}${systemd_system_unitdir}/webserver-oob.service

    # Install config file to /etc
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/webserver-oob.conf ${D}${sysconfdir}/webserver-oob.conf

    # Update config file to point to correct APP_DIR
    sed -i -e 's|^APP_DIR=.*$|APP_DIR='"${datadir}/${BPN}/app"'|' ${D}${sysconfdir}/webserver-oob.conf
}


do_install:append:am64xx() {

    install -m 0755 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/rpmsg_json ${D}${bindir}/rpmsg_json
    install -m 0644 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/rpmsg-json.service ${D}${systemd_system_unitdir}/

}

SYSTEMD_SERVICE:${PN} = "webserver-oob.service"
SYSTEMD_SERVICE:${PN}:append:am64xx = " rpmsg-json.service"

FILES:${PN} = " \
    ${bindir}/webserver-oob \
    ${bindir}/cpu_stats \
    ${bindir}/audio_utils \
    ${nonarch_libdir}/node_modules/${BPN} \
    ${datadir}/${BPN}/demos \
    ${systemd_system_unitdir}/webserver-oob.service \
    ${datadir}/${BPN}/app \
    ${sysconfdir}/webserver-oob.conf \
"

FILES:${PN}:append:am64xx = " ${bindir}/rpmsg_json ${systemd_system_unitdir}/rpmsg-json.service"

PR = "r1"
