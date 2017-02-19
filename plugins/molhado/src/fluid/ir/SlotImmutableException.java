package fluid.ir;

import fluid.FluidRuntimeException;

public class SlotImmutableException extends FluidRuntimeException {
  public SlotImmutableException() { super(); }
  public SlotImmutableException(String s) { super(s); }
}
