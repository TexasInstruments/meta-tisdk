FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_configure[network] = "1"

SRC_URI += "file://0001-Switch-submodule-common-to-github.patch"

PR:append = "_tisdk_0"
