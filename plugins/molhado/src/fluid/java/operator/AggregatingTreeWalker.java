package fluid.java.operator;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;

/** 
 * Abstract class for syntax-driven Set-valued analysis over a 
 * Java parse tree.  The <tt>walkTree()</tt> method dispatches control 
 * to the appropriate abstract method based on the class of the node's
 * operator.  Operator types that have subclasses, or relate to typing
 * information are ignored.  The one exception to this is
 * <tt>BinopExpression</tt>, which does have an abstract method.
 * A single set object is operated on during the tree walk, with methods
 * adding or removing items from the set.
 * 
 * @author Aaron Greenhouse 
 */
public abstract class AggregatingTreeWalker
{
  protected static final SyntaxTree tree = JavaNode.tree;

  /** Create a new TreeWalker.
    */
  public AggregatingTreeWalker() { }

  /**
   * Convienence method; get the operator of a node.
   * @return The operator of a node.
   */
  protected final Operator getOperator( final IRNode node )
  {
    return tree.getOperator( node );
  }

  /**
   * Perform the analysis on a given node.  
   * @param root The node to be analyzed
   * @return An unmodifiable set holding the results of the analysis.
   */
  public final Set walkTree( final IRNode root )
  {
    final Set result = new HashSet();
    this.walkTree( result, root );
    return Collections.unmodifiableSet( result );
  }
  

