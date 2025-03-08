SRCREV:tie-jailhouse = "d9e5e81dd6b69648177ce5cef9b7a4bbb70edec3"

include ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'linux-ti-enable-xen.inc', '', d)}

PR:append = "_tisdk_8"

