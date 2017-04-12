SUMMARY = "A collection of cross-platform QtQuick components for building fluid and dynamic applications"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = " \
    file://LICENSE.MPL2;md5=815ca599c9df247a0c7f619bab123dad \
"

inherit liri

FLUID_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/fluid.git;branch=${FLUID_GIT_BRANCH}"

PV = "0.10.0+git${SRCPV}"
SRCREV = "5e7f3c59cbe3b6339e3225b533dd11bd61368a8e"
S = "${WORKDIR}/git"

# TODO: Add ttf-roboto
DEPENDS += " \
    qtgraphicaleffects \
    qtquickcontrols2 \
    qtsvg \
"

QBS_BUILD_PROPERTIES = "qbsbuildconfig.withDocumentation:false"
QBS_INSTALL_PROPERTIES = "${QBS_BUILD_PROPERTIES}"
