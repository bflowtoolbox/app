package org.bflow.toolbox.hive.templating.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Simple user dialog for the connector selection.
 * 
 * @author Markus Schnaedelbach
 */
public class ConnectorDialog extends Dialog {

	  private boolean notForcedConnector = true;
	  private ConnectorType connectortype;
	  

	public ConnectorDialog(Shell parent, boolean notForceConnector) {
		super(parent);
		setShellStyle(getShellStyle() & ~SWT.CLOSE);
		notForcedConnector  = notForceConnector;
		 if (!notForcedConnector) {
				connectortype = ConnectorType.AND;
			}else {
				connectortype = ConnectorType.NOTHING;
			}
	}


	  @Override
	  protected Control createDialogArea(Composite parent) {
	    Composite container = (Composite) super.createDialogArea(parent);
	    Button btn_AND = new Button(container, SWT.RADIO);
	    Button btn_XOR = new Button(container, SWT.RADIO);
	    Button btn_OR = new Button(container, SWT.RADIO);
	    Button btn_NOTHING = new Button(container, SWT.RADIO);
	       
	    GridData gridDataBtn = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		
	    btn_AND.setLayoutData(gridDataBtn);
	    btn_AND.setText(Messages.ConnectorDialog_0);
	    btn_XOR.setLayoutData(gridDataBtn);
	    btn_XOR.setText(Messages.ConnectorDialog_1);
	    btn_OR.setLayoutData(gridDataBtn);
	    btn_OR.setText(Messages.ConnectorDialog_2);
	    btn_NOTHING.setLayoutData(gridDataBtn);
	    btn_NOTHING.setText(Messages.ConnectorDialog_3);
	    if (!notForcedConnector) {
			btn_NOTHING.setEnabled(false);
			btn_AND.setSelection(true);
		}else {
			btn_NOTHING.setSelection(true);
		}
	    
	    btn_AND.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setConnectortype(ConnectorType.AND);
			}
		});
	    btn_XOR.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setConnectortype(ConnectorType.XOR);
			}
		});
	    btn_OR.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setConnectortype(ConnectorType.OR);
			}
		});
	    btn_NOTHING.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setConnectortype(ConnectorType.NOTHING);
			}
		});
	    return container;
	  }
	  
	  @Override
	  protected void configureShell(Shell newShell) {
	    super.configureShell(newShell);
	    newShell.setText(Messages.ConnectorDialog_4);
	  }
	  
	  @Override
	  protected Point getInitialSize() {
	    return new Point(400, 300);
	  }
	  
	  @Override
	  protected Button createButton(Composite parent, int id,
		        String label, boolean defaultButton) {
		    if (id == IDialogConstants.CANCEL_ID) return null;
		    return super.createButton(parent, id, label, defaultButton);
		}


	public ConnectorType getConnectortype() {
		return connectortype;
	}


	public void setConnectortype(ConnectorType connectortype) {
		this.connectortype = connectortype;
	}

}
