FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:am57xx = " \
	file://0001-FROMLIST-arm-dts-am57xx-idk-common-u-boot-Include-IP.patch \
	file://0002-FROMLIST-configs-am57xx_evm_defconfig-Enable-configs.patch \
	file://0003-FROMLIST-configs-am57xx_hs_evm_defconfig-Enable-conf.patch \
	"

SRCREV:tie-jailhouse = "252b02299cf637f96be9c735a86ed5aad328e00c"

PR:append = "_tisdk_6"

