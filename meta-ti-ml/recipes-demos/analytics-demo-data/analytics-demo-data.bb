DESCRIPTION = "Model test data for ARM Analytics demos"
HOMEPAGE = "https://github.com/TexasInstruments/oob-demo-assets"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

SRC_URI = "git://github.com/TexasInstruments/oob-demo-assets.git;protocol=https;branch=master"
SRCREV = "3deadefecc49877c7f6c9d59854f801fa3fc43e1"

S = "${WORKDIR}/git"

do_install() {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

	install -d ${D}${datadir}/oob-demo-assets
	cp ${CP_ARGS} ${S}/* ${D}${datadir}/oob-demo-assets
}

# No configure nor build steps
do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES:${PN} = "${datadir}/oob-demo-assets"
