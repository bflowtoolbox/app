package org.bflow.toolbox.hive.assets;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;

/**
 * Provides methods to add/remove {@link AssetLink} to/from an
 * {@link AssetLinkCollection} with common UI features like confirmation
 * dialogs.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-26
 *
 */
public class AssetLinkOperation {
	/**
	 * Adds each path of the given {@code paths} set to the given {@code assetLinkCollection}. 
	 * A question dialog is shown to the user to determine the kind (hard-copy vs. symbolic link) 
	 * of linkage. If the user cancels the question dialog, nothing happens.
	 */
	public static void addAssetLinkToCollection(AssetLinkCollection assetLinkCollection, String[] paths) {
		String keyRemember = "assetlink.dialog.remember";
		String keyValue    = "assetlink.dialog.decision"; 
		IPreferenceStore prefStore = AssetsViewPlugin.getDefault().getPreferenceStore();
		String value = prefStore.getString(keyRemember);
		int rc = IDialogConstants.CANCEL_ID;
		
		if (value != null && value.equals("always")) {
			rc = prefStore.getInt(keyValue);
		} else {
			MessageDialogWithToggle dlg = MessageDialogWithToggle.openYesNoCancelQuestion(
					Display.getCurrent().getActiveShell(), 
					"Asset-Verknüpfung anlegen", // Title
					"Wollen Sie nur eine symbolische Verknüpfung anlegen?", 
					null,      // "Nicht mehr Fragen", 
					false,     // Initial state
					prefStore, // IPreferencesStore
					keyRemember // Key
			 );
			rc = dlg.getReturnCode();
			
			if (dlg.getToggleState()) {
				prefStore.setValue(keyValue, rc);
			} else {
				prefStore.setToDefault(keyValue);
				prefStore.setToDefault(keyRemember);
			}
		}

		if (rc == IDialogConstants.CANCEL_ID) return;
		
		boolean isSymlink = rc == IDialogConstants.YES_ID;
		
		for (int i = -1; ++i != paths.length;) {
			String path = paths[i];
			File file = new File(path);		
			
			assetLinkCollection.addLink(file, isSymlink);
		}	
	}
	
	/**
	 * Removes the given {@code assetLink} from the given {@code assetLinkCollection} if 
	 * the user confirms a dialog.
	 */
	public static void removeAssetLinkFromCollection(AssetLinkCollection assetLinkCollection, AssetLink assetLink) {
		if (!MessageDialog.openConfirm(
				Display.getCurrent().getActiveShell(), 
				"Asset-Verknüpfung löschen", 
				"Sind Sie sicher, dass Sie diese Verknüpfung löschen möchten?")
				) return;
		
		assetLinkCollection.removeLink(assetLink);
	}
}
