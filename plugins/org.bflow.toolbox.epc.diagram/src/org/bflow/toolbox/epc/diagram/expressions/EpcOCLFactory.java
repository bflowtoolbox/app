/*
 * 
 */
package org.bflow.toolbox.epc.diagram.expressions;

import java.util.Collections;
import java.util.Map;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.EcoreFactory;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.ecore.Variable;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.options.ParsingOptions;


/**
 * @generated NOT
 */
public class EpcOCLFactory {

	/**
	 * @generated NOT
	 */
	private final EpcAbstractExpression[] expressions;

	/**
	 * @generated NOT
	 */
	private final String[] expressionBodies;

	/**
	 * @generated NOT
	 */
	protected EpcOCLFactory() {
		this.expressions = new EpcAbstractExpression[6];
		this.expressionBodies = new String[] {
				//Regeln ARC (erste Zeile Regeln für QuellNodes, 2. Zeile beschreibt mögliche ZielNodes)
				"((self <> oppositeEnd) and (self.oclIsKindOf(Function) or self.oclIsKindOf(Event) or self.oclIsKindOf(OR) or self.oclIsKindOf(AND) or self.oclIsKindOf(XOR) or self.oclIsKindOf(ProcessInterface)))", //$NON-NLS-1$
				"((self.oclIsTypeOf(Function) and (oppositeEnd.oclIsKindOf(Event) or oppositeEnd.oclIsKindOf(Function) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND))) or (oppositeEnd.oclIsTypeOf(Function) and (self.oclIsKindOf(Event) or self.oclIsKindOf(Function) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND))) or (self.oclIsTypeOf(Event) and (oppositeEnd.oclIsKindOf(Function) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND) or oppositeEnd.oclIsKindOf(ProcessInterface))) or (oppositeEnd.oclIsTypeOf(Event) and (self.oclIsKindOf(Function) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND) or self.oclIsKindOf(ProcessInterface))) or (self.oclIsTypeOf(OR) and (oppositeEnd.oclIsKindOf(Event) or oppositeEnd.oclIsKindOf(Function) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND) or oppositeEnd.oclIsKindOf(ProcessInterface))) or (oppositeEnd.oclIsTypeOf(OR) and (self.oclIsKindOf(Event) or self.oclIsKindOf(Function) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND) or self.oclIsKindOf(ProcessInterface))) or (self.oclIsTypeOf(XOR) and (oppositeEnd.oclIsKindOf(Event) or oppositeEnd.oclIsKindOf(Function) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND) or oppositeEnd.oclIsKindOf(ProcessInterface))) or (oppositeEnd.oclIsTypeOf(XOR) and (self.oclIsKindOf(Event) or self.oclIsKindOf(Function) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND) or self.oclIsKindOf(ProcessInterface))) or (self.oclIsTypeOf(AND) and (oppositeEnd.oclIsKindOf(Event) or oppositeEnd.oclIsKindOf(Function) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND) or oppositeEnd.oclIsKindOf(ProcessInterface))) or (oppositeEnd.oclIsTypeOf(AND) and (self.oclIsKindOf(Event) or self.oclIsKindOf(Function) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND) or self.oclIsKindOf(ProcessInterface))) or (self.oclIsTypeOf(ProcessInterface) and (oppositeEnd.oclIsKindOf(Event) or oppositeEnd.oclIsKindOf(OR) or oppositeEnd.oclIsKindOf(XOR) or oppositeEnd.oclIsKindOf(AND))) or (oppositeEnd.oclIsTypeOf(ProcessInterface) and (self.oclIsKindOf(Event) or self.oclIsKindOf(OR) or self.oclIsKindOf(XOR) or self.oclIsKindOf(AND))))", //$NON-NLS-1$
				
				//Regeln IARC
				"((self <> oppositeEnd) and (self.oclIsKindOf(Function) or self.oclIsKindOf(Product) or self.oclIsKindOf(TechnicalTerm) or self.oclIsKindOf(File) or self.oclIsKindOf(Document) or self.oclIsKindOf(Cluster) or self.oclIsKindOf(CardFile)))", //$NON-NLS-1$
				"((self.oclIsTypeOf(Function) and (oppositeEnd.oclIsKindOf(Product) or oppositeEnd.oclIsKindOf(TechnicalTerm) or oppositeEnd.oclIsKindOf(File) or oppositeEnd.oclIsKindOf(Document) or oppositeEnd.oclIsKindOf(Cluster) or oppositeEnd.oclIsKindOf(CardFile))) or (oppositeEnd.oclIsTypeOf(Function) and (self.oclIsKindOf(Product) or self.oclIsKindOf(TechnicalTerm) or self.oclIsKindOf(File) or self.oclIsKindOf(Document) or self.oclIsKindOf(Cluster) or self.oclIsKindOf(CardFile))))", //$NON-NLS-1$
				
				//Regeln RELATION
				"((self <> oppositeEnd) and (self.oclIsKindOf(Function) or self.oclIsKindOf(PersonType) or self.oclIsKindOf(Objective) or self.oclIsKindOf(Participant) or self.oclIsKindOf(Group) or self.oclIsKindOf(Location) or self.oclIsKindOf(Position) or self.oclIsKindOf(InternalPerson) or self.oclIsKindOf(ExternalPerson) or self.oclIsKindOf(Application)))", //$NON-NLS-1$
				"((self.oclIsTypeOf(Function) and (oppositeEnd.oclIsKindOf(PersonType) or oppositeEnd.oclIsKindOf(Objective) or oppositeEnd.oclIsKindOf(Participant) or oppositeEnd.oclIsKindOf(Group) or oppositeEnd.oclIsKindOf(Location) or oppositeEnd.oclIsKindOf(Position) or oppositeEnd.oclIsKindOf(InternalPerson) or oppositeEnd.oclIsKindOf(ExternalPerson) or oppositeEnd.oclIsKindOf(Application))) or (oppositeEnd.oclIsTypeOf(Function) and (self.oclIsKindOf(PersonType) or self.oclIsKindOf(Objective) or self.oclIsKindOf(Participant) or self.oclIsKindOf(Group) or self.oclIsKindOf(Location) or self.oclIsKindOf(Position) or self.oclIsKindOf(InternalPerson) or self.oclIsKindOf(ExternalPerson) or self.oclIsKindOf(Application))))", //$NON-NLS-1$
		};
	}

