package sc.document;

import fluid.ir.IRNode;

/**
 * @author Tien
 *
 */
public class SCCausalLink extends SCLink {
  
  public SCCausalLink(IRNode n, SCHypertext ht) {
    super(n,ht);
  }
  
  // Overwritten methods
  public boolean equals(SCCausalLink l) {
    if (l == null || l.getIRNode() == null) return false;
    return irnode.equals(l.getIRNode());
  }
  
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SCCausalLink)) return false;
    return equals((SCCausalLink) o);
  }
}
