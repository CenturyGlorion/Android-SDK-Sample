include ../../../3rdparty/build/makeconfig/make-rules.mk

all: arm x86
	ant

release:
	ant

debug:
	ant

clean:
	ant clean

arm:: check-env copy-arm
	cd $(SDK_DIR) && ant build-project -e

x86:: check-env copy-x86
	cd $(SDK_DIR) && ant build-project -e

install: copy-arm copy-x86 install-jar
