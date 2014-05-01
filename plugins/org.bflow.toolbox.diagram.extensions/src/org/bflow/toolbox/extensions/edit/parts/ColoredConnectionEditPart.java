package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;


/**
 * Let an <code>ConnectionNodeEditPart</code> extend me, to provide color 
 * choosing and style action.
 * @author Joerg Hartmann
 *
 */
public abstract class ColoredConnectionEditPart extends ConnectionNodeEditPart implements ColorChangeable {

	
	/**
	 * Create this edit part.
	 * @param view
	 */
	public ColoredConnectionEditPart(View view) {
		super(view);
	}

	
	/**
	 * Applies fore- and background color.
	 * @param foreground
	 * @param background
	 */
	public void applyColor(Color foreground, Color background) {
		setForegroundColor(foreground);
		setBackgroundColor(background);
	}

	
	/**
	 * Returns the edit domain.
	 * @return
	 */
	public EditDomain getEditDomain() {
		return super.getEditDomain();
	}

	
	/**
	 * Returns the connection figure.
	 * @return 
	 */
	public abstract IFigure getPrimaryFigure();

	
	/**
	 * Sets the background color.
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		// no background for connections
	}
	
	
	/**
	 * Sets the line color.
	 * @param color
	 */
	public void setForegroundColor(Color color) {
		super.setForegroundColor(color);
	}
}
