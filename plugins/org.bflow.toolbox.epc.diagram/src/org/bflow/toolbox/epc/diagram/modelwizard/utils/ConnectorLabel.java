package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
	private Label imgLabel;
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
		Composite imgWithLabel = new Composite(composite, SWT.NONE);
		imgWithLabel.setLayout(new GridLayout(1, false));
		
		imgLabel = new Label(imgWithLabel, SWT.NONE);
				
		String imgName = type.name().toLowerCase();
		
		img = ImageFactory.getImage(imgLabel.getDisplay(), imgName+".png");
		imgHighlight = ImageFactory.getImage(imgLabel.getDisplay(), imgName+"_highlight.png");
		
		imgLabel.setImage(img);
		imgLabel.addMouseListener(listener);
		
		Label labelShortcut = new Label(imgWithLabel, SWT.NONE);
		GridData infoLabelLayoutData = new GridData(GridData.FILL_BOTH);
		infoLabelLayoutData.horizontalAlignment = SWT.CENTER;
		labelShortcut.setText(type.getShortcut());
		labelShortcut.setLayoutData(infoLabelLayoutData);
	}
	
	/**
	 * Sets the image selected.
	 * @param b true or false
	 */
	public void setSelected(boolean b)
	{
		this.selected = b;
		
		imgLabel.setImage((selected ? imgHighlight : img));
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
		return imgLabel;
	}
}
