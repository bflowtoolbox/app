/*
 * 
 */
package orgchart.diagram.providers;

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
import orgchart.OrgchartPackage;
import orgchart.diagram.edit.parts.ExternalPersonEditPart;
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.InternalPersonEditPart;
import orgchart.diagram.edit.parts.LocationEditPart;
import orgchart.diagram.edit.parts.ModelEditPart;
import orgchart.diagram.edit.parts.ParticipantEditPart;
import orgchart.diagram.edit.parts.PersonTypeEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.edit.parts.Relation1EditPart;
import orgchart.diagram.edit.parts.Relation2EditPart;
import orgchart.diagram.part.OrgcDiagramEditorPlugin;

/**
 * @generated
 */
public class OrgcElementTypes {

	/**
	 * @generated
	 */
	private OrgcElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			OrgcDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Model_1000 = getElementType("org.bflow.toolbox.orgc.diagram.Model_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Position_2001 = getElementType("org.bflow.toolbox.orgc.diagram.Position_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InternalPerson_2002 = getElementType("org.bflow.toolbox.orgc.diagram.InternalPerson_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ExternalPerson_2003 = getElementType("org.bflow.toolbox.orgc.diagram.ExternalPerson_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Group_2004 = getElementType("org.bflow.toolbox.orgc.diagram.Group_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Participant_2005 = getElementType("org.bflow.toolbox.orgc.diagram.Participant_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType PersonType_2006 = getElementType("org.bflow.toolbox.orgc.diagram.PersonType_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Location_2007 = getElementType("org.bflow.toolbox.orgc.diagram.Location_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Relation1_4001 = getElementType("org.bflow.toolbox.orgc.diagram.Relation1_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Relation2_4002 = getElementType("org.bflow.toolbox.orgc.diagram.Relation2_4002"); //$NON-NLS-1$

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

			elements.put(Model_1000, OrgchartPackage.eINSTANCE.getModel());

			elements.put(Position_2001, OrgchartPackage.eINSTANCE.getPosition());

			elements.put(InternalPerson_2002,
					OrgchartPackage.eINSTANCE.getInternalPerson());

			elements.put(ExternalPerson_2003,
					OrgchartPackage.eINSTANCE.getExternalPerson());

			elements.put(Group_2004, OrgchartPackage.eINSTANCE.getGroup());

			elements.put(Participant_2005,
					OrgchartPackage.eINSTANCE.getParticipant());

			elements.put(PersonType_2006,
					OrgchartPackage.eINSTANCE.getPersonType());

			elements.put(Location_2007, OrgchartPackage.eINSTANCE.getLocation());

			elements.put(Relation1_4001,
					OrgchartPackage.eINSTANCE.getRelation1());

			elements.put(Relation2_4002,
					OrgchartPackage.eINSTANCE.getRelation2());
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
			KNOWN_ELEMENT_TYPES.add(Position_2001);
			KNOWN_ELEMENT_TYPES.add(InternalPerson_2002);
			KNOWN_ELEMENT_TYPES.add(ExternalPerson_2003);
			KNOWN_ELEMENT_TYPES.add(Group_2004);
			KNOWN_ELEMENT_TYPES.add(Participant_2005);
			KNOWN_ELEMENT_TYPES.add(PersonType_2006);
			KNOWN_ELEMENT_TYPES.add(Location_2007);
			KNOWN_ELEMENT_TYPES.add(Relation1_4001);
			KNOWN_ELEMENT_TYPES.add(Relation2_4002);
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
		case PositionEditPart.VISUAL_ID:
			return Position_2001;
		case InternalPersonEditPart.VISUAL_ID:
			return InternalPerson_2002;
		case ExternalPersonEditPart.VISUAL_ID:
			return ExternalPerson_2003;
		case GroupEditPart.VISUAL_ID:
			return Group_2004;
		case ParticipantEditPart.VISUAL_ID:
			return Participant_2005;
		case PersonTypeEditPart.VISUAL_ID:
			return PersonType_2006;
		case LocationEditPart.VISUAL_ID:
			return Location_2007;
		case Relation1EditPart.VISUAL_ID:
			return Relation1_4001;
		case Relation2EditPart.VISUAL_ID:
			return Relation2_4002;
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
			return orgchart.diagram.providers.OrgcElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return orgchart.diagram.providers.OrgcElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return orgchart.diagram.providers.OrgcElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
