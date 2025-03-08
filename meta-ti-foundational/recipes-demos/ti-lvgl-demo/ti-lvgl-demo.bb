DESCRIPTION = "High Resolution OOB Demo"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4"

SRC_URI = "gitsm://github.com/texasinstruments/ti-lvgl-demo.git;branch=master;protocol=https \
           file://ti-lvgl-demo.service \
          "
S = "${WORKDIR}/git/lv_port_linux"
B = "${S}/build-arm64"

SRCREV = "a1cec6469551d8ec6c1c0d2d5041c0c5432d9e3b"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "${PN}.service"

DEPENDS += "cmake mosquitto alsa-lib alsa-utils alsa-tools libdrm libsdl2 libsdl2-image curl wayland-protocols curl ca-certificates"
RDEPENDS:${PN} += "cmake mosquitto alsa-lib alsa-utils alsa-tools libdrm libsdl2 libsdl2-image curl wayland-protocols curl ca-certificates"

inherit cmake
OECMAKE_SOURCEPATH = "${S}"
EXTRA_OECMAKE += " \
      -DCMAKE_CXX_FLAGS=-O3 \
      -DCMAKE_C_FLAGS=-O3 \
      -DCMAKE_C_FLAGS=-I${STAGING_INCDIR}/libdrm \
      -DCMAKE_BUILD_TYPE=Release \
"

do_configure() {
    cmake -B ${S}/build-arm64 -S ${S} \
        ${EXTRA_OECMAKE}
}

do_compile() {
    make -C ${S}/build-arm64
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${bindir}
    install -d ${D}${datadir}/ti-lvgl-demo/assets
    install -d ${D}${datadir}/ti-lvgl-demo/slides
    install -d ${D}${datadir}/ti-lvgl-demo/cert
    install -m 0755 ${S}/bin/lvglsim ${D}${bindir}
    cp ${CP_ARGS} ${S}/lvgl/demos/high_res/assets/* ${D}${datadir}/ti-lvgl-demo/assets
    cp ${CP_ARGS} ${S}/lvgl/demos/high_res/slides/* ${D}${datadir}/ti-lvgl-demo/slides
    install -m 0755 ${S}/certs/AmazonRootCA1.pem ${D}${datadir}/ti-lvgl-demo/cert/

    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/ti-lvgl-demo.service ${D}${systemd_system_unitdir}/ti-lvgl-demo.service
}

FILES:${PN} += " \
    ${bindir}/lvglsim \
    ${datadir}/ti-lvgl-demo/assets \
    ${datadir}/ti-lvgl-demo/slides \
    ${systemd_system_unitdir}/ti-lvgl-demo.service \
    ${datadir}/ti-lvgl-demo/cert/AmazonRootCA1.pem \
"
