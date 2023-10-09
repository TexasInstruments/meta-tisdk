PR:append = "_tisdk_0"

DEPENDS += " docker-ce"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh -p '' --user-group -G video,input,render,wayland,docker weston"

GROUPADD_PARAM:${PN} = "-r wayland; -r render; -r docker" 

