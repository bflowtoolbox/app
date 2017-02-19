# $Header: /cvs/fluid/fluid/code/fluid/version/GNUmakefile,v 1.33 2002/08/02 14:42:10 cvsuser Exp $
# Makefile for ./code/fluid/version
HERE=${FLUIDTOP}/code/fluid/version
SUBDIRS= 
JAVASRC=	CachedSlot.java \
		DependentVersionedSlot.java \
		DisconnectedEraException.java \
		Era.java \
		IndependentVersionedSlot.java \
		IRChunkVersionedStructure.java \
	        IRVersionType.java \
		ManyAssignedVersionedSlot.java \
		MemoedSlotInfo.java \
		NodeVersionPair.java \
		OnceAssignedVersionedSlot.java \
		OverlappingEraException.java \
		SlotUnknownEraException.java \
		SlotUnknownVersionException.java \
		TestPersistent.java \
		TestVersionedTree.java \
		TreeChanged.java \
		UnassignedVersionedSlot.java \
		Version.java \
		VersionCursor.java \
		VersionedChunk.java \
		VersionedDelta.java \
		VersionedEnumeration.java \
		VersionedRegion.java \
		VersionedRegionDelta.java \
		VersionedSequence.java \
                VersionedSequenceProjection.java \
		VersionedSlot.java \
		VersionedSlotFactory.java \
		VersionedSlotInfo.java \
		VersionedSnapshot.java \
		VersionedStructure.java \
		VersionedStructureFactory.java \
		VersionedStructureProxy.java \
		VersionedUnitSlotInfo.java \
		VersionedIterator.java \
		VersionMarker.java \
		VersionTest.java \
		VersionTracker.java \
	        VersionTrackerEvent.java \
	        VersionTrackerListener.java

HTML=		package.html

JAVAC_FLAGS=-g -O

OBSOLETE =	VersionedArray.* \
		SlotUnknownException.* \
		doc.html

CLEANFILES = test.ser test-serializable.tmp test-serializable.sh
CLEANDIRS = tmp

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

.PHONY: test test-persistent test-serializable
test : VersionTest.run TestVersionedTree.run
# The other tests still require too much interaction.

# The following line is used to add tests.  See test-persistent
TESTLINE= 'COMMANDS="$$COMMANDS $$K 0 0 $$ERA $$V $$K 0 1 $$ERA $$V $$K 0 2 $$ERA $$V $$K 1 0 $$ERA $$V $$K 1 1 $$ERA $$V $$K 1 2 $$ERA $$V"'
# This is used to load in slots on version shadow nodes:
TESTVCHUNK= 'COMMANDS="$$COMMANDS --vchunk 3 $$ERA"'
# Very little is changed in the last era
TESTSMALLERA= 'COMMANDS="$$COMMANDS $$K 1 2 $$ERA $$V --test all.tst"'

test-persistent : tmp/test
	@cat tmp/test
	-tmp/test | diff tmp/Store.out -

# Fiddle with the following lines to try other tests:

tmp/test : ${JAVAOBJ} GNUmakefile
	rm -rf tmp
	mkdir tmp
	@echo 'ERA=1' > tmp/test
	@echo 'K="--snapshot"' >> tmp/test
	@echo 'V=1' >> tmp/test
	@echo ${TESTLINE} >> tmp/test
	@echo 'K="--snapshot"' >> tmp/test
	@echo 'V=3' >> tmp/test
	@echo ${TESTLINE} >>tmp/test
	@echo 'K="--delta"' >> tmp/test
	@echo 'V=""' >> tmp/test
	@echo ${TESTLINE} >>tmp/test
	@echo ' ' >> tmp/test
# Uncommenting the following three lines will test
# whether we can load the initial VCD and thus
# have all fully initialized slots.
#	@echo 'ERA=0' >> tmp/test
#	@echo ${TESTLINE} >>tmp/test
#	@echo ' ' >> tmp/test
	@echo 'ERA=1' >> tmp/test
	@echo ${TESTVCHUNK} >> tmp/test
	@echo ' ' >> tmp/test
	@echo 'ERA=0' >> tmp/test
	@echo ${TESTVCHUNK} >> tmp/test
	@echo ' ' >> tmp/test
# Commenting the following lines will test
# whether we correctly have some unknown in third era.
	@echo 'ERA=2' >> tmp/test
	@echo ${TESTSMALLERA} >> tmp/test
	@echo ' ' >> tmp/test

	${MAKE} FLUIDINPUTS=${HERE}/tmp:${FLUIDINPUTS} RUNARGS="--store --trace" \
		TestPersistent.run > tmp/Store.out 
	grep "gmake RUN" tmp/Store.out | \
	 sed 's#gmake#gmake FLUIDINPUTS=${HERE}/tmp:${FLUIDINPUTS}#' >> tmp/test
	chmod 755 tmp/test

test-serializable : test.ser test-serializable.tmp
	grep RUNARGS test-serializable.tmp > test-serializable.sh
	chmod 755 test-serializable.sh
	-test-serializable.sh | diff test-serializable.tmp -

test.ser test-serializable.tmp : ${JAVAOBJ}
	${MAKE} RUNARGS="--write" TestPersistent.run > test-serializable.tmp
