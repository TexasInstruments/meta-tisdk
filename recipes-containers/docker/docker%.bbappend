PR:append = "_tisdk_0"

# Disable docker for AM62P's tisdk-display-cluster-image
SYSTEMD_AUTO_ENABLE:${PN} = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "disable", "enable", d)}"

# Always disable docker for AM62SIP
SYSTEMD_AUTO_ENABLE:${PN}:am62xxsip-evm = "disable"
