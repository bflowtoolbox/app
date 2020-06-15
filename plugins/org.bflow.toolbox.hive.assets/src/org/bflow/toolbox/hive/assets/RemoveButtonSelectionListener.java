package org.bflow.toolbox.hive.assets;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Extends {@link SelectionAdapter} to handle the remove button click above the
 * asset link table viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-26
 *
 */
public class RemoveButtonSelectionListener extends SelectionAdapter {
	private final AssetLinkCollection _assetLinkCollection;
	
	/**
	 * Initializes the new instance and associates it with the given 
	 * {@code assetLinkCollection}.
	 */
	public RemoveButtonSelectionListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {		
		AssetLinkOperation.removeAssetLinkFromCollection(_assetLinkCollection, (AssetLink) e.widget.getData());
	}
}
