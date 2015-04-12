package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Factory to create adapting instances.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.04.2015
 * 
 */
public class AdapterFactory {
	
	static private HashMap<EObject, IGraphicalEditPart> fAdapterCache = new HashMap<>();

	/**
	 * Returns an adapter for the given edit part.
	 * 
	 * @param editPart
	 *            Edit part to adapt
	 * @return Newly created or a cached instance
	 */
	static public ShapeEditPartAdapter getShapeEditPartAdapter(EditPart editPart) {
		ShapeEditPartAdapter adapter = new ShapeEditPartAdapter(editPart);
		EObject eObj = adapter.resolveSemanticElement();
		
		if (eObj == null) {
			throw new RuntimeException("There is no semantic element for the given edit part");
		}
		
		ShapeEditPartAdapter cachedInstance = (ShapeEditPartAdapter) fAdapterCache.get(eObj);
		if (cachedInstance == null) {
			fAdapterCache.put(eObj, (cachedInstance = adapter));
		}
		
		return cachedInstance;
	}
	
	/**
	 * Returns an adapter for the given model object.
	 * 
	 * @param modelObject
	 *            Model object to adapt
	 * @return Newly created or a cached instance
	 */
	static public ShapeEditPartAdapter getShapeEditPartAdapter(EObject modelObject) {
		ShapeEditPartAdapter cachedInstance = (ShapeEditPartAdapter) fAdapterCache.get(modelObject);
		if (cachedInstance == null) {
			Map<?,?> editPartRegistry = ModelUtil.getEditor(modelObject).getGraphicalViewer().getEditPartRegistry();
			modelObject = mapToOriginModel(modelObject, editPartRegistry);
			EditPart editPart = (EditPart) editPartRegistry.get(modelObject);
			cachedInstance = getShapeEditPartAdapter(editPart);
		}
		return cachedInstance;
	}
	
	/**
	 * Returns an adapter for the given edit part.
	 * 
	 * @param editPart
	 *            Edit part to adapt
	 * @return Newly created or a cached instance
	 */
	static public ConnectionEditPartAdapter getConnectionEditPartAdapter(EditPart editPart) {
		ConnectionEditPartAdapter adapter = new ConnectionEditPartAdapter(editPart);
		EObject eObj = adapter.resolveSemanticElement();
		
		if (eObj == null) {
			throw new RuntimeException("There is no semantic element for the given edit part");
		}
		
		ConnectionEditPartAdapter cachedInstance = (ConnectionEditPartAdapter) fAdapterCache.get(eObj);
		if (cachedInstance == null) {
			fAdapterCache.put(eObj, (cachedInstance = adapter));
		}
		
		return cachedInstance;
	}
	
	/**
	 * Returns an adapter for the given model object.
	 * 
	 * @param modelObject
	 *            Model object to adapt
	 * @return Newly created or a cached instance
	 */
	static public ConnectionEditPartAdapter getConnectionEditPartAdapter(EObject modelObject) {
		ConnectionEditPartAdapter cachedInstance = (ConnectionEditPartAdapter) fAdapterCache.get(modelObject);
		if (cachedInstance == null) {
			Map<?,?> editPartRegistry = ModelUtil.getEditor(modelObject).getGraphicalViewer().getEditPartRegistry();
			modelObject = mapToOriginModel(modelObject, editPartRegistry);
			EditPart editPart = (EditPart) editPartRegistry.get(modelObject);
			cachedInstance = getConnectionEditPartAdapter(editPart);
		}
		return cachedInstance;
	}
	
	/**
	 * Clears the cache.
	 */
	public static void clearCache() {
		fAdapterCache.clear();
	}
	
	/**
	 * Looks up the given edit part factory to find the origin model object of
	 * the given (underlying) one. If none can be found, the given one is
	 * returned.
	 * 
	 * @param modelObject
	 *            Model object to look up
	 * @param editPartRegistry
	 *            Edit part registry to process
	 * @return Origin model object or previously given one
	 */
	static private EObject mapToOriginModel(EObject modelObject, Map<?, ?> editPartRegistry) {
		for (Entry<?, ?> entry : editPartRegistry.entrySet()) {
			Object entryKey = entry.getKey();
			if (!(entryKey instanceof EObject)) continue;
			EObject originModelObject = (EObject) entryKey;
			// EditPart originEditPart = (EditPart) entry.getValue();
			PictogramElement pe = (PictogramElement) originModelObject;
			EObject underlyingModelObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
			if (underlyingModelObject == modelObject) {
				return originModelObject;
			}
		}
		return modelObject;
	}
}
