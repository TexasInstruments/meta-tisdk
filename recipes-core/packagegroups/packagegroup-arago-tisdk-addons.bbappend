PR:append = "_tisdk_0"

DEMOS = ""

DEMOS:append:am62xx = " ti-apps-launcher"

DEMOS:append:am62pxx = " ti-apps-launcher"

RDEPENDS:${PN}:append = " \
    ${DEMOS} \
"

