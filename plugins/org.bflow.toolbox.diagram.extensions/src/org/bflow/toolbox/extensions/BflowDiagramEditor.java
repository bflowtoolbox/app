package org.bflow.toolbox.extensions;

import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Implements a base {@link DiagramDocumentEditor} that provides access to 
 * bflow toolbox features that all diagram editors (for instance EPC, oEPC ...) have 
 * in common. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-07
 *
 */
public abstract class BflowDiagramEditor extends DiagramDocumentEditor {
	/**
	 * Initializes the new instance.
	 * 
	 * @param hasFlyoutPalette creates a palette if true, else no palette
	 */
	protected BflowDiagramEditor(boolean hasFlyoutPalette) {
		super(hasFlyoutPalette);
	}
}
