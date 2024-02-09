PR:append = "_tisdk_5"

# Holds all packagegroups that includes Matrix v2
MATRIX_GUI_PACKAGEGROUPS = " \
    packagegroup-arago-tisdk-matrix \
    packagegroup-arago-tisdk-matrix-extra \
"

# Avoid building Matrix V2 for all k3 platforms
IMAGE_INSTALL:remove:k3 = " \
    ${MATRIX_GUI_PACKAGEGROUPS} \
"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:j721e = " pmic-fix"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

