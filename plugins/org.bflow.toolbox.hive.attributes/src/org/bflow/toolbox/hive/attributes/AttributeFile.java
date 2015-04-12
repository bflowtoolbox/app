package org.bflow.toolbox.hive.attributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
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
 * @since 24.04.2010
 * @version 27.09.2014
 * 			10.04.2015 Introducing feature IAttributeFilePersister and IAttributeFileListener
 * 
 */
public class AttributeFile {
	private boolean dirty;

	private final HashMap<String, HashMap<String, String>> attributes = new HashMap<>();
	private ArrayList<IAttributeFileListener> fListeners = new ArrayList<>();
	
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
	 * Adds the given listener to this instance.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	public void addListener(IAttributeFileListener listener) {
		if (listener == null) return;
		fListeners.add(listener);
	}
	
	/**
	 * Removes the given listener from this instance.
	 * 
	 * @param listener
	 *            Listener to remove
	 */
	public void removeListener(IAttributeFileListener listener) {
		if (listener == null) return;
		fListeners.remove(listener);
	}

	/**
	 * Loads the attribute file.
	 */
	public void load() {	
		if (loaded) return;
		
		try {
			if (diagramEditPart instanceof IAttributeFilePersister) {
				loadUsingPersister((IAttributeFilePersister) diagramEditPart);
			} else {
				loadUsingResource();
			}
		} catch(Exception ex) {
			AttributeViewPlugin.logError("Error on loading attribute file", ex);
		} finally {
			dirty = false;
			loaded = true;
		}
	}
	
	private void loadUsingPersister(IAttributeFilePersister persister) throws Exception {
		if (persister == null) throw new NullPointerException("persister is null!");
		persister.load(attributes);
	}
	
	private void loadUsingResource() throws Exception {
		EObject semanticElement = diagramEditPart.resolveSemanticElement();
		Resource resource = semanticElement.eResource();
		this.resource = resource;
		
		for (EObject eobj:resource.getContents()) {
			if (eobj.eClass() == addonEClass) {
				EObject addonAttributes = eobj;
				
				@SuppressWarnings("rawtypes")
				List list = (List) addonAttributes.eGet(addon_attributes);
				
				for (Object attrObj:list) {
					EObject attribute = (EObject)attrObj;
					
					String id = (String) attribute.eGet(attributeId);
					String name = (String) attribute.eGet(attributeName);
					String value = (String) attribute.eGet(attributeValue);
					
					HashMap<String, String> map = attributes.get(id);
					
					if (map == null)
						map = new HashMap<String, String>();
					
					map.put(name, value);
					attributes.put(id, map);
				}				
				break;
			}
		}
	}

	/**
	 * Saves the attribute file.
	 */
	public void save() {		
		// TODO Fix saving though the user rejected to
		// The file can be dirty but the user has rejected to save any changes
		// in this case the file will be saved though
		
		// Only perform a save operation if the file is dirty
		if (!dirty) return;
		
		try {
			if (diagramEditPart instanceof IAttributeFilePersister) {
				saveUsingPersister((IAttributeFilePersister)diagramEditPart);
			} else {
				saveUsingResource();
			}
		} catch (Exception e1) {
			AttributeViewPlugin.logError("Error on saving attribute file", e1);
		} finally {
			dirty = false;
		}
	}
	
	/**
	 * Saves the attributes using the given perister.
	 * 
	 * @param persister
	 *            Persister which saves the attributes
	 * @throws Exception
	 */
	private void saveUsingPersister(IAttributeFilePersister persister) throws Exception {
		if (persister == null) throw new NullPointerException("persister is null!");
		persister.save(attributes);
	}
	
