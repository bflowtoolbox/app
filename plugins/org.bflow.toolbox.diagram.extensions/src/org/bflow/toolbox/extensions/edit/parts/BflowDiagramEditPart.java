package org.bflow.toolbox.extensions.edit.parts;

import java.util.Iterator;
import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.BlackWhiteColorSchema;
import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.colorschemes.OriginalColorSchema;
import org.bflow.toolbox.extensions.colorschemes.PresentationColorSchema;
import org.bflow.toolbox.extensions.figures.DiagramFormLayer;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Let your <code>DiagramEditPart</code> implement this, to activate the style
 * actions. Each <code>BflowDiagramEditPart</code> allows you to set specific
 * <code>IGlobalColorSchema</code> to switch the look of the elements.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 01/11/13
 */
public abstract class BflowDiagramEditPart extends DiagramEditPart {

	/**
	 * The current used <code>IGlobalColorSchema</code>.
	 */
	private IGlobalColorSchema colorSchema;

	/**
	 * Contains all usable <code>IGlobalColorSchema</code>s.
	 */
	private Vector<IGlobalColorSchema> installedSchemas;

	/**
	 * The figure for this edit part.
	 */
	private DiagramFormLayer figure;

	/**
	 * Create me.
	 * 
	 * @param diagramView
	 */
	public BflowDiagramEditPart(View diagramView) {
		super(diagramView);

		installedSchemas = new Vector<IGlobalColorSchema>();
		installSchemas();
	}

	/**
	 * Installs all additional <code>IGlobalColorSchema</code>. The default
	 * schema is an <code>OriginialColorSchema</code>. If subclasses must
	 * override this, to provides other schemas.
	 */
	private void installSchemas() {
		installedSchemas.add(new OriginalColorSchema());
		installedSchemas.add(new BlackWhiteColorSchema());
		installedSchemas.add(new PresentationColorSchema());
	}

	/**
	 * Gets an <code>IGlobalColorSchema</code> by it's id or an
	 * <code>OriginialColorSchema</code> if no installed schema matches the
	 * delivered id.
	 * 
	 * @param schemaId
	 * @return returns not null
	 */
	public IGlobalColorSchema getSchema(String schemaId) {
		IGlobalColorSchema schema = new OriginalColorSchema();
		Iterator<IGlobalColorSchema> schemeIterator = installedSchemas.iterator();
		while(schemeIterator.hasNext()) {
			IGlobalColorSchema s = schemeIterator.next();
			if (s.toString().equals(schemaId))
				return s;
		}
		return schema;
	}

	/**
	 * Refreshes all diagram visuals.
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshColorSchema();
		refreshPageSize();
	}
	
	/**
	 * Refreshes the color schema by loading the edit part.
	 */
	protected void refreshColorSchema() {
		DiagramStyle descriptionStyle = (DiagramStyle) getDiagramView()
				.getStyle(NotationPackage.Literals.DIAGRAM_STYLE);

		if (descriptionStyle != null) {
			String description = descriptionStyle.getDescription();
			setColorSchema(getSchema(description));
		} else {
			setColorSchema(new OriginalColorSchema());
		}
	}
	

	/**
	 * Loads the stored size of the page and sets it.
	 */
	protected void refreshPageSize() {
		DiagramStyle pageStyle = (DiagramStyle) getDiagramView().getStyle(
				NotationPackage.Literals.DIAGRAM_STYLE);

		if(pageStyle != null && figure != null) {			
			figure.getFormHelper().setMinimumSize(
					pageStyle.getPageX(),
					pageStyle.getPageY(),
					pageStyle.getPageWidth(), 
					pageStyle.getPageHeight());
		} else {
			figure.getFormHelper().setMinimumSize(0, 0, 500, 500);
		}
	}
	
	
	/**
	 * Returns the map mode.
	 * @return
	 */
	public IMapMode getMapMode() {
		return super.getMapMode();
	}

	/**
	 * Returns the <code>EditDomain</code>.
	 * 
	 * @return
	 */
	public EditDomain getEditDomain() {
		return super.getEditDomain();
	}

	/**
	 * Returns the color schema.
	 * 
	 * @return
	 */
	public IGlobalColorSchema getColorSchema() {
		return colorSchema;
	}

	/**
	 * Sets the color schema.
	 * 
	 * @param colorSchema
	 */
	public void setColorSchema(IGlobalColorSchema colorSchema) {
		this.colorSchema = colorSchema;
	}

	/**
	 * Returns the current edited <code>BflowDiagramEditPart</code> which is
	 * shown in the open editor or null, if no <code>BflowDiagramEditPart</code>
	 * is edited.
	 * 
	 * @return
	 */
	public static BflowDiagramEditPart getCurrentViewer() {
		BflowDiagramEditPart diagramEditPart = null;

		try {
			IEditorPart editorPart = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

			if (editorPart != null) {
				if (((IDiagramWorkbenchPart) editorPart).getDiagramEditPart() instanceof BflowDiagramEditPart) {
					diagramEditPart = (BflowDiagramEditPart) ((IDiagramWorkbenchPart) editorPart).getDiagramEditPart();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return diagramEditPart;
	}

	/**
	 * Applies the in the color schema defined colors to the delivered
	 * <code>ShapeStyle</code>.
	 * 
	 * @param type
	 * @param style
	 */
	public static void apply(Class<?> type, ShapeStyle style) {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if (diagramEditPart != null) {
			style.setFillColor(FigureUtilities.colorToInteger(diagramEditPart.getColorSchema().getBackground(type)));
			style.setLineColor(FigureUtilities.colorToInteger(diagramEditPart.getColorSchema().getForeground(type)));
		} else {
			style.setFillColor(ColorConstants.white.hashCode());
			style.setLineColor(ColorConstants.black.hashCode());
		}
	}

	
	/**
	 * Creates the figure for the diagram.
	 * @return 
	 */
	protected IFigure createFigure() {

		figure = new DiagramFormLayer() {

			public boolean containsPoint(int x, int y) {
				return getBounds().contains(x, y);
			}

			public IFigure findFigureAt(int x, int y, TreeSearch search) {
				if (!isEnabled())
					return null;
				if (!containsPoint(x, y))
					return null;
				if (search.prune(this))
					return null;
				IFigure child = findDescendantAtExcluding(x, y, search);
				if (child != null)
					return child;
				if (search.accept(this))
					return this;
				return null;
			}

			public void validate() {
				super.validate();
				updatePageBreaksLocation();
			}
		};
		figure.setLayoutManager(new FreeFormLayoutEx());
		figure.addLayoutListener(LayoutAnimator.getDefault());
		
		return figure;
	}
	
	public DiagramFormLayer getDiagramFormLayer(){
		return figure;
	}
}
