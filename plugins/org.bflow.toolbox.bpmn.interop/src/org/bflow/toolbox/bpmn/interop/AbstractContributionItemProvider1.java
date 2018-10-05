package org.bflow.toolbox.bpmn.interop;

import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class AbstractContributionItemProvider1 extends AbstractContributionItemProvider {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#createAction(java.lang.String, org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
	 */
	@Override
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {

		if (actionId.equals("org.bflow.toolbox.bpmn.interop.partAction2"))
			return new FooAction(partDescriptor);
		
		return super.createAction(actionId, partDescriptor);
	}	
	
	class FooAction extends DiagramAction implements IObjectActionDelegate {

		public static final String Id = "myAction";
		
		public FooAction(IWorkbenchPartDescriptor partDescriptor) {
			super(partDescriptor.getPartPage());
			setId(Id);
			setText("Foo");
			// setImageDescriptor(newImage);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
		 */
		@Override
		public void run(IAction action) {
			// TODO Auto-generated method stub
		}
		
		@Override
		protected boolean calculateEnabled() {
			return true;
		}
	

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
		 */
		@Override
		public void selectionChanged(IAction action, ISelection selection) {
			// TODO Auto-generated method stub
			
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
		 */
		@Override
		public void setActivePart(IAction action, IWorkbenchPart targetPart) {
			// TODO Auto-generated method stub
			
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
		 */
		@Override
		protected Request createTargetRequest() {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
		 */
		@Override
		protected boolean isSelectionListener() {
			return true;
		}
		
	}
}
