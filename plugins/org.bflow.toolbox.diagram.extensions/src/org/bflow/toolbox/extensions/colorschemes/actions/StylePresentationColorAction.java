package org.bflow.toolbox.extensions.colorschemes.actions;

import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.colorschemes.PresentationColorSchema;

/**
 * Extends {@link AbstractStyleAction} to provide the action for applying 
 * the {@link PresentationColorSchema} to a diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01/11/13
 */
public class StylePresentationColorAction extends AbstractStyleAction {

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colorschemes.actions.AbstractStyleAction#getColorSchema()
	 */
	@Override
	protected IGlobalColorSchema getColorSchema() {
		return new PresentationColorSchema();
	}
}
