SUMMARY = "Wallpapers for Liri OS"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.CCBYSAv4;md5=eacc0b19e3fb8dd12d2e110b24be0452 \
"

inherit liribase

SRC_URI = "git://github.com/lirios/wallpapers.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.10.0+git${SRCPV}"
SRCREV = "093de08bf9c68fc11484af1465cfbea5b49efe07"
S = "${WORKDIR}/git"

QBS_PROJECT = "${S}/wallpapers.qbs"

FILES_${PN} += "${datadir}"

RREPLACES_${PN} = "hawaii-wallpapers"
RPROVIDES_${PN} = "hawaii-wallpapers"
RCONFLICTS_${PN} = "hawaii-wallpapers"
