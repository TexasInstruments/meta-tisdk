EXTRA_OEMAKE:append:am62xx = " \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "CFG_WITH_SOFTWARE_PRNG=y", "", d)} \
"

EXTRA_OEMAKE:append:am62pxx = " \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "CFG_WITH_SOFTWARE_PRNG=y", "", d)} \
"

EXTRA_OEMAKE:append:am62lxx = " \
	${@oe.utils.conditional("TI_EXTRAS", "tie-jailhouse", "CFG_WITH_SOFTWARE_PRNG=y", "", d)} \
"

EXTRA_OEMAKE:append:beagleplay = " \
	CFG_WITH_SOFTWARE_PRNG=y \
"

PR:append = "_tisdk_5"
