package org.bflow.toolbox.contributions.addons;

import org.bflow.toolbox.hive.attributes.IAttribute;
import org.bflow.toolbox.hive.attributes.IAttributeAdjustProcessor;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Implements {@link IAttributeAdjustProcessor} for handling epc specific attributes.
 * 
 * @author Arian Storch
 * @since 10/02/13
 */
public class EpcAttributeAdjustProcessor implements IAttributeAdjustProcessor {
	
	private static final String AttributeHandles[] = {
		"$reference"
	};

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.attributes.IAttributeAdjustProcessor#doesHandle(org.bflow.toolbox.attributes.IAttribute)
	 */
	@Override
	public boolean doesHandle(IAttribute attribute) {
		String attributeType = attribute.getName();
		
		for(int i = 0; i< AttributeHandles.length; i++) {
			String handle = AttributeHandles[i];
			if(handle.equalsIgnoreCase(attributeType)) {
				return true;
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.attributes.IAttributeAdjustProcessor#handle(org.bflow.toolbox.attributes.IAttribute, org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart)
	 */
	@Override
	public void handle(IAttribute attribute, DiagramEditPart diagramEditPart) {
		String attributeType = attribute.getName();
		String elementId = attribute.getElementID();
		String attributeValue = attribute.getValue();
		
		EObject eObject = resolveEObject(diagramEditPart, elementId);

		// Handle subdiagram changes
		if(attributeType.equalsIgnoreCase(AttributeHandles[0])) {
			handleSubdiagramChange(diagramEditPart, eObject, attributeValue, "set");
			return;
		}
	}
	
	/**
	 * Handles the change of the subdiagram value of a function.
	 * 
	 * @param diagramEditPart Containing diagram edit part
	 * @param eObject EObject to edit
	 * @param attributeValue New subdiagram value
	 * @param modus Change modus (SET, ADD, REMOVE)
	 */
	private void handleSubdiagramChange(DiagramEditPart diagramEditPart, 
			EObject eObject, String attributeValue, String modus) {
		// null -> 1 	| Add
		// n -> n+1 	| Add
		// 1 -> 1* 		| Set
		// n -> n-1		| Remove
		// 1 -> null	> Remove
		
		// Doing set
		if(modus.equalsIgnoreCase("set")) {
			EMFSetSubdiagramCommand cmd = new EMFSetSubdiagramCommand(eObject, attributeValue);
			EMFCommandOperation op = new EMFCommandOperation(
					diagramEditPart.getEditingDomain(), cmd);
			
			try {
				op.execute(null, null);
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
			
			return;
		}
	}

	/**
	 * Resolves the EObject instance of the given element id.
	 * 
	 * @param diagramEditPart Containing diagram edit part
	 * @param elementId element id
	 * @return Instance of EObject or null
	 */
	private EObject resolveEObject(DiagramEditPart diagramEditPart, String elementId) {
		for(Object obj:diagramEditPart.getChildren()) {
			ShapeNodeEditPart snep = (ShapeNodeEditPart)obj;
			EObject eObject = snep.resolveSemanticElement();
			
			if (EMFCoreUtil.getProxyID(eObject).
					equalsIgnoreCase(elementId)) {
				return eObject;
			}
		}
		
		return null;
	}
}
