# Override files from GitHub repository
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Fetch from GitHub
SRC_URI:append = " git://github.com/TexasInstruments/tida-010939-cg5317-utils;protocol=https;branch=main;name=github"
SRCREV_github = "7b703f67ff6eaca2c2cff4e38d98154fc23d19c8"

S = "${WORKDIR}/git"

do_install:append() {
    # Override firmware files from TI GitHub
    install -m 0644 ${S}/firmware/config.bin ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${S}/firmware/FW.bin ${D}${nonarch_base_libdir}/firmware

    # Override systemd services from TI GitHub
    install -m 0644 ${S}/services/cg5317-host.service ${D}${sysconfdir}/systemd/system
    install -m 0644 ${S}/services/cg5317-reset.service ${D}${sysconfdir}/systemd/system

    # Enable cg5317-reset.service for network.target
    install -d ${D}${sysconfdir}/systemd/system/network.target.wants
    ln -sf ../cg5317-reset.service ${D}${sysconfdir}/systemd/system/network.target.wants/cg5317-reset.service

    # Replace /root/examples with utils from TI GitHub
    rm -rf ${D}/root/examples
    install -d ${D}/root/examples

    # Copy all utils subdirectories
    cp -r ${S}/utils/* ${D}/root/examples/

    # Make all files in utils executable
    find ${D}/root/examples -type f -exec chmod +x {} \;
}

PR:append = "_tisdk_0"
