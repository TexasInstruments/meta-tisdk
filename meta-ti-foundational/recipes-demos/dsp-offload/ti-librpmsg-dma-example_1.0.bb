SUMMARY = "Audio Offload Example using RPMsg DMA library"
SUMMARY = "RPMsg DMA shared library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=744e63d2bb8c6151dcdd97f49aa02c53"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=main"
SRCREV = "609fd72f458ba56f80d06810509ef88d010d2c03"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "ti-librpmsg-dma fftw libsndfile1 alsa-lib"

# Install to /lib and /bin instead of default /usr
EXTRA_OECMAKE += "-DBUILD_LIB=OFF -DBUILD_EXAMPLE=ON -DCMAKE_INSTALL_PREFIX=${prefix} -DCMAKE_INSTALL_BINDIR=bin"

# Avoid stripping the binary
INHIBIT_PACKAGE_STRIP = "1"

FILES:${PN} += "/bin/rpmsg_audio_offload_example"
FILES:${PN} += "/etc/dsp_offload.cfg"
FILES:${PN} += "/opt/sample_audio.wav"

