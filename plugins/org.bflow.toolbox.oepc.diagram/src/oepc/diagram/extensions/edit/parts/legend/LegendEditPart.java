package oepc.diagram.extensions.edit.parts.legend;



import java.util.Iterator;
import java.util.List;

import oepc.diagram.edit.parts.ANDConnectorEditPart;
import oepc.diagram.edit.parts.BusinessObjectEditPart;
import oepc.diagram.edit.parts.DocumentEditPart;
import oepc.diagram.edit.parts.EventEditPart;
import oepc.diagram.edit.parts.ITSystemEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.edit.parts.OrganisationUnitEditPart;
import oepc.diagram.edit.parts.XORConnectorEditPart;
import oepc.diagram.edit.parts.ANDConnectorEditPart.ANDFigure;
import oepc.diagram.edit.parts.BusinessObjectEditPart.BusinessObjectFigure;
import oepc.diagram.edit.parts.DocumentEditPart.DocumentFigure;
import oepc.diagram.edit.parts.EventEditPart.EventFigure;
import oepc.diagram.edit.parts.ITSystemEditPart.ITSystemFigure;
import oepc.diagram.edit.parts.ORConnectorEditPart.ORFigure;
import oepc.diagram.edit.parts.OrganisationUnitEditPart.OrganisationUnitFigure;
import oepc.diagram.edit.parts.XORConnectorEditPart.XORFigure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;


/**
 * Defining an <code>EditPart</code> to display a non-changeable node
 * to inform about all elements.
 * @author Joerg Hartmann
 * @since 0.0.7
 */
public class LegendEditPart extends NoteEditPart{

	/*
	 * NOTE for creation of you're own edit part.
	 * in OepcViewProvider#getNodeViewClass(IAdaptable, View, String)
	 * 
	 * add where semanticHint != null and elementType == null
	 * 	case LegendEditPart.VISUAL_ID:
	 *		return getNodeViewClass(containerView, visualID);
	 *
	 * in OepcViewProvider#getNodeViewClass(View, int)
	 *	case LegendEditPart.VISUAL_ID:
	 *		return LegendViewFactory.class;
	 *
	 * -> each edit part must have an ViewFactory
	 * 
	 * 
	 * in OepcVisualIDRegistry#canCreateNode(View, int)
	 * 	if(nodeVisualID == LegendEditPart.VISUAL_ID){
	 *		return true;
	 *	}
	 *
	 * in OepcEditPartFactory#createEditPart(EditPart, Object)
	 *  case LegendEditPart.VISUAL_ID:
	 *		return new LegendEditPart(view);
	 *
	 *
	 * in Plugin.xml
	 * add to <extension point="org.eclipse.gmf.runtime.emf.type.core.elementTypeBindings">
	 * <elementType ref="org.bflow.toolbox.oepc.diagram.Legend_01"/>
	 * 
	 * add to <extension point="org.eclipse.gmf.runtime.emf.type.core.elementTypes">
	 *  <specializationType
     *           id="org.bflow.toolbox.oepc.diagram.Legend_01"     
     *           icon="icons/text.gif"
     *           name="%TextTool.Label"
     *           kind="org.eclipse.gmf.runtime.diagram.ui.util.INotationType"> 
     *        <specializes id="org.eclipse.gmf.runtime.emf.type.core.null"/>
     *        <param name="semanticHint" value="01"/> 
     *   </specializationType>
     *   
     *   -> ID must defined in the INotationType
	 */
	
