PR:append = "_tisdk_0"

DEMOS = ""

DEMOS:append:am62xx = " ti-apps-launcher"

DEMOS:append:am62pxx = " ti-apps-launcher"

DEMOS:append:am64xx = " benchmark-server"

RDEPENDS:${PN}:append = " \
    ${DEMOS} \
"

