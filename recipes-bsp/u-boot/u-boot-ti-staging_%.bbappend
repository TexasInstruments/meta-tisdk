FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:sk = " \
		    file://0001-configs-j721e-Sign-the-j721e-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j721s2-Sign-the-am68-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j784s4-Sign-the-am69-sk-dtb-by-default-for-h.patch \
		    "
SRCREV:tie-jailhouse = "4acc8decd872406df399072217eb073e02dd4dcd"

PR:append = "_tisdk_1"

