// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Expression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.unparse.Delim;
import fluid.unparse.Token;
/** The class of all Java expression operators.
 */
public class Expression extends Initializer { 
  protected Expression() {}

  public static final Expression prototype = new Expression();

  public Operator superOperator() {
    return Initializer.prototype;
  }


  /* precedences */
  protected static int addLeft = 5;
  protected static int addRight = 6;
  protected static int mulLeft = 7;
  protected static int mulRight = 8;
  protected static int unopPrec = 10;
  
  /** In this wrapper, we add parentheses as necessary. */
  public void unparseWrapper(IRNode node, JavaUnparser u) {
    //! TODO: Fix potential bugs
    //!! Too specific to UnopExpression, won't work with
    //!! CastExpression, or other operators that do not have classic
    //!! form.  Also, what to do with operators that have starting
    //!! keywords (their own parentheses)?
    IRNode parent = tree.getParentOrNull(node);
    boolean done = false;
    if (parent != null && tree.numChildren(node) > 0) {
      JavaOperator pop = (JavaOperator)tree.getOperator(parent);
      if (pop instanceof Expression) {
        Expression pope = (Expression)pop;
	IRLocation loc = tree.getLocation(node);
	int parentPrec = pope.childPrecedence(loc);
	if (parentPrec > 0) { // otherwise no need to parenthesize
	  if (tree.childLocationIndex(parent,loc) == 0 &&
	      (!(pope instanceof UnopExpression) ||
	       !((UnopExpression)pope).isPrefix())) {
	    if (childPrecedence(0) < parentPrec) {
	      parenthesize(node,u);
	      done = true;
	    }
	  } else {
	    if (childPrecedence(tree.numChildren(node)-1) < parentPrec) {
	      parenthesize(node,u);
	      done = true;
	    }
	  }
	}
      }	  
    }
    if (!done) {
      super.unparseWrapper(node,u);
    }
  }
  
  private static Token littoken1 = new Delim("(");
  private static Token littoken2 = new Delim(")");
//  private static Token parenBegin = new OpenClose(true);
//  private static Token parenEnd = new OpenClose(false);

  protected void parenthesize(IRNode node, JavaUnparser u) {
    littoken1.emit(u,node);
//  parenBegin.emit(u,node);
    fluid.unparse.IndepBP.JUXTBP.emit(u,node);    
    super.unparseWrapper(node,u);
    fluid.unparse.IndepBP.JUXTBP.emit(u,node);    
//  parenEnd.emit(u,node);
    littoken2.emit(u,node);
  }
  
  /** Return the binding of this operator to the i'th child. */
  public int childPrecedence(int i) {
    return 0;
  }
  
  /** Return the binding of this operator to the child at location loc.
   *! Warning: this method only works for fixed arity nodes, and
   *! It depends on the order IRArray assigns location identifiers.
   */
  public int childPrecedence(IRLocation loc) {
    return childPrecedence(loc.getID());
  }
}
