package org.bflow.toolbox.extensions.colorschemes.actions;

import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.colorschemes.OriginalColorSchema;

/**
 * This action is used to apply the original-color switch for all selected 
 * elements.
 * 
 * @author Joerg Hartman
 * @since 0.0.7
 * @version 01/11/13
 */
public class StyleOriginalColorAction extends AbstractStyleAction {
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colorschemes.actions.AbstractStyleAction#getColorSchema()
	 */
	@Override
	protected IGlobalColorSchema getColorSchema() {
		return new OriginalColorSchema();
	}
}
