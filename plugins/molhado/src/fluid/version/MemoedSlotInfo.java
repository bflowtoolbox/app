/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/MemoedSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Hashtable;

import fluid.NotImplemented;
import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRNode;
import fluid.ir.IRType;
import fluid.ir.PlainIRNode;
import fluid.ir.Slot;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.ir.Slots;

/** SlotInfo for slots which must be computed and whose computed
 * values should be saved.
 * <p> The implementation keeps one set of values for each
 * requested version.  If the versioned has not been requested yet, we
 * allocate a new table.  If the value for a particular node and
 * version has not been computed yet, we call the recomputeValue
 * method.
 */
public abstract class MemoedSlotInfo extends DerivedSlotInfo {
  /** A hashtable mapping versions to tables of values */
  private final Hashtable versionMemo = new Hashtable();

  /** Create an anonymous attribute.
   */
  protected MemoedSlotInfo() {
    super();
  }

  /** Create an anonymous attribute.
   * @deprecated omit Slots parameter.
   */
  protected MemoedSlotInfo(Slots unused) {
    super();
  }

  /** Create a named attribute memoing a computation. */
  protected MemoedSlotInfo(String name, IRType type) 
      throws SlotAlreadyRegisteredException 
  {
    super(name,type);
  }

  /** Create a named attribute memoing a computation.
   * @deprecated omit Slots parameter
   */
  protected MemoedSlotInfo(String name, IRType type, Slots unused) 
      throws SlotAlreadyRegisteredException 
  {
    super(name,type);
  }

  /** This value is used to represent null when stored in the hashtable.
   * (Too bad, java.util.Hashtable insists on using null as undefined.)
   * A value not equal to any other.
   */
  private Object nullStandin = new Object();

  /** Return a precomputed value if it exists, otherwise
   * invoke the method for computing a value and then return it,
   * After saving the result.
   */
  protected Object getSlotValue(IRNode node) {
    Hashtable valueMemo = getMemo();
    Object value        = valueMemo.get(node);
    if (value == null) {
      value = computeSlotValue(node);
      putValue(valueMemo, node, value);
    }
    else if (value == nullStandin) return null;

    return value;
  }

  protected boolean valueExists(IRNode node) {
    throw new NotImplemented();
  }

  /** Save away a computation that was not directly requested.
   * Sometimes it makes sense to compute values eagerly rather
   * than on demand only.
   */
  protected void memoSlotValue(IRNode node, Object value) {
    putValue(getMemo(), node, value);
  }

  protected Hashtable getMemo() {
    Version v           = Version.getVersion();
    Hashtable valueMemo = (Hashtable)versionMemo.get(v);
    if (valueMemo == null) {
      valueMemo = new Hashtable();
      versionMemo.put(v,valueMemo);
    }
    return valueMemo;
  }
  private final void putValue(Hashtable valueMemo, IRNode node, Object val) {
    valueMemo.put(node, (val == null) ? nullStandin : val);
  }

  private static SlotUndefinedException undefE = new SlotUndefinedException();

  protected final Object peekAtValue(IRNode node) {
    Object v = getMemo().get(node);
    if (v == nullStandin) {
      return null;
    }
    else if (v == null) {
      throw undefE;
    }
    return v;
  }

  /** This method represents the function to be memoed.
   */
  protected abstract Object computeSlotValue(IRNode node);

  /** Flush all computed values. */
  public void clear() {
    versionMemo.clear();
  }
}

class TestMemoedSlotInfo {
  public static void main(String[] args) {
    Slot sl =
      VersionedSlotFactory.prototype.undefinedSlot();
    sl = sl.setValue(new Integer(0));
    Version.getVersion(); // force version to be frozen
    sl = sl.setValue(new Integer(1));
    final Slot slcopy = sl;
    SlotInfo s = new MemoedSlotInfo() {
      protected Object computeSlotValue(IRNode node) {
	System.out.println("Computing...");
	if (slcopy.getValue().equals(new Integer(2))) {
	  return null;
	} else {
	  return node.toString() + " = " + slcopy.getValue().toString();
	}
      }
    };
    IRNode n = new PlainIRNode();
    System.out.println(n.getSlotValue(s));
    System.out.println(n.getSlotValue(s));
    sl.setValue(new Integer(2));
    System.out.println(n.getSlotValue(s));
    System.out.println(n.getSlotValue(s));    
    sl.setValue(new Integer(3));
    System.out.println(n.getSlotValue(s));
    System.out.println(n.getSlotValue(s));    
  }
}
