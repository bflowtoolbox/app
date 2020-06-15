package org.bflow.toolbox.extensions.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Diagram link action base class.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 *
 * @param <TSelectionData> Selection data class
 * @param <TModificationValue> Modification value class
 */
public abstract class AbstractDiagramLinkAction<TSelectionData, TModificationValue> implements IObjectActionDelegate {
	private final Log _log;
	
	private Shell _shell;
	private IWorkbench _workbench;
	private TSelectionData _selectionData;
	
	/** Initializes the new instance. */ 
	protected AbstractDiagramLinkAction() {
		_log = LogFactory.getLog(getClass());
	}
	
	/** Log instance */
	protected Log Log() { return _log; }
	
	/** Shell */
	protected Shell Shell() { return _shell; }
	
	/** Workbench */
	protected IWorkbench Workbench() { return _workbench; }
	
	/** Selection data */
	protected TSelectionData SelectionData() { return _selectionData; }
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		_shell = targetPart.getSite().getShell();
		_workbench = targetPart.getSite().getWorkbenchWindow().getWorkbench();		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		_selectionData = getSelectionData(selection);
		action.setEnabled(isEnabled(_selectionData));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		TModificationValue modificationValue = getModificationValue(_selectionData);
		
		try {
			performModification(_selectionData, modificationValue);
		} catch(Exception ex) {
			_log.error("Error on modifying diagram element", ex);
		}
	}
	
	/** Return the selection data that is used for the insert operation. */
	protected abstract TSelectionData getSelectionData(ISelection selection);
	
	/** Returns TRUE if the current action is enabled and therefore executable. */
	protected abstract boolean isEnabled(TSelectionData selectionData);
	
	/** Returns the value that is used to perform the modification. */
	protected abstract TModificationValue getModificationValue(TSelectionData selectionData);
	
	/** Notifies the instance to perform the modification with the given values. */
	protected abstract void performModification(TSelectionData selectionData, TModificationValue modificationValue) throws Exception;
}
