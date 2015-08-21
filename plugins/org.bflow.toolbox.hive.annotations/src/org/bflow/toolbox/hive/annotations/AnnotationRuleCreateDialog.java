package org.bflow.toolbox.hive.annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This Dialog is prompted to the user. He/she has to enter Strings which
 * defines a new annotation rule. It comprises the direction of a given
 * annotation, a unique attribute name/value pair, an Operator, a category to
 * which the new annotation fits and the filename of the annotation. It shows a
 * preview of the image.
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 19.05.2015
 *
 */
public class AnnotationRuleCreateDialog extends TitleAreaDialog {

	/**
	 * Array of Text fields to store a user entry for the annotation logic (+1
	 * for the desctiption/name of the rule)
	 */
	private Text[] userInput = new Text[RuleEntry.ColumnQuantity + 2];
	/**
	 * Array of Comboboxes to show all entries of the AnnotatioRule .xml files
	 * (+1 for the desctiption/name of the rule)
	 */
	private Combo[] userComboInput = new Combo[RuleEntry.ColumnQuantity + 2];

	private Button sortingOrderButton;

	//used to update the attribute view
	private String key = "";
	private String value = "";
	private RuleEntry defaultRule = null;

	/**
	 * Holds all Strings, which should be shown in the chooser fields and combo
	 * boxes. (As preview which values are already used)
	 */
	private ArrayList<HashSet<String>> strSet;
	/**
	 * Stores all rules. Is used to validate, if a user input is allowed
	 */
	private ArrayList<String[]> strList;
	private AnnotationRuleController annotationRuleController;

	/**
	 * creates the dialog to create a NEW rule. Give the parent Shell and the
	 * current AnnotationRuleController (it is used to read all current rules
	 * and preview the entries).
	 * 
	 * @param parent
	 * @param annotationRuleController
	 */
	public AnnotationRuleCreateDialog(Shell parent,
			AnnotationRuleController annotationRuleController) {
		super(parent);
		this.annotationRuleController = annotationRuleController;
		this.setHelpAvailable(false);

	}

	/**
	 * creates the dialog to EDIT an exiting rule (the given entry). (creates
	 * the same dialog as the default constructor, but fill all fields with the
	 * values of the given RuleEntry
	 * 
	 * @param parent
	 * @param annotationRuleController
	 * @param entry
	 */
	public AnnotationRuleCreateDialog(Shell parent,
			AnnotationRuleController annotationRuleController, RuleEntry entry) {
		super(parent);
		this.annotationRuleController = annotationRuleController;
		this.setHelpAvailable(false);
		defaultRule = entry;

	}

	@Override
	protected Point getInitialSize() {
		return new Point(790, 385);

	}


