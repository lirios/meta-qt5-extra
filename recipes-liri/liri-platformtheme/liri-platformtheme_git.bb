SUMMARY = "Qt platform theme plugin for apps integration with Liri OS"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit liri gsettings

SRC_URI = "git://github.com/lirios/platformtheme.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "36f601aef59114be05513e5993e31b63e382586c"
S = "${WORKDIR}/git"

QBS_PROJECT = "${S}/platformtheme.qbs"

DEPENDS += " \
    qtquickcontrols2 \
    qtgsettings \
"
