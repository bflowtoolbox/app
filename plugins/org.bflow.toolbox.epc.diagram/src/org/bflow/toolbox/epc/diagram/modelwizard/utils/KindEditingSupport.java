package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import java.util.Vector;

import org.bflow.toolbox.epc.diagram.modelwizard.pages.ElementGeneratorWizardPage;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element.Kind;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

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

	private Vector<ProcessStep> steps;

	/**
	 * Default constructor.
	 * @param viewer table viewer
	 * @param column column of the table
	 * @param steps 
	 */
	public KindEditingSupport(ColumnViewer viewer, int column, Vector<ProcessStep> steps) 
	{
		super(viewer);
		
		this.editor = new ComboBoxCellEditor(((TableViewer)viewer).getTable(), 
								new String[]{ "Event", "Function", "None" }, 
								SWT.BORDER | SWT.READ_ONLY);
		this.steps = steps;
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
		ProcessStep currentProcessStep = (ProcessStep)element;
		Element el = currentProcessStep.get(column);
		TableViewer tv = (TableViewer) getViewer();

		if (el.getKind() == Kind.Event || el.getKind() == Kind.Function) {
			Kind currentNewKind = (el.getKind() == Kind.Event ? Kind.Function : Kind.Event);
			currentProcessStep.set(new Element(el.getName(), currentNewKind), column);
			if (ElementGeneratorWizardPage.isLastElementInColumn(steps, currentProcessStep, column)) {
				ProcessStep nextStep = steps.get(steps.indexOf(currentProcessStep) + 1);
				Kind nextKind = (currentNewKind == Kind.Event ? Kind.Function : Kind.Event);
				nextStep.set(new Element("", nextKind), column);
			}
		}
		
		tv.update(element, null);
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) 
	{

	}
	
}
