PR:append = "_tisdk_4"

# Holds all packagegroups that includes Matrix v2
MATRIX_GUI_PACKAGEGROUPS = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"

IMAGE_INSTALL:remove:am62xx = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:am62pxx = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:am62axx = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:am64xx = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:am65xx = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:j784s4 = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:j721s2 = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:j7200 = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:j721e = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"
IMAGE_INSTALL:remove:j722s = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:j721e = " pmic-fix"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

