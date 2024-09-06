SRCREV:tie-jailhouse = "782a2e18a2af099d5c53bc4ba9f59d24a0ce8467"

include ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'linux-ti-enable-xen.inc', '', d)}

PR:append = "_tisdk_8"

