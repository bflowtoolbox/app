package oepc.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import oepc.OepcPackage;
import oepc.diagram.part.OepcDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class OepcElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private OepcElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType OEPC_79 = getElementType("org.bflow.toolbox.oepc.diagram.OEPC_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Event_2001 = getElementType("org.bflow.toolbox.oepc.diagram.Event_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ITSystem_2002 = getElementType("org.bflow.toolbox.oepc.diagram.ITSystem_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType OrganisationUnit_2003 = getElementType("org.bflow.toolbox.oepc.diagram.OrganisationUnit_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType XORConnector_2004 = getElementType("org.bflow.toolbox.oepc.diagram.XORConnector_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BusinessObject_2005 = getElementType("org.bflow.toolbox.oepc.diagram.BusinessObject_2005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ANDConnector_2006 = getElementType("org.bflow.toolbox.oepc.diagram.ANDConnector_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ORConnector_2007 = getElementType("org.bflow.toolbox.oepc.diagram.ORConnector_2007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Document_2008 = getElementType("org.bflow.toolbox.oepc.diagram.Document_2008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BusinessAttribute_3001 = getElementType("org.bflow.toolbox.oepc.diagram.BusinessAttribute_3001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BusinessMethod_3002 = getElementType("org.bflow.toolbox.oepc.diagram.BusinessMethod_3002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ControlFlowEdge_4001 = getElementType("org.bflow.toolbox.oepc.diagram.ControlFlowEdge_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InformationEdge_4002 = getElementType("org.bflow.toolbox.oepc.diagram.InformationEdge_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return OepcDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(OEPC_79, OepcPackage.eINSTANCE.getOEPC());

			elements.put(Event_2001, OepcPackage.eINSTANCE.getEvent());

			elements.put(ITSystem_2002, OepcPackage.eINSTANCE.getITSystem());

			elements.put(OrganisationUnit_2003, OepcPackage.eINSTANCE
					.getOrganisationUnit());

			elements.put(XORConnector_2004, OepcPackage.eINSTANCE
					.getXORConnector());

			elements.put(BusinessObject_2005, OepcPackage.eINSTANCE
					.getBusinessObject());

			elements.put(ANDConnector_2006, OepcPackage.eINSTANCE
					.getANDConnector());

			elements.put(ORConnector_2007, OepcPackage.eINSTANCE
					.getORConnector());

			elements.put(Document_2008, OepcPackage.eINSTANCE.getDocument());

			elements.put(BusinessAttribute_3001, OepcPackage.eINSTANCE
					.getBusinessAttribute());

			elements.put(BusinessMethod_3002, OepcPackage.eINSTANCE
					.getBusinessMethod());

			elements.put(ControlFlowEdge_4001, OepcPackage.eINSTANCE
					.getControlFlowEdge());

			elements.put(InformationEdge_4002, OepcPackage.eINSTANCE
					.getInformationEdge());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(OEPC_79);
			KNOWN_ELEMENT_TYPES.add(Event_2001);
			KNOWN_ELEMENT_TYPES.add(ITSystem_2002);
			KNOWN_ELEMENT_TYPES.add(OrganisationUnit_2003);
			KNOWN_ELEMENT_TYPES.add(XORConnector_2004);
			KNOWN_ELEMENT_TYPES.add(BusinessObject_2005);
			KNOWN_ELEMENT_TYPES.add(ANDConnector_2006);
			KNOWN_ELEMENT_TYPES.add(ORConnector_2007);
			KNOWN_ELEMENT_TYPES.add(Document_2008);
			KNOWN_ELEMENT_TYPES.add(BusinessAttribute_3001);
			KNOWN_ELEMENT_TYPES.add(BusinessMethod_3002);
			KNOWN_ELEMENT_TYPES.add(ControlFlowEdge_4001);
			KNOWN_ELEMENT_TYPES.add(InformationEdge_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
