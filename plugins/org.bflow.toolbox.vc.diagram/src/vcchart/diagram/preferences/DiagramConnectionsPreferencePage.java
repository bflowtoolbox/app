/*
 * 
 */
package vcchart.diagram.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.jface.preference.IPreferenceStore;

import vcchart.diagram.part.VcDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramConnectionsPreferencePage() {
		setPreferenceStore(VcDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}
	
	/**
	* Initializes the default preference values for this preference store.
	* 
	* @param preferenceStore
	*            the preference store
	*
	* @generated NOT
	*/
	public static void initDefaults(IPreferenceStore preferenceStore) {
	  preferenceStore.setDefault(IPreferenceConstants.PREF_LINE_STYLE, Routing.RECTILINEAR);
	}
}