  /**
   * Walk the tree from inside the analysis.  Not to be used by clients of the
   * analysis, which should invoke {@link #walkTree( IRNode )}.
   * 
   * @param result The set that holds the current analysis results.
   * @param root The node to be analyzed
   */
  protected final void walkTree( final Set result, final IRNode root )
  {
    final Operator rootOp = tree.getOperator( root );

    // 13 Mar 2000, test for Binop moved to the very end do to
    // changes made in the hierarchy of operators that caused
    // Assignment to match binop and therefore return only
    // read effects!
    
    // AddExpression instanceof BinopExpression
    // Skip AllocationExpression because it has subclasses
    // AndExpression instanceof BinopExpression
    if( rootOp == Arguments.prototype ) {
      final Enumeration args = Arguments.getArgEnumeration( root );
      this.arguments( result, root, args );
    }
    // Skipping ArithBinopExpression because it has subclasses
    // Skipping ArithUnopExpression because it has subclasses
    else if( rootOp == ArrayCreationExpression.prototype ) {
      final IRNode alloc = ArrayCreationExpression.getAllocated( root );
      final IRNode base = ArrayCreationExpression.getBase( root );
      final IRNode init = ArrayCreationExpression.getInit( root );
      final int unalloc = ArrayCreationExpression.getUnallocated( root );
      this.arrayCreationExpression( result, root, alloc, base, init, unalloc );
    } else if( rootOp == ArrayDeclaration.prototype ) {
      final IRNode base = ArrayDeclaration.getBase( root );
      final int dims = ArrayDeclaration.getDims( root );
      this.arrayDeclaration( result, root, base, dims );
    } else if( rootOp == ArrayInitializer.prototype ) {
      final Enumeration elements = ArrayInitializer.getChild0Enumeration( root );
      this.arrayInitializer( result, root, elements );
    } else if( rootOp == ArrayRefExpression.prototype ) {
      final IRNode array = ArrayRefExpression.getArray( root );
      final IRNode index = ArrayRefExpression.getIndex( root );
      this.arrayRefExpression( result, root, array, index );
    }
    // Skipping ArrayType, not sure if it is useful
    else if( rootOp == AssertMessageStatement.prototype ) {
      final IRNode assertion = AssertMessageStatement.getAssertion( root );
      final IRNode message = AssertMessageStatement.getMessage( root );
      this.assertMessageStatement( result, root, assertion, message );
    } else if( rootOp == AssertStatement.prototype ) {
      final IRNode assertion = AssertStatement.getAssertion( root );
      this.assertStatement( result, root, assertion );
    } else if( rootOp == AssignExpression.prototype ) {
      final IRNode lhs = AssignExpression.getLhs( root );
      final IRNode rhs = AssignExpression.getRhs( root );
      this.assignExpression( result, root, lhs, rhs );
    }
    // Skipping Assignment because it has subclasses
    // Skipping BinopExpression because it has subclasses
    else if( rootOp == BlockStatement.prototype ) {
      final Enumeration stmts = BlockStatement.getStmtEnumeration( root );
      this.blockStatement( result, root, stmts );
    }
    // Skipping BooleanLiteral
    // Skipping BooleanType, not sure if it is useful
    else if( rootOp == BreakStatement.prototype ) {
      this.breakStatement( result, root );
    }
    // Skipping ByteType, not sure if it is useful
    else if( rootOp == CastExpression.prototype ) {
      final IRNode expr = CastExpression.getExpr( root );
      final IRNode type = CastExpression.getType( root );
      this.castExpression( result, root, expr, type );
    } else if( rootOp == CatchClause.prototype ) {
      final IRNode body = CatchClause.getBody( root );
      final IRNode param = CatchClause.getParam( root );
      this.catchClause( result, root, body, param );
    } else if( rootOp == CatchClauses.prototype ) {
      final Enumeration clauses = CatchClauses.getCatchClauseEnumeration( root );
      this.catchClauses( result, root, clauses );
    } else if( rootOp == CharLiteral.prototype ) {
      final Object token = CharLiteral.getToken( root );
      this.charLiteral( result, root, token );
    }
    // Skipping CharType, not sure if it is useful
    else if( rootOp == ClassBody.prototype ) {
      final Enumeration decls = ClassBody.getDeclEnumeration( root );
      this.classBody( result, root, decls );
    }
    // Skipping ClassBodyDeclaration because it has subclasses
    else if( rootOp == ClassDeclaration.prototype ) {
      final IRNode body = ClassDeclaration.getBody( root );
      final IRNode extension = ClassDeclaration.getExtension( root );
      final Object id = ClassDeclaration.getId( root );
      final IRNode impls = ClassDeclaration.getImpls( root );
      final int mods = ClassDeclaration.getMods( root );
      this.classDeclaration( result, root, body, extension, id, impls, mods );
    } else if( rootOp == ClassExpression.prototype ) {
      final IRNode type = ClassExpression.getType( root );
      this.classExpression( result, root, type );
    } else if( rootOp == ClassInitializer.prototype ) {
      final IRNode block = ClassInitializer.getBlock( root );
      final int mods = ClassInitializer.getModifiers( root );
      this.classInitializer( result, root, block, mods );
    }
    // Skipping CompareExpression  because it has subclasses
    else if( rootOp == CompilationUnit.prototype ) {
      final IRNode decls = CompilationUnit.getDecls( root );
      final IRNode imps = CompilationUnit.getImps( root );
      final IRNode pkg = CompilationUnit.getPkg( root );
      this.compilationUnit( result, root, decls, imps, pkg );
    } else if( rootOp == CompiledMethodBody.prototype ) {
      final Object code = JavaNode.getCode( root );
      this.compiledMethodBody( result, root, code );
    } else if( rootOp == ComplementExpression.prototype ) {
      final IRNode expr = ComplementExpression.getOp( root );
      this.complementExpression( result, root, expr );
    }
    // ConditionalAndExpression instanceof BinopExpression
    else if( rootOp == ConditionalExpression.prototype ) {
      final IRNode cond = ConditionalExpression.getCond( root );
      final IRNode ifFalse = ConditionalExpression.getIffalse( root );
      final IRNode ifTrue = ConditionalExpression.getIftrue( root );
      this.conditionalExpression( result, root, cond, ifTrue, ifFalse );
    }    
    // ConditionalOrExpression instanceof BinopExpression
    else if( rootOp == ConstantLabel.prototype ) {
      final IRNode expr = ConstantLabel.getExpr( root );
      this.constantLabel( result, root, expr );
    }
    // Skipping ConstructionObject because it has subclasses
    else if( rootOp == ConstructorCall.prototype ) {
      final IRNode object = ConstructorCall.getObject( root );
      final IRNode args = ConstructorCall.getArgs( root );
      this.constructorCall( result, root, object, args );
    } else if( rootOp == ConstructorDeclaration.prototype ) {
      final IRNode body = ConstructorDeclaration.getBody( root );
      final IRNode exceptions = ConstructorDeclaration.getExceptions( root );
      final int mods = ConstructorDeclaration.getModifiers( root );
      final Object name = ConstructorDeclaration.getName( root );
      final IRNode params = ConstructorDeclaration.getParams( root );
      this.constructorDeclaration(
        result, root, body, exceptions, mods, name, params );
    } else if( rootOp == ContinueStatement.prototype ) {
      this.continueStatement( result, root );
    }
    // Skipping CrementExpression because it has subclasses
    // Skipping Declaration because it has subclasse
    else if( rootOp == DeclStatement.prototype ) {
      final int mods = DeclStatement.getMods( root );
      final IRNode type = DeclStatement.getType( root );
      final IRNode vars = DeclStatement.getVars( root );
      this.declStatement( result, root, mods, type, vars );
    } else if( rootOp == DefaultLabel.prototype ) {
      this.defaultLabel( result, root );
    } else if( rootOp == DemandName.prototype ) {
      final String pkg = DemandName.getPkg( root );
      this.demandName( result, root, pkg );
    } else if( rootOp == DimExprs.prototype ) {
      final Enumeration dims = DimExprs.getSizeEnumeration( root );
      this.dimExprs( result, root, dims );
    }
    // DivExpression instanceof BinopExpression
    // Skipping DivRemExpression because it has subclasses
    else if( rootOp == DoStatement.prototype ) {
      final IRNode cond = DoStatement.getCond( root );
      final IRNode loop = DoStatement.getLoop( root );
      this.doStatement( result, root, cond, loop );
    }
    // Skipping DoubleType
    else if( rootOp == EmptyStatement.prototype ) {
      this.emptyStatement( result, root );
    }
    // EqExpression instance of BinopExpression
    // Skipping EqualityExpression because it has subclasses
    else if( rootOp == ExprStatement.prototype ) {
      final IRNode expr = ExprStatement.getExpr( root );
      this.exprStatement( result, root, expr );
    }
    // Skipping Expression because it has subclasses
    else if( rootOp == Extensions.prototype ) {
      final Enumeration classes =
	Extensions.getSuperInterfaceEnumeration( root );
      this.extensions( result, root, classes );
    }
    else if( rootOp == FalseExpression.prototype ) {
      this.falseExpression( result, root );
    } else if( rootOp == FieldDeclaration.prototype ) {
      final int mods = FieldDeclaration.getMods( root );
      final IRNode type = FieldDeclaration.getType( root );
      final IRNode vars = FieldDeclaration.getVars( root );
      this.fieldDeclaration( result, root, mods, type, vars );
    } else if( rootOp == FieldRef.prototype ) {
      final Object id = FieldRef.getId( root );
      final IRNode object = FieldRef.getObject( root );
      this.fieldRef( result, root, object, id );
    } else if( rootOp == Finally.prototype ) {
      final IRNode body = Finally.getBody( root );
      this.finallyClause( result, root, body );
    } else if( rootOp == FloatLiteral.prototype ) {
      final Object token = FloatLiteral.getToken( root );
      this.floatLiteral( result, root, token );
    }
    // Skipping FloatType
    // Skipping FloatingPointType because it has subclasses
    // Skipping ForInit because it has subclasses
    else if( rootOp == ForStatement.prototype ) {
      final IRNode cond = ForStatement.getCond( root );
      final IRNode init = ForStatement.getInit( root );
      final IRNode loop = ForStatement.getLoop( root );
      final IRNode update = ForStatement.getUpdate( root );
      this.forStatement( result, root, init, cond, update, loop );
    }
    // GreaterThanEqualExpression instanceof BinopExpression
    // GreaterThanExpression instanceof BinopExpression
    else if( rootOp == IfElseStatement.prototype ) {
      final IRNode cond = IfElseStatement.getCond( root );
      final IRNode thenPart = IfElseStatement.getThenpart( root );
      final IRNode elsePart = IfElseStatement.getElsepart( root );
      this.ifElseStatement( result, root, cond, thenPart, elsePart );
    } else if( rootOp == IfStatement.prototype ) {
      final IRNode cond = IfStatement.getCond( root );
      final IRNode thenPart = IfStatement.getThenpart( root );
      this.ifStatement( result, root, cond, thenPart );
    } else if( rootOp == Implements.prototype ) {
      final Enumeration interfaces = Implements.getIntfEnumeration( root );
      this.implementsList( result, root, interfaces );
    } else if( rootOp == ImportDeclaration.prototype ) {
      final IRNode item = ImportDeclaration.getItem( root );
      this.importDeclaration( result, root, item );
    } else if( rootOp == ImportDeclarations.prototype ) {
      final Enumeration imports = ImportDeclarations.getImpsEnumeration( root );
      this.importDeclarations( result, root, imports );
    }
    // Skipping ImportName because it has subclasses
    else if( rootOp == Initialization.prototype ) {
      final IRNode value = Initialization.getValue( root );
      this.initialization( result, root, value );
    }
    // Skipping Initializer because it has subclasses
    else if( rootOp == InstanceOfExpression.prototype ) {
      final IRNode val = InstanceOfExpression.getValue( root );
      final IRNode type = InstanceOfExpression.getType( root );
      this.instanceOfExpression( result, root, val, type );
    } else if( rootOp == IntLiteral.prototype ) {
      final Object token = IntLiteral.getToken( root );
      this.intLiteral( result, root, token );
    }
    // Skipping IntType
    // Skipping IntegralType because it has subclasses
    else if( rootOp == InterfaceDeclaration.prototype ) {
      final IRNode body = InterfaceDeclaration.getBody( root );
      final IRNode extensions = InterfaceDeclaration.getExtensions( root );
      final Object id = InterfaceDeclaration.getId( root );
      final int mods = InterfaceDeclaration.getMods( root );
      this.interfaceDeclaration( result, root, body, extensions, id, mods );
    } else if( rootOp == LabeledBreakStatement.prototype ) {
      final Object id = LabeledBreakStatement.getId( root );
      this.labeledBreakStatement( result, root, id );
    } else if( rootOp == LabeledContinueStatement.prototype ) {
      final Object id = LabeledContinueStatement.getId( root );
      this.labeledContinueStatement( result, root, id );
    } else if( rootOp == LabeledStatement.prototype ) {
      final Object label = LabeledStatement.getLabel( root );
      final IRNode stmt = LabeledStatement.getStmt( root );
      this.labeledStatement( result, root, label, stmt );
    }
    // LeftShiftExpression instanceof BinopExpression
    // LessThanEqualExpression instanceof BinopExpression
    // LessThanExpression instanceof BinopExpression
    // Skipping LiteralExpression because it has subclasses
    // Skipping LoadOperator, because it is not a real operator
    // Skipping LogBinopExpression because it has subclasses
    // Skipping LogUnopExpression because it has subclasses
    // Skipping LongType
    else if( rootOp == MethodBody.prototype ) {
      final IRNode block = MethodBody.getBlock( root );
      this.methodBody( result, root, block );
    } else if( rootOp == MethodCall.prototype ) {
      final IRNode args = MethodCall.getArgs( root );
      final Object method = MethodCall.getMethod( root );
      final IRNode obj = MethodCall.getObject( root );
      this.methodCall( result, root, args, method, obj );
    } else if( rootOp == MethodDeclaration.prototype ) {
      final IRNode body = MethodDeclaration.getBody( root );
      final int dims = MethodDeclaration.getDims( root );
      final IRNode exceptions = MethodDeclaration.getExceptions( root );
      final Object id = MethodDeclaration.getId( root );
      final int mods = MethodDeclaration.getModifiers( root );
      final IRNode params = MethodDeclaration.getParams( root );
      final IRNode returnType = MethodDeclaration.getReturnType( root );
      this.methodDeclaration( result, root, body, dims, exceptions, id, mods,
				params, returnType );
    } else if( rootOp == MinusExpression.prototype ) {
      final IRNode op = MinusExpression.getOp( root );
      this.minusExpression( result, root, op );
    }
    // MulExpression instanceof BinopExpression

    // else if( rootOp == Name.prototype ) {
    //	String names = Name.getNames( root );
    //   this.name( root, names );
    // }

    // Skipping NameExpression
    // Skipping NamedPackageDeclaration
    // Skipping NamedType
    else if( rootOp == NamedType.prototype ) {
      // do nothing
    } else if( rootOp == NestedClassDeclaration.prototype ) {
      final IRNode body = NestedClassDeclaration.getBody( root );
      final IRNode extension = NestedClassDeclaration.getExtension( root );
      final Object id = NestedClassDeclaration.getId( root );
      final IRNode impls = NestedClassDeclaration.getImpls( root );
      final int mods = NestedClassDeclaration.getMods( root );
      this.nestedClassDeclaration( result, root, body, extension, id,
					  impls, mods );
    } else if( rootOp == NestedInterfaceDeclaration.prototype ) {
      final IRNode body = NestedInterfaceDeclaration.getBody( root );
      final IRNode extensions = NestedInterfaceDeclaration.getExtensions( root );
      final Object id = NestedInterfaceDeclaration.getId( root );
      final int mods = NestedInterfaceDeclaration.getMods( root );
      this.nestedInterfaceDeclaration( result, root, body, extensions,
					      id, mods ); 
    } else if( rootOp == NewExpression.prototype ) {
      final IRNode args = NewExpression.getArgs( root );
      final IRNode type = NewExpression.getType( root );
      this.newExpression( result, root, args, type );
    } else if( rootOp == AnonClassExpression.prototype ) {
      final IRNode args = AnonClassExpression.getArgs( root );
      final IRNode body = AnonClassExpression.getBody( root );
      final IRNode type = AnonClassExpression.getType( root );
      this.anonClassExpression( result, root, args, body, type );
    } else if( rootOp == NoArrayInitializer.prototype ) {
      this.noArrayInitializer( result, root );
    } else if( rootOp == NoClassBody.prototype ) {
      this.noClassBody( result, root );
    } else if( rootOp == NoFinally.prototype ) {
      this.noFinally( result, root );
    } else if( rootOp == NoInitialization.prototype ) {
      this.noInitialization( result, root );
    } else if( rootOp == NoMethodBody.prototype ) {
      this.noMethodBody( result, root );
    }
    // NotEqExpression instanceof BinopExpression
    else if( rootOp == NotExpression.prototype ) {
      final IRNode op = NotExpression.getOp( root );
      this.notExpression( result, root, op );
    } else if( rootOp == NullLiteral.prototype ) {
      this.nullLiteral( result, root );
    }
    // SKipping NumericType
    else if( rootOp == OpAssignExpression.prototype ) {
      final IRNode lhs = OpAssignExpression.getLhs( root );
      final IRNode rhs = OpAssignExpression.getRhs( root );
      final Operator op = OpAssignExpression.getOp( root );
      this.opAssignExpression( result, root, lhs, rhs, op );
    }
    // Skipping OptArrayInitializer 
    // Skipping OptClassBody 
    // Skipping OptExtension 
    // Skipping OptFinally 
    // Skipping OptInitialization 
    // Skipping OptMethodBody     
    // OrExpression instanceof BinopExpression
    // Skipping PackageDeclaration
    else if( rootOp == ParameterDeclaration.prototype ) {
      final int dims = ParameterDeclaration.getDims( root );
      final Object id = ParameterDeclaration.getId( root );
      final int mods = ParameterDeclaration.getMods( root );
      final IRNode type = ParameterDeclaration.getType( root );
      this.parameterDeclaration( result, root, dims, id, mods, type );
    } else if ( rootOp == Parameters.prototype ) {
      final Enumeration params = Parameters.getFormalEnumeration( root );
      this.parameters( result, root, params );
    } else if( rootOp == PlusExpression.prototype ) {
      final IRNode expr = PlusExpression.getOp( root );
      this.plusExpression( result, root, expr );
    } else if( rootOp == PostDecrementExpression.prototype ) {
      final IRNode expr = PostDecrementExpression.getOp( root );
      this.postDecrementExpression( result, root, expr );
    } else if( rootOp == PostIncrementExpression.prototype ) {
      final IRNode expr = PostIncrementExpression.getOp( root );
      this.postIncrementExpression( result, root, expr );
    } else if( rootOp == PreDecrementExpression.prototype ) {
      final IRNode expr = PreDecrementExpression.getOp( root );
      this.preDecrementExpression( result, root, expr );
    } else if( rootOp == PreIncrementExpression.prototype ) {
      final IRNode expr = PreIncrementExpression.getOp( root );
      this.preIncrementExpression( result, root, expr );
    }
    // Skipping PrimLiteral
    // Skipping PrimaryExpression
    // Skipping PrimType
    else if( rootOp == QualifiedAllocationExpression.prototype ) {
      final IRNode alloc = QualifiedAllocationExpression.getAlloc( root );
      final IRNode type = QualifiedAllocationExpression.getType( root );
      this.qualifiedAllocationExpression( result, root, alloc, type );
    }
    // Skipping QualifiedNameType
    else if( rootOp == QualifiedThisExpression.prototype ) {
      final IRNode type = QualifiedThisExpression.getType( root );
      this.qualifiedThisExpression( result, root, type );
    }
    // Skipping RefLiteral
    // SKipping REferenceType
    // SKipping RelopExpression
    // RemExpression instance of BinopExpression
    else if( rootOp == ReturnStatement.prototype ) {
      final IRNode val = ReturnStatement.getValue( root );
      this.returnStatement( result, root, val );
    }
    // RightShiftExpression instanceof BinopExpression
    // Skipping ShiftExpression
    // Skipping ShortType
    // Skipping SImpleNamedType
    // Skipping Statement
    // Skipping StatementExpression
    else if( rootOp == StatementExpressionList.prototype ) {
      final Enumeration stmts = StatementExpressionList.getExprEnumeration( root );
      this.statementExpressionList( result, root, stmts );
    } else if( rootOp == StringLiteral.prototype ) {
      final Object token = StringLiteral.getToken( root );
      this.stringLiteral( result, root, token );
    }
    // SuybExpression instance of BinopExpression
    else if( rootOp == SuperExpression.prototype ) {
      this.superExpression( result, root );
    } else if( rootOp == SwitchBlock.prototype ) {
      final Enumeration elements = SwitchBlock.getElementEnumeration( root );
      this.switchBlock( result, root, elements );
    } else if( rootOp == SwitchElement.prototype ) {
      final IRNode label = SwitchElement.getLabel( root );
      final IRNode stmts = SwitchElement.getStmts( root );
      this.switchElement( result, root, label, stmts );
    }
    // Skipping SwitchLabel
    else if( rootOp == SwitchStatement.prototype ) {
      final IRNode block = SwitchStatement.getBlock( root );
      final IRNode expr = SwitchStatement.getExpr( root );
      this.switchStatement( result, root, block, expr );
    } else if( rootOp == SwitchStatements.prototype ) {
      final Enumeration stmts = SwitchStatements.getStmtsEnumeration( root );
      this.switchStatements( result, root, stmts );
    } else if( rootOp == SynchronizedStatement.prototype ) {
      final IRNode block = SynchronizedStatement.getBlock( root );
      final IRNode lock = SynchronizedStatement.getLock( root );
      this.synchronizedStatement( result, root, block, lock );
    } else if( rootOp == ThisExpression.prototype ) {
      this.thisExpression( result, root );
    } else if( rootOp == ThrowStatement.prototype ) {
      final IRNode val = ThrowStatement.getValue( root );
      this.throwStatement( result, root, val );
    } else if( rootOp == Throws.prototype ) {
      final Enumeration types = Throws.getTypeEnumeration( root );
      this.throwsList( result, root, types );
    } else if( rootOp == TrueExpression.prototype ) {
      this.trueExpression( result, root );
    } else if( rootOp == TryStatement.prototype ) {
      final IRNode block = TryStatement.getBlock( root );
      final IRNode catchPart = TryStatement.getCatchPart( root );
      final IRNode finallyPart = TryStatement.getFinallyPart( root );
      this.tryStatement( result, root, block, catchPart, finallyPart );
    }
    // Skipping Type
    // Skipping TypeDeclaration
    else if( rootOp == TypeDeclarations.prototype ) {
      final Enumeration types = TypeDeclarations.getTypesEnumeration( root );
      this.typeDeclarations( result, root, types );
    } else if( rootOp == TypeExpression.prototype ) {
      final IRNode type = TypeExpression.getType( root );
      this.typeExpression( result, root, type );
    } else if( rootOp == UnnamedPackageDeclaration.prototype ) {
      this.unnamedPackageDeclaration( result, root );
    }
    // Skipping UnopExpression
    // UnsignedRightShiftExpression instanceof BinopExpression
    else if( rootOp == UseExpression.prototype ) {
      final Object id = UseExpression.getId( root );
      this.useExpression( result, root, id );
    } else if( rootOp == VariableDeclaration.prototype ) {
      final Object id = VariableDeclaration.getId( root );
      this.variableDeclaration( result, root, id );
    } else if( rootOp == VariableDeclarator.prototype ) {
      final Object id = VariableDeclarator.getId( root );
      final int dims = VariableDeclarator.getDims( root );
      final IRNode init = VariableDeclarator.getInit( root );
      this.variableDeclarator( result, root, id, dims, init );
    } else if( rootOp == VariableDeclarators.prototype ) {
      final Enumeration vars = VariableDeclarators.getVarEnumeration( root );
      this.variableDeclarators( result, root, vars );
    } else if( rootOp == VoidReturnStatement.prototype ) {
      this.voidReturnStatement( result, root );
    }
    // Skipping VoidType
    else if( rootOp == WhileStatement.prototype ) {
      final IRNode cond = WhileStatement.getCond( root );
      final IRNode loop = WhileStatement.getLoop( root );
      this.whileStatement( result, root, cond, loop );
    }

    // XorExpression instanceof BinopExpression

    // Lump all BinopExpressions together
    else if( rootOp instanceof BinopExpression ) {
      final IRNode op1 = BinopExpression.getOp1( root );
      final IRNode op2 = BinopExpression.getOp2( root );
      this.binopExpression( result, rootOp, root, op1, op2 );
    } else {
      throw new IllegalArgumentException( 
                   "Unknown Operator \"" + rootOp.name() + "\"" );
    }
  }

