/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoDefinedEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

public class SlotInfoDefinedEvent extends SlotInfoEvent {
  public SlotInfoDefinedEvent(SlotInfo attr, IRNode n, Object newVal) {
    super(attr,n,newVal);
  }
}
