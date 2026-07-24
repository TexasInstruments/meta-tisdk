SUMMARY = "Out-of-box web server for demonstrating example application demos"
LICENSE = "BSD-3-Clause & MIT & ISC"

COMPATIBLE_MACHINE = "ti33x|am62xx|am62pxx|am62lxx|am62dxx|am64xx"

# Maps to repo devices/<DEVICE_ID>/ directory.
# Use SOC_FAMILY overrides where one override covers multiple machines.
DEVICE_ID = "unknown"
DEVICE_ID:ti33x = "am335x"
DEVICE_ID:am62xx = "am62xx"
DEVICE_ID:am62pxx = "am62pxx"
DEVICE_ID:am62lxx = "am62lxx"
DEVICE_ID:am62dxx = "am62dxx"
DEVICE_ID:am64xx = "am64xx"

# NPM pulls in many "independent" packages into a single app package
LIC_FILES_CHKSUM = "\
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
    file://node_modules/accepts/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
    file://node_modules/array-flatten/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
    file://node_modules/body-parser/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
    file://node_modules/bytes/LICENSE;md5=013e95467eddb048f19a6f5b42820f86 \
    file://node_modules/call-bind-apply-helpers/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/call-bound/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/content-disposition/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/content-type/LICENSE;md5=f4b767f006864f81a4901347fe4efdab \
    file://node_modules/cookie/LICENSE;md5=bc85b43b6f963e8ab3f88e63628448ca \
    file://node_modules/cookie-signature/Readme.md;beginline=21;md5=db8ec78b5bb012861b19b8353c342fbe \
    file://node_modules/debug/LICENSE;md5=ddd815a475e7338b0be7a14d8ee35a99 \
    file://node_modules/depd/LICENSE;md5=ebc30494fd072dc98368da73e1821715 \
    file://node_modules/destroy/LICENSE;md5=d5eb22cf6cc99e645b98b28ee3503ddf \
    file://node_modules/dunder-proto/LICENSE;md5=a5b1dd92a77a6632ebcc7425b08e9078 \
    file://node_modules/ee-first/LICENSE;md5=c8d3a30332ecb31cfaf4c0a06da18f5c \
    file://node_modules/encodeurl/LICENSE;md5=272621efa0ff4f18a73221e49ab60654 \
    file://node_modules/es-define-property/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/es-errors/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/es-object-atoms/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/escape-html/LICENSE;md5=f8746101546eeb9e4f6de64bb8bdf595 \
    file://node_modules/etag/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
    file://node_modules/express/LICENSE;md5=5513c00a5c36cd361da863dd9aa8875d \
    file://node_modules/finalhandler/LICENSE;md5=462b10b32bb9175b97944aabef4aa171 \
    file://node_modules/forwarded/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/fresh/LICENSE;md5=373c2cf0978b37e434394a43b4cbbdb4 \
    file://node_modules/function-bind/LICENSE;md5=e7417c1a8ad83f88bcac21ad440d48b2 \
    file://node_modules/get-intrinsic/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
    file://node_modules/get-proto/LICENSE;md5=a0b3a4562fb57e50242fb66b24fd2cdf \
    file://node_modules/gopd/LICENSE;md5=8478c87d16770f6d32a4578c475d3930 \
    file://node_modules/has-symbols/LICENSE;md5=afee57a289508ed4df3456667778aaf6 \
    file://node_modules/hasown/LICENSE;md5=19283ee92f78c91154834571c1f05a94 \
    file://node_modules/http-errors/LICENSE;md5=607209623abfcc77b9098f71a0ef52f9 \
    file://node_modules/iconv-lite/LICENSE;md5=f942263d98f0d75e0e0101884e86261d \
    file://node_modules/inherits/LICENSE;md5=5b2ef2247af6d355ae9d9f988092d470 \
    file://node_modules/ipaddr.js/LICENSE;md5=88f60a4b6e44cb849b5d907a7664c0ef \
    file://node_modules/math-intrinsics/LICENSE;md5=a5b1dd92a77a6632ebcc7425b08e9078 \
    file://node_modules/media-typer/LICENSE;md5=c6e0ce1e688c5ff16db06b7259e9cd20 \
    file://node_modules/merge-descriptors/LICENSE;md5=aaf57ba8c5c9bf256fea7e943991a81a \
    file://node_modules/methods/LICENSE;md5=c16a7dd9f946172f07086576d135d9d3 \
    file://node_modules/mime/LICENSE;md5=8e8ea2ad138ce468f8570a0edbadea65 \
    file://node_modules/mime-db/LICENSE;md5=175b28b58359f8b4a969c9ab7c828445 \
    file://node_modules/mime-types/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
    file://node_modules/ms/license.md;md5=fd56fd5f1860961dfa92d313167c37a6 \
    file://node_modules/negotiator/LICENSE;md5=6417a862a5e35c17c904d9dda2cbd499 \
    file://node_modules/object-inspect/LICENSE;md5=288162f1d1bfa064f127f2b42d2a656f \
    file://node_modules/on-finished/LICENSE;md5=1b1f7f9cec194121fdf616b971df7a7b \
    file://node_modules/parseurl/LICENSE;md5=e7842ed4f188e53e53c3e8d9c4807e89 \
    file://node_modules/path-to-regexp/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
    file://node_modules/proxy-addr/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
    file://node_modules/qs/LICENSE.md;md5=b289135779dd930509ae81e6041690c0 \
    file://node_modules/range-parser/LICENSE;md5=d4246fb961a4f121eef5ffca47f0b010 \
    file://node_modules/raw-body/LICENSE;md5=f22163d3bc6b4bc1bbbdf654fe30af5b \
    file://node_modules/safe-buffer/LICENSE;md5=badd5e91c737e7ffdf10b40c1f907761 \
    file://node_modules/safer-buffer/LICENSE;md5=3baebc2a17b8f5bff04882cd0dc0f76e \
    file://node_modules/send/LICENSE;md5=5f1a8369a899b128aaa8a59d60d00b40 \
    file://node_modules/serve-static/LICENSE;md5=27b1707520b14d0bc890f4e75cd387b0 \
    file://node_modules/setprototypeof/LICENSE;md5=4846f1626304c2c0f806a539bbc7d54a \
    file://node_modules/side-channel/LICENSE;md5=375dc7ca936a14e9c29418d5263bd066 \
    file://node_modules/side-channel-list/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/side-channel-map/LICENSE;md5=8fe23ea421aaf9f9d687709f6a6a09b7 \
    file://node_modules/side-channel-weakmap/LICENSE;md5=375dc7ca936a14e9c29418d5263bd066 \
    file://node_modules/statuses/LICENSE;md5=36e2bc837ce69a98cc33a9e140d457e5 \
    file://node_modules/toidentifier/LICENSE;md5=1a261071a044d02eb6f2bb47f51a3502 \
    file://node_modules/type-is/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
    file://node_modules/unpipe/LICENSE;md5=934ab86a8ab081ea0326add08d550739 \
    file://node_modules/utils-merge/LICENSE;md5=1cf0906082187f374cb9a63c54eb782c \
    file://node_modules/vary/LICENSE;md5=13babc4f212ce635d68da544339c962b \
    file://node_modules/ws/LICENSE;md5=7a4bd929a6c0e6951846d75e53fc9f51 \
