
IMAGE_INSTALL:remove = "\
	packagegroup-arago-tisdk-matrix \
	packagegroup-arago-tisdk-matrix-extra \
"


IMAGE_INSTALL:append = " \
	packagegroup-arago-gst-sdk-target \
	resize-rootfs \
"

IMAGE_INSTALL:append:j721e = " \
	wifi-oob \
	pru-icss \
	pmic-fix \
"

IMAGE_INSTALL:append:j721s2 = " \
	wifi-oob \
"

IMAGE_INSTALL:append:j784s4 = " \
	wifi-oob \
"

WIC_CREATE_EXTRA_ARGS += " --no-fstab-update"

PR:append = "_tisdk_0"
