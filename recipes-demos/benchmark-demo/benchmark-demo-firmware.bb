SUMMARY = "Benchmark demo firmware binaries"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "am64xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PV = "08_01_00_25"

SRC_URI = "https://software-dl.ti.com/mcu-plus-sdk/esd/AM64X/${PV}/exports/mcu_plus_sdk_am64x_${PV}_firmware_demos.tar.xz"
SRC_URI[sha256sum] = "9edbe6a9c8b967be9f4111be597cf8426b57179e77268255a2bb8fbb08c1f813"

S = "${WORKDIR}/lib"

do_install() {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
	install -d ${D}${base_libdir}
	cp ${CP_ARGS} ${S} ${D}
}

inherit deploy
inherit update-alternatives

INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} += "arch"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

ALTERNATIVE:${PN}:am64xx = " \
    am64-main-r5f0_0-fw \
    am64-main-r5f0_1-fw \
    am64-main-r5f1_0-fw \
    am64-main-r5f1_1-fw \
"

ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/am64-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/am64-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/am64-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/am64-main-r5f1_1-fw"

ALTERNATIVE_TARGET[am64-main-r5f0_0-fw] = "${base_libdir}/firmware/mcusdk-benchmark_demo/am64-main-r5f0_0-fw"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw] = "${base_libdir}/firmware/mcusdk-benchmark_demo/am64-main-r5f0_1-fw"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw] = "${base_libdir}/firmware/mcusdk-benchmark_demo/am64-main-r5f1_0-fw"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw] = "${base_libdir}/firmware/mcusdk-benchmark_demo/am64-main-r5f1_1-fw"

ALTERNATIVE_PRIORITY = "20"

FILES:${PN} += "${base_libdir}"

PR:append = "r1"
