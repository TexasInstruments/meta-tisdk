PR:append = "_tisdk_4"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:j784s4 = " \
    file://tiLFW-1-1-ti-dm-Update-firmware-for-j784s4.patch \
"

PATCHTOOL = "git"

IPC_DM_FW = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# DM FW to be used only for AM62P tisdk-display-cluster image
DISPLAY_CLUSTER_FW = "dss_display_share.wkup-r5f0_0.release.strip.out"

DM_FIRMWARE:am62pxx = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "${DISPLAY_CLUSTER_FW}", "${IPC_DM_FW}", d)}"
