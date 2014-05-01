package org.bflow.toolbox.epc.diagram.preferences;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage() {
		setPreferenceStore(EpcDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}

	public static void initDefaults(IPreferenceStore preferenceStore) {

		preferenceStore.setDefault(
				IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_POPUP_BARS,
				true);

		preferenceStore.setDefault(
				IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, true);

		preferenceStore.setDefault(
				IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANTIALIAS,
				true);

	}
}
