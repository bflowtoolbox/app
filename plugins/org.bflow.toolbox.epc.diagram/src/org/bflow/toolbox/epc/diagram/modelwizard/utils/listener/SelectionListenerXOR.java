package org.bflow.toolbox.epc.diagram.modelwizard.utils.listener;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.ConnectionManager;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ProcessStep;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;

/**
 * Implements the selection listener for the connect to menu item. 
 * @author Arian Storch
 * @since 02/02/10
 *
 */
public class SelectionListenerXOR implements SelectionListener 
{
	private ProcessStep source;
	private ProcessStep target;
	private TableViewer viewer;
	
	/**
	 * Default constructor.
	 * @param source source process step
	 * @param target target process step
	 */
	public SelectionListenerXOR(ProcessStep source, ProcessStep target, TableViewer viewer) 
	{
		this.source = source;
		this.target = target;
		this.viewer = viewer;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) 
	{
		widgetSelected(e);		
	}

	@Override
	public void widgetSelected(SelectionEvent e) 
	{
		Color c = ConnectionManager.getNextConnectionColor();
		
		source.getConnector().setConnectedWith(target.getConnector());
		target.getConnector().setConnectedWith(source.getConnector());
		
		source.getConnector().setConnectionColor(c);
		target.getConnector().setConnectionColor(c);
		
		viewer.update(new Object[]{source,target}, null);
	}



}
