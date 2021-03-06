package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractRemoveDiagramLinkAction;
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
 * Action for removing a linked diagram from a VC element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 * 
 */
public class VcRemoveSubdiagramAction extends AbstractRemoveDiagramLinkAction<VcRemoveSubdiagramAction.SelectionData> {
	
	class SelectionData {
		public Activity1 _activity1;
		public Activity2 _activity2;
		public Product _product;
		public Objective _objective;
		public Application _application;
		public Participant _participant;
		public Document _document;
		public TechnicalTerm _technicalTerm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		IStructuredSelection strSel = (IStructuredSelection) selection;
		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof Activity1EditPart) {
			sd._activity1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();			
		} else if (part instanceof Activity2EditPart) {
			sd._activity2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();			
		} else if ( part instanceof ProductEditPart) {
			sd._product = (Product) ((ProductEditPart) part).resolveSemanticElement();
		} else if (part instanceof ObjectiveEditPart) {
			sd._objective = (Objective) ((ObjectiveEditPart) part).resolveSemanticElement();
		} else if (part instanceof ApplicationEditPart) {
			sd._application = (Application) ((ApplicationEditPart) part).resolveSemanticElement();
		} else if (part instanceof ParticipantEditPart) {
			sd._participant = (Participant) ((ParticipantEditPart) part).resolveSemanticElement();
		} else if (part instanceof DocumentEditPart) {
			sd._document = (Document) ((DocumentEditPart) part).resolveSemanticElement();
		} else if (part instanceof TechnicalTermEditPart) {
			sd._technicalTerm = (TechnicalTerm) ((TechnicalTermEditPart) part).resolveSemanticElement();
		}
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData sd) {
		boolean isEnabled = true;
		if (sd._activity1 != null)
			isEnabled &= sd._activity1.getSubdiagram() != null;
		
		if (sd._activity2 != null)
			isEnabled &= sd._activity2.getSubdiagram() != null;
		
		if (sd._product != null)
			isEnabled &= sd._product.getSubdiagram() != null;
		
		if (sd._objective != null)
			isEnabled &= sd._objective.getSubdiagram() != null;
		
		if (sd._application != null)
			isEnabled &= sd._application.getSubdiagram() != null;
		
		if (sd._participant != null)
			isEnabled &= sd._participant.getSubdiagram() != null;
		
		if (sd._document != null)
			isEnabled &= sd._document.getSubdiagram() != null;
		
		if (sd._technicalTerm != null)
			isEnabled &= sd._technicalTerm.getSubdiagram() != null;
		
		return isEnabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, Void modificationValue) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1,     null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2,     null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._product,       null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._objective,     null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._application,   null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._participant,   null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._document,      null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._technicalTerm, null, (e, v) -> e.setSubdiagram(null));
	}
}
