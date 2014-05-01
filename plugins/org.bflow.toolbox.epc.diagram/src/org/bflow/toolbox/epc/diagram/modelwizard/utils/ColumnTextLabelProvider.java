package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Implements the ColumnLabelProvider. 
 * @author Arian Storch
 * @since 18/12/09
 * @version 01/02/10
 *
 */
public class ColumnTextLabelProvider extends ColumnLabelProvider 
{
	private int column;
	
	/**
	 * Default constructor.
	 * @param column column of the element within the process step 
	 */
	public ColumnTextLabelProvider(int column) 
	{
		this.column = column;
	}
	
	@Override
	public Image getImage(Object element) {
		return null;
	}
	
	@Override
	public String getText(Object element) 
	{
		if(column > ((ProcessStep)element).size()-1)
			return null;
		
		return ((ProcessStep)element).get(column).getName();
	}
	
	@Override
	public Color getBackground(Object element) 
	{
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		
		if(step.getConnector().getConnectedWith() != null)
			return step.getConnector().getConnectionColor();
		
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE ||
				type == ConnectorType.XOR_SINGLE)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		else		
			return super.getBackground(element);
	}

}
