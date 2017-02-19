/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/PersistentSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** Persistable attribute need a few more methods.
 */
public interface PersistentSlotInfo {
  public SlotFactory getSlotFactory();
  public IRType getType();
  public boolean isPredefined();
  public Object getDefaultValue();

  /** Return true if the slot for this node has changed. */
  public boolean valueChanged(IRNode node);
  
  /** If there is a value is for this node that should be stored,
   * output both node and value (in that order), otherwise do nothing.
   */
  public void writeSlot(IRNode node, IROutput out) throws IOException;

  /** If the slot may have changed since its "previous value"
   * (however defined, but "initial value" by default),
   * output both node and value (in that order), otherwise do nothing.
   * If the slot contains an IRCompound, write the changedContents too.
   */
  public void writeChangedSlot(IRNode node, IROutput out) throws IOException;

  /** Read in a slot value for the specific node.
   * This method must <em>not</em> read in a node first.
   */
  public void readSlotValue(IRNode node, IRInput in) throws IOException;

  /** Read in a changed slot description for a particular node.
   * Must be able to read in information written by writeChangedSlot
   * after the node.
   */
  public void readChangedSlotValue(IRNode node, IRInput in)
    throws IOException;
}
