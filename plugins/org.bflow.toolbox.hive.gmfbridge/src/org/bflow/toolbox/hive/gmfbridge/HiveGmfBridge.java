package org.bflow.toolbox.hive.gmfbridge;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Provides methods to create adapting classes to GMF class model.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public class HiveGmfBridge {
	private static ArrayList<IGmfEditPartAdapterFactory> fAdapterFactories = new ArrayList<>();
	
	static {
		resolveRegisteredAdapterFactories();
	}
	
	/**
	 * Returns an instance of {@link DiagramEditor} that has been adapted to the
	 * given editor part. If the editor part implements {@link DiagramEditor}
	 * itself, it will be simply returned.
	 * 
	 * @param editorPart
	 *            Editor part to adapt
	 * @return DiagramEditor that has been adapted or derived from the given
	 *         editor part instance
	 */
	static public DiagramEditor adapt(IEditorPart editorPart) {
		if (DiagramEditor.class.isAssignableFrom(editorPart.getClass())) return (DiagramEditor) editorPart;
		
		for (int i = -1; ++i != fAdapterFactories.size();) {
			IGmfEditPartAdapterFactory factory = fAdapterFactories.get(i);
			if (!factory.canAdapt(editorPart)) continue;
			return factory.getAdapter(editorPart);
		}
		
		throw new RuntimeException("Unable to adapt editor part to diagram editor");
	}
	
	/**
	 * Returns an instance of {@link DiagramEditor} that has been adapted to the
	 * given graphical editor. If the editor implements {@link DiagramEditor}
	 * itself, it will be simply returned.
	 * 
	 * @param graphicalEditor 
	 *            Graphical editor to adapt
	 * @return DiagramEditor that has been adapted or derived from the given
	 *         editor part instance
	 */
	static public DiagramEditor adapt(GraphicalEditor graphicalEditor) {
		return adapt((IEditorPart)graphicalEditor);
	}
	
	/**
	 * Adapts the given GEF based graphical edit part to a GMF based edit part.
	 * If the edit part implements the interface itself, it will be simply
	 * returned.
	 * 
	 * @param graphicalEditPart
	 *            Graphical edit part to adapt
	 * @return GraphicalEditPart that has been adapted or derived from the given
	 *         edit part instance
	 */
	static public IGraphicalEditPart adapt(org.eclipse.gef.GraphicalEditPart graphicalEditPart) {
		if (IGraphicalEditPart.class.isAssignableFrom(graphicalEditPart.getClass())) return (IGraphicalEditPart) graphicalEditPart;
		
		for (int i = -1; ++i != fAdapterFactories.size();) {
			IGmfEditPartAdapterFactory factory = fAdapterFactories.get(i);
			if (!factory.canAdapt(graphicalEditPart)) continue;
			return factory.getAdapter(graphicalEditPart);
		}
		
		throw new RuntimeException("Unable to adapt graphical edit part");
	}
	
	/**
	 * Adapts each part of the given array if it's an instance of a graphical
	 * edit part. Otherwise the origin reference is returned.
	 * 
	 * @param editParts
	 *            Array of edit parts to process
	 * @return Array of adapted edit parts
	 */
	static public Object[] adaptSelection(Object[] editParts) {
		Object[] adaptedObjects = new Object[editParts.length];
		for (int i = -1; ++i != editParts.length; ) {
			Object obj = editParts[i];
			adaptedObjects[i] = obj;
			if (!(obj instanceof org.eclipse.gef.GraphicalEditPart)) continue;
			org.eclipse.gef.GraphicalEditPart graphicalEditPart = (org.eclipse.gef.GraphicalEditPart) obj;
			adaptedObjects[i] = adapt(graphicalEditPart);
		}
		return adaptedObjects;
	}

	/**
	 * Resolves all via extension point registered adapter factories.
	 */
	static private void resolveRegisteredAdapterFactories() {
		fAdapterFactories.clear();
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(ExtensionPointId);
		
		for (int i = -1; ++i != configurationElements.length;) {
			IConfigurationElement element = configurationElements[i];
			try {
				Object factoryInstance = element.createExecutableExtension("class");
				IGmfEditPartAdapterFactory factory = (IGmfEditPartAdapterFactory) factoryInstance;
				if (!factory.canOperate()) continue;
				fAdapterFactories.add(factory);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Extension point id */
	static final String ExtensionPointId = "org.bflow.toolbox.hive.gmfBridge.adapterFactory";
}
