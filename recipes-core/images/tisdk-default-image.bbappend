PR:append = "_tisdk_1"

IMAGE_INSTALL:remove:am62xx = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:am62pxx = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:am62axx = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:am64xx = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:j784s4 = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:j721s2 = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:j7200 = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"
IMAGE_INSTALL:remove:j721e = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:j721e = " \
    wifi-oob \
    pmic-fix \
"

IMAGE_INSTALL:append:j721s2 = " \
    wifi-oob \
"

IMAGE_INSTALL:append:j784s4 = " \
    wifi-oob \
"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

