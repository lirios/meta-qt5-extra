SUMMARY = "Liri Web browser"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=8f0e2cd40e05189ec81232da84bd6e1a \
"

inherit qmake5

LIRI_BROWSER_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/browser.git;branch=${LIRI_BROWSER_GIT_BRANCH}"

PV = "0.0.0+git${SRCPV}"
SRCREV = "4f27d0b1abb85ad0166482128570b158c3b446aa"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtdeclarative \
    qtquickcontrols2 \
    qtwebengine \
    fluid \
"

EXTRA_QMAKEVARS_PRE_append = " LIRI_INSTALL_PREFIX=/usr CONFIG+=use_qt_paths"

FILES_${PN} += " \
    ${OE_QMAKE_PATH_DATA}/icons/ \
    ${OE_QMAKE_PATH_DATA}/applications/ \
    ${OE_QMAKE_PATH_DATA}/appdata/ \
"
