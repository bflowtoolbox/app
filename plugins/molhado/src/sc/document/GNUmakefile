# $Header: /cvs/fluid/fluid/code/sc/document/GNUmakefile,v 1.14 2003/03/13 01:04:24 boyland Exp $

# Makefile for ./code/sc/document
HERE=${FLUIDTOP}/code/sc/document

SUBDIRS= 

JAVASRC=	\
                SCDocumentException.java \
		SCDocumentNodeAttributeUnknownException.java \
		SCDocumentParseException.java \

HTML=		package.html

OBSOLETE=	\
		SCDocumentLeafNode.java \


include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

