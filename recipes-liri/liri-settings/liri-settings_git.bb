SUMMARY = "Settings application for Liri OS"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

inherit liri distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "git://github.com/lirios/settings.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "70946a3d57801d51e41d19cfe48afc194d3969f3"
S = "${WORKDIR}/git"

DEPENDS += " \
    liri-wayland \
    vibe \
    qtaccountsservice \
    polkit \
    xkeyboard-config \
"

FILES_${PN} += " \
    ${datadir}/liri/settings \
"

RREPLACES_${PN} = "hawaii-system-preferences"
RPROVIDES_${PN} = "hawaii-system-preferences"
RCONFLICTS_${PN} = "hawaii-system-preferences"
