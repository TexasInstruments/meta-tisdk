SRCREV:tie-jailhouse = "c76373e447c96b32fe08beceef7a499d16eaed96"

include ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'linux-ti-enable-xen.inc', '', d)}

PR:append = "_tisdk_5"
