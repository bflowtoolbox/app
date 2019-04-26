package org.bflow.toolbox.hive.assets;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * Extends {@link SelectionAdapter} to handle the add button click above the
 * asset link table viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-26
 *
 */
public class AddButtonSelectionListener extends SelectionAdapter {
	private final AssetLinkCollection _assetLinkCollection;
	
	/**
	 * Initializes the new instance and associates it with the given 
	 * {@code assetLinkCollection}.
	 */
	public AddButtonSelectionListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
		fd.setText("Asset-Verknüpfung auswählen");
		
		String path = fd.open();
		if (path == null) return;
		
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) return;
		
		boolean isSymlink = true; // TODO
		_assetLinkCollection.addLink(file, isSymlink);
	}
}
