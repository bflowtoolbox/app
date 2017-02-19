/******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package edu.toronto.cs.openome_model.diagram.layout.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import edu.toronto.cs.openome_model.diagram.layout.provider.SquareLayoutProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;


/**
 * @author sshaw
 *
 * Sample action for demonstrating invokation of a custom layout provider.  In this
 * case the square layout provider is invoked through a separate menu action.
 */
public class SquareLayoutHandler implements org.eclipse.core.commands.IHandler {

	/**
	 * Title of diagram creation operation
	 */
	private static final String KEY_SQUARE_LAYOUT = "squareLayoutTitle"; //$NON-NLS-1$


	/**
	 * Walk the selected objects and creates a new diagram for each visited
	 * packages
	 *
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/* Get selection */
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		// Get selection from the window
		final ISelection selection = window.getSelectionService().getSelection();
        Diagram diagramView = null;
        
        // get the editing domain
        if (selection instanceof IStructuredSelection) {

            IStructuredSelection structuredSelection = (IStructuredSelection) selection;

            // Walk selection
            for (Iterator i = structuredSelection.iterator(); i.hasNext();) {

                // Try to adapt the selection to a view
                Object selectedObject = i.next();
                if (selectedObject instanceof IAdaptable) {

                    // Try to get a View (new notation)
                    Object object = ((IAdaptable) selectedObject)
                        .getAdapter(View.class);
                    
                    diagramView = ((View)object).getDiagram();
                }
            }
        }
        
        if (diagramView != null) {
            final Diagram diag = diagramView;
            TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(diagramView);
            AbstractEMFOperation operation = new AbstractEMFOperation(
                ted, KEY_SQUARE_LAYOUT, null) {

                protected IStatus doExecute(IProgressMonitor monitor,
                        IAdaptable info)
                    throws ExecutionException {

                    LayoutService.getInstance().layout(diag, SquareLayoutProvider.SQUARE_LAYOUT);

                    return Status.OK_STATUS;
                }
            };
            try {
                operation.execute(new NullProgressMonitor(), null);
            } 
            catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        }
		return null;
	}

	/**
	 * Constructor
	 */
	public SquareLayoutHandler() {
		//No-op
	}

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}


}
