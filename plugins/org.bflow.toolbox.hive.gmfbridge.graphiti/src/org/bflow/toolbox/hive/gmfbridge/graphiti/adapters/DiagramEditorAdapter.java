package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.lang.reflect.Field;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;


/**
 * Implements an adapter for
 * {@link org.eclipse.graphiti.ui.editor.DiagramEditor}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public class DiagramEditorAdapter extends DiagramEditor {
	
	org.eclipse.graphiti.ui.editor.DiagramEditor fGraphitiDiagramEditor;
	
	/**
	 * Creates a new instance based on the given graphiti editor.
	 * 
	 * @param graphitiEditor
	 */
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#getEditorInput()
	 */
	@Override
	public IEditorInput getEditorInput() {
		IEditorInput editorInput = fGraphitiDiagramEditor.getEditorInput();
		DiagramEditorInput diagramEditorInput = (DiagramEditorInput) editorInput;
		URI modelUri;
		try {
			Class<?> editorInputCls = diagramEditorInput.getClass();
			Field modelUriField = editorInputCls.getDeclaredField("modelUri");
			modelUriField.setAccessible(true);
			URI uriValue = (URI) modelUriField.get(diagramEditorInput);
			modelUriField.setAccessible(false);
			modelUri = uriValue;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		if (modelUri.isFile()) {
			// TODO
//			String uriFileString = modelUri.toFileString();
//			System.out.println();
		}

		if (modelUri.isPlatformResource()) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();

			IResource member = root.findMember(modelUri.toPlatformString(true));
			if (member != null) {
				IFile file = (IFile) member.getAdapter(IFile.class);
				editorInput = new FileEditorInput(file);
//				String uriOsString = member.getLocation().toOSString();
			}
		}
		return editorInput;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getEditingDomain()
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		TransactionalEditingDomain transactionalEditingDomain = fGraphitiDiagramEditor.getEditingDomain();
		return transactionalEditingDomain;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getDiagramEditPart()
	 */
	@Override
	public DiagramEditPart getDiagramEditPart() {
		DiagramBehavior diagramBehaviour = fGraphitiDiagramEditor.getDiagramBehavior();
		EditPart editPart = diagramBehaviour.getContentEditPart();
		
		@SuppressWarnings("restriction")
		org.eclipse.graphiti.ui.internal.parts.DiagramEditPart graphitiDiagramEditPart = (org.eclipse.graphiti.ui.internal.parts.DiagramEditPart) editPart;
		return new DiagramEditPartAdapter(graphitiDiagramEditPart, fGraphitiDiagramEditor.getGraphicalViewer());
	}
}