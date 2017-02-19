package fluid.ir;

import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;

import fluid.FluidRuntimeException;
import fluid.util.ImmutableSet;

/** Instance of this class can be abstractly viewed as tables from
 * IRNodes to slot values.  In subclasses, the abstraction is
 * implemented as either compute-on-demand, or using stored instances
 * implementing interface Slot. </p>
 *
 * <p> Some of the instances have names, and are <em>registered</em>
 * so that the information can be communicated with other analyses,
 * and so that values can be saved in persistent store.  Other slots
 * are anonymous (and therefore private) and temporary.  We may want
 * to separate registering from persistent later. </p>
 *
 * @see IRNode
 * @see Slot
 * @see StoredSlotInfo
 * @see Bundle
 *
 * @typeparam Value The type of values stored in this slot.
 * <dl purpose=fluid>
 *  <dt>capabilities<dd> store
 * </dl>
 */
public abstract class SlotInfo extends IRObservable {
  /** Private registration table
   * @type Hashtable[String,SlotInfo]
   */
  private static final Hashtable registry = new Hashtable();

  /** Name under which slots are known.
   * Null for anonymous slots.
   */
  private String name;

  /** Type of values stored in the slots.
   * Necessary for persistence.
   */
  private IRType type;

  /** Bundle this slot info (attribute) belongs to.
   */
  private Bundle bundle;

  public String name() {
    return name;
  }

  public IRType type() {
    return type;
  }

  /** Calls <code>type()</code> */
  public IRType getType() {
    return type();
  }

  /** Get the bundle this attribute is associated with. */
  public Bundle getBundle() {
    return bundle;
  }

  /** Set the bundle this attribute is associated with.
   * @throws FluidRuntimeException if bundle already specified.
   */
  protected void setBundle(Bundle b) {
    if (bundle != null && bundle != b)
      throw new FluidRuntimeException("attribute already bundled");
    bundle = b;
  }

  /** Allocate a new anonymous and temporary slot. */
  public SlotInfo() {
  }

  /** Allocate and register a new and possibly persistent slot.
   * @param name The name under which to register the slots.
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   * @capabilities store, read, write
   */
  public SlotInfo(String name, IRType type)
    throws SlotAlreadyRegisteredException {
    this.name = name;
    this.type = type;
    SlotInfo oldSlotInfo = (SlotInfo) registry.put(name, this);
    if (oldSlotInfo != null) {
      registry.put(name, oldSlotInfo);
      throw new SlotAlreadyRegisteredException(name);
    }
  }

  /** Locate a registered SlotInfo.
   * @return the SlotInfo instance registered under this name.
   * @exception SlotNotRegisteredException
   * If no slots have been registered under this name.
   */
  public static SlotInfo findSlotInfo(String name)
    throws SlotNotRegisteredException {
    SlotInfo si = (SlotInfo) registry.get(name);
    if (si == null)
      throw new SlotNotRegisteredException(name);
    return si;
  }

  /** Unregister a registered SlotInfo.
   * Not public because this should only be done when the slot is
   * being discarded.
   */
  protected static void unregisterSlotInfo(SlotInfo si) {
    registry.remove(si.name);
  }

  /** Get ready to discard this attribute.  Because of the way
   * registration works, this method must be called explicitly
   * for registered slots.
   */
  public void finalize() throws Throwable {
    if (name != null) {
      // Only unregister the slot info name if this *is* the
      // registered SlotInfo.  This required because otherwise
      // SlotInfos for which a SlotAlreadyRegisteredException
      // is thrown during construction will cause the slot
      // to become unregistered when the bogus SlotInfo
      // is finalized.
      if (this == registry.get(name))
        unregisterSlotInfo(this);
    }
    super.finalize();
  }

  private Vector listeners = null;

  /** Return true if slot info events are being looked at. */
  protected boolean hasListeners() {
    return listeners != null && listeners.size() > 0;
  }

  /** Add a new listener to this attribute. */
  public void addSlotInfoListener(SlotInfoListener l) {
    if (listeners == null)
      listeners = new Vector();
    listeners.addElement(l);
  }
  /** Remove a listener from this attribute. */
  public void removeSlotInfoListener(SlotInfoListener l) {
    if (listeners == null)
      return;
    listeners.removeElement(l);
  }

  /** Inform all listeners that something has happened. */
  protected final void informListeners(SlotInfoEvent e) {
    if (!hasListeners())
      return;
    int n = listeners.size();
    for (int i = 0; i < n; ++i) {
      ((SlotInfoListener) listeners.elementAt(i)).handleSlotInfoEvent(e);
    }
  }

  /** Check if a value is defined for a particular node.
   * This is an internal method, use IRNode.valueExists
   * to get the value of a slot's value for a node.
   * @param node IR node for which the slot is to be returned.
   * @precondition nonNull(node)
   * @return true if getSlotValue would return a value, false otherwise.
   */
  protected abstract boolean valueExists(IRNode node);

  /** Get the slot's value for a particular node.
   * This is an internal method, use IRNode.getSlotValue
   * to get the value of a slot's value for a node.
   * @param node IR node for which the slot is to be returned.
   * @precondition nonNull(node)
   * @return the value of the slot associated with this node.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @exception SlotUndefinedException
   * If the slot is not initialized with a value.
   */
  protected abstract Object getSlotValue(IRNode node)
    throws SlotUndefinedException;

  /** Modify the slot's value for a particular node.
   * This is an internal method, use IRNode.setSlotValue
   * to set the value of a slot's value for a node.
   * @param node IR node for which the slot is to be modified.
   * @precondition nonNull(node)
   * @param newValue value to be stored in the slot.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @exception SlotImmutableException
   * If the slot is not mutable.  The slot may be constant.
   * Or its value may be derived from other slots.
   */
  protected abstract void setSlotValue(IRNode node, Object newValue)
    throws SlotImmutableException;

  /** Return the set of nodes that have the given value
   * as their (defined) values.  This is an optional feature:
   * any slot info may simply decline to index by returning
   * null.  The return value may also be an infinite set
   * if the value requested is the default value.
   * In that case, do not iterate over the set!
   */
  public ImmutableSet index(Object value) {
    return null;
  }

  public String toString() {
    if (name == null)
      return super.toString();
    return name;
  }

  public void describeSlot(IRNode node, PrintStream out) {
    out.println("Intrinsic slot of <" + getClass().getName() + ">");
  }
}
