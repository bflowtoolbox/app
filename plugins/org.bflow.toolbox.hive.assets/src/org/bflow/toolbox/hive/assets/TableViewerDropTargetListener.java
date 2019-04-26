package org.bflow.toolbox.hive.assets;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.widgets.Display;

/**
 * Extends {@link DropTargetAdapter} to handle file drops on the asset link
 * table view.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class TableViewerDropTargetListener extends DropTargetAdapter {
	private final FileTransfer _fileTransfer;
	private final URLTransfer _urlTransfer;
	private final AssetLinkCollection _assetLinkCollection;
	
	/**
	 * Initializes the new instance with the given arguments.
	 * 
	 * @param fileTransfer File transfer instance
	 * @param urlTransfer  URL transfer instance
	 * @param assetLinkCollection Instance of {@link AssetLinkCollection} that is being notified
	 */
	public TableViewerDropTargetListener(FileTransfer fileTransfer, URLTransfer urlTransfer, AssetLinkCollection assetLinkCollection) {
		_fileTransfer = fileTransfer;
		_urlTransfer = urlTransfer;
		_assetLinkCollection = assetLinkCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.DropTargetAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public void drop(DropTargetEvent event) {
		TransferData transferData = event.currentDataType;
		if (_fileTransfer.isSupportedType(transferData)) {
			handleFileTransfer((String[]) _fileTransfer.nativeToJava(transferData));
			return;
		}
		
		if (_urlTransfer.isSupportedType(transferData))	{
			handleUrlTransfer((String) _urlTransfer.nativeToJava(transferData));
			return;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.DropTargetAdapter#dragOver(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragOver(DropTargetEvent event) {
		event.feedback = DND.FEEDBACK_SCROLL;
		if (event.item != null) {
			event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
		} else {
			event.feedback |= DND.FEEDBACK_SELECT;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.DropTargetAdapter#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragEnter(DropTargetEvent event) {
		TransferData data = event.currentDataType;
		
		if (_urlTransfer.isSupportedType(data))
			event.detail = DND.DROP_LINK;
		else if (_fileTransfer.isSupportedType(data) && hasNoFolder(data))
			event.detail = DND.DROP_COPY;
	}
	
	/**
	 * Returns TRUE if no path provided by the given {@code transferData} 
	 * points to a directory. 
	 */
	private boolean hasNoFolder(TransferData transferData) {
		Object obj = _fileTransfer.nativeToJava(transferData);
		String[] paths = (String[]) obj;
		
		for (int i = -1; ++i != paths.length;) {
			String path = paths[i];
			Path pathObj = Paths.get(path);
			if (Files.isDirectory(pathObj)) return false;
		}
		
		return true;
	}
	
	/** Handles the given set of {@code paths} as result of a file transfer. */
	private void handleFileTransfer(String[] paths) {
		if (paths == null) return;
		
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
			
			_assetLinkCollection.addLink(file, isSymlink);
		}		
	}
	
	/** Handles the given {@code url} as result of an URL transfer. */
	private void handleUrlTransfer(String url) {
		if (url == null) return;
		_assetLinkCollection.addLink(url);
	}
}
