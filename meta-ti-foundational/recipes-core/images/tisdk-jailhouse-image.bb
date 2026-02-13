SUMMARY = "TI SDK image for Jailhouse Hypervisor"

DESCRIPTION = "Arago based TI SDK image for running Jailhouse, a partitioning Hypervisor based on Linux. \
This image is derived from tisdk-default-image and includes additional firmware and management \
tools for Jailhouse.\
"

require recipes-core/images/tisdk-default-image.bb

COMPATIBLE_MACHINE = "am62xx|am62pxx|am62lxx"

IMAGE_INSTALL += " jailhouse"
