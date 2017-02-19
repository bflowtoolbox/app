package customsrc;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import edu.toronto.cs.openome_model.impl.IntentionImpl;

public class ReplaceElement
{
	public static void replace(GraphicalEditPart part, GraphicalEditPart container, IElementType type, IProgressMonitor monitor, IAdaptable info)
		throws ExecutionException
	{
		TransactionalEditingDomain domain = part.getEditingDomain();
		
		View originalView = part.getNotationView();
		EObject originalImpl = originalView.getElement();
		
		// Create a new element
		
		CreateElementCommand commandElement = new CreateElementCommand(
			new CreateElementRequest(domain, originalImpl.eContainer(), type)
		);
		
		if(commandElement.canExecute()) {
			commandElement.execute(monitor, info);
		} else {
			System.err.println("commandElement problem!");
		}

		// Copy the old element's meta data and links
		
		EObject element = commandElement.getNewElement();
		
		TransferMetaCommand commandMeta = new TransferMetaCommand(domain, element, originalImpl);
		
		if(commandMeta.canExecute()) {
			commandMeta.execute(monitor, info);
		} else {
			System.err.println("commandMeta problem!");
		}

		// Create a view for the new element
		
		ViewDescriptor viewDescriptor = new ViewDescriptor(
			new EObjectAdapter(element),
			Node.class,
			((IHintedType)type).getSemanticHint(), true,
			container.getDiagramPreferencesHint()
		);
		
		Command commandView = container.getCommand(
			new CreateViewRequest(viewDescriptor)
		);
		
		if(commandView.canExecute()) {
			commandView.execute();
		} else {
			System.err.println("commandView problem!");
		}

		// Move the old view's edges into the new one
		
		View view = (View)viewDescriptor.getAdapter(View.class);
		
		TransferEdgesCommand commandEdges = new TransferEdgesCommand(domain, view, originalView);
		
		if(commandEdges.canExecute()) {
			commandEdges.execute(monitor, info);
		} else {
			System.err.println("commandEdges problem!");
		}
		
		// Keep the old element's canvas position		
		
		IFigure fig = part.getContentPane();
		Rectangle coords = fig.getBounds();
		
		SetBoundsCommand commandPosition = new SetBoundsCommand(
			domain,
			"",
			new EObjectAdapter(view),
			new Point(coords.x, coords.y)
		);
		
		if(commandPosition.canExecute()) {
			commandPosition.execute(monitor, info);
		} else {
			System.err.println("commandPosition problem!");
		}
		
		// Delete the old element
		
		DestroyElementCommand commandDelete = new DestroyElementCommand(
			new DestroyElementRequest(domain, originalImpl, false)
		);
		
		if(commandDelete.canExecute()) {
			commandDelete.execute(monitor, info);
		} else {
			System.err.println("commandDelete problem!");
		}
		
		// Delete the old element's view
		
		DeleteCommand commandDeleteView = new DeleteCommand(domain, originalView);
		
		if(commandDeleteView.canExecute()) {
			commandDeleteView.execute(monitor, info);
		} else {
			System.err.println("commandDeleteView problem!");
		}
	}
	
	/*
	 * Command to copy an element's links, labels, and name to another one.
	 */
	private static class TransferMetaCommand extends AbstractTransactionalCommand
	{
		private IntentionImpl dst;
		private IntentionImpl src;
		
		public TransferMetaCommand(TransactionalEditingDomain domain, EObject newElement, EObject oldElement)
		{
	        super(domain, "", null);
	        
	        this.dst = (IntentionImpl)newElement;
	        this.src = (IntentionImpl)oldElement;
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
		    throws ExecutionException
		{  	
			dst.setName(src.getName());
	        
	    	dst.setQualitativeReasoningCombinedLabel(src.getQualitativeReasoningCombinedLabel());
	    	dst.setQualitativeReasoningDenialLabel(src.getQualitativeReasoningDenialLabel());
	    	dst.setQualitativeReasoningSatisfiedLabel(src.getQualitativeReasoningSatisfiedLabel());
	    	dst.setQuantitativeReasoningCombinedLabel(src.getQuantitativeReasoningCombinedLabel());
	    	dst.setQuantitativeReasoningDeniedLabel(src.getQuantitativeReasoningDeniedLabel());
	    	dst.setQuantitativeReasoningSatisfiedLabel(src.getQuantitativeReasoningSatisfiedLabel());
	    	
	    	dst.getContributesFrom().addAll(src.getContributesFrom());
	    	dst.getContributesTo().addAll(src.getContributesTo());
	    	dst.getDecompositions().addAll(src.getDecompositions());
	    	dst.getDecompositionsFrom().addAll(src.getDecompositionsFrom());
	    	dst.getDecompositionsTo().addAll(src.getDecompositionsTo());
	    	dst.getDependencyFrom().addAll(src.getDependencyFrom());
	    	dst.getDependencyTo().addAll(src.getDependencyTo());
			
			return CommandResult.newOKCommandResult();
		}
	}
	
	/*
	 * Command to copy a view's edges to another one.
	 */
	private static class TransferEdgesCommand extends AbstractTransactionalCommand
	{
		private View dst;
		private View src;
		
		public TransferEdgesCommand(TransactionalEditingDomain domain, View newView, View oldView)
		{
	        super(domain, "", null);
	        
	        this.dst = newView;
	        this.src = oldView;
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
	    	throws ExecutionException
		{    
			dst.getSourceEdges().addAll(src.getSourceEdges());
			dst.getTargetEdges().addAll(src.getTargetEdges());
			
			return CommandResult.newOKCommandResult();
		}
	}
}
