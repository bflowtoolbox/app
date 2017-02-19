package edu.toronto.cs.openome_model.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		edu.toronto.cs.openome_model.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		edu.toronto.cs.openome_model.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		edu.toronto.cs.openome_model.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		edu.toronto.cs.openome_model.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		edu.toronto.cs.openome_model.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);

	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.getInstance().getPreferenceStore();
	}
}
