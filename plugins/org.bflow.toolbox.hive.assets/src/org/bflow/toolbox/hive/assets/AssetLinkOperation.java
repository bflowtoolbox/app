package org.bflow.toolbox.hive.assets;

import org.eclipse.jface.dialogs.MessageDialog;
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
	public static void addAssetLinkToCollection(AssetLinkCollection assetLinkCollection, AssetLink assetLink) {
		
	}
	
	public static void removeAssetLinkFromCollection(AssetLinkCollection assetLinkCollection, AssetLink assetLink) {
		if (!MessageDialog.openConfirm(
				Display.getCurrent().getActiveShell(), 
				"Asset-Verkn�pfung l�schen", 
				"Sind Sie sicher, dass Sie diese Verkn�pfung l�schen m�chten?")
				) return;
		
		assetLinkCollection.remove(assetLink);
	}
}
