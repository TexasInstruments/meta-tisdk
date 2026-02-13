SUMMARY = "TI SDK filesystem image with display sharing for AM62P"

DESCRIPTION = "Complete Arago based TI SDK filesystem image containing comprehensive applications \
with display sharing capabilities and packages for AM62P. This image is derived from \
tisdk-default-image and includes additional features specific to AM62P display cluster use cases."

# Use tisdk-default-image as base
require recipes-core/images/tisdk-default-image.bb

# Ensure that we build this image only for AM62P
COMPATIBLE_MACHINE = "am62pxx"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

export IMAGE_BASENAME = "tisdk-display-cluster-image${ARAGO_IMAGE_SUFFIX}"