"

# webserver-oob-npm.inc auto-generated by tools/generate-inc.js from the webserver-oob-demo
# source repository. Provides NPM_SRC_URI and NPM_PACKAGE_MAP variables.
require webserver-oob-npm.inc

SRC_URI = " \
    git://github.com/TexasInstruments/webserver-oob-demo.git;protocol=https;branch=main \
    git://git.ti.com/git/gui-composer-components/ti-gc-components.git;protocol=https;branch=master;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/common/app/components;name=guicomposer \
    ${NPM_SRC_URI} \
"
SRCREV = "297f002e65c4eceb172706049f703bed7db4152e"
SRCREV_guicomposer = "18115d266ba9f1956d06258ce2c8997fd1ef2efe"
SRCREV_FORMAT = "default"
PV = "1.0.0"

RDEPENDS:${PN} = "nodejs tensorflow-lite nnstreamer analytics-demo-data"
RDEPENDS:${PN}:append:am64xx = " benchmark-demo-firmware"
# speech-to-text support for am62pxx, am62xx, am62lxx
RDEPENDS:${PN}:append:am62pxx = " gstreamer1.0 glib-2.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good onnxruntime"
RDEPENDS:${PN}:append:am62xx = " gstreamer1.0 glib-2.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good onnxruntime"
RDEPENDS:${PN}:append:am62lxx = " gstreamer1.0 glib-2.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good onnxruntime"

# Build-time GStreamer headers for speech_utils cross-compilation (am62pxx, am62xx, am62lxx)
DEPENDS:append:am62pxx = " gstreamer1.0 glib-2.0"
DEPENDS:append:am62xx = " gstreamer1.0 glib-2.0"
DEPENDS:append:am62lxx = " gstreamer1.0 glib-2.0"

WEBSERVER_ROOT = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"
S = "${WEBSERVER_ROOT}/common/webserver"

# linux_app dirs are outside S so add explicit prefix map so that debug info paths get remapped
DEBUG_PREFIX_MAP:append = " -ffile-prefix-map=${WEBSERVER_ROOT}=/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}"

TARGET_CC_ARCH += "${LDFLAGS}"
inherit pkgconfig systemd

