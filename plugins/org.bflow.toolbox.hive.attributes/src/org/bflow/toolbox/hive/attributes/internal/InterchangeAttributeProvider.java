package org.bflow.toolbox.hive.attributes.internal;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.AttributeProviderRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.IAttributeProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;


/**
 * Implements {@link IAttributeProvider} to provide the attribute support for the export process.
 * 
 * @author Arian Storch
 * @since 08/10/12
 * @version 02/05/13
 */
public class InterchangeAttributeProvider implements IAttributeProvider {
	
	/** The editor types. */
	private final String[] editorTypes = { "epc", "oepc" };
	
	/**
	 * Instantiates a new interchange attribute provider.
	 */
	public InterchangeAttributeProvider() {
		AttributeProviderRegistry.registerProvider(this);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IAttributeProvider#getApplicableDiagramEditorFileExtensions()
	 */
	@Override
	public String[] getApplicableDiagramEditorFileExtensions() {
		return editorTypes;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IAttributeProvider#getAttributesFor(org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart, java.lang.String)
	 */
	@Override
	public Map<String, String> getAttributesFor(DiagramEditPart diagramEditPart, String id) {
		AttributeFile file = AttributeFileRegistry.getInstance().getActiveAttributeFile();
		
		if(file == null) {
			return new HashMap<String, String>();
		}
		
		return file.get(id);
	}

	/**
	 * Dispose and unregisters this instance.
	 */
	public void dispose() {
		AttributeProviderRegistry.unregisterProvider(this);
	}
}
