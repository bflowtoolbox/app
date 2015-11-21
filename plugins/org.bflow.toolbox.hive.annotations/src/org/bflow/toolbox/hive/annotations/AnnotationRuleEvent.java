package org.bflow.toolbox.hive.annotations;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * used to store the diagramEditor which should be notified, that a annotation
 * rule has changed
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 11.06.2015
 *
 */
public class AnnotationRuleEvent {

	/**
	 * Diagram Document Editor
	 */
	public DiagramEditor diagramEditor;

}
