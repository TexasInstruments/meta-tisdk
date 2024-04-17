SUMMARY = "Webserver for the Sitara Benchmark Demo"
LICENSE = "BSD-3-Clause & MIT & ISC"

COMPATIBLE_MACHINE = "am64xx"

# NPM pulls in many "independent" packages into a single app package
LIC_FILES_CHKSUM = "\
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
    file://node_modules/accepts/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
    file://node_modules/array-flatten/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
    file://node_modules/body-parser/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
    file://node_modules/bytes/LICENSE;md5=013e95467eddb048f19a6f5b42820f86 \
    file://node_modules/content-disposition/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/content-type/LICENSE;md5=f4b767f006864f81a4901347fe4efdab \
    file://node_modules/cookie/LICENSE;md5=bc85b43b6f963e8ab3f88e63628448ca \
    file://node_modules/cookie-signature/Readme.md;beginline=21;md5=db8ec78b5bb012861b19b8353c342fbe \
    file://node_modules/debug/LICENSE;md5=ddd815a475e7338b0be7a14d8ee35a99 \
    file://node_modules/depd/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/destroy/LICENSE;md5=c8d3a30332ecb31cfaf4c0a06da18f5c \
    file://node_modules/edit-json-file/LICENSE;md5=53e80078cd02a6dd9555b612acad2860 \
    file://node_modules/ee-first/LICENSE;md5=c8d3a30332ecb31cfaf4c0a06da18f5c \
    file://node_modules/encodeurl/LICENSE;md5=272621efa0ff4f18a73221e49ab60654 \
    file://node_modules/escape-html/LICENSE;md5=f8746101546eeb9e4f6de64bb8bdf595 \
    file://node_modules/etag/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
    file://node_modules/express/LICENSE;md5=5513c00a5c36cd361da863dd9aa8875d \
    file://node_modules/finalhandler/LICENSE;md5=b506956e5cbfbe0d04f8a5c40107ec89 \
    file://node_modules/find-value/LICENSE;md5=4f53b2fb7fd6b09d250ddb4373ae1e7b \
    file://node_modules/forwarded/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/fresh/LICENSE;md5=373c2cf0978b37e434394a43b4cbbdb4 \
    file://node_modules/http-errors/LICENSE;md5=607209623abfcc77b9098f71a0ef52f9 \
    file://node_modules/iconv-lite/LICENSE;md5=f942263d98f0d75e0e0101884e86261d \
    file://node_modules/inherits/LICENSE;md5=5b2ef2247af6d355ae9d9f988092d470 \
    file://node_modules/ipaddr.js/LICENSE;md5=88f60a4b6e44cb849b5d907a7664c0ef \
    file://node_modules/isobject/LICENSE;md5=2f5638b0557426da0c3bd67e00a0ebde \
    file://node_modules/is-plain-object/LICENSE;md5=3d83ea4c8ec9b31d9ff2c82fa29beabb \
    file://node_modules/iterate-object/LICENSE;md5=2cfa2550f2771f0df1bdb718e3d4f9b4 \
    file://node_modules/media-typer/LICENSE;md5=c6e0ce1e688c5ff16db06b7259e9cd20 \
    file://node_modules/merge-descriptors/LICENSE;md5=aaf57ba8c5c9bf256fea7e943991a81a \
    file://node_modules/methods/LICENSE;md5=c16a7dd9f946172f07086576d135d9d3 \
    file://node_modules/mime-db/LICENSE;md5=c8d3a30332ecb31cfaf4c0a06da18f5c \
    file://node_modules/mime/LICENSE;md5=8e8ea2ad138ce468f8570a0edbadea65 \
    file://node_modules/mime-types/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
    file://node_modules/ms/license.md;md5=fd56fd5f1860961dfa92d313167c37a6 \
    file://node_modules/negotiator/LICENSE;md5=6417a862a5e35c17c904d9dda2cbd499 \
    file://node_modules/on-finished/LICENSE;md5=1b1f7f9cec194121fdf616b971df7a7b \
    file://node_modules/parseurl/LICENSE;md5=e7842ed4f188e53e53c3e8d9c4807e89 \
    file://node_modules/path-to-regexp/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
    file://node_modules/proxy-addr/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
    file://node_modules/qs/LICENSE;md5=d5c7c6dc45a33a0a9620ed81315672d7 \
    file://node_modules/range-parser/LICENSE;md5=d4246fb961a4f121eef5ffca47f0b010 \
    file://node_modules/raw-body/LICENSE;md5=c970d30155ebbdb1903e6de8c0666e18 \
    file://node_modules/r-json/LICENSE;md5=4f53b2fb7fd6b09d250ddb4373ae1e7b \
    file://node_modules/safe-buffer/LICENSE;md5=badd5e91c737e7ffdf10b40c1f907761 \
    file://node_modules/safer-buffer/LICENSE;md5=3baebc2a17b8f5bff04882cd0dc0f76e \
    file://node_modules/send/LICENSE;md5=df2b0938eba0709b77ac937e2d552b7a \
    file://node_modules/serve-static/LICENSE;md5=27b1707520b14d0bc890f4e75cd387b0 \
    file://node_modules/setprototypeof/LICENSE;md5=4846f1626304c2c0f806a539bbc7d54a \
    file://node_modules/set-value/LICENSE;md5=0f64900f8f30e53054962c9f1fc3205b \
    file://node_modules/statuses/LICENSE;md5=36e2bc837ce69a98cc33a9e140d457e5 \
    file://node_modules/toidentifier/LICENSE;md5=1a261071a044d02eb6f2bb47f51a3502 \
    file://node_modules/type-is/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
    file://node_modules/unpipe/LICENSE;md5=934ab86a8ab081ea0326add08d550739 \
    file://node_modules/utils-merge/LICENSE;md5=1cf0906082187f374cb9a63c54eb782c \
    file://node_modules/vary/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/w-json/LICENSE;md5=4f53b2fb7fd6b09d250ddb4373ae1e7b \
