package org.bflow.toolbox.hive.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.attributes.utils.EMFUtility;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Provides a clipboard that handles the attributes of graphical edit parts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01.03.2015
 *
 */
public class AttributeClipboard {
	
	private static final String CopyNamePrefix = "Copy_";
	
	public static AttributeClipboard Instance = new AttributeClipboard();
	
	private ClipboardContent clipboardContent = null;
	
	/**
	 * Private constructor.
	 */
	private AttributeClipboard() { }
	
	/**
	 * Puts the attributes of the selected element(s) to the clipboard.
	 * 
	 * @param structuredSelection
	 *            Structured selection to process
	 */
	public void put(IStructuredSelection structuredSelection) {
		if (structuredSelection == null || structuredSelection.isEmpty()) return;
		
		Object[] selection = structuredSelection.toArray();
		ArrayList<IGraphicalEditPart> graphicalEditParts = new ArrayList<>();
		for (int i = -1; ++i != selection.length;) {
			Object selectedObject = selection[i];
			if (!(selectedObject instanceof IGraphicalEditPart)) continue;
			graphicalEditParts.add((IGraphicalEditPart) selectedObject);
		}
		
		put(graphicalEditParts.toArray(new IGraphicalEditPart[0]));
	}
	
	/**
	 * Puts the attributes of the given collection to the clipboard.
	 * 
	 * @param editParts
	 *            Collection of edit parts to process
	 */
	public void put(IGraphicalEditPart[] editParts) {
		if (editParts == null) return;
		clipboardContent = new ClipboardContent();
		for (int i = -1; ++i != editParts.length;) {
			IGraphicalEditPart editPart = editParts[i];
			putToClipboardContent(editPart);
		}
	}
	
	/**
	 * Puts the attributes of the given edit part to the clipboard.
	 * 
	 * @param editPart
	 *            Edit part to process
	 */
	public void put(IGraphicalEditPart editPart) {
		if (editPart == null) return;
		
		clipboardContent = new ClipboardContent();
		putToClipboardContent(editPart);
	}
	
	/**
	 * Returns TRUE if the clipboard contains attributes of an element which has
	 * been copied before and is derived from the given one.
	 * 
	 * @param editPart
	 *            Edit part to check
	 * @return TRUE if there a attributes of the origin element
	 */
	public boolean isCopy(IGraphicalEditPart editPart) {
		return isCopy(editPart.resolveSemanticElement());
	}
	
	/**
	 * Returns TRUE if the clipboard contains attributes of an element which has
	 * been copied before and is derived from the given one.
	 * 
	 * @param eObject
	 *            EObject to check
	 * @return TRUE if there a attributes of the origin element
	 */
	public boolean isCopy(EObject eObject) {
		return (findOrigin(eObject) != null);
	}
	
	/**
	 * Returns the ClipboardContentItem of the origin element of the given one. If 
	 * there is none, NULL will be returned.
	 * 
	 * @param eObject
	 *            eObject whose origin to look up
	 * @return NULL or the ClipboardContentItem of the origin
	 */
	private ClipboardContentItem findOrigin(EObject eObject) {
		if (isEmpty()) return null;
		
		String eObjName = EMFCoreUtil.getName(eObject);
		if (!eObjName.startsWith(CopyNamePrefix)) return null;
		
		for (Iterator<ClipboardContentItem> it = clipboardContent.contentItems.iterator(); it.hasNext();) {
			ClipboardContentItem item = it.next();
			String pattern = String.format("_%s", item.ElementName);
			boolean isCopy = eObjName.endsWith(pattern);
			if (isCopy) return item;
		}
		
		return null;
	}
	
	/**
	 * Returns the attributes of the origin element of the given one. If there
	 * is non NULL, will be returned.
	 * 
	 * @param eObject
	 *            eObject whose origin to look up
	 * @return NULL or the attributes of the origin
	 */
	public Map<String, String> getOriginAttributes(EObject eObject) {
		ClipboardContentItem item = findOrigin(eObject);
		if (item == null) return null;
		return item.ElementAttributes;
	}
	
