package org.bflow.toolbox.epc.diagram.edit.commands;

import org.bflow.toolbox.epc.EpcPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EventCreateCommand extends CreateElementCommand {
	
	/**
	 * @generated
	 */
	public EventCreateCommand(CreateElementRequest req) {
		super(req);
	}
	
	@Override
	protected EObject doDefaultElementCreation() {
		
		EObject obj =  super.doDefaultElementCreation();
		
		return obj;
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return EpcPackage.eINSTANCE.getEpc();
	}
}
