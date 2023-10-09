PR:append = "_tisdk_0"

SRC_URI:remove = "git://git.infradead.org/mtd-utils.git;branch=master \
"

SRC_URI:prepend = "git://github.com/sigma-star/mtd-utils.git;branch=master;protocol=https \
"

SRC_URI[sha256sum] = "3d6f058f8d3743068e45404d5c6c743fb91b19685c481b3a0601cad77171de75"

