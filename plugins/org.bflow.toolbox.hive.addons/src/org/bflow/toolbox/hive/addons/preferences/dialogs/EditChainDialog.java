package org.bflow.toolbox.hive.addons.preferences.dialogs;

import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Defines an InputDialog for registering an external chain.
 * 
 * @author Arian Storch
 * @since 17/04/10
 */
public class EditChainDialog extends Dialog {
	private String input[] = new String[3];
	
	private boolean inputChanged = false;

	/**
	 * Constructor
	 * @param parent parent shell
	 */
	public EditChainDialog(Shell parent) {
		super(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		this.setText(NLUtil.getMessage("EditChainDialog#msg1"));
	}
	
	/**
	 * Creates the content of this dialog.
	 * @param composite composite
	 */
	protected void createContents(final Composite composite) {
		composite.setLayout(new GridLayout(2, true));
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalSpan = 2;
		gridData.widthHint = 400;
		gridData.verticalSpan = 2;
		gridData.minimumHeight = 20;
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText(NLUtil.getMessage("EditChainDialog#msg2"));
		lblName.setLayoutData(gridData);
		
		final Text txtName = new Text(composite, SWT.BORDER);
		txtName.setText("");
		txtName.setLayoutData(gridData);
		
		Label lblClass = new Label(composite, SWT.NONE);
		lblClass.setText(NLUtil.getMessage("EditChainDialog#msg3"));
		lblClass.setLayoutData(gridData);
		
		final Text txtClass = new Text(composite, SWT.BORDER);
		txtClass.setText("");
		txtClass.setLayoutData(gridData); 
		
		Button btnSelect = new Button(composite, SWT.PUSH);
		btnSelect.setText(NLUtil.getMessage("EditChainDialog#msg4"));
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fDlg = new FileDialog(composite.getShell(), SWT.OPEN);
					
				if(fDlg.open() != null) {
					String toolPath = fDlg.getFilterPath()+System.getProperty("file.separator")+fDlg.getFileName();
					txtClass.setText(toolPath);						
				}
				
			}});
		
		Label lblTool = new Label(composite, SWT.NONE);
		lblTool.setText(NLUtil.getMessage("EditChainDialog#msg5"));
		lblTool.setLayoutData(gridData);
		
		final Combo combo = new Combo(composite, SWT.NONE);
		
		for(ToolDescriptor tool:ToolStore.getInstalledTools())
			combo.add(tool.getName());
		
		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayout(new GridLayout(2,false));
		GridData panelGridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		panelGridData.horizontalSpan = 2;
		
		panel.setLayoutData(panelGridData);
		
		Button btnOK = new Button(panel, SWT.PUSH);
		btnOK.setText("OK");
		
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				input = new String[] {txtName.getText(), txtClass.getText(), combo.getText()};
				composite.getShell().close();
			}
			
		});
		
		Button btnCancel = new Button(panel, SWT.PUSH);
		btnCancel.setText(NLUtil.getMessage("EditChainDialog#msg6"));
		
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				input = null;
				composite.getShell().close();
			}
			});
		
		composite.getShell().setDefaultButton(btnOK);
		
		if(inputChanged) {
			txtName.setText(input[0]);
			txtClass.setText(input[2]);
			combo.setText(input[1]);			
		}
		
	}
	
	/**
	 * Opens the dialog.
	 * @return Returns the inserted values or null if the user cancelled the dialog.
	 */
	public String[] open() {
		Shell shell = new Shell(this.getParent(), this.getStyle());
		shell.setText(this.getText());
		createContents(shell);
		
		shell.pack();
		shell.open();
		
		Display display = this.getParent().getDisplay();
		
		while(!shell.isDisposed())
			if(!display.readAndDispatch())
				display.sleep();
		
		return input;
	}
	
	/**
	 * Sets a default input state.
	 * @param input input values
	 */
	public void setInput(String input[]) {
		inputChanged = true;
		this.input = input; 
	}

}
