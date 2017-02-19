/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/PlainIRNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fluid.util.ThreadGlobal;

/** A default implementation of the intermediate representation node 
 * interface.  There is a notion of a current region;
 * if it is non-null, the node is added to that region.
 * @see SlotInfo
 * @see IRRegion
 */
public class PlainIRNode implements IRNode, Serializable {
  private static ThreadGlobal regionVar = new ThreadGlobal(null);

  /** Set the current region.
   * By default, newly created nodes are in this region.
   */
  public static void setCurrentRegion(IRRegion r) {
    regionVar.setValue(r);
  }
  public static IRRegion getCurrentRegion() {
    return (IRRegion) regionVar.getValue();
  }
  public static void pushCurrentRegion(IRRegion r) {
    regionVar.pushValue(r);
  }
  public static void popCurrentRegion() {
    regionVar.popValue();
  }

  public Object identity() {
    return this;
  }

  public boolean equals(Object other) {
    if (other instanceof IRNode) {
      return this == ((IRNode) other).identity();
    } else {
      return false;
    }
  }

  private static int nodesCreated = 0;

  public static int getTotalNodesCreated() {
    return nodesCreated;
  }

  /** Create a new IRNode.  Add it to current region, if any.
   */
  public PlainIRNode() {
    this(getCurrentRegion());
  }

  /** Create a new IRNode.
   * @param region region to add node to.
   */
  public PlainIRNode(IRRegion region) {
    nodesCreated++;
    if (region != null) {
      region.saveNode(this);
    } else {
      // (new Throwable()).printStackTrace();
    }
  }

  /** Get the slot's value for a particular node.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @precondition nonNull(si)
   * @return the value of the slot associated with this node.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @exception SlotUndefinedException
   * If the slot is not initialized with a value.
   */
  public Object getSlotValue(SlotInfo si) throws SlotUndefinedException {
    return si.getSlotValue(this);
  }

  /** Change the value stored in the slot.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @param newValue value to store in the slot.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   *   <dt>capabilities<dd> store
   * </dl>
   */
  public void setSlotValue(SlotInfo si, Object newValue)
    throws SlotImmutableException {
    si.setSlotValue(this, newValue);
  }

  // for convenience:
  public int getIntSlotValue(SlotInfo si) {
    return ((Integer) getSlotValue(si)).intValue();
  }
  // for convenience
  public void setSlotValue(SlotInfo si, int newValue) {
    setSlotValue(si, new Integer(newValue));
  }

  /** Check if a value is defined for a particular node.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @precondition nonNull(si)
   * @return true if getSlotValue would return a value, false otherwise
   */
  public boolean valueExists(SlotInfo si) {
    return si.valueExists(this);
  }

  /** If in a region, then self-identify */
  public String toString() {
    if (IRRegion.hasOwner(this)) {
      return IRRegion.getOwner(this) + " #" + IRRegion.getOwnerIndex(this);
    } else {
      return super.toString();
    }
  }

  /** Return a wrapped IRNode for serialization.
   * Requires JDK 1.2
   */
  public Object writeReplace() {
    return new PlainIRNodeWrapper(this);
  }
}

class PlainIRNodeWrapper implements Serializable {
  private transient IRNode node;

  PlainIRNodeWrapper(IRNode n) {
    node = n;
  }
  private void writeObject(ObjectOutputStream out) throws IOException {
    final IRRegion reg = IRRegion.getOwner(node);
    final int index = IRRegion.getOwnerIndex(node);
    out.writeInt(index);
    reg.writeReference(out);
  }
  private void readObject(ObjectInputStream in)
    throws IOException, ClassNotFoundException {
    int i = in.readInt();
    IRRegion reg = (IRRegion) IRPersistent.readReference(in);
    node = reg.getNode(i);
  }
  public Object readResolve() {
    return node;
  }
}