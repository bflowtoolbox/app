/*
 * 
 */
package vcchart.diagram.navigator;

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

import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity1NameEditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.Activity2NameEditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.ApplicationNameEditPart;
import vcchart.diagram.edit.parts.ClusterEditPart;
import vcchart.diagram.edit.parts.ClusterNameEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.DocumentNameEditPart;
import vcchart.diagram.edit.parts.ModelEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ObjectiveNameEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ParticipantNameEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.ProductNameEditPart;
import vcchart.diagram.edit.parts.Relation1EditPart;
import vcchart.diagram.edit.parts.Relation2EditPart;
import vcchart.diagram.edit.parts.Relation3EditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;
import vcchart.diagram.edit.parts.TechnicalTermNameEditPart;
import vcchart.diagram.part.VcDiagramEditorPlugin;
import vcchart.diagram.part.VcVisualIDRegistry;
import vcchart.diagram.providers.VcElementTypes;
import vcchart.diagram.providers.VcParserProvider;

/**
 * @generated
 */
public class VcNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		VcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		VcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof VcNavigatorItem
				&& !isOwnView(((VcNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof VcNavigatorGroup) {
			VcNavigatorGroup group = (VcNavigatorGroup) element;
			return VcDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof VcNavigatorItem) {
			VcNavigatorItem navigatorItem = (VcNavigatorItem) element;
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
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?vc?Model", VcElementTypes.Model_1000); //$NON-NLS-1$
		case ProductEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Product", VcElementTypes.Product_2001); //$NON-NLS-1$
		case ObjectiveEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Objective", VcElementTypes.Objective_2002); //$NON-NLS-1$
		case Activity1EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Activity1", VcElementTypes.Activity1_2003); //$NON-NLS-1$
		case Activity2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Activity2", VcElementTypes.Activity2_2004); //$NON-NLS-1$
		case ClusterEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Cluster", VcElementTypes.Cluster_2005); //$NON-NLS-1$
		case TechnicalTermEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?TechnicalTerm", VcElementTypes.TechnicalTerm_2006); //$NON-NLS-1$
		case ParticipantEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Participant", VcElementTypes.Participant_2007); //$NON-NLS-1$
		case ApplicationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Application", VcElementTypes.Application_2008); //$NON-NLS-1$
		case DocumentEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?vc?Document", VcElementTypes.Document_2009); //$NON-NLS-1$
		case Relation1EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?vc?Relation1", VcElementTypes.Relation1_4001); //$NON-NLS-1$
		case Relation2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?vc?Relation2", VcElementTypes.Relation2_4002); //$NON-NLS-1$
		case Relation3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?vc?Relation3", VcElementTypes.Relation3_4003); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = VcDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& VcElementTypes.isKnownElementType(elementType)) {
			image = VcElementTypes.getImage(elementType);
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
		if (element instanceof VcNavigatorGroup) {
			VcNavigatorGroup group = (VcNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof VcNavigatorItem) {
			VcNavigatorItem navigatorItem = (VcNavigatorItem) element;
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
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2001Text(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2002Text(view);
		case Activity1EditPart.VISUAL_ID:
			return getActivity1_2003Text(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity2_2004Text(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2005Text(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2006Text(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2007Text(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2008Text(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2009Text(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001Text(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002Text(view);
		case Relation3EditPart.VISUAL_ID:
			return getRelation3_4003Text(view);
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
	private String getProduct_2001Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Product_2001,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(ProductNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getObjective_2002Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Objective_2002,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(ObjectiveNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActivity1_2003Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Activity1_2003,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(Activity1NameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActivity2_2004Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Activity2_2004,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(Activity2NameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCluster_2005Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Cluster_2005,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(ClusterNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTechnicalTerm_2006Text(View view) {
		IParser parser = VcParserProvider
				.getParser(VcElementTypes.TechnicalTerm_2006,
						view.getElement() != null ? view.getElement() : view,
						VcVisualIDRegistry
								.getType(TechnicalTermNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getParticipant_2007Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Participant_2007,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(ParticipantNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getApplication_2008Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Application_2008,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(ApplicationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDocument_2009Text(View view) {
		IParser parser = VcParserProvider.getParser(
				VcElementTypes.Document_2009,
				view.getElement() != null ? view.getElement() : view,
				VcVisualIDRegistry.getType(DocumentNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			VcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5009); //$NON-NLS-1$
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
	private String getRelation3_4003Text(View view) {
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
		return ModelEditPart.MODEL_ID.equals(VcVisualIDRegistry
				.getModelID(view));
	}

}
