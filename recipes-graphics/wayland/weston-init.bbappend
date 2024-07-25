PR:append = "_tisdk_0"

DEPENDS += " docker-moby"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh -p '' --user-group -G video,input,render,wayland,docker weston"

GROUPADD_PARAM:${PN} = "-r wayland; -r render; -r docker" 

# Always disable weston for AM62SIP
SYSTEMD_AUTO_ENABLE:${PN}:am62xxsip-evm = "disable"

# Always disable weston for AM64X
SYSTEMD_AUTO_ENABLE:${PN}:am64xx-evm = "disable"
