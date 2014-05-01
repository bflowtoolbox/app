package oepc.diagram.part;

import oepc.OepcPackage;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 */
public class OepcDomainModelElementTester extends PropertyTester {

	/**
	 * @generated
	 */
	public boolean test(Object receiver, String method, Object[] args,
			Object expectedValue) {
		if (false == receiver instanceof EObject) {
			return false;
		}
		EObject eObject = (EObject) receiver;
		EClass eClass = eObject.eClass();
		if (eClass == OepcPackage.eINSTANCE.getOEPC()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getEvent()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getBusinessObject()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getITSystem()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getOrganisationUnit()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getXORConnector()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getANDConnector()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getORConnector()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getControlFlowEdge()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getInformationEdge()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getBusinessObjectElement()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getBusinessAttribute()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getBusinessMethod()) {
			return true;
		}
		if (eClass == OepcPackage.eINSTANCE.getDocument()) {
			return true;
		}
		return false;
	}

}
