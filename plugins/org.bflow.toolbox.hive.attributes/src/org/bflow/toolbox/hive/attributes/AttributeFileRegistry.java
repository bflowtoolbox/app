package org.bflow.toolbox.hive.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bflow.toolbox.hive.attributes.utils.WorkbenchUtil;
import org.bflow.toolbox.hive.gmfbridge.HiveGmfBridge;
import org.eclipse.core.resources.IFile;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * This class provides a registry instance for attribute files. Everytime when a
 * diagram editor is opened which could modify attributes this registry notice
 * this. It also provides the model for the <code>AttributeViewPart</code>.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17.07.11
 * @version 04.04.2015 Using lower GMF object references
 */
public class AttributeFileRegistry implements IPartListener {
	
	private static final HashMap<DiagramEditor, AttributeFile> fileMap = new HashMap<DiagramEditor, AttributeFile>();
	private static final List<IAttributeFileRegistryListener> listeners = new ArrayList<IAttributeFileRegistryListener>();
	private static final AttributeResourceSetListener resourceSetListener = new AttributeResourceSetListener();
	
	private static AttributeFile activeAttrFile;
	private static DiagramEditor activeDiagramEditor;
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
	 * 
	 * @return instance
	 */
	public static AttributeFileRegistry getInstance() {
		if (instance == null) {
			instance = new AttributeFileRegistry();
		}
				
		return instance;
	}
	
	/**
	 * Returns all opened and registered editors.
	 * 
	 * @return opened and registered editors
	 */
	public Set<DiagramEditor> getRegisteredEditors() {
		return fileMap.keySet();
	}
	
	/**
	 * Returns the Attribute File that is hold by the editor.
	 * 
	 * @param editor
	 *            editor
	 * @return
	 */
	public AttributeFile getAttributeFile(DiagramEditor editor) {
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
	 * 
	 * @param part
	 *            workbench part
	 * @return IFile of Editor Input
	 */
	private IFile getActiveResource(IWorkbenchPart part) {
		if (part instanceof DiagramEditor) {
			IEditorInput input = ((DiagramEditor) part).getEditorInput();

			if (input instanceof IFileEditorInput)
				return ((IFileEditorInput) input).getFile();
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partActivated(IWorkbenchPart part) {
		part = WorkbenchUtil.getActiveEditorPart(part);
		if (!(part instanceof GraphicalEditor)) return;
		
		GraphicalEditor graphicalEditor = (GraphicalEditor) part;
		part = HiveGmfBridge.adapt(graphicalEditor);
		
		if (part instanceof DiagramEditor) {
			activeDiagramEditor = (DiagramEditor)part;

			activeAttrFile = fileMap.get(part);
			
			if (activeAttrFile == null) { // partOpened wasn't called
				partOpened(part);
				activeAttrFile = fileMap.get(part);
			}
			
			resourceSetListener.setFile(activeAttrFile);
			
			IFile diagramFileInput = getActiveResource(part);
			resourceSetListener.setDiagramName(diagramFileInput.getName());
			resourceSetListener.setProjectName(diagramFileInput.getProject().getName());

			activeDiagramEditor.getEditingDomain().addResourceSetListener(resourceSetListener);
			
			dispatchAttributeFileChangedEvent(activeAttrFile, activeDiagramEditor);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partClosed(IWorkbenchPart part) {
		part = WorkbenchUtil.getActiveEditorPart(part);
		if (!(part instanceof GraphicalEditor)) return;
		
		GraphicalEditor graphicalEditor = (GraphicalEditor) part;
		part = HiveGmfBridge.adapt(graphicalEditor);
		
		if (part instanceof DiagramEditor) {
			AttributeFile attributeFile = fileMap.get(part);
			attributeFile.save();

			fileMap.remove(part);
			
			DiagramEditor diagramEditor = (DiagramEditor)part;
			diagramEditor.getEditingDomain().removeResourceSetListener(resourceSetListener);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partDeactivated(IWorkbenchPart part) {	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partOpened(IWorkbenchPart part) {
		part = WorkbenchUtil.getActiveEditorPart(part);
		if (!(part instanceof GraphicalEditor)) return;
		
		GraphicalEditor graphicalEditor = (GraphicalEditor) part;
		part = HiveGmfBridge.adapt(graphicalEditor);
		
		if (part instanceof DiagramEditor) {
			DiagramEditor diagramEditor = (DiagramEditor) part;
			DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();

			AttributeFile attributeFile = new AttributeFile(diagramEditPart);
			attributeFile.load();

			fileMap.put(diagramEditor, attributeFile);
		}
	}
	
	/**
	 * Dispatches the event to all listeners.
	 * 
	 * @param file
	 *            Attribute File
	 * @param diagramEditor
	 *            Diagram Editor
	 */
	private void dispatchAttributeFileChangedEvent(AttributeFile file, DiagramEditor diagramEditor) {
		AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
		event.attributeFile = file;
		event.diagramEditor = diagramEditor;
		
		for (IAttributeFileRegistryListener listener:listeners) {
			listener.noticeAttributeFileChange(event);			
		}
	}

}
