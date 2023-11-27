TI_LINUX_FW_SRCREV:j721s2 = "1c28bc84f3e82a29e27ac400aaf880062559575c"

PR:append = "_tisdk_1"

IPC_DM_FW = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

# DM FW to be used only for AM62P tisdk-display-cluster image
DISPLAY_CLUSTER_FW = "dss_display_share.wkup-r5f0_0.release.strip.out"

DM_FIRMWARE:am62pxx = "${@oe.utils.conditional("DISPLAY_CLUSTER_ENABLE", "1", "${DISPLAY_CLUSTER_FW}", "${IPC_DM_FW}", d)}"

# For AM62Px, this will install any FW with different name as ipc_echo_testb_mcu1_0_release_strip.xer5f
do_install:am62pxx() {
    # Sign the firmware
    # DM Firmware
    for FW_NAME in ${DM_FW_LIST}
    do
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${S}/${DM_FW_DIR}/${FW_NAME} ${S}/${DM_FW_DIR}/${FW_NAME}.signed
    done

    # Install DM Firmware as ipc
    install -d ${D}${INSTALL_DM_FW_DIR}
    for FW_NAME in ${DM_FW_LIST}
    do
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}        ${D}${INSTALL_DM_FW_DIR}/${IPC_DM_FW}
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}.signed ${D}${INSTALL_DM_FW_DIR}/${IPC_DM_FW}.signed
    done
}

# For AM62P, this hack is needed as DM FW filename is not flexible w.r.t binman & is hardcoded
# as "ipc_echo_testb_mcu1_0_release_strip.xer5f"
do_deploy:am62pxx() {
    # DM Firmware is needed for rebuilding U-Boot
    install -d ${DEPLOYDIR}/${DM_FW_DIR}
    for FW_NAME in ${DM_FW_LIST}
    do
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}        ${DEPLOYDIR}/${DM_FW_DIR}/${IPC_DM_FW}
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}.signed ${DEPLOYDIR}/${DM_FW_DIR}/${IPC_DM_FW}.signed
    done
}
