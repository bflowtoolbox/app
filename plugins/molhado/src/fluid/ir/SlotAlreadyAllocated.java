/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotAlreadyAllocated.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import fluid.FluidException;

public class SlotAlreadyAllocated extends FluidException {
  public SlotAlreadyAllocated() { super(); }
  public SlotAlreadyAllocated(String s) { super(s); }
}
