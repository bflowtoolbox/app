// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/operator/DefaultTreeWalker.java,v 1.1 2006/03/21 23:20:53 dig Exp $
package fluid.java.operator;

import java.util.Enumeration;

import fluid.ir.IRNode;
import fluid.tree.Operator;

/** Implementation of <TT>ExtendedTreeWalker</tt> that simply recurses on every
  *  child IRNode at each point using walkTree/Enumeration
  * Roughly equivalent to a topDown() enumeration
  * @see TreeWalker
  * @see ExtendedTreeWalker
  * @author Edwin Chan
  */
public class DefaultTreeWalker extends ExtendedTreeWalker {

  /** Create a new DefaultTreeWalker.
    * @param defVal The value to be returned if the operator is unknown,
    * or if the analysis has nothing to do.  This may not necessarily be
    * useful for all analyses.
    */
  public DefaultTreeWalker(final Object defVal) {
    super(defVal);
  }

  //**********************************************************************
  //**********************************************************************
  // Methods to handle each operator type (from TreeWalker)
  //**********************************************************************
  //**********************************************************************

  public Object arguments(final IRNode root, final Enumeration args) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object arrayCreationExpression(
    final IRNode root,
    final IRNode alloc,
    final IRNode base,
    final IRNode init,
    final int unalloc) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object arrayDeclaration(
    final IRNode root,
    final IRNode base,
    final int dims) {
    return walkTree(base);
  }

  //----------------------------------------------------------------------

  public Object arrayInitializer(
    final IRNode root,
    final Enumeration elements) {
    return walkEnumeration(elements);
  }

  //----------------------------------------------------------------------

  public Object arrayRefExpression(
    final IRNode root,
    final IRNode array,
    final IRNode index) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object assertStatement(final IRNode root, final IRNode assertion) {
    return walkTree(assertion);
  }

  //----------------------------------------------------------------------

  public Object assertMessageStatement(
    final IRNode root,
    final IRNode assertion,
    final IRNode message) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object assignExpression(
    final IRNode root,
    final IRNode lhs,
    final IRNode rhs) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  // Already implemented by ExtendedTreeWalker
  public Object binopExpression(
    final Operator op,
    final IRNode root,
    final IRNode op1,
    final IRNode op2) {
    return binOpExpression(op, root, op1, op2);
  }

  //----------------------------------------------------------------------

  public Object blockStatement(final IRNode root, final Enumeration stmts) {
    return walkEnumeration(stmts);
  }

  //----------------------------------------------------------------------

