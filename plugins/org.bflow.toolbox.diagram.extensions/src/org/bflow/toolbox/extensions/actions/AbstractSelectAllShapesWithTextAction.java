package org.bflow.toolbox.extensions.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bflow.toolbox.extensions.NLSupport;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Provides a basic implementation of {@link DiagramAction} to support an action that selects all edit
 * parts which have a editable text field.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 21/07/13
 */
public abstract class AbstractSelectAllShapesWithTextAction extends DiagramAction {
	
	// This code is mostly copied from org.eclipse.gmf.runtime.diagram.ui.actions.internal.SelectAllAction
	
	/**
	 * Id of this action.
	 */
	public static final String Id = "selectAllShapesWithText"; //$NON-NLS-1$
	
	/**
	 * Creates a new instance bound to the given workbench page.
	 * 
	 * @param workbenchPage Workbench page 
	 */
	public AbstractSelectAllShapesWithTextAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
		setId(Id);
		setText(NLSupport.AbstractSelectAllShapesWithTextAction_Text);
		setToolTipText(NLSupport.AbstractSelectAllShapesWithTextAction_Tooltip);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.gmf.runtime.diagram.ui.actions", "icons/elcl16/selectshapes.gif")); //$NON-NLS-1$ //$NON-NLS-2$
//		setActionDefinitionId(Id);
	}
	
	@Override
	protected List<EditPart> createOperationSet() {
		List<?> selection = getSelectedObjects();
		if (selection.isEmpty() || !(selection.get(0) instanceof IGraphicalEditPart))
			return Collections.emptyList();

		EditPart primaryEP = (EditPart) selection.get(selection.size() - 1);
		
		// Looking for the DiagramEditPart
		while(!(primaryEP instanceof DiagramEditPart)) {
			primaryEP = primaryEP.getParent();
		}
		
		DiagramEditPart diagramEditPart = (DiagramEditPart) primaryEP;
		
		List<EditPart> selectableEditParts = new ArrayList<EditPart>();

		for(Object node:diagramEditPart.getChildren()) {
			EditPart editPart = (EditPart) node;
			if(isSelectable(editPart))
				selectableEditParts.add(editPart);
		}

		return selectableEditParts;
	}
	
	/**
	 * Return true if the given edit part shall be selected by this action.
	 * 
	 * @param editPart Edit part that is selectable
	 * @return True if the given edit part shall be selected
	 */
	protected boolean isSelectable(EditPart editPart) {
		Predicate<EditPart> predicate = getMatcher();
		return predicate.is(editPart);
	}
	
	/**
	 * Returns the matcher.
	 *
	 * @return the matcher
	 */
	protected abstract Predicate<EditPart> getMatcher();
	
	/**
	 * @return The Selection Conditional which tests if the edit part is selectable
	 */
	protected EditPartViewer.Conditional getSelectionConditional() {
		return new EditPartViewer.Conditional() {
			public boolean evaluate(EditPart editpart) {
				return editpart.isSelectable();
			}
		};
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		getDiagramGraphicalViewer().setSelection(
				new StructuredSelection(getOperationSet()));
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
	 */
	@Override
	protected boolean isSelectionListener() {
		return true;
	}
	
	/**
	 * The Interface Predicate.
	 *
	 * @param <K> the key type
	 */
	protected interface Predicate<K> {
		
		/**
		 * Checks if is.
		 *
		 * @param item the item
		 * @return true, if successful
		 */
		public boolean is(K item);
	}
}
