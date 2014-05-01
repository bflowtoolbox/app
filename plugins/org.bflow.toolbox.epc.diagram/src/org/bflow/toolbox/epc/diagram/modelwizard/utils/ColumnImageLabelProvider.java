package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element.Kind;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Implements the ColumnLabelProvider.
 * @author Arian Storch
 * @since 18/12/09
 *
 */
public class ColumnImageLabelProvider extends ColumnLabelProvider 
{	
	private Image FUNCTION; 
	
	private Image EVENT; 
	
	private Image NONE;
	
	private int column;
	
	/**
	 * Default constructor.
	 * @param composite composite that holds the images
	 * @param column column of the element within the process step
	 */
	public ColumnImageLabelProvider(Composite composite, int column) 
	{
		FUNCTION = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"function.png"));
		
		EVENT = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"event.png"));
		
		NONE = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"none.png"));
		
		this.column = column;
	}
	
	@Override
	public String getText(Object element) {
		return null;
	}
	
	@Override
	public Image getImage(Object element) 
	{
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		
		if(column > step.size()-1)
				return null;
		
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE ||
				type == ConnectorType.XOR_SINGLE)
			return null;
			
		Element el = step.get(column);
		
		if(el.getName().isEmpty())
			return NONE;
		
		if(el.getKind() == Kind.Event)
			return EVENT;
		
		if(el.getKind() == Kind.Function)
			return FUNCTION;
		
		return NONE;
	}
	
	@Override
	public Color getBackground(Object element) {
		
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE ||
				type == ConnectorType.XOR_SINGLE)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		else		
			return super.getBackground(element);
	}
}
