SUMMARY = "TI SDK minimal image for initramfs"

DESCRIPTION = "Minimal TI SDK initramfs image for basic Linux kernel boot with interactive shell support. \
This image extends arago-tiny-initramfs by adding busybox, user authentication (shadow-base), and \
basic networking (netbase) while maintaining a small footprint. Features passwordless root login \
for easy development access. The kernel is deployed separately via TFTP, boot partition, or JTAG, \
keeping the initramfs lightweight and suitable for embedded system boot. \
"

require recipes-core/images/arago-tiny-initramfs.bb

# Enable passwordless root login (user types "root", no password asked)
IMAGE_FEATURES += "empty-root-password"

PACKAGE_INSTALL = " \
    base-files \
    base-passwd \
    busybox \
    netbase \
    shadow-base \
    update-alternatives-opkg \
    parted \
    util-linux \
    e2fsprogs \
    dosfstools \
    mmc-utils \
    tar \
    gzip \
    xz \
    wget \
    dropbear \
"
