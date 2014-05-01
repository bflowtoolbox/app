package oepc.diagram.edit.parts;

import oepc.diagram.extensions.edit.parts.legend.LegendEditPart;
import oepc.diagram.part.OepcVisualIDRegistry;

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
public class OepcEditPartFactory implements EditPartFactory {

	/**
	 * @generated NOT
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (OepcVisualIDRegistry.getVisualID(view)) {
			
			case LegendEditPart.VISUAL_ID:
				return new LegendEditPart(view);
			case OEPCEditPart.VISUAL_ID:
				return new OEPCEditPart(view);

			case EventEditPart.VISUAL_ID:
				return new EventEditPart(view);

			case EventNameEditPart.VISUAL_ID:
				return new EventNameEditPart(view);

			case ITSystemEditPart.VISUAL_ID:
				return new ITSystemEditPart(view);

			case ITSystemNameEditPart.VISUAL_ID:
				return new ITSystemNameEditPart(view);

			case OrganisationUnitEditPart.VISUAL_ID:
				return new OrganisationUnitEditPart(view);

			case OrganisationUnitNameEditPart.VISUAL_ID:
				return new OrganisationUnitNameEditPart(view);

			case XORConnectorEditPart.VISUAL_ID:
				return new XORConnectorEditPart(view);

			case BusinessObjectEditPart.VISUAL_ID:
				return new BusinessObjectEditPart(view);

			case BusinessObjectNameEditPart.VISUAL_ID:
				return new BusinessObjectNameEditPart(view);

			case ANDConnectorEditPart.VISUAL_ID:
				return new ANDConnectorEditPart(view);

			case ORConnectorEditPart.VISUAL_ID:
				return new ORConnectorEditPart(view);

			case DocumentEditPart.VISUAL_ID:
				return new DocumentEditPart(view);

			case DocumentNameEditPart.VISUAL_ID:
				return new DocumentNameEditPart(view);

			case BusinessAttributeEditPart.VISUAL_ID:
				return new BusinessAttributeEditPart(view);

			case BusinessMethodEditPart.VISUAL_ID:
				return new BusinessMethodEditPart(view);

			case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
				return new BusinessObjectBusinessObjectAttributeCompartmentEditPart(
						view);

			case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
				return new BusinessObjectBusinessObjectMethodCompartmentEditPart(
						view);

			case ControlFlowEdgeEditPart.VISUAL_ID:
				return new ControlFlowEdgeEditPart(view);

			case InformationEdgeEditPart.VISUAL_ID:
				return new InformationEdgeEditPart(view);
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
