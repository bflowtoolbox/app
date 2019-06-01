package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.actions.AbstractOpenDiagramLinkAction;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.Application;
import vcchart.Document;
import vcchart.Objective;
import vcchart.Participant;
import vcchart.Product;
import vcchart.TechnicalTerm;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;

/**
 * Action for opening a linked diagram file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class VcOpenSubdiagramAction extends AbstractOpenDiagramLinkAction {	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected String getSelectionData(ISelection selection) {
		IStructuredSelection strSel = (IStructuredSelection) selection;
		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();
		
		if (part instanceof Activity1EditPart) {
			Activity1 a1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();
			return a1.getSubdiagram();
		}
		
		if (part instanceof Activity2EditPart) {
			Activity2 a2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();
			return a2.getSubdiagram();
		}	
		
		if (part instanceof ProductEditPart) {
			Product p = (Product) ((ProductEditPart) part).resolveSemanticElement();
			return p.getSubdiagram();
		}
		
		if (part instanceof ObjectiveEditPart) {
			Objective o = (Objective) ((ObjectiveEditPart) part).resolveSemanticElement();
			return o.getSubdiagram();
		}
		
		if (part instanceof ApplicationEditPart) {
			Application a = (Application) ((ApplicationEditPart) part).resolveSemanticElement();
			return a.getSubdiagram();
		}
		
		if (part instanceof ParticipantEditPart) {
			Participant p = (Participant) ((ParticipantEditPart) part).resolveSemanticElement();
			return p.getSubdiagram();
		}
		
		if (part instanceof DocumentEditPart) {
			Document d = (Document) ((DocumentEditPart) part).resolveSemanticElement();
			return d.getSubdiagram();
		}
		
		if (part instanceof TechnicalTermEditPart) {
			TechnicalTerm t = (TechnicalTerm) ((TechnicalTermEditPart) part).resolveSemanticElement();
			return t.getSubdiagram();
		}
		
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(String selectionData) {
		return selectionData != null;
	}
}
