/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoChangedEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

public class SlotInfoChangedEvent extends SlotInfoEvent {
  private final Object oldValue;
  public SlotInfoChangedEvent(SlotInfo attr, IRNode n, 
  			      Object oldVal, Object newVal) 
  {
    super(attr,n,newVal);
    oldValue = oldVal;
  }
  
  public Object getOldValue() {
    return oldValue;
  }
}
