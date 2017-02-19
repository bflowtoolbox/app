// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/util/VisitUtil.java,v 1.1 2006/03/21 23:21:00 dig Exp $
package fluid.java.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import fluid.FluidError;
import fluid.ir.IRNode;
import fluid.java.JavaGlobals;
import fluid.java.JavaNode;
import fluid.java.JavaPromise;
import fluid.java.operator.AnonClassExpression;
import fluid.java.operator.ClassBody;
import fluid.java.operator.ClassDeclaration;
import fluid.java.operator.CompilationUnit;
import fluid.java.operator.DeclStatement;
import fluid.java.operator.FieldDeclaration;
import fluid.java.operator.InterfaceDeclaration;
import fluid.java.operator.NamedPackageDeclaration;
import fluid.java.operator.NestedClassDeclaration;
import fluid.java.operator.NestedInterfaceDeclaration;
import fluid.java.operator.ParameterDeclaration;
import fluid.java.operator.UnnamedPackageDeclaration;
import fluid.parse.JJNode;
import fluid.tree.Operator;
import fluid.util.SimpleEnumeration;

// This is for code that is used to "visit" parts of a tree, given a root
public class VisitUtil implements JavaGlobals {
  /// findRoot : traverse up until no longer possible
  public static IRNode findRoot(IRNode here) {
    return OpSearch.rootSearch.find(here);
  }

  /// rootWalk
  public static Enumeration rootWalk(final IRNode starting) {
    return new Enumeration(){
      IRNode next = starting;
      public boolean hasMoreElements() {
	return next != null;
      }
      public Object nextElement() {
	if (next == null) throw new NoSuchElementException("root walk done");
	try {
	  return next;
	} finally {
	  next = JavaPromise.getParentOrPromisedFor(next);
	}
      }
    };
  }

  /// getEnclosingType - for any node
  public static IRNode getEnclosingType(IRNode here) {
    return OpSearch.typeSearch.findEnclosing(here);
  }

  /// getEnclosingClassBodyDecl - for any node
  public static IRNode getEnclosingClassBodyDecl(IRNode here) {
    return OpSearch.memberSearch.findEnclosing(here);
  }

  /// getEnclosingVarDecls - for any node
  public static IRNode getEnclosingVarDecls(IRNode here) {
    return varDeclsSearch.findEnclosing(here);
  }

  public static final OpSearch varDeclsSearch = new OpSearch() {
    protected boolean found(Operator op) {
      return 
	(op instanceof ParameterDeclaration) ||
	(op instanceof DeclStatement) ||
	(op instanceof FieldDeclaration);
    }
  };

  /// getClassBody - for types
  public static IRNode getClassBody(IRNode decl) {
    Operator op = JavaNode.tree.getOperator(decl);
    if (ClassDeclaration.prototype.includes(op)) {
      return ClassDeclaration.getBody(decl);
    }
    else if (InterfaceDeclaration.prototype.includes(op)) {
      return InterfaceDeclaration.getBody(decl);
    }
    else if (op == AnonClassExpression.prototype) {      
      return AnonClassExpression.getBody(decl);
    }
    throw new FluidError("Getting a class body on a "+op);
  }
  
  public static Enumeration getClassBodyMembers(IRNode decl) {
  	return JavaNode.tree.children(getClassBody(decl));
  }

  /// getTypeDecls - for CUs
  /**
   * Get the top-level types declared in the compilation unit
   */
  public static Enumeration getTypeDecls(IRNode jf) {
    IRNode decls = CompilationUnit.getDecls(jf);
    return jtree.children(decls);
  }

  public static Enumeration getInnerClasses(IRNode root) {
    final IRNode body = VisitUtil.getClassBody(root);

    return new SimpleEnumeration() {
      Enumeration en = ClassBody.getDeclEnumeration(body);
      protected Object computeNextElement() {
        while (en.hasMoreElements()) {
          IRNode n = (IRNode) en.nextElement();
          Operator op = JJNode.tree.getOperator(n);

          if ((op == NestedClassDeclaration.prototype)
            || (op == NestedInterfaceDeclaration.prototype)) {
            return n;
          }
        }
        return noElement;
      }
    };
  }

  public static IRNode findInnerClass(IRNode root, String name) {
    Enumeration en = VisitUtil.getInnerClasses(root);
    while (en.hasMoreElements()) {
      IRNode n = (IRNode) en.nextElement();
      String inner = JJNode.getInfo(n);
      if (inner == name) {
        return n;
      }
    }
    return null;
  }

  /// other stuff
  public static String getPackageName(IRNode cu) {
  	IRNode pkg  = CompilationUnit.getPkg(cu);
  	Operator op = JJNode.tree.getOperator(pkg);
  	if (op instanceof UnnamedPackageDeclaration) {
  		return null;
  	}
  	return NamedPackageDeclaration.getName(pkg);
  }
}








