package edu.toronto.cs.openome.evaluation.gui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import edu.toronto.cs.openome.evaluation.views.ModelInstance;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * @author aftabs
 * A dialog that prompts for a name and description of the evaluation alternate.
 */

public class AlternateDialog extends StatusDialog {
	
   Label errorLabel, nameLabel, descLabel;
   Text nameText, descText;
   String name, description;
   
	/**
     * Open the example dialog.
     * 
     * @param parent
     *            the parent shell
     * @param username
     *            the default username
     */
    public AlternateDialog(Shell parent) {
        super (parent);
    }

   /**
    * Initializes the dialog area
    */
    protected Control createDialogArea(Composite parent) {

        Composite outer = (Composite) super .createDialogArea(parent);

        initializeDialogUnits(outer);
        createSecurityGroup(outer);

        return outer;
    }


    /**
     * Returns the GridData for each field in the dialog
     * @return
     */
    GridData getFieldGridData() {
        int margin = FieldDecorationRegistry.getDefault()
                .getMaximumDecorationWidth();
        GridData data = new GridData();
        data.horizontalAlignment = SWT.FILL;
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH + margin;
        data.horizontalIndent = margin;
        data.grabExcessHorizontalSpace = true;
        return data;

    }


    /**
     * Create and initialize the label and text fields in the shell
     * provided. 
     * @param parent
     */
    void createSecurityGroup(Composite parent) {

    	// Error Label, shows up before all other labels and fields
    	errorLabel = new Label(parent, SWT.NONE);
    	
    	// Show the error in red font
    	errorLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
        errorLabel.setText(" ");

        setTitle(GUIConstants.ALTERNATE_EVAL_DIALOG_TITLE);
        Group main = new Group(parent, SWT.NONE);
        main.setLayoutData(new GridData(GridData.FILL_BOTH));
        main.setText(GUIConstants.ALTERNATE_EVAL_DIALOG_HEADING);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        main.setLayout(layout);
        
        // Alternate name label
        nameLabel = new Label(main, SWT.LEFT);
        nameLabel.setText(GUIConstants.ALTERNATE_NAME);

        // Create a field representing a name
        nameText = new Text(main, SWT.BORDER);
        
        // Alternate description label
        descLabel = new Label(main, SWT.LEFT);
        descLabel.setText(GUIConstants.ALTERNATE_DESC);

        // Create a field representing a description
        descText = new Text(main, SWT.BORDER);
       
        
        // Assign the grid data to the text fields
        nameText.setLayoutData(getFieldGridData());
        descText.setLayoutData(getFieldGridData());
        errorLabel.setLayoutData(getFieldGridData());
 
    }

    /**
     * Returns the alternative description
     * @return description
     */
    public String getDescription() {
    	return description;
    }

    /**
     * Returns the alternative name
     * @return name
     */
    	public String getName() {
    	return name;
    }

    /**
     * Displays the error in the dialog
     * @param error
     */
    private void showError (String error) {
    	errorLabel.setText("ERROR: " + error);
    }
    
	/*
	 * Overrides method from Dialog
	 */
	protected void okPressed() {
		
		// show an error if user doesn't fill in the name, which
		// is mandatory. 
		if (nameText.getText().equals("")) {
			showError(GUIConstants.ALTERNATE_NAME_EMPTY_ERROR);
		} 
		/* Check for duplicate */
		else if (checkForDuplicate(nameText.getText())) { 
			showError(GUIConstants.ALTERNATE_DUPLICATE_NAME_ERROR);
		} else {
			
			// Store all text fields
			name = nameText.getText();
			description = descText.getText();
			super.okPressed();
		}
	}

	/**
	 * Check if the given Alternate name already exists in the model 
	 * @param newAltName
	 * @return
	 */
	private boolean checkForDuplicate(String newAltName) {
		
		ModelImpl mi = ModelInstance.getModelImpl();
		EList<Alternative> alternateList = mi.getAlternatives();
		
		for (Alternative alt : alternateList){
			if (alt.getName().equals(newAltName))
				return true;
			
		}
		return false;
	}
	
	
}