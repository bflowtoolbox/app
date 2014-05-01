package org.bflow.toolbox.hive.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * This class provides a registry instance for attribute files. Everytime when a diagram editor is opened which
 * could modify attributes this registry notice this. It also provides the model for the <code>AttributeViewPart</code>. 
 * @author Arian Storch
 * @since 17/07/11
 */
public class AttributeFileRegistry implements IPartListener {
	
	private static HashMap<DiagramDocumentEditor, AttributeFile> fileMap = new HashMap<DiagramDocumentEditor, AttributeFile>();
	
	private static AttributeResourceSetListener resourceSetListener = new AttributeResourceSetListener();
	
	private static AttributeFile activeAttrFile;
	
	private static DiagramDocumentEditor activeDiagramEditor;
	
	private static List<IAttributeFileRegistryListener> listeners = new ArrayList<IAttributeFileRegistryListener>();
	
	private static AttributeFileRegistry instance;
	
	/**
	 * Constructor.
	 */
	private AttributeFileRegistry() {
		instance = this;
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPageListener(new IPageListener() {

			@Override
			public void pageActivated(IWorkbenchPage page) {
				page.addPartListener(instance);
			}

			@Override
			public void pageClosed(IWorkbenchPage page) {
				page.removePartListener(instance);
			}

			@Override
			public void pageOpened(IWorkbenchPage page) {
				page.addPartListener(instance);
			}
			
		});

	}
	
	/**
	 * Returns the instance of the registry.
	 * @return instance
	 */
	public static AttributeFileRegistry getInstance() {
		if(instance == null) {
			instance = new AttributeFileRegistry();
		}
				
		return instance;
	}
	
	/**
	 * Returns all opened and registered editors.
	 * @return opened and registered editors
	 */
	public Set<DiagramDocumentEditor> getRegisteredEditors() {
		return fileMap.keySet();
	}
	
	/**
	 * Returns the Attribute File that is hold by the editor.
	 * @param editor editor
	 * @return
	 */
	public AttributeFile getAttributeFile(DiagramDocumentEditor editor) {
		return fileMap.get(editor);
	}
	
	/**
	 * Returns the Attribute File of the active editor.
	 * @return Attribute File of the active editor.
	 */
	public AttributeFile getActiveAttributeFile() {
		return activeAttrFile;
	}
	
	/**
	 * Adds a listener to the registry.
	 * @param listener listener
	 */
	public void addRegistryListener(IAttributeFileRegistryListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes the listener from the registry.
	 * @param listener listener
	 */
	public void removeRegistryListener(IAttributeFileRegistryListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Returns the IFile of the Editor Input of the active workbench part.
	 * @param part workbench part
	 * @return IFile of Editor Input
	 */
	private IFile getActiveResource(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			IEditorInput input = ((DiagramDocumentEditor) part)
					.getEditorInput();

			if (input instanceof IFileEditorInput)
				return ((IFileEditorInput) input).getFile();
		}

		return null;
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			activeDiagramEditor = (DiagramDocumentEditor)part;

			activeAttrFile = fileMap.get(part);
			
			if(activeAttrFile == null) { // partOpened wasn't called
				partOpened(part);
				
				activeAttrFile = fileMap.get(part);
			}
			
			resourceSetListener.setFile(activeAttrFile);
			resourceSetListener.setDiagramName(getActiveResource(part).getName());
			resourceSetListener.setProjectName(getActiveResource(part).getProject().getName());

			activeDiagramEditor.getEditingDomain().addResourceSetListener(resourceSetListener);
			
			dispatchAttributeFileChangedEvent(activeAttrFile, activeDiagramEditor);
		}
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {		
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			AttributeFile aFile = fileMap.get(part);

			aFile.save();

			fileMap.remove(part);
			
			DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor)part;
			
			diagramEditor.getEditingDomain().removeResourceSetListener(resourceSetListener);
		}
		
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		/*if (part instanceof DiagramDocumentEditor) {
			activeDiagramEditor.getEditingDomain().removeResourceSetListener(resourceSetListener);
			activeAttrFile = null;
			activeDiagramEditor = null;
			
			dispatchAttributeFileChangedEvent(null);
		}*/
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			DiagramEditPart diagramEditPart = ((DiagramDocumentEditor) part)
					.getDiagramEditPart();

			AttributeFile f = new AttributeFile(diagramEditPart);
			f.load();

			fileMap.put((DiagramDocumentEditor) part, f);
		}
	}
	
	/**
	 * Dispatches the event to all listeners.
	 * @param file Attribute File
	 * @param diagramEditor Diagram Editor
	 */
	private void dispatchAttributeFileChangedEvent(AttributeFile file, DiagramDocumentEditor diagramEditor) {
		AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
		event.attributeFile = file;
		event.diagramEditor = diagramEditor;
		
		for(IAttributeFileRegistryListener listener:listeners) {
			listener.noticeAttributeFileChange(event);			
		}
	}

}
