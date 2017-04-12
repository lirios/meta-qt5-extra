SUMMARY = "Qt-style API to use freedesktop.org's AccountsService DBus service"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri

QTACCOUNTSSERVICE_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/qtaccountsservice.git;branch=${QTACCOUNTSSERVICE_GIT_BRANCH}"

PV = "0.7.0+git${SRCPV}"
SRCREV = "992f8e0f1d0c47a14ac5fb2b28a1d418c387d502"
S = "${WORKDIR}/git"

DEPENDS += " \
    qtdeclarative \
"

#RDEPENDS = " \
#    accountsservice \
#"
