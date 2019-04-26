package org.bflow.toolbox.hive.assets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Extends {@link KeyAdapter} to handle key-bindings for the asset link table
 * viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-25
 *
 */
public class TableViewerKeyListener extends KeyAdapter {
	private final AssetLinkCollection _assetLinkCollection;
	
	/** Initializes the new instance and binds it to the given {@code listener}. */
	public TableViewerKeyListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.keyCode != SWT.DEL) return;	
				
		AssetLinkOperation.removeAssetLinkFromCollection(_assetLinkCollection, (AssetLink) e.widget.getData());
	}
}
