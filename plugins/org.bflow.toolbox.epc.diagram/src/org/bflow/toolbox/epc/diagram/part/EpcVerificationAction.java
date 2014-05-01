package org.bflow.toolbox.epc.diagram.part;

import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated NOT
 */
public class EpcVerificationAction implements IObjectActionDelegate {

	/**
	 * @generated NOT
	 */
	private EpcEditPart mySelectedElement;

	/**
	 * @generated NOT
	 */
	private Shell myShell;

	/**
	 * @generated NOT
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();
	}

	/**
	 * @generated NOT
	 */
	public void run(IAction action) {
		
		Epc epc = (Epc) mySelectedElement.getDiagramView().getElement();
		for (Element e : epc.getElements())
		  System.out.print(e.getName()+" ");
		
		System.out.println();
		
	}

	/**
	 * @generated NOT
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof EpcEditPart) {
				mySelectedElement = (EpcEditPart) structuredSelection
						.getFirstElement();
			}
		}
		action.setEnabled(isEnabled());
	}

	/**
	 * @generated NOT
	 */
	private boolean isEnabled() {
		return mySelectedElement != null;
	}

}
