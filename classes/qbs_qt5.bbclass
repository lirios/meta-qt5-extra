inherit qmake5_paths qbs

DEPENDS_prepend += "qtbase "

# Qbs
OE_QBS_QTCONF_PATH = "${WORKDIR}/qt.conf"
OE_QBS_QT_PROFILE = "oeqt"
OE_QBS_PROFILE ?= "${OE_QBS_QT_PROFILE}"

qbs_qt5_do_setup_qt() {
    cat > ${OE_QBS_QTCONF_PATH} <<EOF
[Paths]
Prefix = ${OE_QMAKE_PATH_PREFIX}
Headers = ${OE_QMAKE_PATH_HEADERS}
Libraries = ${OE_QMAKE_PATH_LIBS}
ArchData = ${OE_QMAKE_PATH_ARCHDATA}
Data = ${OE_QMAKE_PATH_DATA}
Binaries = ${OE_QMAKE_PATH_BINS}
LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}
Plugins = ${OE_QMAKE_PATH_PLUGINS}
Imports = ${OE_QMAKE_PATH_IMPORTS}
Qml2Imports = ${OE_QMAKE_PATH_QML}
Translations = ${OE_QMAKE_PATH_TRANSLATIONS}
Documentation = ${OE_QMAKE_PATH_DOCS}
Settings = ${OE_QMAKE_PATH_SETTINGS}
Examples = ${OE_QMAKE_PATH_EXAMPLES}
Tests = ${OE_QMAKE_PATH_TESTS}
HostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
HostData = ${OE_QMAKE_PATH_HOST_DATA}
HostLibraries = ${OE_QMAKE_PATH_HOST_LIBS}
HostSpec = ${OE_QMAKE_PLATFORM_NATIVE}
TargetSpec = ${OE_QMAKE_PLATFORM}
ExternalHostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
Sysroot = ${STAGING_DIR_TARGET}
[EffectivePaths]
HostPrefix = ${STAGING_DIR_NATIVE}${prefix_native}
EOF

    cat > ${WORKDIR}/qmake <<EOF
#!/bin/sh
exec ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/qmake \$* -qtconf ${OE_QBS_QTCONF_PATH}
EOF
    chmod 755 ${WORKDIR}/qmake

    ${OE_QBS_QBS} setup-qt --settings-dir ${WORKDIR}/qbs ${WORKDIR}/qmake ${OE_QBS_QT_PROFILE}
}

addtask setup_qt after do_configure before do_set_base_profile

qbs_qt5_do_set_base_profile() {
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_QT_PROFILE}.baseProfile ${OE_QBS_TOOLCHAIN_PROFILE}
}

addtask set_base_profile after do_setup_qt before do_list_profiles

EXPORT_FUNCTIONS do_setup_qt do_set_base_profile
