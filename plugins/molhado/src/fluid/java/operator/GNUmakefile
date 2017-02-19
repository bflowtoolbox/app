# $Header: /cvs/fluid/fluid/code/fluid/java/operator/GNUmakefile,v 1.45 2002/09/13 17:00:10 chance Exp $
# Makefile for ./code/fluid/java/operator
HERE=${FLUIDTOP}/code/fluid/java/operator
SUBDIRS= 

OPSRC=	AddExpression.op \
	AllocationExpression.op \
	AndExpression.op \
	AnonClassExpression.op \
	Arguments.op \
	ArithBinopExpression.op \
	ArithUnopExpression.op \
	ArrayCreationExpression.op \
	ArrayDeclaration.op \
	ArrayInitializer.op \
	ArrayRefExpression.op \
	ArrayType.op \
	AssertMessageStatement.op \
	AssertStatement.op \
	AssignExpression.op \
	BinopExpression.op \
	BlockStatement.op \
	BooleanLiteral.op \
	BooleanType.op \
	BreakStatement.op \
	ByteType.op \
	CastExpression.op \
	CatchClause.op \
	CatchClauses.op \
	CharLiteral.op \
	CharType.op \
	ClassBody.op \
	ClassBodyDeclaration.op \
	ClassDeclaration.op \
	ClassExpression.op \
	ClassInitializer.op \
	CompareExpression.op \
	CompilationUnit.op \
	CompiledMethodBody.op \
	ComplementExpression.op \
	ConditionalAndExpression.op \
	ConditionalExpression.op \
	ConditionalOrExpression.op \
	ConstantLabel.op \
	ConstructionObject.op \
	ConstructorCall.op \
	ConstructorDeclaration.op \
	ContinueStatement.op \
	CrementExpression.op \
	Declaration.op \
	DeclStatement.op \
	DefaultLabel.op \
	DemandName.op \
	DimExprs.op \
	Dims.op \
	DivExpression.op \
	DivRemExpression.op \
	DoStatement.op \
	DoubleType.op \
	EmptyStatement.op \
	EqExpression.op \
	EqualityExpression.op \
	ExprStatement.op \
	Expression.op \
	Extensions.op \
	FalseExpression.op \
	FieldDeclaration.op \
	FieldRef.op \
	Finally.op \
	FloatLiteral.op \
	FloatType.op \
	ForInit.op \
	ForStatement.op \
	GreaterThanEqualExpression.op \
	GreaterThanExpression.op \
	IfElseStatement.op \
	IfStatement.op \
	Implements.op \
	ImportDeclaration.op \
	ImportDeclarations.op \
	ImportName.op \
	Initialization.op \
	Initializer.op \
	InstanceOfExpression.op \
	IntLiteral.op \
	IntType.op \
	InterfaceDeclaration.op \
	LabeledBreakStatement.op \
	LabeledContinueStatement.op \
	LabeledStatement.op \
	LeftShiftExpression.op \
	LessThanEqualExpression.op \
	LessThanExpression.op \
	LiteralExpression.op \
	LogBinopExpression.op \
	LogUnopExpression.op \
	LongType.op \
	MethodBody.op \
	MethodCall.op \
	MethodDeclaration.op \
	MinusExpression.op \
	MulExpression.op \
	NameExpression.op \
	NamedPackageDeclaration.op \
	NamedType.op \
	NestedClassDeclaration.op \
	NestedInterfaceDeclaration.op \
	NewExpression.op \
	NoArrayInitializer.op \
	NoClassBody.op \
	NoFinally.op \
	NoInitialization.op \
	NoMethodBody.op \
	NotEqExpression.op \
	NotExpression.op \
	NullLiteral.op \
	OpAssignExpression.op \
	OptArrayInitializer.op \
	OptClassBody.op \
	OptFinally.op \
	OptInitialization.op \
	OptMethodBody.op \
	OrExpression.op \
	PackageDeclaration.op \
	ParameterDeclaration.op \
	Parameters.op \
	PlusExpression.op \
	PostDecrementExpression.op \
	PostIncrementExpression.op \
	PreDecrementExpression.op \
	PreIncrementExpression.op \
	PrimLiteral.op \
	PrimaryExpression.op \
	QualifiedAllocationExpression.op \
	QualifiedThisExpression.op \
	RefLiteral.op \
	RelopExpression.op \
	RemExpression.op \
	ReturnStatement.op \
	ReturnType.op \
	RightShiftExpression.op \
	ShiftExpression.op \
	ShortType.op \
	Statement.op \
	StatementExpression.op \
	StatementExpressionList.op \
	StringLiteral.op \
	StringConcat.op \
	SubExpression.op \
	SuperExpression.op \
	SwitchBlock.op \
	SwitchElement.op \
	SwitchLabel.op \
	SwitchStatement.op \
	SwitchStatements.op \
	SynchronizedStatement.op \
	ThisExpression.op \
	ThrowStatement.op \
	Throws.op \
	TrueExpression.op \
	TryStatement.op \
	Type.op \
	TypeDeclaration.op \
	TypeDeclarations.op \
	TypeExpression.op \
	UnnamedPackageDeclaration.op \
	UnopExpression.op \
	UnsignedRightShiftExpression.op \
	UseExpression.op \
	VariableDeclaration.op \
	VariableDeclarator.op \
	VariableDeclarators.op \
	VoidReturnStatement.op \
	VoidType.op \
	WhileStatement.op \
	XorExpression.op 

