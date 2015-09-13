package org.bflow.toolbox.epc.templating.dialogs;

import java.util.Collection;

import org.bflow.toolbox.epc.templating.dialogs.BflowTemplate.NamingVariable;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;

/**
 * Defines an TemplateWizardPage1 for choosing a template
 * 
 * @author Markus Schnaedelbach, Christian Soward
 * 
 */
public class TemplateWizardPage1 extends WizardPage {

	private final int SCROLL_ZOOM_PREVIEW_WIDTHHINT = 350;
	private final int MAIN_HEIGHTHINT = 300;

	private Composite composite;
	private Composite panelPreview;
	private ScrollZoomComposite scrollZoomPreviewComposite;

	private String lastTemplateID;

	private TemplateFileService importer;
	private Button btnChkLocal;
	private Button btnChkGlobal;
	private Table templateList;
	private Table namingtable;
	private Rectangle lastBounds;
	private Label description;
	
	public TemplateWizardPage1(String pageName, TemplateFileService importer) {
		super(pageName);
		this.importer = importer;
		setTitle(Messages.TemplateWizardPage1_0);
		setDescription(Messages.TemplateWizardPage1_1);
	}

	@Override
	public void createControl(final Composite composite) {
		this.composite = composite;
		final Composite container = new Composite(composite, SWT.NONE);		
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(new GridLayout(3, false));
				
		// Preview and element-naming
		panelPreview = new Composite(container, SWT.NONE);
		panelPreview.setLayout(new GridLayout(1, false));
		panelPreview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		scrollZoomPreviewComposite = new ScrollZoomComposite(this, SWT.H_SCROLL | SWT.V_SCROLL, SCROLL_ZOOM_PREVIEW_WIDTHHINT,
				MAIN_HEIGHTHINT);

		description = new Label(panelPreview, SWT.None);
		GridData descriptionGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		descriptionGridData.widthHint = SCROLL_ZOOM_PREVIEW_WIDTHHINT;
		description.setLayoutData(descriptionGridData);
		// Prevents the scrollbar from beeing moved, when the user scrolls. The
		// Mouse-Wheel is intended for zooming.
		container.getDisplay().addFilter(SWT.MouseWheel, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget.equals(scrollZoomPreviewComposite))
					event.doit = false;
			}
		});

		createLabelTable(container);
		
		//TEMPLATE-AUSWAHL
		Composite panelList = new Composite(container, SWT.NONE);
		panelList.setLayout(new GridLayout(1, false));
		
		GridData panelListGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		panelListGridData.heightHint = MAIN_HEIGHTHINT;
		panelList.setLayoutData(panelListGridData);		
		// RADIOBUTTON für Unterscheidung lokale und globale Templates
		//Checkbox LOCAL
		btnChkLocal = new Button(panelList, SWT.CHECK);
		btnChkLocal.setText(Messages.TemplateWizardPage1_2);
		btnChkLocal.setSelection(true);
		btnChkLocal.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateTemplateList(); 
			}
		});
		// Checkbox GLOBAL
		btnChkGlobal = new Button(panelList, SWT.CHECK);
		btnChkGlobal.setText(Messages.TemplateWizardPage1_3);
		btnChkGlobal.setSelection(true);
		btnChkGlobal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateTemplateList();
			}
		});
		
		// TemplateList
	    templateList = new Table(panelList, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
	    updateTemplateList();
	    GridData listLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    listLayoutData.heightHint = MAIN_HEIGHTHINT - 50;
	    listLayoutData.widthHint = 120;
		templateList.setLayoutData(listLayoutData);
		templateList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				int selectionCount = templateList.getSelectionIndex();
				if (selectionCount != -1) {
					importer.setCurrentBflowTemplate((BflowTemplate) templateList.getData(String.valueOf(selectionCount)));
					
					
					
					
					scrollZoomPreviewComposite.updateDiagramPreview(event);
					description.setText(importer.getCurrentDescription());
					updateNamingTable();
					if (!isPageComplete()) {
						setPageComplete(true);
					}
				}
			}
		});
		setControl(container);
		setPageComplete(false);
	}

	/**
	 * Empties the template-listfield and fills it with templatenames
	 */
	private void updateTemplateList() {
		
		if (templateList.getItems().length != 0) {
			templateList.removeAll();
		}
		
		int count = 0;
		for (BflowTemplate template : importer.getTemplates(btnChkLocal.getSelection(),btnChkGlobal.getSelection())) {
			TableItem item = new TableItem(templateList, SWT.NONE);
			item.setText(template.getTemplateName());
			if (!template.isValidForAction()) {
				Display display = Display.getCurrent();
				item.setForeground(display.getSystemColor(SWT.COLOR_RED));
			}
			templateList.setData(String.valueOf(count), template);
			count++;
		}
	}

	/**
	 * Creates a editable viewtable for naming the template-elements
	 * @param composite
	 */
	private void createLabelTable(final Composite composite) {
		
		namingtable = new Table(composite, SWT.V_SCROLL);
		namingtable.setHeaderVisible(true);
		namingtable.setLinesVisible(true);
		GridData tableGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableGridData.heightHint = MAIN_HEIGHTHINT;
		tableGridData.widthHint = 200;
		namingtable.setLayoutData(tableGridData);
		TableColumn columnVar = new TableColumn(namingtable, SWT.NONE);
		columnVar.setText(Messages.TemplateWizardPage1_4);
//		columnVar.setWidth(150);
		TableColumn columnLbl = new TableColumn(namingtable, SWT.NONE);
		columnLbl.setWidth(150);
		columnLbl.setText(Messages.TemplateWizardPage1_5);
		
	}
	
	private void updateNamingTable() {
		namingtable.removeAll();
		for (Control items : namingtable.getChildren()) {
			items.dispose();
		}
		
		Collection<NamingVariable> namingVars = importer.getCurrentNamingVariables();
		namingtable.setRedraw(false);
		
		for (NamingVariable nvar : namingVars) {
			final TableItem item = new TableItem(namingtable, SWT.None);
			TableEditor editor = new TableEditor(namingtable);
			editor.grabHorizontal = true;
			item.setText(0, nvar.getName());
			String namingValue = nvar.getValue();
			
			String[] options = nvar.getOptions();
			if (options != null) {
				
				final CCombo combo = new CCombo(namingtable, SWT.NONE);
				combo.setText(Messages.TemplateWizardPage1_9);
				combo.setEditable(false);
				for (String option : options) {
					combo.add(option);
				}
				int indexOfPreSelection = combo.indexOf(namingValue);
				if (indexOfPreSelection != -1) {
					combo.select(indexOfPreSelection);
				}
			    editor.setEditor(combo, item, 1);
			    combo.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						importer.updateNamingVarEntry(item.getText(0), combo.getText());
					}
				});
			} else {
				
				final Text text = new Text(namingtable, SWT.NONE);
				if ("".equals(namingValue)) {
					text.setText(Messages.TemplateWizardPage1_9);
				}else {
					text.setText(namingValue);
				}
				editor.setEditor(text, item, 1);
				text.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent e) {
						if ("".equals(text.getText())) {
							text.setText(Messages.TemplateWizardPage1_9);
						} else {
							importer.updateNamingVarEntry(item.getText(0), text.getText());
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (Messages.TemplateWizardPage1_9.equals(text.getText())) {
							text.setText(""); //$NON-NLS-1$
						}
					}
				});
			}
		}
		namingtable.getColumn(0).pack();
		namingtable.setRedraw(true);	
	}

	protected Table getTemplateList() {
		return templateList;
	}

	protected Composite getTopComposite() {
		return composite;
	}

	public Composite getPanelPreview() {
		return panelPreview;
	}

	public TemplateFileService getImporter() {
		return importer;
	}

	public String getLastTemplateID() {
		return lastTemplateID;
	}

	public void setLastTemplateID(String currentTemplateID) {
		lastTemplateID = currentTemplateID;
	}

	public Rectangle getLastBounds() {
		
		return lastBounds;
	}

	public void setLastBounds(Rectangle bounds) {
		lastBounds = bounds;
	}
}
