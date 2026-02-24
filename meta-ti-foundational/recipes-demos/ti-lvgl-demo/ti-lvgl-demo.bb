DESCRIPTION = "High Resolution OOB Demo"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}/LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4"

BRANCH = "legacy"
BRANCH:am62lxx-evm = "version_9.4"

SRC_URI = "gitsm://github.com/texasinstruments/ti-lvgl-demo.git;branch=${BRANCH};protocol=https;name=main \
           git://github.com/TexasInstruments/lv_demos.git;protocol=https;branch=${BRANCH};name=lvdemos;destsuffix=git-lvdemos \
           file://ti-lvgl-demo.service \
          "

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}/lv_port_linux"

SRCREV_main = "edc14fdab29376d9642ee057c9a2095fdc58416a"
SRCREV_main:am62lxx-evm = "a69da8bd3a163e3ee7eebc96b34fd51f0788fdb8"
SRCREV_lvdemos = "de9c755979b690a2064b80d993bd14f0be7eff5b"
SRCREV_lvdemos:am62lxx-evm = "8e41791a2dce961ff9e157b413dcea2217301146"
SRCREV_FORMAT = "main_lvdemos"

inherit cmake pkgconfig systemd python3native

# Disable debug package to avoid buildpaths QA issue
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "${PN}.service"

DEPENDS += "alsa-lib curl libdrm libevdev libsdl2 libsdl2-image mosquitto pkgconfig-native python3-native python3-pcpp-native wayland wayland-native wayland-protocols"
RDEPENDS:${PN} += "alsa-tools alsa-utils ca-certificates curl mosquitto-clients"

# Ensure build system uses correct target sysroot for cross-compilation
export SDKTARGETSYSROOT = "${STAGING_DIR_TARGET}"

# Disable CMake FetchContent from downloading sources during do_configure stage and point FetchContent to our pre-fetched lv_demos repository
EXTRA_OECMAKE += " \
      -DCMAKE_BUILD_TYPE=Release \
      -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
      -DFETCHCONTENT_SOURCE_DIR_LV_DEMOS_EXT=${UNPACKDIR}/git-lvdemos \
"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${bindir}
    install -d ${D}${datadir}/ti-lvgl-demo/assets
    install -d ${D}${datadir}/ti-lvgl-demo/slides
    install -d ${D}${datadir}/ti-lvgl-demo/cert
    install -m 0755 ${B}/bin/lvglsim ${D}${bindir}
    cp ${CP_ARGS} ${UNPACKDIR}/git-lvdemos/src/high_res/assets/* ${D}${datadir}/ti-lvgl-demo/assets
    cp ${CP_ARGS} ${UNPACKDIR}/git-lvdemos/src/high_res/slides/* ${D}${datadir}/ti-lvgl-demo/slides

    # Install certificate with correct permissions (not executable)
    install -m 0644 ${S}/certs/AmazonRootCA1.pem ${D}${datadir}/ti-lvgl-demo/cert/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/ti-lvgl-demo.service ${D}${systemd_system_unitdir}/ti-lvgl-demo.service
}

FILES:${PN} += " \
    ${bindir}/lvglsim \
    ${datadir}/ti-lvgl-demo/assets \
    ${datadir}/ti-lvgl-demo/slides \
    ${systemd_system_unitdir}/ti-lvgl-demo.service \
    ${datadir}/ti-lvgl-demo/cert/AmazonRootCA1.pem \
"