	/**
	 * Visual ID.
	 */
	public static final int VISUAL_ID = 01;
	
	
	/**
	 * Creates this <code>EditPart</code> by delivering the view.
	 * @param view
	 */
	public LegendEditPart(View view) {
		super(view);
	}
	
	
	/**
	 * Creates the figure.
	 */
	protected NodeFigure createNodeFigure() {
		IMapMode mm = getMapMode();
		int insetSize = mm.DPtoLP(5);
		Insets insets = new Insets(insetSize, insetSize, insetSize, insetSize);
		NoteFigure noteFigure = new NoteFigure(mm.DPtoLP(110), mm.DPtoLP(54), 
				insets);
		noteFigure.setLayoutManager(new ToolbarLayout());
		noteFigure.setForegroundColor(ColorConstants.black);
		
		WrappingLabel description = new WrappingLabel();
		description.setText("Legende");
		description.setEnabled(false);
		description.setForegroundColor(ColorConstants.black);
		noteFigure.add(description);
		
		int height = 76;
		int width = 100;
		height += addEventFigure(noteFigure, width);
		height += addITSystemFigure(noteFigure, width);
		height += addOrganisationUnitFigure(noteFigure, width);
		height += addDocumentFigure(noteFigure, width);
		height += addBusinessObjectFigure(noteFigure, width);
		height += addConnectorFigure(noteFigure, width);

		noteFigure.setSize(width + 20, height);
		noteFigure.setMinimumSize(new Dimension(width + 20, height));
		noteFigure.setMaximumSize(new Dimension(width + 20, height));
		
		return noteFigure;
	}
	
	
	/**
	 * Adds an compartment to show the IT-System figure.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	public int addITSystemFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"IT-System", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		
		final int height = 41;
		ITSystemEditPart itEditPart = new ITSystemEditPart(null);
		ITSystemFigure figure = itEditPart.new ITSystemFigure();
		figure.removeAll();
		figure.setBackgroundColor(ColorConstants.white);
		figure.setBorder(new CompoundBorder(new LineBorder(null, getMapMode()
					.DPtoLP(1)), new CompoundBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(3),
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(3)),
					new CompoundBorder(new LineBorder(null, getMapMode()
							.DPtoLP(1)), new CompoundBorder(new MarginBorder(
							getMapMode().DPtoLP(-1), getMapMode().DPtoLP(3),
							getMapMode().DPtoLP(-1), getMapMode().DPtoLP(3)),
							new LineBorder(null, getMapMode().DPtoLP(1)))))));
		
		figure.getBorder().getPreferredSize(figure).width = 110;
		figure.getBorder().getPreferredSize(figure).height = height;
		rcf.setForegroundColor(ColorConstants.black);
		
		figure.setSize(width, height);
		figure.setPreferredSize(width, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(figure);
		legend.add(rcf);
		
		return height + 10;
	}
	
	
	/**
	 * Adds an compartment to show the Event figure.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	@SuppressWarnings("unchecked")
	public int addEventFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"Ereignis", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		rcf.setForegroundColor(ColorConstants.black);
		
		final int height = 41;
		EventEditPart eventEditPart = new EventEditPart(null);
		EventFigure figure = eventEditPart.new EventFigure();
		
		List<IFigure> childrenList = figure.getChildren();
		for(Iterator<IFigure> children = childrenList.iterator(); 
			children.hasNext();){
			children.next().setBackgroundColor(ColorConstants.white);
		}
		
		figure.setSize(width, height);
		figure.setPreferredSize(width, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(figure);
		legend.add(rcf);
		
		return height + 10;
	}
	
	
	/**
	 * Adds an compartment to show the Document figure.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	public int addDocumentFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"Dokument", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		rcf.setForegroundColor(ColorConstants.black);
		
		final int height = 41;
		DocumentEditPart documentEditPart = new DocumentEditPart(null);
		DocumentFigure figure = documentEditPart.new DocumentFigure();
		figure.setBackgroundColor(ColorConstants.white);
		
		figure.setSize(width, height);
		figure.setPreferredSize(width, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(figure);
		legend.add(rcf);
		
		return height + 10;
	}
	
	
	/**
	 * Adds an compartment to show the OrganisationUnit figure.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	public int addOrganisationUnitFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"Organisationseinheit", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		rcf.setForegroundColor(ColorConstants.black);
		
		final int height = 41;
		OrganisationUnitEditPart orgaUnitEditPart = 
			new OrganisationUnitEditPart(null);
		OrganisationUnitFigure figure = 
			orgaUnitEditPart.new OrganisationUnitFigure();
		figure.removeAll();
		figure.setBackgroundColor(ColorConstants.white);
		
		figure.setSize(width, height);
		figure.setPreferredSize(width, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(figure);
		legend.add(rcf);
		
		return height + 10;
	}
	
	
	/**
	 * Adds an compartment to show the Business Object figure.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	public int addBusinessObjectFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"Geschäftsobjekt", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		rcf.setForegroundColor(ColorConstants.black);
		
		final int height = 41;
		BusinessObjectEditPart businessObjectEditPart = 
			new BusinessObjectEditPart(null);
		BusinessObjectFigure figure = 
			businessObjectEditPart.new BusinessObjectFigure();
		figure.setBorder(new CompoundBorder(new LineBorder(null, getMapMode()
				.DPtoLP(1)), new CompoundBorder(new MarginBorder(
				getMapMode().DPtoLP(-1), getMapMode().DPtoLP(5),
				getMapMode().DPtoLP(-1), getMapMode().DPtoLP(5)),
				new LineBorder(null, getMapMode().DPtoLP(1)))));
		figure.setBackgroundColor(ColorConstants.white);
		
		figure.setSize(width, height);
		figure.setPreferredSize(width, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(figure);
		legend.add(rcf);
		
		return height + 10;
	}
	
	
	/**
	 * Adds an compartment to show all connector figures.
	 * @param legend legend figure
	 * @param width the width of the element
	 * @return the height of the element
	 */
	public int addConnectorFigure(IFigure legend, int width){
		ResizableCompartmentFigure rcf = new ResizableCompartmentFigure(
				"Und, Oder, X-Oder", getMapMode());
		rcf.getContentPane().setLayoutManager(new FlowLayout());
		rcf.setForegroundColor(ColorConstants.black);
		
		final int height = 26;
		ANDConnectorEditPart andConnectorEditPart = 
			new ANDConnectorEditPart(null);
		ANDFigure andFigure = andConnectorEditPart.new ANDFigure();
		Polyline aNDSymbol0 = new Polyline();
		aNDSymbol0.addPoint(new Point(getMapMode().DPtoLP(6), getMapMode()
				.DPtoLP(17)));
		aNDSymbol0.addPoint(new Point(getMapMode().DPtoLP(13), getMapMode()
				.DPtoLP(7)));
		aNDSymbol0.addPoint(new Point(getMapMode().DPtoLP(20), getMapMode()
				.DPtoLP(17)));
		andFigure.add(aNDSymbol0);
		
		ORConnectorEditPart orConnectorEditPart = new ORConnectorEditPart(null);
		ORFigure orFigure = orConnectorEditPart.new ORFigure();
		Polyline oRSymbol0 = new Polyline();
		oRSymbol0.addPoint(new Point(getMapMode().DPtoLP(6), getMapMode()
				.DPtoLP(9)));
		oRSymbol0.addPoint(new Point(getMapMode().DPtoLP(13), getMapMode()
				.DPtoLP(19)));
		oRSymbol0.addPoint(new Point(getMapMode().DPtoLP(20), getMapMode()
				.DPtoLP(9)));

		orFigure.add(oRSymbol0);
		
		XORConnectorEditPart xorConnectorEditPart = 
			new XORConnectorEditPart(null);
		XORFigure xorFigure = xorConnectorEditPart.new XORFigure();
		Polyline xORSymbol10 = new Polyline();
		xORSymbol10.addPoint(new Point(getMapMode().DPtoLP(8), getMapMode()
				.DPtoLP(18)));
		xORSymbol10.addPoint(new Point(getMapMode().DPtoLP(18),
				getMapMode().DPtoLP(8)));

		xorFigure.add(xORSymbol10);

		Polyline xORSymbol20 = new Polyline();
		xORSymbol20.addPoint(new Point(getMapMode().DPtoLP(8), getMapMode()
				.DPtoLP(8)));
		xORSymbol20.addPoint(new Point(getMapMode().DPtoLP(18),
				getMapMode().DPtoLP(18)));

		xorFigure.add(xORSymbol20);
		
		
		orFigure.setSize(26, height);
		orFigure.setPreferredSize(26, height);
		xorFigure.setSize(26, height);
		xorFigure.setPreferredSize(26, height);
		andFigure.setSize(26, height);
		andFigure.setPreferredSize(26, height);
		rcf.setSize(new Dimension(width, height));
		rcf.setPreferredSize(width, height);
		
		rcf.getContentPane().add(andFigure);
		rcf.getContentPane().add(orFigure);
		rcf.getContentPane().add(xorFigure);
		legend.add(rcf);
		
		return height + 10;
	}
}
