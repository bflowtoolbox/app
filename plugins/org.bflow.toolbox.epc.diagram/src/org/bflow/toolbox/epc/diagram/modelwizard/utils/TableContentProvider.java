package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Implements the IStructuredContentProvider for a table.
 * @author Arian Storch
 * @since 03/12/09
 * @deprecated
 */
public class TableContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) 
	{
		System.out.println(inputElement);

		return new String[] { "aa2" };
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
		System.out.println("--------->");
	}

}
