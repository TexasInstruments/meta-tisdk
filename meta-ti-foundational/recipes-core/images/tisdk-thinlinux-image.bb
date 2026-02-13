SUMMARY = "TI SDK Thin Linux image"

DESCRIPTION = "Minimal bootable TI SDK image with container support to start complex systems. \
This image is derived from arago-thinlinux-image and includes additional packages for TI SDK."

require recipes-core/images/arago-thinlinux-image.bb

IMAGE_INSTALL:append = " resize-rootfs"
