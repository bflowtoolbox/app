package org.bflow.toolbox.hive.gmfbridge;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Factory to create adapters for {@link DiagramEditor}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public interface IGmfEditPartAdapterFactory {

	/**
	 * Returns TRUE if the factory can create an adapter for the given editor
	 * part.
	 * 
	 * @param editorPart
	 *            Editor part to adapt
	 * @return TRUE or FALSE
	 */
	boolean canAdapt(IEditorPart editorPart);
	
	/**
	 * Returns TRUE if the factory can create an adapter for the given graphical
	 * edit part.
	 * 
	 * @param graphicalEditPart
	 *            Graphical edit part to adapt
	 * @return TRUE or FALSE
	 */
	boolean canAdapt(org.eclipse.gef.GraphicalEditPart graphicalEditPart);

	/**
	 * Returns the adapted instance of {@link DiagramEditor} derived from the
	 * given editor part.
	 * 
	 * @param editorPart
	 *            Editor part to adapt
	 * @return Instance of {@link DiagramEditor}
	 */
	DiagramEditor getAdapter(IEditorPart editorPart);
	
	/**
	 * Returns the adapted instance of {@link GraphicalEditPart} derived from
	 * the given graphical edit part.
	 * 
	 * @param graphicalEditPart
	 *            Graphical edit part to adapt
	 * @return Instance of {@link GraphicalEditPart}
	 */
	IGraphicalEditPart getAdapter(org.eclipse.gef.GraphicalEditPart graphicalEditPart);
}
