package oepc.diagram.extensions.edit.parts.legend;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;


/**
 * ViewFactory decorates the correspond node.
 * @author Joerg Hartmann
 * @since 0.0.7
 */
public class LegendViewFactory extends AbstractShapeViewFactory{

	
	/**
	 * Returns an empty list, because the look of the view is not changeable.
	 * @return
	 */
	protected List<Style> createStyles(View view) {
		return new ArrayList<Style>();
	}

}
