package org.bflow.toolbox.hive.assets;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;

/**
 * Extends {@link KeyAdapter} to handle key-bindings for the asset link table
 * viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-25
 *
 */
public class TableViewerKeyListener extends KeyAdapter {
	private final ITableViewerRemoveListener _listener;	
	
	/** Initializes the new instance and binds it to the given {@code listener}. */
	public TableViewerKeyListener(ITableViewerRemoveListener listener) {
		_listener = listener;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.keyCode != SWT.DEL) return;	
		
		if (!MessageDialog.openConfirm(
				Display.getCurrent().getActiveShell(), 
				"Asset-Verknüpfung löschen", 
				"Sind Sie sicher, dass Sie diese Verknüpfung löschen möchten?")
				) return;
		
		_listener.onRemoveItem();
	}
}
