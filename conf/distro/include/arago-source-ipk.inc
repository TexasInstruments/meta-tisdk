#===============================================================================
# BOM Configuration file
#
# Syntax:
#   Select one of available providers (recipes) for a package.
#   Usually used for virtual/xyz style packages. i.e virtual/kernel
#       PREFERRED_PROVIDER_<pkg> = "recipe"
#       PREFERRED_PROVIDER_<pkg>-<mach> = "recipe"
#   i.e.
#       PREFERRED_PROVIDER_virtual/kernel = "linux-davinci"
#       PREFERRED_PROVIDER_virtual/kenrel_omap3evm = "linux-omap3"
#
#   Select one of the available versions for a package
#       PREFERRED_VERSION_<pkg> = "X.Y.Z"
#       PREFERRED_VERSION_<pkg>-<mach> = "X.Y.Z"
#   i.e.
#       PREFERRED_VERSION_udev = "141"
#       PREFERRED_VERSION_udev:omap3evm = "124"
#
#   Enable source ipk creation for a package
#       CREATE_SRCIPK:pn-<pkg> = "1"
#   i.e.
#       CREATE_SRCIPK:pn-u-boot = "1"
#
#   Change install diretory of source ipk for a package
#       SRCIPK_INSTALL_DIR:pn-<pkg> = "<install dir>"
#   i.e.
#       SRCIPK_INSTALL_DIR:pn-u-boot = "board-support/u-boot-source"
#
#   Change source ipk architecture for a package
#       SRCIPK_PACKAGE_ARCH:pn-<pkg> = "<arch>"
#   i.e.
#       SRCIPK_PACKAGE_ARCH:pn-u-boot = "${MACHINE_ARCH}"
#===============================================================================

# Enable the creation of source ipks
INHERIT += "sourceipk"

# Disable including README and recipe files in sourceipks for SDK builds.
SRCIPK_INCLUDE_EXTRAFILES = "0"

# Set source ipk packages and install directories
CREATE_SRCIPK:pn-arm-benchmarks ?= "1"
SRCIPK_INSTALL_DIR:pn-arm-benchmarks ?= "example-applications/${PN}-${PV}"

CREATE_SRCIPK:pn-u-boot ?= "1"
SRCIPK_INSTALL_DIR:pn-u-boot ?= "board-support/u-boot-${PV}"

CREATE_SRCIPK:pn-u-boot-ti-staging ?= "1"
SRCIPK_INSTALL_DIR:pn-u-boot-ti-staging ?= "board-support/u-boot-${PV}${UBOOT_LOCALVERSION}"
SRCIPK_PRESERVE_GIT:pn-u-boot-ti-staging ?= "true"

CREATE_SRCIPK:pn-linux-ti-staging ?= "1"
SRCIPK_INSTALL_DIR:pn-linux-ti-staging ?= "board-support/linux-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT:pn-linux-ti-staging ?= "true"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging ?= "true"

CREATE_SRCIPK:pn-linux-ti-staging-rt ?= "1"
SRCIPK_INSTALL_DIR:pn-linux-ti-staging-rt ?= "board-support/linux-rt-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT:pn-linux-ti-staging-rt ?= "true"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging-rt ?= "true"

CREATE_SRCIPK:pn-linux-ti-staging-systest ?= "1"
SRCIPK_INSTALL_DIR:pn-linux-ti-staging-systest ?= "board-support/linux-systest-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT:pn-linux-ti-staging-systest ?= "true"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging-systest ?= "true"

CREATE_SRCIPK:pn-linux ?= "1"
SRCIPK_INSTALL_DIR:pn-linux ?= "board-support/linux-${PV}"

CREATE_SRCIPK:pn-cryptodev-module ?= "1"
SRCIPK_INSTALL_DIR:pn-cryptodev-module ?= "board-support/extra-drivers/cryptodev-module-${PV}"
SRCIPK_PRESERVE_GIT:pn-cryptodev-module ?= "true"

CREATE_SRCIPK:pn-ti-img-rogue-driver ?= "1"
SRCIPK_INSTALL_DIR:pn-ti-img-rogue-driver ?= "board-support/extra-drivers/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-ti-img-rogue-driver ?= "true"


CREATE_SRCIPK:pn-optee-os ?= "1"
SRCIPK_INSTALL_DIR:pn-optee-os ?= "board-support/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-optee-os ?= "true"

CREATE_SRCIPK:pn-trusted-firmware-a ?= "1"
SRCIPK_INSTALL_DIR:pn-trusted-firmware-a ?= "board-support/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-trusted-firmware-a ?= "true"

