package oepc.diagram.extensions.edit.parts.legend;

import org.eclipse.gmf.runtime.diagram.ui.util.INotationType;
import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;

/**
 * Defines an <code>INotationType</code> for the legend to create it by 
 * an <code>CreateElementRequest</code>.
 * @author Joerg Hartmann
 * @since 0.0.7
 */
public class LegendElementTypes extends AbstractElementTypeEnumerator{

	
	/**
	 * The <code>INotationType</code> for the legend.
	 */
	public static final INotationType Legend_01 = (INotationType) 
		getElementType("org.bflow.toolbox.oepc.diagram.Legend_01");
}
