/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/AbstractProxyNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** Placeholders for other IRNodes.
 * These nodes do not have identity in themselves, but instead defer
 * to another node for slot storage.
 * @see ProxyNode
 */
public abstract class AbstractProxyNode implements IRNode {
  /** return the IRNode that this node is the proxy for. */
  protected abstract IRNode getIRNode();
  
  public Object identity() {
    return getIRNode().identity();
  }

  public boolean equals(Object other) {
    return getIRNode().equals(other);
  }

  public int hashCode() {
    return getIRNode().hashCode();
  }

  public Object getSlotValue(SlotInfo si) throws SlotUndefinedException {
    return getIRNode().getSlotValue(si);
  }

  public void setSlotValue(SlotInfo si, Object newValue) 
      throws SlotImmutableException
  {
    getIRNode().setSlotValue(si,newValue);
  }

  public int getIntSlotValue(SlotInfo si) throws SlotUndefinedException {
    return getIRNode().getIntSlotValue(si);
  }

  public void setSlotValue(SlotInfo si, int newValue) 
       throws SlotImmutableException
  {
    getIRNode().setSlotValue(si,newValue);
  }
  
  public boolean valueExists(SlotInfo si) {
    return getIRNode().valueExists(si);
  }

  public String toString() {
    return "AbstractProxyNode(" + getIRNode() + ")";
  }
}
