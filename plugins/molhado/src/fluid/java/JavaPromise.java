/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaPromise.java,v 1.1 2006/03/21 23:20:59 dig Exp $ */
package fluid.java;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fluid.FluidRuntimeException;
import fluid.ir.Bundle;
import fluid.ir.EmptyIRSequence;
import fluid.ir.IRBooleanType;
import fluid.ir.IRList;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IRPersistent;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceException;
import fluid.ir.IRSequenceType;
import fluid.ir.SlotImmutableException;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Combined;
import fluid.unparse.Glue;
import fluid.unparse.IndepBP;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
import fluid.unparse.UnitedBP;
import fluid.util.EmptyEnumeration;
import fluid.util.EnumerationIterator;
import fluid.util.ProcessIterator;
import fluid.util.UniqueID;

/** Class to hold methods working with promises on top of Java.
 * The package fluid.java.promises holds additional operators used.
 * 
 * Summary of promise representation
 */
public class JavaPromise extends JavaNode {
  public JavaPromise(Operator op) {
    super(op);
  }
  public JavaPromise(Operator op, IRNode[] children) {
    super(op, children);
  }

  public JavaPromise(SyntaxTreeInterface tree, Operator op) {
    super(tree, op);
  }
  public JavaPromise(
    SyntaxTreeInterface tree,
    Operator op,
    IRNode[] children) {
    super(tree, op, children);
  }
  
  public static final String UNIQUE            = "Unique";
  public static final String UNSHARED          = "Unshared";
  public static final String IMMUTABLE         = "Immutable";
  public static final String BORROWED          = "Borrowed";  
  public static final String SYNCHRONIZED      = "Synchronized";    
  public static final String SELF_PROTECTED    = "Self-protected";
  public static final String EXHAUSTIVE_THROWS = "Exhaustive throws";
  public static final String FIELD_REGION      = "Region mapping for fields";
  public static final String FIELD_AGGREGATION = "Region aggregation for fields";
  public static final String PRECONDITION      = "Pre-conditions";
  public static final String POSTCONDITION     = "Post-conditions";
  public static final String THROW_CONDITION   = "Throws condition";
  public static final String CLASS_INVARIANT   = "Class invariants";
  public static final String EFFECTS           = "Declared effects";
  public static final String CLASS_REGION      = "Declared regions";    
  public static final String LOCKS             = "Declared locks";
  public static final String POLICY_LOCKS      = "Declared policy locks";  
  public static final String RETURN_LOCK       = "Lock as return value";
  public static final String LOCK_PARAM        = "Lock as parameter";
  public static final String REQUIRED_LOCKS    = "Required locks";
  public static final String COLOR_DECL = "Color";
  public static final String COLOR_GRANT = "Grant";
  public static final String COLOR_REVOKE = "Revoke";
  public static final String COLOR_NOTE = "Note";
  
  public static final String[] promiseLabels = {
  	UNIQUE,
  	UNSHARED,
  	IMMUTABLE,
  	BORROWED,
    SYNCHRONIZED,
    SELF_PROTECTED,
  	EXHAUSTIVE_THROWS,
  	FIELD_REGION,
  	FIELD_AGGREGATION,
  	PRECONDITION,
  	POSTCONDITION,
  	THROW_CONDITION,
  	CLASS_INVARIANT,
  	EFFECTS,
  	CLASS_REGION,
  	LOCKS,
    POLICY_LOCKS,
  	RETURN_LOCK,
  	LOCK_PARAM,
      COLOR_DECL,
      COLOR_GRANT,
      COLOR_REVOKE,
      COLOR_NOTE,
  };  

  /*******************************************************************
   * Boolean SlotInfos for promises
   *******************************************************************/

  private static SlotInfo getBooleanVSI(String name) {
    return getVersionedSlotInfo(name, IRBooleanType.prototype, Boolean.FALSE);
  }

  private static boolean isX(SlotInfo si, IRNode n) {
    return ((Boolean) n.getSlotValue(si)).booleanValue();
  }

  /* parameter or return value is a unique reference. */

  public static final SlotInfo isUniqueSlotInfo =
    getBooleanVSI("JavaPromise.isUnique");