  //----------------------------------------------------------------------

  /** An expression using a binary operator
    * @param op The operator being used
    * @param root The node of the expression
    * @param op1 The node of the first operand
    * @param op2 The node of the second operand
    */
  protected abstract void binopExpression( 
    Set result, Operator op, IRNode root, IRNode op1, IRNode op2 );

  /** List of method call actual arguments
    * @param root The Node of the arguments
    * @param args Enumeration of the nodes of the arguments
    */
  protected abstract void arguments( Set result, IRNode root, Enumeration args );

  /** An array allocation.
    * @param root The node of the allocation expression
    * @param alloc ?
    * @param base The type of the array elements?
    * @param init The node of the intializer expression
    * @param unalloc ?
    */
  protected abstract void arrayCreationExpression( Set result, IRNode root, IRNode alloc,
						  IRNode base, IRNode init,
						  int unalloc );

  /** An array declaration.
    * @param root The node of the declaration
    * @param base The type of the array elements?
    * @param dims ?
    */
  protected abstract void arrayDeclaration( Set result, IRNode root, IRNode base,
					   int dims );

  /** An array initializer.
    * @param root The node of the initializer expression
    * @param elements An Enumeration of the nodes of element expressions
    */
  protected abstract void arrayInitializer( Set result, IRNode root, Enumeration elements );

