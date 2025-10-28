PR:append = "_tisdk_8"

# Avoid building bootstrap-image while generating tisdk-core-bundle for PROC SDK
TARGET_IMAGES:remove = " \
	tisdk-bootstrap-image \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "tisdk-base-image", "", d)} \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "tisdk-thinlinux-image", "", d)} \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "tisdk-default-image", "", d)} \
"

TARGET_IMAGES:append = " ${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "tisdk-jailhouse-image", "", d)}"

TARGET_IMAGE_TYPES = "tar.xz wic.xz wic.bmap"

# Add packagegroup to deploy sources in SDK installer
IMAGE_INSTALL:append = " \
    packagegroup-arago-tisdk-sourceipks-sdk-host \
"

# Set DTB filters for each machine.  Use "unknown" by default to avoid
# picking up DTB files for devices with no DTB support.
DTB_FILTER:j721e = "j721e\|fpdlink"
DTB_FILTER:j721s2 = "j721s2\|am68\|fpdlink\|k3-v3link"
DTB_FILTER:j784s4 = "j784s4\|am69\|fpdlink\|k3-v3link"
DTB_FILTER:j722s = "j722s\|fpdlink\|k3-v3link"
DTB_FILTER:am65xx = "k3-am654"
DTB_FILTER:am62xx-evm = "k3-am625"
DTB_FILTER:am62lxx = "k3-am62l"
DTB_FILTER:am62xx-lp-evm = "k3-am62-lp\|k3-am625-sk"
DTB_FILTER:am62xxsip-evm = "k3-am625"
DTB_FILTER:am64xx = "k3-am642"
DTB_FILTER:am62pxx-evm = "k3-am62p5"
DTB_FILTER:am62axx = "k3-am62a7\|k3-am62x-sk\|k3-fpdlink\|k3-v3link"
DTB_FILTER:am62dxx = "k3-am62d2\|k3-am62a7-sk-edgeai\|k3-am62a7-sk-rpi-hdr-ehrpwm\|k3-am62x-sk"
DTB_FILTER:ti33x = "am335x"
DTB_FILTER:ti43x = "am437x\|am43x"

# All the boot partition files, including all the device variants that were built
BOOT_PART:k3 = "uEnv.txt u-boot.img tispl.bin tiboot3*.bin"
BOOT_PART:append:j721e = " sysfw.itb sysfw-*evm.itb"
BOOT_PART:append:am65xx = " sysfw.itb sysfw-*evm.itb"

# Update the default boot binaries in prebuilt-images
SYSFW_SOC ?= "*"
SYSFW_SOC:j721e = "j721e"
SYSFW_SOC:j7200 = "j7200"
SYSFW_SOC:j721s2 = "j721s2"
SYSFW_SOC:j784s4 = "j784s4"
SYSFW_SOC:j722s = "j722s"
SYSFW_SOC:am62axx = "am62ax"
SYSFW_SOC:am62xx = "am62x"
SYSFW_SOC:am62lxx = "am62lx"
SYSFW_SOC:am64xx = "am64x_sr2"
SYSFW_SOC:am62pxx = "am62px"
SYSFW_SOC:am65xx = "am65x_sr2"
SYSFW_SOC:am62dxx = "am62ax"

SYSFW_PREFIX ?= "fs"
SYSFW_PREFIX:am64xx = "sci"
SYSFW_PREFIX:am65xx = "sci"
SYSFW_PREFIX:am62xx = "fs*"
SYSFW_PREFIX:am62pxx = "fs*"
SYSFW_PREFIX:am62axx = "fs*"
SYSFW_PREFIX:am62dxx = "fs*"

SYSFW_BINARY = "ti-${SYSFW_PREFIX}-firmware-${SYSFW_SOC}*.bin"

PREBUILT_DIR = "${IMAGE_ROOTFS}/board-support/prebuilt-images"

tisdk_image_build:append() {
    # Add ti-sysfw needed by binman builds for u-boot
    if [ -d "${DEPLOY_DIR_IMAGE}/ti-sysfw" ]
    then
        mkdir -p ${PREBUILT_DIR}/ti-sysfw/
        cp ${DEPLOY_DIR_IMAGE}/ti-sysfw/${SYSFW_BINARY} ${PREBUILT_DIR}/ti-sysfw/
    fi

    # Add ti-dm needed by binman builds for u-boot
    if [ -d "${DEPLOY_DIR_IMAGE}/ti-dm" ]
    then
        cp -r ${DEPLOY_DIR_IMAGE}/ti-dm ${PREBUILT_DIR}/
    fi

    # Add ti-hsm needed by binman builds for u-boot
    if [ -d "${DEPLOY_DIR_IMAGE}/ti-hsm" ]
    then
        mkdir -p ${PREBUILT_DIR}/ti-hsm/
        cp ${DEPLOY_DIR_IMAGE}/ti-hsm/* ${PREBUILT_DIR}/ti-hsm/
    fi

    # Copy all the boot partition files (for all soc types: gp/hs/hs-fs)
    for f in ${BOOT_PART}
    do
        cp ${DEPLOY_DIR_IMAGE}/$f ${PREBUILT_DIR}
    done
}


