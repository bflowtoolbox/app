package org.bflow.toolbox.hive.statement.views;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.statement.views.Property.Variable;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;


/**
 * Implements the view part to support the add-ons Statement View.
 * 
 * @author Markus Schn�delbach
 */
public class StatementView extends ViewPart implements ISelectionListener, IAttributeFileRegistryListener{

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

	private TableColumn tableColumPropertyTemplate;

	private ISelectionService selectionService;

	private DiagramEditor activeEditorPart;

	private String diagramId;
	
	private ISelectionListener selectionListener;
	private boolean selectionInProgress = false;
	//current Proptery in user-selection-mode
	private Property selectionProperty;
	private int selectionVarId;
	private String selectionLinkText;

	private AttributeFile attrFile;
	
	
	/**
	 * The constructor.
	 */
	public StatementView() {
		
		IEditorPart currentEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		activeEditorPart = null;
		if (currentEditorPart instanceof DiagramEditor) {
			activeEditorPart = (DiagramEditor) currentEditorPart;
		}
		selectionService  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		propertyTemplates = getStatmentTemplatesFromWorkspace();
		AttributeFileRegistry.getInstance().addRegistryListener(this);
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
        ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE); 
        
        final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(viewer,new FocusCellOwnerDrawHighlighter(viewer));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {

			@Override
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				ViewerCell focusCell = (ViewerCell) event.getSource();
				int currentColumn = focusCell.getVisualIndex();
				Property currentProperty = (Property) focusCell.getElement();
				
				if (currentColumn == 1) {
					if (event.keyCode == SWT.SPACE ||event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
						if (!isLastProperty(currentProperty)) {
							boolean bool = MessageDialog.openQuestion(viewer.getControl().getShell(), "Statement wirklich entfernen?", "Soll "+ currentProperty.getTemplateString() + " wirklich entfernt werden?");
							if (bool) {
								attrFile.remove(diagramId, currentProperty.getId());			
								properties.remove(currentProperty);
								controlsToLinks.get(currentProperty).dispose();
								controlsToLinks.remove(currentProperty);
								if (combo != null) {
									combo.dispose();
									combo = null;
								}
								if (selectionInProgress && currentProperty.equals(selectionProperty)) {
									selectionService.removeSelectionListener(selectionListener);
									selectionInProgress = false;
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
        tableColumPropertyTemplate = new TableColumn(viewer.getTable(), SWT.NONE);
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
        disableView();
	}
	
	
	/**
	 * Reads the available templates from the workspace.
	 * 
	 * @return a List of available Property-Templates
	 */
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

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	/**
	 * Checks if the given property is the last in the current view table
	 * 
	 * @param Property
	 * @return true if the given property is the last in the view table
	 */
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		AttributeFileRegistry.getInstance().removeRegistryListener(this);
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart editorPart = part.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
		selectionService = part.getSite().getWorkbenchWindow().getSelectionService();

		// Kein unterst�tztes Diagram ge�ffnet
		if (!(editorPart instanceof DiagramEditor)) {
			disableView();
		}
	}

	/**
	 * If the attributefile is changed, that method will be called and re-init
	 * the Statement-View
	 * @see org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener#noticeAttributeFileChange(org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent)
	 */
	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		if (event.attributeFile == null || event.diagramEditor == null) {
			disableView();
			return;
		}
		DiagramEditor editorPart =event.diagramEditor;

		// Kontextwechsel - View muss reinitialisiert werden
		if (!editorPart.equals(activeEditorPart) || !event.attributeFile.equals(attrFile)) {
			activeEditorPart = editorPart;
			attrFile = event.attributeFile;
			Property.setAttributFile(attrFile);
			this.diagramTitle = activeEditorPart.getTitle();
			this.diagramId = getDiagramIdFromEditorPart((DiagramEditor) activeEditorPart);
			HashMap<String, String> allAttr = attrFile.get(diagramId);
			properties.clear();
			
			HashMap<String, NodeName> shapeIdtoClassname = null;
			if (allAttr != null) {
				for (String propertyId : allAttr.keySet()) {
					//Id is an valid UUID?
					if (propertyId.matches("property_[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")) {
						if (shapeIdtoClassname == null) {
							shapeIdtoClassname = getShapeIdsAndClassnamesFromDiagram();
						}
						properties.add(getPropertyObjectfromAttribute(allAttr.get(propertyId), diagramId, propertyId, shapeIdtoClassname));
					}
				}
			}
			
			properties.add(new Property());
			tableColumPropertyTemplate.setText("Properties f�r " + diagramTitle);
			for (Property prop : controlsToLinks.keySet()) {
				controlsToLinks.get(prop).dispose();
			}
			controlsToLinks.clear();
			if (combo != null) {
				combo.dispose();
				combo = null;
			}
			if (selectionInProgress) {
				selectionService.removeSelectionListener(selectionListener);
				selectionInProgress = false;
			}
			viewer.getTable().setEnabled(true);
			viewer.refresh();
		}
	}
	
	/**
	 * Returns the value of the nameattribute of the given eObject.
	 * If there is no such attribute it will return null 
	 * 
	 * @param eObj
	 * @return String or null
	 */
	private String getNameAttributeFromEObject(EObject eObj) {
		String shapename = null;
		Method getName = null;
		try {
		    getName = eObj.getClass().getMethod("getName");
		    
		    if(getName != null) {
		        Object shapenameobject = getName.invoke(eObj);
		        if (shapenameobject instanceof String) {
					shapename = (String) shapenameobject;
				}
		    }
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		    e.printStackTrace();
		}
		if (shapename != null && !shapename.trim().isEmpty()) {
			return shapename;
		}else {
			return null;
		}
	}
	
	/**
	 * Returns the uuid of the current diagram 
	 * 
	 * @param activeEditorPart as DiagramEditor
	 * @return the uuid 
	 */
	private String getDiagramIdFromEditorPart(DiagramEditor activeEditorPart) {
		DiagramEditor diagramEditor = activeEditorPart;
		DiagramEditPart dep = diagramEditor.getDiagramEditPart();
		DiagramImpl diagramImpl = (DiagramImpl) dep.getModel();
		EObject eObj = diagramImpl.getElement();
		XMLResource resource = (XMLResource) eObj.eResource();
		return resource.getID(eObj);
	}
	
	/**
	 * Returns a new property restored from a attribute.
	 * 
	 * @param propertyString
	 * @param diagramId
	 * @param propertyId
	 * @param shapeIdtoClassname
	 * @return Property - the restored Property
	 */
	private Property getPropertyObjectfromAttribute(String propertyString, String diagramId, String propertyId, HashMap<String, NodeName> shapeIdtoClassname) {
		Property property = new Property();
		property.setDiagramId(diagramId);
		property.setId(propertyId);
		
		ArrayList<Variable> vars = new ArrayList<>();
		
		if (propertyString.contains("$")) { //$NON-NLS-1$
			String[] words = propertyString.split("\\s"); //$NON-NLS-1$
			for (int i = 0; i < words.length; i++) {
				
				if (words[i].startsWith("$")) { //$NON-NLS-1$
					while (words[i].startsWith("$")) {
						words[i] = words[i].substring(1);
					}
					String classname = null;
					NodeName nn = shapeIdtoClassname.get(words[i]);
					if (nn != null) {
						classname = nn.getClassname();
					}
					
					if (classname != null) {
						Variable var = property.new Variable(classname, words[i]);
						String shapename = shapeIdtoClassname.get(words[i]).getName();
						if (shapename != null) {
							words[i] = "$" + shapename;
						}else {
							words[i] = "$" + words[i];
						}
						vars.add(var);
					}else {
						words[i] = "$unknown";
						vars.add(property.new Variable("unknown"));
					}
				}
				property.setVariables(vars);
			}
			StringBuilder builder = new StringBuilder();
			for(String s : words) {
			    builder.append(s);
			    builder.append(" ");
			}
			property.setTemplateString(builder.toString().trim());
		}else {
			property.setTemplateString(propertyString);
		}
		return property;
	}

	/**
	 * Returns all uuids:classnames of ShapeNodeEditParts contained in the activeEdidorPart
	 * @return Hashmap with uuid and classname pairs of ShapeNodeEditParts
	 */
	private HashMap<String, NodeName> getShapeIdsAndClassnamesFromDiagram() {
		HashMap<String, NodeName> shapeIdtoClassname = new HashMap<>();
		List<Object> children = activeEditorPart.getDiagramEditPart().getChildren();
		for (Object child : children) {
			if (child instanceof ShapeNodeEditPart) {
				//hole ShapeId
				ShapeNodeEditPart editPart = (ShapeNodeEditPart) child;
				NodeImpl nodeImpl = (NodeImpl) editPart.getModel();
				EObject eObj = nodeImpl.getElement();
				XMLResource resource = (XMLResource) eObj.eResource();
				String id = resource.getID(eObj);
				String classname = child.getClass().getSimpleName().replace("EditPart", "").toLowerCase();
				String shapename = getNameAttributeFromEObject(eObj);
				shapeIdtoClassname.put(id, new NodeName(classname, shapename));
			}
		}
		return shapeIdtoClassname;
	}

	/**
	 * Disabled the StatementView
	 */
	private void disableView() {
		//activeEditorPart = null;
		this.diagramTitle = "";
		tableColumPropertyTemplate.setText(diagramTitle);
		this.diagramId = "";
		properties.clear();
		for (Property prop : controlsToLinks.keySet()) {
			controlsToLinks.get(prop).dispose();
		}
		controlsToLinks.clear();
		if (combo != null) {
			combo.dispose();
			combo = null;
		}
		if (selectionInProgress) {
			selectionService.removeSelectionListener(selectionListener);
			selectionInProgress = false;
		}		
		viewer.getTable().setEnabled(false);
		viewer.refresh();
	}
	
	public String getDiagramId() {
		return diagramId;
	}
	
	/**
	 * Stores the name and classname of an editpart
	 */
	private class NodeName {
		String classname;
		String name;
		
		public NodeName(String classname, String name) {
			this.classname = classname;
			this.name = name;
		}
		
		public String getClassname() {
			return classname;
		}
		public String getName() {
			return name;
		}
	}
	
	private class ColumnTextLabelProvider extends ColumnLabelProvider{
		private int column;
		
		public ColumnTextLabelProvider(int column) 
		{
			this.column = column;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
		 */
		@Override
		public void update(ViewerCell cell) {
			super.update(cell);

			if (column == 0) {
				final Property property = (Property) cell.getElement();
				final Control control;
				
				if (!isLastProperty(property) && !controlsToLinks.containsKey(property)) {
					final Link link = new Link((Composite) cell.getViewerRow().getControl(), SWT.NONE);
					link.setText(property.getTemplateStringWithLinks());
					if (!property.isComplete()) {
						link.setToolTipText("nicht zugeordnet");
					}else {
						link.setToolTipText("zugeordnet");
					}
					
					link.addListener(SWT.Selection, new Listener() {

						@Override
						public void handleEvent(Event event) {
							final int varId = Integer.parseInt(event.text);
							final String variablename = property.getVariable(varId).getName();
							if (selectionInProgress && property.equals(selectionProperty) && selectionVarId == varId) {
								link.setText(selectionLinkText);
								selectionService.removeSelectionListener(selectionListener);
                				selectionInProgress = false;
                				selectionVarId = -1;
                				selectionProperty = null;
                				combo.setEnabled(true);
								return;
							}
							if (selectionInProgress) {
								return;
							}
							
							String oldLinkText = link.getText();
							String newLink = "<a href=\""+ event.text +"\">" + "["+ ">....<" + "]" + "</a>";
							String newLinkText = oldLinkText.replaceAll("<a href=\"" + event.text + "\">\\[(.*?)\\]</a>", newLink);
							link.setText(newLinkText);
							
							selectionInProgress = true;
							selectionVarId = varId;
							selectionLinkText = oldLinkText;
							selectionProperty = property;
							combo.setEnabled(false);
							
							activeEditorPart.getDiagramGraphicalViewer().deselectAll();
							
							selectionService.addSelectionListener(selectionListener =	new ISelectionListener() {
								@Override
								public void selectionChanged(IWorkbenchPart part, ISelection selection) {
									
										IStructuredSelection sel = (IStructuredSelection) selection;
										if (sel.getFirstElement() instanceof ShapeNodeEditPart) {
											ShapeNodeEditPart editPart = (ShapeNodeEditPart) sel.getFirstElement();
											String classname = editPart.getClass().getSimpleName().replace("EditPart", "");
				                			if (variablename.toLowerCase().equals(classname.toLowerCase())) {
				                				NodeImpl nodeImpl = (NodeImpl) editPart.getModel();
												EObject eObj = nodeImpl.getElement();
												XMLResource resource = (XMLResource) eObj.eResource();
												String id = resource.getID(eObj);
												property.getVariable(varId).setId(id);
												
												//try to get the name attribute of that shape
												String shapename = getNameAttributeFromEObject(eObj);
				                				
												String replacementString;
												if (shapename != null) {
													replacementString = shapename;
												}else {
													replacementString = id;
												}
												
				                				link.setText(link.getText().replace(">....<", replacementString));
				                				link.setToolTipText("zugeordnet");
				                				selectionService.removeSelectionListener(this);
				                				selectionInProgress = false;
				                				selectionVarId = -1;
				                				selectionProperty = null;
				                				combo.setEnabled(true);
												viewer.refresh();
				                			}
										}
								}
							});
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
							properties.add(new Property(selectedTemplateString, diagramId));
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
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			return "";
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object obj) {
			Property prop = (Property) obj;
			if (column == 0) {
				if (isLastProperty(prop)) {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
					//return new Image(getSite().getShell().getDisplay(), this.getClass().getResourceAsStream("/icons/add.gif"));
				}
				if(prop.isComplete()){
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
				}else {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
				}
			}
			if (column == 1) {
				if (isLastProperty((Property) obj)) {
					return null;
				}
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE_DISABLED);
			}
			return null;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
		 */
		@Override
		public String getToolTipText(Object element) {
			Property prop = (Property) element;
			
			if (column == 0 && prop.isComplete()) {
				return "Property ist vollst�ndig";
			}
			if (column == 0 && !prop.isValid()) {
				return "Besch�digte Property! Ein zugeordnetes Node existiert nicht mehr.";
			}
			if (column == 0 && !prop.isComplete()) {
				return "Property ist nicht vollst�ndig. Alle Varaiblen m�ssen dem Diagramm zugeordnet sein";
			}
			if (column == 1 && isLastProperty(prop)) {
				return null;
			}
			if (column == 1 && !isLastProperty(prop)) {
				return "L�schen";
			}
			return null;
		}
	}
}