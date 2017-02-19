# $Header: /cvs/fluid/fluid/code/GNUmakefile,v 1.26 2002/06/13 13:10:34 chance Exp $
HERE = ${FLUIDTOP}/code
SUBDIRS = sun fluid cspace filemon sc

JARROOT = ${FLUIDMASTER}/jars
JARDIRS = bsh de gnu org

OBSOLETE=   swingall.jar rt.jar Solaris-rt.jar


include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

all : 

fluid.jar:
	echo "Main-Class: fluid.java.main.Main" > fluid.manifest
	jar cvmf fluid.manifest fluid.jar ${SUBDIRS}
	rm -rf fluid.manifest
	jar -i fluid.jar

demo.jar:
	echo "Main-Class: fluid.java.examples.DemoSelf" > demo.manifest
	jar cvmf demo.manifest demo.jar ${SUBDIRS}
	rm -rf demo.manifest
	jar -i demo.jar

ntfsServer.jar:
	echo "Main-Class: filemon.RunServer" > ntfsServer.manifest
	jar cvmf ntfsServer.manifest ntfsServer.jar ${SUBDIRS}
	rm -rf ntfsServer.manifest
	mv ntfsServer.jar ${JARROOT}
	cd ${JARROOT}; jar uvf ntfsServer.jar ${JARDIRS}
	mv ${JARROOT}/ntfsServer.jar .

test.jar:
	cp -f ntfsServer.jar test.jar
	echo "Main-Class: fluid.java.examples.DemoSelf" > test.manifest
	jar umf test.manifest test.jar 
	rm -rf demo.manifest

myclean:
	rm -rf gnu de bsh

