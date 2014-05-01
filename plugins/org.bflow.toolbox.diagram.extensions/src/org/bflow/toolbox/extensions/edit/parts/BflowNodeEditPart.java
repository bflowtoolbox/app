package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.TextStyleImpl;


/**
 * This is an implementation of an edit part, which extends the 
 * <code>ColoredNodeEditPart</code>.
 * Additional to the color feature, you can change and save the text alignment.
 * Also elements, implement this class, are auto-sizeable.
 * @author Joerg Hartmann
 *
 */
public abstract class BflowNodeEditPart extends ColoredNodeEditPart 
										implements ChangeableTextAlignment, 
												   AutoSizable{

	
	/**
	 * Stores the current used text alignment.
	 */
	private TextAlignment currentAlignment;
	
	
	/**
	 * Create me.
	 * @param view
	 */
	public BflowNodeEditPart(View view) {
		super(view);
	}
	
	
	/**
	 * Refresh's all visuals like color or alignment.
	 */
	public void refreshVisuals(){
		super.refreshVisuals();
		refreshTextAlignment();
	}
	

	
	/**
	 * Refresh's the text alignment.
	 * This method will be called by refreshing the visuals of the view.
	 * Loads the alignment from the TextStyle, so the style must be contained. 
	 */
	public void refreshTextAlignment(){
		TextStyle style = (TextStyle) getPrimaryView().getStyle(
				NotationPackage.Literals.TEXT_STYLE);
		if (style != null) {
			TextAlignment alignment = style.getTextAlignment();
			currentAlignment = alignment;
			WrappingLabel[] labels = getLabels();
			for(int i = 0; i <= labels.length - 1; i++){
				setTextAlignment(labels[i], alignment);
			}
		}	
	}
	
	/**
	 * Sets the text alignment.
	 * @param label
	 * @param alignment
	 */
	public void setTextAlignment(WrappingLabel label, TextAlignment alignment){
		if(alignment == TextAlignment.CENTER_LITERAL){
			setTextAlignment(label, PositionConstants.CENTER);
		}
		else if(alignment == TextAlignment.LEFT_LITERAL){
			setTextAlignment(label, PositionConstants.LEFT);
		}
		else if(alignment == TextAlignment.RIGHT_LITERAL){
			setTextAlignment(label, PositionConstants.RIGHT);
		}
	}
	
	
	/**
	 * Sets the delivered alignment to the given label.
	 * @param label
	 * @param alignment
	 */
	public void setTextAlignment(WrappingLabel label, int alignment){
		label.setAlignment(alignment);
		label.setTextJustification(alignment);
	}
	
	
	/**
	 * Returns the current text alignment.
	 * @return 
	 */
	public TextAlignment getCurrentAlignment(){
		return currentAlignment;
	}
	
	/**
	 * Called after an <code>Notification</code> was thrown.
	 * Refreshs the text-alignment, if the notifier is an
	 * <code>TextStyleImpl</code>.
	 * @param notification
	 */
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if(notification.getNotifier() instanceof TextStyleImpl){
			refreshTextAlignment();
		}
	}
}
