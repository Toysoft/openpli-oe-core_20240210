DESCRIPTION = "Automatic full backup and manual flashing image"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=f521231b9317995c51bdc211746b2802"

PLUGINNAME = "automatic-full-backup"
PLUGIN_PATH = "Extensions/FullBackup"

require dima-plugins.inc

RDEPENDS:${PN} = " \
	mtd-utils-ubifs \
	mtd-utils \
	ofgwrite \
	zip \
	"
