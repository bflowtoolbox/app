package org.bflow.toolbox.epc.extensions.actions;

import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.extensions.actions.AbstractSelectAllShapesWithTextAction;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Implements {@link AbstractSelectAllShapesWithTextAction} to provide the selection within 
 * EPC diagram editors.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 21/07/13
 */
public class SelectAllShapesWithTextAction extends AbstractSelectAllShapesWithTextAction {
	
	/**
	 * Creates a new instance bound to the given workbench page.
	 * 
	 * @param workbenchPage Workbench page 
	 */
	public SelectAllShapesWithTextAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractSelectAllShapesWithTextAction#getMatcher()
	 */
	protected Predicate<EditPart> getMatcher() {
		return EPCMatcher;
	}
	
	/** The Constant EPCMatcher. */
	private static final Predicate<EditPart> EPCMatcher =  new Predicate<EditPart>() {
		@Override
		public boolean is(EditPart item) {
			return !(item instanceof ANDEditPart) && !(item instanceof OREditPart) && !(item instanceof XOREditPart);
		}};
}
