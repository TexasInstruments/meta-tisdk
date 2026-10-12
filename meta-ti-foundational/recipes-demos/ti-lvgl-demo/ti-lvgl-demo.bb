DESCRIPTION = "High Resolution OOB Demo"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

BRANCH = "legacy"
BRANCH:am62lxx-evm = "version_9.4"

SRC_URI = " \
    gitsm://github.com/TexasInstruments/lv_port_linux.git;protocol=https;branch=version_9.4;name=demo;destsuffix=lv_port_linux \
    git://github.com/TexasInstruments/lv_demos.git;protocol=https;branch=${BRANCH};name=lvdemos;destsuffix=git-lvdemos \
    file://ti-lvgl-demo.service \
    file://ti-high-res.cfg \
    ${@bb.utils.contains('PACKAGECONFIG', 'fbdev', 'file://fbdev.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'wayland', 'file://wayland.cfg', '', d)} \
"

S = "${UNPACKDIR}/lv_port_linux"

SRCREV_demo    = "5448e939cde01be46684e4fdfc661f849603bbc2"
SRCREV_lvdemos = "2256b9c056d2d75cc8304718e8d53ec6cf0039d7"
SRCREV_lvdemos:am62lxx-evm = "28b9e42694b6c81aeff97c4d908f7619e2073a89"
SRCREV_FORMAT = "demo_lvdemos"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4"

PV = "1.0"
PV .= "+git"

require recipes-graphics/lvgl/lv-conf.inc

KCONFIG_CONFIG_ROOTDIR = "${S}/lvgl"

inherit cmake pkgconfig systemd python3native

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

PACKAGECONFIG ??= "wayland"
PACKAGECONFIG:am62lxx-evm = "fbdev"
PACKAGECONFIG[fbdev] = ""
PACKAGECONFIG[wayland] = ""

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "ti-lvgl-demo.service"

DEPENDS += "alsa-lib curl libdrm libevdev libsdl2 libsdl2-image mosquitto pkgconfig-native python3-native python3-pcpp-native wayland wayland-native wayland-protocols"
RDEPENDS:${PN} += "alsa-tools alsa-utils analytics-demo-data ca-certificates curl mosquitto-clients nnstreamer tensorflow-lite"

export SDKTARGETSYSROOT = "${STAGING_DIR_TARGET}"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
    -DUSE_EXTERNAL_DEMOS=ON \
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DFETCHCONTENT_SOURCE_DIR_LV_DEMOS_EXT=${UNPACKDIR}/git-lvdemos \
"

TARGET_CC_ARCH:append = " -ffile-prefix-map=${UNPACKDIR}/git-lvdemos=/usr/src/debug/${PN}/${PV}"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${bindir}
    install -d ${D}${datadir}/ti-lvgl-demo/assets
    install -d ${D}${datadir}/ti-lvgl-demo/slides
    install -d ${D}${datadir}/ti-lvgl-demo/cert
    install -m 0755 ${B}/bin/lvglsim ${D}${bindir}
    cp ${CP_ARGS} ${UNPACKDIR}/git-lvdemos/src/high_res/assets/* ${D}${datadir}/ti-lvgl-demo/assets
    cp ${CP_ARGS} ${UNPACKDIR}/git-lvdemos/src/high_res/slides/* ${D}${datadir}/ti-lvgl-demo/slides
    install -m 0644 ${UNPACKDIR}/git-lvdemos/certs/AmazonRootCA1.pem ${D}${datadir}/ti-lvgl-demo/cert/
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/ti-lvgl-demo.service ${D}${systemd_system_unitdir}/ti-lvgl-demo.service
}

FILES:${PN} += " \
    ${bindir}/lvglsim \
    ${datadir}/ti-lvgl-demo/assets \
    ${datadir}/ti-lvgl-demo/slides \
    ${datadir}/ti-lvgl-demo/cert/AmazonRootCA1.pem \
    ${systemd_system_unitdir}/ti-lvgl-demo.service \
"
