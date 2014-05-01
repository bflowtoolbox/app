package org.bflow.toolbox.epc.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
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
public class EpcElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private EpcElementTypes() {
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
	public static final IElementType Epc_79 = getElementType("org.bflow.toolbox.epc.diagram.Epc_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType OR_2001 = getElementType("org.bflow.toolbox.epc.diagram.OR_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Participant_2002 = getElementType("org.bflow.toolbox.epc.diagram.Participant_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AND_2003 = getElementType("org.bflow.toolbox.epc.diagram.AND_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Application_2004 = getElementType("org.bflow.toolbox.epc.diagram.Application_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ProcessInterface_2005 = getElementType("org.bflow.toolbox.epc.diagram.ProcessInterface_2005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Event_2006 = getElementType("org.bflow.toolbox.epc.diagram.Event_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Function_2007 = getElementType("org.bflow.toolbox.epc.diagram.Function_2007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType XOR_2008 = getElementType("org.bflow.toolbox.epc.diagram.XOR_2008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Group_2009 = getElementType("org.bflow.toolbox.epc.diagram.Group_2009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Cluster_2010 = getElementType("org.bflow.toolbox.epc.diagram.Cluster_2010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ExternalPerson_2011 = getElementType("org.bflow.toolbox.epc.diagram.ExternalPerson_2011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InternalPerson_2012 = getElementType("org.bflow.toolbox.epc.diagram.InternalPerson_2012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Position_2013 = getElementType("org.bflow.toolbox.epc.diagram.Position_2013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Location_2014 = getElementType("org.bflow.toolbox.epc.diagram.Location_2014"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PersonType_2015 = getElementType("org.bflow.toolbox.epc.diagram.PersonType_2015"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TechnicalTerm_2016 = getElementType("org.bflow.toolbox.epc.diagram.TechnicalTerm_2016"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType CardFile_2017 = getElementType("org.bflow.toolbox.epc.diagram.CardFile_2017"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Document_2018 = getElementType("org.bflow.toolbox.epc.diagram.Document_2018"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType File_2019 = getElementType("org.bflow.toolbox.epc.diagram.File_2019"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Objective_2020 = getElementType("org.bflow.toolbox.epc.diagram.Objective_2020"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Product_2021 = getElementType("org.bflow.toolbox.epc.diagram.Product_2021"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Arc_4001 = getElementType("org.bflow.toolbox.epc.diagram.Arc_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Relation_4002 = getElementType("org.bflow.toolbox.epc.diagram.Relation_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InformationArc_4003 = getElementType("org.bflow.toolbox.epc.diagram.InformationArc_4003"); //$NON-NLS-1$

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
				return EpcDiagramEditorPlugin.getInstance()
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
		if (hint == Event_2006) {
			iconName = "event.gif";
		} else if (hint == Function_2007) {
			iconName = "function.gif";
		} else if (hint == Application_2004) {
			iconName = "application.gif";
		} else if (hint == Participant_2002) {
			iconName = "participant.gif";
		} else if (hint == ProcessInterface_2005) {
			iconName = "processinterface.gif";
		} else if (hint == AND_2003) {
			iconName = "and.gif";
		} else if (hint == XOR_2008) {
			iconName = "xor.gif";
		} else if (hint == OR_2001) {
			iconName = "or.gif";
		} else if (hint == Group_2009) {
			iconName = "group.gif";
		} else if (hint == Location_2014) {
			iconName = "location.gif";
		} else if (hint == Position_2013) {
			iconName = "position.gif";
		} else if (hint == File_2019) {
			iconName = "file.gif";
		} else if (hint == CardFile_2017) {
			iconName = "cardfile.gif";
		} else if (hint == Cluster_2010) {
			iconName = "cluster.gif";
		} else if (hint == InternalPerson_2012) {
			iconName = "intperson.gif";
		} else if (hint == ExternalPerson_2011) {
			iconName = "extperson.gif";
		} else if (hint == PersonType_2015) {
			iconName = "persontype.gif";
		} else if (hint == TechnicalTerm_2016) {
			iconName = "technicalterm.gif";
		} else if (hint == Document_2018) {
			iconName = "document.gif";
		} else if (hint == Product_2021) {
			iconName = "product.gif";
		} else if (hint == Objective_2020) {
			iconName = "objective.gif";
		} else if (hint == Arc_4001) {
			iconName = "arc.gif";
		} else if (hint == Relation_4002) {
			iconName = "relation.gif";
		} else if (hint == InformationArc_4003) {
			iconName = "infarc.gif";
		}
		if (iconName != null) {
			iconName = "icons/" + iconName;
			Image image = getImageRegistry().get(iconName);
			if (image == null) {
				ImageDescriptor imageDescriptor = AbstractUIPlugin
						.imageDescriptorFromPlugin(EpcDiagramEditorPlugin.ID,
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

			elements.put(Epc_79, EpcPackage.eINSTANCE.getEpc());

			elements.put(OR_2001, EpcPackage.eINSTANCE.getOR());

			elements.put(Participant_2002, EpcPackage.eINSTANCE
					.getParticipant());

			elements.put(AND_2003, EpcPackage.eINSTANCE.getAND());

			elements.put(Application_2004, EpcPackage.eINSTANCE
					.getApplication());

			elements.put(ProcessInterface_2005, EpcPackage.eINSTANCE
					.getProcessInterface());

			elements.put(Event_2006, EpcPackage.eINSTANCE.getEvent());

			elements.put(Function_2007, EpcPackage.eINSTANCE.getFunction());

			elements.put(XOR_2008, EpcPackage.eINSTANCE.getXOR());

			elements.put(Group_2009, EpcPackage.eINSTANCE.getGroup());

			elements.put(Cluster_2010, EpcPackage.eINSTANCE.getCluster());

			elements.put(ExternalPerson_2011, EpcPackage.eINSTANCE
					.getExternalPerson());

			elements.put(InternalPerson_2012, EpcPackage.eINSTANCE
					.getInternalPerson());

			elements.put(Position_2013, EpcPackage.eINSTANCE.getPosition());

			elements.put(Location_2014, EpcPackage.eINSTANCE.getLocation());

			elements.put(PersonType_2015, EpcPackage.eINSTANCE.getPersonType());

			elements.put(TechnicalTerm_2016, EpcPackage.eINSTANCE
					.getTechnicalTerm());

			elements.put(CardFile_2017, EpcPackage.eINSTANCE.getCardFile());

			elements.put(Document_2018, EpcPackage.eINSTANCE.getDocument());

			elements.put(File_2019, EpcPackage.eINSTANCE.getFile());

			elements.put(Objective_2020, EpcPackage.eINSTANCE.getObjective());

			elements.put(Product_2021, EpcPackage.eINSTANCE.getProduct());

			elements.put(Arc_4001, EpcPackage.eINSTANCE.getArc());

			elements.put(Relation_4002, EpcPackage.eINSTANCE.getRelation());

			elements.put(InformationArc_4003, EpcPackage.eINSTANCE
					.getInformationArc());
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
			KNOWN_ELEMENT_TYPES.add(Epc_79);
			KNOWN_ELEMENT_TYPES.add(OR_2001);
			KNOWN_ELEMENT_TYPES.add(Participant_2002);
			KNOWN_ELEMENT_TYPES.add(AND_2003);
			KNOWN_ELEMENT_TYPES.add(Application_2004);
			KNOWN_ELEMENT_TYPES.add(ProcessInterface_2005);
			KNOWN_ELEMENT_TYPES.add(Event_2006);
			KNOWN_ELEMENT_TYPES.add(Function_2007);
			KNOWN_ELEMENT_TYPES.add(XOR_2008);
			KNOWN_ELEMENT_TYPES.add(Group_2009);
			KNOWN_ELEMENT_TYPES.add(Cluster_2010);
			KNOWN_ELEMENT_TYPES.add(ExternalPerson_2011);
			KNOWN_ELEMENT_TYPES.add(InternalPerson_2012);
			KNOWN_ELEMENT_TYPES.add(Position_2013);
			KNOWN_ELEMENT_TYPES.add(Location_2014);
			KNOWN_ELEMENT_TYPES.add(PersonType_2015);
			KNOWN_ELEMENT_TYPES.add(TechnicalTerm_2016);
			KNOWN_ELEMENT_TYPES.add(CardFile_2017);
			KNOWN_ELEMENT_TYPES.add(Document_2018);
			KNOWN_ELEMENT_TYPES.add(File_2019);
			KNOWN_ELEMENT_TYPES.add(Objective_2020);
			KNOWN_ELEMENT_TYPES.add(Product_2021);
			KNOWN_ELEMENT_TYPES.add(Arc_4001);
			KNOWN_ELEMENT_TYPES.add(Relation_4002);
			KNOWN_ELEMENT_TYPES.add(InformationArc_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
