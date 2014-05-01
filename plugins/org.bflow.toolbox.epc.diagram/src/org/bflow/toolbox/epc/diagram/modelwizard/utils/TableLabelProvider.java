package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * Implements the LabelProvider interface for providing table layout content.
 * @author Arian Storch
 * @since 03/12/09
 * @deprecated
 */
public class TableLabelProvider extends LabelProvider implements
		ITableLabelProvider 
		{

	private Image FUNCTION; 
	
	private Image EVENT; 
	
	private Image NONE;
	
	/**
	 * Default constructor.
	 * @param c Composite that holds the images
	 */
	public TableLabelProvider(Composite c) 
	{
		FUNCTION = new Image(c.getDisplay(), 
				this.getClass().
				getResourceAsStream("/org/bflow/toolbox/epc/diagram/elementgeneration/pages/img/function.png"));
		
		EVENT = new Image(c.getDisplay(), 
				this.getClass().
				getResourceAsStream("/org/bflow/toolbox/epc/diagram/elementgeneration/pages/img/event.png"));
		
		NONE = new Image(c.getDisplay(), 
				this.getClass().
				getResourceAsStream("/org/bflow/toolbox/epc/diagram/elementgeneration/pages/img/none.png"));
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) 
	{				
		ProcessStep processStep = (ProcessStep)element;
		Element el = null;
		
	
		
		if(columnIndex > processStep.size())
			return null;
		
		if(columnIndex == 0)
			el = processStep.get(0);
		
		if(columnIndex == 2)
			el = processStep.get(1);
		
		if(columnIndex == 4)
			el = processStep.get(2);
		
		if(el != null)
		{			
			if(el.getKind() == Element.Kind.Event)
				return EVENT;
			
			if(el.getKind() == Element.Kind.Function)
				return FUNCTION;
			
			if(el.getKind() == Element.Kind.Null)
				return NONE;
		}
		
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) 
	{						
		ProcessStep processStep = (ProcessStep)element;
		Element el = null;
		
		if(columnIndex > processStep.size())
			return "";
		
		if(columnIndex == 1)
			el = processStep.get(0);
		
		if(columnIndex == 3)
			el = processStep.get(1);
		
		if(columnIndex == 5)
			el = processStep.get(2);
		
		if(el != null)		
			return el.getName();
		
		return "";
		
	}

}
