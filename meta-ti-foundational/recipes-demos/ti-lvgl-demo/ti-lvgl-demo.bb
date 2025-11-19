DESCRIPTION = "High Resolution OOB Demo"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4"

BRANCH = "legacy"
BRANCH:am62lxx-evm = "master"

SRC_URI = "gitsm://github.com/texasinstruments/ti-lvgl-demo.git;branch=${BRANCH};protocol=https;name=main \
           git://github.com/TexasInstruments/lv_demos.git;protocol=https;branch=legacy;name=lvdemos;destsuffix=git-lvdemos \
           file://ti-lvgl-demo.service \
          "
SRC_URI:remove:am62lxx-evm = "git://github.com/TexasInstruments/lv_demos.git;protocol=https;branch=legacy;name=lvdemos;destsuffix=git-lvdemos"

S = "${WORKDIR}/git/lv_port_linux"
B = "${S}/build-arm64"

SRCREV_main = "edc14fdab29376d9642ee057c9a2095fdc58416a"
SRCREV_main:am62lxx-evm = "231f5d956c83f934c6b4175870d867a61ae5bc32"
SRCREV_lvdemos = "de9c755979b690a2064b80d993bd14f0be7eff5b"
SRCREV_FORMAT = "main_lvdemos"

inherit cmake systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "${PN}.service"

DEPENDS += "cmake mosquitto alsa-lib alsa-utils alsa-tools libdrm libsdl2 libsdl2-image curl wayland-protocols curl ca-certificates python3-pcpp-native python3-native wayland wayland-native"
RDEPENDS:${PN} += "cmake mosquitto alsa-lib alsa-utils alsa-tools libdrm libsdl2 libsdl2-image mosquitto-clients curl wayland-protocols curl ca-certificates tensorflow-lite nnstreamer analytics-demo-data"

OECMAKE_SOURCEPATH = "${S}"

# Configure Python for build-time script execution
export Python3_EXECUTABLE = "${STAGING_BINDIR_NATIVE}/python3-native/python3"
# Set target sysroot for Wayland protocol discovery
export SDKTARGETSYSROOT = "${STAGING_DIR_TARGET}"

# Disable CMake FetchContent from downloading sources during do_configure stage and point FetchContent to our pre-fetched lv_demos repository
EXTRA_OECMAKE += " \
      -DCMAKE_CXX_FLAGS=-O3 \
      -DCMAKE_C_FLAGS=-O3 \
      -DCMAKE_C_FLAGS=-I${STAGING_INCDIR}/libdrm \
      -DCMAKE_BUILD_TYPE=Release \
      -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
      -DFETCHCONTENT_SOURCE_DIR_LV_DEMOS_EXT=${WORKDIR}/git-lvdemos \
"

do_configure() {
    # Set up Python environment to find pcpp module in native sysroot
    export PYTHONPATH="${STAGING_LIBDIR_NATIVE}/${PYTHON_DIR}/site-packages:${PYTHONPATH}"
    export PATH="${STAGING_BINDIR_NATIVE}/python3-native:${PATH}"
    cmake -B ${B} -S ${S} ${EXTRA_OECMAKE}
}

do_compile() {
    oe_runmake -C ${B}
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${bindir}
    install -d ${D}${datadir}/ti-lvgl-demo/assets
    install -d ${D}${datadir}/ti-lvgl-demo/slides
    install -d ${D}${datadir}/ti-lvgl-demo/cert
    if [ "${MACHINE}" = "am62lxx-evm" ]; then
        install -m 0755 ${S}/bin/lvglsim ${D}${bindir}
        cp ${CP_ARGS} ${S}/lvgl/demos/high_res/assets/* ${D}${datadir}/ti-lvgl-demo/assets
        cp ${CP_ARGS} ${S}/lvgl/demos/high_res/slides/* ${D}${datadir}/ti-lvgl-demo/slides
    else
        install -m 0755 ${B}/bin/lvglsim ${D}${bindir}
        cp ${CP_ARGS} ${WORKDIR}/git-lvdemos/src/high_res/assets/* ${D}${datadir}/ti-lvgl-demo/assets
        cp ${CP_ARGS} ${WORKDIR}/git-lvdemos/src/high_res/slides/* ${D}${datadir}/ti-lvgl-demo/slides
    fi

    # Install certificate with correct permissions (not executable)
    install -m 0644 ${S}/certs/AmazonRootCA1.pem ${D}${datadir}/ti-lvgl-demo/cert/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ti-lvgl-demo.service ${D}${systemd_system_unitdir}/ti-lvgl-demo.service
}

FILES:${PN} += " \
    ${bindir}/lvglsim \
    ${datadir}/ti-lvgl-demo/assets \
    ${datadir}/ti-lvgl-demo/slides \
    ${systemd_system_unitdir}/ti-lvgl-demo.service \
    ${datadir}/ti-lvgl-demo/cert/AmazonRootCA1.pem \
"
