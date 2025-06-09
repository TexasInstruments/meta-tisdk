SUMMARY = "Custom arm-only GStreamer plugins for TI devices"
DESCRIPTION = "TI GST arm-only plugins which internally uses Arm NEON optimized kernels on TI Sitara devices"
HOMEPAGE = "https://github.com/TexasInstruments/edgeai-gst-plugins"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1f7721ee7d288457c5a70d0c8ff44b87"

PV = "1.0.0"
BRANCH = "main"
SRC_URI = "git://github.com/TexasInstruments/edgeai-gst-plugins.git;branch=${BRANCH};protocol=https"
SRCREV = "39577530481ba0efe5eb0e20becb6442d84d29ac"

PLAT_SOC = ""
PLAT_SOC:am62xx = "am62x"
PLAT_SOC:am62pxx = "am62p"

S = "${WORKDIR}/git"

DEPENDS = "gstreamer1.0-plugins-base ti-gst-utils"
RDEPENDS:${PN}-source = "bash meson ninja"

COMPATIBLE_MACHINE = "am62xx|am62pxx"

export SOC = "${PLAT_SOC}"

PACKAGES += "${PN}-source"
FILES:${PN}-source += "/opt/"
FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"

EXTRA_OEMESON = "--prefix=/usr -Dpkg_config_path=${S}/pkgconfig -Ddl-plugins=disabled -Denable-tidl=disabled"

inherit meson pkgconfig

do_install:append() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    mkdir -p ${D}/opt/edgeai-gst-plugins
    cp ${CP_ARGS} ${S}/* ${D}/opt/edgeai-gst-plugins
}

INSANE_SKIP:${PN}-source += "dev-deps"
INSANE_SKIP:${PN} += "rpaths"

PR:append = "tisdk_0"