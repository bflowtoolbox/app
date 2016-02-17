/*
 * 
 */
package orgchart.diagram.providers;

import orgchart.diagram.part.OrgcDiagramEditorPlugin;

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
		ElementInitializers cached = OrgcDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			OrgcDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
