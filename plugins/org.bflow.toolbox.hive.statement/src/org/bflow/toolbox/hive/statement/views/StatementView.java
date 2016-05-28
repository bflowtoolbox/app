package org.bflow.toolbox.hive.statement.views;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.*;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;


public class StatementView extends ViewPart implements ISelectionListener{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.bflow.toolbox.hive.statement.views.StatementView";

	private TableViewer viewer;
	private Combo combo = null;
	
	private List<Property> properties = new ArrayList<Property>();
	private Map<Property, Control> controlsToLinks = new HashMap<Property, Control>();	
	
	private String diagramTitle;

	private List<Property> propertyTemplates;

	class ColumnTextLabelProvider extends ColumnLabelProvider{
		private int column;
		
		public ColumnTextLabelProvider(int column) 
		{
			this.column = column;
		}
		
		@Override
		public void update(ViewerCell cell) {
			super.update(cell);

			if (column == 0) {
				Property property = (Property) cell.getElement();
				final Control control;
				
				if (!isLastProperty(property) && !controlsToLinks.containsKey(property)) {
					final Link link = new Link((Composite) cell.getViewerRow().getControl(), SWT.NONE);
					link.setText(property.getTemplateString());
					link.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							System.out.println("Selection: " + link.getText());
						}
					});
					control = link;
					controlsToLinks.put(property, link);
				}else if (isLastProperty(property) && combo == null) {
					combo = new Combo((Composite) cell.getViewerRow().getControl(), SWT.DROP_DOWN);
					
					
					
					String[] templatesArray = new String[propertyTemplates.size()];
					for (int i = 0; i < templatesArray.length; i++) {
						templatesArray[i] = propertyTemplates.get(i).getTemplateString();
					}
					
					combo.setItems(templatesArray);
					combo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							String selectedTemplateString = combo.getItem(combo.getSelectionIndex());
							Property last = properties.get(properties.size()-1);
							properties.remove(last);
							properties.add(new Property(selectedTemplateString));
							properties.add(last);
					        combo.dispose();
					        combo = null;
					        viewer.refresh();
						}
						
					});
					control = combo;
				}else{
					control = controlsToLinks.get(property);
				}
				
				TableItem item = (TableItem) cell.getItem();
				TableEditor editor = new TableEditor(item.getParent());
				editor.grabHorizontal = true;
				editor.grabVertical = true;
				editor.setEditor(control, item, cell.getColumnIndex());
				editor.layout();
				
			}
		}
		
		@Override
		public Image getImage(Object obj) {
			
			if (column == 0) {
				if (isLastProperty((Property) obj)) {
					return null;
					//return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
					//return new Image(getSite().getShell().getDisplay(), this.getClass().getResourceAsStream("/icons/add.gif"));
				}
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			}
			if (column == 1) {
				if (isLastProperty((Property) obj)) {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
				}
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE_DISABLED);
			}
			return null;
		}
	}

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public StatementView() {
		
		IEditorPart currentEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (currentEditorPart != null) {
			this.diagramTitle = currentEditorPart.getTitle();
		}else {
			this.diagramTitle = "Kein Diagram ausgew�hlt";
		}
		propertyTemplates = getStatmentTemplatesFromWorkspace();
	}
	
	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        
        final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(viewer,new FocusCellOwnerDrawHighlighter(viewer));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {

			@Override
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				ViewerCell focusCell = (ViewerCell) event.getSource();
				int currentColumn = focusCell.getVisualIndex();
				Property currentProperty = (Property) focusCell.getElement();
				if (currentColumn == 0) {
//					if (isLastStatement(currentStatement)) {
//						if (event.keyCode == SWT.SPACE ||event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
//							addSatementAction.run();
//						}
//					}
				}
				
				if (currentColumn == 1) {
					if (event.keyCode == SWT.SPACE ||event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
						if (!isLastProperty(currentProperty)) {
							boolean bool = MessageDialog.openQuestion(viewer.getControl().getShell(), "Statement wirklich entfernen?", "Soll "+ currentProperty.getTemplateString() + " wirklich entfernt werden?");
							if (bool) {
								properties.remove(currentProperty);
								controlsToLinks.get(currentProperty).dispose();
								controlsToLinks.remove(currentProperty);
								if (combo != null) {
									combo.dispose();
									combo = null;
								}
								viewer.refresh();
								
							}
						}
					}
				}
				return false;
			}};
			
		int features = ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL
				| ColumnViewerEditor.KEYBOARD_ACTIVATION;

		TableViewerEditor.create(viewer, focusCellManager, actSupport, features);

		//FIRST Column
        TableColumn tableColumPropertyTemplate = new TableColumn(viewer.getTable(), SWT.NONE);
        tableColumPropertyTemplate.setText("Properties f�r " + diagramTitle);
        tableColumPropertyTemplate.setWidth(480);
        TableViewerColumn columnPropertyTemplate = new TableViewerColumn(viewer, tableColumPropertyTemplate);
        columnPropertyTemplate.setLabelProvider(new ColumnTextLabelProvider(0));
		        
        //SECOND Column
        TableColumn columnRemoveAction = new TableColumn(viewer.getTable(), SWT.NONE);
		columnRemoveAction.setWidth(17);
		TableViewerColumn col = new TableViewerColumn(viewer, columnRemoveAction);
		col.setLabelProvider(new ColumnTextLabelProvider(1));

        properties.add(new Property());
        
        viewer.setInput(properties);
	}
	
	private List<Property> getStatmentTemplatesFromWorkspace() {
		ArrayList<Property> propertyTemplates = new ArrayList<>();
		
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
	            		propertyTemplates.add(new Property(temp));
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
		return propertyTemplates;
	}
	
	private class Property {

		private String templateString;

		Property(String templateString) {
			this.templateString = templateString;
		}

		public Property() {
		}

		public String getTemplateString() {
			return templateString;
		}
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private boolean isLastProperty(Property property) {
		return this.properties.indexOf(property) == this.properties.size()-1;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
	}
}