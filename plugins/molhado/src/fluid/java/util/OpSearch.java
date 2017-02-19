// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/util/OpSearch.java,v 1.1 2006/03/21 23:21:00 dig Exp $
package fluid.java.util;

import fluid.ir.IRNode;
import fluid.java.JavaGlobals;
import fluid.java.JavaPromise;
import fluid.java.operator.ClassBodyDeclaration;
import fluid.java.operator.DoStatement;
import fluid.java.operator.Expression;
import fluid.java.operator.ForStatement;
import fluid.java.operator.Statement;
import fluid.java.operator.SwitchStatement;
import fluid.java.operator.TypeDeclInterface;
import fluid.java.operator.WhileStatement;
import fluid.tree.Operator;

// Used to search the parents of a node for a particular kind of parent
public class OpSearch implements JavaGlobals {
  private static IRNode find(OpSearch os, IRNode here, IRNode last) {
    // based on bsi.findRoot
    while (os.continueLoop(here, last)) {
      IRNode rv = os.foundNode(here, last);
      if (rv != null) {
	return rv;
      }
      last = here;
      here = JavaPromise.getParentOrPromisedFor(here);
    }
    return null;    
  }

  public final IRNode find(IRNode here) {
    return find(this, here, null);
  }
  public final IRNode findEnclosing(IRNode here) {
    return find(this, JavaPromise.getParentOrPromisedFor(here), here);
  }

  protected boolean continueLoop(IRNode n, IRNode last) {
    return (n != null);
  }

  protected IRNode foundNode(IRNode n, IRNode last) {
    return (foundNode(n)) ? n : null;
  }

  protected boolean foundNode(IRNode n) {
    Operator op = jtree.getOperator(n);
    return found(op);
  }

  protected boolean found(Operator op) {
    return false;
  }

  /*
  static IRNode findEnclosingOp(Operator op0, IRNode here) {
    // based on bsi.findRoot
    IRNode parent = JavaPromise.getParentOrPromisedFor(here);
    while (parent != null) {
      Operator op = jtree.getOperator(parent); 
      if (op == op0) {
	return parent;
      }
      here = parent;
      parent = JavaPromise.getParentOrPromisedFor(here);
    }
    return null;    
  }
  */

  /// Specific searches
  public static final OpSearch rootSearch = new OpSearch() {
    protected IRNode foundNode(IRNode n, IRNode last) { 
      return (n == null) ? last : null;
    }
    protected boolean continueLoop(IRNode n, IRNode last) {
      return (n != null) || (last != null);
    }
  };
  public static final OpSearch stmtSearch = new OpSearch() {
    protected boolean found(Operator op) { return op instanceof Statement; }
  };
  public static final OpSearch exprSearch = new OpSearch() {
    protected boolean found(Operator op) { return op instanceof Expression; }
  };
  public static final OpSearch typeSearch = new OpSearch() {
    protected boolean found(Operator op) { 
      return op instanceof TypeDeclInterface; 
    }
  };
  public static final OpSearch memberSearch = new OpSearch() {
    protected boolean found(Operator op) { 
      return ClassBodyDeclaration.prototype.includes(op);
    }
  };

  public static final OpSearch breakSearch = new OpSearch() {
    protected boolean found(Operator op) { 
      return 
      (op instanceof ForStatement) ||
      (op instanceof DoStatement) ||
      (op instanceof WhileStatement) ||
      (op instanceof SwitchStatement); 
    }
  };
  public static final OpSearch continueSearch = new OpSearch() {
    protected boolean found(Operator op) { 
      return 
      (op instanceof ForStatement) ||
      (op instanceof DoStatement) ||
      (op instanceof WhileStatement);
    }
  };  
}
