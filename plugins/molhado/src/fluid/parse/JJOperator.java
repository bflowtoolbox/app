/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/parse/JJOperator.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.parse;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;
import fluid.tree.SyntaxTreeInterface;

/** Operators to be used with parseable syntax trees.
 * @see JJNode
 */
public abstract class JJOperator extends Operator {
  public static final SyntaxTree tree = JJNode.tree;
  public SyntaxTreeInterface tree() { return tree; }
  
  public IRNode createNode() {
    return new JJNode(this);
  }

  public IRNode createNode(SyntaxTreeInterface tree) {
    return new JJNode(tree, this);
  }

  /** Copy a node and all of its attributes and children
   * recursively.  Override to 
   */
  public IRNode copyTree(IRNode node) {
    return copyTree(tree, node);
  }
  public IRNode copyTree(SyntaxTreeInterface tree, IRNode node) {
    if (node == null) return null;
    int num = tree.numChildren(node);
    IRNode newNode = new JJNode(tree.getOperator(node),num);
    if (num != 0) {
      IRLocation loc1 = tree.firstChildLocation(node),
	loc2 = tree.firstChildLocation(newNode);
      while (true) {
	IRNode child = tree.getChild(node,loc1);
	IRNode newChild;
	if (child != null) {
	  JJOperator op = (JJOperator)tree.getOperator(child);
	  newChild = op.copyTree(child);
	} else {
	  newChild = null;
	}
	tree.setChild(newNode,loc2,newChild);
	if (--num == 0) break;
	loc1 = tree.nextChildLocation(node,loc1);
	loc2 = tree.nextChildLocation(newNode,loc2);
      }
    }
    return newNode;
  }

  // Only for direct attributes of this node (not recursive)
  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return isEquivalentNode(tree, n1, n2);
  }
  public boolean isEquivalentNode(SyntaxTreeInterface tree, IRNode n1, IRNode n2) {
    return 
      (this == tree.getOperator(n1)) && 
      (this.getClass() == tree.getOperator(n2).getClass());
  }
}
