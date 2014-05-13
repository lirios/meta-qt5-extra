DESCRIPTION = "System preferences for the Hawaii desktop environment"
LICENSE = "GPLv2 LGPLv2.1"
LIC_FILES_CHKSUM = " \
	file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
	file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

inherit cmake_qt5

DEPENDS = "qtbase qtdeclarative qtquickcontrols polkit-qt-1 qtconfiguration libhawaii qtaccountsservice qttools-native extra-cmake-modules-native"

SRC_URI = "git://github.com/mauios/${BPN}.git;protocol=git;branch=dev"
SRCREV = "71e2f960ad254e065c2c10fe034443ca34bb9bd4"
PV = "0.2.0.git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DQt5LinguistTools_DIR=${STAGING_LIBDIR_NATIVE}/cmake/Qt5LinguistTools"

FILES_${PN} += "${libdir}/hawaii ${datadir}/hawaii"
FILES_${PN}-dbg += "${libdir}/hawaii/qml/Hawaii/SystemPreferences/Background/.debug"