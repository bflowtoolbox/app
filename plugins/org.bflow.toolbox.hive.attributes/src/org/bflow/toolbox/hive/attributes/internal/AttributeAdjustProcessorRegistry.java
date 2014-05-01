package org.bflow.toolbox.hive.attributes.internal;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.attributes.IAttribute;
import org.bflow.toolbox.hive.attributes.IAttributeAdjustProcessor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * Provides a registry for instances of {@link IAttributeAdjustProcessor}. These
 * processor will be asked for handling different kinds of attribute changes. If a
 * processor can handle such a change it will get the order to do it.
 * 
 * @author Arian Storch
 * @since 10/02/13
 *
 */
public class AttributeAdjustProcessorRegistry {

	private static List<IAttributeAdjustProcessor> processorRegistry = 
											new ArrayList<IAttributeAdjustProcessor>();
	
	/**
	 * Adds a processor to the registry.
	 * 
	 * @param processor Process to add
	 */
	public static void AddProcessor(IAttributeAdjustProcessor processor) {
		processorRegistry.add(processor);
	}
	
	/**
	 * Tells the registry to try to delegate the attribute change to a processor who
	 * can handle it. If no processor can be found, false will be returned.
	 * 
	 * @param attribute Attribute to process
	 * @param diagramEditPart Affected diagram edit part
	 * @return true if a processor could handle the given attribute change
	 */
	public static boolean tryToDelegate(IAttribute attribute, DiagramEditPart diagramEditPart) {
		for(int i = 0; i < processorRegistry.size(); i++) {
			IAttributeAdjustProcessor processor = processorRegistry.get(i);
			
			if(processor.doesHandle(attribute)) {
				processor.handle(attribute, diagramEditPart);
				return true;
			}
		}
		
		return false;
	}
	
}
