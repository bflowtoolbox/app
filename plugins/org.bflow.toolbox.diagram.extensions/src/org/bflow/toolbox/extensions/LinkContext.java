package org.bflow.toolbox.extensions;

import org.bflow.toolbox.extensions.actions.AbstractOpenDiagramLinkAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Provides an generic implementation of {@link ILinkContext}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class LinkContext implements ILinkContext {	
	/**
	 * Provides information about the link to open.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 2019-02-17
	 *
	 */
	@FunctionalInterface
	public interface ILinkProvider {
		/** Returns the diagram link to open. */
		String getDiagramLink();		
	}

	private final ILinkProvider _linkProvider;
	
	/**
	 * Initializes the new instance. The given {@code linkProvider} is used to get
	 * the diagram link to operate.
	 */
	public LinkContext(ILinkProvider linkProvider) {
		_linkProvider = linkProvider;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.ILinkContext#showLink()
	 */
	@Override
	public boolean showLink() {
		return _linkProvider.getDiagramLink() != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.ILinkContext#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		return _linkProvider.getDiagramLink();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.ILinkContext#onLinkDoubleClick()
	 */
	@Override
	public void onLinkDoubleClick() {
		AbstractOpenDiagramLinkAction openLinkAction = new AbstractOpenDiagramLinkAction() {			
			@Override
			protected boolean isEnabled(String selectionData) {
				return true;
			}
			
			@Override
			protected String getSelectionData(ISelection selection) {
				return _linkProvider.getDiagramLink();
			}
		};
		Action dumpAction = new Action() {};
		IWorkbenchPart workbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		openLinkAction.setActivePart(dumpAction, workbenchPart); 
		openLinkAction.selectionChanged(dumpAction, selection);
		openLinkAction.run(dumpAction);
	}
}
