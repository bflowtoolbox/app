package org.bflow.toolbox.epc.diagram.modelwizard.utils;

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
		ProcessStep currentProcessStep = (ProcessStep)element;
		Element el = currentProcessStep.get(column);
		TableViewer tv = (TableViewer) getViewer();
		
		if (el.getKind() == Kind.Event || el.getKind() == Kind.Function) {
			currentProcessStep.set(new Element(el.getName(),(el.getKind() == Kind.Event ? Kind.Function : Kind.Event)),column);

			Table table = tv.getTable();
			TableItem[] items = table.getItems();
			ProcessStep lastItem = (ProcessStep) items[items.length - 1].getData();
			if (items.length >= 2) {
				ProcessStep secondLastItem = (ProcessStep) items[items.length - 2].getData();

				if (secondLastItem.equals(currentProcessStep) && !lastItem.isEmpty()) {//diese Bedingung ist nicht korrekt!
					Element lastelement = lastItem.get(column);		//die bedingung sollte sein, ob vorletztes element in dieser spalte
					if (lastelement.getName().isEmpty()) {			//nicht vorletzter ProzessStep!!! evtl Refactor, weil so eien Methode
																	//existiert bereits in Nameditingsupport
						Kind kind = currentProcessStep.get(column).getKind();
						Element el2 = new Element("",(kind == Kind.Event ? Kind.Function	: Kind.Event));
						lastItem.set(el2, column);
					}
				}
			}
		}else {// es ist eine Single-Konnektor
			Element el2 = new Element(" ",(Kind.Event));
			currentProcessStep.set(el2, column);
		}
		tv.update(element, null);
		
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) 
	{

	}

}
