// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CompilationUnit.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class CompilationUnit extends JavaOperator implements DripOperator { 
  protected CompilationUnit() {}

  public static final CompilationUnit prototype = new CompilationUnit();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return PackageDeclaration.prototype;
    case 1: return ImportDeclarations.prototype;
    case 2: return TypeDeclarations.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(IRNode pkg,
                                    IRNode imps,
                                    IRNode decls) {
    return createNode(tree, pkg, imps, decls);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode pkg,
                                    IRNode imps,
                                    IRNode decls) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{pkg,imps,decls});
    return _result;
  }

  public static IRNode getPkg(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return getPkg(tree, node);
  }

  public PackageDeclaration getPkg() {
    return getPkg(tree);
  }

  public static IRNode getPkg(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return tree.getChild(node,0);
  }

  public PackageDeclaration getPkg(SyntaxTreeInterface tree) {
    return (PackageDeclaration)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getImps(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return getImps(tree, node);
  }

  public ImportDeclarations getImps() {
    return getImps(tree);
  }

  public static IRNode getImps(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return tree.getChild(node,1);
  }

  public ImportDeclarations getImps(SyntaxTreeInterface tree) {
    return (ImportDeclarations)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getDecls(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return getDecls(tree, node);
  }

  public TypeDeclarations getDecls() {
    return getDecls(tree);
  }

  public static IRNode getDecls(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    return tree.getChild(node,2);
  }

  public TypeDeclarations getDecls(SyntaxTreeInterface tree) {
    return (TypeDeclarations)instantiate(tree.getChild(baseNode,2));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
    unparser.unparse(tree.getChild(node,2));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompilationUnit)) {
      throw new IllegalArgumentException("node not CompilationUnit: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    tree.getChild(node,1);
    tree.getChild(node,2);
    return TokenList;
  }

}
