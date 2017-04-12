SUMMARY = "A collection of core classes used throught Liri"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri distro_features_check

REQUIRED_DISTRO_FEATURES = "pulseaudio"

VIBE_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/vibe.git;branch=${VIBE_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "41b0908d7376860f90b09204d08c89ff0fee9adc"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtdeclarative \
    solid \
    kwallet \
    networkmanager-qt \
    modemmanager-qt \
    networkmanager \
    modemmanager \
    pulseaudio \
    fluid \
    libliri \
"

RDEPENDS_${PN} += "pulseaudio-server"
