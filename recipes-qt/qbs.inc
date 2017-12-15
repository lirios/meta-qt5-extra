DESCRIPTION = "Modern build tool for software projects"
HOMEPAGE = "https://wiki.qt.io/qbs"
SECTION = "console/utils"
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0) | GPL-2.0"

SRC_URI = " \
    https://download.qt.io/official_releases/${BPN}/${PV}/${BPN}-src-${PV}.tar.gz \
    file://0001-Allow-a-build-without-config-ui.patch \
    file://0002-Fix-build-with-OpenEmbedded.patch \
"
SRC_URI[md5sum] = "5c822783262a314f87247911be6b6811"
SRC_URI[sha256sum] = "ab82fb9f9fd72617b175b73ebadd3a3ac8a089af741edb777ead6dc9937394b0"

QMAKE_PROFILES = "${S}/qbs.pro"
EXTRA_QMAKEVARS_PRE += " QBS_INSTALL_PREFIX=${prefix}"
