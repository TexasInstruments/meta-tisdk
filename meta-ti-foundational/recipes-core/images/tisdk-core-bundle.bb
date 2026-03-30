SUMMARY = "TI SDK installer package - NOT for direct use on target"

DESCRIPTION = "This creates an installer package including all the default images, \
source packages, binaries, and filesystems for TI SDK. This installer is meant to be \
used on the host system and is derived from arago-core-bundle.\
"

require recipes-core/images/arago-core-bundle.bb

# Replace arago images with tisdk-branded images
TARGET_IMAGES:remove = "arago-base-image arago-default-image arago-thinlinux-image arago-bootstrap-image"

# Add tisdk-branded images
TARGET_IMAGES:append = " \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "tisdk-jailhouse-image", "tisdk-base-image tisdk-default-image tisdk-thinlinux-image", d)} \
"

TARGET_IMAGE_TYPES = "tar.xz wic.xz wic.bmap"

# Add packagegroup to deploy sources in SDK installer
IMAGE_INSTALL:append = " \
    packagegroup-arago-tisdk-sourceipks-sdk-host \
"