  /** Indexing of an array.
    * @param root The node of the expression
    * @param array The node of the expression providing the array
    * @param index The node of the expression computing the index
    */
  protected abstract void arrayRefExpression( Set result, IRNode root, IRNode array,
					     IRNode index );
               
  /**
   * An assertion statement without a message constructor.
   * @param root The node of the expression
   * @param assertion The node of the assertion expression
   */
  protected abstract void assertStatement(
    Set result, IRNode root, IRNode assertion );

  /**
   * An assertion statement with a message constructor
   * @param root The node of the expression.
   * @param assertion The node of the assertion subexpression.
   * @param message The node of the message constructor.
   * @return Object
   */
  protected abstract void assertMessageStatement(
    Set result, IRNode root, IRNode assertion, IRNode message );


  /** An assignment expression.
    * @param root The node of the expression
    * @param lhs The node of the left hand side expression
    * @param rhs The node of the right hand side expression
    */
  protected abstract void assignExpression( Set result, IRNode root,
					   IRNode lhs, IRNode rhs );

  /** A block of statements.
    * @param root The node of the block
    * @param stmts Enumeration of the nodes of the statements in the block
    */
  protected abstract void blockStatement( Set result, IRNode root, Enumeration stmts );

  /** An unlabeled break statement.
    * @param root The node of the break statement
    */
  protected abstract void breakStatement( Set result, IRNode root );

