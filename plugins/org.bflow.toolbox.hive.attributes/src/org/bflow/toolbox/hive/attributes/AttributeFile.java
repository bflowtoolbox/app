package org.bflow.toolbox.hive.attributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * Defines a class that handles attributes to a model element identified by its
 * model id.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24/04/10
 * @version 30/12/13
 * 
 */
public class AttributeFile {
	private boolean dirty;

	private HashMap<String, HashMap<String, String>> attributes = new HashMap<String, HashMap<String, String>>();
	
	private DiagramEditPart diagramEditPart;
	
	public static EClass attributeEClass, addonEClass;
	public static EPackage addonEPackage;
	public static EAttribute attributeId, attributeName, attributeValue;
	public static EReference addon_attributes;
	
	private Resource resource;
	
	private boolean loaded = false;
	
	/**
	 * Constructor.
	 * @param diagramEditPart diagram edit part
	 */
	public AttributeFile(DiagramEditPart diagramEditPart) {
		super();
		this.diagramEditPart = diagramEditPart;
	}

	/**
	 * Loads the attribute file.
	 */
	public void load() {	
		if(loaded)
			return ;
		
		EObject semanticElement = diagramEditPart.resolveSemanticElement();
		
		Resource resource = semanticElement.eResource();
		this.resource = resource;
		
		for(EObject eobj:resource.getContents()) {
			if(eobj.eClass() == addonEClass) {
				EObject addonAttributes = eobj;
				
				@SuppressWarnings("rawtypes")
				List list = (List) addonAttributes.eGet(addon_attributes);
				
				for(Object attrObj:list) {
					EObject attribute = (EObject)attrObj;
					
					String id = (String) attribute.eGet(attributeId);
					String name = (String) attribute.eGet(attributeName);
					String value = (String) attribute.eGet(attributeValue);
					
					HashMap<String, String> map = attributes.get(id);
					
					if(map == null)
						map = new HashMap<String, String>();
					
					map.put(name, value);
					attributes.put(id, map);
				}
				
				/*EMFRemoveCommand com = new EMFRemoveCommand(resource, addonAttributes);
				
				EMFCommandOperation op = new EMFCommandOperation(diagramEditPart.getEditingDomain(), com);
				try {
					op.execute(null, null);
				} catch (ExecutionException e1) {
					AttributeViewPlugin.logError(e1.getMessage(), e1);
				}*/
				
				break;
			}
		}

		dirty = false;
		loaded = true;
	}

	/**
	 * Saves the attribute file.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save() {
		// Create instances
		EFactory addonFactory = addonEPackage.getEFactoryInstance();
		
		final EObject addonAttributes = addonFactory.create(addonEClass);
		
		for(String id:attributes.keySet()) {
			HashMap<String, String> map = attributes.get(id);
			
			for(String name:map.keySet()) {
				EObject attribute = addonFactory.create(attributeEClass);
				attribute.eSet(attributeId, id);
				attribute.eSet(attributeName, name);
				attribute.eSet(attributeValue, map.get(name));
				
				((List)addonAttributes.eGet(addon_attributes)).add(attribute);
			}
		}
		
//		EObject semanticElement = diagramEditPart.resolveSemanticElement();
//		Resource resource = semanticElement.eResource();
		
		EMFAddCommand com = new EMFAddCommand(resource, addonAttributes);
		EMFRemoveCommand remCom = new EMFRemoveCommand(resource, addonAttributes);
		
		EMFCommandOperation op = new EMFCommandOperation(diagramEditPart.getEditingDomain(), com);
		EMFCommandOperation opRem = new EMFCommandOperation(diagramEditPart.getEditingDomain(), remCom);
		
		// TODO Fix saving though the user rejected to
		// The file can be dirty but the user has rejected to save any changes
		// in this case the file will be saved though
		
		// Only perform a save operation if the file is dirty
		if(!dirty) return;
		
		try {
			opRem.execute(null, null);
			op.execute(null, null);
		} catch (ExecutionException e1) {
			AttributeViewPlugin.logError(e1.getMessage(), e1);
		}
			
		dirty = false;
	}

	/**
	 * Returns true if this file is dirty and should be saved.
	 * 
	 * @return true if this file is dirty
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * Adds a new attribute to model element.
	 * 
	 * @param id
	 *            model id
	 * @param attribute
	 *            attribute name
	 * @param value
	 *            attribute value
	 */
	public void add(String id, String attribute, String value) {
		if (!attributes.containsKey(id))
			attributes.put(id, new HashMap<String, String>());

		if (value.isEmpty())
			value = "0";

		attributes.get(id).put(attribute, value);

		dirty = true;
	}

