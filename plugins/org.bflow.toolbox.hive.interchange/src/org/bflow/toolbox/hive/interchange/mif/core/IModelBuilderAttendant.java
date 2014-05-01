package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * Defines an interface for a provider which grants access to specific 
 * meta model informations that will be used by the interchange framework to 
 * create the models during the import process.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07/07/13
 * @version 12/07/13
 */
public interface IModelBuilderAttendant {
	
	/**
	 * Return a newly created instance of the desired diagram type.
	 * 
	 * @param diagramURI URI of the resource to create
	 * @param progressMonitor Pre-configured progress monitor 
	 * @return Newly created resource for the given URI
	 */
	public Resource createDiagramInstance(URI diagramURI, IProgressMonitor progressMonitor);
	
	/**
	 * Return the instance of {@link EStructuralFeature} that is addressed by the given type 
	 * and name informations.
	 * 
	 * @param type The {@link IElementType} type of the wanted structural feature 
	 * @param name The name of the wanted structural feature
	 * @return Instance of {@link EStructuralFeature}
	 */
	public EStructuralFeature getEStructuralFeatureFor(String type, String name);
	
	/**
	 * Return the save options that are used by your DiagramEditorUtil.
	 * @return Map containing save options
	 */
	public Map<?,?> getSaveOptions();
	
	/**
	 * Notifies the attendant to create an attribute file. This method is called
	 * first before <code>setAttributes()</code> will be invoked.
	 * 
	 * @param diagramEditPart
	 *            The diagram edit part
	 * @throws Exception
	 */
	public void createAttributeFile(DiagramEditPart diagramEditPart) throws Exception;
	
	/**
	 * Notifies the attendant to store the given attributes for the given edit
	 * part.
	 * 
	 * @param editPart
	 *            The edit part which shall own the attributes
	 * @param attributesMap
	 *            The attributes
	 */
	public void setAttributes(EditPart editPart, Map<String, String> attributesMap);
	
	/**
	 * Hook method that is invoked just before the model builder finishes its processing.
	 * 
	 * @param diagramEditPart Diagram edit part that has been created
	 */
	public void onBeforeFinish(DiagramEditPart diagramEditPart) throws Exception;
}
