package oepc.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import oepc.Document;
import oepc.ITSystem;
import oepc.OrganisationUnit;
import oepc.diagram.edit.parts.BusinessObjectEditPart;
import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.part.Messages;
import oepc.diagram.part.OepcDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class OepcModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated NOT
	 */
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof BusinessObjectEditPart) {
			List<IElementType> types = new ArrayList<IElementType>();
			types.add(OepcElementTypes.BusinessAttribute_3001);
			types.add(OepcElementTypes.BusinessMethod_3002);
			return types;
		}
		if (editPart instanceof OEPCEditPart) {
			List<IElementType> types = new ArrayList<IElementType>();
			types.add(OepcElementTypes.Event_2001);
			types.add(OepcElementTypes.ITSystem_2002);
			types.add(OepcElementTypes.OrganisationUnit_2003);
			types.add(OepcElementTypes.XORConnector_2004);
			types.add(OepcElementTypes.BusinessObject_2005);
			types.add(OepcElementTypes.ANDConnector_2006);
			types.add(OepcElementTypes.ORConnector_2007);
			types.add(OepcElementTypes.Document_2008);
			return types;
		}
		return Collections.emptyList();
	}

	/**
	 * @generated NOT
	 */
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		List<IElementType> types = new ArrayList<IElementType>();
		EObject semanticElement = sourceEditPart.resolveSemanticElement();

		if (semanticElement instanceof OrganisationUnit
				|| semanticElement instanceof ITSystem
				|| semanticElement instanceof Document) {
			types.add(OepcElementTypes.InformationEdge_4002);
		} else {
			types.add(OepcElementTypes.InformationEdge_4002);
			types.add(OepcElementTypes.ControlFlowEdge_4001);
		}

		return types;
	}

	/**
	 * @generated NOT
	 */
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		List<IElementType> types = new ArrayList<IElementType>();
		EObject semanticElement = targetEditPart.resolveSemanticElement();

		if (semanticElement instanceof OrganisationUnit
				|| semanticElement instanceof ITSystem
				|| semanticElement instanceof Document) {
			types.add(OepcElementTypes.InformationEdge_4002);
		} else {
			types.add(OepcElementTypes.InformationEdge_4002);
			types.add(OepcElementTypes.ControlFlowEdge_4001);
		}

		return types;
	}

	/**
	 * @generated NOT
	 */
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		List<IElementType> types = new ArrayList<IElementType>();
		EObject sourceSemanticEO = sourceEditPart.resolveSemanticElement();
		EObject targetSemanticEO = targetEditPart.resolveSemanticElement();

		if (sourceSemanticEO instanceof OrganisationUnit
				|| sourceSemanticEO instanceof ITSystem
				|| sourceSemanticEO instanceof Document) {
			types.add(OepcElementTypes.InformationEdge_4002);
		} else if (targetSemanticEO instanceof OrganisationUnit
				|| targetSemanticEO instanceof ITSystem
				|| targetSemanticEO instanceof Document) {
			types.add(OepcElementTypes.InformationEdge_4002);
		} else {
			types.add(OepcElementTypes.ControlFlowEdge_4001);
		}

		return types;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target,
				relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source,
				relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				OepcDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.OepcModelingAssistantProviderMessage);
		dialog.setTitle(Messages.OepcModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
