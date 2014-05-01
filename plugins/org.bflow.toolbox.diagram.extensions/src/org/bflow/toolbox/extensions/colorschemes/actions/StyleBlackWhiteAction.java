package org.bflow.toolbox.extensions.colorschemes.actions;

import org.bflow.toolbox.extensions.colorschemes.BlackWhiteColorSchema;
import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;

/**
 * This action is used to apply the black-white switch for all selected 
 * elements.
 * 
 * @author Joerg Hartman
 * @since 0.0.7
 * @version 01/11/13
 *
 */
public class StyleBlackWhiteAction extends AbstractStyleAction {
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colorschemes.actions.AbstractStyleAction#getColorSchema()
	 */
	@Override
	protected IGlobalColorSchema getColorSchema() {
		return new BlackWhiteColorSchema();
	}
}
