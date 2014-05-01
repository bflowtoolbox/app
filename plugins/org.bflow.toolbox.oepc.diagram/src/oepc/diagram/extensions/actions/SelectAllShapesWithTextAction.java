package oepc.diagram.extensions.actions;

import oepc.diagram.edit.parts.ANDConnectorEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.edit.parts.XORConnectorEditPart;

import org.bflow.toolbox.extensions.actions.AbstractSelectAllShapesWithTextAction;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Implements {@link AbstractSelectAllShapesWithTextAction} to provide the selection within 
 * OEPC diagram editors.
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
		return OEPCMatcher;
	}
	
	/** The Constant OEPCMatcher. */
	private static final Predicate<EditPart> OEPCMatcher =  new Predicate<EditPart>() {
		@Override
		public boolean is(EditPart item) {
			return !(item instanceof ANDConnectorEditPart) && !(item instanceof ORConnectorEditPart) && !(item instanceof XORConnectorEditPart);
		}};

}