  /** A type case expression.
    * @param root The node of the type cast
    * @param expr The node of the expression being cast
    * @param type The node of the type being cast into
    */
  protected abstract void castExpression( Set result, IRNode root,
					 IRNode expr, IRNode type );

  /** A Catch clause in a try statement.
    * @param root The node of the catch clause
    * @param body The node of the body
    * @param param The node of the parameter declaration of the clause
    */
  protected abstract void catchClause( Set result, IRNode root, IRNode body, IRNode param );

  /** The list of catch clauses of a try statement
    * @param root The node of the list
    * @param clauses Enumeration of the nodes of the catch clauses
    */
  protected abstract void catchClauses( Set result, IRNode root, Enumeration clauses );

  /** Character constant
    * @param root The node of the expression
    * @param token The character 
    */
  protected abstract void charLiteral( Set result, IRNode root, Object token );

  /** Body of class declaration
    * @param root The node of the body
    * @param decls Enumeration of the nodes of the field and method
    * declarations
    */
  protected abstract void classBody( Set result, IRNode root, Enumeration decls );

  /** A class declaration
    * @param root The node of the class declaration
    * @param body The node of the class body
    * @param extension The node of the list of super classes
    * @param id Name of the class
    * @param impls The node of the list of implemented interfaces
    * @param mods Flags for declaration modifers
    */
  protected abstract void classDeclaration( Set result, IRNode root, IRNode body,
					   IRNode extension, Object id,
					   IRNode impls, int mods );

