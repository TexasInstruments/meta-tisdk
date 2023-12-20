EXTRA_OEMAKE:append:am62xx = " \
	${@oe.utils.conditional("ARAGO_RT_ENABLE", "1", "CFG_WITH_SOFTWARE_PRNG=y", "", d)} \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "CFG_WITH_SOFTWARE_PRNG=y", "", d)} \
"

EXTRA_OEMAKE:append:am64xx = " ${@oe.utils.conditional("ARAGO_RT_ENABLE", "1", "CFG_WITH_SOFTWARE_PRNG=y", "", d)}"
EXTRA_OEMAKE:append:am62axx = " ${@oe.utils.conditional("ARAGO_RT_ENABLE", "1", "CFG_WITH_SOFTWARE_PRNG=y", "", d)}"

PR:append = "_tisdk_3"
