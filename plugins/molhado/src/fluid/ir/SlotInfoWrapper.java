/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoWrapper.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import fluid.util.ImmutableSet;

/**
 * A class that delegates all requests for attribute values
 * to another slot info.  It can be used as the base
 * for implementing various kinds of wrapped attributes.
 * <p>Known Bugs:
 * <ol>
 *   <li> Observers/listeners will notice nothing.
 * </ol>
 */
public class SlotInfoWrapper extends SlotInfo {
  protected final SlotInfo wrapped;
  /** Construct a wrapper around the given attribute. */
  public SlotInfoWrapper(SlotInfo attr) {
    super();
    wrapped = attr;
  }

  public IRType type() {
    return wrapped.type();
  }

  protected boolean valueExists(IRNode node) {
    return node.valueExists(wrapped);
  }

  protected  Object getSlotValue(IRNode node)
      throws SlotUndefinedException
  {
    return node.getSlotValue(wrapped);
  }

  protected void setSlotValue(IRNode node, Object newValue)
      throws SlotImmutableException
  {
    node.setSlotValue(wrapped,newValue);
  }

  public ImmutableSet index(Object value) {
    return wrapped.index(value);
  }
}
