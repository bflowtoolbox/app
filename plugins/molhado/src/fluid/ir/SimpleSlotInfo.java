/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SimpleSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import fluid.util.ImmutableHashOrderSet;
import fluid.util.ImmutableSet;

/** An attribute with values stored directly in a hashtable. 
 * This attribute is basically an adapter of a hashtable.
 */
public class SimpleSlotInfo extends SlotInfo implements PersistentSlotInfo {
  public SlotFactory getSlotFactory() { return SimpleSlotFactory.prototype; }
  
  private final boolean predefined;
  private final Object defaultValue;

  public boolean isPredefined() { return predefined; }
  public Object getDefaultValue() { return defaultValue; }
  
  /** Allocate a new anonymous and temporary slot. */
  public SimpleSlotInfo() { 
    super(); 
    predefined = false;
    defaultValue = null;
  }

  /** Allocate and register a new and possibly persistent slot.
   * @param name The name under which to register the slots.
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   */
  public SimpleSlotInfo(String name, IRType type)
       throws SlotAlreadyRegisteredException
  {
    super(name,type);
    predefined = false;
    defaultValue = null;
  }
  
  /** Allocate a new anonymous predefined slot 
   * @param def initial value
   */
  public SimpleSlotInfo(Object def) {
    super();
    predefined = true;
    defaultValue = def;
  }

  /** Allocate and register a new and possibly persistent slot.
   * @param name The name under which to register the slots.
   * @param def initial value
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   */
  public SimpleSlotInfo(String name, IRType type, Object def)
       throws SlotAlreadyRegisteredException
  {
    super(name,type);
    predefined = true;
    defaultValue = def;
  }
  
  private Hashtable table = new Hashtable();
  private HashSet changed = new HashSet();

  private static Object nullObject = new Object() {
    // A special version of equals
    // that makes it equal to null.
    // We don't need to worry about symmetricity since
    // null.equals(X) crashes.
    public boolean equals(Object other) {
      return other == null || other == this;
    }
  };

  protected boolean valueExists(IRNode node) {
    return predefined || table.get(node) != null;
  }

  public boolean valueChanged(IRNode node) {
    Object obj = table.get(node);
    if (obj == null) return false;
    return changed.contains(node);
  }

  protected Object getSlotValue(IRNode node) throws SlotUndefinedException {
    Object obj = table.get(node);
    if (obj == null) {
      if (predefined) return defaultValue;
      throw new SlotUndefinedException(node.toString());
    }
    if (obj == nullObject) return null;
    return obj;
  }

  protected void setSlotValue(IRNode node, Object newValue)
     throws SlotImmutableException
  {
    changed.add(node);
    doSetSlotValue(node,newValue);
  }

  /** Remove any explicit value for this slot, reventing to
   * the default value, or to undefined.
   */
  public void removeSlotValue(IRNode node)
     throws SlotImmutableException
  {
    table.remove(node);
    changed.remove(node);
  }

  /** An internal version of setSlotValue that
   * always succeeds.  used in reading slot values,
   * especialy for constant slot info's.
   */
  protected void doSetSlotValue(IRNode node, Object newValue)
  {
    if (newValue == null) newValue = nullObject;
    Object oldValue = table.put(node,newValue);
    if (oldValue == null)
      notifyDefineObservers(node);
    else
      notifyIRObservers(node);
    if (hasListeners()) {
      if (oldValue == null) {
	informListeners(new SlotInfoDefinedEvent(this,node,newValue));
      } else {
	if (oldValue == nullObject) oldValue = null;
	informListeners(new SlotInfoChangedEvent(this,node,oldValue,newValue));
      }
    }
  }

  public void writeSlot(IRNode node, IROutput out)
    throws IOException
  {
    Object obj = table.get(node);
    if (obj == null) return;
    if (out.debug()) {
      IRRegion r = IRRegion.getOwner(node);
      System.out.println("Writing " + name() + " for " +
			 r + " #" + r.getIndex(node));
    }
    if (obj == nullObject) obj = null;
    out.writeNode(node);
    type().writeValue(obj,out);
  }

  public void writeChangedSlot(IRNode node, IROutput out)
    throws IOException
  {
    Object obj = table.get(node);
    if (obj == null) return;
    boolean valueChanged = changed.contains(node);
    boolean contentsChanged =
      (obj instanceof IRCompound &&
       ((IRCompound)obj).isChanged());
    if (!valueChanged && !contentsChanged) return;
    if (obj == nullObject) obj = null;
    out.writeNode(node);

    // If both changed, the value change takes precedence
    // since it will involve writing the entire thing.
    out.writeBoolean(!valueChanged);
    if (valueChanged) {
      if (out.debug()) {
	IRRegion r = IRRegion.getOwner(node);
	System.out.println("Writing changed " + name() +
			   " for " + r + " #" + r.getIndex(node));
      }
      type().writeValue(obj,out);
      changed.remove(node);
    } else {
      if (out.debug()) {
	IRRegion r = IRRegion.getOwner(node);
	System.out.println("Writing changed contents of " + name() +
			   " for " + r + " #" + r.getIndex(node));
      }
      IRCompound comp = (IRCompound)obj;
      comp.writeChangedContents((IRCompoundType)type(),out);
    }
  }

  public void readSlotValue(IRNode node, IRInput in)
     throws IOException
  {
    if (in.debug()) {
      IRRegion r = IRRegion.getOwner(node);
      System.out.println("Reading " + name() + " for " +
			 r + " #" + r.getIndex(node));
    }
    doSetSlotValue(node,type().readValue(in));
    changed.remove(node);
  }

  public void readChangedSlotValue(IRNode node, IRInput in) throws IOException
  {
    if (in.getRevision() >= 4) {
      boolean changedContents = in.readBoolean();
      if (changedContents) {
	if (in.debug()) {
	  IRRegion r = IRRegion.getOwner(node);
	  System.out.println("Reading changed contents of " + name() +
			     " for " + r + " #" + r.getIndex(node));
	}
	IRCompound comp = (IRCompound)table.get(node);
	comp.readChangedContents((IRCompoundType)type(),in);
	return;
      }
    }
    readSlotValue(node,in);
  }

  public ImmutableSet index(Object value) {
    ArrayList nodes = new ArrayList();
    Iterator contents = table.entrySet().iterator();
    boolean normal = true;
    if (predefined &&
	(defaultValue == value ||
	 (defaultValue != null && defaultValue.equals(value)))) {
      normal = false;
    }
    while (contents.hasNext()) {
      Map.Entry e = (Map.Entry)contents.next();
      if (e.getValue().equals(value) == normal)
	nodes.add(e.getKey());
    }
    return new ImmutableHashOrderSet(nodes.toArray(),!normal);
  }

  public void describeSlot(IRNode node, PrintStream out) {
    if (changed.contains(node)) {
      out.print("Changed ");
    }
    super.describeSlot(node,out);
    Object obj = table.get(node);
    if (obj == null) {
      out.println("    <no info>");
    } else {
      if (obj == nullObject) obj = null;
      out.println("    value = " + obj);
    }
  }
}
