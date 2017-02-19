// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ImportDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ImportDeclaration extends JavaOperator { 
  protected ImportDeclaration() {}

  public static final ImportDeclaration prototype = new ImportDeclaration();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return ImportName.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode item) {
    return createNode(tree, item);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode item) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{item});
    return _result;
  }

  public static IRNode getItem(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclaration)) {
      throw new IllegalArgumentException("node not ImportDeclaration: "+op);
    }
    return getItem(tree, node);
  }

  public ImportNameInterface getItem() {
    return getItem(tree);
  }

  public static IRNode getItem(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclaration)) {
      throw new IllegalArgumentException("node not ImportDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public ImportNameInterface getItem(SyntaxTreeInterface tree) {
    return (ImportNameInterface)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Keyword("import");
  private static Token littoken2 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclaration)) {
      throw new IllegalArgumentException("node not ImportDeclaration: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclaration)) {
      throw new IllegalArgumentException("node not ImportDeclaration: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    TokenList[1].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}

