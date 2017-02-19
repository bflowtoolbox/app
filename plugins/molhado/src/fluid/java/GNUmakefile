# $Header: /cvs/fluid/fluid/code/fluid/java/GNUmakefile,v 1.30 2002/09/13 17:00:10 chance Exp $
# Makefile for ./code/fluid/java
HERE=${FLUIDTOP}/code/fluid/java
SUBDIRS= 	view control operator promise parse util bind oldbind analysis template \
		history xform ui test main

JAVASRC=	DebugUnparser.java \
		DripOperator.java \
		JavaComponentFactory.java \
		JavaFmtStream.java \
		JavaGlobals.java \
		JavaNode.java \
		JavaOperator.java \
		JavaPredicates.java \
		JavaPromise.java \
		JavaPromiseOperator.java \
		JavaPromiseTreeEnumeration.java \
		JavaUnparser.java \
		JavaUnparseStyle.java

OBSOLETE=	Main.java Main.class \
	        PromiseChildrenEnumeration.class \
		PromiseTreeEnumeration.class \
		ViewJava.* DisplayJava.* JavaDTD.*\
		doc.html

HTML=		package.html
JAVAC_FLAGS= 	-g

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}
