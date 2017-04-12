SUMMARY = "Responsive shell for Liri OS"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit liri systemd pythonnative distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland pam"

SRC_URI = "git://github.com/lirios/shell.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.9.0+git${SRCPV}"
SRCREV = "e3e27bec06546cac71d2ec2d3f74757cc9478e7e"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtwayland-native \
    qtaccountsservice \
    libpam \
    libqtxdg \
    vibe \
    liri-wayland \
    liri-workspace \
"

QBS_PROJECT = "${S}/shell.qbs"

SYSTEMD_SERVICE_${PN} = "liri.service"

# for starter scripts
RDEPENDS_${PN} = "qttools-tools qtwayland-plugins"

# REVISIT optionals
RRECOMMENDS_${PN} += " \
    liri-wallpapers \
    paper-icon-theme \
    qtvirtualkeyboard-qmlplugins \
"

FILES_${PN} += " \
    ${systemd_unitdir} \
"

RREPLACES_${PN} = "hawaii-shell"
RPROVIDES_${PN} = "hawaii-shell"
RCONFLICTS_${PN} = "hawaii-shell"
