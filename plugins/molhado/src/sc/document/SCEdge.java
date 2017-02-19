package sc.document;

import fluid.ir.IRNode;

/**
 * @author Tien
 */
public class SCEdge {
  
  SCHypertext hypertext;
  IRNode      irnode;
  
  public SCEdge(IRNode n, SCHypertext ht) {
    irnode = n;
    hypertext = ht;
  }
  
  public IRNode getIRNode() { return irnode;}
  public SCHypertext getHypertext() { return hypertext;}
  
  // Overwritten methods
  public boolean equals(SCEdge e) {
    if (e == null || e.getIRNode() == null) return false;
    return irnode.equals(e.getIRNode());
  }
  
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SCEdge)) return false;
    return equals((SCEdge) o);
  }
  
  public int hashCode() {
    return irnode.identity().hashCode();
  }
}
