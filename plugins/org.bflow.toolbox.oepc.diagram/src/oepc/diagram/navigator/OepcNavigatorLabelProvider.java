package oepc.diagram.navigator;

import oepc.ANDConnector;
import oepc.ControlFlowEdge;
import oepc.Document;
import oepc.InformationEdge;
import oepc.ORConnector;
import oepc.XORConnector;
import oepc.diagram.edit.parts.ANDConnectorEditPart;
import oepc.diagram.edit.parts.BusinessAttributeEditPart;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.edit.parts.BusinessObjectEditPart;
import oepc.diagram.edit.parts.BusinessObjectNameEditPart;
import oepc.diagram.edit.parts.ControlFlowEdgeEditPart;
import oepc.diagram.edit.parts.DocumentEditPart;
import oepc.diagram.edit.parts.DocumentNameEditPart;
import oepc.diagram.edit.parts.EventEditPart;
import oepc.diagram.edit.parts.EventNameEditPart;
import oepc.diagram.edit.parts.ITSystemEditPart;
import oepc.diagram.edit.parts.ITSystemNameEditPart;
import oepc.diagram.edit.parts.InformationEdgeEditPart;
import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.edit.parts.OrganisationUnitEditPart;
import oepc.diagram.edit.parts.OrganisationUnitNameEditPart;
import oepc.diagram.edit.parts.XORConnectorEditPart;
import oepc.diagram.part.OepcDiagramEditorPlugin;
import oepc.diagram.part.OepcVisualIDRegistry;
import oepc.diagram.providers.OepcElementTypes;
import oepc.diagram.providers.OepcParserProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
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

/**
 * @generated
 */
public class OepcNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		OepcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		OepcDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof OepcNavigatorItem
				&& !isOwnView(((OepcNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof OepcNavigatorGroup) {
			OepcNavigatorGroup group = (OepcNavigatorGroup) element;
			return OepcDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof OepcNavigatorItem) {
			OepcNavigatorItem navigatorItem = (OepcNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case OEPCEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?org.bflow.toolbox.oepc?OEPC", OepcElementTypes.OEPC_79); //$NON-NLS-1$
		case EventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?Event", OepcElementTypes.Event_2001); //$NON-NLS-1$
		case ITSystemEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?ITSystem", OepcElementTypes.ITSystem_2002); //$NON-NLS-1$
		case OrganisationUnitEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?OrganisationUnit", OepcElementTypes.OrganisationUnit_2003); //$NON-NLS-1$
		case XORConnectorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?XORConnector", OepcElementTypes.XORConnector_2004); //$NON-NLS-1$
		case BusinessObjectEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?BusinessObject", OepcElementTypes.BusinessObject_2005); //$NON-NLS-1$
		case ANDConnectorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?ANDConnector", OepcElementTypes.ANDConnector_2006); //$NON-NLS-1$
		case ORConnectorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?ORConnector", OepcElementTypes.ORConnector_2007); //$NON-NLS-1$
		case DocumentEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?org.bflow.toolbox.oepc?Document", OepcElementTypes.Document_2008); //$NON-NLS-1$
		case BusinessAttributeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?org.bflow.toolbox.oepc?BusinessAttribute", OepcElementTypes.BusinessAttribute_3001); //$NON-NLS-1$
		case BusinessMethodEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?org.bflow.toolbox.oepc?BusinessMethod", OepcElementTypes.BusinessMethod_3002); //$NON-NLS-1$
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?org.bflow.toolbox.oepc?ControlFlowEdge", OepcElementTypes.ControlFlowEdge_4001); //$NON-NLS-1$
		case InformationEdgeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?org.bflow.toolbox.oepc?InformationEdge", OepcElementTypes.InformationEdge_4002); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = OepcDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& OepcElementTypes.isKnownElementType(elementType)) {
			image = OepcElementTypes.getImage(elementType);
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
		if (element instanceof OepcNavigatorGroup) {
			OepcNavigatorGroup group = (OepcNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof OepcNavigatorItem) {
			OepcNavigatorItem navigatorItem = (OepcNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
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
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case OEPCEditPart.VISUAL_ID:
			return getOEPC_79Text(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2001Text(view);
		case ITSystemEditPart.VISUAL_ID:
			return getITSystem_2002Text(view);
		case OrganisationUnitEditPart.VISUAL_ID:
			return getOrganisationUnit_2003Text(view);
		case XORConnectorEditPart.VISUAL_ID:
			return getXORConnector_2004Text(view);
		case BusinessObjectEditPart.VISUAL_ID:
			return getBusinessObject_2005Text(view);
		case ANDConnectorEditPart.VISUAL_ID:
			return getANDConnector_2006Text(view);
		case ORConnectorEditPart.VISUAL_ID:
			return getORConnector_2007Text(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2008Text(view);
		case BusinessAttributeEditPart.VISUAL_ID:
			return getBusinessAttribute_3001Text(view);
		case BusinessMethodEditPart.VISUAL_ID:
			return getBusinessMethod_3002Text(view);
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getControlFlowEdge_4001Text(view);
		case InformationEdgeEditPart.VISUAL_ID:
			return getInformationEdge_4002Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getOEPC_79Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getEvent_2001Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.Event_2001, (view.getElement() != null ? view
						.getElement() : view), OepcVisualIDRegistry
						.getType(EventNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getITSystem_2002Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.ITSystem_2002,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry.getType(ITSystemNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getOrganisationUnit_2003Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.OrganisationUnit_2003,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry
						.getType(OrganisationUnitNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getXORConnector_2004Text(View view) {
		XORConnector domainModelElement = (XORConnector) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBusinessObject_2005Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.BusinessObject_2005,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry
						.getType(BusinessObjectNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getANDConnector_2006Text(View view) {
		ANDConnector domainModelElement = (ANDConnector) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getORConnector_2007Text(View view) {
		ORConnector domainModelElement = (ORConnector) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDocument_2008Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.Document_2008,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry.getType(DocumentNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getBusinessAttribute_3001Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.BusinessAttribute_3001,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry
						.getType(BusinessAttributeEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 3001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBusinessMethod_3002Text(View view) {
		IAdaptable hintAdapter = new OepcParserProvider.HintAdapter(
				OepcElementTypes.BusinessMethod_3002,
				(view.getElement() != null ? view.getElement() : view),
				OepcVisualIDRegistry.getType(BusinessMethodEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 3002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getControlFlowEdge_4001Text(View view) {
		ControlFlowEdge domainModelElement = (ControlFlowEdge) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getInformationEdge_4002Text(View view) {
		InformationEdge domainModelElement = (InformationEdge) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			OepcDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
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
		return OEPCEditPart.MODEL_ID.equals(OepcVisualIDRegistry
				.getModelID(view));
	}

}
