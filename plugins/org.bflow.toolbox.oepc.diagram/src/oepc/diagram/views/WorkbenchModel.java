package oepc.diagram.views;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.MultiPageEditorPart;

import oepc.diagram.part.OepcDiagramEditor;

public class WorkbenchModel implements ISelectionListener {

	private OepcAssestsViewModel viewModel;
	
	private File currentAssociationsFile;
	
	/**
	 * Sets the {@code viewModel} that this class will be in exchange with.
	 * @param viewModel
	 */
	public void setViewModel(OepcAssestsViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (viewModel == null) return;
		
		viewModel.setSelectedElementId(GraphicalEditPartUtil.getElementId(getSelectedElement(selection)));

		IEditorPart activeEditor = part.getSite().getPage().getActiveEditor();
		if(isEditorOepcDiagramEditor(activeEditor)) viewModel.setDiagramEditor((DiagramEditor) activeEditor);
		
		IFile diagram = getOpenedDiagramForWorkbenchPart(part);
		File folder = aquireFolderForDiagram(diagram);
		viewModel.setFolder(folder);
		
		File associationsFile = AssociationPersistence.aquireAssociationsFile(folder);
		if (associationsFile == currentAssociationsFile) return;
		currentAssociationsFile = associationsFile;
		
		viewModel.setAssociationsFile(currentAssociationsFile);
		viewModel.setAssociations(AssociationPersistence.readAssociationsFromFile(currentAssociationsFile));
	}
	
	
	/**
	 * Gets or creates the folder in which all associations, associated and copied files
	 * will be stored to, based on the name of the currently opened diagram.
	 * @param diagram
	 * @return The folder corresponding to the diagram, or {@code null} if no diagram is open.
	 */
	static File aquireFolderForDiagram(IFile diagram) {
		if (diagram == null) return null;
		
		IPath path = diagram.getRawLocation();
		
		String folderName = "." + path.removeFileExtension().lastSegment();
		String pathString = path.removeLastSegments(1).append(folderName).toOSString();
		
		File folder = new File(pathString);
		if (!folder.exists()) folder.mkdirs();
		
		return folder;
	}
	
	/**
	 * Returns the file associated with the currently opened diagram.
	 * @param part The currently opened diagram
	 */
	static IFile getOpenedDiagramForWorkbenchPart(IWorkbenchPart part) {
		if (!(part instanceof DiagramDocumentEditor)) return null;
		IEditorInput input = ((DiagramDocumentEditor) part).getEditorInput();
			
		if (!(input instanceof IFileEditorInput)) return null;
		IFile file = ((IFileEditorInput) input).getFile();

		return file;
	}
	
	/**
	 * Extracts and returns the an {@code IGraphicalEditPart} out of the
	 * given {@code selection}.
	 * @param selection
	 */
	static IGraphicalEditPart getSelectedElement(ISelection selection) {
		if (selection == null || selection.isEmpty() || !(selection instanceof StructuredSelection)) return null;
		StructuredSelection structuredSelection = (StructuredSelection) selection;
		
		Object firstElement = structuredSelection.getFirstElement();
		if (!(firstElement instanceof IGraphicalEditPart)) return null;
		return (IGraphicalEditPart) firstElement;
	}

	/**
	 * Checks whether the supplied {@code IEditorPart} contains a {@code OepcDiagramEditor}
	 * as its currently active page.
	 * @param editorPart
	 * @return {@code true} if active page is an instance of {@code OepcDiagramEditor},
	 * {@code false} otherwise.
	 */
	static boolean isEditorOepcDiagramEditor(IEditorPart editorPart) {
		if (editorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorPart;
			editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		return editorPart instanceof OepcDiagramEditor; 
	}
}
