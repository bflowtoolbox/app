/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** An adapter class that permits us to refer to a slot by attribute
 * and node.
 */
public class SlotInfoSlot extends DefaultSlotStorage {
    public final SlotInfo attr;
    public final IRNode node;

    public SlotInfoSlot(SlotInfo si, IRNode n) {
	attr = si;
	node = n;
    }

    public SlotInfo getSlotInfo() { return attr; }
    public SlotInfo getAttribute() { return attr; }

    public IRNode getNode() { return node; }

    public Object getValue() throws SlotUndefinedException
    {
	return node.getSlotValue(attr);
    }

    public Slot setValue(Object newValue) throws SlotImmutableException
    {
	node.setSlotValue(attr,newValue);
	return this;
    }

    public boolean isValid()
    {
	return node.valueExists(attr);
    }

    public boolean isChanged()
    {
	if (attr instanceof PersistentSlotInfo) {
	    return ((PersistentSlotInfo)attr).valueChanged(node);
	}
	return false;
    }
}
