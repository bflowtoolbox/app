# $Header: /cvs/fluid/fluid/code/fluid/history/GNUmakefile,v 1.7 2002/02/15 04:34:30 thallora Exp $
# Makefile for ./code/fluid/history
HERE=${FLUIDTOP}/code/fluid/history
SUBDIRS= 

OPSRC=		Record.op \
		SimpleRecord.op \
		NoRecord.op

JAVASRC=	\
		HistoryNode.java \
		HistoryOperator.java \
		HistoryUnparser.java \
		HistoryUnparseStyle.java \
		TemplateHistoryEvent.java \
		HistoryEvent.java \

SOURCE= ${OPSRC} header 

OBSOLETE= HistoryBroswer*.class

GENJAVASRC= ${OPSRC:.op=.java}

GENJAVAOBJ= ${OPSRC:.op=.class}

CLEANFILES= ${GENJAVASRC}

CREATEOPFLAGS=	-header=header \
		-nodetype=HistoryNode \
		-optype=HistoryOperator \
		-createmethod=create \

#		-unparse=true \
#		-unparsetype=HistoryUnparser 
# other options follow, like Info:String

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${GENJAVAOBJ} ${JAVAOBJ} 

doc : ${GENJAVASRC}

ant : ${GENJAVASRC}

${GENJAVAOBJ} : ${GENJAVASRC} 
	${JAVAC} ${GENJAVASRC}

${GENJAVASRC} : header