  /** Use of the special <tt>class</tt> field of an object.
    * @param root The node of the expression
    * @param type Node representing the class of the object 
    */
  protected abstract void classExpression( Set result, IRNode root, IRNode type );

  /** The <tt>static</tt> class initializer block.
    * @param root The node of the initializer
    * @param block The node of the statements in the block
    * @param mods Flags for declaration modifers on the block
    */
  protected abstract void classInitializer( Set result, IRNode root, IRNode block,
					   int mods );

  /** The source file.
    * @param root The node representing the file
    * @param decls Node of class/interface declarations in the file
    * @param imps Node of import statements
    * @param pkg Node of package statement
    */
  protected abstract void compilationUnit( Set result, IRNode root, IRNode decls,
					  IRNode imps, IRNode pkg );

  /** A compiled method body (for methods or constructors)
   * @param root the node representing the method body
   * @param code structure containing code.
   */
  protected abstract void compiledMethodBody( Set result, IRNode root, Object code);

  /** Binary complement expression
    * @param root The node of the expression
    * @param expr The node of the operand expression
    */
  protected abstract void complementExpression( Set result, IRNode root, IRNode expr );

  /** Conditional expression
    * @param root Node of the conditional
    * @param cond Node of the condition
    * @param ifTrue Node of the true branch
    * @param ifFalse Node of the false branch
    */
  protected abstract void conditionalExpression( Set result, IRNode root, IRNode cond,
						IRNode ifTrue,
						IRNode ifFalse );

  /** ??? */
  protected abstract void constantLabel( Set result, IRNode root, IRNode op );

  /** Constructor call.
    * @param root Node of the expression
    * @param object Name of the constructor
    * @param args Enumeration of the nodes of the actual parameters
    */
  protected abstract void constructorCall( Set result, IRNode root, IRNode object,
					  IRNode args );

  /** Declaration of a constructor.
    * @param root The node of the declaration
    * @param block The node of the of the constructor body
    * @param exceptions The node of the list of declared exceptions
    * @param mods Flags for declaration modifiers
    * @param name The name of the constructor
    * @param params Node of the list of formal parameters
    */
  protected abstract void constructorDeclaration( Set result, IRNode root, IRNode block,
						 IRNode exceptions,
						 int mods, Object name,
						 IRNode params );

  /** An unlabed continue statement.
    * @param root The node of the statement
    */
  protected abstract void continueStatement( Set result, IRNode root );

  /** A field/local var declaration statement.
    * @param root Node of the declaration statement
    * @param mods Flags of declaration modifiers
    * @param type Node of the type of the items being declared
    * @param vars Node of the items being declared
    */
  protected abstract void declStatement( Set result, IRNode root, int mods, IRNode type,
					IRNode vars );

  /** Use of a default label in a case statement.
    * @param root Node of the label
    */
  protected abstract void defaultLabel( Set result, IRNode root );

  /** ? */
  protected abstract void demandName( Set result, IRNode root, String pkg );

  /** ? */
  protected abstract void dimExprs( Set result, IRNode root, Enumeration dims );

  /** A do-while statement.
    * @param root Node of the do-while statement
    * @param cond Node of the condition 
    * @param loop Node of the body of the loop
    */
  protected abstract void doStatement( Set result, IRNode root, IRNode cond, IRNode loop );

  /** An empty statment.
    * @param root Node of the empty statement
    */
  protected abstract void emptyStatement( Set result, IRNode root );

  /** Expression used as a statement.
    * @param root Node of the statement
    * @param expr Node of the expression
    */
  protected abstract void exprStatement( Set result, IRNode root, IRNode expr );

  /** List of classes/interfaces being extended.
    * @param root Node of the list
    * @param classes Enumeration of the classes
    */
  protected abstract void extensions( Set result, IRNode root, Enumeration classes );

  /** <tt>false</tt> constant.
    * @param root Node of the expression
    */
  protected abstract void falseExpression( Set result, IRNode root );

  /** Declaration of a field
    * @param root The node of the declaration
    * @param mods Flags for declaration modifiers
    * @param type Node of the type of the fields
    * @param vars Node of the list of fields being declared
    */
  protected abstract void fieldDeclaration( Set result, IRNode root, int mods, IRNode type,
					   IRNode vars );

  /** Use of a field
    * @param root Node of the expression
    * @param object Node of the expression providing the object instance
    * @param id Name of the field
    */
  protected abstract void fieldRef( Set result, IRNode root, IRNode object, Object id );

  /** Finally clause of a try statement.
    * @param root Node of the clause
    * @param body Node of the body of the clause
    */
  protected abstract void finallyClause( Set result, IRNode root, IRNode body );

  /** Use of a floating point constant.
    * @param root Node of the expression
    * @param token value of the constant
    */
  protected abstract void floatLiteral( Set result, IRNode root, Object token );
  
  /** For statement.
    * @param root Node of the for statement
    * @param init Node of the initialization clause
    * @param cond Node of the expression in the condition clause
    * @param update Node of the update clause
    * @param loop Node of the loop body
    */
  protected abstract void forStatement( Set result, IRNode root, IRNode init, IRNode cond,
				       IRNode update, IRNode loop );

  /** If-then-else statement.
    * @param root Node of the statement
    * @param cond Node of the condional epxression
    * @param thenPart Node of the "then" statement
    * @param elsePart Node of the "else" statement
    */
  protected abstract void ifElseStatement( Set result, IRNode root, IRNode cond,
					  IRNode thenPart, IRNode elsePart );

  /** If-then statement.
    * @param root Node of the statement
    * @param cond Node of the condional epxression
    * @param thenPart Node of the "then" statement
    */
  protected abstract void ifStatement( Set result, IRNode root, IRNode cond,
				      IRNode thenPart );

