PR:append = "_tisdk_0"

IMAGE_INSTALL:append = " resize-rootfs"

IMAGE_INSTALL:append:am62xx = " seva-launcher"
IMAGE_INSTALL:append:am62pxx = " seva-launcher"
IMAGE_INSTALL:append:j721s2 = " seva-launcher"
IMAGE_INSTALL:append:j784s4 = " seva-launcher"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

