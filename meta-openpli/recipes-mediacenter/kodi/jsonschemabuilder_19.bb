SUMMARY = "Kodi json schema builder"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

SRCREV = "dc2adb742477bc95b0211be4ab4c2afdf3428b3c"

PV = "19.3+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Matrix"

inherit autotools-brokensep gettext native

S = "${WORKDIR}/git/tools/depends/native/JsonSchemaBuilder/src"

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

BBCLASSEXTEND = "native"