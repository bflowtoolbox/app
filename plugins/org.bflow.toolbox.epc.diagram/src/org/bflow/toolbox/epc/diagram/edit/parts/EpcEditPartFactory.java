package org.bflow.toolbox.epc.diagram.edit.parts;

import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
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
public class EpcEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (EpcVisualIDRegistry.getVisualID(view)) {

			case EpcEditPart.VISUAL_ID:
				return new EpcEditPart(view);

			case OREditPart.VISUAL_ID:
				return new OREditPart(view);

			case ParticipantEditPart.VISUAL_ID:
				return new ParticipantEditPart(view);

			case ParticipantNameEditPart.VISUAL_ID:
				return new ParticipantNameEditPart(view);

			case ANDEditPart.VISUAL_ID:
				return new ANDEditPart(view);

			case ApplicationEditPart.VISUAL_ID:
				return new ApplicationEditPart(view);

			case ApplicationNameEditPart.VISUAL_ID:
				return new ApplicationNameEditPart(view);

			case ProcessInterfaceEditPart.VISUAL_ID:
				return new ProcessInterfaceEditPart(view);

			case ProcessInterfaceNameEditPart.VISUAL_ID:
				return new ProcessInterfaceNameEditPart(view);

			case EventEditPart.VISUAL_ID:
				return new EventEditPart(view);

			case EventNameEditPart.VISUAL_ID:
				return new EventNameEditPart(view);

			case FunctionEditPart.VISUAL_ID:
				return new FunctionEditPart(view);

			case FunctionNameEditPart.VISUAL_ID:
				return new FunctionNameEditPart(view);

			case XOREditPart.VISUAL_ID:
				return new XOREditPart(view);

			case GroupEditPart.VISUAL_ID:
				return new GroupEditPart(view);

			case GroupNameEditPart.VISUAL_ID:
				return new GroupNameEditPart(view);

			case ClusterEditPart.VISUAL_ID:
				return new ClusterEditPart(view);

			case ClusterNameEditPart.VISUAL_ID:
				return new ClusterNameEditPart(view);

			case ExternalPersonEditPart.VISUAL_ID:
				return new ExternalPersonEditPart(view);

			case ExternalPersonNameEditPart.VISUAL_ID:
				return new ExternalPersonNameEditPart(view);

			case InternalPersonEditPart.VISUAL_ID:
				return new InternalPersonEditPart(view);

			case InternalPersonNameEditPart.VISUAL_ID:
				return new InternalPersonNameEditPart(view);

			case PositionEditPart.VISUAL_ID:
				return new PositionEditPart(view);

			case PositionNameEditPart.VISUAL_ID:
				return new PositionNameEditPart(view);

			case LocationEditPart.VISUAL_ID:
				return new LocationEditPart(view);

			case LocationNameEditPart.VISUAL_ID:
				return new LocationNameEditPart(view);

			case PersonTypeEditPart.VISUAL_ID:
				return new PersonTypeEditPart(view);

			case PersonTypeNameEditPart.VISUAL_ID:
				return new PersonTypeNameEditPart(view);

			case TechnicalTermEditPart.VISUAL_ID:
				return new TechnicalTermEditPart(view);

			case TechnicalTermNameEditPart.VISUAL_ID:
				return new TechnicalTermNameEditPart(view);

			case CardFileEditPart.VISUAL_ID:
				return new CardFileEditPart(view);

			case CardFileNameEditPart.VISUAL_ID:
				return new CardFileNameEditPart(view);

			case DocumentEditPart.VISUAL_ID:
				return new DocumentEditPart(view);

			case DocumentNameEditPart.VISUAL_ID:
				return new DocumentNameEditPart(view);

			case FileEditPart.VISUAL_ID:
				return new FileEditPart(view);

			case FileNameEditPart.VISUAL_ID:
				return new FileNameEditPart(view);

			case ObjectiveEditPart.VISUAL_ID:
				return new ObjectiveEditPart(view);

			case ObjectiveNameEditPart.VISUAL_ID:
				return new ObjectiveNameEditPart(view);

			case ProductEditPart.VISUAL_ID:
				return new ProductEditPart(view);

			case ProductNameEditPart.VISUAL_ID:
				return new ProductNameEditPart(view);

			case ArcEditPart.VISUAL_ID:
				return new ArcEditPart(view);

			case RelationEditPart.VISUAL_ID:
				return new RelationEditPart(view);

			case InformationArcEditPart.VISUAL_ID:
				return new InformationArcEditPart(view);
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
