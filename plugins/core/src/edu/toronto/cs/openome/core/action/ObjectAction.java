package edu.toronto.cs.openome.core.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ObjectAction extends ExtensionReader implements IObjectActionDelegate {

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}
	StructuredSelection ts = null;
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (ts == null) {
			return;
		}
		Object tp [] = ts.toArray();
		for (int i=0; i< tp.length; i++) {
			IFile f = (IFile) tp[i];
			if (f.exists()) {
				String name = f.getLocation().toOSString();				
				performAction(name);
			}
		}
	}

	protected void performAction(String name) {
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		ts = (StructuredSelection) selection;
	}
}