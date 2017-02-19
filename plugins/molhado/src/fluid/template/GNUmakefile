# $Header: /cvs/fluid/fluid/code/fluid/template/GNUmakefile,v 1.13 2000/07/27 11:52:41 chance Exp $
# Makefile for ./code/fluid/template
HERE=${FLUIDTOP}/code/fluid/template
SUBDIRS= 

JAVASRC=    AbstractFieldConsultant.java \
	    AbstractScalarStrategy.java \
            AbstractVectorStrategy.java \
            BooleanField.java \
            BooleanStrategy.java \
            DefaultConsultant.java \
            FancyVectorStrategy.java \
            Field.java \
            FieldConsultant.java \
            FieldRole.java \
            FieldStrategy.java \
            IntegerField.java \
            IntegerStrategy.java \
            IRNodeField.java \
            IRNodeStrategy.java \
            IRNodeVectorField.java \
            JavaClassStrategy.java \
            NodeSet.java \
            PartialTemplate.java \
            ScalarField.java \
            SimpleVectorStrategy.java \
	    StrategizedField.java \
            StringField.java \
            StringStrategy.java \
            StringVectorField.java \
            Template.java \
            TemplateAdapter.java \
            TemplateEvent.java \
	    TemplateHandler.java \
            TemplateListener.java \
            TemplateSpawnedEvent.java \
            TemplateSpawnedListener.java \
            TemplateType.java \
            VectorField.java

SOURCE=		

OBSOLETE=   SimpleTemplate.* \
            ExampleTemplate.* \
            NodeVersionPair*

HTML=

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

