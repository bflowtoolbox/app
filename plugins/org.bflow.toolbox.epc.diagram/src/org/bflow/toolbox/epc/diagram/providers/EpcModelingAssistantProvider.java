package org.bflow.toolbox.epc.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.bflow.toolbox.bflow.IBflowElement;
import org.bflow.toolbox.bflow.IConnector;
import org.bflow.toolbox.bflow.IEBflowElement;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.diagram.part.Messages;
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
public class EpcModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated NOT
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof EpcEditPart) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Event_2006);
			types.add(EpcElementTypes.Function_2007);
			types.add(EpcElementTypes.Application_2004);
			types.add(EpcElementTypes.Participant_2002);
			types.add(EpcElementTypes.ProcessInterface_2005);
			types.add(EpcElementTypes.AND_2003);
			types.add(EpcElementTypes.OR_2001);
			types.add(EpcElementTypes.XOR_2008);
			//			types.add(EpcElementTypes.Group_2017);
			//			types.add(EpcElementTypes.Location_2018);
			//			types.add(EpcElementTypes.Position_2019);
			//			types.add(EpcElementTypes.File_2020);
			//			types.add(EpcElementTypes.CardFile_2021);
			//			types.add(EpcElementTypes.Cluster_2022);
			//			types.add(EpcElementTypes.InternalPerson_2023);
			//			types.add(EpcElementTypes.ExternalPerson_2024);
			//			types.add(EpcElementTypes.PersonType_2025);
			//			types.add(EpcElementTypes.TechnicalTerm_2026);
			//			types.add(EpcElementTypes.Document_2027);
			//			types.add(EpcElementTypes.Product_2029);
			//			types.add(EpcElementTypes.Objective_2028);

			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated NOT
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart.resolveSemanticElement() instanceof IEBflowElement) {
			return Collections.singletonList(EpcElementTypes.Relation_4002);
		}
		List rel = new ArrayList();
		rel.add(EpcElementTypes.Arc_4001);
		rel.add(EpcElementTypes.Relation_4002);
		return rel;
	}

	/**
	 * @generated NOT
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart.resolveSemanticElement() instanceof IEBflowElement) {
			return Collections.singletonList(EpcElementTypes.Relation_4002);
		}
		List rel = new ArrayList();
		rel.add(EpcElementTypes.Arc_4001);
		rel.add(EpcElementTypes.Relation_4002);
		return rel;
	}

	/**
	 * @generated NOT
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);

		if ((sourceEditPart.resolveSemanticElement() instanceof IEBflowElement && targetEditPart
				.resolveSemanticElement() instanceof Function)
				|| (targetEditPart.resolveSemanticElement() instanceof IEBflowElement && sourceEditPart
						.resolveSemanticElement() instanceof Function)) {
			return Collections.singletonList(EpcElementTypes.Relation_4002);
		}
		return Collections.singletonList(EpcElementTypes.Arc_4001);
	}

	/**
	 * @generated NOT
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);

		if (targetEditPart.resolveSemanticElement() instanceof IEBflowElement) {
			return Collections.singletonList(EpcElementTypes.Function_2007);
		} else if (targetEditPart.resolveSemanticElement() instanceof Function
				&& relationshipType.equals(EpcElementTypes.Relation_4002)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Participant_2002);
			types.add(EpcElementTypes.Application_2004);
			return types;
		} else if (targetEditPart.resolveSemanticElement() instanceof IBflowElement
				&& relationshipType.equals(EpcElementTypes.Arc_4001)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Event_2006);
			types.add(EpcElementTypes.Function_2007);
			types.add(EpcElementTypes.ProcessInterface_2005);
			types.add(EpcElementTypes.AND_2003);
			types.add(EpcElementTypes.XOR_2008);
			types.add(EpcElementTypes.OR_2001);
			return types;
		} else if (targetEditPart.resolveSemanticElement() instanceof IConnector
				&& relationshipType.equals(EpcElementTypes.Arc_4001)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Event_2006);
			types.add(EpcElementTypes.Function_2007);
			types.add(EpcElementTypes.ProcessInterface_2005);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated NOT
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart.resolveSemanticElement() instanceof IEBflowElement) {
			return Collections.singletonList(EpcElementTypes.Function_2007);
		} else if (sourceEditPart.resolveSemanticElement() instanceof Function
				&& relationshipType.equals(EpcElementTypes.Relation_4002)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Participant_2002);
			types.add(EpcElementTypes.Application_2004);
			return types;
		} else if (sourceEditPart.resolveSemanticElement() instanceof IBflowElement
				&& relationshipType.equals(EpcElementTypes.Arc_4001)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Event_2006);
			types.add(EpcElementTypes.Function_2007);
			types.add(EpcElementTypes.ProcessInterface_2005);
			types.add(EpcElementTypes.AND_2003);
			types.add(EpcElementTypes.XOR_2008);
			types.add(EpcElementTypes.OR_2001);
			return types;
		} else if (sourceEditPart.resolveSemanticElement() instanceof IConnector
				&& relationshipType.equals(EpcElementTypes.Arc_4001)) {
			List types = new ArrayList();
			types.add(EpcElementTypes.Event_2006);
			types.add(EpcElementTypes.Function_2007);
			types.add(EpcElementTypes.ProcessInterface_2005);
			return types;
		}
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
				EpcDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.EpcModelingAssistantProviderMessage);
		dialog.setTitle(Messages.EpcModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
