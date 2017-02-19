# $Header: /cvs/fluid/fluid/code/fluid/java/template/GNUmakefile,v 1.12 1999/07/14 14:26:30 aarong Exp $
# Makefile for ./code/fluid/java/template
HERE=${FLUIDTOP}/code/fluid/java/template
SUBDIRS= 
JAVASRC=    ConflictResult.java \
            ExampleTemplate.java \
            IdentifierConsultant.java \
            JavaNodeField.java \
            JavaNodeVectorField.java \
            JavaNodeStrategy.java \
            ReorderEvent.java \
            SimpleTemplate.java \
            TemplateConflictResults.java \
            TupleTemplate.java

OBSOLETE=	doc.html StdTemplate.java Expr1Template.java

HTML=		package.html

JAVAC_FLAGS= 	-g

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

all : ${JAVAOBJ}
