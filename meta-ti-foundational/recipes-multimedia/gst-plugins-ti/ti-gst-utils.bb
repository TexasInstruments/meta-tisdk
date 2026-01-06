SUMMARY = "TI GST Utils"
DESCRIPTION = "TI GST Utils implements ARM neon optimized utility functions and also NV12 post process utility functions required for implementing TI GST arm-only plugins "
HOMEPAGE = "https://git.ti.com/cgit/edgeai/edgeai-apps-utils"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1f7721ee7d288457c5a70d0c8ff44b87"

PV = "1.0.0"
BRANCH = "main"
SRC_URI = "git://git.ti.com/git/edgeai/edgeai-apps-utils.git;protocol=https;branch=${BRANCH}"
SRCREV = "5a5a694ae02f0d2e4e39028b847e1c777c465cbf"

PLAT_SOC = ""
PLAT_SOC:am62xx = "am62x"
PLAT_SOC:am62pxx = "am62p"

RDEPENDS:${PN}-source += "python3-core cmake"

COMPATIBLE_MACHINE = "am62xx|am62pxx"

export SOC = "${PLAT_SOC}"

EXTRA_OECMAKE = "-DTARGET_FS=${WORKDIR}/recipe-sysroot -DCMAKE_SKIP_RPATH=TRUE -DCMAKE_OUTPUT_DIR=${WORKDIR}/out"

PACKAGES += "${PN}-source"
FILES:${PN}-source += "/opt/"

inherit cmake

do_install:append() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    mkdir -p ${D}/opt/edgeai-apps-utils
    cp ${CP_ARGS} ${S}/* ${D}/opt/edgeai-apps-utils
    cd ${D}/opt/edgeai-apps-utils
}

PR:append = "tisdk_0"