  public static boolean isUnique(IRNode node) {
    return ((Boolean) node.getSlotValue(isUniqueSlotInfo)).booleanValue();
  }
  public static void setIsUnique(IRNode node, boolean unique) {
    Boolean b = unique ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isUniqueSlotInfo, b);
  }

  /* field is unshared. */

  public static final SlotInfo isUnsharedSlotInfo =
    getBooleanVSI("JavaPromise.isUnshared");

  public static boolean isUnshared(IRNode node) {
    return ((Boolean) node.getSlotValue(isUnsharedSlotInfo)).booleanValue();
  }
  public static void setIsUnshared(IRNode node, boolean unshared) {
    Boolean b = unshared ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isUnsharedSlotInfo, b);
  }

  /* field, parameter, return value or class is immutable */

  public static final SlotInfo isImmutableSlotInfo =
    getBooleanVSI("JavaPromise.isImmutable");

  public static boolean isImmutable(IRNode node) {
    return ((Boolean) node.getSlotValue(isImmutableSlotInfo)).booleanValue();
  }
  public static void setIsImmutable(IRNode node, boolean immutable) {
    Boolean b = immutable ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isImmutableSlotInfo, b);
  }

  /* parameter will not be stored. */

  public static final SlotInfo isBorrowedSlotInfo =
    getBooleanVSI("JavaPromise.isBorrowed");

  public static boolean isBorrowed(IRNode node) {
    return ((Boolean) node.getSlotValue(isBorrowedSlotInfo)).booleanValue();
  }
  public static void setIsBorrowed(IRNode node, boolean borrowed) {
    Boolean b = borrowed ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isBorrowedSlotInfo, b);
  }

  public static final SlotInfo isSynchronizedSlotInfo =
    getBooleanVSI("JavaPromise.isSynchronized");

  public static boolean isSynchronized(IRNode node) {
    return ((Boolean) node.getSlotValue(isSynchronizedSlotInfo)).booleanValue();
  }
  public static void setIsSynchronized(IRNode node, boolean Synchronized) {
    Boolean b = Synchronized ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isSynchronizedSlotInfo, b);
  }  
  
  public static final SlotInfo isSelfProtectedSlotInfo =
    getBooleanVSI("JavaPromise.isSelfProtected");

  public static boolean isSelfProtected(IRNode node) {
    return ((Boolean) node.getSlotValue(isSelfProtectedSlotInfo)).booleanValue();
  }
  public static void setIsSelfProtected(IRNode node, boolean SelfProtected) {
    Boolean b = SelfProtected ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isSelfProtectedSlotInfo, b);
  }  

  /* whether the throw list for a method is exhuastive. */

  public static final SlotInfo hasExhaustiveThrowsSlotInfo =
    getBooleanVSI("JavaPromise.hasExhaustiveThrows");

  public static boolean hasExhaustiveThrows(IRNode node) {
    return ((Boolean) node.getSlotValue(hasExhaustiveThrowsSlotInfo))
      .booleanValue();
  }
  public static void setHasExhaustiveThrows(
    IRNode node,
    boolean exhaustiveThrows) {
    Boolean b = exhaustiveThrows ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(hasExhaustiveThrowsSlotInfo, b);
  }

  /* true for write, false for read effects */

  static final SlotInfo isWriteSlotInfo = getBooleanVSI("JavaPromise.isWrite");

  public static boolean isWrite(IRNode node) {
    return ((Boolean) node.getSlotValue(isWriteSlotInfo)).booleanValue();
  }
  public static boolean getIsWrite(IRNode node) { // needed by create-operator
    return isWrite(node);
  }

  public static void setIsWrite(IRNode node, boolean write) {
    Boolean b = write ? Boolean.TRUE : Boolean.FALSE;
    node.setSlotValue(isWriteSlotInfo, b);
  }

  private static final Token writeToken = new Keyword("modifies");
  private static final Token readToken = new Keyword("reads");

  public static void unparseIsWrite(IRNode node, JavaUnparser u) {
    (isWrite(node) ? writeToken : readToken).emit(u, node);
  }

  /*******************************************************************
   * IRNode SlotInfos for promises
   *******************************************************************/

  private static IRNode getXorNull(SlotInfo si, IRNode n) {
    if (n == null) {
      return null;
    }
    if (n.valueExists(si)) {
      return (IRNode) n.getSlotValue(si);
    }
    return null;
  }

  /* A back reference to the node the promise node is for. */

  static final SlotInfo promisedForSlotInfo =
    JavaPromise.getVersionedSlotInfo(
      "JavaPromise.promisedFor",
      IRNodeType.prototype,
      null);
  /** Return the node for which this promise applies.
   * (Something like the parent node).
   */
  public static IRNode getPromisedFor(IRNode promise) {
    return (IRNode) promise.getSlotValue(promisedForSlotInfo);
  }

  public static IRNode getPromisedForOrNull(IRNode promise) {
    return getXorNull(promisedForSlotInfo, promise);
  }

  private static void setPromisedFor(IRNode promise, IRNode decl) {
    promise.setSlotValue(promisedForSlotInfo, decl);
  }

  private static void attachPromiseNode(IRNode node, IRNode promise) {
    if (promise == null)
      return;
    if (!(tree.getOperator(promise) instanceof JavaPromiseOperator)) {
      throw new FluidRuntimeException("Promise node does not have a promise operator");
    }
    JavaNode.tree.removeSubtree(promise); // ensure a null parent
    setPromisedFor(promise, node);
  }
  private static void detachPromiseNode(IRNode node, IRNode promise) {
    if (promise != null)
      setPromisedFor(promise, null);
  }

  /* a special node to which to attach promises about the return value. */

  private static final SlotInfo returnNodeSlotInfo =
    getConstantSlotInfo("JavaPromise.returnNode", IRNodeType.prototype);

  public static IRNode getReturnNode(IRNode methodNode)
    throws SlotUndefinedException {
    return (IRNode) methodNode.getSlotValue(returnNodeSlotInfo);
  }
  public static IRNode getReturnNodeOrNull(IRNode methodNode) {
    return getXorNull(returnNodeSlotInfo, methodNode);
  }
  public static void setReturnNode(IRNode methodNode, IRNode returnNode)
    throws SlotImmutableException {
    methodNode.setSlotValue(returnNodeSlotInfo, returnNode);
    attachPromiseNode(methodNode, returnNode);
  }

  /* a special node to which to attach promises about the receiver. */

  private static final SlotInfo receiverNodeSlotInfo =
    getConstantSlotInfo("JavaPromise.receiverNode", IRNodeType.prototype);

  public static IRNode getReceiverNode(IRNode methodNode)
    throws SlotUndefinedException {
    return (IRNode) methodNode.getSlotValue(receiverNodeSlotInfo);
  }
  public static IRNode getReceiverNodeOrNull(IRNode methodNode) {
    return getXorNull(receiverNodeSlotInfo, methodNode);
  }
  public static void setReceiverNode(IRNode methodNode, IRNode receiverNode)
    throws SlotImmutableException {
    methodNode.setSlotValue(receiverNodeSlotInfo, receiverNode);
    attachPromiseNode(methodNode, receiverNode);
  }

  /* a special node to which to attach promises about the instance
   * initializer for a class.
   */

  private static final SlotInfo initMethodSlotInfo =
    getConstantSlotInfo("JavaPromise.initMethod", IRNodeType.prototype);

  public static IRNode getInitMethod(IRNode classNode)
    throws SlotUndefinedException {
    return (IRNode) classNode.getSlotValue(initMethodSlotInfo);
  }
  public static IRNode getInitMethodOrNull(IRNode classNode) {
    return getXorNull(initMethodSlotInfo, classNode);
  }
  public static void setInitMethod(IRNode classNode, IRNode initMethod)
    throws SlotImmutableException {
    classNode.setSlotValue(initMethodSlotInfo, initMethod);
    attachPromiseNode(classNode, initMethod);
  }

  /* a special node to which to attach promises about the class
   * initializer for a class.
   */

  private static final SlotInfo classInitMethodSlotInfo =
    getConstantSlotInfo("JavaPromise.classInitMethod", IRNodeType.prototype);

  public static IRNode getClassInitMethod(IRNode classNode)
    throws SlotUndefinedException {
    return (IRNode) classNode.getSlotValue(classInitMethodSlotInfo);
  }
  public static IRNode getClassInitOrNull(IRNode classNode) {
    return getXorNull(classInitMethodSlotInfo, classNode);
  }
  public static void setClassInitMethod(
    IRNode classNode,
    IRNode classInitMethod)
    throws SlotImmutableException {
    classNode.setSlotValue(classInitMethodSlotInfo, classInitMethod);
    attachPromiseNode(classNode, classInitMethod);
  }

  /* Method pre- and post- conditions */

  static final SlotInfo preconditionSlotInfo =
    getVersionedSlotInfo(
      "JavaPromise.precondition",
      IRNodeType.prototype,
      null);

  public static IRNode getPrecondition(IRNode methodNode) {
    return (IRNode) methodNode.getSlotValue(preconditionSlotInfo);
  }

  public static IRNode getPreconditionOrNull(IRNode methodNode) {
    return getXorNull(preconditionSlotInfo, methodNode);
  }

  public static void setPrecondition(IRNode methodNode, IRNode condition) {
    detachPromiseNode(methodNode, getPrecondition(methodNode));
    methodNode.setSlotValue(preconditionSlotInfo, condition);
    attachPromiseNode(methodNode, condition);
  }

  static final SlotInfo postconditionSlotInfo =
    getVersionedSlotInfo(
      "JavaPromise.postcondition",
      IRNodeType.prototype,
      null);

  public static IRNode getPostcondition(IRNode methodNode) {
    return (IRNode) methodNode.getSlotValue(postconditionSlotInfo);
  }

  public static IRNode getPostconditionOrNull(IRNode methodNode) {
    return getXorNull(postconditionSlotInfo, methodNode);
  }

  public static void setPostcondition(IRNode methodNode, IRNode condition) {
    detachPromiseNode(methodNode, getPostcondition(methodNode));
    methodNode.setSlotValue(postconditionSlotInfo, condition);
    attachPromiseNode(methodNode, condition);
  }

  /* Throw conditions (necessary condition to throw an exception) */

  static final SlotInfo throwConditionSlotInfo =
    getVersionedSlotInfo(
      "JavaPromise.throwCondition",
      IRNodeType.prototype,
      null);

  public static IRNode getThrowCondition(IRNode typeNode) {
    return (IRNode) typeNode.getSlotValue(throwConditionSlotInfo);
  }

  public static IRNode getThrowConditionOrNull(IRNode typeNode) {
    return getXorNull(throwConditionSlotInfo, typeNode);
  }

  public static void setThrowCondition(IRNode typeNode, IRNode condition) {
    detachPromiseNode(typeNode, getThrowCondition(typeNode));
    typeNode.setSlotValue(throwConditionSlotInfo, condition);
    attachPromiseNode(typeNode, condition);
  }

  static final SlotInfo fieldRegionSlotInfo =
    getVersionedSlotInfo("JavaPromise.fieldRegion", IRNodeType.prototype);

  public static IRNode getFieldRegion(IRNode fieldNode) {
    return (IRNode) fieldNode.getSlotValue(fieldRegionSlotInfo);
  }

  public static IRNode getFieldRegionOrNull(IRNode fieldNode) {
    return getXorNull(fieldRegionSlotInfo, fieldNode);
  }

  public static boolean fieldRegionExists(IRNode fieldNode) {
    return fieldNode.valueExists(fieldRegionSlotInfo);
  }

  public static void setFieldRegion(IRNode fieldNode, IRNode region) {
    if (fieldRegionExists(fieldNode)) {
      detachPromiseNode(fieldNode, getFieldRegion(fieldNode));
    }
    fieldNode.setSlotValue(fieldRegionSlotInfo, region);
    attachPromiseNode(fieldNode, region);
  }

  static final SlotInfo fieldAggregationSlotInfo =
    getVersionedSlotInfo("JavaPromise.fieldAggregation", IRNodeType.prototype);

  public static IRNode getFieldAggregation(IRNode fieldNode) {
    return (IRNode) fieldNode.getSlotValue(fieldAggregationSlotInfo);
  }

  public static IRNode getFieldAggregationOrNull(IRNode fieldNode) {
    return getXorNull(fieldAggregationSlotInfo, fieldNode);
  }

  public static boolean fieldAggregationExists(IRNode fieldNode) {
    return fieldNode.valueExists(fieldAggregationSlotInfo);
  }

  public static void setFieldAggregation(IRNode fieldNode, IRNode region) {
    if (fieldAggregationExists(fieldNode)) {
      detachPromiseNode(fieldNode, getFieldAggregation(fieldNode));
    }
    fieldNode.setSlotValue(fieldAggregationSlotInfo, region);
    attachPromiseNode(fieldNode, region);
  }

  /*******************************************************************
   * IRSequence(IRNode) SlotInfos for promises
   *******************************************************************/

  private static Enumeration getEnum(SlotInfo si, IRNode n) {
    if (n.valueExists(si)) {
      IRSequence s = (IRSequence) n.getSlotValue(si);
      return s.elements();
    }
    return EmptyEnumeration.prototype;
  }

  private static void addToEnum(SlotInfo si, IRNode n, IRNode elt) {
    IRSequence s;
    if (n.valueExists(si)) {
      s = (IRSequence) n.getSlotValue(si);
    } else {
      s = new IRList(treeSlotFactory);
      n.setSlotValue(si, s);
    }
    s.appendElement(elt);
    attachPromiseNode(n, elt);
    treeChanged.noteChange(n);
  }

  private static boolean removeFromEnum(SlotInfo si, IRNode n, IRNode elt) {
    if (!n.valueExists(si)) {
      return false;
    }
    final IRSequence s = (IRSequence) n.getSlotValue(si);
    try {
      IRLocation loc = s.firstLocation();
      while (true) {
        if (s.elementAt(loc).equals(elt)) {
          s.removeElementAt(loc);
          detachPromiseNode(n, elt);
          treeChanged.noteChange(n);
          return true;
        }
        loc = s.nextLocation(loc);
      }
    } catch (IRSequenceException e) {
      return false;
    }
  }

  /* Class regions */

  static final SlotInfo classRegionsSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.classRegions",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration classRegions(IRNode classNode) {
    return getEnum(classRegionsSlotInfo, classNode);
  }

  /** Add a region declaration node to the list of regions for this class
   * declaration node.  It does not check to see this region declaration
   * node is already in the list.
   */
  public static void addClassRegion(IRNode classNode, IRNode regionNode) {
    addToEnum(classRegionsSlotInfo, classNode, regionNode);
  }

  /** Remove a region declaration node from the list of regions for
   * this class declaration node.  It returns true if the region node
   * was found (and removed).
   */
  public static boolean removeClassRegion(
    IRNode classNode,
    IRNode regionNode) {
    return removeFromEnum(classRegionsSlotInfo, classNode, regionNode);
  }

  /* Color Declarations */
  static final SlotInfo colorDeclSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.colorDecl",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration declaredColors(IRNode classNode) {
    return getEnum(colorDeclSlotInfo, classNode);
  }

  /** Add a Color declaration node to the list of Colors for this Color
     * declaration node.  It does not check to see this Color declaration
     * node is already in the list.
     */
  public static void addColorToDecl(IRNode declNode, IRNode colorNode) {
    addToEnum(colorDeclSlotInfo, declNode, colorNode);
  }

  /** Remove a color declaration node from the list of colors for
   * this class declaration node.  It returns true if the color node
   * was found (and removed).
   */
  public static boolean removeColorFromDecl(IRNode classNode, IRNode colorNode) {
    return removeFromEnum(colorDeclSlotInfo, classNode, colorNode);
  }

  /* Color Revokes */
  static final SlotInfo colorRevokeSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.colorRevoke",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration revokedColors(IRNode classNode) {
    return getEnum(colorRevokeSlotInfo, classNode);
  }

  /** Add a Color node to the list of Colors for this Color
     * revoke node.  It does not check to see this Color
     * node is already in the list.
     */
  public static void addColorToRevoke(IRNode revokeNode, IRNode colorNode) {
    addToEnum(colorRevokeSlotInfo, revokeNode, colorNode);
  }

  /** Remove a color node from the list of colors for
   * this color revoke node.  It returns true if the color node
   * was found (and removed).
   */
  public static boolean removeColorFromRevoke(IRNode revokeNode, IRNode colorNode) {
    return removeFromEnum(colorRevokeSlotInfo, revokeNode, colorNode);
  }


  /* Color Grants */
  static final SlotInfo colorGrantSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.colorGrant",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration grantedColors(IRNode classNode) {
    return getEnum(colorGrantSlotInfo, classNode);
  }

  /** Add a Color node to the list of Colors for this Color
     * grant node.  It does not check to see this Color
     * node is already in the list.
     */
  public static void addColorToGrant(IRNode grantNode, IRNode colorNode) {
    addToEnum(colorGrantSlotInfo, grantNode, colorNode);
  }

  /** Remove a color node from the list of colors for
   * this color grant node.  It returns true if the color node
   * was found (and removed).
   */
  public static boolean removeColorFromGrant(IRNode grantNode, IRNode colorNode) {
    return removeFromEnum(colorGrantSlotInfo, grantNode, colorNode);
  }

  /* Color Notes */
  static final SlotInfo colorNoteSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.colorNote",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration notedColors(IRNode classNode) {
    return getEnum(colorNoteSlotInfo, classNode);
  }

  /** Add a Color node to the list of Colors for this Color
     * note node.  It does not check to see this Color
     * node is already in the list.
     */
  public static void addColorToNote(IRNode noteNode, IRNode colorNode) {
    addToEnum(colorNoteSlotInfo, noteNode, colorNode);
  }

  /** Remove a color node from the list of colors for
   * this color note node.  It returns true if the color node
   * was found (and removed).
   */
  public static boolean removeColorFromNote(IRNode noteNode, IRNode colorNode) {
    return removeFromEnum(colorNoteSlotInfo, noteNode, colorNode);
  }

  /* Class invariants */

  static final SlotInfo classInvariantsSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.classInvariants",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration classInvariants(IRNode classNode) {
    return getEnum(classInvariantsSlotInfo, classNode);
  }

  /** Add a invariant declaration node to the list of invariants for this class
   * declaration node.  It does not check to see this invariant declaration
   * node is already in the list.
   */
  public static void addClassInvariant(
    IRNode classNode,
    IRNode invariantNode) {
    addToEnum(classInvariantsSlotInfo, classNode, invariantNode);
  }

  /** Remove a invariant declaration node from the list of invariants for
   * this class declaration node.  It returns true if the invariant node
   * was found (and removed).
   */
  public static boolean removeClassInvariant(
    IRNode classNode,
    IRNode invariantNode) {
    return removeFromEnum(classInvariantsSlotInfo, classNode, invariantNode);
  }

  /* Method effects.
   * 
   * NOTE this is different from the other sequences
   * 
   * The slot stores a sequence. If that sequence is empty,
   * then nothing is promised for the method.
   * Otherwise, the first element is null and the rest of
   * the elements are effects nodes.
   */
  static final SlotInfo methodEffectsSlotInfo =
    getVersionedSlotInfo(
      "JavaPromise.methodEffects",
      new IRSequenceType(IRNodeType.prototype),
      EmptyIRSequence.prototype);

  /** Enumerate the effects of a method.
   * @return null if no effects promised for method.
   */
  public static Enumeration methodEffects(IRNode methodNode) {
    Enumeration e = getEnum(methodEffectsSlotInfo, methodNode);
    if (e.hasMoreElements()) {
      e.nextElement();
      return e;
    } else {
      return null;
    }
  }

  private static IRSequence createEmptyEffectsSequence() {
    IRSequence s = new IRList(treeSlotFactory);
    s.appendElement(null);
    return s;
  }

  /** Set the effects of a method.
   * @param effects an enumeration of EffectSpecification IR nodes.
   *        if null then no promises are made concerning the method.
   */
  public static void setMethodEffects(IRNode methodNode, Enumeration effects) {
    /* First we remove the old effects.
     * (We do this first since some of these effects may be in the
     * enumeration we're about to add.)
     */
    Enumeration old = methodEffects(methodNode);
    if (old != null) {
      while (old.hasMoreElements()) {
        IRNode effectNode = (IRNode) old.nextElement();
        detachPromiseNode(methodNode, effectNode);
      }
    }
    IRSequence s;
    if (effects == null) {
      s = EmptyIRSequence.prototype;
    } else {
      s = createEmptyEffectsSequence();

      while (effects.hasMoreElements()) {
        IRNode effect = (IRNode) effects.nextElement();
        attachPromiseNode(methodNode, effect);
        s.appendElement(effect);
      }
    }
    methodNode.setSlotValue(methodEffectsSlotInfo, s);
    treeChanged.noteChange(methodNode);
  }

  /** Add a effect declaration node to the list of effects for this method
   * declaration node.  It does not check to see this effect declaration
   * node is already in the list.
   */
  public static void addMethodEffect(IRNode methodNode, IRNode effectNode) {
    if (effectNode == null) {
      return;
    }
    Object o = methodNode.getSlotValue(methodEffectsSlotInfo);
    if (o == EmptyIRSequence.prototype) {
      methodNode.setSlotValue(
        methodEffectsSlotInfo,
        createEmptyEffectsSequence());
    }
    addToEnum(methodEffectsSlotInfo, methodNode, effectNode);
  }

  /** Remove a effect declaration node from the list of effects for
   * this method declaration node.  It returns true if the effect node
   * was found (and removed).
   */
  public static boolean removeMethodEffect(
    IRNode methodNode,
    IRNode effectNode) {
    if (effectNode == null) {
      return false;
    }
    return removeFromEnum(methodEffectsSlotInfo, methodNode, effectNode);
  }

  /* Lock declarations */

  static final SlotInfo lockDeclarationsSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.lockDeclarations",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration lockDeclarations(IRNode classNode) {
    return getEnum(lockDeclarationsSlotInfo, classNode);
  }

  /** Add a lock declaration node to the list of locks for this class
   * declaration node.  It does not check to see this lock declaration
   * node is already in the list.
   */
  public static void addLockDeclaration(IRNode classNode, IRNode lockNode) {
    addToEnum(lockDeclarationsSlotInfo, classNode, lockNode);
  }

  /** Remove a lock declaration node from the list of locks for
   * this class declaration node.  It returns true if the lock node
   * was found (and removed).
   */
  public static boolean removeLockDeclaration(
    IRNode classNode,
    IRNode lockNode) {
    return removeFromEnum(lockDeclarationsSlotInfo, classNode, lockNode);
  }
  
  /* Lock declarations */

  static final SlotInfo policyLockDeclarationsSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.PolicyLockDeclarations",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration policyLockDeclarations(IRNode classNode) {
    return getEnum(policyLockDeclarationsSlotInfo, classNode);
  }

  /** Add a lock declaration node to the list of locks for this class
   * declaration node.  It does not check to see this lock declaration
   * node is already in the list.
   */
  public static void addPolicyLockDeclaration(
    IRNode classNode,
    IRNode lockNode) {
      addToEnum(policyLockDeclarationsSlotInfo, classNode, lockNode);
  }

  /** Remove a lock declaration node from the list of locks for
   * this class declaration node.  It returns true if the lock node
   * was found (and removed).
   */
  public static boolean removePolicyLockDeclaration(
    IRNode classNode,
    IRNode lockNode) {
    return  removeFromEnum(policyLockDeclarationsSlotInfo, classNode, lockNode);
  }
  
  
    

  static final SlotInfo requiredLocksSlotInfo =
    getConstantSlotInfo(
      "JavaPromise.requiredLocks",
      new IRSequenceType(IRNodeType.prototype));

  public static Enumeration requiredLocks(IRNode classNode) {
    return getEnum(requiredLocksSlotInfo, classNode);
  }

  /** Add a lock declaration node to the list of locks for this class
   * declaration node.  It does not check to see this lock declaration
   * node is already in the list.
   */
  public static void addRequiredLock(IRNode classNode, IRNode lockNode) {
    addToEnum(requiredLocksSlotInfo, classNode, lockNode);
  }

  /** Remove a lock declaration node from the list of locks for
   * this class declaration node.  It returns true if the lock node
   * was found (and removed).
   */
  public static boolean removeRequiredLock(IRNode classNode, IRNode lockNode) {
    return removeFromEnum(requiredLocksSlotInfo, classNode, lockNode);
  }

  static final SlotInfo lockReturnSlotInfo =
    getVersionedSlotInfo("JavaPromise.lockReturn", IRNodeType.prototype);

  public static IRNode getLockReturn(IRNode fieldNode) {
    return (IRNode) fieldNode.getSlotValue(lockReturnSlotInfo);
  }

  public static IRNode getLockReturnOrNull(IRNode fieldNode) {
    return getXorNull(lockReturnSlotInfo, fieldNode);
  }

  public static boolean lockReturnExists(IRNode fieldNode) {
    return fieldNode.valueExists(lockReturnSlotInfo);
  }

  public static void setLockReturn(IRNode fieldNode, IRNode region) {
    if (lockReturnExists(fieldNode)) {
      detachPromiseNode(fieldNode, getLockReturn(fieldNode));
    }
    fieldNode.setSlotValue(lockReturnSlotInfo, region);
    attachPromiseNode(fieldNode, region);
  }

  static final SlotInfo lockParamSlotInfo =
    getVersionedSlotInfo("JavaPromise.lockParam", IRNodeType.prototype);

  public static IRNode getLockParam(IRNode fieldNode) {
    return (IRNode) fieldNode.getSlotValue(lockParamSlotInfo);
  }

  public static IRNode getLockParamOrNull(IRNode fieldNode) {
    return getXorNull(lockParamSlotInfo, fieldNode);
  }

  public static boolean lockParamExists(IRNode fieldNode) {
    return fieldNode.valueExists(lockParamSlotInfo);
  }

  public static void setLockParam(IRNode fieldNode, IRNode region) {
    if (lockParamExists(fieldNode)) {
      detachPromiseNode(fieldNode, getLockParam(fieldNode));
    }
    fieldNode.setSlotValue(lockParamSlotInfo, region);
    attachPromiseNode(fieldNode, region);
  }

  /*******************************************************************
   * Stuff to operate over the SlotInfos 
   * 
   * promiseBooleanInfo and promiseChildrenInfo are all of them
   * (others are subsets)
   *******************************************************************/

  /** All promise slots which are Boolean-valued
   */
  static SlotInfo[] promiseBooleanInfo =
  {
    isUniqueSlotInfo,
    isUnsharedSlotInfo,
    isImmutableSlotInfo,      
    isBorrowedSlotInfo,
    isSynchronizedSlotInfo,
    isSelfProtectedSlotInfo,
    hasExhaustiveThrowsSlotInfo,
    isWriteSlotInfo,
  };      

  /** subset */
  static SlotInfo[] promiseIRNodeInfo = {
    // These are handled specially
    // returnNodeSlotInfo,
    // receiverNodeSlotInfo,
    initMethodSlotInfo,
      classInitMethodSlotInfo,
      preconditionSlotInfo,
      postconditionSlotInfo,
      throwConditionSlotInfo,
      fieldRegionSlotInfo,
      fieldAggregationSlotInfo,
      lockReturnSlotInfo,
      lockParamSlotInfo,
      };

  /** subset */
  static SlotInfo[] promiseIRSequenceInfo = {
    classRegionsSlotInfo,
    classInvariantsSlotInfo,
    methodEffectsSlotInfo,
    lockDeclarationsSlotInfo,    
    policyLockDeclarationsSlotInfo,
    requiredLocksSlotInfo,
      colorDeclSlotInfo,
      colorGrantSlotInfo,
      colorRevokeSlotInfo,
      colorNoteSlotInfo

  };                 

  /** All promise slots which involve nodes or sequences of nodes.
   */
  static SlotInfo[] promiseChildrenInfo =
    {
      returnNodeSlotInfo,
      receiverNodeSlotInfo,
      initMethodSlotInfo,
      classInitMethodSlotInfo,
      preconditionSlotInfo,
      postconditionSlotInfo,
      throwConditionSlotInfo,
      fieldRegionSlotInfo,
      fieldAggregationSlotInfo,
      lockReturnSlotInfo,
      lockParamSlotInfo,
    // these below are sequences      
    classRegionsSlotInfo,
    classInvariantsSlotInfo,
    methodEffectsSlotInfo,
    lockDeclarationsSlotInfo,
    policyLockDeclarationsSlotInfo,
      requiredLocksSlotInfo,
      colorDeclSlotInfo,
      colorGrantSlotInfo,
      colorRevokeSlotInfo,
      colorNoteSlotInfo

  };     
            
  public static void saveAttributes(Bundle b) {
    for (int i = 0; i < promiseBooleanInfo.length; i++) {
      b.saveAttribute(promiseBooleanInfo[i]);
    }
    for (int i = 0; i < promiseChildrenInfo.length; i++) {
      b.saveAttribute(promiseChildrenInfo[i]);
    }
  }

  /*
  public static void main(String args[]) {
    Bundle b = new Bundle();
    FileLocator floc = IRPersistent.fluidFileLocator;
    saveAttributes(b);
    try {
      b.store(floc);
    } catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Please press return to try again");
      try {
  System.in.read();
  b.store(floc);
      } catch (IOException ex2) {
  System.out.println(ex2.toString());
      }
    }
  }
  */

  private static Bundle promisebundle = null;

  public static Bundle getBundle() {
    if (promisebundle == null) {
      try {
        promisebundle =
          Bundle.loadBundle(
            UniqueID.parseUniqueID("javapromise"),
            IRPersistent.fluidFileLocator);
      } catch (Throwable t) {
        JavaGlobals.JAVA.info(t.toString());
      }
    }
    return promisebundle;
  }

  public static void main(String args[]) {
    JavaNode.main(args);
    Bundle b = getBundle();
    if (b != null)
      b.describe(System.out);
  }

  public static IRNode getParentOrPromisedFor(IRNode node) {
    IRNode p = tree.getParentOrNull(node);
    if (p == null)
      return getPromisedForOrNull(node);
    else
      return p;
  }

  public static Enumeration promiseChildren(IRNode node) {
    return new JavaPromiseChildrenEnumeration(node, promiseChildrenInfo);
  }

  public static Iterator promisesBottomUp(IRNode node) {
    return new JavaPromiseDescendantsIterator(node, promiseChildrenInfo);
  }

  /** Return an enumeration of all nodes in the tree or
   * in promises attached to the tree.
   */
  public static Enumeration bottomUp(IRNode node) {
    return new JavaPromiseTreeEnumeration(node, true);
  }

  private static final Token uniqueToken = new Keyword("unique");
  private static final Token unsharedToken = new Keyword("unshared");
  private static final Token immutableToken = new Keyword("immutable");
  private static final Token borrowedToken = new Keyword("borrowed");
  private static final Token synchronizedToken = new Keyword("synchronized");
  private static final Token selfProtectedToken = new Keyword("selfProtected");
  private static final Token hasExhaustiveThrowsToken =
    new Keyword("hasExhaustiveThrows");
  private static final Token returnToken = new Keyword("returns");
  private static final Token receiverToken = new Keyword("receiver");
  private static final Token preconditionToken = new Keyword("precondition");
  private static final Token postconditionToken = new Keyword("postcondition");
  private static final Token throwConditionToken = new Keyword("condition");
  private static final Token fieldRegionToken = new Keyword("mapInto");
  private static final Token fieldAggregationToken = new Keyword("aggregate");
  private static final Token classRegionToken = new Keyword("region");
  private static final Token classInvariantToken = new Keyword("invariant");
  private static final Token lockDeclarationToken = new Keyword("lock");
  private static final Token policyLockDeclarationToken = new Keyword("policyLock");
  private static final Token lockReturnToken = new Keyword("returnsLock");
  private static final Token lockParamToken = new Keyword("isLock");
  private static final Token requiredLocksToken = new Keyword("requiresLock");
  private static final Token colorDeclToken = new Keyword("color");
  private static final Token colorGrantToken = new Keyword("grant");
  private static final Token colorRevokeToken = new Keyword("revoke");
  private static final Token colorNoteToken = new Keyword("note");

  private static final Token methodEffectsStartToken =
    new Combined(
      new Keyword("effects"),
      new UnitedBP(1, "effects", Glue.UNIT, Glue.INDENT));
  private static final Token methodEffectsSeparatorToken =
    new Combined(
      new Combined(IndepBP.JUXTBP, new Keyword(",")),
      new UnitedBP(2, "effects", Glue.UNIT, Glue.JUXT));
  private static final Token methodEffectsStopToken =
    new UnitedBP(1, "effects", Glue.UNIT, Glue.JUXT);

  private static class TokenInfo {
  	public final String index;
  	public final SlotInfo si;
  	public final Token token;
  	TokenInfo(String i, SlotInfo sinfo, Token t) {
  		index = i;
  		si    = sinfo;
  		token = t;
  	}
  }

  private static final TokenInfo[] varPromiseTokens = {
  	new TokenInfo(UNIQUE, isUniqueSlotInfo, uniqueToken),
  	new TokenInfo(IMMUTABLE, isImmutableSlotInfo, immutableToken),
  	new TokenInfo(BORROWED, isBorrowedSlotInfo, borrowedToken),
  };
  
  private static final TokenInfo[] boolPromiseTokens = {
  	new TokenInfo(UNSHARED, isUnsharedSlotInfo, unsharedToken),
    new TokenInfo(SYNCHRONIZED, isSynchronizedSlotInfo, synchronizedToken),    
    new TokenInfo(SELF_PROTECTED, isSelfProtectedSlotInfo, selfProtectedToken),        
  	new TokenInfo(EXHAUSTIVE_THROWS, 
  	              hasExhaustiveThrowsSlotInfo, 
  	              hasExhaustiveThrowsToken),
  };
  
  private static final TokenInfo[] nodePromiseTokens = {
  	new TokenInfo(PRECONDITION, preconditionSlotInfo, preconditionToken),
  	new TokenInfo(POSTCONDITION, postconditionSlotInfo, postconditionToken),
  	new TokenInfo(THROW_CONDITION, throwConditionSlotInfo, throwConditionToken), 
  	new TokenInfo(FIELD_REGION, fieldRegionSlotInfo, fieldRegionToken), 
  	new TokenInfo(FIELD_AGGREGATION, fieldAggregationSlotInfo, fieldAggregationToken),
  	new TokenInfo(RETURN_LOCK, lockReturnSlotInfo, lockReturnToken),   	
  	new TokenInfo(LOCK_PARAM, lockParamSlotInfo, lockParamToken), 
  };
  
  private static final TokenInfo[] seqPromiseTokens = {
  	new TokenInfo(CLASS_INVARIANT, classInvariantsSlotInfo, classInvariantToken),  	
  	new TokenInfo(CLASS_REGION, classRegionsSlotInfo, classRegionToken),
  	new TokenInfo(LOCKS, lockDeclarationsSlotInfo, lockDeclarationToken),
    new TokenInfo(POLICY_LOCKS, policyLockDeclarationsSlotInfo, policyLockDeclarationToken),
  	new TokenInfo(REQUIRED_LOCKS, requiredLocksSlotInfo, requiredLocksToken),
      new TokenInfo(COLOR_DECL, colorDeclSlotInfo, colorDeclToken),
      new TokenInfo(COLOR_GRANT, colorGrantSlotInfo, colorGrantToken),
      new TokenInfo(COLOR_REVOKE, colorRevokeSlotInfo, colorRevokeToken),
      new TokenInfo(COLOR_NOTE, colorNoteSlotInfo, colorNoteToken),
  };

  public static boolean unparseVariablePromises(final IRNode node, 
                                                  final JavaUnparser u,
                                                  final boolean isSub,
                                                  Token promiseToken, 
                                                  boolean hasPromises) {
    final JavaUnparseStyle style = u.getStyle();
    for (int i = 0; i < varPromiseTokens.length; i++) {
      final TokenInfo info = varPromiseTokens[i];
      if (isX(info.si, node) && style.getUnparsePromiseOption(info.index)) {
        startPromise(node, u, hasPromises, promiseToken, isSub);
        hasPromises = true;
        promiseToken = null;
        info.token.emit(u, node);
      }
    }
    return hasPromises;
  }

  public static boolean unparseNodePromises(
    final IRNode node,
    final JavaUnparser u,
    final boolean isSub,
    Token promiseToken,
    boolean hasPromises) {
    final JavaUnparseStyle style = u.getStyle();
    for (int i = 0; i < nodePromiseTokens.length; i++) {
      final TokenInfo info = nodePromiseTokens[i];
      IRNode sub = getXorNull(info.si, node);

      if (sub != null && style.getUnparsePromiseOption(info.index)) {
        startPromise(node, u, hasPromises, promiseToken, isSub);
        hasPromises = true;
        promiseToken = null;
        info.token.emit(u, node);
        u.unparse(sub);
      }
    }
    return hasPromises;
  }

  /** Unparse promises into a fmt stream
   * This class must be updated by hand for every new promise added.
   */
  public static void unparsePromises(final IRNode node, final JavaUnparser u) {
    final JavaUnparseStyle style = u.getStyle();
    boolean hasPromises = unparseVariablePromises(node, u, false, null, false);

    for (int i = 0; i < boolPromiseTokens.length; i++) {
      final TokenInfo info = boolPromiseTokens[i];
      if (isX(info.si, node) && style.getUnparsePromiseOption(info.index)) {
        startPromise(node, u, hasPromises);
        hasPromises = true;
        info.token.emit(u, node);
      }
    }

    IRNode subnode = getReceiverNodeOrNull(node);
    if (subnode != null) {
      hasPromises =
        unparseVariablePromises(subnode, u, true, receiverToken, hasPromises);
    }

    subnode = getReturnNodeOrNull(node);
    if (subnode != null) {
      hasPromises =
        unparseVariablePromises(subnode, u, true, returnToken, hasPromises);
      hasPromises =
        unparseNodePromises(subnode, u, true, returnToken, hasPromises);
    }

    /* instance and class inits not looked at (unused) */

    hasPromises = unparseNodePromises(node, u, false, null, hasPromises);

    for (int i = 0; i < seqPromiseTokens.length; i++) {
      final TokenInfo info = seqPromiseTokens[i];
      final Enumeration e = getEnum(info.si, node);
      while (e.hasMoreElements()) {
        IRNode elt = (IRNode) e.nextElement();
        if (elt != null) {
          startPromise(node, u, hasPromises);
          hasPromises = true;
          info.token.emit(u, node);
          u.unparse(elt);
        }
      }
    }


    // handle enumerated method effects
    Enumeration fx = methodEffects(node);
    if (style.unparsePromiseEffects() && fx != null) {
      startPromise(node, u, hasPromises);
      hasPromises = true;
      methodEffectsStartToken.emit(u, node);

      if (!fx.hasMoreElements()) {
        // nothing
         (new Keyword("@reads nothing")).emit(u, node);
      } else {
        for (;;) {
          IRNode effect = (IRNode) fx.nextElement();
          if (effect != null) {
            u.unparse(effect);
          }
          if (!fx.hasMoreElements()) {
            break;
          }
          methodEffectsSeparatorToken.emit(u, node);
        }
      }
      methodEffectsStopToken.emit(u, node);
    }

    stopPromises(node, u, hasPromises);
  }

  private static Glue promiseIndent = new Glue(2);
  private static final Token startPromises =
    new Combined(
      new Keyword("/*"),
      new UnitedBP(1, "promise", Glue.UNIT, promiseIndent));
  private static final Token startPromise =
    new Combined(
      new UnitedBP(2, "promise", Glue.UNIT, Glue.UNIT),
      new Combined(new Keyword("@"), IndepBP.JUXTBP));
  private static final Token startSubPromise =
    new Combined(
      new UnitedBP(2, "promise", Glue.UNIT, Glue.UNIT),
      new Combined(new Keyword("@@"), IndepBP.JUXTBP));
  private static final Token stopPromises =
    new Combined(
      new UnitedBP(1, "promise", Glue.UNIT, Glue.JUXT),
      new Combined(new Keyword("*/"), IndepBP.DEFAULTBP));

  private static void startPromise(
    IRNode node,
    JavaUnparser u,
    boolean hasPromises,
    Token promiseToken,
    boolean isSub) {
    if (isSub) {
      startSubPromise(node, u, hasPromises, promiseToken);
    } else {
      startPromise(node, u, hasPromises);
    }
  }

  private static void startPromise(
    IRNode node,
    JavaUnparser u,
    boolean hasPromises) {
    if (!hasPromises) {
      startPromises.emit(u, node);
    }
    startPromise.emit(u, node);
  }

  private static void startSubPromise(
    IRNode node,
    JavaUnparser u,
    boolean hasPromises,
    Token promiseToken) {
    if (promiseToken != null) {
      startPromise(node, u, hasPromises);
      promiseToken.emit(u, node);
    }
    startSubPromise.emit(u, node);
  }

  private static void stopPromises(
    IRNode node,
    JavaUnparser u,
    boolean hasPromises) {
    if (hasPromises) {
      stopPromises.emit(u, node);
    }
  }
}

