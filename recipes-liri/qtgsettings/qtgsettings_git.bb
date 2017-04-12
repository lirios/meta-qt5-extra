SUMMARY = "Qt-style API to use GSettings"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri

QTGSETTINGS_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/qtgsettings.git;branch=${QTGSETTINGS_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "e578c73c9c021ee10890e442ab216e438bbd0730"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtdeclarative \
    glib-2.0 \
"
