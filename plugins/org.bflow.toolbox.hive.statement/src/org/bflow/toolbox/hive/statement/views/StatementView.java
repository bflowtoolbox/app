package org.bflow.toolbox.hive.statement.views;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;


public class StatementView extends ViewPart implements ISelectionListener{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.bflow.toolbox.hive.statement.views.StatementView";

	private TableViewer viewer;
	private Action addSatementAction;
	private Action doubleClickAction;
	
	
	private List<Property> properties = new ArrayList<Property>();
	private Map<Property, Link> propertiesToLinks = new HashMap<Property, Link>();	
	
	private String diagramTitle;

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
				final Link link;
				
				if (!properties.contains(property) || !propertiesToLinks.containsKey(property)) {
					
					link = new Link((Composite) cell.getViewerRow().getControl(), SWT.NONE);
					int randomNum = 0 + (int)(Math.random() * 100); 
					link.setText(property.getTemplateString() + " " + randomNum);
					// link.setSize(400, 400);
					link.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							System.out.println("Selection: " + link.getText());
						}
					});
					propertiesToLinks.put(property, link);
				}else{
					link = propertiesToLinks.get(property);
				}
				
				TableItem item = (TableItem) cell.getItem();
				TableEditor editor = new TableEditor(item.getParent());
				editor.grabHorizontal = true;
				editor.grabVertical = true;
				editor.setEditor(link, item, cell.getColumnIndex());
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
			this.diagramTitle = "Kein Diagram ausgewählt";
		}
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
								propertiesToLinks.get(currentProperty).dispose();
								propertiesToLinks.remove(currentProperty);
								viewer.refresh();
							}
						}
						else {
							///ERSTMAL FÜR TESTSÄTZE
							addSatementAction.run();
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
        tableColumPropertyTemplate.setText("Properties für " + diagramTitle);
        tableColumPropertyTemplate.setWidth(480);
        TableViewerColumn columnPropertyTemplate = new TableViewerColumn(viewer, tableColumPropertyTemplate);
        columnPropertyTemplate.setLabelProvider(new ColumnTextLabelProvider(0));
		        
        //SECOND Column
        TableColumn columnRemoveAction = new TableColumn(viewer.getTable(), SWT.NONE);
		columnRemoveAction.setWidth(17);
		TableViewerColumn col = new TableViewerColumn(viewer, columnRemoveAction);
		col.setLabelProvider(new ColumnTextLabelProvider(1));

		//TESTDATENSATZ
		Property p1 = new Property("<a>Funktion1</a> kann mehrfach ausgeführt werden");
        properties.add(p1);
        
        viewer.setInput(properties);
        
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.bflow.toolbox.hive.statement.viewer");
		makeActions();
		// hookDoubleClickAction();
        
	}
	
	private class Property {

		private String templateString;

		Property(String templateString) {
			this.templateString = templateString;
		}

		public String getTemplateString() {
			return templateString;
		}
	}
	

	private void makeActions() {
		addSatementAction = new Action() {
			public void run() {
				//Weiteren Testdatensatz hinzufügen
				Property p1 = new Property("<a>Funktion1</a> kann mehrfach ausgeführt werden");
		        properties.add(p1);
		        viewer.refresh(properties);
//				StatementDialog statmentDialog = new StatementDialog(getSite().getShell(),null);
//				
//				if (statmentDialog.open() == Window.OK) {
//					Link last = statements.get(statements.size()-1);
//					statements.remove(last);
//					//baue einen link
//					//statements.add(statmentDialog.getSelectedTemplate());
//					statements.add(last);
//					viewer.refresh(statements);
//				}
			}
		};
		addSatementAction.setText("Property hinzufügen");
		addSatementAction.setToolTipText("Füge eine neue Property hinzu");
		addSatementAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		doubleClickAction = new Action() {
			public void run() {
//				ISelection selection = viewer.getSelection();
//				String property = (String) ((IStructuredSelection)selection).getFirstElement();
//				if (isLastProperty(statement)) {
//					return;
//				}
//				StatementDialog statmentDialog = new StatementDialog(getSite().getShell(),statement);
//				
//				if (statmentDialog.open() == Window.OK) {
//					  int index = statements.indexOf(statement);
//					  //statements.set(index, statmentDialog.getSelectedTemplate());
//					  viewer.refresh(statements);
//					} 
			}
		};
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