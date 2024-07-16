SRCREV:tie-jailhouse = "6f201e0dc3f047010321c8a9f1966d6b8de9db8a"

IPC_DM_FW = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# DM FW to be used only for AM62P tisdk-display-cluster image
DISPLAY_CLUSTER_FW = "dss_display_share.wkup-r5f0_0.release.strip.out"

DM_FIRMWARE:am62pxx = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "${DISPLAY_CLUSTER_FW}", "${IPC_DM_FW}", d)}"

TI_DM="${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/ti-dm/${PLAT_SFX}/${DM_FIRMWARE}"

EXTRA_OEMAKE += "TI_DM=${TI_DM}"

PR:append = "_tisdk_5"