# Extract npm tarballs to node_modules, stripping top-level package/ directory.
# Runs after do_unpack (git sources ready) and before do_patch (for license checks).
python do_npm_unpack() {
    import subprocess
    import os

    s_dir = d.getVar("S")
    dl_dir = d.getVar("DL_DIR")
    npm_map = d.getVar("NPM_PACKAGE_MAP") or ""

    # Parse package map into list of (install_path, tarball) tuples
    packages = [e.split('=', 1) for e in npm_map.split() if '=' in e]
    if not packages:
        bb.note("No npm packages to extract")
        return

    bb.note("Preparing to extract %d npm packages..." % len(packages))

    # Pre-validate that all tarballs exist before extraction
    missing = [t for _, t in packages if not os.path.exists(os.path.join(dl_dir, t))]
    if missing:
        bb.fatal("Missing npm tarballs (%d):\n  %s" % (len(missing), "\n  ".join(missing)))

    # Extract each package
    for idx, (install_path, tarball) in enumerate(packages, 1):
        target_dir = os.path.join(s_dir, install_path)
        tarball_path = os.path.join(dl_dir, tarball)

        bb.utils.mkdirhier(target_dir)

        cmd = ["tar", "xzf", tarball_path, "--strip-components=1", "-C", target_dir]
        try:
            subprocess.check_output(cmd, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            bb.fatal("Failed to extract package %d/%d (%s -> %s): %s\nOutput: %s"
                     % (idx, len(packages), tarball, install_path, str(e), e.output.decode('utf-8', errors='replace')))

        # Progress indicator for every 20 packages
        if idx % 20 == 0:
            bb.note("Progress: %d/%d packages extracted" % (idx, len(packages)))

    bb.note("Successfully extracted %d npm packages into %s/node_modules" % (len(packages), s_dir))
}
addtask npm_unpack after do_unpack before do_patch

do_configure[noexec] = "1"

do_compile() {
    oe_runmake -C ${WEBSERVER_ROOT}/common/linux_app
    oe_runmake -C ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    install -d ${D}${nonarch_libdir}/node_modules/${BPN}
    cp $CP_ARGS ${S}/. ${D}${nonarch_libdir}/node_modules/${BPN}/
    rm -f ${D}${nonarch_libdir}/node_modules/${BPN}/npm-shrinkwrap.json

    install -d ${D}${bindir}
    chmod +x ${D}${nonarch_libdir}/node_modules/${BPN}/server.js
    ln -s ${nonarch_libdir}/node_modules/${BPN}/server.js ${D}${bindir}/webserver-oob

    install -m 0755 ${WEBSERVER_ROOT}/common/linux_app/cpu_stats ${D}${bindir}/cpu_stats
    if [ -f ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/audio_utils ]; then
        install -m 0755 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/audio_utils ${D}${bindir}/audio_utils
    fi
    if [ -f ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/speech_utils ]; then
        install -m 0755 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/speech_utils ${D}${bindir}/speech_utils
    fi

    # Install demos
    install -d ${D}${datadir}/${BPN}/demos
    cp $CP_ARGS ${WEBSERVER_ROOT}/demos/. ${D}${datadir}/${BPN}/demos/

    install -d ${D}${datadir}/${BPN}/app
    cp $CP_ARGS ${WEBSERVER_ROOT}/common/app/. ${D}${datadir}/${BPN}/app/
    if [ -d ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/app ]; then
        cp $CP_ARGS ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/app/. ${D}${datadir}/${BPN}/app/
    fi
    install -m 0644 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/device.json \
        ${D}${datadir}/${BPN}/app/device.json

    rm -rf ${D}${datadir}/${BPN}/app/components/.git*
    find ${D}${datadir}/${BPN}/app/components -name '*\.out' -exec rm {} \;
    find ${D}${datadir}/${BPN}/app/components -name '*\.exe' -exec rm {} \;

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/webserver-oob.service ${D}${systemd_system_unitdir}/

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/webserver-oob.conf ${D}${sysconfdir}/
    sed -i -e 's|^APP_DIR=.*$|APP_DIR='"${datadir}/${BPN}/app"'|' \
           -e 's|^DEVICE_CONFIG=.*$|DEVICE_CONFIG='"${datadir}/${BPN}/app/device.json"'|' \
        ${D}${sysconfdir}/webserver-oob.conf
}


do_install:append:am64xx() {

    install -m 0755 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/rpmsg_json ${D}${bindir}/rpmsg_json
    install -m 0644 ${WEBSERVER_ROOT}/devices/${DEVICE_ID}/linux_app/rpmsg-json.service ${D}${systemd_system_unitdir}/

}

SYSTEMD_SERVICE:${PN} = "webserver-oob.service"
SYSTEMD_SERVICE:${PN}:append:am64xx = " rpmsg-json.service"

FILES:${PN} = " \
    ${bindir}/webserver-oob \
    ${bindir}/cpu_stats \
    ${bindir}/audio_utils \
    ${nonarch_libdir}/node_modules/${BPN} \
    ${datadir}/${BPN}/demos \
    ${systemd_system_unitdir}/webserver-oob.service \
    ${datadir}/${BPN}/app \
    ${sysconfdir}/webserver-oob.conf \
"

FILES:${PN}:append:am62pxx = " ${bindir}/speech_utils"
FILES:${PN}:append:am62xx = " ${bindir}/speech_utils"
FILES:${PN}:append:am62lxx = " ${bindir}/speech_utils"

FILES:${PN}:append:am64xx = " ${bindir}/rpmsg_json ${systemd_system_unitdir}/rpmsg-json.service"

PR = "r2"
