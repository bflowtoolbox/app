package fluid.ir;

import fluid.FluidException;

public class SlotAlreadyRegisteredException extends FluidException {
  public SlotAlreadyRegisteredException() { super(); }
  public SlotAlreadyRegisteredException(String s) { super(s); }
}
