package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import java.util.Vector;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Implements a ColumnLabelProvider.
 * @author Arian Storch
 * @since 18/12/09
 *
 */
public class ColumnConnectorLabelProvider extends ColumnLabelProvider 
{
	private Composite composite;
	
	private Vector<ProcessStep> steps;
	
	/**
	 * Default constructor.
	 * @param composite Composite which holds the images
	 * @param steps Vector of ProcessSteps shown in the table
	 */
	public ColumnConnectorLabelProvider(Composite composite, Vector<ProcessStep> steps) {
		this.composite = composite;
		this.steps = steps;
	}
	
	@Override
	public Image getImage(Object element) 
	{		
		ProcessStep step = (ProcessStep)element;
		Connector c = step.getConnector();
		
		int pos = steps.indexOf(step);
		int begin = -1;
		int end = -1;
		
		if(c.getConnectorType() == ConnectorType.NONE)
			return null;
		
		for(int i = 0; i < steps.size(); i++)
		{
			if(steps.get(i).getConnector() == c && begin == -1)
				begin = i;
			
			if(steps.get(i).getConnector() != c && begin != -1)
			{
				end = i-1;
				break;
			}
		}
		
		if(end == -1)
			end = steps.size()-1;
		
		int dLength = end-begin;
		
		// extra Behandlung für xor iteration
		if(step.getConnector().getConnectorType() == ConnectorType.XOR_IT)
			return getXorItImage(begin, pos, end, step);
		
		// Rest
		
		if(dLength == 0)
			return step.getConnector().getConnectorType().getImage(composite);
		
		if(dLength == 1)
		{
			if(pos == begin)
				return step.getConnector().getConnectorType().getImage(composite);
			else
				return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"output.png"));
		}
		
		if(dLength == 2)
		{
			if(pos == begin)
				return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"input.png"));
			
			if(pos == end)
				return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"output.png"));
			
			return step.getConnector().getConnectorType().getImage(composite);
		}
		
		if(dLength > 2)
		{
			if(pos == begin)
				return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"input.png"));
			
			if(pos == end)
				return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"output.png"));
			
			if(pos == ((begin+end)/2))
					return step.getConnector().getConnectorType().getImage(composite);
			
			return new Image(composite.getDisplay(), this.getClass().getResourceAsStream(Constants.IMGPACKAGE+"throughput.png"));
		}
		
		return null;
	}
	
	private Image getXorItImage(int begin, int position, int end, ProcessStep step)
	{
				
		if(position == begin || position == end)
			return step.getConnector().getConnectorType().getImage(composite);
		
		if(position == begin+1)
			return ImageFactory.getImage(composite.getDisplay(), "throughputitin.png");
		
		if(position == end-1)
			return ImageFactory.getImage(composite.getDisplay(), "throughputitout.png");
		
		if(position > begin && position < end)
			return ImageFactory.getImage(composite.getDisplay(), "throughputit.png");
		
		return null;
	}
	
	@Override
	public String getText(Object element) {
		return null;
	}
	
	@Override
	public Color getBackground(Object element) 
	{
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE ||
				type == ConnectorType.XOR_SINGLE)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		else		
			return super.getBackground(element);
	}
	
	
}
