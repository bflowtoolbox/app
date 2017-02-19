/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/ConstantSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** A constant attribute with values stored directly in a hashtable. 
 */
public class ConstantSlotInfo extends SimpleSlotInfo {
  public SlotFactory getSlotFactory() { return ConstantSlotFactory.prototype; }

  /** Allocate a new anonymous and temporary slot. */
  public ConstantSlotInfo() { super(); }

  /** Allocate and register a new and possibly persistent slot.
   * @param name The name under which to register the slots.
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   */
  public ConstantSlotInfo(String name, IRType type)
       throws SlotAlreadyRegisteredException
  {
    super(name,type);
  }
  
  /** Allocate an anonymous slot that also returns the same result.
   * (Probably useless, this constructor is defined merely for orthogonality.)
   */
  public ConstantSlotInfo(Object value) {
    super(value);
  }
  /** Allocate and register a new and possibly persistent slot.
   * @param name The name under which to register the slots.
   * @param value The value for all nodes (immutable!). 
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   * (Probably useless, this constructor is defined merely for orthogonality.)
   */
  public ConstantSlotInfo(String name, IRType type, Object value)
       throws SlotAlreadyRegisteredException
  {
    super(name,type,value);
  }

  /*
   * The following redefinition doesn't work all the time
   * because sometimes we *do* know that the slot is undefined.
   * But finding a way to shift a slot from unknown to undefined
   * is complicated and uninteresting.  And so as long as
   * we're not going to be perfectly correct, we might as well
   * do the easier thing.
  protected Object getSlotValue(IRNode node)
    throws SlotUndefinedException
  {
    if (!valueExists(node)) {
      // if the node is old, it may be defined in some ir store file.
      if (IRRegion.hasOwner(node) && !IRRegion.getOwner(node).isNew()) {
	new SlotUnknownException("constant slot of shared node",
	                         new SlotInfoSlot(this,node)).handle();
        return getSlotValue(node);
      }
    }
    return super.getSlotValue(node);
  }
   */

  protected void setSlotValue(IRNode node, Object newValue) 
    throws SlotImmutableException
  {
    if (valueExists(node)) throw new SlotImmutableException();
    // we don't permit constant slots to be defined for a
    // node that is not new because we don't know who is in
    // charge of saving it, and to avoid clashes (where the
    // same slot gets multiply defined in different JVM's).
    if (IRRegion.hasOwner(node) && !IRRegion.getOwner(node).isNew())
      throw new SlotImmutableException("constant slot of shared node");
    super.setSlotValue(node,newValue);
  }

  public void readSlotValue(IRNode node, IRInput in)
     throws IOException
  {
    if (valueExists(node)) {
      // permit reads if the value is the same
      if (in.debug()) {
	IRRegion r = IRRegion.getOwner(node);
	System.out.println("Reading " + name() + " for " +
			   r + " #" + r.getIndex(node));
      }
      Object oldValue = getSlotValue(node);
      Object newValue;
      // IRCompounds need help: because two different compounds
      // are always different.
      if (type() instanceof IRCompoundType) {
	newValue = ((IRCompoundType)type()).readValue(in,oldValue);
      } else {
	newValue = type().readValue(in);
      }
      if (newValue != oldValue &&
	  (newValue == null || !newValue.equals(oldValue)))
	throw new SlotImmutableException();
    } else super.readSlotValue(node,in);
  }

  /* Write the changed contents of the compound (if any). */
  public void writeChangedSlot(IRNode node, IROutput out) throws IOException {
    if (type() instanceof IRCompoundType && valueExists(node)) {
      IRCompound comp = (IRCompound)getSlotValue(node);
      if (comp.isChanged()) {
	out.writeNode(node);
	comp.writeChangedContents((IRCompoundType)type(),out);
      }
    }
  }

  /* Read the changed contents of the compound */
  public void readChangedSlotValue(IRNode node, IRInput in)
    throws IOException 
  {
    IRCompound comp = (IRCompound)getSlotValue(node);
    comp.readChangedContents((IRCompoundType)type(),in);
  }
}
  
