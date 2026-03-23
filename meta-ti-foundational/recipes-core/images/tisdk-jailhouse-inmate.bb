SUMMARY = "TI SDK minimal image for Jailhouse Linux inmate demo"

DESCRIPTION = "Minimal TI SDK image for basic boot of Linux inmate for Jailhouse. \
This image is derived from tisdk-tiny-initramfs and includes additional packages \
for out-of-box demo.\
"

require recipes-core/images/tisdk-tiny-initramfs.bb

# Increase size limit for jailhouse inmate demo packages
INITRAMFS_MAXSIZE = "262144"

# Add jailhouse-specific packages and additional tools needed for the demo
PACKAGE_INSTALL += " \
    jailhouse-inmate \
    jailhouse-oob \
    systemd \
    perf \
"
