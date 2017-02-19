/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ConnectedNodes.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Return all the nodes that can be reached from
 * a root by traversing a symmetric digraph in either direction.
 */
public class ConnectedNodes extends DepthFirstSearch {
  public ConnectedNodes(SymmetricDigraphInterface dig, IRNode root) {
    super(dig,root);
  }
  private IRLocation ploc;

  protected void pushState() {
    super.pushState();
    stack.push(ploc);
  }

  protected void popState() {
    ploc = (IRLocation)stack.pop();
    super.popState();
  }
  
  protected void visit(IRNode n) {
    super.visit(n);
    ploc = ((SymmetricDigraphInterface)digraph).firstParentLocation(n);
  }

  protected boolean additionalChildren(IRNode node) {
    if (ploc == null) return false;
    IRNode newNode = ((SymmetricDigraphInterface)digraph).getParent(node,ploc);
    ploc = ((SymmetricDigraphInterface)digraph).nextParentLocation(node,ploc);
    if (mark(newNode)) {
      visit(newNode);
    }
    return true;
  }
}
