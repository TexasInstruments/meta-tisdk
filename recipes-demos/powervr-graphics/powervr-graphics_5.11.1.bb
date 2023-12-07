PR = "r0"

DESCRIPTION = "Imagination PowerVR SDK binaries/examples"
HOMEPAGE = "https://docs.imgtec.com"

COMPATIBLE_MACHINE = "am62xx|am62pxx"

SRC_URI = " \
    gitsm://github.com/powervr-graphics/Native_SDK.git;protocol=https;branch=${BRANCH} \
    file://0001-PATCH-use-library-so-names-for-linking.patch \
"

BRANCH = "master"
SRCREV = "3576dbd5c651c4c92517af83dbd9fdf394a16b22"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=402476d9302b00251cc699d23264b191"

S = "${WORKDIR}/git"
SRC_DIR = "arm"
SRC_DIR:k3 = "armv8_64"

inherit cmake pkgconfig

export http_proxy
export https_proxy

EXTRA_OECMAKE += " -DPVR_WINDOW_SYSTEM=Wayland -DCMAKE_LIBRARY_PATH= -DPVR_BUILD_OPENGLES_EXAMPLES=On -DPVR_BUILD_VULKAN_EXAMPLES=Off"

do_install () {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    install -d ${D}${bindir}/SGX/demos/Wayland/

    cp ${CP_ARGS} ${WORKDIR}/build/bin/Assets_OpenGLESSkinning ${D}${bindir}/SGX/demos/Wayland/
    cp ${CP_ARGS} ${WORKDIR}/build/bin/OpenGLESSkinning ${D}${bindir}/SGX/demos/Wayland/

    cp ${CP_ARGS} ${WORKDIR}/build/bin/Assets_OpenGLESBinaryShaders ${D}${bindir}/SGX/demos/Wayland/
    cp ${CP_ARGS} ${WORKDIR}/build/bin/OpenGLESBinaryShaders ${D}${bindir}/SGX/demos/Wayland/
}

DEPENDS = "wayland wayland-native wayland-protocols"
RDEPENDS:${PN} = "wayland libegl wayland-protocols"

FILES:${PN} += " \
    /opt/img-powervr-sdk/* \
    ${bindir}/SGX/demos/Wayland/* \
"

