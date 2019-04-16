package oepc.diagram.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class FileOperationDialog extends Dialog {

	public static boolean isNotAskAgain = false;
	
	public static boolean isCopy = true;
	
	public FileOperationDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
        GridLayout layout = new GridLayout(1, false);
        layout.marginLeft = 5;
        layout.marginRight = 5;
        layout.marginBottom = 0;
        
        container.setLayoutData(data);
        container.setLayout(layout);
        
        Label questionLabel = new Label(container, SWT.NONE);
        questionLabel.setText("Wählen Sie, wie die Datei(en) mit dem Element verknüpft werden sollen:"); 
        
        Composite radioComposite = new Composite(container, SWT.NONE);

        data = new GridData(SWT.FILL, SWT.FILL, true, false);
        layout = new GridLayout(1, false);
        layout.marginLeft = 20;
        layout.marginBottom = 0;
        
        radioComposite.setLayoutData(data);
        radioComposite.setLayout(layout);
        
        Button[] radios = new Button[2];
        radios[0] = new Button(radioComposite, SWT.RADIO);
        radios[0].setSelection(isCopy);
        radios[0].setText("Dateien kopieren");
        radios[0].setBounds(10, 5, 75, 30);
        radios[0].addSelectionListener(SelectionListener.widgetSelectedAdapter(evt -> isCopy = true));

        radios[1] = new Button(radioComposite, SWT.RADIO);
        radios[1].setSelection(!isCopy);
        radios[1].setText("Dateien verknüpfen");
        radios[1].setBounds(10, 30, 75, 30);
        radios[1].addSelectionListener(SelectionListener.widgetSelectedAdapter(evt -> isCopy = false));
        
        Composite checkComposite = new Composite(container, SWT.NONE);

        data = new GridData(SWT.FILL, SWT.FILL, true, false);
        layout = new GridLayout(1, false);
        layout.marginLeft = 20;
        layout.marginBottom = 0;
        
        checkComposite.setLayoutData(data);
        checkComposite.setLayout(layout);
        
        Button dontAskAgainCheck = new Button(checkComposite, SWT.CHECK);
        dontAskAgainCheck.setText("In Zukunft nicht mehr fragen");
        dontAskAgainCheck.setBounds(10, 30, 75, 30);
        dontAskAgainCheck.addSelectionListener(SelectionListener.widgetSelectedAdapter(evt -> isNotAskAgain = !isNotAskAgain));
        
        parent.layout();
		parent.pack();
        
        return container;
    }
	
	@Override
	protected Control createButtonBar(Composite parent) {
		Control buttonBar = super.createButtonBar(parent);
		Composite composite = (Composite) buttonBar;
		
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.marginBottom = layout.marginHeight;
		layout.marginHeight = 0;
		layout.marginTop = 5;		
		
		return composite;
	}

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Assoziations Dialog");
    }
}
