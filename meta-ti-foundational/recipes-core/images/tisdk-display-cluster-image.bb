SUMMARY = "Arago TI SDK full filesystem image showcasing display sharing in AM62P"

DESCRIPTION = "Complete Arago TI SDK filesystem image containing complete \
applications (along with display sharing) and packages intended to be used for AM62P."

# Use tisdk-default-image as base
require recipes-core/images/tisdk-default-image.bb

# Ensure that we build this image only for AM62P
COMPATIBLE_MACHINE = "am62pxx"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

export IMAGE_BASENAME = "tisdk-display-cluster-image${ARAGO_IMAGE_SUFFIX}"
