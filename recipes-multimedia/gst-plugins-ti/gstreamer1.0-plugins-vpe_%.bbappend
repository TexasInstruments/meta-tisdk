FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_configure[network] = "1"

SRC_URI += "file://0001-Fix-unable-to-connect-to-naongit.freedesktop.org.patch"

PR:append = "_tisdk_0"
