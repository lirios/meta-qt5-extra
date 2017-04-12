SUMMARY = "Software store"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=7702f203b58979ebbc31bfaeb44f219c \
"

inherit liri

REQUIRED_DISTRO_FEATURES = "flatpak"

LIRI_APPCENTER_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/appcenter.git;branch=${LIRI_APPCENTER_GIT_BRANCH}"

PV = "0.1.0+git${SRCPV}"
SRCREV = "97891bf07ca849bc2f5f76e352276d99c43fc92a"
S = "${WORKDIR}/git"

DEPENDS += " \
    fluid \
    libliri \
    flatpak \
    karchive \
"

FILES_${PN} += "${datadir}"
