package org.bflow.toolbox.extensions.diagram.preferences;

import org.bflow.toolbox.check.CheckPlugin;
import org.bflow.toolbox.check.LauncherConfigurator;
import org.bflow.toolbox.extensions.NLSupport;
import org.bflow.toolbox.extensions.configurator.BflowConfigurator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the main preferences page for bflow settings.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 21/11/09
 * @version 15/12/13
 * 
 */
public class BflowMainPreferencesPage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
		
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());
		
		Label lblInfo = new Label(top, SWT.NONE);
		lblInfo.setText(NLSupport.BflowMainPreferencesPage_LabelInfoText);
		
		Composite comp = new Composite(top, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		
		Label lblLang = new Label(comp, SWT.NONE);
		lblLang.setText(NLSupport.BflowMainPreferencesPage_LabelLanguageText);
		
		final Combo langCombo = new Combo(comp, SWT.BORDER | SWT.READ_ONLY);
		
		if(!LauncherConfigurator.getInstance().isIniFound()) {
			langCombo.setEnabled(false);
		}
		
		int selIndex = -1;
		boolean langSelected = false;
		String defaultLang = CheckPlugin.getInstance().getActiveLanguage();
		
		for(String instLang: CheckPlugin.getInstance().getAvailableLanguages())
		{
			langCombo.add(instLang);
			
			if(!langSelected)
				selIndex++;
			
			if(instLang.contains(defaultLang))
				langSelected = true;
		}
		
		langCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int index = langCombo.getSelectionIndex();
				
				if(index == -1)
					return ;
				
				String lang = CheckPlugin.getInstance().getLanguageAbbreviation(index);
				
				CheckPlugin.getInstance().setActiveLanguage(lang);
				
			}});
		
		langCombo.select(selIndex);
		
		Label lblIDelete = new Label(comp, SWT.NONE);
		lblIDelete.setText(NLSupport.BflowMainPreferencesPage_LabelIntelligentDeleterText);
		
		final Button btnIDelete = new Button(comp, SWT.CHECK);
		btnIDelete.setText(NLSupport.BflowMainPreferencesPage_ButtonIntelligentDeleterText);
		
		boolean b = Boolean.parseBoolean(BflowConfigurator.getInstance().get(BflowConfigurator.Key.IDELETE));
		
		btnIDelete.setSelection(b);
		btnIDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BflowConfigurator.getInstance().put
				(BflowConfigurator.Key.IDELETE, ""+btnIDelete.getSelection()); //$NON-NLS-1$
			}
		});
		
		return top;
	}

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
	}

}
