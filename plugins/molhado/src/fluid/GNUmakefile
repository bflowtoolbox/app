# $Header: /cvs/fluid/fluid/code/fluid/GNUmakefile,v 1.23 2003/03/14 14:12:24 boyland Exp $
# Makefile for ./code/fluid
HERE=${FLUIDTOP}/code/fluid
SUBDIRS= util display ir tree version view pattern unparse parse control \
	 template history ui command java demo

JAVASRC=	FluidError.java \
		FluidException.java \
		FluidRuntimeException.java \
		NotImplemented.java

HTML=		package.html

OBSOLETE=   doc.html sc

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

