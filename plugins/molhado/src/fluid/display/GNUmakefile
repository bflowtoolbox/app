# $Header: /cvs/fluid/fluid/code/fluid/display/GNUmakefile,v 1.10 1999/02/05 13:43:06 aarong Exp $
# Makefile for relative path from shadow directory
HERE= ${FLUIDTOP}/code/fluid/display

SUBDIRS= examples
JAVASRC=	TextBean.java \
		TextClickedEvent.java \
		TextClickedListener.java \
		TextCoord.java \
		TextRegion.java \
		TextResizedEvent.java \
		TextResizedListener.java \
        TextResizedAdapter.java \
		TextStyle.java \
		InsertableGraphic.java \
		InsertableCircle.java \
		RightIBeam.java \
        LeftIBeam.java

OBSOLETE=	AttributeNotSetException* \
            IBeam.*

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

