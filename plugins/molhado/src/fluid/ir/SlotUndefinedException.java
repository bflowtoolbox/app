package fluid.ir;

import fluid.FluidRuntimeException;

public class SlotUndefinedException extends FluidRuntimeException {
  public SlotUndefinedException() { super(); }
  public SlotUndefinedException(String s) { super(s); }
}
