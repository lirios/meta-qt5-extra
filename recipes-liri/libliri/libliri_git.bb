SUMMARY = "A collection of core classes used throught Liri"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri

SRC_URI = "git://github.com/lirios/libliri.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "95d0834f579e1173d5fcc0dd2aa2b7791a5dab37"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtdeclarative \
    libqtxdg \
"

RREPLACES_${PN} = "libhawaii"
RPROVIDES_${PN} = "libhawaii"
RCONFLICTS_${PN} = "libhawaii"