class JavaPromiseDescendantsIterator extends ProcessIterator {
  final SlotInfo[] promiseChildrenInfo;

  JavaPromiseDescendantsIterator(IRNode n, SlotInfo[] pci) {
    super(new EnumerationIterator(JavaNode.tree.bottomUp(n)));
    promiseChildrenInfo = pci;
  }

  protected Iterator getNextIter(Object o) {
    return new EnumerationIterator(
      new JavaPromiseChildrenEnumeration((IRNode) o, promiseChildrenInfo));
  }

  protected Object select(Object o) {
    return (o == null) ? notSelected : o;
  }
}

class JavaPromiseChildrenEnumeration implements Enumeration {
  final IRNode node;
  final SlotInfo[] promiseChildrenInfo;

  JavaPromiseChildrenEnumeration(IRNode n, SlotInfo[] pci) {
    node = n;
    promiseChildrenInfo = pci;
  }

  Object next = null;
  boolean nextIsValid = false;
  int info = 0;

  private Object getNext() {
    if ((sub != null) && sub.hasMoreElements()) {
      return sub.nextElement();
    } else {
      sub = null;
    }

    while (info < promiseChildrenInfo.length) {
      SlotInfo childSI = promiseChildrenInfo[info++];
      if (node.valueExists(childSI)) {
        Object value = node.getSlotValue(childSI);
        if (value instanceof IRSequence) {
          sub = ((IRSequence) value).elements();
          return getNext();
        }
        if (value != null)
          return value;
      }
    }
    return noNextElement;
  }
  private static Object noNextElement = new Object();

  public boolean hasMoreElements() {
    if (nextIsValid) {
      return true;
    }
    next = getNext();
    nextIsValid = (next != noNextElement);
    return nextIsValid;
  }

  Enumeration sub = null;

  public Object nextElement() {
    if (nextIsValid || hasMoreElements()) {
      nextIsValid = false;
      return next;
    }
    throw new NoSuchElementException("no more promise children");
  }
}
