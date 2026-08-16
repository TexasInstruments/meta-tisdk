SUMMARY = "DSP Offload Examples using RPMsg DMA library"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91dc4ee6d125d0aaba4e5bd2fcc50ed3"

SRC_URI = "git://github.com/TexasInstruments/rpmsg-dma.git;protocol=https;branch=main"
SRCREV = "d0e107211275c69eda653a93a2ab3cc66b16f608"

inherit cmake pkgconfig

DEPENDS = "ti-librpmsg-dma fftw libsndfile1 alsa-lib ti-tidl-osrt json-c readline pkgconfig-native"

EXTRA_OECMAKE += "-DBUILD_LIB=OFF -DBUILD_EXAMPLE=ON \
	-DTVM_ROOT=${RECIPE_SYSROOT}${includedir}/tvm/tvm \
	-DTVM_RUNTIME_LIB=${RECIPE_SYSROOT}${libdir}/libtvm_runtime.so"

PACKAGES += "${PN}-firmware"
RDEPENDS:${PN} += "${PN}-firmware"
FILES:${PN}-firmware = "${base_libdir}/firmware/*"
INSANE_SKIP:${PN}-firmware += "arch"

FILES:${PN} += " \
	${bindir}/rpmsg_audio_offload_example \
	${bindir}/rpmsg_2dfft_example \
	${bindir}/rpmsg_sigchain_biquad_example \
	${bindir}/rpmsg_inference_example \
	${sysconfdir}/dsp_offload.cfg \
	${datadir}/sample_audio.wav \
	${datadir}/2dfft_test_data \
	${datadir}/2dfft_test_data/2dfft_expected_output_data.bin \
	${datadir}/2dfft_test_data/2dfft_input_data.bin \
	${datadir}/tvm_inference/json/ \
	${datadir}/tvm_inference/input/ \
"
