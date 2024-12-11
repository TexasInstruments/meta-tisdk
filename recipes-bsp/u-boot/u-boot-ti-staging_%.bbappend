FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:sk = " \
		    file://0001-configs-j721e-Sign-the-j721e-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j721s2-Sign-the-am68-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j784s4-Sign-the-am69-sk-dtb-by-default-for-h.patch \
		    "

SRC_URI:append:am57xx = " \
            file://0001-arm-dts-am57xx-idk-common-u-boot-Include-IPU-Early-B.patch \
            file://0002-configs-am57xx_evm_defconfig-Enable-configs-needed-f.patch \
            file://0003-configs-am57xx_hs_evm_defconfig-Enable-configs-neede.patch \
		    "

SRCREV:tie-jailhouse = "4acc8decd872406df399072217eb073e02dd4dcd"

PR:append = "_tisdk_2"

