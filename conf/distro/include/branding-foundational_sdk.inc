require conf/distro/include/branding-core.inc

# Foundational SDK supports SK device variants by default
SOC_FAMILY:append = ":sk"

# Remove run-time dependency for TI ETH firmware in the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:remove:j721s2 = "ti-eth-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:remove:j784s4 = "ti-eth-fw"

