DESCRIPTION = "Task to install sources for the BSP, out of tree drivers, and additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r7"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Choose the kernel and u-boot recipe sources to use
UBOOT_SRC = "${PREFERRED_PROVIDER_virtual/bootloader}-src"
KERNEL_SRC = "${PREFERRED_PROVIDER_virtual/kernel}-src"

# Task to install graphics sources in SDK
GRAPHICS_RDEPENDS = "${@d.getVar('PREFERRED_PROVIDER_virtual/gpudriver', True) and d.getVar('PREFERRED_PROVIDER_virtual/gpudriver', True) + '-src' or ''}"

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
