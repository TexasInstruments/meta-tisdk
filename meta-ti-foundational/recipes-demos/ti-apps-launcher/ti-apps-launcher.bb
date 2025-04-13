PR = "r19"

DESCRIPTION = "ti-apps-launcher service"
HOMEPAGE = "https://github.com/TexasInstruments/ti-apps-launcher"

COMPATIBLE_MACHINE = "am62xx|am62pxx|j721s2|j784s4|j722s"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c3a7f5f6886ba6f33ec3d214dc7ab4c"

DEPENDS = "\
    qtbase \
    qtbase-native \
    qttools \
    qt3d \
    qtquick3d \
    qtquick3d-native \
    qtdeclarative \
    qtmultimedia \
    gstreamer1.0 \
    qtdeclarative-native \
    qtshadertools \
    qt5compat \
    qtwayland \
"

RDEPENDS:${PN} = "\
    cmake \
    qtquick3d \
    qtmultimedia \
    qttools \
    qt3d \
    bash \
    pulseaudio-service \
    qtdeclarative-qmlplugins \
    qtwayland-qmlplugins \
    qtdeclarative-tools \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-good-qml6 \
    qt5compat \
    qtquick3d \
    qtwayland \
    tensorflow-lite \
    onnx \
    onnxruntime \
    nnstreamer \
    analytics-demo-data \
"

RDEPENDS:${PN}:remove:am62xxsip-evm = "seva-launcher"
RDEPENDS:${PN}:append:am62xx = " powervr-graphics"
RDEPENDS:${PN}:append:am62pxx = " powervr-graphics"

BRANCH = "master"
SRCREV = "918e886551b2c2968c6d62d5dd90d7e65de96f34"

SRC_URI = " \
    git://github.com/TexasInstruments/ti-apps-launcher.git;protocol=https;branch=${BRANCH} \
    file://ti-apps-launcher.service \
    file://ti-apps-launcher-eglfs.service \
    file://ti-demo.service \
    file://dev-dri-card1.rules \
    file://Usage.md \
"

S = "${WORKDIR}/git"

APPS_DEFINES = ""
APPS_DEFINES:am62xx = "SOC_AM62"
APPS_DEFINES:am62xx-lp-evm = "SOC_AM62_LP"
APPS_DEFINES:am62pxx-evm = "SOC_AM62P"
APPS_DEFINES:am62xxsip-evm = "SOC_AM62_LP"
APPS_DEFINES:j721s2 = "SOC_J721S2"
APPS_DEFINES:j784s4 = "SOC_J784S4"
APPS_DEFINES:j722s = "SOC_J722S"

SERVICE_SUFFIX = ""
SERVICE_SUFFIX:am62xxsip-evm = "-eglfs"

HW_CODEC = "0"
HW_CODEC:am62pxx = "1"
HW_CODEC:j721s2 = "1"
HW_CODEC:j784s4 = "1"
HW_CODEC:j722s = "1"

APP_NAME = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "ti-demo", "ti-apps-launcher", d)}"
RT_BUILD_VALUE = "${@oe.utils.conditional("ARAGO_RT_ENABLE", "1", "1", "0", d)}"

inherit systemd pkgconfig cmake

SYSTEMD_PACKAGES = "${PN}"

SYSTEMD_SERVICE:${PN} = "${APP_NAME}.service"

OECMAKE_CXX_FLAGS += "-D${APPS_DEFINES}=1"
OECMAKE_CXX_FLAGS += "-DRT_BUILD=${RT_BUILD_VALUE}"

EXTRA_OECMAKE = "-DOE_QMAKE_PATH_EXTERNAL_HOST_BINS=${STAGING_BINDIR_NATIVE}"
EXTRA_OECMAKE += "-DQT_HOST_PATH=${RECIPE_SYSROOT_NATIVE}${prefix_native}"

do_install:append() {
    if [ "${DISPLAY_CLUSTER_ENABLE}" != "1" ]; then
        install -d ${D}/opt/ti-apps-launcher
        install -m 0755 ${S}/scripts/* ${D}/opt/ti-apps-launcher/

        install -d ${D}${systemd_system_unitdir}
        install -m 0755 ${WORKDIR}/ti-apps-launcher${SERVICE_SUFFIX}.service ${D}${systemd_system_unitdir}/ti-apps-launcher.service

        if [ "${HW_CODEC}" = "1" ]; then
            install -d ${D}/opt/ti-apps-launcher/gallery
            install -m 0644 ${WORKDIR}/Usage.md ${D}/opt/ti-apps-launcher/gallery/
        fi
    else
        install -d ${D}/opt/ti-demo
        install -m 0755 ${S}/apps/auto_cluster.qml ${D}/opt/ti-demo/
        install -m 0755 ${S}/apps/CircularGauge.qml ${D}/opt/ti-demo/

        install -d ${D}${systemd_system_unitdir}
        install -m 0755 ${WORKDIR}/ti-demo.service ${D}${systemd_system_unitdir}/ti-demo.service
    fi

    if [ "${SERVICE_SUFFIX}" == "-eglfs" ] || [ "${DISPLAY_CLUSTER_ENABLE}" == "1" ]; then
        install -d ${D}${sysconfdir}/udev/rules.d
        install -m 0644 ${WORKDIR}/dev-dri-card1.rules ${D}${sysconfdir}/udev/rules.d/
    fi
}

FILES:${PN} += " \
    ${bindir}/${APP_NAME} \
    /opt/${APP_NAME}/ \
    ${sysconfdir} \
"
