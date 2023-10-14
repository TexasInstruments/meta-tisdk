SUMMARY = "OPC/UA server for experimentation"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "git://git.ti.com/processor-sdk/sitara-apps.git;protocol=git;branch=master"
SRCREV = "2f75495113219580eb1f5351920aa04abf43ccd0"
PV = "0.1"

S = "${WORKDIR}/git/servo_drive_demo/opc_ua_app"

DEPENDS = "open62541"

inherit cmake python3native

CREATE_SRCIPK = "1"
SRCIPK_INSTALL_DIR = "examples/opc-ua-server"

PR = "r0"
