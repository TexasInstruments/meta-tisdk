# The .so contains an ABI tag that denotes the minimum kernel version required.
# Since Docker has no own kernel, the Docker host’s kernel version is used for
# that comparison. Remove this check to un-break builds on some hosts.
do_install:append:class-native() {
        strip --remove-section=.note.ABI-tag ${D}${libdir}/libQt6Core.so.6.9.0
}

PR:append = ".tisdk0"
