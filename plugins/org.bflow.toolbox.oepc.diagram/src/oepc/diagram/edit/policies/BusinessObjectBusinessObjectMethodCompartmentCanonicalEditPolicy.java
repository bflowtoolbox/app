package oepc.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import oepc.OepcPackage;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.part.OepcDiagramUpdater;
import oepc.diagram.part.OepcNodeDescriptor;
import oepc.diagram.part.OepcVisualIDRegistry;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class BusinessObjectBusinessObjectMethodCompartmentCanonicalEditPolicy
		extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = OepcDiagramUpdater
				.getBusinessObjectBusinessObjectMethodCompartment_7002SemanticChildren(
						viewObject).iterator(); it.hasNext();) {
			result.add(((OepcNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = OepcVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case BusinessMethodEditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(OepcPackage.eINSTANCE
					.getBusinessObject_Methods());
		}
		return myFeaturesToSynchronize;
	}

}
