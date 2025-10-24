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

# Disable including README and recipe files in sourceipks for SDK builds.
SRCIPK_INCLUDE_EXTRAFILES = "0"

# Set source ipk packages and install directories
INHERIT:pn-arm-benchmarks += "sourceipk"
CREATE_SRCIPK:pn-arm-benchmarks ?= "1"
SRCIPK_INSTALL_DIR:pn-arm-benchmarks ?= "example-applications/${PN}-${PV}"

INHERIT:pn-oprofile-example += "sourceipk"
CREATE_SRCIPK:pn-oprofile-example ?= "1"
SRCIPK_INSTALL_DIR:pn-oprofile-example ?= "example-applications/${PN}"

INHERIT:pn-u-boot-ti-staging += "sourceipk"
CREATE_SRCIPK:pn-u-boot-ti-staging ?= "1"
SRCIPK_INSTALL_DIR:pn-u-boot-ti-staging ?= "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "board-support/u-boot-extras-jailhouse-${PV}${UBOOT_LOCALVERSION}", "board-support/ti-u-boot-${PV}${UBOOT_LOCALVERSION}", d)}"
SRCIPK_PRESERVE_GIT:pn-u-boot-ti-staging ?= "true"
SRCIPK_SHALLOW_CLONE:pn-u-boot-ti-staging ?= "true"

INHERIT:pn-linux-ti-staging += "sourceipk"
CREATE_SRCIPK:pn-linux-ti-staging ?= "1"
SRCIPK_INSTALL_DIR:pn-linux-ti-staging ?= "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "board-support/linux-extras-${PV}${KERNEL_LOCALVERSION}", "board-support/ti-linux-kernel-${PV}${KERNEL_LOCALVERSION}", d)}"
SRCIPK_PRESERVE_GIT:pn-linux-ti-staging ?= "true"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging ?= "true"

INHERIT:pn-linux-ti-staging-rt += "sourceipk"
CREATE_SRCIPK:pn-linux-ti-staging-rt ?= "1"
SRCIPK_INSTALL_DIR:pn-linux-ti-staging-rt ?= "${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "board-support/linux-extras-${PV}${KERNEL_LOCALVERSION}", "board-support/ti-linux-kernel-${PV}${KERNEL_LOCALVERSION}", d)}"
SRCIPK_PRESERVE_GIT:pn-linux-ti-staging-rt ?= "true"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging-rt ?= "true"

INHERIT:pn-cryptodev-module += "sourceipk"
CREATE_SRCIPK:pn-cryptodev-module ?= "1"
SRCIPK_INSTALL_DIR:pn-cryptodev-module ?= "board-support/extra-drivers/cryptodev-module-${PV}"
SRCIPK_PRESERVE_GIT:pn-cryptodev-module ?= "true"

INHERIT:pn-ti-img-rogue-driver += "sourceipk"
CREATE_SRCIPK:pn-ti-img-rogue-driver ?= "1"
SRCIPK_INSTALL_DIR:pn-ti-img-rogue-driver ?= "board-support/extra-drivers/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-ti-img-rogue-driver ?= "true"

INHERIT:pn-ti-sgx-ddk-km += "sourceipk"
CREATE_SRCIPK:pn-ti-sgx-ddk-km ?= "1"
SRCIPK_INSTALL_DIR:pn-ti-sgx-ddk-km ?= "board-support/extra-drivers/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-ti-sgx-ddk-km ?= "true"

INHERIT:pn-jailhouse += "sourceipk"
CREATE_SRCIPK:pn-jailhouse ?= "1"
SRCIPK_INSTALL_DIR:pn-jailhouse ?= "board-support/extra-drivers/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-jailhouse ?= "true"

INHERIT:pn-optee-os += "sourceipk"
CREATE_SRCIPK:pn-optee-os ?= "1"
SRCIPK_INSTALL_DIR:pn-optee-os ?= "board-support/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-optee-os ?= "true"

INHERIT:pn-trusted-firmware-a += "sourceipk"
CREATE_SRCIPK:pn-trusted-firmware-a ?= "1"
SRCIPK_INSTALL_DIR:pn-trusted-firmware-a ?= "board-support/${PN}-${PV}"
SRCIPK_PRESERVE_GIT:pn-trusted-firmware-a ?= "true"

INHERIT:pn-pru-icss += "sourceipk"
CREATE_SRCIPK:pn-pru-icss ?= "1"
SRCIPK_INSTALL_DIR:pn-pru-icss ?= "example-applications/${PN}-${PV}"

INHERIT:pn-omapconf += "sourceipk"
CREATE_SRCIPK:pn-omapconf ?= "1"
SRCIPK_INSTALL_DIR:pn-omapconf ?= "example-applications/${PN}-${PV}"
