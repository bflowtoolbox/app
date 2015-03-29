package org.bflow.toolbox.hive.gmfbridge;

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
	 * Returns the adapted instance of {@link DiagramEditor} derived from the
	 * given editor part.
	 * 
	 * @param editorPart
	 *            Editor part to adapt
	 * @return Instance of {@link DiagramEditor}
	 */
	DiagramEditor getAdapter(IEditorPart editorPart);

}
