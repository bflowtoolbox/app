package fluid.ir;

import fluid.FluidException;

public class SlotNotRegisteredException extends FluidException {
  public SlotNotRegisteredException() { super(); }
  public SlotNotRegisteredException(String s) { super(s); }
}
