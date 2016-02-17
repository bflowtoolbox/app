package vcchart.diagram.actions;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.part.EpcCreationWizard;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;


/**
 * Action for creating and linking a new EPC to a VC-Activity.
 * @author Markus Schnädelbach
 *
 */
public class CreateSubdiagramAction implements IObjectActionDelegate {
	
	private Shell myShell;
	private IWorkbench workbench;
	private Activity1 a1;
	private Activity2 a2;
	
	/** The log instance for this class */
	private static final Log logger = LogFactory.getLog(CreateSubdiagramAction.class);
	
	@Override
	public void run(IAction action) {
		EpcCreationWizard wizard = new EpcCreationWizard();
		wizard.init(workbench, StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(myShell, wizard);
		wizardDialog.open();
		
		final String pathName = wizard.getDiagram().getURI().toPlatformString(true);
		
		if (a1 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a1.eContainer()), false,Collections.EMPTY_MAP);
			try {
				t.start();
				a1.setSubdiagram(pathName);
				t.commit();
			} catch (RollbackException e) {
				logger.error("Subdiagram could not linked with Activity1.", e);
			} catch (InterruptedException e) {
				logger.error("The current thread is interuppted, therefore no transaction can be started.", e);
			}
			
		}else if (a2 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a2.eContainer()), false,Collections.EMPTY_MAP);
			try {
				t.start();
				a2.setSubdiagram(pathName);
				t.commit();
			} catch (RollbackException e) {
				logger.error("Subdiagram could not linked with Activity2.", e);
			} catch (InterruptedException e) {
				logger.error("The current thread is interuppted, therefore no transaction can be started.", e);
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					Activity1EditPart a1EP = (Activity1EditPart) structuredSelection.getFirstElement();
					this.a1 = (Activity1) a1EP.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					Activity2EditPart a2EP = (Activity2EditPart) structuredSelection.getFirstElement();
					this.a2 = (Activity2) a2EP.getPrimaryView().getElement();
				}	
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();
		workbench = targetPart.getSite().getWorkbenchWindow().getWorkbench();
	}
}
