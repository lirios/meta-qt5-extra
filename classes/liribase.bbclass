inherit qbs pkgconfig

LIRI_GIT_BRANCH ?= "develop"

# Always fetch submodules with common build configuration
do_configure_prepend() {
    (cd ${S} && git submodule update --init)
}

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-dbg = "1"

INSANE_SKIP_${PN} = "ldflags"
