package fluid.java.operator;

import fluid.ir.IRNode;
import fluid.tree.Operator;

/** Extension of <TT>TreeWalker</tt> that overrides <tt>binOpExpression</tt>
  *  to branch on the different binary operators.
  * @see TreeWalker
  * @author Aaron Greenhouse
  */
public abstract class ExtendedTreeWalker extends TreeWalker {

  /** Create a new ExtendedTreeWalker.
    * @param defVal The value to be returned if the operator is unknown,
    * or if the analysis has nothing to do.  This may not necessarily be
    * useful for all analyses.
    */
  public ExtendedTreeWalker(final Object defVal) {
    super(defVal);
  }

  /** An expression using a binary operator.  Branches on the operator
    * to call one of the new operator specific methods.
    * @param op The operator being used
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    * @return <tt>defaultValue</tt> if the operator is unknown
    */
  public Object binOpExpression(
    final Operator op,
    final IRNode root,
    final IRNode op1,
    final IRNode op2) {
    if (op == AddExpression.prototype)
      return this.addExpression(root, op1, op2);
    else if (op == DivExpression.prototype)
      return this.divExpression(root, op1, op2);
    else if (op == RemExpression.prototype)
      return this.remExpression(root, op1, op2);
    else if (op == MulExpression.prototype)
      return this.mulExpression(root, op1, op2);
    else if (op == SubExpression.prototype)
      return this.subExpression(root, op1, op2);
    else if (op == ConditionalAndExpression.prototype)
      return this.conditionalAndExpression(root, op1, op2);
    else if (op == ConditionalOrExpression.prototype)
      return this.conditionalOrExpression(root, op1, op2);
    else if (op == AndExpression.prototype)
      return this.andExpression(root, op1, op2);
    else if (op == OrExpression.prototype)
      return this.orExpression(root, op1, op2);
    else if (op == XorExpression.prototype)
      return this.xorExpression(root, op1, op2);
    else if (op == GreaterThanEqualExpression.prototype)
      return this.greaterThanEqualExpression(root, op1, op2);
    else if (op == GreaterThanExpression.prototype)
      return this.greaterThanExpression(root, op1, op2);
    else if (op == LessThanEqualExpression.prototype)
      return this.lessThanEqualExpression(root, op1, op2);
    else if (op == LessThanExpression.prototype)
      return this.LessThanExpression(root, op1, op2);
    else if (op == EqExpression.prototype)
      return this.eqExpression(root, op1, op2);
    else if (op == NotEqExpression.prototype)
      return this.notEqExpression(root, op1, op2);
    else if (op == LeftShiftExpression.prototype)
      return this.leftShiftExpression(root, op1, op2);
    else if (op == RightShiftExpression.prototype)
      return this.rightShiftExpression(root, op1, op2);
    else if (op == UnsignedRightShiftExpression.prototype)
      return this.unsignedRightShiftExpression(root, op1, op2);
    else
      return defaultValue;
  }

  /** <tt>+</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object addExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>/</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object divExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>%</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object remExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>*</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object mulExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>-</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object subExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>&&</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object conditionalAndExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>||</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object conditionalOrExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>&</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object andExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>|</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object orExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>^</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object xorExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt><=</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object greaterThanEqualExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>></tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object greaterThanExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt><=</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object lessThanEqualExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt><</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object LessThanExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>==</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object eqExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt>!=</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object notEqExpression(IRNode root, IRNode op1, IRNode op2);

  /** <tt><<</tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object leftShiftExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>>></tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object rightShiftExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);

  /** <tt>>>></tt> operator
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  public abstract Object unsignedRightShiftExpression(
    IRNode root,
    IRNode op1,
    IRNode op2);
}
