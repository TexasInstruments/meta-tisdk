SUMMARY = "U-Boot script to boot Xen"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/meta-ti-bsp/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

PR = "r0"

SRC_URI = " \
    file://boot.xen.source.in \
    file://boot.xen.its \
"

DEPENDS = "u-boot-mkimage-native dtc-native"

XEN_IMAGE_NAME ??= "xen"
XEN_KERNEL_IMAGE_NAME ??= "Image"
XEN_RAMDISK_NAME ??= "${XEN_RAMFS_IMAGE}-${MACHINE}.rootfs.cpio"
XEN_FDT_ADDRESS ??= "0x80400000"
XEN_ADDRESS ??= "0x81000000"
XEN_KERNEL_ADDRESS ??= "0x83000000"
XEN_RAMDISK_ADDRESS ??= "0x89000000"
XEN_BOOT_DOM0LESS ??= ""
DOM0_MEM ??= "1G"
DOM0_MAX_VCPUS ??= "1"
XEN_SHMEM_UIO_NAME ??= "xen-uio"
XEN_SHMEM_START ??= "0x80400000"
XEN_SHMEM_SIZE ??= "0x1000"

XEN_DTUART_SERIAL ??= ""
XEN_DTUART_SERIAL:am62xx ??= "serial2"
XEN_DTUART_SERIAL:am62pxx ??= "serial2"

XEN_IRQ_DEVICES ??= ""
XEN_IRQ_DEVICES:am62xx ??= "\/bus@f0000\/bus@48000000\/interrupt-controller@48000000"
XEN_IRQ_DEVICES:am62pxx ??= "\/bus@f0000\/bus@48000000\/interrupt-controller@48000000"

S = "${WORKDIR}"

do_compile () {
    sed -e 's/@@XEN_IMAGE_NAME@@/${XEN_IMAGE_NAME}/' \
        -e 's/@@XEN_KERNEL_IMAGE_NAME@@/${XEN_KERNEL_IMAGE_NAME}/' \
        -e 's/@@XEN_RAMDISK_NAME@@/${XEN_RAMDISK_NAME}/' \
        -e 's/@@XEN_FDT_ADDRESS@@/${XEN_FDT_ADDRESS}/' \
        -e 's/@@XEN_ADDRESS@@/${XEN_ADDRESS}/' \
        -e 's/@@XEN_KERNEL_ADDRESS@@/${XEN_KERNEL_ADDRESS}/' \
        -e 's/@@XEN_RAMDISK_ADDRESS@@/${XEN_RAMDISK_ADDRESS}/' \
        -e 's/@@XEN_BOOT_DOM0LESS@@/${XEN_BOOT_DOM0LESS}/' \
        -e 's/@@XEN_DTUART_SERIAL@@/${XEN_DTUART_SERIAL}/' \
        -e 's/@@DOM0_MEM@@/${DOM0_MEM}/' \
        -e 's/@@DOM0_MAX_VCPUS@@/${DOM0_MAX_VCPUS}/' \
        -e 's/@@XEN_IRQ_DEVICES@@/${XEN_IRQ_DEVICES}/' \
        -e 's/@@XEN_SHMEM_ID@@/${XEN_SHMEM_ID}/' \
        -e 's/@@XEN_SHMEM_UIO_NAME@@/${XEN_SHMEM_UIO_NAME}/' \
        -e 's/@@XEN_SHMEM_START@@/${XEN_SHMEM_START}/' \
        -e 's/@@XEN_SHMEM_SIZE@@/${XEN_SHMEM_SIZE}/' \
        "${S}/boot.xen.source.in" > "${S}/boot.xen.source"

    mkimage -f ${S}/boot.xen.its ${S}/boot.xen.scr
}

inherit deploy
do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/boot.xen.scr ${DEPLOYDIR}
}
addtask deploy before do_build after do_unpack
