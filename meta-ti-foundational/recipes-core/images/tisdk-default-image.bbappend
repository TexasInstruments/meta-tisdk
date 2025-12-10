PR:append = "_tisdk_5"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:am62lxx = " mosquitto libmosquitto1 libmosquittopp1 mosquitto-clients mosquitto-dev"
IMAGE_INSTALL:append:am62xx = " ti-gst-plugins-source ti-gst-plugins-dev ti-gst-utils "
IMAGE_INSTALL:append:am62pxx = " ti-gst-plugins-source ti-gst-plugins-dev ti-gst-utils "
IMAGE_INSTALL:remove:am62dxx = " packagegroup-arago-tisdk-graphics"
IMAGE_INSTALL:append:am62dxx = " cpld-ctl tad5212-reconfig"