	/**
	 * Puts the attributes of the given edit part to the clipboard.
	 * 
	 * @param editPart
	 *            Edit part to process
	 */
	private void putToClipboardContent(IGraphicalEditPart editPart) {
		ClipboardContentItem item = buildClipboardContentItem(editPart);
		clipboardContent.add(item);
	}
	
	/**
	 * Resolves all needed information to build a ClipboardContentItem from the
	 * given edit part.
	 * 
	 * @param editPart
	 *            Edit part to process
	 * @return Instance of {@link ClipboardContentItem}
	 */
	private ClipboardContentItem buildClipboardContentItem(IGraphicalEditPart editPart) {
		if (editPart == null) throw new NullPointerException("editPart is null!");
		
		EObject eObj = EMFUtility.getEObject(editPart);
		String name = EMFCoreUtil.getName(eObj);
		String id = EMFCoreUtil.getProxyID(eObj);
		if (StringUtils.isBlank(id)) throw new NullPointerException("Id of given element is null or empty!");
		
		AttributeFile attributeFile = AttributeFileRegistry.getInstance().getActiveAttributeFile();
		if (attributeFile == null) throw new NullPointerException("attributeFile is null!");
		
		Map<String, String> attributes = attributeFile.get(id);
		if (attributes == null)
			attributes = new HashMap<>();
		
		return buildClipboardContentItem(eObj, id, name, attributes);
	}
	
	/**
	 * Creates an instance of {@link ClipboardContentItem} using the given parameters.
	 * @param eObject EObject
	 * @param elementId Element id
	 * @param elementName Element name
	 * @param elementAttributes Element attributes
	 * @return Instance of {@link ClipboardContentItem}
	 */
	private ClipboardContentItem buildClipboardContentItem(EObject eObject, String elementId, String elementName, Map<String, String> elementAttributes) {
		if (StringUtils.isBlank(elementId)) throw new NullPointerException("elementId is null or empty!");
		if (elementAttributes == null) throw new NullPointerException("elementAttributes is null!");
		
		ClipboardContentItem item = new ClipboardContentItem(eObject, elementId, elementName, elementAttributes);
		return item;
	}
	
	/**
	 * Clears the clipboard.
	 */
	public void clear() {
		clipboardContent = null;
	}

	/**
	 * Returns TRUE if the clipboard is empty.
	 * 
	 * @return TRUE or FALSE
	 */
	public boolean isEmpty() {
		return clipboardContent == null;
	}
	
	/**
	 * Defines a clipboard content.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 01.03.2015
	 *
	 */
	class ClipboardContent {
		private ArrayList<ClipboardContentItem> contentItems = new ArrayList<>();
		
		/**
		 * Adds the given item to the clipboard.
		 * @param contentItem Item to add
		 */
		public void add(ClipboardContentItem contentItem) {
			if (contentItem == null) return;
			contentItems.add(contentItem);
		}
		
		/**
		 * Returns TRUE if the content exactly contains one element.
		 * 
		 * @return TRUE or FALSE
		 */
		public boolean isSingle() {
			return contentItems.size() == 1;
		}
		
	}
	
	/**
	 * Defines a clipboard content item.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 01.03.2015
	 *
	 */
	class ClipboardContentItem { 
		public EObject EObject;
		public String ElementName;
		public String ElementId;
		public Map<String, String> ElementAttributes;
		
		/**
		 * Creates a new instance using the given parameters.
		 * 
		 * @param eObject
		 *            EObject
		 * @param elementId
		 *            Element id
		 * @param elementName
		 *            Element name
		 * @param elementAttributes
		 *            Element attributes
		 */
		public ClipboardContentItem(EObject eObject, String elementId, String elementName, Map<String, String> elementAttributes) {
			EObject = eObject;
			ElementId = elementId;
			ElementName = elementName;
			ElementAttributes = elementAttributes;
		}
	}
}