  /** List of interfaces
    * @param root Node of the list
    * @param interfaces Enumeration of the interfaces implemented by a class
    */
  protected abstract void implementsList( Set result, IRNode root, Enumeration interfaces );

  /** An import declaration
    * @param root Node of the declaration
    * @param item Node of the package name
    */
  protected abstract void importDeclaration( Set result, IRNode root, IRNode item );

  /** List of import statements
    * @param root Node of the list
    * @param imports Enumeration of the nodes of the import statements
    */
  protected abstract void importDeclarations( Set result, IRNode root,
					     Enumeration imports );

  /** Initializer of a non-array variable
    * @param root Node of the initialization
    * @param value Node of the expression to intialize with
    */
  protected abstract void initialization( Set result, IRNode root, IRNode value );

  /** Instance of expression
    * @param root Node of the expression
    * @param val Node of the expression producing the instance to be tested
    * @param type Node of the type being tested against
    */
  protected abstract void instanceOfExpression( Set result, IRNode root, IRNode val,
					       IRNode type );

  /** Use of an integer constant
    * @param root Node of the use
    * @param token Value of the constant
    */
  protected abstract void intLiteral( Set result, IRNode root, Object token );

  /** An interface declaration
    * @param root The node of the interface declaration
    * @param body The node of the interface body
    * @param extension The node of the list of any super interfaces
    * @param id Name of the interface
    * @param mods Flags for declaration modifers
    */
  protected abstract void interfaceDeclaration( Set result, IRNode root, IRNode body,
					       IRNode extensions, Object id,
					       int mods );

  /** Break with a labeled target
    * @param root Node of the break
    * @param id Name of the label
    */
  protected abstract void labeledBreakStatement( Set result, IRNode root, Object id );

  /** Continue with a labeled target
    * @param root Node of the continue
    * @param id Name of the label
    */
  protected abstract void labeledContinueStatement( Set result, IRNode root, Object id );

  /** A statement with a label
    * @param root Node of the labeled statement
    * @param label Name of the label
    * @param stmt Node of the statement being labeled
    */
  protected abstract void labeledStatement( Set result, IRNode root, Object label,
					   IRNode stmt );

  /** Body of a method
    * @param root Node of the method body
    * @param block Node of the block of statements in the body
    */
  protected abstract void methodBody( Set result, IRNode root, IRNode block );

  /** Method call with a specific instance
    * @param root Node of the method call
    * @param args Node of the list of actual parameters
    * @param method Name of the method
    * @param obj Node of the expression producing the object instance
    */
  protected abstract void methodCall( Set result, IRNode root, IRNode args, Object method,
				     IRNode obj );

  /** Declaration of a method
    * @param root Node of the declaration
    * @param body Node of the method body
    * @param dims ?
    * @param exceptions Node of the list of any declared exceptions
    * @param id Name of the method
    * @param mods Flags of declaration modifiers
    * @param params Node of the list of formal parameters
    * @param returnType of the return type
    */
  protected abstract void methodDeclaration( Set result, IRNode root, IRNode body,
					    int dims, IRNode exceptions,
					    Object id, int mods,
					    IRNode params, IRNode returnType );

  /** Unary minus expression
    * @param root Node of the expression
    * @param op Node of the operand
    */
  protected abstract void minusExpression( Set result, IRNode root, IRNode op );

  /** ? */
  protected abstract void name( Set result, IRNode root, String names );

  /** Declartion of a nested class
    * @param root Node of the declaration
    * @param body Node of the body of the declaration
    * @param extentions Node of the list of any extended classes
    * @param id Name of the interface
    * @param extentions Node of the list of any implemented interfaces
    * @param mods Flags for declaration modifiers
    */
  protected abstract void nestedClassDeclaration( Set result, IRNode root, IRNode body,
						 IRNode extension, Object id,
						 IRNode impls, int mods );

  /** Declartion of a nested interface
    * @param root Node of the declaration
    * @param body Node of the body of the declaration
    * @param extentions Node of the list of any extended interfaces
    * @param id Name of the interface
    * @param mods Flags for declaration modifiers
    */
  protected abstract void nestedInterfaceDeclaration( Set result, IRNode root, IRNode body,
						     IRNode extensions,
						     Object id, int mods ); 

  /** Creation of a new instance
    * @param root Node of the new expression
    * @param args Node of the list of actual parameters
    * @param type Node of the constructor name
    */
  protected abstract void newExpression( Set result, IRNode root, IRNode args,
					IRNode type );

  /** Creation of a new instance
    * @param root Node of the new expression
    * @param args Node of the list of actual parameters
    * @param body Node for the body of an anonymous class
    * @param type Node of the constructor name
    */
  protected abstract void anonClassExpression( Set result, IRNode root, IRNode args,
				   	      IRNode body, IRNode type );

  /** Node for an non-existant array initialization
    * @param root Node of the absent array initialization
    */
  protected abstract void noArrayInitializer( Set result, IRNode root );

  /** Node for an empty class body
    * @param root Node of empty body
    */
  protected abstract void noClassBody( Set result, IRNode root );

  /** Node for a non-existant finally clause
    * @param root Node of empty finally
    */
  protected abstract void noFinally( Set result, IRNode root );

  /** Node for an non-existant initialization
    * @param root Node of the absent initialization
    */
  protected abstract void noInitialization( Set result, IRNode root );

  /** Body of an abstract method
    * @param root Node of the body
    */
  protected abstract void noMethodBody( Set result, IRNode root );

  /** Boolean not expression
    * @param root Node of the expression
    * @param op Node of the operand
    */
  protected abstract void notExpression( Set result, IRNode root, IRNode op );

  /** Use of <tt>null</tt>
    * @param root Node of the expression
    */
  protected abstract void nullLiteral( Set result, IRNode root );

  /** Operator assignment expression
    * @param root Node of the expression
    * @param lhs Node of the left hand side expression
    * @param rhs Node of the right had side expression
    * @param op The operator
    */
  protected abstract void opAssignExpression( Set result, IRNode root, IRNode lhs, 
					     IRNode rhs, Operator op );

