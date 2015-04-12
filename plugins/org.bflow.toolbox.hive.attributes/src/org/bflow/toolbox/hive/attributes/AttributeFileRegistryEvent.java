package org.bflow.toolbox.hive.attributes;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * Defines an Attribute File Registry Event.
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17.0.11
 * @version 04.04.2015 Using lower GMF object references
 */
public class AttributeFileRegistryEvent {
	
	/**
	 * Attribute File
	 */
	public AttributeFile attributeFile;
	
	/**
	 * Diagram Document Editor
	 */
	public DiagramEditor diagramEditor;

}
