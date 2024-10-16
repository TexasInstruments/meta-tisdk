SRCREV:tie-jailhouse = "6301979bc99cd27951ee140df4b29bcfa4823fdd"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

IPC_DM_FW = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# DM FW to be used only for AM62P tisdk-display-cluster image
DISPLAY_CLUSTER_FW = "dss_display_share.wkup-r5f0_0.release.strip.out"

DM_FIRMWARE:am62pxx = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "${DISPLAY_CLUSTER_FW}", "${IPC_DM_FW}", d)}"

TI_DM="${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/ti-dm/${PLAT_SFX}/${DM_FIRMWARE}"

EXTRA_OEMAKE += "TI_DM=${TI_DM}"

SRC_URI:append:k3 = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'file://bootmethscript.cfg', '', d)} \
"

PR:append = "_tisdk_6"

