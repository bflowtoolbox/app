package org.bflow.toolbox.hive.actions;

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * Implements {@link IToolboxDiagramAction} to provide an action 
 * which calculates a diagram page size where all elements are fitting. 
 * Afterwards the diagram bounds will be set to the new size.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/08/13
 * @version 22/08/13
 */
public class PackPageAction implements IToolboxDiagramAction {
	
	/** The diagram edit part. */
	private DiagramEditPart iDiagramEditPart;

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.actions.IToolboxDiagramAction#setDiagramEditPart(org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart)
	 */
	@Override
	public void setDiagramEditPart(DiagramEditPart diagramEditPart) {
		iDiagramEditPart = diagramEditPart;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.actions.IToolboxDiagramAction#getDiagramEditPart()
	 */
	@Override
	public DiagramEditPart getDiagramEditPart() {
		return iDiagramEditPart;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.actions.IToolboxDiagramAction#doRun()
	 */
	@Override
	public void doRun() {
		if(iDiagramEditPart == null)
			return;
		
		final Dimension pageDimension = new Dimension();
		
		for(@SuppressWarnings("unchecked")
		Iterator<IGraphicalEditPart> it = iDiagramEditPart.getChildren().iterator(); it.hasNext(); ) {
			IGraphicalEditPart editPart = it.next();
			IFigure figure = editPart.getFigure();
			Rectangle rect = figure.getBounds();
			
			int maxX = rect.x + rect.width;
			int maxY = rect.y + rect.height;
			
			if(pageDimension.width < maxX)
				pageDimension.width = maxX;
			
			if(pageDimension.height < maxY)
				pageDimension.height = maxY;
		}
		
		final DiagramEditPart diagramPart = iDiagramEditPart;
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				iDiagramEditPart.getEditingDomain(), "My Command", null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {				
				DiagramStyle pageStyle = (DiagramStyle) diagramPart.getDiagramView().getStyle(
						NotationPackage.Literals.DIAGRAM_STYLE);
				
				pageStyle.setPageWidth(pageDimension.width);
				pageStyle.setPageHeight(pageDimension.height);
				
				return CommandResult.newOKCommandResult();
			}};
			
		try {
			command.execute(null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
