SUMMARY = "PRU Multi-channel ADC Interface Reference Design"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PRUMulti-channelADCInterfaceReferen_manifest.html;md5=1302c86d3951a0d1254e1805158ccf2e"

require recipes-ti/includes/ti-paths.inc

inherit update-alternatives

COMPATIBLE_MACHINE = "am335x-evm"

PV = "1.0"
PR = "r3"

BRANCH = "master"
SRC_URI = "git://git.ti.com/apps/tida01555.git;protocol=git;branch=${BRANCH}"

SRCREV = "0a0f7700d11ec8a6b7ed02e4c70d4bf196ca35e5"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "ti-cgt-pru-native pru-icss ncurses"
RDEPENDS:${PN} = "bash"

EXTRA_OEMAKE += "PRU_CGT="${TI_CGT_PRU_INSTALL_DIR}" PRU_SSP="${STAGING_DIR_TARGET}/usr""

do_install() {
# install firmware
    install -d ${D}${base_libdir}/firmware/pru
    install -m 0644 PRU_ADS8688_Controller/gen/PRU_ADS8688_Controller.out ${D}${base_libdir}/firmware/pru
    install -m 0644 PRU_ADS8688_Interface/gen/PRU_ADS8688_Interface.out ${D}${base_libdir}/firmware/pru
# install binary and scripts
    install -d ${D}${bindir}
    install -m 755 ARM_User_Space_App/gen/ARM_User_Space_App.out ${D}${bindir}/pru-adc-arm-app.out
    install -m 755 run.sh ${D}${bindir}/run-pru-adc.sh
    sed -i 's|./ARM_User_Space_App.out|/usr/bin/pru-adc-arm-app.out|g' ${D}${bindir}/run-pru-adc.sh
}

FILES:${PN} = " \
    ${base_libdir}/firmware \
    ${bindir}/run-pru-adc.sh \
    ${bindir}/pru-adc-arm-app.out \
"

ALTERNATIVE_TARGET:pru-adc[am335x-pru0-fw] = "/lib/firmware/pru/PRU_ADS8688_Controller.out"
ALTERNATIVE_TARGET:pru-adc[am335x-pru1-fw] = "/lib/firmware/pru/PRU_ADS8688_Interface.out"

ALTERNATIVE_PRIORITY:pru-adc = "20"

CREATE_SRCIPK = "1"
SRCIPK_INSTALL_DIR = "example-applications/${PN}-${PV}"

INSANE_SKIP:${PN} = "arch ldflags"
