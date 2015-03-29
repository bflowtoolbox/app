package org.bflow.toolbox.hive.gmfbridge;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
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
				fAdapterFactories.add(factory);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Extension point id */
	static final String ExtensionPointId = "org.bflow.toolbox.hive.gmfBridge.adapterFactory";
}
