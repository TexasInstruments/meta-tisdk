DESCRIPTION = "Task to install sources for the BSP, out of tree drivers, and additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r5"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Choose the kernel and u-boot recipe sources to use
UBOOT_SRC = "${PREFERRED_PROVIDER_virtual/bootloader}-src"
KERNEL_SRC = "${PREFERRED_PROVIDER_virtual/kernel}-src"

# Task to install graphics sources in SDK
GRAPHICS_RDEPENDS = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Remove GPU driver sources for j7200
GRAPHICS_RDEPENDS:remove:j7200 = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Remove GPU driver sources for am62axx
GRAPHICS_RDEPENDS:remove:am62axx = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Remove GPU driver sources for am64xx
GRAPHICS_RDEPENDS:remove:am64xx = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Remove gpudriver sources for ti33x and ti43x family of devices until SGX driver is fixed
GRAPHICS_RDEPENDS:remove:ti33x = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"
GRAPHICS_RDEPENDS:remove:ti43x = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"
GRAPHICS_RDEPENDS:remove:omap-a15 = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Task to install crypto sources in SDK"
CRYPTO_RDEPENDS = "cryptodev-module-src"

# Task to install sources for additional utilities/demos for SDKs
UTILS = "arm-benchmarks-src"
UTILS:append:am64xx = " pru-icss-src"
UTILS:append:am335x-evm = " pru-adc-src"

UTILS:append:ti33x = " \
    omapconf-src \
    pru-icss-src \
    mmwavegesture-hmi-src \
    evse-hmi-src \
    protection-relays-hmi-src \
    matrix-gui-browser-src \
    refresh-screen-src \
    qt-tstat-src \
    oprofile-example-src \
    matrix-gui-src \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gpu', 'pdm-anomaly-detection-src', '', d)} \
"

UTILS:append:ti43x = " \
    pru-icss-src \
    mmwavegesture-hmi-src \
    evse-hmi-src \
    matrix-gui-browser-src \
    refresh-screen-src \
    qt-tstat-src \
    dual-camera-demo-src \
    image-gallery-src \
    oprofile-example-src \
    matrix-gui-src \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gpu', 'pdm-anomaly-detection-src', '', d)} \
"

UTILS:append:am65xx = " \
    pru-icss-src \
    matrix-gui-browser-src \
    oprofile-example-src \
    matrix-gui-src \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gpu', 'pdm-anomaly-detection-src', '', d)} \
"

UTILS:append = " \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "jailhouse-src", "", d)} \
"

# Collect all of the source packages into a packagegroup
RDEPENDS:${PN} = "\
    ${UBOOT_SRC} \
    ${KERNEL_SRC} \
    ${GRAPHICS_RDEPENDS} \
    ${CRYPTO_RDEPENDS} \
    ${UTILS} \
"
RDEPENDS:${PN}:append:am57xx-hs-evm = "optee-os-src"
