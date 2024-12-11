inherit deploy

do_deploy() {
  install -d ${DEPLOYDIR}
}

do_deploy:append:omap-a15() {
  install -d ${DEPLOYDIR}/ipc
  install -m 0644 ${S}/packages/ti/ipc/tests/bin/ti_platforms_evmDRA7XX_ipu1/test_omx_ipu1_vayu.xem4 ${DEPLOYDIR}/ipc/dra7-ipu1-fw.xem4
}

addtask deploy after do_install

