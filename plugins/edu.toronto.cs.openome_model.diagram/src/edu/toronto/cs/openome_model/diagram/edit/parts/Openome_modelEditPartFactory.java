package edu.toronto.cs.openome_model.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class Openome_modelEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
					.getVisualID(view)) {

			case edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ActorNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ActorNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AgentNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AgentNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.RoleNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.RoleNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningComEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningComEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningComEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningComEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningComEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningComEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceNameEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceNameEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningComEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningComEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalName2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskName2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom2EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom2EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalName3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskName3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom3EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom3EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalName4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskName4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom4EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom4EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalName5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskName5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom5EditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom5EditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionContributionTypeEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionContributionTypeEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationLabelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationLabelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationLabelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationLabelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationLabelEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart(
						view);

			case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationLabelEditPart.VISUAL_ID:
				return new edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationLabelEditPart(
						view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapOn()
					&& getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