	/**
	 * Creates the gui.
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {

		final Composite area = (Composite) super.createDialogArea(parent);
		final Composite container = new Composite(area, SWT.NONE);

		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		//init data structure
		strSet = new ArrayList<HashSet<String>>((RuleEntry.ColumnQuantity + 2)); //+1 for the .xml filename input (+1 for the desctiption/name of the rule)
		for (int i = 0; i < RuleEntry.ColumnQuantity + 2; i++) {
			strSet.add(i, new HashSet<String>(63));
		}
		strList = new ArrayList<String[]>(40);
		//read all entries of already used rules (to set them as a preview
		for (RuleEntry ruleEntry : annotationRuleController.getRules()) {

			strSet.get(RuleEntry.ColumnATTRIBUTE_NAME).add(
					ruleEntry.getEntryInColumn(RuleEntry.ColumnATTRIBUTE_NAME));
			strSet.get(RuleEntry.ColumnCATEGORY).add(
					ruleEntry.getEntryInColumn(RuleEntry.ColumnCATEGORY));
			strSet.get(RuleEntry.ColumnFILENAME).add(
					ruleEntry.getEntryInColumn(RuleEntry.ColumnFILENAME));
			strSet.get(RuleEntry.ColumnRuleName).add(
					ruleEntry.getEntryInColumn(RuleEntry.ColumnRuleName));

			//read all lines for writing

			strList.add(ruleEntry.getEntrySeparated(RuleEntry.SEPARATOR));
		}
		//set the strings for the columns as preview for the selection
		strSet.set(
				RuleEntry.ColumnDIRECTION,
				new HashSet<String>(Arrays.asList("NORTH_EAST", "NORTH_WEST",
						"SOUTH_EAST", "SOUTH_WEST", "NORTH", "SOUTH", "EAST",
						"WEST", "CENTER")));
		strSet.set(
				RuleEntry.ColumnOPERATOR,
				new HashSet<String>(Arrays.asList("<", ">", "\u2260", "\u2264",
						"\u2265", "=")));

		//create input text fields
		createComboInputRow(
				container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_AttributeName,
				RuleEntry.ColumnATTRIBUTE_NAME, false);
		createComboInputRow(container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Operator,
				RuleEntry.ColumnOPERATOR, true);
		createInputRow(container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Value,
				RuleEntry.ColumnVALUE);
		createComboInputRow(container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Category,
				RuleEntry.ColumnCATEGORY, false);
		createComboInputRow(container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Direction,
				RuleEntry.ColumnDIRECTION, true);

		//create input row for the image with all available image filenames as possible entries and a image preview window
		final Label imgLabel = new Label(container, SWT.NONE);
		imgLabel.setText(NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_ImagePreview);
		userComboInput[RuleEntry.ColumnFILENAME] = new Combo(container,
				SWT.DROP_DOWN | SWT.READ_ONLY);
		userComboInput[RuleEntry.ColumnFILENAME].setLayoutData(new GridData(
				SWT.FILL, SWT.NONE, true, true));
		userComboInput[RuleEntry.ColumnFILENAME]
				.setItems(annotationRuleController.getAllAvailableImgNames());
		userComboInput[RuleEntry.ColumnFILENAME]
				.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						Combo comb = (Combo) e.getSource();
						String completePath = AnnotationLauncherConfigurator
								.getANNOTATIONLOGIC_FOLDER_PATH()
								+ comb.getText();
						try {
							imgLabel.setImage(new Image(container.getShell()
									.getDisplay(), new FileInputStream(
									completePath)));

						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						area.getShell().pack(true);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});

		//crating a row for the name/description of the rule
		createInputRow(container,
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_RuleName,
				RuleEntry.ColumnRuleName);

		//choosing the sorting order is only possible when creating new rules.
		Label rowLabel = new Label(container, SWT.NONE);
		rowLabel.setText(NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_SortASC);
		sortingOrderButton = new Button(container, SWT.CHECK);
		sortingOrderButton
				.setSelection(annotationRuleController
						.getSortingOrderForCategory(userComboInput[RuleEntry.ColumnCATEGORY]
								.getText()));
		userComboInput[RuleEntry.ColumnCATEGORY]
				.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						Combo combo = (Combo) e.getSource();

						sortingOrderButton
								.setSelection(annotationRuleController
										.getSortingOrderForCategory(combo
												.getText()));
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});

		//if a rule should be edited (and not new created) defaultRule is != null, set the used image file name as default
		if (defaultRule != null) {
			//calculate the index of the filename in the string[] of all available image names
			String[] allImages = annotationRuleController
					.getAllAvailableImgNames();
			for (int i = 0; i < allImages.length; i++) {
				if (allImages[i].equals(defaultRule.getFilename())) {
					//and set this index as a default value
					userComboInput[RuleEntry.ColumnFILENAME].select(i);
					break;
				}
			}

		}
		return area;
	}



	/**
	 * creates a new user input line with a given label and a Textfield
	 * 
	 * @param container
	 *            parent composite
	 * @param labelName
	 *            Label name
	 * @param userInputPointer
	 *            pointer to the local text array {@link #userInput}
	 */
	private void createInputRow(Composite container, String labelName,
			int userInputPointer) {
		Label rowLabel = new Label(container, SWT.NONE);
		rowLabel.setText(labelName);
		userInput[userInputPointer] = new Text(container, SWT.BORDER);
		userInput[userInputPointer].setLayoutData(new GridData(SWT.FILL,
				SWT.NONE, true, false));
		if (defaultRule != null) {

			userInput[userInputPointer].setText(defaultRule
					.getEntryInColumn(userInputPointer));
		}
	}

	/**
	 * creates a new user input line with a given label and a (read-only or not)
	 * Combobox.
	 * 
	 * @param container
	 *            parent composite
	 * @param labelName
	 *            Label
	 * @param userInputPointer
	 *            pointer on the position in the local Combo array
	 *            {@link #userComboInput}
	 * @param readOnly
	 *            true, if so
	 */
	private void createComboInputRow(Composite container, String labelName,
			int userInputPointer, boolean readOnly) {
		Label rowLabel = new Label(container, SWT.NONE);
		rowLabel.setText(labelName);

		if (readOnly) {
			userComboInput[userInputPointer] = new Combo(container,
					SWT.DROP_DOWN | SWT.READ_ONLY);
		} else {
			userComboInput[userInputPointer] = new Combo(container,
					SWT.DROP_DOWN);

		}
		userComboInput[userInputPointer].setLayoutData(new GridData(SWT.FILL,
				SWT.NONE, true, false));
		userComboInput[userInputPointer].setItems(strSet.get(userInputPointer)
				.toArray(new String[0]));
		userComboInput[userInputPointer]
				.select(getSelectedItemInt(userInputPointer));

		//	userComboInput[userInputPointer].setVisibleItemCount(8); usefull???

	}