"

SRC_URI = " \
    git://git.ti.com/processor-sdk/sitara-apps.git;protocol=git;branch=master \
    npmsw://${NPM_SHIRNKWRAP} \
    git://git.ti.com/gui-composer-components/ti-gc-components.git;protocol=git;branch=master;destsuffix=git/benchmark_demo/webserver_app/app/components;name=guicomposer \
"
SRCREV = "89cd7d9d048231bc6f9f504f9b705e7655b0e085"
SRCREV_FORMAT = "default"
PV = "1.0.0"

NPM_SHIRNKWRAP := "${THISDIR}/${BPN}/npm-shrinkwrap.json"
SRCREV_guicomposer = "18115d266ba9f1956d06258ce2c8997fd1ef2efe"

RDEPENDS:${PN} = "benchmark-demo-firmware sitara-ipc-app"

inherit npm systemd

# Must be set after inherit npm since that itself sets S

WEBSERVER_ROOT = "${WORKDIR}/git/benchmark_demo/webserver_app"

# Set this for npm.bbclass
S = "${WEBSERVER_ROOT}/webserver"

LICENSE:${PN} = "BSD-3-Clause"

do_install:append() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    # Install service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 benchmark_server.service \
                    ${D}${systemd_system_unitdir}/benchmark_server.service

    # Install GUI Composer app
    install -d ${D}${datadir}/${BPN}/app
    cp $CP_ARGS ${WEBSERVER_ROOT}/app/* ${D}${datadir}/${BPN}/app/

    # Remove git directory
    rm -rf ${D}${datadir}/${BPN}/app/components/.git*

    # GUI composer provides some examples, remove the binaries for now
    find ${D}${datadir}/${BPN}/app/components -name '*\.out' -exec rm {} \;

    # install server defaults file, replacing with how we choose to install
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/benchmark_server.conf ${D}${sysconfdir}
    sed -i -e 's|^APP_DIR=.*$|APP_DIR='"${datadir}/${BPN}/app"'|' ${D}${sysconfdir}/benchmark_server.conf
}

SYSTEMD_SERVICE:${PN} = "benchmark_server.service"

PR = "r1"
