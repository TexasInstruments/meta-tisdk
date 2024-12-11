# Add IPU firmware to boot partition of wic images
IMAGE_BOOT_FILES = "${SPL_BINARY} u-boot.${UBOOT_SUFFIX} ${UBOOT_EXTLINUX_BOOT_FILES} ipc/dra7-ipu1-fw.xem4;dra7-ipu1-fw.xem4"
do_image_wic[depends] += "ti-ipc-rtos:do_deploy"

