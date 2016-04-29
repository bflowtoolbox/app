package org.bflow.toolbox.hive.eclipse.integration;

import org.eclipse.ui.IEditorPart;

/**
 * Describes the contribution of an editor proxy extension.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26.07.2015
 *
 */
public interface IEditorProxyInfo {

	/**
	 * Returns TRUE if this contribution can provide an editor proxy for the
	 * given editor part.
	 * 
	 * @param fileName
	 *            File name of the file that is handled by the editor part
	 * @param editorPart
	 *            Editor part that originally handles the file name
	 * @return TRUE or FALSE
	 */
	boolean canProvideProxy(String fileName, IEditorPart editorPart);

	/**
	 * Returns the editor proxy id that is used to open the editor proxy
	 * instance.
	 * 
	 * @return Editor proxy id
	 */
	String EditorProxyId();
}
