package org.bflow.toolbox.vc.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.bflow.toolbox.vc.VcPackage;
import org.bflow.toolbox.vc.diagram.part.VcDiagramEditorPlugin;
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
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @generated
 */
public class VcElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private VcElementTypes() {
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
	public static final IElementType Vc_1000 = getElementType("org.bflow.toolbox.vc.diagram.Vc_1000"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ValueChain_2001 = getElementType("org.bflow.toolbox.vc.diagram.ValueChain_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ValueChain2_2002 = getElementType("org.bflow.toolbox.vc.diagram.ValueChain2_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TechnicalTerm_2003 = getElementType("org.bflow.toolbox.vc.diagram.TechnicalTerm_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Cluster_2004 = getElementType("org.bflow.toolbox.vc.diagram.Cluster_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Objective_2005 = getElementType("org.bflow.toolbox.vc.diagram.Objective_2005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Product_2006 = getElementType("org.bflow.toolbox.vc.diagram.Product_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PredecessorConnection_4001 = getElementType("org.bflow.toolbox.vc.diagram.PredecessorConnection_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ProcessSuperiority_4002 = getElementType("org.bflow.toolbox.vc.diagram.ProcessSuperiority_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Relation_4003 = getElementType("org.bflow.toolbox.vc.diagram.Relation_4003"); //$NON-NLS-1$

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
				return VcDiagramEditorPlugin.getInstance()
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
	public static Image getImageGen(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * @generated NOT
	 */
	public static Image getImage(IAdaptable hint) {
		String iconName = null;
		if (hint == ValueChain_2001) {
			iconName = "valuechain.gif";
		} else if (hint == ValueChain2_2002) {
			iconName = "valuechain2.gif";
		} else if (hint == TechnicalTerm_2003) {
			iconName = "technicalterm.gif";
		} else if (hint == Cluster_2004) {
			iconName = "cluster.gif";
		} else if (hint == Objective_2005) {
			iconName = "objective.gif";
		} else if (hint == Product_2006) {
			iconName = "product.gif";
		}
		if (iconName != null) {
			iconName = "icons/" + iconName;
			Image image = getImageRegistry().get(iconName);
			if (image == null) {
				ImageDescriptor imageDescriptor = AbstractUIPlugin
						.imageDescriptorFromPlugin(VcDiagramEditorPlugin.ID,
								iconName);
				if (imageDescriptor == null) {
					imageDescriptor = ImageDescriptor
							.getMissingImageDescriptor();
				}
				getImageRegistry().put(iconName, imageDescriptor);
				image = getImageRegistry().get(iconName);
			}
			return image;
		}

		return getImageGen(hint);
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

			elements.put(Vc_1000, VcPackage.eINSTANCE.getVc());

			elements.put(ValueChain_2001, VcPackage.eINSTANCE.getValueChain());

			elements
					.put(ValueChain2_2002, VcPackage.eINSTANCE.getValueChain2());

			elements.put(TechnicalTerm_2003, VcPackage.eINSTANCE
					.getTechnicalTerm());

			elements.put(Cluster_2004, VcPackage.eINSTANCE.getCluster());

			elements.put(Objective_2005, VcPackage.eINSTANCE.getObjective());

			elements.put(Product_2006, VcPackage.eINSTANCE.getProduct());

			elements.put(PredecessorConnection_4001, VcPackage.eINSTANCE
					.getPredecessorConnection());

			elements.put(ProcessSuperiority_4002, VcPackage.eINSTANCE
					.getProcessSuperiority());

			elements.put(Relation_4003, VcPackage.eINSTANCE.getRelation());
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
			KNOWN_ELEMENT_TYPES.add(Vc_1000);
			KNOWN_ELEMENT_TYPES.add(ValueChain_2001);
			KNOWN_ELEMENT_TYPES.add(ValueChain2_2002);
			KNOWN_ELEMENT_TYPES.add(TechnicalTerm_2003);
			KNOWN_ELEMENT_TYPES.add(Cluster_2004);
			KNOWN_ELEMENT_TYPES.add(Objective_2005);
			KNOWN_ELEMENT_TYPES.add(Product_2006);
			KNOWN_ELEMENT_TYPES.add(PredecessorConnection_4001);
			KNOWN_ELEMENT_TYPES.add(ProcessSuperiority_4002);
			KNOWN_ELEMENT_TYPES.add(Relation_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
