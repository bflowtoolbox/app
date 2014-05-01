package org.bflow.toolbox.extensions.edit.parts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;


/**
 * This is an edit part, who can change the foreground and background color.
 * @see ColorChangeable
 * @author Joerg Hartmann
 *
 */
public abstract class ColoredNodeEditPart extends AnchoredNodeEditPart 
										  implements ColorChangeable{

	/**
	 * Creates this edit part.
	 * @param view
	 */
	public ColoredNodeEditPart(View view) {
		super(view);
	}
	
	/**
	 * @see ColorChangeable
	 */
	public abstract IFigure getPrimaryFigure();
	 
	
	/**
	 * @see ColorChangeable
	 */
	@SuppressWarnings("unchecked")
	public void setBackgroundColor(Color background){
		if(getPrimaryFigure() != null){
			getPrimaryFigure().setBackgroundColor(background);
			List<IFigure> children = getPrimaryFigure().getChildren();
			applyToChildren(children, null, background);
		}	
	}
	
	
	/**
	 * @see ColorChangeable
	 */
	@SuppressWarnings("unchecked")
	public void setForegroundColor(Color foreground){
		if(getPrimaryFigure() != null){
			getPrimaryFigure().setForegroundColor(foreground);
			List<IFigure> children = getPrimaryFigure().getChildren();
			applyToChildren(children, foreground, null);
		}
	}
	
	
	/**
	 * Applies the delivered colors to all children.
	 */
	@SuppressWarnings("unchecked")
	private void applyToChildren(List<IFigure> children, Color foreground, Color background){
		for(Iterator<IFigure> figures = children.iterator(); 
			figures.hasNext();){
			IFigure figure = figures.next();
			if(foreground != null){
				figure.setForegroundColor(foreground);
			}
			if(background != null){
				figure.setBackgroundColor(foreground);
			}
			applyToChildren(figure.getChildren(), foreground, background);
		}
	}
	
	
	/**
	 * @see ColorChangeable
	 */
	public void applyColor(Color foreground, Color background){
		setForegroundColor(foreground);
		setBackgroundColor(background);
	}
	
	
	/**
	 * @see ColorChangeable
	 */
	public EditDomain getEditDomain(){
		return super.getEditDomain();
	}
}
