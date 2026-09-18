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

# Essential packages for a functional initramfs
TISDK_INITRAMFS_ESSENTIALS = " \
    base-files \
    base-passwd \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${VIRTUAL-RUNTIME_login_manager} \
"

# Additional utilities (can be overridden or emptied for minimal image size)
TISDK_INITRAMFS_UTILITIES ?= " \
    netbase \
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

PACKAGE_INSTALL = "${TISDK_INITRAMFS_ESSENTIALS} ${TISDK_INITRAMFS_UTILITIES}"

# Conditionally add sysvinit and initscripts when distro uses sysvinit, else systemd
PACKAGE_INSTALL:append = "${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', ' ${VIRTUAL-RUNTIME_init_manager} ${VIRTUAL-RUNTIME_initscripts}', 'systemd', d)}"

# Set INITRAMFS_MAXSIZE as 64MB for sysvinit, 256MB for systemd
INITRAMFS_MAXSIZE = "${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '65536', '262144', d)}"
