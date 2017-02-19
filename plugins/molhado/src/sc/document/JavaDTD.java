/* $Header: /usr/local/refactoring/molhadoRef/src/sc/document/JavaDTD.java,v 1.1 2006/03/21 23:20:57 dig Exp $ */

package sc.document;

import sc.xml.IRDTD;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.operator.LoadOperator;
import fluid.tree.Operator;

/** The Java DTD used to describe Java ASTs in the XML world.
 */
public class JavaDTD extends IRDTD {
  public static final JavaDTD prototype = new JavaDTD();

  private LoadOperator magic = new LoadOperator();
  // by having this field, operators are loaded when this is created.

  protected JavaDTD() { super("JavaAST"); }

  public Operator findOperator(String name) {
    return JavaOperator.findOperator(name);
  }

  public Operator getOperator(IRNode ir) {
    return JavaNode.tree.getOperator(ir);
  }

  public boolean  isOperatorOK(Operator o) {
    return (o!=null && (o instanceof JavaOperator));
  }

  public boolean  isIntermediateOperator(Operator o) {
    return (isOperatorOK(o));
  }

  public boolean  isRawOperator(Operator o) {
    return false;
  }

  public Operator getRawTextOperator() {
    return null;
  }

  public String getRawTextOperatorAttrName() {
    return new String("info");
  }
}
