/*
 * Created on Jun 25, 2003
 *
 */
package sc.document;

import fluid.ir.IRNode;

/**
 * @author tien
 *
 */
public abstract class SCLink {
	
  IRNode      irnode;
	SCHypertext hypertext;
	
  public SCLink(IRNode n, SCHypertext ht) {
    irnode = n;
  	hypertext = ht;
  }
  
  public String getName() {
  	return (String) hypertext.getHTNodeName(irnode);
  }
  
  public IRNode getIRNode() { return irnode;}
  public SCHypertext getHypertext() { return hypertext;}
  
  // Overwritten methods
  
  public int hashCode() {
    return irnode.identity().hashCode();
  }
}
