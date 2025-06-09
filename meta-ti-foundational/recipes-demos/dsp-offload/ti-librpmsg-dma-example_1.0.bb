SUMMARY = "Audio Offload Example using RPMsg DMA library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=744e63d2bb8c6151dcdd97f49aa02c53"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=main"
SRCREV = "5dfd8c38de78352d4606d174e12113ec3209466f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS = "ti-librpmsg-dma fftw libsndfile1 alsa-lib"

EXTRA_OECMAKE += "-DBUILD_LIB=OFF -DBUILD_EXAMPLE=ON"

FILES:${PN} += " \
	${bindir}/rpmsg_audio_offload_example \
	${sysconfdir}/dsp_offload.cfg \
	${datadir}/sample_audio.wav \
"
