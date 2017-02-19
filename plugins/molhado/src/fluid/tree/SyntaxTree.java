/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SyntaxTree.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.Bundle;
import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotInfo;

/** A type of tree in which each tree has an operator which determines
 * the kinds of children which are legal.  In essence, this class
 * layers a strong (but dynamically checked) type system on top of
 * the untyped trees implemented in Tree.
 * <P> Known bugs:
 * <ul>
 * <li> The long version of <tt>initNode</tt> that takes a minimum
 *      number of children does not work as advertised for a delegating 
 *	syntax tree:
 *      The operator-determined number of children is used to create the node,
 *	and then new null children are added to reach the desired minumum.
 *	This will be a hard bug to fix (probably requiring simulated
 *	dynamically scoped variables).
 * <li> See bugs for @{link Tree} and @{link Digraph} as well.
 * @see Operator
 */
public class SyntaxTree extends Tree implements SyntaxTreeInterface {
  interface Mutator extends Tree.Mutator {
    public void initNode(IRNode n, Operator op, int min);
  }

  protected Digraph.Mutator createStoredMutator(SlotFactory sf) {
    return new StoredMutator(sf);
  }
  protected Digraph.Mutator createDelegatingMutator() {
    return new DelegatingMutator();
  }

  private /*final*/
  SlotInfo operatorSlotInfo;

  /** Return operator of a tree node.
   * @precondition nonNull(node)
   */
  public Operator getOperator(IRNode node) {
    if (node == null) {
      LOG.error("Node is null in getting operator");
      return (Operator) (node.getSlotValue(operatorSlotInfo));
    }
    return (Operator) (node.getSlotValue(operatorSlotInfo));
  }
  public boolean opExists(IRNode node) {
    return node.valueExists(operatorSlotInfo);
  }

  private void setOperator(IRNode node, Operator operator) {
    node.setSlotValue(operatorSlotInfo, operator);
  }

  public SyntaxTree(String name, SlotFactory sf)
    throws SlotAlreadyRegisteredException {
    super(name, sf);
    if (name == null)
      operatorSlotInfo = sf.newAttribute();
    else
      operatorSlotInfo =
        sf.newAttribute(
          name + ".SyntaxTree.operator",
          IROperatorType.prototype);
  }

  /** Create a syntax tree that delegates to attributes from
   * an existing Syntax Tree.  This syntax tree mutates slots
   * sometimes where a stored tree does not.
   * @see #getAttribute(String)
   */
  public SyntaxTree(
    SlotInfo childrenAttribute,
    SlotInfo parentAttribute,
    SlotInfo locationAttribute,
    SlotInfo operatorAttribute) {
    super(childrenAttribute, parentAttribute, locationAttribute);
    operatorSlotInfo = operatorAttribute;
  }

  /** Create a node for the particular operator.
   * The number of children required is determined from the operator.
   */
  public void initNode(IRNode n, Operator op) {
    initNode(n, op, 0);
  }

  /** Create a node for a particular operator, with minimum number of children
   * NB: no slots are mutated with this code.
   */
  public void initNode(IRNode n, Operator op, int min) {
    ((Mutator) mutator).initNode(n, op, min);
  }

  /** Create a node for a particular operator, with particular children.
   * NB: no slots are mutated with this code.
   */
  public void initNode(IRNode n, Operator op, IRNode[] children) {
    initNode(n, op, children.length);
    for (int i = 0; i < children.length; ++i) {
      setChild(n, i, children[i]);
    }
  }

  class StoredMutator extends Tree.StoredMutator implements Mutator {
    StoredMutator(SlotFactory sf) {
      super(sf);
    }

    /** Create a node with a null operator, an untyped node,
     * with the specified number of children.
     */
    public void initNode(IRNode n, int numChildren) {
      super.initNode(n, numChildren);
      setOperator(n, null);
    }

    /** Create a node for a particular operator,
     * with minimum number of children (relevant only for variable
     * arity nodes).
     * NB: no slots are mutated with this code.
     */
    public void initNode(IRNode n, Operator op, int min) {
      int num = (op == null) ? -1 : op.numChildren();
      if (num < 0) {
        if (~num < min)
          num = ~min;
      }
      super.initNode(n, num);
      setOperator(n, op);
    }

    protected void checkChild(IRNode parent, IRLocation loc, IRNode child)
      throws IllegalChildException {
      Operator op;
      if (opExists(parent)) {
        op = getOperator(parent);
      } else
        return;

      if (op == null)
        return;
      Operator childOp = op.childOperator(loc);
      if (childOp == null)
        throw new IllegalChildException("bad location for a child: " + loc);
      if (child != null) {
        Operator actualOp = getOperator(child);
        if (!childOp.includes(actualOp))
          throw new IllegalChildException(
            "child has wrong operator: expected "
              + childOp.name()
              + " got "
              + (actualOp == null ? "null" : actualOp.name()));
      }
    }

    protected void checkVariableChild(IRNode parent, IRNode child)
      throws IllegalChildException {
      Operator op;
      if (opExists(parent)) {
        op = getOperator(parent);
      } else
        return;

      if (op == null)
        return;
      Operator childOp = op.variableOperator();
      if (childOp == null)
        throw new IllegalChildException(
          op.name() + " node cannot accept variable children");
      if (child != null) {
        Operator actualOp = getOperator(child);
        if (!childOp.includes(actualOp))
          throw new IllegalChildException(
            "child has wrong operator: expected "
              + childOp.name()
              + " got "
              + (actualOp == null ? "null" : actualOp.name()));
      }
    }

    public void saveAttributes(Bundle b) {
      super.saveAttributes(b);
      b.saveAttribute(operatorSlotInfo);
    }

    protected final SlotInfo wrappedOperatorAttribute =
      new WrappedOperatorSlotInfo();

    public SlotInfo getAttribute(String name) {
      if (name == "operator") {
        return wrappedOperatorAttribute;
      } else {
        return super.getAttribute(name);
      }
    }

    class WrappedOperatorSlotInfo extends DerivedSlotInfo {
      protected boolean valueExists(IRNode node) {
        return opExists(node);
      }
      protected Object getSlotValue(IRNode node) {
        return getOperator(node);
      }
      protected void setSlotValue(IRNode node, Object value) {
        initNode(node, (Operator) value, 0);
      }
    }
  }

  class DelegatingMutator extends Tree.DelegatingMutator implements Mutator {
    /** Create a node for a particular operator,
     * with minimum number of children (relevant only for variable
     * arity nodes).
     * <bf>This code is noncompliant and will mutate slots.</bf>
     */
    public void initNode(IRNode n, Operator op, int min) {
      setOperator(n, op); // which sets up children too
      //! now expand as necessary
      //! NB: this mutates slots!
      int num = (op == null) ? -1 : op.numChildren();
      if (num < 0)
        for (int i = ~num; i < min; ++i)
          SyntaxTree.super.appendChild(n, null);
    }
  }
}