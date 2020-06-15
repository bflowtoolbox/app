package org.bflow.toolbox.hive.assets;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
 * Implements {@link IStructuredContentProvider} to provide the content of the
 * asset link table.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class TableViewerContentProvider implements IStructuredContentProvider {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		AssetLinkCollection assetLinkCollection = Cast.as(AssetLinkCollection.class, inputElement);
		if (assetLinkCollection == null) return new AssetLink[0];
		return assetLinkCollection.toArray();
	}

}
