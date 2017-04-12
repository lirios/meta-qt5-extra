SUMMARY = "Support library to make Qt-based Wayland compositors development easier"
LICENSE = "LGPLv3 | GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "git://github.com/lirios/wayland.git;branch=${LIRI_GIT_BRANCH}"

PV = "0.9.0.1+git${SRCPV}"
SRCREV = "1a9c1fd63a46e265f271b54c6042609d58826d3f"
S = "${WORKDIR}/git"

QBS_PROJECT = "${S}/wayland.qbs"

DEPENDS += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb-util-cursor libxcursor', '', d)} \
    wayland \
    qtwayland \
    qtwayland-native \
    glib-2.0 \
    virtual/egl \
    libinput \
"

# egl packageconfig factored out to make it easy for BSP layers to pick what
# machines need
PACKAGECONFIG_EGL ?= "kms"

PACKAGECONFIG ??= " \
    ${PACKAGECONFIG_EGL} \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", "xwayland", "",d)} \
"
PACKAGECONFIG[kms] = "qbsbuildconfig:withKmsPlugin=true,qbsbuildconfig:withKmsPlugin=false,drm,libgbm"
PACKAGECONFIG[brcm] = "qbsbuildconfig:withBrcmPlugin=true,,"
PACKAGECONFIG[xwayland] = "qbsbuildconfig:withXWayland=true,qbsbuildconfig:withXWayland=false,libxcb xserver-xorg,xserver-xorg-xwayland"
