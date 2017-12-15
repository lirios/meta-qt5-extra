require qbs.inc
require qt5-native.inc

LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=243b725d71bb5df4a1e5920b344b86ad \
    file://LICENSE.LGPLv3;md5=466a5dbc4996e12165deb9b21185e2ad \
    file://LGPL_EXCEPTION.txt;md5=f4748b0d1a72c5c8fb5dab2dd1f7fa46 \
"

DEPENDS += "qtbase-native qtscript-native"

S = "${WORKDIR}/${BPN}-src-${PV}"

EXTRA_QMAKEVARS_PRE = " QBS_INSTALL_PREFIX=${prefix_native}"

do_configure() {
    qmake5_base_do_configure
}

do_install() {
    oe_runmake install INSTALL_ROOT=${STAGING_DIR_NATIVE} QBS_INSTALL_PREFIX=${prefix_native}
}
