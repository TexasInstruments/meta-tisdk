FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:sk = " \
		    file://0001-configs-j721e-Sign-the-j721e-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j721s2-Sign-the-am68-sk-dtb-by-default-for-h.patch \
		    file://0001-configs-j784s4-Sign-the-am69-sk-dtb-by-default-for-h.patch \
		    "
SRCREV:tie-jailhouse = "9ed275573821d171ac072cf8c78f94ec92a2a469"

IPC_DM_FW = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# DM FW to be used only for AM62P tisdk-display-cluster image
DISPLAY_CLUSTER_FW = "dss_display_share.wkup-r5f0_0.release.strip.out"

DM_FIRMWARE:am62pxx = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "${DISPLAY_CLUSTER_FW}", "${IPC_DM_FW}", d)}"

TI_DM="${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/ti-dm/${PLAT_SFX}/${DM_FIRMWARE}"

EXTRA_OEMAKE += "TI_DM=${TI_DM}"

PR:append = "_tisdk_3"

