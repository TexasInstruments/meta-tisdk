PR:append = "_tisdk_0"

# Avoid building bootstrap-image while generating tisdk-core-bundle for PROC SDK
TARGET_IMAGES:remove = "tisdk-bootstrap-image"
TARGET_IMAGE_TYPES = "tar.xz wic.xz wic.bmap"

DEPLOY_IMAGES_NAME:append = " Image fitImage fitImage-its-${MACHINE}"

# Add packagegroup to deploy sources in SDK installer
IMAGE_INSTALL:append = " \
    packagegroup-arago-tisdk-sourceipks-sdk-host \
"

# Set DTB filters for each machine.  Use "unknown" by default to avoid
# picking up DTB files for devices with no DTB support.
DTB_FILTER:j721e = "j721e\|fpdlink"
DTB_FILTER:j721s2 = "j721s2\|am68\|fpdlink"
DTB_FILTER:j784s4 = "j784s4\|am69\|fpdlink"

# All the boot partition files, including all the device variants that were built
BOOT_PART:k3 = "uEnv.txt u-boot.img tispl.bin tiboot3.bin tiboot3-*.bin"
BOOT_PART:append:j721e = " sysfw.itb sysfw-*evm.itb"

# Update the default boot binaries in prebuilt-images
SYSFW_SOC ?= "*"
SYSFW_SOC:j721e = "j721e"
SYSFW_SOC:j7200 = "j7200"
SYSFW_SOC:j721s2 = "j721s2"
SYSFW_SOC:j784s4 = "j784s4"
SYSFW_SOC:am62axx = "am62ax"

SYSFW_SUFFIX ?= "gp"
SYSFW_SUFFIX:am62axx = "hs-fs"
SYSFW_SUFFIX:j721s2:sk = "hs-fs"
SYSFW_SUFFIX:j784s4:sk = "hs-fs"

PREBUILT_DIR = "${IMAGE_ROOTFS}/board-support/prebuilt-images"


tisdk_image_build:append() {
    # Add ti-sysfw, ti-dm needed by binman builds for u-boot
    if [ -d "${DEPLOY_DIR_IMAGE}/ti-sysfw" ]
    then
        cp -r ${DEPLOY_DIR_IMAGE}/ti-sysfw ${PREBUILT_DIR}/
    fi
    if [ -d "${DEPLOY_DIR_IMAGE}/ti-dm" ]
    then
        cp -r ${DEPLOY_DIR_IMAGE}/ti-dm ${PREBUILT_DIR}/
    fi

    # Copy all the boot partition files (for all soc types: gp/hs/hs-fs)
    for f in ${BOOT_PART}
    do
        cp ${DEPLOY_DIR_IMAGE}/$f ${PREBUILT_DIR}
    done

    # Update the tiboot3.bin/sysfw.itb for the platform
    if [ -f "${PREBUILT_DIR}/tiboot3.bin" ]
    then
        cp ${DEPLOY_DIR_IMAGE}/tiboot3-${SYSFW_SOC}-${SYSFW_SUFFIX}-evm.bin ${PREBUILT_DIR}/tiboot3.bin
    fi
    if [ -f "${PREBUILT_DIR}/sysfw.itb" ]
    then
        cp ${DEPLOY_DIR_IMAGE}/sysfw-${SYSFW_SOC}-${SYSFW_SUFFIX}-evm.itb ${PREBUILT_DIR}/sysfw.itb
    fi
}