  public Object breakStatement(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object castExpression(
    final IRNode root,
    final IRNode expr,
    final IRNode type) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object catchClause(
    final IRNode root,
    final IRNode body,
    final IRNode param) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object catchClauses(final IRNode root, final Enumeration clauses) {
    return walkEnumeration(clauses);
  }

  //----------------------------------------------------------------------

  public Object charLiteral(final IRNode root, final Object token) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object classBody(final IRNode root, final Enumeration decls) {
    return walkEnumeration(decls);
  }

  //----------------------------------------------------------------------

  public Object classDeclaration(
    final IRNode root,
    final IRNode body,
    final IRNode extension,
    final Object id,
    final IRNode impls,
    final int mods) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object classExpression(final IRNode root, final IRNode type) {
    return walkTree(type);
  }

  //----------------------------------------------------------------------

  public Object classInitializer(
    final IRNode root,
    final IRNode block,
    final int mods) {
    return walkTree(block);
  }

  //----------------------------------------------------------------------

  public Object compilationUnit(
    final IRNode root,
    final IRNode decls,
    final IRNode imps,
    final IRNode pkg) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object complementExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object conditionalExpression(
    final IRNode root,
    final IRNode cond,
    final IRNode ifTrue,
    final IRNode ifFalse) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object constantLabel(final IRNode root, final IRNode op) {
    return walkTree(op);
  }

  //----------------------------------------------------------------------

  public Object constructorCall(
    final IRNode root,
    final IRNode object,
    final IRNode args) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object constructorDeclaration(
    final IRNode root,
    final IRNode block,
    final IRNode exceptions,
    final int mods,
    final Object name,
    final IRNode params) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object continueStatement(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object declStatement(
    final IRNode root,
    final int mods,
    final IRNode type,
    final IRNode vars) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object defaultLabel(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object demandName(final IRNode root, final String pkg) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object dimExprs(final IRNode root, final Enumeration dims) {
    return walkEnumeration(dims);
  }

  //----------------------------------------------------------------------

  public Object dims(final IRNode root, final int dims) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object doStatement(
    final IRNode root,
    final IRNode cond,
    final IRNode loop) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object emptyStatement(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object exprStatement(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object extensions(final IRNode root, final Enumeration classes) {
    return walkEnumeration(classes);
  }

  //----------------------------------------------------------------------

  public Object falseExpression(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  // special
  public Object fieldDeclaration(
    final IRNode root,
    final int mods,
    final IRNode type,
    final IRNode vars) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object fieldRef(
    final IRNode root,
    final IRNode object,
    final Object id) {
    return walkTree(object);
  }

  //----------------------------------------------------------------------

  public Object finallyClause(final IRNode root, final IRNode body) {
    return walkTree(body);
  }

  //----------------------------------------------------------------------

  public Object floatLiteral(final IRNode root, final Object token) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object forStatement(
    final IRNode root,
    final IRNode init,
    final IRNode cond,
    final IRNode update,
    final IRNode loop) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object ifElseStatement(
    final IRNode root,
    final IRNode cond,
    final IRNode thenPart,
    final IRNode elsePart) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object ifStatement(
    final IRNode root,
    final IRNode cond,
    final IRNode thenPart) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object implementsList(
    final IRNode root,
    final Enumeration interfaces) {
    return walkEnumeration(interfaces);
  }

  //----------------------------------------------------------------------

  public Object importDeclaration(final IRNode root, final IRNode item) {
    return walkTree(item);
  }

  //----------------------------------------------------------------------

  public Object importDeclarations(
    final IRNode root,
    final Enumeration imports) {
    return walkEnumeration(imports);
  }

  //----------------------------------------------------------------------

  public Object initialization(final IRNode root, final IRNode value) {
    return walkTree(value);
  }

  //----------------------------------------------------------------------

  public Object instanceOfExpression(
    final IRNode root,
    final IRNode val,
    final IRNode type) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object intLiteral(final IRNode root, final Object token) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object interfaceDeclaration(
    final IRNode root,
    final IRNode body,
    final IRNode extensions,
    final Object id,
    final int mods) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object labeledBreakStatement(final IRNode root, final Object id) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object labeledContinueStatement(final IRNode root, final Object id) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object labeledStatement(
    final IRNode root,
    final Object label,
    final IRNode stmt) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object methodBody(final IRNode root, final IRNode block) {
    return walkTree(block);
  }

  //----------------------------------------------------------------------

  public Object methodCall(
    final IRNode root,
    final IRNode args,
    final Object method,
    final IRNode obj) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object methodDeclaration(
    final IRNode root,
    final IRNode body,
    final int dims,
    final IRNode exceptions,
    final Object id,
    final int mods,
    final IRNode params,
    final IRNode returnType) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object minusExpression(final IRNode root, final IRNode op) {
    return walkTree(op);
  }

  //----------------------------------------------------------------------

  public Object name(final IRNode root, String names) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object nestedClassDeclaration(
    final IRNode root,
    final IRNode body,
    final IRNode extension,
    final Object id,
    final IRNode impls,
    int mods) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object nestedInterfaceDeclaration(
    final IRNode root,
    final IRNode body,
    final IRNode extensions,
    final Object id,
    int mods) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object newExpression(
    final IRNode root,
    final IRNode args,
    final IRNode type) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object noArrayInitializer(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object noClassBody(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object noFinally(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object noInitialization(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object noMethodBody(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object notExpression(final IRNode root, final IRNode op) {
    return walkTree(op);
  }

  //----------------------------------------------------------------------

  public Object nullLiteral(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object opAssignExpression(
    final IRNode root,
    final IRNode lhs,
    final IRNode rhs,
    final Operator op) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object parameterDeclaration(
    final IRNode root,
    final int dims,
    final Object id,
    final int mods,
    final IRNode type) {
    return walkTree(type);
  }

  //----------------------------------------------------------------------

  public Object parameters(final IRNode root, final Enumeration params) {
    return walkEnumeration(params);
  }

  //----------------------------------------------------------------------

  public Object plusExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object postDecrementExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object postIncrementExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object preDecrementExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object preIncrementExpression(final IRNode root, final IRNode expr) {
    return walkTree(expr);
  }

  //----------------------------------------------------------------------

  public Object qualifiedAllocationExpression(
    final IRNode root,
    final IRNode alloc,
    final IRNode type) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object qualifiedName(
    final IRNode root,
    final Object id,
    final IRNode pkg) {
    return walkTree(pkg);
  }

  //----------------------------------------------------------------------

  public Object qualifiedThisExpression(final IRNode root, final IRNode type) {
    return walkTree(type);
  }

  //----------------------------------------------------------------------

  public Object returnStatement(final IRNode root, final IRNode val) {
    return walkTree(val);
  }

  //----------------------------------------------------------------------

  public Object simpleName(final IRNode root, final Object id) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object statementExpressionList(
    final IRNode root,
    final Enumeration stmts) {
    return walkEnumeration(stmts);
  }

  //----------------------------------------------------------------------

  public Object stringLiteral(final IRNode root, final Object token) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object superExpression(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object switchBlock(final IRNode root, final Enumeration elements) {
    return walkEnumeration(elements);
  }

  //----------------------------------------------------------------------

  public Object switchElement(
    final IRNode root,
    final IRNode label,
    final IRNode stmts) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object switchStatement(
    final IRNode root,
    final IRNode block,
    final IRNode expr) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object switchStatements(final IRNode root, final Enumeration stmts) {
    return walkEnumeration(stmts);
  }

  //----------------------------------------------------------------------

  public Object synchronizedStatement(
    final IRNode root,
    final IRNode block,
    final IRNode lock) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object thisExpression(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object throwStatement(final IRNode root, final IRNode val) {
    return walkTree(val);
  }

  //----------------------------------------------------------------------

  public Object throwsList(final IRNode root, final Enumeration types) {
    return walkEnumeration(types);
  }

  //----------------------------------------------------------------------

  public Object trueExpression(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object tryStatement(
    final IRNode root,
    final IRNode block,
    final IRNode catchPart,
    final IRNode finallyPart) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object typeDeclarations(final IRNode root, final Enumeration types) {
    return walkEnumeration(types);
  }

  //----------------------------------------------------------------------

  public Object typeExpression(final IRNode root, final IRNode type) {
    return walkTree(type);
  }

  //----------------------------------------------------------------------

  public Object unnamedPackageDeclaration(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object useExpression(final IRNode root, final Object id) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object variableDeclaration(final IRNode root, final Object id) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object variableDeclarator(
    final IRNode root,
    final Object id,
    final int dims,
    final IRNode init) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------

  public Object variableDeclarators(
    final IRNode root,
    final Enumeration vars) {
    return walkEnumeration(vars);
  }

  //----------------------------------------------------------------------

  public Object voidReturnStatement(final IRNode root) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object whileStatement(
    final IRNode root,
    final IRNode cond,
    final IRNode loop) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------
  //----------------------------------------------------------------------
  //----------------------------------------------------------------------

  public Object compiledMethodBody(final IRNode root, final Object code) {
    return defaultValue;
  }

  //----------------------------------------------------------------------

  public Object anonClassExpression(
    final IRNode root,
    final IRNode args,
    final IRNode body,
    final IRNode type) {
    return walkEnumeration(tree.children(root));
  }

  //----------------------------------------------------------------------
  //----Stuff from ExtendedTreeWalker
  //----------------------------------------------------------------------

  public Object addExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object divExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object remExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object mulExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object subExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object conditionalAndExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object conditionalOrExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object andExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object orExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object xorExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object greaterThanEqualExpression(
    IRNode root,
    IRNode op1,
    IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object greaterThanExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object lessThanEqualExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object LessThanExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object eqExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object notEqExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object leftShiftExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object rightShiftExpression(IRNode root, IRNode op1, IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
  public Object unsignedRightShiftExpression(
    IRNode root,
    IRNode op1,
    IRNode op2) {
    return walkEnumeration(tree.children(root));
  }
}
