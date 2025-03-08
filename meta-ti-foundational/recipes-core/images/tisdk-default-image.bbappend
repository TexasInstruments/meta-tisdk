PR:append = "_tisdk_5"

IMAGE_INSTALL:append = " \
    packagegroup-arago-gst-sdk-target \
    resize-rootfs \
"

IMAGE_INSTALL:append:am62lxx = " mosquitto libmosquitto1 libmosquittopp1 mosquitto-clients mosquitto-dev"
