DESCRIPTION = "Task to install sources for the BSP, out of tree drivers, and additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r7"

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

# Remove GPU driver sources for am62lxx
GRAPHICS_RDEPENDS:remove:am62lxx = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Remove gpudriver sources for ti33x, ti43x and am65xx family of devices until we have SGX driver working with kernel 6.6
GRAPHICS_RDEPENDS:remove:ti33x = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"
GRAPHICS_RDEPENDS:remove:ti43x = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"
GRAPHICS_RDEPENDS:remove:am65xx = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${PREFERRED_PROVIDER_virtual/gpudriver}-src','',d)}"

# Task to install crypto sources in SDK"
CRYPTO_RDEPENDS = "cryptodev-module-src"

# Task to install sources for additional utilities/demos for SDKs
UTILS = "arm-benchmarks-src"
UTILS:append:am62xx = " pru-icss-src"
UTILS:append:am64xx = " pru-icss-src"
UTILS:append:am335x-evm = " pru-adc-src"

UTILS:append:ti33x = " \
    omapconf-src \
    pru-icss-src \
    oprofile-example-src \
"

UTILS:append:ti43x = " \
    pru-icss-src \
    oprofile-example-src \
"

UTILS:append:am65xx = " \
    pru-icss-src \
    oprofile-example-src \
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

# Package ATF & OPTEE sources for k3 platforms
RDEPENDS:${PN}:append:k3 = "\
    trusted-firmware-a-src \
    optee-os-src \
"
