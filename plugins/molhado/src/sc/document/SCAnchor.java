package sc.document;

import fluid.ir.IRNode;

// import sc.document.*;

/**
 * @author Tien
 */
public class SCAnchor {
  
  SCHypertext hypertext;
  IRNode irnode;
  
  // Constructor 
  public SCAnchor(IRNode n, SCHypertext ht) {
    irnode = n;
    hypertext = ht;    
  }
  
  // Get the name of this anchor
  public String getName() {
    return hypertext.getHTNodeName(irnode);
  }
  
  // Get the document node which this anchor points to.
  public IRNode getDocumentNode() {
    return hypertext.getDocumentNode(this);
  }
  
  // Get the hypertext
  public SCHypertext getHypertext() {
  	return hypertext;
  }
  
  public IRNode getIRNode() {
    return irnode;
  }
  
  // Overwritten methods
  public boolean equals(SCAnchor a) {
    if (a == null || a.getIRNode() == null) return false;
    return irnode.equals(a.getIRNode());
  }
  
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SCAnchor)) return false;
    return equals((SCAnchor) o);
  }
  
  public int hashCode() {
    return irnode.identity().hashCode();
  }
}
