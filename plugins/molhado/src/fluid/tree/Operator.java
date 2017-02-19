/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/Operator.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import fluid.FluidRuntimeException;
import fluid.ir.IRInput;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.SlotInfo;
import fluid.util.ImmutableHashOrderSet;

/** Operators contain information about the node, essentially
 * identifying nonterminals and production in the abstract grammar.
 * Operators are declared in a hierarchy that corresponds with the
 * Java hierarchy.  Each operator is a class with a single instance
 * (named "prototype").  All operator classes should be concrete,
 * even those representing nonterminals, because they are needed
 * to specify allowable nodes for children.
 *
 * <p> For example, for Java, we expect the following operators
 * (among many others):
 * <pre>
 *    JavaOperator
 *    Expression
 *    BinopExpression
 *    AndExpression
 * </pre>
 * Only the last operator would be used in nodes with children.
 * (The others may appear as operators of place-filling nodes).
 * </p>
 *
 * <p> We have recently added the capability to <em>instantiate</em>
 * an operator with a particular node.  This permits a syntax tree to be
 * traversed where each node has the type of its oeprator.  Essentially
 * an instantiation is a copy of the operator for a particular node. </p>
 *
 * <p> Since operator classes have a specific form, they can
 * be generated from concise descriptions.  See the script
 * in <tt>lib/perl/create-operator</tt>. </p>
 */
public abstract class Operator implements Cloneable {
  /** Return a textual string naming the abstract nonterminal or production
   * @functional
   */
  public abstract String name();

  /** Return the superoperator for this operator.
   * @functional
   */
  public Operator superOperator() {
    return null;
  }

  /** Return the syntax tree type associated with this operator.
   */
  public abstract SyntaxTreeInterface tree();
  
  /** Create a new node with this operator and with the correct
   * shape of children.
   * @postcondition unique(return)
   */
  public abstract IRNode createNode();
  public abstract IRNode createNode(SyntaxTreeInterface tree);

  /** Return true if this operator is the same as this
   * or if it represents a suboperator.
   * @functional
   */
  public boolean includes(Operator other) {
    // System.out.println("Got "+other);
    while (other != null) {
      if (other == this) return true;
      other = other.superOperator();
    }
    return false;
  }
    
  /** Return true if this node represents a "real"
   * node in the abstract syntax or not.
   */
  public boolean isProduction() {
    return false;
  }

  /** Return the class of nodes acceptable to nodes of this operator,
   * or null if a nonterminal or leaf production.
   * @functional
   */
  public Operator childOperator(int i) {
    return null;
  }
  /** Return the class of nodes acceptable to nodes of this operator,
   * or null if a nonterminal or leaf production.
   * @functional
   */
  public Operator childOperator(IRLocation loc) {
    return childOperator(loc.getID());
  }

  /** Return the class of nodes acceptable to nodes of this operator,
   * if it is a node with variable number of children (otherwise null).
   * @functional
   */
  public Operator variableOperator() {
    return null;
  }
  
  /** Return the number of children, or a negative number
   * if the operator takes avariable number of children.
   */
  public int numChildren() {
    return 0;
  }

  /* We make the clone method private to prevent random clone's */
  private Operator privateClone() {
    try {
      return (Operator)super.clone();
    } catch (CloneNotSupportedException e) {
      return null; // never happens
    }
  }
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Do not clone operators directly");
  }

  protected IRNode baseNode = null;

  /** Return a copy of an operator for a particular node of the tree.
   * (The method is an instance method so that it can be overridden.)
   */
  public Operator instantiate(IRNode node) {
    return instantiate(tree(), node);
  }
  public Operator instantiate(SyntaxTreeInterface tree, IRNode node) {
    if (node == null) return null;
    Operator copied = tree.getOperator(node).privateClone();
    copied.baseNode = node;
    return copied;
  }

  /** Return an enumeration of nodes, each of which should be instantiated.
   */
  public Enumeration instantiate(final Enumeration en) {
    return instantiate(tree(),en);
  } 
  public Enumeration instantiate(final SyntaxTreeInterface tree,
				 final Enumeration en) {
    return new Enumeration() {
      public boolean hasMoreElements() { return en.hasMoreElements(); }
      public Object nextElement() throws NoSuchElementException {
	return instantiate(tree, (IRNode)en.nextElement());
      }
    };
  }

  public IRNode getBaseNode() {
    return baseNode;
  }

  private static final Hashtable allOperators = new Hashtable();

  public Operator() {
    allOperators.put(internalName(),this);
  }
  final String internalName() {
    return getClass().getName();
  }
  static Operator findOperatorInternal(String name) {
    Operator op = (Operator)allOperators.get(name);
    if (op == null) {
      try {
        Class.forName(name);
	op = (Operator)allOperators.get(name);
      } catch (ClassNotFoundException e) {
      }
    }
    if (op == null) {
      throw new FluidRuntimeException("Operator not found for " + name);
    }
    return op;
  }

  /** Write information about this specific instance.
   * If all operators of the same class are identical,
   * this method need not do anything.
   * @see #readInstance
   */
  protected void writeInstance(IROutput out)
  {
    // do nothing
  }

  /** Read information about an instance and return an
   * instance with the information.  If all operators of the same
   * class are identical, this method can simply return 'this.'
   * @see #writeInstance
   */
  protected Operator readInstance(IRInput in)
  {
    return this;
  }

  // help for attributes on nodes:

  /** Return set of attributes that may be set for
   * nodes with this operator.
   * @see #getAttribute
   */
  public java.util.Set getAttributes() {
    return ImmutableHashOrderSet.empty;
  }

  /** Return an attribute given a name.
   * The attribute may or may not be set on nodes with
   * this operator.
   * @return null or slot info for this name.
   * @param name (case insensitive) of attribute.
   */
  public SlotInfo getAttribute(String name) {
    java.util.Iterator attrs = getAttributes().iterator();
    while (attrs.hasNext()) {
      SlotInfo attr = (SlotInfo)attrs.next();
      if (attr.name().equalsIgnoreCase(name)) return attr;
    }
    return null;
  }

  /** Return set of attribute names that may be set for
   * nodes with this operator (for compatibility with Models).
   * @see #getAttribute
   */
  public java.util.Set getAttributeNames() {
    return ImmutableHashOrderSet.empty;
  }

  /** Return an attribute given a name.
   * The attribute may or may not be set on nodes with
   * this operator.
   * @return null or slot info for this name.
   * @param name of attribute.
   */
  public SlotInfo getAttribute(SyntaxTreeInterface tree, String name) {
    return getAttribute(name);
  }

  /** Return true if the node has all children required and
   * if all required attributes are set.
   * This default method only works if no attributes are required.
   */
  public boolean isComplete(IRNode node) {
    return isComplete(tree(), node);
  }

  public boolean isComplete(SyntaxTreeInterface t, IRNode node) {
    int ch = numChildren();
    if (ch < 0) ch = ~ch;
    for (int i=0; i < ch; ++i) {
      if (!t.hasChild(node,i)) return false;
    }
    return true;
  }
}


