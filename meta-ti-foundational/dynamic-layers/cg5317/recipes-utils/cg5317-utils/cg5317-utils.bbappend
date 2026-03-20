FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_install:append() {
    install -m 0644 ${D}${nonarch_base_libdir}/firmware/lumissil/spi_evse_config.bin ${D}${nonarch_base_libdir}/firmware/lumissil/config.bin
}

PR:append = "_tisdk_1"
