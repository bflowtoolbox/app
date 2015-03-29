package org.bflow.toolbox.hive.gmfbridge.graphiti.internal;

import org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Provides an implementation of {@link IGmfEditPartAdapterFactory} to adapt
 * graphiti edit parts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public class GraphitiGmfEditPartAdapterFactory implements IGmfEditPartAdapterFactory {

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#canAdapt(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean canAdapt(IEditorPart editorPart) {
		boolean canAdapt = (org.eclipse.graphiti.ui.editor.DiagramEditor.class.isAssignableFrom(editorPart.getClass()));
		return canAdapt;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#getAdapter(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public DiagramEditor getAdapter(IEditorPart editorPart) {
		return new DiagramEditorAdapter((org.eclipse.graphiti.ui.editor.DiagramEditor) editorPart);
	}

	class DiagramEditorAdapter extends DiagramEditor {
		
		org.eclipse.graphiti.ui.editor.DiagramEditor fGraphitiDiagramEditor;
		
		public DiagramEditorAdapter(org.eclipse.graphiti.ui.editor.DiagramEditor graphitiEditor) {
			fGraphitiDiagramEditor = graphitiEditor;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public void doSave(IProgressMonitor monitor) {
			throw new RuntimeException("#AS not implemented yet");
		}
		
		@Override
		public DiagramEditPart getDiagramEditPart() {
			// TODO Auto-generated method stub
			return super.getDiagramEditPart();
		}
	}
}
