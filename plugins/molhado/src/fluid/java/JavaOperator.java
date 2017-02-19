/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaOperator.java,v 1.2 2006/06/19 17:28:20 kashif Exp $ */
package fluid.java;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import fluid.FluidError;
import fluid.FluidRuntimeException;
import fluid.control.Component;
import fluid.ir.Bundle;
import fluid.ir.IRNode;
import fluid.ir.SlotInfo;
import fluid.ir.SlotInfoWrapper;
import fluid.ir.SlotNotRegisteredException;
import fluid.parse.JJOperator;
import fluid.unparse.Keyword;
import fluid.unparse.OpenClose;
import fluid.unparse.Token;
import fluid.util.ImmutableHashOrderSet;

/** The operator class for all operators used for Java IRNodes.
 * It contains a hashtable to map operator names to prototypes.
 * @see JavaNode
 */
public class JavaOperator extends JJOperator {
  /** A hashtable from strings to operator names.
   * @type Hashtable[String,JavaOperator]
   */
  static Hashtable operatorTable = new Hashtable();
  
  /** Compute a name for an operator.
   * By default this is the class name (with all prefixes removed).
   */
  public String name() {
    String complete = getClass().getName();
    return complete.substring(complete.lastIndexOf('.')+1);
  }

  public JavaOperator() {
    // System.out.println("Loaded " + name());
    
    String n = name();
    operatorTable.put(n,this);
  }
  
  public static JavaOperator findOperator(String name) {
    JavaOperator op = (JavaOperator)operatorTable.get(name);
    if (op == null) {
      throw new FluidRuntimeException("No operator " + name + " loaded");
    }
    return op;
  }

  /** A routine called indirectly by the parser
   * and which creates a JavaNode for this operator
   * @see JJOperator#createNode
   */
  public JavaNode jjtCreate() {
    return new JavaNode(this);
  }

  /** This method is called to create a control-flow graph component
   * for the node with this operator.  This method is not called
   * more than once for every IRNode.  It should be overridden
   * by nodes that need to create control-flow graph components.
   */
  public Component createComponent(IRNode node) {
    return null;
  }
  
  private static Token OPENTOKEN = new OpenClose(true);
  private static Token CLOSETOKEN = new OpenClose(false);

  /** This method is called to unparse a node.
   * it is overridden by hand-written code to perform special
   * ations around the basic unparsing actions.
   * @see #unparse
   */
  public void unparseWrapper(IRNode node, JavaUnparser u) {
    OPENTOKEN.emit(u,node);
    JavaPromise.unparsePromises(node,u);
    unparse(node,u);
    CLOSETOKEN.emit(u,node);
  }

  /** This method is called to perform the basic
   * unparsing action for a node.
   * It is overridden by each production with automatically
   * generated code that does not call super.
   * In order to insert hand-written changes around unparsings
   * for particular nodes, one must override the "wrapper" method.
   * @see #unparseWrapper
   */
  public void unparse(IRNode node, JavaUnparser u) {
   /* by default, do nothing */
  }

  // UNPARSE TOKENS
  public boolean isMissingTokensWrapper(IRNode node) {
    return isMissingTokens(node);
  }
  
  public boolean isMissingTokens(IRNode node) {
    return false; // by default
  }
  
  public Vector[] missingTokensWrapper(IRNode node) {
    return missingTokens(node);
  }
  
  public Vector[] missingTokens(IRNode node) {
    return null; // by default
  }
  
  private static Token defaultToken = new Keyword("<operator>");
  
  public Token asToken() {
    return defaultToken;
  }

//  public boolean includes(Operator other) {
//    if (other instanceof Ellipsis) {
//      System.out.println(other+" ?= "+Ellipsis.prototype);
//    }
//    return 
//        super.includes(other) || (other == Ellipsis.prototype) || (this == JavaOperator.prototype);
//  }
//
  // attributes:

  private static Set attributes = null;

  public Set getAttributes() {
    if (attributes == null) {
      // something of a hack since these are private:
      Bundle here = JavaNode.getBundle();
      int num_here = here.getNumAttributes();

      // Multimedia attribute bundle
      Bundle multimediaBundle = sc.xml.MultimediaAttributes.getBundle();
      int num_multimedia = multimediaBundle.getNumAttributes();

      Object[] a = new Object[num_here+num_multimedia+1];
      for (int i=0; i < num_here; ++i) {
	      SlotInfo si = here.getAttribute(i+1);
	      a[i] = new RootNamedSlotInfoWrapper(si);
      }

      try {
	      SlotInfo nodeInfo = SlotInfo.findSlotInfo("JJNode.info");
	      a[num_here] = new RootNamedSlotInfoWrapper(nodeInfo);
      } catch (SlotNotRegisteredException ex) {
	      throw new FluidError("JJNode.info not registered");
      }

      // add multimedia attributes
      for (int i=0; i<num_multimedia; i++) {
        SlotInfo si = multimediaBundle.getAttribute(i+1);
        a[num_here+1+i] = new RootNamedSlotInfoWrapper(si);
      }

      attributes = new ImmutableHashOrderSet(a);
    }
    return attributes;
  }

  public static final JavaOperator prototype = new JavaOperator();
}

class RootNamedSlotInfoWrapper extends SlotInfoWrapper {
  private final String name;
  public RootNamedSlotInfoWrapper(SlotInfo si) {
    super(si);
    name = root(si.name());
  }

  private static String root(String name) {
    int afterDot = name.lastIndexOf('.');
    return name.substring(afterDot+1);
  }

  public String name() { return name; }
} 
