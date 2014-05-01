package org.bflow.toolbox.vc.diagram.edit.policies;

import java.util.Iterator;

import org.bflow.toolbox.vc.diagram.edit.commands.MySetBoundsCommand;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;



public abstract class MyXYLayoutEditPolicy extends XYLayoutEditPolicy{


	protected Command getCreateCommand(CreateRequest request) {
		CreateViewRequest req = (CreateViewRequest) request;
        
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
            .getEditingDomain();

       
		CompositeTransactionalCommand cc = new CompositeTransactionalCommand(
            editingDomain, DiagramUIMessages.AddCommand_Label);
        Iterator iter = req.getViewDescriptors().iterator();

		final Rectangle BOUNDS = (Rectangle) getConstraintFor(request);

		while (iter.hasNext()) {
			CreateViewRequest.ViewDescriptor viewDescriptor = (CreateViewRequest.ViewDescriptor)iter.next(); 
			Rectangle rect = getBoundsOffest(req, BOUNDS,viewDescriptor);
			
			cc.compose(new MySetBoundsCommand(editingDomain, 
				DiagramUIMessages.SetLocationCommand_Label_Resize,
				viewDescriptor,
				rect));
		}
		
		if( cc.reduce() == null )
			return null;

		return chainGuideAttachmentCommands( request,
			new ICommandProxy(cc.reduce()));
	}    
}
