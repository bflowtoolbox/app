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
	
	private Image XOR_SINGLE;
	
	private Image OR_SINGLE;
	
	private Image AND_SINGLE;
	
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
		
		XOR_SINGLE = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"xor.gif"));
		
		OR_SINGLE = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"or.gif"));
		
		AND_SINGLE = new Image(composite.getDisplay(), 
				this.getClass().
				getResourceAsStream(Constants.IMGPACKAGE+"and.gif"));
		
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
					
		Element el = step.get(column);
		
		if(el.getName().isEmpty())
			return NONE;
		
		if(el.getKind() == Kind.Event)
			return EVENT;
		
		if(el.getKind() == Kind.XOR_Single)
			return XOR_SINGLE;
		
		if(el.getKind() == Kind.OR_Single)
			return OR_SINGLE;
		
		if(el.getKind() == Kind.AND_Single)
			return AND_SINGLE;
		
		if(el.getKind() == Kind.Function)
			return FUNCTION;
		
		return NONE;
	}
	
	@Override
	public Color getBackground(Object element) {
		
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		//KANN WAHRSCHEINLICH SPÄTER ENTFERNT WERDEN, WENN KOMPLETT AUF SINGLE KONNEKTOREN ALS KIND UMGESTELLT IST
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE ||
				type == ConnectorType.XOR_SINGLE)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		else		
			return super.getBackground(element);
	}
}