	/**
	 * get the position for an inputPointer in the strSet
	 * 
	 * @param userInputPointer
	 * @return
	 */
	private int getSelectedItemInt(int userInputPointer) {
		int itemAtPos = 0; // first element is default

		if (userInputPointer == RuleEntry.ColumnOPERATOR)
			itemAtPos = 4; //4 is default for the "="-Operator

		if (defaultRule != null) {
			int i = 0;
			for (String str : strSet.get(userInputPointer)) {
				if (str.equals(defaultRule.getEntryInColumn(userInputPointer))) {
					itemAtPos = i;
					break;
				}
				i++;
			}
		}
		return itemAtPos;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	public void create() {
		super.create();
		if (defaultRule == null) {
			setTitle(NLSupport.AnnotationRuleViewPart_Annotation_ButtonAddText);
		} else {
			setTitle(NLSupport.AnnotationRuleViewPart_Annotation_ButtonEditText);
		}
		setMessage(
				NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_Text,
				IMessageProvider.INFORMATION);

	}

	@Override
	public void okPressed() {

		//read user input
		boolean validEntries = true;
		String inputAttributeName = userComboInput[RuleEntry.ColumnATTRIBUTE_NAME]
				.getText();
		String inputValue = userInput[RuleEntry.ColumnVALUE].getText().trim();
		String inputOperator = userComboInput[RuleEntry.ColumnOPERATOR]
				.getText().trim();
		String inputCategory = userComboInput[RuleEntry.ColumnCATEGORY]
				.getText().trim();
		String inputDirection = userComboInput[RuleEntry.ColumnDIRECTION]
				.getText().trim();
		String inputFilename = userComboInput[RuleEntry.ColumnFILENAME]
				.getText().trim();
		String inputXMLFilename = annotationRuleController
				.getXMLFilePathForCategory(inputCategory);
		Boolean sortCategoryASC = sortingOrderButton.getSelection();
		if (defaultRule == null) {
			//			inputXMLFilename = userComboInput[RuleEntry.ColumnQuantity]
			//					.getText().trim();
		}
		String inputRulename = userInput[RuleEntry.ColumnRuleName].getText()
				.trim();

		//check if input is NOT empty
		if (inputValue.isEmpty() || inputAttributeName.isEmpty()
				|| inputCategory.isEmpty() || inputFilename.isEmpty()
				|| inputRulename.isEmpty()
		) {
			setMessage(
					NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_WarningEmpty,
					IMessageProvider.WARNING);
			setReturnCode(CANCEL);
		} else if (!isCategoryValid(inputCategory)) {
			setMessage(
					NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_WarningWrongChars,
					IMessageProvider.WARNING);
			setReturnCode(CANCEL);
		} else

		{
			//VALIDATION. Do we need this?
			if (defaultRule == null) { //only validate new rules
				for (String[] line : strList) {
					if ((line[RuleEntry.ColumnATTRIBUTE_NAME]
							.equals(inputAttributeName)
							&& line[RuleEntry.ColumnVALUE].equals(inputValue)
							&& line[RuleEntry.ColumnOPERATOR]
									.equals(inputOperator)
							&& line[RuleEntry.ColumnCATEGORY]
									.equals(inputCategory)
							&& line[RuleEntry.ColumnFILENAME]
									.equals(inputFilename) && line[RuleEntry.ColumnDIRECTION]
								.equals(inputDirection))) {
						validEntries = false;
						break;
					}
				}
			}

			if (validEntries) {

				if (defaultRule == null) {
					//add the new rule if entries are valid
					RuleEntry entry = new RuleEntry(inputCategory,
							inputAttributeName, inputOperator, inputValue,
							inputDirection, inputFilename, inputRulename);

					annotationRuleController.addRule(entry, inputXMLFilename,
							sortCategoryASC);
				} else {

					annotationRuleController.updateRule(defaultRule,
							inputCategory, inputAttributeName, inputOperator,
							inputValue, inputDirection, inputFilename,
							inputRulename, sortCategoryASC);
				}

				key = inputAttributeName;
				value = inputValue;
				setReturnCode(OK);
				close();
			} else {
				//text a warning message if entries are not valid
				setMessage(
						NLSupport.AnnotationRuleViewPart_AnnotationChooserDialog_Warning,
						IMessageProvider.WARNING);
				setReturnCode(CANCEL);
			}
		}
	}

	/**
	 * returns false if one of the following character is used:  *?"\/|<>:
	 */
	private boolean isCategoryValid(String inputCategory) {
		return !(inputCategory.contains(">") || inputCategory.contains("<")
				|| inputCategory.contains("|") || inputCategory.contains("\"")
				|| inputCategory.contains("*") || inputCategory.contains("?")
				|| inputCategory.contains(":") || inputCategory.contains("\\") || inputCategory
					.contains("/"));
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
