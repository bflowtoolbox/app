package org.bflow.toolbox.hive.statement.dialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public class StatementDialog extends Dialog {

	private List<String> templates;
	private String selectedTemplate;
	private Combo combo;
	private Composite panelVars;

	public StatementDialog(Shell parentShell, String selectedTemplate) {
		super(parentShell);
		templates = getStatmentTemplatesFromWorkspace();
		this.selectedTemplate = selectedTemplate;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.VERTICAL));

		Label label = new Label(container, SWT.LEFT);
		label.setText("Wähle das zu verwendende Template:");
		combo = new Combo(container, SWT.DROP_DOWN);
		String[] templatesArray = templates.toArray(new String[templates.size()]);
		combo.setItems(templatesArray);
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedTemplate = combo.getItem(combo.getSelectionIndex());
				List<String> vars = getVariablesFromTemplate(selectedTemplate);
				panelVars.dispose();
				panelVars = new Composite(container, SWT.NONE);
				panelVars.setLayout(new FillLayout(SWT.VERTICAL));
				for (String var : vars) {
					
					Label label = new Label(panelVars, SWT.NONE);
	                label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	                label.setText("Wähle zugehöriges Element:");
	                Combo combo = new Combo(panelVars, SWT.DROP_DOWN);
	                
	                /////////////////////AUSLÖAGERN später/////////////////////////////////
	                IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	                List<String> nodes= new ArrayList<>();
	                if (editor instanceof DiagramEditor) {
	                	DiagramEditor diagramEditor = (DiagramEditor) editor;
	                	List<Object> children = diagramEditor.getDiagramEditPart().getChildren();
	                	
	                	for (Object child : children) {
	                		if (child instanceof ShapeNodeEditPart) {
	                			
	                			String classname = child.getClass().getSimpleName().replace("EditPart", "").toLowerCase();
	                			if (var.toLowerCase().equals(classname)) {
	                				nodes.add(child.toString());
								}
//								ShapeNodeEditPart node = (ShapeNodeEditPart) child;
//								NodeImpl node = (NodeImpl) ttt.getModel();
//								BflowSymbolImpl nodeImpl = (BflowSymbolImpl) node.getElement();
//								String shapename = nodeImpl.getName();
							}
						}
					}
	                /////////////////////////////////////////////////////////////////////////
	        		combo.setItems(nodes.toArray(new String[nodes.size()]));
	                
//	                container.getParent().layout(true, true);
	                container.layout(true, true);
				}
				getButton(IDialogConstants.OK_ID).setEnabled(true);
			}
		});
		if (selectedTemplate != null) {
			int i = 0;
			for (String temp : combo.getItems()) {
				if (temp.equals(selectedTemplate)) {
					combo.select(i);
					break;
				}
			}
		}
		
		panelVars = new Composite(container, SWT.NONE);
		panelVars.setLayout(new FillLayout(SWT.VERTICAL));
		
		
		
		return container;
	}

	@Override
	protected Control createContents(Composite parent) {
		super.createContents(parent);
		if (combo.getSelectionIndex() == -1) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
		return parent;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Statment Creation");
	}
	
//	@Override
//	protected boolean isResizable() {
//	    return true;
//	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	public String getSelectedTemplate() {
		return selectedTemplate;
	}
	
	private List<String> getStatmentTemplatesFromWorkspace() {
		ArrayList<String> temps = new ArrayList<>();
		
		IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		rootPath = rootPath.append(".properties/templates.txt");
		File templateFile = rootPath.toFile();
		if (templateFile.isFile() && templateFile.canRead()) {
			BufferedReader in = null;
	        try {
	            in = new BufferedReader(new FileReader(templateFile));
	            String temp = null;
	            while ((temp = in.readLine()) != null) {
	            	if (!temp.trim().isEmpty()) {
	            		temps.add(temp);
					}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null)
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        } 
		}
		return temps;
	}
	
	private List<String> getVariablesFromTemplate(String template) {
		ArrayList<String> vars = new ArrayList<>();

		if (template.contains("$")) { //$NON-NLS-1$
			String[] words = template.split("\\s"); //$NON-NLS-1$
			for (String word : words) {
				if (word.startsWith("$")) { //$NON-NLS-1$
					while (word.startsWith("$")) {
						word = word.substring(1);
					}
					vars.add(word);
				}
			}
		}
		return vars;
	}
}
