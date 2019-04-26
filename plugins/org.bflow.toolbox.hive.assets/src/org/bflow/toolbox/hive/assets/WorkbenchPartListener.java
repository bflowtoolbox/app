package org.bflow.toolbox.hive.assets;

import java.util.ArrayList;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public class WorkbenchPartListener implements IPartListener {
	private final ArrayList<ICompatibleEditorListener> _listenerSet = new ArrayList<>(5);
	private final AssetLinkCollection _assetLinkCollection;
	
	public WorkbenchPartListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}
	
	public void addCompatibleEditorListener(ICompatibleEditorListener listener) {
		if (listener == null) return;
		_listenerSet.add(listener);
	}
	
	public void removeCompatibleEditorListener(ICompatibleEditorListener listener) {
		if (listener == null) return;
		_listenerSet.remove(listener);
	}
	
	/** Disposes the instance and its related resources. */
	public void dispose() {
		_listenerSet.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partActivated(IWorkbenchPart part) {
		IEditorPart editorPart = Cast.as(IEditorPart.class, part);
		if (editorPart == null) return;
		
		_assetLinkCollection.onActiveEditorPartChanged(editorPart);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partDeactivated(IWorkbenchPart part) {
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partOpened(IWorkbenchPart part) {
		// Nothing to do here		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partClosed(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// Nothing to do here
	}
	
	/**
	 * Raises {@link ICompatibleEditorListener#onEditorChanged(boolean)} with the 
	 * specified {@code isCompatible} value.
	 */
	protected void raiseEditorChangedEvent(boolean isCompatible) {
		for (int i = -1; ++i != _listenerSet.size();) {
			ICompatibleEditorListener listener = _listenerSet.get(i);
			listener.onEditorChanged(isCompatible);
		}
	}
}
