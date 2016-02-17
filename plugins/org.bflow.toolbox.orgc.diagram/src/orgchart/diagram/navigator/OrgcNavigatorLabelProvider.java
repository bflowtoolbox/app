/*
 * 
 */
package orgchart.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import orgchart.diagram.edit.parts.ExternalPersonEditPart;
import orgchart.diagram.edit.parts.ExternalPersonNameEditPart;
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.GroupNameEditPart;
import orgchart.diagram.edit.parts.InternalPersonEditPart;
import orgchart.diagram.edit.parts.InternalPersonNameEditPart;
import orgchart.diagram.edit.parts.LocationEditPart;
import orgchart.diagram.edit.parts.LocationNameEditPart;
import orgchart.diagram.edit.parts.ModelEditPart;
import orgchart.diagram.edit.parts.ParticipantEditPart;
import orgchart.diagram.edit.parts.ParticipantNameEditPart;
import orgchart.diagram.edit.parts.PersonTypeEditPart;
import orgchart.diagram.edit.parts.PersonTypeNameEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.edit.parts.PositionNameEditPart;
import orgchart.diagram.edit.parts.Relation1EditPart;
import orgchart.diagram.edit.parts.Relation2EditPart;
import orgchart.diagram.part.OrgcDiagramEditorPlugin;
import orgchart.diagram.part.OrgcVisualIDRegistry;
import orgchart.diagram.providers.OrgcElementTypes;
import orgchart.diagram.providers.OrgcParserProvider;

/**
 * @generated
 */
public class OrgcNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		OrgcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		OrgcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof OrgcNavigatorItem
				&& !isOwnView(((OrgcNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof OrgcNavigatorGroup) {
			OrgcNavigatorGroup group = (OrgcNavigatorGroup) element;
			return OrgcDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof OrgcNavigatorItem) {
			OrgcNavigatorItem navigatorItem = (OrgcNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		// Due to plugin.xml content will be called only for "own" views
		if (element instanceof IAdaptable) {
			View view = (View) ((IAdaptable) element).getAdapter(View.class);
			if (view != null && isOwnView(view)) {
				return getImage(view);
			}
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?orgc?Model", OrgcElementTypes.Model_1000); //$NON-NLS-1$
		case PositionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?Position", OrgcElementTypes.Position_2001); //$NON-NLS-1$
		case InternalPersonEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?InternalPerson", OrgcElementTypes.InternalPerson_2002); //$NON-NLS-1$
		case ExternalPersonEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?ExternalPerson", OrgcElementTypes.ExternalPerson_2003); //$NON-NLS-1$
		case GroupEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?Group", OrgcElementTypes.Group_2004); //$NON-NLS-1$
		case ParticipantEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?Participant", OrgcElementTypes.Participant_2005); //$NON-NLS-1$
		case PersonTypeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?PersonType", OrgcElementTypes.PersonType_2006); //$NON-NLS-1$
		case LocationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?orgc?Location", OrgcElementTypes.Location_2007); //$NON-NLS-1$
		case Relation1EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?orgc?Relation1", OrgcElementTypes.Relation1_4001); //$NON-NLS-1$
		case Relation2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?orgc?Relation2", OrgcElementTypes.Relation2_4002); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = OrgcDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& OrgcElementTypes.isKnownElementType(elementType)) {
			image = OrgcElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof OrgcNavigatorGroup) {
			OrgcNavigatorGroup group = (OrgcNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof OrgcNavigatorItem) {
			OrgcNavigatorItem navigatorItem = (OrgcNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		// Due to plugin.xml content will be called only for "own" views
		if (element instanceof IAdaptable) {
			View view = (View) ((IAdaptable) element).getAdapter(View.class);
			if (view != null && isOwnView(view)) {
				return getText(view);
			}
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case PositionEditPart.VISUAL_ID:
			return getPosition_2001Text(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2002Text(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2003Text(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2004Text(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2005Text(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2006Text(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2007Text(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001Text(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getModel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getPosition_2001Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.Position_2001,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry.getType(PositionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getInternalPerson_2002Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.InternalPerson_2002,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry
						.getType(InternalPersonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getExternalPerson_2003Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.ExternalPerson_2003,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry
						.getType(ExternalPersonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGroup_2004Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.Group_2004,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry.getType(GroupNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getParticipant_2005Text(View view) {
		IParser parser = OrgcParserProvider
				.getParser(OrgcElementTypes.Participant_2005,
						view.getElement() != null ? view.getElement() : view,
						OrgcVisualIDRegistry
								.getType(ParticipantNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPersonType_2006Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.PersonType_2006,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry.getType(PersonTypeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getLocation_2007Text(View view) {
		IParser parser = OrgcParserProvider.getParser(
				OrgcElementTypes.Location_2007,
				view.getElement() != null ? view.getElement() : view,
				OrgcVisualIDRegistry.getType(LocationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OrgcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getRelation1_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getRelation2_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return ModelEditPart.MODEL_ID.equals(OrgcVisualIDRegistry
				.getModelID(view));
	}

}
