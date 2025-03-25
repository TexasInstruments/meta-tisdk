DESCRIPTION = "Model test data for ARM Analytics demos"
HOMEPAGE = "https://github.com/TexasInstruments/oob-demo-assets"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9581b427bf58e35c66e06c572d472e88"

SRC_URI = "git://github.com/TexasInstruments/oob-demo-assets.git;protocol=https;branch=master"
SRCREV = "6b64cbdfd16648f1dd75dfc1ec8711c2b6559826"

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
