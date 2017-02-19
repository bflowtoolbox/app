// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\DemandName.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class DemandName extends JavaOperator implements ImportNameInterface { 
  protected DemandName() {}

  public static final DemandName prototype = new DemandName();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return null;
  }

  public int numChildren() {
    return 0;
  }

  public static JavaNode createNode(String pkg) {
    return createNode(tree, pkg);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, String pkg) {
    JavaNode _result = new JavaNode(tree, prototype);
    JavaNode.setInfo(_result,pkg);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
    true;
  }

  public static String getPkg(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof DemandName)) {
      throw new IllegalArgumentException("node not DemandName: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getPkg() {
    return JavaNode.getInfo(baseNode);
  }

  private static Token littoken1 = new Delim(".");
  private static Token littoken2 = new Delim("*");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof DemandName)) {
      throw new IllegalArgumentException("node not DemandName: "+op);
    }
    JavaNode.unparseInfo(node,unparser);
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof DemandName)) {
      throw new IllegalArgumentException("node not DemandName: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    TokenList[0].add(littoken1);
    TokenList[0].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