	/**
	 * Saves the attributes within the resource that is associated with the
	 * diagram edit part.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void saveUsingResource() throws Exception {
		// Create instances
		EFactory addonFactory = addonEPackage.getEFactoryInstance();
		EObject addonAttributes = addonFactory.create(addonEClass);
		
		for (String id:attributes.keySet()) {
			HashMap<String, String> map = attributes.get(id);
			
			for (String name:map.keySet()) {
				EObject attribute = addonFactory.create(attributeEClass);
				attribute.eSet(attributeId, id);
				attribute.eSet(attributeName, name);
				attribute.eSet(attributeValue, map.get(name));
				
				((List)addonAttributes.eGet(addon_attributes)).add(attribute);
			}
		}
		
		EMFAddCommand com = new EMFAddCommand(resource, addonAttributes);
		EMFRemoveCommand remCom = new EMFRemoveCommand(resource, addonAttributes);
		
		EMFCommandOperation op = new EMFCommandOperation(diagramEditPart.getEditingDomain(), com);
		EMFCommandOperation opRem = new EMFCommandOperation(diagramEditPart.getEditingDomain(), remCom);
		
		opRem.execute(null, null);
		op.execute(null, null);
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
		raiseAttributeAddedEvent(id, attribute, value);
		
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
		if (!attributes.containsKey(id)) return;

		attributes.get(id).remove(attribute);
		raiseAttributeRemovedEvent(id, attribute);
		
		dirty = true;
	}
	
	/**
	 * Removes all attributes from the model element.
	 * 
	 * @param id
	 *            Model element id
	 */
	public void removeAll(String id) {
		if (!attributes.containsKey(id)) return;
		
		attributes.remove(id);
		raiseAttributesRemovedEvent(id);
		
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
	 * Returns a collection of all attribute names of the model element with the
	 * given id. If the model element is unknown NULL will be returned. If the
	 * collection is empty the model element doensn't have attributes.
	 * 
	 * @param id
	 *            Model element id
	 * @return Collection of attribute names or NULL
	 */
	public Set<String> getAllAttributes(String id) {
		HashMap<String, String> elementAttributes = attributes.get(id);
		if (elementAttributes == null) return null;
		return elementAttributes.keySet();
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
	 * Raises the event that an attribute with the given name and value has been
	 * added to the given model element.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 * @param attributeName
	 *            Name of the newly created attribute
	 * @param attributeValue
	 *            Value of the newly created attribute
	 */
	private void raiseAttributeAddedEvent(final String modelElementId, final String attributeName, final String attributeValue) {
		dispatchEvent(new DispatchEvent<IAttributeFileListener>() {
			@Override
			public void dispatch(IAttributeFileListener item) {
				item.onAttributeAdded(modelElementId, attributeName, attributeValue);
			}});
	}
	
	/**
	 * Raises the event that the attribute with the given name has been removed
	 * from the given model element.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 * @param attributeName
	 *            Name of the removed attribute
	 */
	private void raiseAttributeRemovedEvent(final String modelElementId, final String attributeName) {
		dispatchEvent(new DispatchEvent<IAttributeFileListener>() {
			@Override
			public void dispatch(IAttributeFileListener item) {
				item.onAttributeRemoved(modelElementId, attributeName);
			}
		});
	}
	
	/**
	 * Raises the event that all attributes of a model element has been removed.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 */
	private void raiseAttributesRemovedEvent(final String modelElementId) {
		dispatchEvent(new DispatchEvent<IAttributeFileListener>() {
			@Override
			public void dispatch(IAttributeFileListener item) {
				item.onAttributesRemoved(modelElementId);
			}
		});
	}
	
	/**
	 * Dispatches the given event to all listeners.
	 * 
	 * @param event
	 *            Event to dispatch
	 */
	private void dispatchEvent(DispatchEvent<IAttributeFileListener> event) {
		for (int i = -1; ++i != fListeners.size();) {
			try {
				IAttributeFileListener listener = fListeners.get(i);
				event.dispatch(listener);
			} catch (Exception ex) {
				AttributeViewPlugin.logError("Error on notifying listener", ex);
			}
		}
	}
	
	/**
	 * Defines a delegate to simply dispatch events.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 11.04.2015
	 * 
	 * @param <CItem>
	 *            Listener type
	 */
	interface DispatchEvent<CItem> {
		/**
		 * Tells the dispatcher to notify the given listener.
		 * 
		 * @param item
		 *            Listener to notify
		 */
		void dispatch(CItem item);
	}
	
	/**
	 * Provides a simple EMF add command.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 27.05.11
	 * @version 02.07.12
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
		public void redo() { }
		
		@Override
		public void execute() {	
			if (resource == null) return;
			
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
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 27.05.11
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
			if (resource == null) return ;
			
			List<EObject> coll = new ArrayList<EObject>();
			
			for (EObject eObj:resource.getContents()) {
				if (eObj.eClass().getInstanceClassName().equalsIgnoreCase(addonAttributes.eClass().getInstanceClassName()))
					coll.add(eObj);
			}
			
			resource.eSetDeliver(false);
			resource.getContents().removeAll(coll);
			resource.eSetDeliver(true);		
		}

		@Override
		public void redo() { }
		
	}

}
