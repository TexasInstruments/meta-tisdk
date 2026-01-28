FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://init_crypt_verity.sh;beginline=1;endline=62;md5=907b750a47c5cfa49f82cb712643f470"

# This script is used to decrypt and verify data during boot
SRC_URI = "file://init_crypt_verity.sh"

do_configure() {
    # This demo uses a random pass_key
    dd if=/dev/urandom of=${WORKDIR}/pass_key bs=64 count=1
}

do_install() {
    install -d ${D}${base_prefix}
    install -m 0755 ${WORKDIR}/init_crypt_verity.sh ${D}${base_prefix}/init
    install -m 0600 ${WORKDIR}/pass_key ${D}${base_prefix}/pass_key
}

pkg_postinst_ontarget:${PN}() {
    #!/bin/sh

    sleep 5 #For mmcblk1 to populate
    chown root:root /bin/mount.util-linux

    /bin/mount -t devtmpfs none /dev >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    mount /dev/mmcblk1p2 /mnt >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    umount /dev/mmcblk1p3 >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    umount /dev/mmcblk1p4 >/dev/ttyS2 2>/dev/ttyS2
    sleep 1

    # Set up encrypted root
    cryptsetup luksFormat /dev/mmcblk1p3 --key-file=/pass_key --batch-mode >/dev/ttyS2 2>/dev/ttyS2
    sleep 3
    cryptsetup luksOpen /dev/mmcblk1p3 crypt_root --key-file=/pass_key >/dev/ttyS2 2>/dev/ttyS2
    sleep 1

    # Format and copy rootfs inside encrypted partition
    mkfs.ext4 /dev/mapper/crypt_root >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    mkdir -p /new_mount >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    mount /dev/mapper/crypt_root /new_mount >/dev/ttyS2 2>/dev/ttyS2
    sleep 1
    cp -r /mnt /new_mount
    umount /new_mount
    umount /mnt

    # Generate verity hash
    veritysetup format /dev/mapper/crypt_root /dev/mmcblk1p4 > /pass_key.hash
}

FILES:${PN} += " \
    ${base_prefix}/init \
    ${base_prefix}/pass_key \
"
