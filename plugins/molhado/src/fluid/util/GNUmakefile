# $Header: /cvs/fluid/fluid/code/fluid/util/GNUmakefile,v 1.41 2002/12/22 05:23:58 boyland Exp $
# Makefile for ./code/fluid/util
HERE=${FLUIDTOP}/code/fluid/util
SUBDIRS= 
JAVASRC= 	AbstractCachedSet.java \
	        AbstractRemovelessIterator.java \
		AppendEnumeration.java \
		ArrayLattice.java \
		AssocList.java \
		Base64InputStream.java \
		Base64OutputStream.java \
		Base64StreamCorruptedException.java \
		BitsLattice.java \
		BooleanLattice.java \
		CachedSet.java \
		ChainLattice.java \
		EmptyEnumeration.java \
		EmptyIterator.java \
		EnumerationIterator.java \
		FilterIterator.java \
		FilterEnumeration.java \
		FileLocator.java \
		FlatLattice.java \
		Hashtable2.java \
		ImmutableSet.java \
		ImmutableHashOrderSet.java \
		IntegerTable.java \
		IntersectionLattice.java \
		Lattice.java \
		Pair.java \
		PairEnumeration.java \
		PathFileLocator.java \
		ProcessIterator.java \
		Profiler.java \
		Queue.java \
		RecordLattice.java \
		RemovelessIterator.java \
		SimpleEnumeration.java \
		SimpleIterator.java \
		SimpleRemovelessIterator.java \
		SingletonEnumeration.java \
		SingletonIterator.java \
		SetCache.java \
		SetException.java \
		SetLattice.java \
		Stack.java \
		StringFilter.java \
		SuffixFileFilter.java \
		TestSet.java \
		ThreadGlobal.java \
		Trace.java \
		Triple.java \
		UnionLattice.java \
		UniqueID.java \
		ZipFileLocator.java

#		FilterEnumeration.java \

HTML=		package.html

OBSOLETE=	ASCII_CharStream.* \
		InfiniteSetException.* \
		ParseError.* \
		Token.* \
        Set.* \
        doc.html

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all test

all : ${JAVAOBJ}

testpath : 
	echo ${CLASSPATH}

INTEGERS= 0 9 8 7 6 5 4 3 2 1 10 11 12 13 7 14 15 8 16 17 2 0 0 \
	  18 7 11 5 4 10 20 0 0 0 3 18 21 21 7 2 4 1 8 13 22

test : ${JAVAOBJ} testset testbase64
	${JAVA} fluid.util.TestHashtable2 
	${JAVA} fluid.util.TestIntegerTable ${INTEGERS}

testset : ${JAVAOBJ}
	${JAVA} fluid.util.TestSet 1 Set 
	${JAVA} fluid.util.TestSet 1 CachedSet 
	${JAVA} fluid.util.TestSet 1 UnionLattice 
	${JAVA} fluid.util.TestSet 1 IntersectionLattice 

testbase64 : ${JAVAOBJ}
	${JAVA} fluid.util.TestBase64 --encode < TestBase64.class | \
	${JAVA} fluid.util.TestBase64 --decode | cmp - TestBase64.class
