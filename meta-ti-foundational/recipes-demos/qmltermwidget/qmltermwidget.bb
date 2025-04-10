# Recipe taken from https://github.com/webOS-ports/meta-webos-ports
# Commit: 528204c26b1550f54b7b93cbc9c32352ab5b0839
SUMMARY = "A terminal emulator QML widget, based on LXQt's QTermWidget"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b"

PV = "0.14.1+git"
SRCREV = "ac84d444b278d5682413f7200dd324ee6c564a01"

DEPENDS = "qtbase qtdeclarative qt5compat"
RDEPENDS:${PN} = "ttf-liberation-mono"

SRC_URI = " \
    git://github.com/kalaksi/qmltermwidget.git;protocol=https;branch=qt6-font-fix \
    file://0001-qmltermwidget.pro-don-t-install-asset-directories-tw.patch \
"
S = "${WORKDIR}/git"

inherit ${@bb.utils.contains('BBLAYERS', 'meta-qt6', 'qt6-qmake', '', d)}

FILES:${PN} += "${libdir}"
