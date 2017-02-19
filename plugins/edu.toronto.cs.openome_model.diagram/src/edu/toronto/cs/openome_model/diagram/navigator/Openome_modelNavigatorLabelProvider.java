package edu.toronto.cs.openome_model.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
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

/**
 * @generated
 */
public class Openome_modelNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
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
		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem
				&& !isOwnView(((edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) element)
						.getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup group = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) element;
			return edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem navigatorItem = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) element;
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
		switch (edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getVisualID(view)) {
		case edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http:///edu/toronto/cs/openome_model.ecore?Model", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Model_1000); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Actor", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Agent", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Position", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Role", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Goal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_2005); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Softgoal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_2006); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Task", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_2007); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http:///edu/toronto/cs/openome_model.ecore?Resource", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_2008); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Goal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3001); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Softgoal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3002); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Resource", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3003); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Task", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3004); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Goal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3005); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Softgoal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3006); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Resource", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3007); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Task", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3008); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Goal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Softgoal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Resource", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Task", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Goal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3013); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Softgoal", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3014); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Resource", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3015); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http:///edu/toronto/cs/openome_model.ecore?Task", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3016); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?Dependency", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?AndDecomposition", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndDecomposition_4002); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?OrDecomposition", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrDecomposition_4003); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?HelpContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HelpContribution_4005); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?HurtContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HurtContribution_4006); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?MakeContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.MakeContribution_4007); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?BreakContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.BreakContribution_4008); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?SomePlusContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomePlusContribution_4009); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?SomeMinusContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomeMinusContribution_4010); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?UnknownContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.UnknownContribution_4011); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?AndContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndContribution_4012); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?OrContribution", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrContribution_4013); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?IsAAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?CoversAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?OccupiesAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?IsPartOfAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?PlaysAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018); //$NON-NLS-1$
		case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http:///edu/toronto/cs/openome_model.ecore?INSAssociation", edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null
				&& elementType != null
				&& edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes
						.isKnownElementType(elementType)) {
			image = edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes
					.getImage(elementType);
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
		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup group = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem navigatorItem = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) element;
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
		switch (edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getVisualID(view)) {
		case edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID:
			return getActor_2001Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID:
			return getAgent_2002Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID:
			return getPosition_2003Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID:
			return getRole_2004Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID:
			return getGoal_2005Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID:
			return getSoftgoal_2006Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID:
			return getTask_2007Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID:
			return getResource_2008Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID:
			return getGoal_3001Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID:
			return getSoftgoal_3002Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID:
			return getResource_3003Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID:
			return getTask_3004Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID:
			return getGoal_3005Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID:
			return getSoftgoal_3006Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID:
			return getResource_3007Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID:
			return getTask_3008Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID:
			return getGoal_3009Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID:
			return getSoftgoal_3010Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID:
			return getResource_3011Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID:
			return getTask_3012Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID:
			return getGoal_3013Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID:
			return getSoftgoal_3014Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID:
			return getResource_3015Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID:
			return getTask_3016Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID:
			return getDependency_4001Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID:
			return getAndDecomposition_4002Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID:
			return getOrDecomposition_4003Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID:
			return getHelpContribution_4005Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID:
			return getHurtContribution_4006Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID:
			return getMakeContribution_4007Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID:
			return getBreakContribution_4008Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID:
			return getSomePlusContribution_4009Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID:
			return getSomeMinusContribution_4010Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID:
			return getUnknownContribution_4011Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID:
			return getAndContribution_4012Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID:
			return getOrContribution_4013Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID:
			return getIsAAssociation_4014Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID:
			return getCoversAssociation_4015Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID:
			return getOccupiesAssociation_4016Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID:
			return getIsPartOfAssociation_4017Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID:
			return getPlaysAssociation_4018Text(view);
		case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID:
			return getINSAssociation_4019Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getModel_1000Text(View view) {
		edu.toronto.cs.openome_model.Model domainModelElement = (edu.toronto.cs.openome_model.Model) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActor_2001Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAgent_2002Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPosition_2003Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getRole_2004Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGoal_2005Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_2005,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5021); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSoftgoal_2006Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_2006,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5022); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTask_2007Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_2007,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5023); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getResource_2008Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_2008,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5024); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGoal_3001Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3001,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSoftgoal_3002Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3002,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getResource_3003Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3003,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTask_3004Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3004,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGoal_3005Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3005,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSoftgoal_3006Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3006,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getResource_3007Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3007,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTask_3008Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3008,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGoal_3009Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSoftgoal_3010Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getResource_3011Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTask_3012Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5014); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGoal_3013Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3013,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSoftgoal_3014Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3014,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getResource_3015Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3015,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTask_3016Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3016,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDependency_4001Text(View view) {
		edu.toronto.cs.openome_model.Dependency domainModelElement = (edu.toronto.cs.openome_model.Dependency) view
				.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getTrust());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance()
					.logError(
							"No domain element for view with visualID = " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAndDecomposition_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getOrDecomposition_4003Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getHelpContribution_4005Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HelpContribution_4005,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getHurtContribution_4006Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HurtContribution_4006,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getMakeContribution_4007Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.MakeContribution_4007,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBreakContribution_4008Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.BreakContribution_4008,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSomePlusContribution_4009Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomePlusContribution_4009,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSomeMinusContribution_4010Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomeMinusContribution_4010,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownContribution_4011Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.UnknownContribution_4011,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAndContribution_4012Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndContribution_4012,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getOrContribution_4013Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrContribution_4013,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionContributionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getIsAAssociation_4014Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCoversAssociation_4015Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6014); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getOccupiesAssociation_4016Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getIsPartOfAssociation_4017Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPlaysAssociation_4018Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getINSAssociation_4019Text(View view) {
		IParser parser = edu.toronto.cs.openome_model.diagram.providers.Openome_modelParserProvider
				.getParser(
						edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019,
						view.getElement() != null ? view.getElement() : view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
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
		return edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.MODEL_ID
				.equals(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getModelID(view));
	}

}
