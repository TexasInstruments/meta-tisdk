PR:append = "_tisdk_0"

DEPENDS += " docker-moby"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh -p '' --user-group -G video,input,render,wayland,docker weston"

GROUPADD_PARAM:${PN} = "-r wayland; -r render; -r docker" 

# Disable weston for tisdk-display-cluster-image
SYSTEMD_AUTO_ENABLE:${PN} = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "disable", "enable", d)}"

# Always disable weston for AM62SIP
SYSTEMD_AUTO_ENABLE:${PN}:am62xxsip-evm = "disable"

# Always disable weston for AM62L
SYSTEMD_AUTO_ENABLE:${PN}:am62lxx-evm = "disable"

# Always disable weston for AM64X
SYSTEMD_AUTO_ENABLE:${PN}:am64xx-evm = "disable"
