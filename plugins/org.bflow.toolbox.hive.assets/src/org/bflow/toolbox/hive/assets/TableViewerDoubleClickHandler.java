package org.bflow.toolbox.hive.assets;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Implements {@link IDoubleClickListener} to open a linked asset 
 * when a double click is made.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class TableViewerDoubleClickHandler implements IDoubleClickListener {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	@Override
	public void doubleClick(DoubleClickEvent event) {
		ISelection selection = event.getSelection();
		if (selection.isEmpty()) return;
		
		IStructuredSelection structSelection = Cast.as(IStructuredSelection.class, selection);
		if (structSelection == null) return;
		
		AssetLink assetLink = Cast.as(AssetLink.class, structSelection.getFirstElement());
		if (assetLink == null) return;
		
		try {
			if (assetLink.LinkType() == EAssetLinkType.Url) {
				Desktop.getDesktop().browse(new URI(assetLink.AssetUrl()));
				return;
			}
			
			Desktop.getDesktop().open(new File(assetLink.AssetUrl()));
		} catch (Exception ex) {
			AssetsViewPlugin.getDefault().logError("Error on opening asset link", ex);
		}		
	}
}
