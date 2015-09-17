package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import java.util.Vector;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.eclipse.jface.viewers.ISelection;

/**
 * Defines a process step which contains single or multiple elements.
 * @author Arian Storch
 * @since 03/12/09
 * @version 01/02/10
 *
 */
public class ProcessStep implements ISelection
{
	private Vector<Element> elements = new Vector<Element>();
	private Connector connector;
	
	/**
	 * Default constructor.
	 * @param connector connector
	 */
	public ProcessStep(Connector connector) 
	{
		this.connector = connector;
	}
	
	/**
	 * Returns the connector.
	 * @return connector
	 */
	public Connector getConnector() {
		return connector;
	};
	
	/**
	 * Sets the connector.
	 * @param connector connector
	 */
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
	
	/**
	 * Adds an element to the process step.
	 * @param el element to add.
	 */
	public void add(Element el)
	{
		elements.add(el);
	}
	
	/**
	 * Returns the element of the process step at the given position.
	 * @param i position of the element
	 * @return element at the given position
	 */
	public Element get(int i)
	{
		return elements.get(i);
	}
	
	/**
	 * Returns the last element of the process step.
	 * @return last element of the process step
	 */
	public Element lastElement()
	{
		return elements.lastElement();
	}
	
	/**
	 * Returns the size of the process step.
	 * @return size of the process step
	 */
	public int size()
	{
		return elements.size();
	}
	
	/**
	 * Sets an element at the given position. Use this to replace something.
	 * @param el element
	 * @param index index
	 */
	public void set(Element el, int index)
	{
		elements.set(index, el);
	}
	
	/**
	 * Sets the size of the process step.
	 * @param size new size
	 */
	public void setSize(int size)
	{
		elements.setSize(size);
	}
	
	/**
	 * Returns true if this process step holds no need information and can be ignored.
	 * @return true if it can be ignored
	 */
	public boolean isReducable()
	{
		ConnectorType type = this.getConnector().getConnectorType();
		
		if(type == ConnectorType.AND_SINGLE || 
				type == ConnectorType.OR_SINGLE || 
				type == ConnectorType.XOR_SINGLE)
			return false;
		
		for(Element el:elements)
			if(!el.isReducable())
				return false;
		
		return true;
	}
	
	/**
	 * Returns the spreading of the process step.
	 * @return spreading of the process step.
	 */
	public int getSpreading()
	{
		int count = 0;
		
		for(Element el:elements)
			if(!el.isReducable())
				count++;
		
		return count;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
