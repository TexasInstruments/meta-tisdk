require conf/distro/include/branding-core.inc

# Foundational SDK supports SK device variants by default
SOC_FAMILY:append = ":sk"

# Remove run-time dependency for TI ETH firmware in the rootfs
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:remove:j721s2 = "ti-eth-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:remove:j784s4 = "ti-eth-fw"

# Add demos relevant to foundational SDK for am68-sk, am69-sk to tisdk-default-image
IMAGE_INSTALL:append:pn-tisdk-default-image:j721s2 = " ti-apps-launcher"
IMAGE_INSTALL:append:pn-tisdk-default-image:j784s4 = " ti-apps-launcher"

