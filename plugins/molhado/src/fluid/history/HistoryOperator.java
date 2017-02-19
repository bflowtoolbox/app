// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/HistoryOperator.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import java.util.Hashtable;

import fluid.FluidRuntimeException;
import fluid.ir.IRNode;
import fluid.parse.JJOperator;
import fluid.unparse.Keyword;
import fluid.unparse.OpenClose;
import fluid.unparse.Token;

/** The operator class for all operators used for History IRNodes.
 * It contains a hashtable to map operator names to prototypes.
 * @see HistoryNode
 */
public abstract class HistoryOperator extends JJOperator {
  /** A hashtable from strings to operator names.
   * @type Hashtable[String,HistoryOperator]
   */
  static Hashtable operatorTable = new Hashtable();

  /** Compute a name for an operator.
   * By default this is the class name (with all prefixes removed).
   */
  public String name() {
    String complete = getClass().getName();
    return complete.substring(complete.lastIndexOf('.') + 1);
  }

  public HistoryOperator() {
    // System.out.println("Loaded " + name());

    String n = name();
    operatorTable.put(n, this);
  }

  public static HistoryOperator findOperator(String name) {
    HistoryOperator op = (HistoryOperator) operatorTable.get(name);
    if (op == null) {
      throw new FluidRuntimeException("No operator " + name + " loaded");
    }
    return op;
  }

  /** A routine called indirectly by the parser
   * and which creates a HistoryNode for this operator
   * @see JJOperator#createNode
   */
  public HistoryNode jjtCreate() {
    return new HistoryNode(this);
  }

  private static Token OPENTOKEN = new OpenClose(true);
  private static Token CLOSETOKEN = new OpenClose(false);

  /** This method is called to unparse a node.
   * it is overridden by hand-written code to perform special
   * ations around the basic unparsing actions.
   * @see #unparse
   */
  public void unparseWrapper(IRNode node, HistoryUnparser u) {
    OPENTOKEN.emit(u, node);
    unparse(node, u);
    CLOSETOKEN.emit(u, node);
  }

  /** This method is called to perform the basic
   * unparsing action for a node.
   * It is overridden by each production with automatically
   * generated code that does not call super.
   * In order to insert hand-written changes around unparsings
   * for particular nodes, one must override the "wrapper" method.
   * @see #unparseWrapper
   */
  public void unparse(IRNode node, HistoryUnparser u) {
    /* by default, do nothing */
  }

  private static Token defaultToken = new Keyword("<operator>");

  public Token asToken() {
    return defaultToken;
  }
}
