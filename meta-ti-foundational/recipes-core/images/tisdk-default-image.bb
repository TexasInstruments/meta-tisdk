SUMMARY = "TI SDK full filesystem image"

DESCRIPTION = "Complete TI SDK filesystem image containing comprehensive \
applications and packages to enable full SoC functionality. This image is derived \
from arago-default-image and includes additional packages for TI SDK."

require recipes-core/images/arago-default-image.bb

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:am62lxx-evm = " mosquitto libmosquitto1 libmosquittopp1 mosquitto-clients mosquitto-dev"
IMAGE_INSTALL:append:am62xx = " ti-gst-plugins-source ti-gst-plugins-dev ti-gst-utils "
IMAGE_INSTALL:append:am62pxx = " ti-gst-plugins-source ti-gst-plugins-dev ti-gst-utils "
IMAGE_INSTALL:remove:am62dxx = " packagegroup-arago-graphics"
IMAGE_INSTALL:append:am62dxx = " cpld-ctl"
IMAGE_INSTALL:remove:beaglebadge-ti = " packagegroup-arago-graphics packagegroup-arago-gst-sdk-target packagegroup-arago-addons packagegroup-arago-addons-extra"

IMAGE_INSTALL:append = "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' packagegroup-core-selinux', '', d)}"
IMAGE_INSTALL:append = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'chromium-browser-layer', ' chromium-ozone-wayland', '', d)}"
