
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:j721s2 = " \
    file://0001-services-memory-coherency-fix-for-HWR-on-BXS-devices.patch \
    file://0002-j7-platforms-disable-SUPPORT_CPUCACHED_FWMEMCTX.patch \
"

SRC_URI:append:j784s4 = " \
    file://0001-services-memory-coherency-fix-for-HWR-on-BXS-devices.patch \
    file://0002-j7-platforms-disable-SUPPORT_CPUCACHED_FWMEMCTX.patch \
"

PR:append = "_tisdk_0"
