package org.bflow.toolbox.hive.interchange.commons;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Provides various methods that are used in different contexts. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 11/08/13
 */
@SuppressWarnings("restriction")
public class CommonInterchangeUtil {
	
	/**
	 * Finds the associated instance of {@link IFile} for the given File pointer.
	 * 
	 * @param file
	 *            the file
	 * @return the instance of {@link IFile}
	 */
	public static IFile toIFile(File file) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath location = Path.fromOSString(file.getAbsolutePath());
		IFile iFile = workspace.getRoot().getFileForLocation(location);
		return iFile;
	}
	
	/**
	 * Creates a EMF-friendly URI.
	 * 
	 * @param file
	 *            The file
	 * @return EMF-friendly URI
	 */
	public static URI toURI(IFile file) {
		return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
	}
	
	/**
	 * Creates an instance of DiagramEditPart from the given file using an
	 * OffscreenEditPartFactory.
	 * 
	 * @param shell
	 *            Shell that is used for creating the graphical parts
	 * @param file
	 *            File which contains the diagram informations
	 * @return Instance of DiagramEditPart
	 * @throws CoreException
	 */
	public static DiagramEditPart getOffscreenDiagramEditPart(Shell shell, IFile file) throws CoreException {
		TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		
		Diagram diagram;
		DiagramEditPart offscreenEditPart = null;
		
		// Try load resolve the diagram edit part off-screen
		diagram = DiagramIOUtil.load(domain, file, true, null);
		offscreenEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
		return offscreenEditPart;
	}
	
	/**
	 * Creates an instance of DiagramEditPart from the given file using default
	 * workbench editor. That means that the registered editor is opened
	 * (visible to the user) and the DiagramEditPart is created.
	 * 
	 * @param file
	 *            File which contains the diagram informations
	 * @return Instance of DiagramEditPart
	 */
	public static DiagramEditPart getOnscreenDiagramEditPart(IFile file) {
		String filename = file.getName();

		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(filename);

		IWorkbenchWindow wbWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = wbWindow.getActivePage();

		IEditorPart editorPart;

		try {
			editorPart = page.openEditor(new FileEditorInput(file), desc.getId());
		} catch (PartInitException e) {
			return null;
		}

		DiagramEditor diagramEditor = (DiagramEditor) editorPart;
		DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
		return diagramEditPart;
	}
	
	/**
	 * Saves the given diagram edit part within the given file.
	 * 
	 * @param diagramEditPart
	 *            Diagram edit part to save
	 * @param file
	 *            File where the data is stored
	 * @throws CoreException
	 */
	public static void saveDiagram(DiagramEditPart diagramEditPart, IFile file) throws CoreException {
		TransactionalEditingDomain domain = diagramEditPart.getEditingDomain();
		Diagram diagram = diagramEditPart.getDiagramView(); 
		DiagramIOUtil.save(domain, file, diagram, null);
	}
	
	/**
	 * Saves the given diagram edit part and reloads it afterwards.
	 * 
	 * @param diagramEditPart
	 *            Diagram edit part to save
	 * @param file
	 *            File where the data is stored
	 * @param shell
	 *            Shell for the new diagram edit part
	 * @return Newly created instance of diagram edit part
	 * @throws CoreException
	 */
	public static DiagramEditPart saveAndReload(DiagramEditPart diagramEditPart, IFile file, Shell shell) throws CoreException {
		Diagram diagram = diagramEditPart.getDiagramView();
		saveDiagram(diagramEditPart, file);
		diagramEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
		return diagramEditPart;
	}

}
