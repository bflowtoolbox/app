package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;


/**
 * Let edit parts implement me to switch their color.
 * @author Joerg Hartmann
 *
 */
public interface ColorChangeable {
	
	
	/**
	 * Sets the background color.
	 * @param color
	 */
	public void setBackgroundColor(Color color);
	
	
	/**
	 * Sets the foreground color.
	 * @param color
	 */
	public void setForegroundColor(Color color);
	
	
	/**
	 * Returning the primary figure.
	 * E.g. the polygon representing the event, not the node plate.
	 * Subclasses must implement this.
	 * @return the figure representing the node
	 */
	public IFigure getPrimaryFigure();
	
	
	/**
	 * Applies the delivered colors.
	 * @param foreground
	 * @param background
	 */
	public void applyColor(Color foreground, Color background);
	
	
	/**
	 * Returns the used edit domain.
	 * @return the edit domain
	 */
	public EditDomain getEditDomain();
	
	
	/**
	 * Returns the editing domain.
	 * @return
	 */
	public TransactionalEditingDomain getEditingDomain();
	
	
	/**
	 * Returns the view.
	 * @return
	 */
	public View getPrimaryView();
}
