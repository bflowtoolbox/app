package org.bflow.toolbox.hive.assets;

import java.util.ArrayList;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements {@link IPartListener} to listen to workbench part changes and
 * notifies the associated {@link AssetLinkCollection}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-26
 *
 */
public class WorkbenchPartListener implements IPartListener {
	private final ArrayList<ICompatibleEditorListener> _listenerSet = new ArrayList<>(5);
	private final AssetLinkCollection _assetLinkCollection;
	private int _diagramEditorCount;
	
	/**
	 * Initializes the new instance that notifies the given
	 * {@code assetLinkCollection} when a new editor part has been opened or closed.
	 */
	public WorkbenchPartListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}
	
	/** Adds the given {@code listener}. */
	public void addCompatibleEditorListener(ICompatibleEditorListener listener) {
		if (listener == null) return;
		_listenerSet.add(listener);
	}
	
	/** Removes the given {@code listener}. */
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
		// Nothing to do here
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partOpened(IWorkbenchPart part) {
		IEditorPart editorPart = Cast.as(IEditorPart.class, part);
		if (editorPart == null) return;
		
		DiagramEditor diagramEditor = Cast.as(DiagramEditor.class, editorPart);
		if (diagramEditor == null) return;
		
		_diagramEditorCount++;
		raiseEditorChangedEvent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partClosed(IWorkbenchPart part) {
		IEditorPart editorPart = Cast.as(IEditorPart.class, part);
		if (editorPart == null) return;
		
		DiagramEditor diagramEditor = Cast.as(DiagramEditor.class, editorPart);
		if (diagramEditor == null) return;
		
		_diagramEditorCount--;
		raiseEditorChangedEvent();
		
		if (_diagramEditorCount == 0)
			_assetLinkCollection.onActiveEditorPartChanged(null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// Nothing to do here
	}
	
	/** Raises {@link ICompatibleEditorListener#onEditorChanged(boolean)}. */
	protected void raiseEditorChangedEvent() {
		boolean isCompatible = _diagramEditorCount != 0;
		for (int i = -1; ++i != _listenerSet.size();) {
			ICompatibleEditorListener listener = _listenerSet.get(i);
			listener.onEditorChanged(isCompatible);
		}
	}
}
