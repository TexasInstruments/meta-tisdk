PR:append = "_tisdk_0"

IMAGE_INSTALL:append = " resize-rootfs"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

