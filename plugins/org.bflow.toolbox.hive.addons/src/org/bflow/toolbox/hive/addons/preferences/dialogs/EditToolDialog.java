package org.bflow.toolbox.hive.addons.preferences.dialogs;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Defines an InputDialog for registering an external tool.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17.04.10
 * @version 06.08.14
 * 			12.03.15 Added usage of ToolStore.hasTool()
 * 					 Removed obsolete NLSupport
 */
public class EditToolDialog extends Dialog {	
	
	private String input[] = new String[3];
	private boolean inputChanged = false;
	private boolean changeable = true;
	
	/**
	 * Constructor.
	 * @param parent parent shell
	 */
	public EditToolDialog(Shell parent) {
		super(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		this.setText(NLSupport.EditToolDialog_Dialog_Description);
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
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText(NLSupport.EditToolDialog_Label_Name);
		lblName.setLayoutData(gridData);
		
		final Text txtName = new Text(composite, SWT.BORDER);
		txtName.setText(StringUtils.EMPTY);
		txtName.setLayoutData(gridData);
		
		if (!changeable)
			txtName.setEditable(false);
		
		Label lblPath = new Label(composite, SWT.NONE);
		lblPath.setText(NLSupport.EditToolDialog_Label_Path);
		lblPath.setLayoutData(gridData);
		
		final Text txtPath = new Text(composite, SWT.BORDER);
		txtPath.setText(StringUtils.EMPTY);
		txtPath.setLayoutData(gridData);
		
		Button btnSelect = new Button(composite, SWT.PUSH);
		btnSelect.setText(NLSupport.EditToolDialog_Button_Select);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fDlg = new FileDialog(composite.getShell(), SWT.OPEN);
				
				if(txtName.getText().equalsIgnoreCase("SWI-Prolog")) //$NON-NLS-1$
					fDlg.setFilterExtensions(new String[] {"plcon.*", "*.*"}); //$NON-NLS-1$ //$NON-NLS-2$
					
				if(fDlg.open() != null) {
					String toolPath = fDlg.getFilterPath()+System.getProperty("file.separator")+fDlg.getFileName(); //$NON-NLS-1$
					txtPath.setText(toolPath);						
				}
				
			}});
		
		Label lblParam = new Label(composite, SWT.NONE);
		lblParam.setText(NLSupport.EditToolDialog_Label_Param);
		lblParam.setLayoutData(gridData);
		
		final Text txtParam = new Text(composite, SWT.BORDER);
		txtParam.setText(StringUtils.EMPTY);
		txtParam.setLayoutData(gridData);
		
		if (!changeable)
			txtParam.setEditable(false);
		
		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayout(new GridLayout(2,false));
		GridData panelGridData = new GridData(SWT.RIGHT, SWT.CENTER, true, true);
		panelGridData.horizontalSpan = 2;
		
		panel.setLayoutData(panelGridData);
		
		GridData btnGridData = new GridData(SWT.RIGHT, SWT.CENTER, true, true);
		btnGridData.widthHint = 94;
		
		Button btnOK = new Button(panel, SWT.PUSH);
		btnOK.setText(NLSupport.EditToolDialog_Button_Ok);
		btnOK.setLayoutData(btnGridData);
		
		btnOK.addSelectionListener(new SelectionAdapter()  {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				String toolName = txtName.getText();
				boolean nameHasChanged = !inputChanged || !toolName.equalsIgnoreCase(input[0]);
				if (nameHasChanged && ToolStore.hasTool(toolName)) {
					MessageDialog.openError(txtName.getShell(), NLSupport.EditToolDialog_Message_Error_DialogTitle, NLSupport.EditToolDialog_Message_Error_ToolExists);
					return;
				}
				
				input = new String[] {txtName.getText(), txtPath.getText(), txtParam.getText()};
				composite.getShell().close();
			}
			
		});
		
		Button btnCancel = new Button(panel, SWT.PUSH);
		btnCancel.setText(NLSupport.EditToolDialog_Button_Cancel);
		btnCancel.setLayoutData(btnGridData);
		
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				input = null;
				composite.getShell().close();
			}
		});
		
		btnOK.setSize(btnCancel.getSize());
		
		composite.getShell().setDefaultButton(btnOK);
		
		if (inputChanged) {
			txtName.setText(input[0]);
			txtPath.setText(input[1]);
			txtParam.setText(input[2]);			
		}
	}
	
	/**
	 * Opens the dialog.
	 * 
	 * @return Returns the inserted values or null if the user cancelled the
	 *         dialog.
	 */
	public String[] open() {
		Shell shell = new Shell(this.getParent(), this.getStyle());
		shell.setText(this.getText());
		createContents(shell);
		
		shell.pack();
		shell.open();
		
		Display display = this.getParent().getDisplay();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				 display.sleep();
		
		return input;
	}
	
	/**
	 * Sets a default input state.
	 * 
	 * @param input
	 *            input values
	 */
	public void setInput(String input[]) {
		inputChanged = true;
		this.input = input; 
	}
	
	/**
	 * Sets the editable state of some input values.
	 * 
	 * @param b
	 *            true or false
	 */
	public void setChangeable(boolean b) {
		this.changeable = b;
	}
}
