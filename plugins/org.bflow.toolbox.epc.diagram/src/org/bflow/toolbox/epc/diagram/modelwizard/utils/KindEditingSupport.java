package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element.Kind;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;

/**
 * Implements the EditingSupport interface for a table viewer.
 * @author Arian Storch
 * @since 03/12/09
 * @version 18/12/09
 *
 */
public class KindEditingSupport extends EditingSupport 
{
	private CellEditor editor;
	
	private int column;

	/**
	 * Default constructor.
	 * @param viewer table viewer
	 * @param column column of the table
	 */
	public KindEditingSupport(ColumnViewer viewer, int column) 
	{
		super(viewer);
		
		this.editor = new ComboBoxCellEditor(((TableViewer)viewer).getTable(), 
								new String[]{ "Event", "Function", "None" }, 
								SWT.BORDER | SWT.READ_ONLY);
		
		this.column = column;
	}

	@Override
	protected boolean canEdit(Object element) 
	{		
		ProcessStep step = (ProcessStep)element;
		ConnectorType type = step.getConnector().getConnectorType();
		
		if(type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE || 
				type == ConnectorType.XOR_SINGLE)
			return false;
		else
			return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) 
	{
		return editor;
	}

	@Override
	protected Object getValue(Object element) 
	{
		ProcessStep processStep = (ProcessStep)element;
		Element el = processStep.get(column);
		
		processStep.set(new Element(el.getName(), (el.getKind() == Kind.Event ? Kind.Function : Kind.Event))
											, column);
		
		getViewer().update(element, null);
		
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) 
	{

	}

}
