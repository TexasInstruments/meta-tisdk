PR:append = "_tisdk_0"

DEPENDS += " docker-moby"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh -p '' --user-group -G video,input,render,nopasswdlogin,wayland,docker weston"

GROUPADD_PARAM:${PN} = "-r wayland; -r render; -r nopasswdlogin; -r docker"

# Add HDMI configuration for am335x-evm machine to support BeagleBone boards with HDMI capes
do_install:append:am335x-evm() {
    cat <<EOF >> ${D}${sysconfdir}/xdg/weston/weston.ini

[output]
name=HDMI-A-1
gbm-format=rgb565
EOF
}
