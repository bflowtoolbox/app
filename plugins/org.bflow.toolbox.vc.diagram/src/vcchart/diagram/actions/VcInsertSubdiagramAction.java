package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.Application;
import vcchart.Document;
import vcchart.Objective;
import vcchart.Participant;
import vcchart.Product;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.part.VcDiagramEditorPlugin;

/**
 * Action for linking existing diagrams to a VC element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 * @version 2019-02-23 AST Added orgc as linkable diagram
 *
 */
public class VcInsertSubdiagramAction extends AbstractInsertDiagramLinkAction<VcInsertSubdiagramAction.SelectionData> {
	
	static class SelectionData {
		public Activity1 _activity1;
		public Activity2 _activity2;
		public Product _product;
		public Objective _objective;
		public ShapeNodeEditPart _selectedActivity;
		public Application _application;
		public Participant _participant;
		public Document _document;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getAdapterFactory()
	 */
	@Override
	protected AdapterFactory getAdapterFactory() {
		return VcDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getFileExtensions()
	 */
	@Override
	protected String[] getFileExtensions() {
		return new String[] { "epc", "oepc", "bpmn", "vc", "orgc" };
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		ShapeNodeEditPart selectedEditPart = null;
		Activity1 activity1 = null;
		Activity2 activity2 = null;
		Product product = null;
		Objective objective = null;
		Application application = null;
		Participant participant = null;
		Document document = null;
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object selObj = structuredSelection.getFirstElement();
			
			if (selObj instanceof Activity1EditPart) {
				selectedEditPart = (ShapeNodeEditPart) selObj;
				activity1 = (Activity1) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof Activity2EditPart) {
				selectedEditPart = (ShapeNodeEditPart) selObj;
				activity2 = (Activity2) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof ProductEditPart) {
				selectedEditPart = (ShapeNodeEditPart) selObj;
				product = (Product) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof ObjectiveEditPart) {
				selectedEditPart = (ShapeNodeEditPart) selObj;
				objective = (Objective) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof ApplicationEditPart) {
				selectedEditPart = (ApplicationEditPart) selObj;
				application = (Application) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof ParticipantEditPart) {
				selectedEditPart = (ParticipantEditPart) selObj;
				participant = (Participant) selectedEditPart.getPrimaryView().getElement();
			} else if (selObj instanceof DocumentEditPart) {
				selectedEditPart = (DocumentEditPart) selObj;
				document = (Document) selectedEditPart.getPrimaryView().getElement();
			}
		}
		
		sd._selectedActivity = selectedEditPart;
		sd._activity1 = activity1;
		sd._activity2 = activity2;
		sd._product = product;
		sd._objective = objective;
		sd._application = application;
		sd._participant = participant;
		sd._document = document;
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData sd) {
		return sd._activity1 != null || sd._activity2 != null 
		      || sd._product != null || sd._objective != null
		  || sd._application != null || sd._participant != null
		  || sd._document != null
				;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getViewForSelection(java.lang.Object)
	 */
	@Override
	protected View getViewForSelection(SelectionData selectionData) {
		ShapeNodeEditPart editPart = selectionData._selectedActivity;
		if (editPart == null) return null;
		return (View) editPart.getModel();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, String path) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1,   path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2,   path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._product  ,   path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._objective,   path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._application, path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._participant, path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._document,    path, (e, v) -> e.setSubdiagram(v));
	}
}
