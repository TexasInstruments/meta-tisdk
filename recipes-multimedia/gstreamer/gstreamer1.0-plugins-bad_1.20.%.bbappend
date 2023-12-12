FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG:append = " faad kms"

GSTDRM_WAYLANDSINK_PATCHES = " \
        file://0001-gstdrmallocator-Add-DRM-allocator-support.patch \
        file://0002-kmssink-Add-omapdrm-and-tidss-in-the-list-of-drivers.patch \
"

SRC_URI:append:omap-a15 = " \
    ${GSTDRM_WAYLANDSINK_PATCHES} \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR:append = ".tisdk_0"

