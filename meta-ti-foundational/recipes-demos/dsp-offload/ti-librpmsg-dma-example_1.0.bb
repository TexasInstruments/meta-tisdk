SUMMARY = "Audio Offload Example using RPMsg DMA library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=744e63d2bb8c6151dcdd97f49aa02c53"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=scarthgap"
SRCREV = "26b8cf53257214860c78ad970643e4f90920b85d"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS = "ti-librpmsg-dma fftw libsndfile1 alsa-lib"

EXTRA_OECMAKE += "-DBUILD_LIB=OFF -DBUILD_EXAMPLE=ON"

PACKAGES += "${PN}-firmware"
RDEPENDS:${PN} += "${PN}-firmware"
FILES:${PN}-firmware = "${base_libdir}/firmware/*"
INSANE_SKIP:${PN}-firmware += "arch"

FILES:${PN} += " \
	${bindir}/rpmsg_audio_offload_example \
	${sysconfdir}/dsp_offload.cfg \
	${datadir}/sample_audio.wav \
"
