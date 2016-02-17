package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import java.util.Vector;

import org.bflow.toolbox.epc.diagram.modelwizard.pages.ElementGeneratorWizardPage;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element.Kind;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * Implements the EditingSupport interface for a table viewer.
 * @author Arian Storch
 * @since 03/12/09
 * @version 01/02/10
 *
 */
public class NameEditingSupport extends EditingSupport 
{
	private CellEditor editor;
	
	private int column;
	
	private TableViewer viewer;
	
	private Vector<ProcessStep> steps;
	
	private boolean moveCursor;
	private int moveTo;

	/**
	 * Default constructor.
	 * @param viewer table viewer
	 * @param column column of the element within the process step
	 * @param steps vector of process steps shown in the table
	 */
	public NameEditingSupport(ColumnViewer viewer, int column, final Vector<ProcessStep> steps) 
	{
		super(viewer);
		
		this.column = column;
		this.steps = steps;
		this.viewer = (TableViewer)viewer;
		
		this.editor = new MyTextCellEditor(((TableViewer)viewer).getTable());
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
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) 
	{
		if(((ProcessStep)element).size() > column)
			return ((ProcessStep)element).get(column).getName();
		else
			return null;
	}

	@Override
	protected void setValue(Object element, Object value) 
	{
		moveCursor = false;
		moveTo = -1;
		
		if(((String)value).isEmpty())
		return ;
		
		ProcessStep processStep = (ProcessStep)element;
		Element el = processStep.get(column);
		if(((String)value).equalsIgnoreCase(el.getName())) // nur Änderungen werden berücksichtigt
			return ;
		
		processStep.set(new Element((String)value, el.getKind()), column);
		
		getViewer().update(element, null);
		
		int row = steps.indexOf(processStep);
		
		if(row == steps.size()-1)	// letzter Prozessstep
		{
			ProcessStep newStep = new ProcessStep(processStep.getConnector());
						
			Kind kind = (el.getKind() == Kind.Event ? Kind.Function : Kind.Event);
			
			for(int i = 0; i < processStep.size(); i++)
				newStep.add(new Element("", kind));
			
			steps.add(newStep);
			((TableViewer)getViewer()).add(newStep);
			
			for(ProcessStep s:steps)
				getViewer().update(s, null);
			
			moveTo = steps.size()-1;
		}else if (ElementGeneratorWizardPage.isLastElementInColumn(steps, processStep, column)) {
			ProcessStep nextStep = steps.get(row + 1);
			Kind kind = (el.getKind() == Kind.Event ? Kind.Function : Kind.Event);
			nextStep.set(new Element("",kind), column);
			
			for(ProcessStep s:steps){
				getViewer().update(s, null);
			}
			moveTo = row+1;
		}
		
		moveCursor = true;
		if(moveTo == -1)
			moveTo = row+1;
	}
	
	/**
	 * Anonymous class to extend the default TextCellEditor to react of apply changes.
	 * @author Arian Storch
	 * @since 16/12/09
	 * @version 26/01/10
	 *
	 */
	private class MyTextCellEditor extends TextCellEditor
	{
		public MyTextCellEditor(Composite parent) {
			super(parent);			
		}
		
		@Override
		public void activate(ColumnViewerEditorActivationEvent activationEvent) {
			super.activate();
			if (activationEvent.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED) {
				this.text.setText(String.valueOf(activationEvent.character));

			}
		}
		
		@Override
		public void deactivate() {
			super.deactivate();
						
			int col = column;
			
			if(moveCursor)
			{
				ProcessStep ps1 = steps.get(moveTo-1);
				ProcessStep ps2 = steps.get(moveTo);
				
				if(ps2.getConnector() != ps1.getConnector())
					if(ps2.size() < ps1.size())
						col = 0;
			}
			
			if(moveCursor)
				if(viewer != null && steps != null && steps.size() > 0)
						viewer.editElement(steps.get(moveTo), col*2+2);
		}	
		
		@Override
		protected void keyReleaseOccured(KeyEvent keyEvent) {
			if (keyEvent.keyCode == 9) { // Tabulator
				super.deactivate();
				this.fireCancelEditor();
			}
			super.keyReleaseOccured(keyEvent);
			if (keyEvent.keyCode == SWT.ESC) {
				super.deactivate();
				this.fireCancelEditor();
			}
		}
		
		@Override
		protected void doSetFocus() {
			super.doSetFocus();
			if (text != null) {
				text.clearSelection();
			}
		}
	}
}
