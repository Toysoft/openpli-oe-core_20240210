PLUGIN_BRANCH ?= "python3"

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-${MODULE}.git;protocol=https;branch=${PLUGIN_BRANCH}"

S="${WORKDIR}/git"

inherit pkgconfig python3-compileall

FILES:${PN}-dbg += "\
    ${libdir}/enigma2/python/Plugins/*/*/.debug \
    ${libdir}/enigma2/python/Plugins/*/*/*/.debug \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/.debug \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/*/.debug \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/*/*/.debug \
    "

FILES:${PN}-src += "\
    ${libdir}/enigma2/python/*/*.py \
    ${libdir}/enigma2/python/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/*/*/*/*/*/*/*/*/*/*/*.py \
    "

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