	/**
	 * Removes the attribute from the model element.
	 * 
	 * @param id
	 *            element id
	 * @param attribute
	 *            name of the attribute
	 */
	public void remove(String id, String attribute) {
		if (!attributes.containsKey(id))
			return;

		attributes.get(id).remove(attribute);

		dirty = true;
	}

	/**
	 * Sets the value of the attribute.
	 * 
	 * @param id
	 *            model element id
	 * @param attribute
	 *            name of the attribute
	 * @param value
	 *            value to set
	 */
	public void set(String id, String attribute, String value) {
		if (!attributes.containsKey(id)) {
			add(id, attribute, value);
			return;
		}

		if (!attributes.get(id).containsKey(attribute)) {
			add(id, attribute, value);
			return;
		}

		attributes.get(id).put(attribute, value);

		dirty = true;
	}

	/**
	 * Returns the value of the attribute.
	 * 
	 * @param id
	 *            model element id
	 * @param attribute
	 *            name of the attribute
	 * @return value or null
	 */
	public String get(String id, String attribute) {
		if (!attributes.containsKey(id))
			return null;

		return attributes.get(id).get(attribute);
	}

	/**
	 * Returns all attributes of the model element.
	 * 
	 * @param id
	 *            model element id
	 * @return all attributes or null
	 */
	public HashMap<String, String> get(String id) {
		return attributes.get(id);
	}

	/**
	 * Returns a vector with all attribute names. Even if some attributes appear
	 * more than once, they will only be once in the list.
	 * 
	 * @return list of attribute names
	 */
	public Vector<String> getAllAttributes() {
		Vector<String> vAttr = new Vector<String>();

		for (String id : attributes.keySet())
			for (String name : attributes.get(id).keySet())
				if (!vAttr.contains(name))
					vAttr.add(name);

		return vAttr;
	}

	/**
	 * Returns a set with all model element ids that got attributes.
	 * 
	 * @return set with model element ids
	 */
	public Set<String> getAllIDs() {
		return attributes.keySet();
	}
	
	/**
	 * Provides a simple EMF add command.
	 * @author Arian Storch
	 * @since 27/05/11
	 * @version 02/07/12
	 */
	private class EMFAddCommand extends AbstractCommand {
		
		private Resource resource;
		private EObject addonAttributes;
		
		public EMFAddCommand(Resource resource, EObject addonAttributes) {
			super("SaveAttributesCommand", "Saves the attributes in the document.");
			
			this.resource = resource;
			this.addonAttributes = addonAttributes;
		}
		
		@Override
		public void redo() {				
		}
		
		@Override
		public void execute() {	
			if(resource == null)
				return ;
			
			resource.getContents().add(addonAttributes);
			try {
				resource.save(null);
			} catch (IOException e) {
				AttributeViewPlugin.logError(e.getMessage(), e);
			}
		}
	};
	
	/**
	 * Provides a simple EMF remove command.
	 * @author Arian Storch
	 * @since 27/05/11
	 */
	private class EMFRemoveCommand extends AbstractCommand {
		
		private Resource resource;
		private EObject addonAttributes;
		
		public EMFRemoveCommand(Resource resource, EObject addonAttributes) {
			super("RemoveAttributesCommand", "Removes the attributes from the document.");
			
			this.resource = resource;
			this.addonAttributes = addonAttributes;
		}

		@Override
		public void execute() {
			
			if(resource == null)
				return ;
			
			List<EObject> coll = new ArrayList<EObject>();
			
			for(EObject eObj:resource.getContents()) {
				if(eObj.eClass().getInstanceClassName().equalsIgnoreCase(
						addonAttributes.eClass().getInstanceClassName()))
					coll.add(eObj);
			}
			
			resource.eSetDeliver(false);
			
			resource.getContents().removeAll(coll);
			
			resource.eSetDeliver(true);		
		}

		@Override
		public void redo() {			
		}
		
	}

}
