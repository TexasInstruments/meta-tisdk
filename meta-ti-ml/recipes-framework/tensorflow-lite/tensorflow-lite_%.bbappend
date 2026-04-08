# This appends the dev files to the -dev in tensorflow-lite
FILES:${PN}-dev += " \
    ${includedir}/* \
    ${libdir}/lib*.so \
"
