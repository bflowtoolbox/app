/*
 * 
 */
package orgchart.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class OrgcEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (OrgcVisualIDRegistry.getVisualID(view)) {

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case PositionEditPart.VISUAL_ID:
				return new PositionEditPart(view);

			case PositionNameEditPart.VISUAL_ID:
				return new PositionNameEditPart(view);

			case InternalPersonEditPart.VISUAL_ID:
				return new InternalPersonEditPart(view);

			case InternalPersonNameEditPart.VISUAL_ID:
				return new InternalPersonNameEditPart(view);

			case ExternalPersonEditPart.VISUAL_ID:
				return new ExternalPersonEditPart(view);

			case ExternalPersonNameEditPart.VISUAL_ID:
				return new ExternalPersonNameEditPart(view);

			case GroupEditPart.VISUAL_ID:
				return new GroupEditPart(view);

			case GroupNameEditPart.VISUAL_ID:
				return new GroupNameEditPart(view);

			case ParticipantEditPart.VISUAL_ID:
				return new ParticipantEditPart(view);

			case ParticipantNameEditPart.VISUAL_ID:
				return new ParticipantNameEditPart(view);

			case PersonTypeEditPart.VISUAL_ID:
				return new PersonTypeEditPart(view);

			case PersonTypeNameEditPart.VISUAL_ID:
				return new PersonTypeNameEditPart(view);

			case LocationEditPart.VISUAL_ID:
				return new LocationEditPart(view);

			case LocationNameEditPart.VISUAL_ID:
				return new LocationNameEditPart(view);

			case Relation1EditPart.VISUAL_ID:
				return new Relation1EditPart(view);

			case Relation2EditPart.VISUAL_ID:
				return new Relation2EditPart(view);

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
