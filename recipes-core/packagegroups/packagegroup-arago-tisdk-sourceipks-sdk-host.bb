DESCRIPTION = "Task to install sources for the BSP, out of tree drivers, and additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Choose the kernel and u-boot recipe sources to use
UBOOT_SRC = "${PREFERRED_PROVIDER_virtual/bootloader}-src"
KERNEL_SRC = "${PREFERRED_PROVIDER_virtual/kernel}-src"

# Task to install graphics sources in SDK
GRAPHICS_SRC = ""
GRAPHICS_SRC:j721e = "ti-img-rogue-driver-src"
GRAPHICS_SRC:j721s2 = "ti-img-rogue-driver-src"
GRAPHICS_SRC:j784s4 = "ti-img-rogue-driver-src"
GRAPHICS_RDEPENDS = "${@bb.utils.contains('MACHINE_FEATURES','gpu','${GRAPHICS_SRC}','',d)}"

# Task to install crypto sources in SDK"
CRYPTO_RDEPENDS = "cryptodev-module-src"

# Task to install sources for additional utilities/demos for SDKs
UTILS = " \
    arm-benchmarks-src \
"

# Collect all of the source packages into a packagegroup
RDEPENDS:${PN} = "\
     ${UBOOT_SRC} \
     ${KERNEL_SRC} \
     ${GRAPHICS_RDEPENDS} \
     ${CRYPTO_RDEPENDS} \
     ${UTILS} \
     optee-os-src \
     trusted-firmware-a-src \
"
