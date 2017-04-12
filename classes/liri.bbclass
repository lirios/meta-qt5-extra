inherit liribase qbs_qt5

LIRI_QMLDIR = "${@os.path.relpath(d.getVar('OE_QMAKE_PATH_QML', True), d.getVar('OE_QMAKE_PATH_PREFIX', True))}"
LIRI_PLUGINSDIR = "${@os.path.relpath(d.getVar('OE_QMAKE_PATH_PLUGINS', True), d.getVar('OE_QMAKE_PATH_PREFIX', True))}"

QBS_BUILD_PROPERTIES = "lirideployment.qmlDir:${LIRI_QMLDIR} lirideployment.pluginsDir:${LIRI_PLUGINSDIR}"

# modifications to normal packages
FILES_${PN} += " \
    ${libdir}/lib*${SOLIBS} \
    ${libexecdir} \
    ${OE_QMAKE_PATH_QML} \
    ${OE_QMAKE_PATH_PLUGINS} \
"
FILES_${PN}-dev += " \
    ${libdir}/lib*${SOLIBSDEV} \
    ${libdir}/pkgconfig \
    ${libdir}/cmake/* \
    ${datadir}/qbs/* \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
    ${libdir}/*.la \
    ${includedir}/* \
"
FILES_${PN}-dbg += " \
    ${libdir}/.debug \
    ${libexecdir}/.debug \
    ${OE_QMAKE_PATH_PLUGINS}/.debug \
"
FILES_${PN}-staticdev += " \
    ${libdir}/*.a \
"
