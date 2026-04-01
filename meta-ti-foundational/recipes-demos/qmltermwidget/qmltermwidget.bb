# Recipe taken from https://github.com/webOS-ports/meta-webos-ports
# Commit: 528204c26b1550f54b7b93cbc9c32352ab5b0839
SUMMARY = "A terminal emulator QML widget, based on LXQt's QTermWidget"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b"

PV = "0.14.1+git"
SRCREV = "ce8e09ad4daeb9fe59b60d6b228b0f7cf4055e16"

DEPENDS = "qtbase qtdeclarative qt5compat"
RDEPENDS:${PN} = "ttf-liberation-mono"

SRC_URI = " \
    git://github.com/Swordfish90/qmltermwidget.git;protocol=https;branch=master \
    file://0001-qmltermwidget.pro-don-t-install-asset-directories-tw.patch \
"

inherit ${@bb.utils.contains('BBFILE_COLLECTIONS', 'qt6-layer', 'qt6-qmake', '', d)}

FILES:${PN} += "${libdir}"
