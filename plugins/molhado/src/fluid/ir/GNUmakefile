# $Header: /cvs/fluid/fluid/code/fluid/ir/GNUmakefile,v 1.34 2003/01/31 23:32:45 boyland Exp $
# Makefile for ./code/fluid/ir
HERE=${FLUIDTOP}/code/fluid/ir
SUBDIRS= 
JAVASRC=	AbstractProxyNode.java \
		Bundle.java \
		CachedType.java \
		ConstantSlot.java \
		ConstantSlotFactory.java \
		ConstantSlotInfo.java \
		DefaultSlotStorage.java \
		DerivedSlotInfo.java \
		DefaultDescribe.java \
		EmptyIRSequence.java \
		EnumerationAlreadyDefinedException.java \
		HashedSlots.java \
		IndependentIRNode.java \
		InsertionPoint.java \
		IRArray.java \
		IRBooleanType.java \
		IRChunk.java \
		IRChunkDelta.java \
		IRCompound.java \
		IRCompoundType.java \
		IREnumeratedType.java \
		IRFloatType.java \
		IRInput.java \
		IRIntegerType.java \
		IRList.java \
		IRLocation.java \
		IRLocationType.java \
		IRLongType.java \
		IRNode.java \
		IRNodeType.java \
		IRObservable.java \
		IROutput.java \
		IRPersistent.java \
		IRPersistentKind.java \
		IRPersistentObserver.java \
		IRRecordType.java \
		IRRegion.java \
		IRSequence.java \
		IRSequenceEnumeration.java \
		IRSequenceException.java \
		IRSequenceType.java \
		IRSequenceWrapper.java \
		IRStringType.java \
		IRType.java \
		IRTypeType.java \
		IRUnitType.java \
		InfoStoredSlotInfo.java \
		ListSlots.java \
		OwnerUndefinedException.java \
		PersistentSlotInfo.java \
		PlainIRNode.java \
		PredefinedSimpleSlot.java \
		PredefinedSlot.java \
		ProxyNode.java \
		SimpleSlot.java \
		SimpleSlotFactory.java \
		SimpleSlotInfo.java \
		Slot.java \
		SlotAlreadyAllocated.java \
		SlotAlreadyRegisteredException.java \
		SlotFactory.java \
		SlotImmutableException.java \
		SlotInfo.java \
		SlotInfoChangedEvent.java \
		SlotInfoDefinedEvent.java \
		SlotInfoEvent.java \
		SlotInfoListener.java \
		SlotInfoSlot.java \
		SlotInfoWrapper.java \
		SlotNotAllocated.java \
		SlotNotRegisteredException.java \
		SlotUndefinedException.java \
		SlotUnknownException.java \
		SlotUnknownHandler.java \
		Slots.java \
		StoredSlotInfo.java \
		UndefinedConstantSlot.java \
		UndefinedSimpleSlot.java \
		UndefinedSlot.java

#		Persistent.java \

HTML=		package.html

OBSOLETE=	NodeStoredSlotInfo.* \
		IRStorage* doc.html IREnumerationType.*

JAVAC_FLAGS=-g -O

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

.PHONY: all
all : ${JAVAOBJ}

