package vcchart.diagram.actions;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for removing a linked EPC.
 * @author Markus Schnädelbach
 *
 */
public class RemoveSubdiagramAction implements IObjectActionDelegate {
	private Activity1 a1;
	private Activity2 a2;
	
	/** The log instance for this class */
	private static final Log logger = LogFactory.getLog(RemoveSubdiagramAction.class);

	@Override
	public void run(IAction action) {
		if (a1 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a1.eContainer()), false, Collections.EMPTY_MAP);
			try {
				t.start();
				a1.setSubdiagram(null);
				t.commit();
			} catch (RollbackException e) {
				logger.error("Subdiagramlink could not removed from Activity2.", e);
			} catch (InterruptedException e) {
				logger.error("The current thread is interuppted, therefore no transaction can be started.", e);
			}

		} else if (a2 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a2.eContainer()), false, Collections.EMPTY_MAP);
			try {
				t.start();
				a2.setSubdiagram(null);
				t.commit();
			} catch (RollbackException e) {
				logger.error("Subdiagramlink could not removed from Activity2.", e);
			} catch (InterruptedException e) {
				logger.error("The current thread is interuppted, therefore no transaction can be started.", e);
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(false);
		a1 = null;
		a2 = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof Activity1EditPart) {
			a1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();
			if (a1.getSubdiagram() != null) {
				action.setEnabled(true);
			}
			return;
		}else if(part instanceof Activity2EditPart) {
			a2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();
			if (a2.getSubdiagram() != null) {
				action.setEnabled(true);
			}
			return;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
	}
	
	
}
