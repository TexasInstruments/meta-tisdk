PR:append = "_tisdk_1"

DEMOS = ""
DEMOS:append:am62xx = " ti-apps-launcher"
DEMOS:append:am62pxx = " ti-apps-launcher"
DEMOS:append:j721e = " wifi-oob"
DEMOS:append:j721s2 = " wifi-oob"
DEMOS:append:j784s4 = " wifi-oob"
DEMOS:append:am335x-evm = " pru-adc"

DEMOS:append:am64xx = " \
    benchmark-server \
    print-ip \
    opcua-server \
"

EXTRA_PACKAGES:append = " watchdog"

UTILS:append = " \
    hidapi \
    net-snmp \
    net-snmp-server-snmpd \
"

RDEPENDS:${PN}:append = " \
    ${DEMOS} \
"

