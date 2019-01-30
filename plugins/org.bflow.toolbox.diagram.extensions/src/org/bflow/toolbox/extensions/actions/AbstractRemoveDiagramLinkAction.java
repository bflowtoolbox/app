package org.bflow.toolbox.extensions.actions;

/**
 * Diagram link action base class to remove a diagram link from a model element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 * @param <TSelectionData> Selection data class
 */
public abstract class AbstractRemoveDiagramLinkAction<TSelectionData> extends AbstractDiagramLinkAction<TSelectionData, Void> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected Void getModificationValue(TSelectionData selectionData) {
		// We don't need additional values
		return null;
	}
}
