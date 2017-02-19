package customsrc;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;

public class SetEvaluationLabelAction extends AbstractActionHandler {
	protected String ID = ""; //$NON-NLS-1$
	protected EvaluationLabel evalField = null;
	protected String evalLabel = "";

	protected SetEvaluationLabelAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
		// TODO Auto-generated constructor stub
	}
	
	public String getID() {
		return ID;
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		
		if(selection == null) {
			return;
		}
		
		for(Object o : selection.toArray()) {
			IGraphicalEditPart part = (IGraphicalEditPart)o;
			EObject object = part.resolveSemanticElement();
			DiagramCommandStack dcs = part.getDiagramEditDomain().getDiagramCommandStack();
			
			applyEvalLabel(object, progressMonitor, dcs);
		}
	}
	
	public void applyEvalLabel(final EObject object, IProgressMonitor progressMonitor, DiagramCommandStack dcs) {
		MyCommand applyLabel = new MyCommand(object) {
			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				if (object instanceof Intention) {
					((Intention) object).setQualitativeReasoningCombinedLabel(evalField);
				}
				return CommandResult.newOKCommandResult();
			}
		};
		
		ICommandProxy command = new ICommandProxy(applyLabel);
		dcs.execute(command, progressMonitor);
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
	}
	
	private abstract class MyCommand extends AbstractTransactionalCommand {
		public MyCommand(EObject elt) {
			super((TransactionalEditingDomain) AdapterFactoryEditingDomain.
					getEditingDomainFor(elt),
					evalLabel,
					getWorkspaceFiles(elt));
		}
	}
}
