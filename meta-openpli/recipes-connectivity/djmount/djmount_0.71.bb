DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPL-2.0-or-later"
DEPENDS = "libupnp fuse"
RDEPENDS:${PN} = "libupnp fuse"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

INITSCRIPT_NAME = "djmount"
INITSCRIPT_PARAMS = "defaults"

inherit autotools update-rc.d pkgconfig gettext

# libupnp make doesn't support it
PARALLEL_MAKE = ""

SRC_URI = "git://github.com/amiri82/djmount.git;protocol=https;branch=main"


SRC_URI:append =" \
	file://init \
	file://02-djmount.1.patch \
	file://03-support-fstab-mounting.patch \
	"

S = "${WORKDIR}/git"

do_configure:prepend() {
	mkdir ${S}/libupnp/config.aux
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}
do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/djmount
}
