package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Implements a class to handle the connection between a Label and image with connector type.
 * @author Arian Storch
 * @since 27/01/10
 *
 */
public class ConnectorLabel 
{
	private Label label;
	private Image img;
	private Image imgHighlight;
	private boolean selected;
	private ConnectorType type;
	
	/**
	 * Default constructor.
	 * @param type connector type
	 * @param composite composite
	 * @param listener mouse listener
	 */
	public ConnectorLabel(ConnectorType type, Composite composite, MouseListener listener)
	{
		this.type = type;
		
		label = new Label(composite, SWT.NONE);
				
		String imgName = type.name().toLowerCase();
		
		img = ImageFactory.getImage(label.getDisplay(), imgName+".png");
		imgHighlight = ImageFactory.getImage(label.getDisplay(), imgName+"_highlight.png");
		
		label.setImage(img);
		label.addMouseListener(listener);
	}
	
	/**
	 * Sets the image selected.
	 * @param b true or false
	 */
	public void setSelected(boolean b)
	{
		this.selected = b;
		
		label.setImage((selected ? imgHighlight : img));
	}
	
	/**
	 * Returns if the image is selected.
	 * @return true or false
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Returns the type of the connector.
	 * @return connector type
	 */
	public ConnectorType getType() {
		return type;
	}
	
	/**
	 * Returns the label associated to the image.
	 * @return associated label
	 */
	public Label getLabel() {
		return label;
	}
}
