package org.bflow.toolbox.hive.modelnavigator;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * The interface for providers of the "nameProvider" extension point.  Implement this interface
 * to contribute an extension to the "nameProvider" extension point.  Name provider is responsible
 * for retrieving a name for a specified element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01.08.12
 */
public interface INameProvider {
	
	/**
	 * Gets the name for the given edit part. <b>Result mustn't be null!</b>
	 * 
	 * @param graphicalEditPart instance of edit part whose name is requested
	 * @return String - name of the edit part; <b>never null!</b>
	 */
	public String getName(IGraphicalEditPart graphicalEditPart);

}
