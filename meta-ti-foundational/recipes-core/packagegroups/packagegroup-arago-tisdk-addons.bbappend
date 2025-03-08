PR:append = "_tisdk_3"

DEMOS = ""
# DEMOS:append:am62xx = " ti-apps-launcher"
# DEMOS:append:am62pxx = " ti-apps-launcher"
DEMOS:append:am62lxx = " ti-lvgl-demo"
DEMOS:append:foundational = " ti-apps-launcher"
DEMOS:append:am335x-evm = " pru-adc"

DEMOS:append:am64xx = " \
    benchmark-server \
    print-ip \
    opcua-server \
"

EXTRA_PACKAGES:append:ti33x = " opencv"
EXTRA_PACKAGES:append:ti43x = " opencv"

UTILS:append = " \
    hidapi \
    net-snmp \
    net-snmp-server-snmpd \
"

RDEPENDS:${PN}:append = " \
    ${DEMOS} \
"

