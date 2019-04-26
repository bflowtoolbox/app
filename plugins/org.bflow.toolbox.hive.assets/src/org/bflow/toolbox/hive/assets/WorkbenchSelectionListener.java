package org.bflow.toolbox.hive.assets;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements {@link ISelectionListener} to notify an associated
 * {@link AssetLinkCollection} about a selection changed event.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class WorkbenchSelectionListener implements ISelectionListener {
	private final AssetLinkCollection _assetLinkCollection;
	
	/**
	 * Initializes the instance with the given argument.
	 * 
	 * @param assetLinkCollection Instance of {@link AssetLinkCollection} to notify
	 */
	public WorkbenchSelectionListener(AssetLinkCollection assetLinkCollection) {
		_assetLinkCollection = assetLinkCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		DiagramEditor diagramEditor = Cast.as(DiagramEditor.class, part);
		if (diagramEditor == null) return;
		
		IStructuredSelection structSelection = Cast.as(IStructuredSelection.class, selection);
		if (structSelection == null) return;
		
		Object selectedObject = structSelection.getFirstElement();
		IGraphicalEditPart editPart = Cast.as(IGraphicalEditPart.class, selectedObject);
		if (editPart == null) return;
		
		_assetLinkCollection.onSelectedEditPartChanged(editPart);
	}
}
