SUMMARY = "Arago TI SDK full image for EV Charging Supply Equipment (EVSE) on AM62L"

DESCRIPTION = "Complete Arago TI SDK filesystem image containing complete \
               applications (along with EVSE utilities) and packages intended to be used for AM62L."

# Use tisdk-default-image as base
require recipes-core/images/tisdk-default-image.bb

# Ensure that we build this image only for AM62L
COMPATIBLE_MACHINE = "am62lxx"

IMAGE_INSTALL:append = " \
    cg5317-utils \
    kernel-module-lms-eth2spi \
    lms-eth2spi \
    open-plc-utils \
    tzdata \
"

export IMAGE_BASENAME = "tisdk-evse-image${ARAGO_IMAGE_SUFFIX}"
