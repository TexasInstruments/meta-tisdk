PR:append = "_tisdk_0"

DEPENDS += " docker-moby"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh -p '' --user-group -G video,input,render,nopasswdlogin,wayland,docker weston"

GROUPADD_PARAM:${PN} = "-r wayland; -r render; -r nopasswdlogin; -r docker"
