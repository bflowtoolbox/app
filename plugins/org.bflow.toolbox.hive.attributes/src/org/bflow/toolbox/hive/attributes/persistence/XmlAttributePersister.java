package org.bflow.toolbox.hive.attributes.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs;
import org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.ui.IFileEditorInput;

/**
 * Implements {@link IEditorLifecycleListener} to provide the persistence of
 * attributes for editors which are using a XML serialization.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31.07.2015
 *
 */
public class XmlAttributePersister implements IEditorLifecycleListener {
	
	private static final Namespace fBflowNamespace = new Namespace("bflow", "http://bflow.org");
	private static final QName fQNameAttributeCollection = new QName("attributeCollection", fBflowNamespace);
	private static final QName fQNameItem = new QName("item", fBflowNamespace);
	private static final QName fQNameElementId = new QName("elementId", fBflowNamespace);
	private static final QName fQNameAttributeName = new QName("attributeName", fBflowNamespace);
	private static final QName fQNameAttributeValue = new QName("attributeValue", fBflowNamespace);
	
	private ArrayList<AttributeInfo> fDeserializedAttributes = new ArrayList<>();
	
	/**
	 * Default constructor.
	 */
	public XmlAttributePersister() {
		// Register listener to copy all deserialized attributes when the editor is activated 
		AttributeFileRegistry.getInstance().addRegistryListener(new IAttributeFileRegistryListener() {
			/* (non-Javadoc)
			 * @see org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener#noticeAttributeFileChange(org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent)
			 */
			@Override
			public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
				boolean attributesAdded = false;
				for (AttributeInfo attributeInfo : fDeserializedAttributes) {
					event.attributeFile.add(attributeInfo.ElementId, attributeInfo.AttributeName, attributeInfo.AttributeValue);
					attributesAdded = true;
				}

				// Perform attribute add just once
				if (attributesAdded) {
					fDeserializedAttributes.clear();
					AttributeFileRegistry.getInstance().removeRegistryListener(this);
				}
				
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener#beforeInit(org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs)
	 */
	@Override
	public void beforeInit(EditorLifecycleEventArgs args) {
		String fileName = ((IFileEditorInput)args.EditorInput()).getFile().getLocation().toFile().getAbsolutePath();
		
		try {
			SAXReader saxReader = new SAXReader();
			Document xmlDocument = saxReader.read(new File(fileName));
			Element rootElement = xmlDocument.getRootElement();
			Element attributeCollectionElement = rootElement.element(fQNameAttributeCollection);
			if (attributeCollectionElement == null) return;
			
			// Read attributes
			for (@SuppressWarnings("unchecked")
			Iterator<Element> itr = attributeCollectionElement.elementIterator(fQNameItem); itr.hasNext();) {
				Element itemElement = itr.next();
				String elementId = itemElement.attributeValue(fQNameElementId);
				String attributeName = itemElement.attributeValue(fQNameAttributeName);
				String attributeValue = itemElement.attributeValue(fQNameAttributeValue);
				
				fDeserializedAttributes.add(new AttributeInfo(elementId, attributeName, attributeValue));
			}
			
			// We have to remove it here because an XML resource cannot have multiple root nodes
			rootElement.remove(attributeCollectionElement);
			
			// Re-write file to disk
			new XMLWriter(new FileOutputStream(new File(fileName)), OutputFormat.createPrettyPrint()).write(xmlDocument);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener#afterInit(org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs)
	 */
	@Override
	public void afterInit(EditorLifecycleEventArgs args) {
		// Nothing to do here
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener#beforeSave(org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs)
	 */
	@Override
	public void beforeSave(EditorLifecycleEventArgs args) {
		// Nothing to do here
	}	
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener#afterSave(org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs)
	 */
	@Override
	public void afterSave(EditorLifecycleEventArgs args) {
		AttributeFile attributeFile = AttributeViewPart.getInstance().getAttributeFile();
		if (attributeFile == null) return;
		
		Set<String> attributedElements = attributeFile.getAllIDs();
		if (attributedElements == null || attributedElements.size() == 0) return;
		
		String fileName = ((IFileEditorInput)args.EditorInput()).getFile().getLocation().toFile().getAbsolutePath();
		
		try {
			SAXReader saxReader = new SAXReader();
			Document xmlDocument = saxReader.read(new File(fileName));
			Element rootElement = xmlDocument.getRootElement();
			
			// Write attributes
			for (String elementId : attributedElements) {
				Set<String> attributeNames = attributeFile.getAllAttributes(elementId);
				if (attributeNames == null || attributeNames.size() == 0) continue;
				
				for (String attributeName : attributeNames) {
					String attributeValue = attributeFile.get(elementId, attributeName);
					
					Element attributeCollectionElement = getAttributeCollectionElement(rootElement);
					attributeCollectionElement.addElement(fQNameItem)
												.addAttribute(fQNameElementId, elementId)
												.addAttribute(fQNameAttributeName, attributeName)
												.addAttribute(fQNameAttributeValue, attributeValue);
				}
			}
			
			// Re-write file to disk
			new XMLWriter(new FileOutputStream(new File(fileName)), OutputFormat.createPrettyPrint()).write(xmlDocument);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.events.IEditorLifecycleListener#onDispose(org.bflow.toolbox.hive.eclipse.integration.events.EditorLifecycleEventArgs)
	 */
	@Override
	public void onDispose(EditorLifecycleEventArgs args) {
		// Nothing to do here
	}

	/**
	 * Returns the element that contains the attributes. If there is no element, a new one will be created.
	 * @param rootElement Root element of the XML document
	 * @return Existing or newly created attribute collection element
	 */
	private Element getAttributeCollectionElement(Element rootElement) {		
		Element attributeCollectionElement = rootElement.element(fQNameAttributeCollection);
		if (attributeCollectionElement != null) return attributeCollectionElement;
		
		return rootElement.addElement(fQNameAttributeCollection);
	}
	
	/**
	 * Describe an XML serialized attribute.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31.07.2015
	 *
	 */
	class AttributeInfo {
		public String ElementId;
		public String AttributeName;
		public String AttributeValue;
		
		public AttributeInfo(String elementId, String attributeName, String attributeValue) {
			super();
			ElementId = elementId;
			AttributeName = attributeName;
			AttributeValue = attributeValue;
		}	
	}
}
