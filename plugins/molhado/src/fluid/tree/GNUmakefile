# $Header: /cvs/fluid/fluid/code/fluid/tree/GNUmakefile,v 1.31 2001/11/06 01:37:04 chance Exp $
# Makefile for ./code/fluid/tree
HERE=${FLUIDTOP}/code/fluid/tree
SUBDIRS= 
# DepthFirstSearch must precede ConnectedNodes
JAVASRC=	ChangedChildEdgeEvent.java \
		ChangedChildEvent.java \
		ChangedParentEdgeEvent.java \
		ChangedParentEvent.java \
		ChangedSinkEvent.java \
		ChangedSourceEvent.java \
		ChildEdgeEvent.java \
		ChildEvent.java \
		DepthFirstSearch.java ConnectedNodes.java \
		Digraph.java \
		DigraphEvent.java \
		DigraphException.java \
		DigraphInterface.java \
		DigraphListener.java \
		DigraphMixin.java \
		EdgeDigraph.java \
		EdgeDigraphConnections.java \
		EdgeDigraphException.java \
		EdgeDigraphInterface.java \
		EdgeEvent.java \
		EdgeNodeEvent.java \
		GraphLabel.java \
		IllegalChildException.java \
		IROperatorType.java \
		MutableDigraphInterface.java \
		MutableEdgeDigraphInterface.java \
		MutableSymmetricDigraphInterface.java \
		MutableSymmetricEdgeDigraphInterface.java \
		MutableTreeInterface.java \
		NewChildEdgeEvent.java \
		NewChildEvent.java \
		NewEdgeEvent.java \
		NewNodeEvent.java \
		NewParentEdgeEvent.java \
		NewParentEvent.java \
		NewSinkEvent.java \
		NewSourceEvent.java \
		NodeEdgeEvent.java \
		NodeEvent.java \
		Operator.java \
		OperatorType.java \
		ParentEdgeEvent.java \
		ParentEvent.java \
		RemoveChildEdgeEvent.java \
		RemoveChildEvent.java \
		RemoveChildrenEvent.java \
		RemoveParentEdgeEvent.java \
		RemoveParentEvent.java \
		RemoveParentsEvent.java \
		SinkEvent.java \
		SourceEvent.java \
		StructureException.java \
		SymmetricDigraph.java \
		SymmetricDigraphInterface.java \
		SymmetricEdgeDigraph.java \
		SymmetricEdgeDigraphInterface.java \
		SyntaxTree.java \
		SyntaxTreeInterface.java \
		TestDigraph.java \
		TestSymmetricDigraph.java \
		TestTree.java \
		TestEdgeDigraph.java \
		TestSymmetricEdgeDigraph.java \
		Tree.java \
		TreeException.java \
		TreeInterface.java 

HTML=		package.html

JAVAC_FLAGS=-g -O

OBSOLETE= 	SimpleNode.java TreeNode.java \
		Associativity.* \
		BpKind.* \
		BpToken.* \
		Breakpoint.* \
		Displayer.* \
		FormatItem.* \
		FormatToken.* \
		Glue.* \
		Precedence.* \
		Token.* \
		TokenConstants.* \
		TokenStream.* \
		Unparser.* \
		JJNode.* JJOperator.* Node.* \
		DisplayTree.* \
		AttributeUnknownException.* \
        doc.html

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

.PHONY: test
test :  TestDigraph.run TestSymmetricDigraph.run \
	TestEdgeDigraph.run TestSymmetricEdgeDigraph.run \
	TestTree.run
