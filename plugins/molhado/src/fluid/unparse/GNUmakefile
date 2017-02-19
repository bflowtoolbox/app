# $Header: /cvs/fluid/fluid/code/fluid/unparse/GNUmakefile,v 1.9 1999/03/10 15:58:12 boyland Exp $
# Makefile for ./code/fluid/unparse
HERE=${FLUIDTOP}/code/fluid/unparse
SUBDIRS= 
JAVASRC=	Breakpoint.java \
		Combined.java \
		Delim.java \
		FmtStream.java \
		Glue.java \
		Identifier.java \
		IndepBP.java \
		Keyword.java \
		MandatoryBP.java \
		NoBP.java \
		NoToken.java \
		OpenClose.java \
		Position.java \
		SimpleTokenStream.java \
		Token.java \
		TokenArray.java \
		TokenStream.java \
		TokenView.java \
		UnitedBP.java \
		UnparseView.java

#		Render.java \

# NOT DOING: Expr, Position, FixOp, AstLoc

OBSOLETE = 	AstLoc.* \
		BeanView* \
		Main.*

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