  /** Formal arameter declaration
    * @param root Node of the declaration
    * @param dims ?
    * @param id Name of the parameter
    * @param mods Flags of declaration modifiers
    * @param type Node of the type of the parameter
    */
  protected abstract void parameterDeclaration( Set result, IRNode root, int dims,
					       Object id, int mods,
					       IRNode type );

  /** List of formal paramers to a constructor or method call
    * @param root Node of the list
    * @param params Enumeration of the nodes of the parameter declarations
    */
  protected abstract void parameters( Set result, IRNode root, Enumeration params );

  /** Unary plus
    * @param root Node of the expression
    * @param expr Node of the operand expression
    */
  protected abstract void plusExpression( Set result, IRNode root, IRNode expr );

  /** x-- expression
    * @param root Node of the decrement expression
    * @param expr Node of the expression being decremented
    */
  protected abstract void postDecrementExpression( Set result, IRNode root, IRNode expr );

  /** x++ expression
    * @param root Node of the increment expression
    * @param expr Node of the expression being incremented
    */
  protected abstract void postIncrementExpression( Set result, IRNode root, IRNode expr );

  /** --x expression
    * @param root Node of the decrement expression
    * @param expr Node of the expression being decremented
    */
  protected abstract void preDecrementExpression( Set result, IRNode root, IRNode expr );

  /** ++x expression
    * @param root Node of the increment expression
    * @param expr Node of the expression being incremented
    */
  protected abstract void preIncrementExpression( Set result, IRNode root, IRNode expr );

  protected abstract void qualifiedAllocationExpression( Set result, IRNode root, IRNode alloc, IRNode type );
  protected abstract void qualifiedThisExpression( Set result, IRNode root, IRNode type );

  /** Return statement
    * @param root Node of the statement
    * @param val Node of the expression being returned
    */
  protected abstract void returnStatement( Set result, IRNode root, IRNode val );

  /** Comma seperated list of expressions.
    * @param root Node of the list
    * @param stmts Enumeration of nodes of the statements
    */
  protected abstract void statementExpressionList( Set result, IRNode root, Enumeration stmts );

  /** Use of a string literal.
    * @param root Node of the string
    * @param Object The body of the string
    */
  protected abstract void stringLiteral( Set result, IRNode root, Object token );

  /** Use of the "super" expression.
    * @param root The node of the expression
    */
  protected abstract void superExpression( Set result, IRNode root );
 
  /** List of "case" statements.
    * @param root The node of the list
    * @param elements Enumeration of the "case" clauses
    */
  protected abstract void switchBlock( Set result, IRNode root, Enumeration elements );

  /** A "case" or "default" clause of a switch statement.
    * @param root The node of the clause
    * @param label The node of the label of the clause
    * @param stmts The node of the statements of the clause
    */
  protected abstract void switchElement( Set result, IRNode root, IRNode label,
					IRNode stmts );

  /** A switch statement.
    * @param root The node of the switch statement
    * @param block The node of body of the switch statement
    * @param expr The node of the expression driving the conditional
    */
  protected abstract void switchStatement( Set result, IRNode root, IRNode block,
					  IRNode expr );

  /** Statements following a "case" of "default" label
    * @param root The node of the statements
    * @param stmts Enumeration of the statements
    */
  protected abstract void switchStatements( Set result, IRNode root, Enumeration stmts );

  /** Synchronized statement.
    * @param root The node of the synchronized statement
    * @param block The node of the body of the statement
    * @param lock The node of the expression to use as the lock
    */
  protected abstract void synchronizedStatement( Set result, IRNode root, IRNode block,
						IRNode lock );

  /** Use of the object receiver
    * @param root The node of the use
    */
  protected abstract void thisExpression( Set result, IRNode root );

  /** A throw statement
    * @param root The root of the throw statement
    * @param val The node of the expression being thrown
    */
  protected abstract void throwStatement( Set result, IRNode root, IRNode val );

  /** List of thrown exceptions
    * @param root The node of the list
    * @param types Enumeration of the exceptions
    */
  protected abstract void throwsList( Set result, IRNode root, Enumeration types );

  /** Use of the <tt>true</tt> constant.
    * @param root The node of the <tt>true</tt>
    */
  protected abstract void trueExpression( Set result, IRNode root );

  /** A try statement.
    * @param root The node of the try statement
    * @param block The node of body of the try
    * @param catchPart The node listing the catch declarations
    * @param finallyPart The node containing the finally 
    */
  protected abstract void tryStatement( Set result, IRNode root, IRNode block,
				       IRNode catchPart, IRNode finallyPart );

  protected abstract void typeDeclarations( Set result, IRNode root, Enumeration types );

  /** A type expression
    * @param root The node of the type expression
    * @param type The type of the expression
    */
  protected abstract void typeExpression( Set result, IRNode root, IRNode type );

  /** An implicit package declaration
    * @param root The node of the implicit declaration
    */
  protected abstract void unnamedPackageDeclaration( Set result, IRNode root );

  /** Use of a variable.
    * @param root The node of the use
    * @param id 
    */
  protected abstract void useExpression( Set result, IRNode root, Object id );

  /** ? */
  protected abstract void variableDeclaration( Set result, IRNode root, Object id );

  /** Declaration of a variable, including initializer
    * @param root The node of the declarator
    * @param id The name of the variable being declared
    * @param dims ?
    * @param init The node of the initializer
    */
  protected abstract void variableDeclarator( Set result, IRNode root, Object id,
					     int dims, IRNode init );
  
  /** List of variables in a declaration.
    * @param root The node of the list
    * @param vars Enumeration of the nodes of the variable names
    */
  protected abstract void variableDeclarators( Set result, IRNode root, Enumeration vars );

  /** Empty return statement.
    * @param root The node of the return statement
    */
  protected abstract void voidReturnStatement( Set result, IRNode root );

  /** While statement.
    * @param root The node of the while statement
    * @param cond The node of the conditional expression
    * @param loop The node of the body of the loop
    */
  protected abstract void whileStatement( Set result, IRNode root, IRNode cond,
					 IRNode loop ); 
}
