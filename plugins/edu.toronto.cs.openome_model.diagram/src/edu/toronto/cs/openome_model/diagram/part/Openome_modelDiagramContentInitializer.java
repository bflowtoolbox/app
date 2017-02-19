package edu.toronto.cs.openome_model.diagram.part;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Openome_modelDiagramContentInitializer {

	/**
	 * @generated
	 */
	private Map myDomain2NotationMap = new HashMap();

	/**
	 * @generated
	 */
	private Collection myLinkDescriptors = new LinkedList();

	/**
	 * @generated
	 */
	public void initDiagramContent(Diagram diagram) {
		if (!edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.MODEL_ID
				.equals(diagram.getType())) {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Incorrect diagram passed as a parameter: "
									+ diagram.getType());
			return;
		}
		if (false == diagram.getElement() instanceof edu.toronto.cs.openome_model.Model) {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Incorrect diagram element specified: "
									+ diagram.getElement()
									+ " instead of Model");
			return;
		}
		createModel_1000Children(diagram);
		createLinks(diagram);
	}

	/**
	 * @generated
	 */
	private void createActor_2001Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getActor_2001OutgoingLinks(view));
		createActorActorCompartment_7001Children(getCompartment(
				view,
				edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createAgent_2002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getAgent_2002OutgoingLinks(view));
		createAgentAgentCompartment_7002Children(getCompartment(
				view,
				edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createPosition_2003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getPosition_2003OutgoingLinks(view));
		createPositionPositionCompartment_7003Children(getCompartment(
				view,
				edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createRole_2004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getRole_2004OutgoingLinks(view));
		createRoleRoleCompartment_7004Children(getCompartment(
				view,
				edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createGoal_2005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getGoal_2005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createSoftgoal_2006Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getSoftgoal_2006OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createTask_2007Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getTask_2007OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createResource_2008Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getResource_2008OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createGoal_3001Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getGoal_3001OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createSoftgoal_3002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getSoftgoal_3002OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createResource_3003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getResource_3003OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createTask_3004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getTask_3004OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createGoal_3005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getGoal_3005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createSoftgoal_3006Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getSoftgoal_3006OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createResource_3007Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getResource_3007OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createTask_3008Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getTask_3008OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createGoal_3009Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getGoal_3009OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createSoftgoal_3010Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getSoftgoal_3010OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createResource_3011Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getResource_3011OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createTask_3012Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getTask_3012OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createGoal_3013Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getGoal_3013OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createSoftgoal_3014Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getSoftgoal_3014OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createResource_3015Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getResource_3015OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createTask_3016Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors
				.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
						.getTask_3016OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createActorActorCompartment_7001Children(View view) {
		Collection childNodeDescriptors = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
				.getActorActorCompartment_7001SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(
					view,
					(edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor) it
							.next());
		}
	}

	/**
	 * @generated
	 */
	private void createAgentAgentCompartment_7002Children(View view) {
		Collection childNodeDescriptors = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
				.getAgentAgentCompartment_7002SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(
					view,
					(edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor) it
							.next());
		}
	}

	/**
	 * @generated
	 */
	private void createPositionPositionCompartment_7003Children(View view) {
		Collection childNodeDescriptors = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
				.getPositionPositionCompartment_7003SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(
					view,
					(edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor) it
							.next());
		}
	}

	/**
	 * @generated
	 */
	private void createRoleRoleCompartment_7004Children(View view) {
		Collection childNodeDescriptors = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
				.getRoleRoleCompartment_7004SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(
					view,
					(edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor) it
							.next());
		}
	}

	/**
	 * @generated
	 */
	private void createModel_1000Children(View view) {
		Collection childNodeDescriptors = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
				.getModel_1000SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(
					view,
					(edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor) it
							.next());
		}
	}

	/**
	 * @generated
	 */
	private void createNode(
			View parentView,
			edu.toronto.cs.openome_model.diagram.part.Openome_modelNodeDescriptor nodeDescriptor) {
		final String nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getType(nodeDescriptor.getVisualID());
		Node node = ViewService
				.createNode(
						parentView,
						nodeDescriptor.getModelElement(),
						nodeType,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		switch (nodeDescriptor.getVisualID()) {
		case edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID:
			createActor_2001Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID:
			createAgent_2002Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID:
			createPosition_2003Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID:
			createRole_2004Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID:
			createGoal_2005Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID:
			createSoftgoal_2006Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID:
			createTask_2007Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID:
			createResource_2008Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID:
			createGoal_3001Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID:
			createSoftgoal_3002Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID:
			createResource_3003Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID:
			createTask_3004Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID:
			createGoal_3005Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID:
			createSoftgoal_3006Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID:
			createResource_3007Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID:
			createTask_3008Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID:
			createGoal_3009Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID:
			createSoftgoal_3010Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID:
			createResource_3011Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID:
			createTask_3012Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID:
			createGoal_3013Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID:
			createSoftgoal_3014Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID:
			createResource_3015Children(node);
			return;
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID:
			createTask_3016Children(node);
			return;
		}
	}

	/**
	 * @generated
	 */
	private void createLinks(Diagram diagram) {
		for (boolean continueLinkCreation = true; continueLinkCreation;) {
			continueLinkCreation = false;
			Collection additionalDescriptors = new LinkedList();
			for (Iterator it = myLinkDescriptors.iterator(); it.hasNext();) {
				edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor nextLinkDescriptor = (edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor) it
						.next();
				if (!myDomain2NotationMap.containsKey(nextLinkDescriptor
						.getSource())
						|| !myDomain2NotationMap.containsKey(nextLinkDescriptor
								.getDestination())) {
					continue;
				}
				final String linkType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(nextLinkDescriptor.getVisualID());
				Edge edge = ViewService
						.getInstance()
						.createEdge(
								nextLinkDescriptor.getSemanticAdapter(),
								diagram,
								linkType,
								ViewUtil.APPEND,
								true,
								edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (edge != null) {
					edge.setSource((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getSource()));
					edge.setTarget((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getDestination()));
					it.remove();
					if (nextLinkDescriptor.getModelElement() != null) {
						myDomain2NotationMap.put(nextLinkDescriptor
								.getModelElement(), edge);
					}
					continueLinkCreation = true;
					switch (nextLinkDescriptor.getVisualID()) {
					case edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getDependency_4001OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getAndDecomposition_4002OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getOrDecomposition_4003OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getHelpContribution_4005OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getHurtContribution_4006OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getMakeContribution_4007OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getBreakContribution_4008OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getSomePlusContribution_4009OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getSomeMinusContribution_4010OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getUnknownContribution_4011OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getAndContribution_4012OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getOrContribution_4013OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getIsAAssociation_4014OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getCoversAssociation_4015OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getOccupiesAssociation_4016OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getIsPartOfAssociation_4017OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getPlaysAssociation_4018OutgoingLinks(edge));
						break;
					case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdater
										.getINSAssociation_4019OutgoingLinks(edge));
						break;
					}
				}
			}
			myLinkDescriptors.addAll(additionalDescriptors);
		}
	}

	/**
	 * @generated
	 */
	private Node getCompartment(View node, int visualID) {
		String type = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getType(visualID);
		for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (nextView instanceof Node && type.equals(nextView.getType())) {
				return (Node) nextView;
			}
		}
		return null;
	}

}