JAVASRC=	ArithExpression.java \
		Assignment.java \
		Call.java \
                ExtendedTreeWalker.java \
		ForInitInterface.java \
		FlowUnit.java \
		IllegalCode.java \
		ImportNameInterface.java \
		LogExpression.java \
	        OptArrayInitializerInterface.java \
		StatementExpressionInterface.java \
                TreeWalker.java \
		ReturnTypeInterface.java \
		TypeInterface.java \
		ReferenceType.java \
		PrimitiveType.java \
		NumericType.java \
		FloatingPointType.java \
		IntegralType.java \
		TypeDeclInterface.java \
		ClassBodyDeclInterface.java \
                DefaultTreeWalker.java \

OBSOLETE= Assignment.op YesExtension.op YexExtension.java \
	YesImplements.op YesImplements.java \
	NoImplements.op NoImplements.java \
	ArithExpression.op \
	ImportNameInterface.op \
	LogExpression.op \
	ConstantExpression.op ConstantExpression.java \
	Dim.op Dim.java \
	DimExpr.op DimExpr.java \
	Package.op Package.java \
	QualifiedPackage.op QualifiedPackage.java \
	SimplePackage.op SimplePackage.java \
	IntExpression.op IntExpression.java \
	ConstructionObjectInterface.* \
	ParenExpression.* \
	QualifiedName.* SimpleName.* \
	QualifiedNamedType.* SimpleNamedType.* \
	LocalMethodCall.* CallExpression.* \
	PrimitiveType.op NumericType.op \
	FloatingPointType.op IntegralType.op \
	ReferenceType.op \
	Name.* \
	Extension.* \
	NoExtension.* \
	OptExtension.* Ellipsis.* \
    doc.html

SOURCE= ${OPSRC} header header.html

GENJAVASRC= ${OPSRC:.op=.java} LoadOperator.java

GENJAVAOBJ= ${GENJAVASRC:.java=.class}

# All Java files are generated
CLEANFILES= ${GENJAVASRC} package.html

CREATEOPFLAGS=	-header=header \
		-nodetype=JavaNode \
		-optype=JavaOperator \
		-unparse=true \
		-unparsetype=JavaUnparser \
		Info:String \
		DimInfo:int \
		Modifiers:int \
		Names:IRSequence \
		Op:JavaOperator \
		Code:Object \
		ForInit:ForInitInterface \
		ImportName:ImportNameInterface \
		StatementExpression:StatementExpressionInterface \
		OptArrayInitializer:OptArrayInitializerInterface \
		ConstantNode:IRNode \
		ConstantInt:int 

DOCOPFLAGS=	-package=fluid.java.operator \
		-header=header.html \
		-start=CompilationUnit \
		ForInit:ForInitInterface \
		ImportName:ImportNameInterface \
		StatementExpression:StatementExpressionInterface \
		OptArrayInitializer:OptArrayInitializerInterface

include ${FLUIDTOP}/lib/make/define.mk
include ${FLUIDTOP}/lib/make/rule.mk

doc : ${GENJAVASRC} package.html

ant : ${GENJAVASRC}

package.html : ${DOCOP} ${OPSRC} header.html
	${LOCALBIN}/perl ${DOCOP} ${DOCOPFLAGS} ${OPSRC} > /tmp/doc.html
	mv /tmp/doc.html package.html

all : ${GENJAVAOBJ}  ${JAVAOBJ}

${GENJAVASRC} : header

${GENJAVAOBJ} : ${GENJAVASRC} ${JAVASRC}
	${JAVAC} ${GENJAVASRC} ${JAVASRC}

LoadOperator.java : GNUmakefile
	echo "// Generated by GNUmakefile.  Do not edit." > $@
	echo "package fluid.java.operator;" >> $@
	echo "public class LoadOperator {" >> $@
	for name in ${OPSRC:%.op=%}; do \
	  echo "  private static $$name x$$name = $$name.prototype;" >> $@; \
	done
	echo "}" >> $@

