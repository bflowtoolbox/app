package org.bflow.toolbox.hive.attributes;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * Defines a processor for handling diagram specific attributes.
 *  
 * @author Arian Storch
 * @since 010/02/13
 *
 */
public interface IAttributeAdjustProcessor {

	/**
	 * Returns true when this processor can handle the given attribute.
	 * 
	 * @param attribute attribute to handle
	 * @return true when this processor can handle the given attribute
	 */
	public boolean doesHandle(IAttribute attribute);
	
	/**
	 * Tells the processor to make any changes to the given diagram edit part
	 * that are needed by the given attribute.
	 * 
	 * @param attribute attribute to handle
	 * @param diagramEditPart Edit part the processor shall operate on
	 */
	public void handle(IAttribute attribute, DiagramEditPart diagramEditPart);
	
}
