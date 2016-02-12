package vcchart.diagram.actions;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.part.EpcElementChooserDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for linking an existing EPC to a VC-Activity.
 * @author Markus Schnädelbach
 *
 */
public class InsertSubdiagramAction implements IObjectActionDelegate {
	
	private Activity1 a1;
	private Activity2 a2;
	private Shell shell;
	private ShapeNodeEditPart  selectedActivity;
	
	/** The log instance for this class */
	private static final Log logger = LogFactory.getLog(CreateSubdiagramAction.class);

	@Override
	public void run(IAction action) {
		final View view = (View) selectedActivity.getModel();
		EpcElementChooserDialog elementChooser = new EpcElementChooserDialog(shell, view);
		int result = elementChooser.open();
		if (result != Window.OK) {
			return;
		}
		URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
		String choosenEpcPath = selectedModelElementURI.toPlatformString(true);

		if (a1 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a1.eContainer()), false, Collections.EMPTY_MAP);
			try {
				t.start();
				a1.setSubdiagram(choosenEpcPath);
				t.commit();
			} catch (RollbackException e) {
				logger.error("Subdiagram could not linked with Activity1.", e);
			} catch (InterruptedException e) {
				logger.error("The current thread is interuppted, therefore no transaction can be started.", e);
			}

		} else if (a2 != null) {
			TransactionImpl t = new TransactionImpl(TransactionUtil.getEditingDomain(a2.eContainer()), false, Collections.EMPTY_MAP);
			try {
				t.start();
				a2.setSubdiagram(choosenEpcPath);
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
		selectedActivity = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					selectedActivity = (Activity1EditPart) structuredSelection.getFirstElement();
					a1 = (Activity1)selectedActivity.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					selectedActivity = (Activity2EditPart) structuredSelection.getFirstElement();		
					a2 = (Activity2)selectedActivity.getPrimaryView().getElement();
				}	
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

}
