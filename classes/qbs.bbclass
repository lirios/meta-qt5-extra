DEPENDS_prepend = "qbs-native "
B = "${WORKDIR}/build"

# Compiler flags
OE_QBS_CXX_COMPILER ?= "`echo ${CXX} | sed 's/^\([^ ]*\).*/\1/'`"
OE_QBS_C_FLAGS ?= "${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} ${CFLAGS}"
OE_QBS_CXX_FLAGS ?= "${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} ${CXXFLAGS}"
OE_QBS_LINKER_FLAGS ?= "${TOOLCHAIN_OPTIONS} ${LDFLAGS}"

# Qbs
OE_QBS_QBS = "${STAGING_BINDIR_NATIVE}/qbs"
OE_QBS_TOOLCHAIN_PROFILE = "oegcc"
OE_QBS_PROFILE ?= "${OE_QBS_TOOLCHAIN_PROFILE}"

QBS_BUILD_ARGUMENTS ?= "--command-echo-mode command-line"
QBS_BUILD_PROPERTIES ?= ""
QBS_INSTALL_ARGUMENTS ?= ""
QBS_INSTALL_PROPERTIES ?= ""

# Qbs wants properties stripped of trailing spaces and in a list, also
# optimization and visibility flags are already dealt by qbs
def clean_property(value):
    result = ''
    parts = value.strip().split(' ')
    for part in parts:
        if part == '':
            continue
        if part.startswith('-target') or part.startswith('-triple') or part.startswith('-arch') or part.startswith('-march'):
            continue
        if part.startswith('-O') or part.startswith('-g') or part.startswith('-pipe') or part.startswith('-fvisibility'):
            continue
        if part.startswith('-Wl'):
            continue
        if part.startswith('--sysroot'):
            continue
        if result != '':
            result += ', '
        result += '"' + part + '"'
    return '[' + result + ']'

# Return cpp.targetSystem from ${TARGET_OS}
def target_system(target_os):
    return target_os.split('-')[0]

# Return cpp.targetAbi from ${TARGET_OS}
def target_abi(target_os):
    return target_os.split('-')[1]

qbs_do_configure() {
    ${OE_QBS_QBS} setup-toolchains --settings-dir ${WORKDIR}/qbs --type gcc ${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${OE_QBS_CXX_COMPILER} ${OE_QBS_TOOLCHAIN_PROFILE}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.qbs.architecture ${TARGET_ARCH}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.qbs.sysroot ${STAGING_DIR_TARGET}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.useRPaths false
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.platformCFlags '${@clean_property("${OE_QBS_C_FLAGS}")}'
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.platformCxxFlags '${@clean_property("${OE_QBS_CXX_FLAGS}")}'
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.platformLinkerFlags '${@clean_property("${OE_QBS_LINKER_FLAGS}")}'
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.targetVendor ${TARGET_VENDOR}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.targetSystem ${@target_system(d.getVar('TARGET_OS', True))}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs profiles.${OE_QBS_TOOLCHAIN_PROFILE}.cpp.targetAbi ${@target_abi(d.getVar('TARGET_OS', True))}
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs preferences.qbsSearchPaths ${STAGING_DIR_TARGET}${datadir}/qbs
}

qbs_do_list_profiles() {
    ${OE_QBS_QBS} config --settings-dir ${WORKDIR}/qbs --list
}

addtask list_profiles after do_configure before do_compile

qbs_do_compile() {
    if [ -z "${QBS_PROJECT}" ]; then
        PROJECT="${BPN}.qbs"
    else
        PROJECT="${QBS_PROJECT}"
    fi

    bbnote "qbs using profile: ${OE_QBS_PROFILE}"
    bbnote "qbs using project: ${PROJECT}"
    bbnote "qbs build arguments: ${QBS_BUILD_ARGUMENTS}"
    bbnote "qbs build properties: ${QBS_BUILD_PROPERTIES}"

    cd ${S}
    rm -f ${B}/default/default.bg
    ${OE_QBS_QBS} build --settings-dir ${WORKDIR}/qbs --no-install -d ${B} ${PARALLEL_MAKE} -f ${PROJECT} ${QBS_BUILD_ARGUMENTS} profile:${OE_QBS_PROFILE} qbs.installRoot:${prefix} ${QBS_BUILD_PROPERTIES} ${PACKAGECONFIG_CONFARGS}
}

qbs_do_install() {
    bbnote "qbs using profile: ${OE_QBS_PROFILE}"
    bbnote "qbs install arguments: ${QBS_INSTALL_ARGUMENTS}"
    bbnote "qbs install properties: ${QBS_INSTALL_PROPERTIES}"

    cd ${S}
    ${OE_QBS_QBS} install --settings-dir ${WORKDIR}/qbs --no-build -d ${B} ${PARALLEL_MAKE} ${QBS_INSTALL_ARGUMENTS} --install-root ${D}${prefix} profile:${OE_QBS_PROFILE} ${QBS_INSTALL_PROPERTIES}
}

EXPORT_FUNCTIONS do_configure do_compile do_install do_list_profiles
