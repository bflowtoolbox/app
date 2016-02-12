/*
 * 
 */
package vcchart.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import vcchart.VcchartPackage;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.ClusterEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.ModelEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.Relation1EditPart;
import vcchart.diagram.edit.parts.Relation2EditPart;
import vcchart.diagram.edit.parts.Relation3EditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;
import vcchart.diagram.part.VcDiagramEditorPlugin;

/**
 * @generated
 */
public class VcElementTypes {

	/**
	 * @generated
	 */
	private VcElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			VcDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Model_1000 = getElementType("org.bflow.toolbox.vc.diagram.Model_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Product_2001 = getElementType("org.bflow.toolbox.vc.diagram.Product_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Objective_2002 = getElementType("org.bflow.toolbox.vc.diagram.Objective_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Activity1_2003 = getElementType("org.bflow.toolbox.vc.diagram.Activity1_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Activity2_2004 = getElementType("org.bflow.toolbox.vc.diagram.Activity2_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Cluster_2005 = getElementType("org.bflow.toolbox.vc.diagram.Cluster_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TechnicalTerm_2006 = getElementType("org.bflow.toolbox.vc.diagram.TechnicalTerm_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Participant_2007 = getElementType("org.bflow.toolbox.vc.diagram.Participant_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Application_2008 = getElementType("org.bflow.toolbox.vc.diagram.Application_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Document_2009 = getElementType("org.bflow.toolbox.vc.diagram.Document_2009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Relation1_4001 = getElementType("org.bflow.toolbox.vc.diagram.Relation1_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Relation2_4002 = getElementType("org.bflow.toolbox.vc.diagram.Relation2_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Relation3_4003 = getElementType("org.bflow.toolbox.vc.diagram.Relation3_4003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Model_1000, VcchartPackage.eINSTANCE.getModel());

			elements.put(Product_2001, VcchartPackage.eINSTANCE.getProduct());

			elements.put(Objective_2002,
					VcchartPackage.eINSTANCE.getObjective());

			elements.put(Activity1_2003,
					VcchartPackage.eINSTANCE.getActivity1());

			elements.put(Activity2_2004,
					VcchartPackage.eINSTANCE.getActivity2());

			elements.put(Cluster_2005, VcchartPackage.eINSTANCE.getCluster());

			elements.put(TechnicalTerm_2006,
					VcchartPackage.eINSTANCE.getTechnicalTerm());

			elements.put(Participant_2007,
					VcchartPackage.eINSTANCE.getParticipant());

			elements.put(Application_2008,
					VcchartPackage.eINSTANCE.getApplication());

			elements.put(Document_2009, VcchartPackage.eINSTANCE.getDocument());

			elements.put(Relation1_4001,
					VcchartPackage.eINSTANCE.getRelation1());

			elements.put(Relation2_4002,
					VcchartPackage.eINSTANCE.getRelation2());

			elements.put(Relation3_4003,
					VcchartPackage.eINSTANCE.getRelation3());
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
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Model_1000);
			KNOWN_ELEMENT_TYPES.add(Product_2001);
			KNOWN_ELEMENT_TYPES.add(Objective_2002);
			KNOWN_ELEMENT_TYPES.add(Activity1_2003);
			KNOWN_ELEMENT_TYPES.add(Activity2_2004);
			KNOWN_ELEMENT_TYPES.add(Cluster_2005);
			KNOWN_ELEMENT_TYPES.add(TechnicalTerm_2006);
			KNOWN_ELEMENT_TYPES.add(Participant_2007);
			KNOWN_ELEMENT_TYPES.add(Application_2008);
			KNOWN_ELEMENT_TYPES.add(Document_2009);
			KNOWN_ELEMENT_TYPES.add(Relation1_4001);
			KNOWN_ELEMENT_TYPES.add(Relation2_4002);
			KNOWN_ELEMENT_TYPES.add(Relation3_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case ModelEditPart.VISUAL_ID:
			return Model_1000;
		case ProductEditPart.VISUAL_ID:
			return Product_2001;
		case ObjectiveEditPart.VISUAL_ID:
			return Objective_2002;
		case Activity1EditPart.VISUAL_ID:
			return Activity1_2003;
		case Activity2EditPart.VISUAL_ID:
			return Activity2_2004;
		case ClusterEditPart.VISUAL_ID:
			return Cluster_2005;
		case TechnicalTermEditPart.VISUAL_ID:
			return TechnicalTerm_2006;
		case ParticipantEditPart.VISUAL_ID:
			return Participant_2007;
		case ApplicationEditPart.VISUAL_ID:
			return Application_2008;
		case DocumentEditPart.VISUAL_ID:
			return Document_2009;
		case Relation1EditPart.VISUAL_ID:
			return Relation1_4001;
		case Relation2EditPart.VISUAL_ID:
			return Relation2_4002;
		case Relation3EditPart.VISUAL_ID:
			return Relation3_4003;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(
			elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return vcchart.diagram.providers.VcElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return vcchart.diagram.providers.VcElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return vcchart.diagram.providers.VcElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
