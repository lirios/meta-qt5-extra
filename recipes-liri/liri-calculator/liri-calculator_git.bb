SUMMARY = "Liri Calculator"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPLv3;md5=8f0e2cd40e05189ec81232da84bd6e1a \
"

inherit liri

LIRI_CALCULATOR_GIT_BRANCH ?= "develop"
SRC_URI = "git://github.com/lirios/calculator.git;branch=${LIRI_CALCULATOR_GIT_BRANCH}"

PV = "1.0.0+git${SRCPV}"
SRCREV = "a9c2393fb712b3fab8b7d5a9d27071b8f9c10363"
S = "${WORKDIR}/git"

DEPENDS += " \
    fluid \
"
