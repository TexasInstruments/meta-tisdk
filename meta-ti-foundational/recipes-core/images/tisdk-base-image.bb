SUMMARY = "TI SDK base image with test tools"

DESCRIPTION = "TI SDK base image suitable for initramfs containing comprehensive test tools. \
This image is derived from arago-base-image and includes additional packages for TI SDK."

require recipes-core/images/arago-base-image.bb

IMAGE_INSTALL:append = " resize-rootfs"
