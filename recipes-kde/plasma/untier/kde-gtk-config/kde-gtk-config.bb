SUMMARY = "GTK2 and GTK3 Configurator for KDE"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

REQUIRED_DISTRO_FEATURES = "x11"

inherit kde-plasma distro_features_check gettext

DEPENDS += " \
    gtk+ \
    gtk+3 \
    ki18n \
    kauth-native \
    kconfig-native \
    kconfigwidgets \
    kcoreaddons-native \
    knewstuff \
    karchive \
    kcmutils \
"

PV = "${PLASMA_VERSION}"
SRC_URI[md5sum] = "196dc596b29d8e483ef774cda8175728"
SRC_URI[sha256sum] = "860690cd19868af76ccc887f656c416f6985e4178302de01f8e26e8a058a32f9"

SRC_URI += "file://0001-fix-for-gtk2-only-and-gtk3-only-environments.patch"

FILES_${PN} += " \
    ${datadir}/kservices5 \
    ${datadir}/kcm-gtk-module \
    ${datadir}/icons \
    ${OE_QMAKE_PATH_PLUGINS} \
"

FILES_${PN}-dbg += " \
    ${OE_QMAKE_PATH_PLUGINS}/.debug \
"
