# $Header: /cvs/fluid/fluid/code/sc/GNUmakefile,v 1.8 2001/11/14 00:13:51 boyland Exp $

# Makefile for ./code/sc
HERE=${FLUIDTOP}/code/sc

SUBDIRS= audios images document presentation ui testsuite

JAVASRC=	\
		SCError.java \
                SCException.java \
                SCRuntimeException.java \


HTML=		package.html

OBSOLETE= demo

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

