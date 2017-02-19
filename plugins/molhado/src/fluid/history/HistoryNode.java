// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/HistoryNode.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import java.io.PrintStream;
import java.util.Enumeration;

import fluid.ir.Bundle;
import fluid.ir.IRNode;
import fluid.parse.JJNode;
import fluid.tree.SyntaxTreeInterface;

/** The class used to store the history and possibly (un)parse 
 * Not all nodes of History IR (for example, IRNodes stored and then
 * loaded) need have this type.
 * @see HistoryOperator
 */
public class HistoryNode extends JJNode {
  /// constructors
  public HistoryNode(HistoryOperator operator) {
    super(operator);
  }

  /** Constructor for bottom-up tree creation. */
  public HistoryNode(HistoryOperator operator, IRNode[] children) {
    super(operator, children);
  }

  public HistoryNode(SyntaxTreeInterface tree, HistoryOperator operator) {
    super(tree, operator);
  }

  /** Constructor for bottom-up tree creation. */
  public HistoryNode(
    SyntaxTreeInterface tree,
    HistoryOperator operator,
    IRNode[] children) {
    super(tree, operator, children);
  }

  /// unparsing info
  public static void unparse(IRNode node, HistoryUnparser u) {
    //! TODO: add comments
     ((HistoryOperator) tree.getOperator(node)).unparseWrapper(node, u);
  }

  // For each kind of annotation hanging off of it
  //
  // public static void unparseX(IRNode node, HistoryUnparser u) {}

  /** Print a node with History-specific AST information */
  public static String toString(IRNode node) {
    if (node == null) {
      return "null";
    } else {
      StringBuffer sb = new StringBuffer();
      sb.append(JJNode.toString(node));
      return sb.toString();
    }
  }

  /** Dump a tree made up of History AST nodes.
   * We add History-specific AST information.
   * @see JJNode#dumpTree
   */
  public static void dumpTree(PrintStream s, IRNode root, int indent) {
    doindent(s, indent);
    if (root == null) {
      s.println("null");
    } else {
      s.println(toString(root));
      Enumeration children = tree.children(root);
      while (children.hasMoreElements()) {
        IRNode child = (IRNode) children.nextElement();
        dumpTree(s, child, indent + 1);
      }
    }
  }

  /*
   %- Storage methods.
   */

  public static void saveAttributes(Bundle b) {
    // NB: Do not call JJNode.saveAttributes(b);
    // b.saveAttribute(modifiersSlotInfo);
  }

  /*
  public static void main(String args[]) {
    Bundle b = new Bundle();
    saveAttributes(b);
    try {
      b.store(floc);
    } catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Please press return to try again");
      try {
  System.in.read();
  b.store(floc);
      } catch (IOException ex2) {
  System.out.println(ex2.toString());
      }
    }
  }
  */

  private static Bundle historybundle = null;
  /*
    static {
      try {
        historybundle = Bundle.loadBundle(UniqueID.parseUniqueID("historynode"),
  				        IRPersistent.fluidFileLocator);
      } catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
  */

  public static Bundle getBundle() {
    return historybundle;
  }

  public static void main(String args[]) {
    JJNode.main(args);
    Bundle b = getBundle();
    if (b != null)
      b.describe(System.out);
  }
}
