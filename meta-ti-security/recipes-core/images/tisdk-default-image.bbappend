IMAGE_FSTYPES += "wic"
WKS_FILE = "tisdk-secure-image.wks"
WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

IMAGE_INSTALL:append = " cryptsetup lvm2"

DEPENDS += "tisdk-tiny-initramfs"
