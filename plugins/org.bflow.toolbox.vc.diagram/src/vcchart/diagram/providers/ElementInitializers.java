/*
 * 
 */
package vcchart.diagram.providers;

import vcchart.diagram.part.VcDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = VcDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			VcDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
