/*
 * 
 */
package vcchart.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class VcEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (VcVisualIDRegistry.getVisualID(view)) {

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case ProductEditPart.VISUAL_ID:
				return new ProductEditPart(view);

			case ProductNameEditPart.VISUAL_ID:
				return new ProductNameEditPart(view);

			case ObjectiveEditPart.VISUAL_ID:
				return new ObjectiveEditPart(view);

			case ObjectiveNameEditPart.VISUAL_ID:
				return new ObjectiveNameEditPart(view);

			case Activity1EditPart.VISUAL_ID:
				return new Activity1EditPart(view);

			case Activity1NameEditPart.VISUAL_ID:
				return new Activity1NameEditPart(view);

			case Activity2EditPart.VISUAL_ID:
				return new Activity2EditPart(view);

			case Activity2NameEditPart.VISUAL_ID:
				return new Activity2NameEditPart(view);

			case ClusterEditPart.VISUAL_ID:
				return new ClusterEditPart(view);

			case ClusterNameEditPart.VISUAL_ID:
				return new ClusterNameEditPart(view);

			case TechnicalTermEditPart.VISUAL_ID:
				return new TechnicalTermEditPart(view);

			case TechnicalTermNameEditPart.VISUAL_ID:
				return new TechnicalTermNameEditPart(view);

			case ParticipantEditPart.VISUAL_ID:
				return new ParticipantEditPart(view);

			case ParticipantNameEditPart.VISUAL_ID:
				return new ParticipantNameEditPart(view);

			case ApplicationEditPart.VISUAL_ID:
				return new ApplicationEditPart(view);

			case ApplicationNameEditPart.VISUAL_ID:
				return new ApplicationNameEditPart(view);

			case DocumentEditPart.VISUAL_ID:
				return new DocumentEditPart(view);

			case DocumentNameEditPart.VISUAL_ID:
				return new DocumentNameEditPart(view);

			case Relation1EditPart.VISUAL_ID:
				return new Relation1EditPart(view);

			case Relation2EditPart.VISUAL_ID:
				return new Relation2EditPart(view);

			case Relation3EditPart.VISUAL_ID:
				return new Relation3EditPart(view);

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
		return CellEditorLocatorAccess.INSTANCE
				.getTextCellEditorLocator(source);
	}

}
