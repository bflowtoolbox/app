package sc.document;

import fluid.ir.IRNode;

/**
 * @author Tien
 *
 */
public class SCNonCausalLink extends SCLink {
  public SCNonCausalLink(IRNode n, SCHypertext ht) {
    super(n,ht);
  }
  // Overwritten methods
  public boolean equals(SCNonCausalLink l) {
    if (l == null || l.getIRNode() == null) return false;
    return irnode.equals(l.getIRNode());
  }
  
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SCNonCausalLink)) return false;
    return equals((SCNonCausalLink) o);
  }
}