	/**
	 * @generated NOT
	 */
	private static EpcOCLFactory getInstance() {
		EpcOCLFactory instance = EpcDiagramEditorPlugin.getInstance()
				.getEpcOCLFactory();
		if (instance == null) {
			EpcDiagramEditorPlugin.getInstance().setEpcOCLFactory(
					instance = new EpcOCLFactory());
		}
		return instance;
	}

	/**
	 * @generated NOT
	 */
	public static String getExpressionBody(int index) {
		return getInstance().expressionBodies[index];
	}

	/**
	 * @generated NOT
	 */
	public static EpcAbstractExpression getExpression(int index,
			EClassifier context, Map<String, EClassifier> environment) {
		EpcOCLFactory cached = getInstance();
		if (index < 0 || index >= cached.expressions.length) {
			throw new IllegalArgumentException();
		}
		if (cached.expressions[index] == null) {
			cached.expressions[index] = getExpression(
					cached.expressionBodies[index],
					context,
					environment == null ? Collections
							.<String, EClassifier> emptyMap() : environment);
		}
		return cached.expressions[index];
	}

	/**
	 * This is factory method, callers are responsible to keep reference to the return value if they want to reuse parsed expression
	 * @generated NOT
	 */
	public static EpcAbstractExpression getExpression(String body,
			EClassifier context, Map<String, EClassifier> environment) {
		return new Expression(body, context, environment);
	}

	/**
	 * This method will become private in the next release
	 * @generated NOT
	 */
	public static EpcAbstractExpression getExpression(String body,
			EClassifier context) {
		return getExpression(body, context,
				Collections.<String, EClassifier> emptyMap());
	}

	/**
	 * @generated NOT
	 */
	private static class Expression extends EpcAbstractExpression {

		/**
		 * @generated NOT
		 */
		private final org.eclipse.ocl.ecore.OCL oclInstance;

		/**
		 * @generated NOT
		 */
		private OCLExpression oclExpression;

		/**
		 * @generated NOT
		 */
		public Expression(String body, EClassifier context,
				Map<String, EClassifier> environment) {
			super(body, context);
			oclInstance = org.eclipse.ocl.ecore.OCL.newInstance();
			initCustomEnv(oclInstance.getEnvironment(), environment);
			Helper oclHelper = oclInstance.createOCLHelper();
			oclHelper.setContext(context());
			try {
				oclExpression = oclHelper.createQuery(body());
				setStatus(IStatus.OK, null, null);
			} catch (ParserException e) {
				setStatus(IStatus.ERROR, e.getMessage(), e);
			}
		}

		/**
		 * @generated NOT
		 */
		@SuppressWarnings("rawtypes")
		protected Object doEvaluate(Object context, Map env) {
			if (oclExpression == null) {
				return null;
			}
			// on the first call, both evalEnvironment and extentMap are clear, for later we have finally, below.
			EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = oclInstance
					.getEvaluationEnvironment();
			// initialize environment
			for (Object nextKey : env.keySet()) {
				evalEnv.replace((String) nextKey, env.get(nextKey));
			}
			try {
				Object result = oclInstance.evaluate(context, oclExpression);
				return oclInstance.isInvalid(result) ? null : result;
			} finally {
				evalEnv.clear();
				oclInstance.setExtentMap(null); // clear allInstances cache, and get the oclInstance ready for the next call
			}
		}

		/**
		 * @generated NOT
		 */
		private static void initCustomEnv(
				Environment<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, ?, ?, ?> ecoreEnv,
				Map<String, EClassifier> environment) {
			// Use EObject as implicit root class for any object, to allow eContainer() and other EObject operations from OCL expressions
			ParsingOptions.setOption(ecoreEnv,
					ParsingOptions.implicitRootClass(ecoreEnv),
					EcorePackage.eINSTANCE.getEObject());
			for (String varName : environment.keySet()) {
				EClassifier varType = environment.get(varName);
				ecoreEnv.addElement(varName,
						createVar(ecoreEnv, varName, varType), false);
			}
		}

		/**
		 * @generated NOT
		 */
		private static Variable createVar(
				Environment<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> ecoreEnv,
				String name, EClassifier type) {
			Variable var = EcoreFactory.eINSTANCE.createVariable();
			var.setName(name);
			var.setType(ecoreEnv.getUMLReflection().getOCLType(type));
			return var;
		}
	}
}
