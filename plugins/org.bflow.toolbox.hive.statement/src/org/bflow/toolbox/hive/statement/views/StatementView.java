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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.nls.NLSupport;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;


/**
 * Implements the view part to support the add-ons Statement View.
 * 
 * @author Markus Schnädelbach
 */
public class StatementView extends ViewPart implements ISelectionListener, IAttributeFileRegistryListener{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.bflow.toolbox.hive.statement.views.StatementView"; //$NON-NLS-1$


	private TableViewer viewer;
	private Combo combo = null;
	
	private List<Property> properties = new ArrayList<Property>();
	private Map<Property, Control> controlsToLinks = new HashMap<Property, Control>();	
	
	private String diagramTitle;

	private List<String> propertyTemplates;

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
							boolean bool = MessageDialog.openQuestion(viewer.getControl().getShell(), NLSupport.StatementView_RemoveDialogTitle, NLSupport.StatementView_RemoveDialogText1+ currentProperty.getTemplateString() + NLSupport.StatementView_RemoveDialogText2);
							if (bool) {
								attrFile.remove(diagramId, currentProperty.getId());
								attrFile.save();
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
        tableColumPropertyTemplate.setText(NLSupport.StatementView_TableColumnText + diagramTitle);
        TableViewerColumn columnPropertyTemplate = new TableViewerColumn(viewer, tableColumPropertyTemplate);
        columnPropertyTemplate.setLabelProvider(new ColumnTextLabelProvider(0));
		        
        //SECOND Column
        TableColumn columnRemoveAction = new TableColumn(viewer.getTable(), SWT.NONE);
		TableViewerColumn col = new TableViewerColumn(viewer, columnRemoveAction);
		col.getColumn().setResizable(false);
		col.setLabelProvider(new ColumnTextLabelProvider(1));
		
		TableColumnLayout layout = new TableColumnLayout();
		layout.setColumnData( tableColumPropertyTemplate, new ColumnWeightData( 99 ) );
		layout.setColumnData( columnRemoveAction, new ColumnWeightData( 1 ) );
        parent.setLayout(layout);

        properties.add(new Property());
        
        viewer.setInput(properties);
        disableView();
	}
	
	
	/**
	 * Reads the available templates from the workspace.
	 * (.properties/templates.txt)
	 * 
	 * @return a List of available Property-Template Strings
	 */
	private List<String> getStatmentTemplatesFromWorkspace() {
		ArrayList<String> propertyTemplates = new ArrayList<>();
		
		IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		rootPath = rootPath.append(".properties/templates.txt"); //$NON-NLS-1$
		File templateFile = rootPath.toFile();
		if (templateFile.isFile() && templateFile.canRead()) {
			BufferedReader in = null;
	        try {
	            in = new BufferedReader(new FileReader(templateFile));
	            String temp = null;
	            while ((temp = in.readLine()) != null) {
	            	if (!temp.trim().isEmpty()) {
	            		propertyTemplates.add(temp);
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
	 * @param Property to check
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

		// Kein unterstütztes Diagram geöffnet
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

		// Context change - View must re-initialed
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
					if (propertyId.matches("property_[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")) { //$NON-NLS-1$
						if (shapeIdtoClassname == null) {
							shapeIdtoClassname = getShapeIdsAndClassnamesFromDiagram();
						}
						properties.add(getPropertyObjectfromAttribute(allAttr.get(propertyId), diagramId, propertyId, shapeIdtoClassname));
					}
				}
			}
			
			properties.add(new Property());
			tableColumPropertyTemplate.setText(NLSupport.StatementView_TableColumnText + diagramTitle);
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
		    getName = eObj.getClass().getMethod("getName"); //$NON-NLS-1$
		    
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
	 * Returns a new property restored from an attribute.
	 * 
	 * @param propertyString the name of the property
	 * @param diagramId the associated diagramId
	 * @param propertyId the unique property Id
	 * @param shapeIdtoClassname list of editpart-classnames with referenced editpart id of the current diagram
	 * @return Property the restored Property
	 */
	private Property getPropertyObjectfromAttribute(String propertyString, String diagramId, String propertyId, HashMap<String, NodeName> shapeIdtoClassname) {
		Property property = new Property();
		property.setDiagramId(diagramId);
		property.setId(propertyId);
		
		ArrayList<Variable> vars = new ArrayList<>();
		
		if (propertyString.contains("$")) { //$NON-NLS-1$
			
			String[] parts = propertyString.split("-->"); //$NON-NLS-1$
			if (parts.length == 2) {
				String templateString = parts[0];
				String formulaString = parts[1];
				
				String[] words = templateString.split("\\s"); //$NON-NLS-1$
				
				for (int i = 0; i < words.length; i++) {
					
					if (words[i].startsWith("$")) { //$NON-NLS-1$
						while (words[i].startsWith("$")) { //$NON-NLS-1$
							words[i] = words[i].substring(1);
						}
						String classname = null;
						String id;
						if (words[i].matches("^.+?(_)\\d$")) { //$NON-NLS-1$
							id = words[i].substring(0, words[i].length()-2);
						}else {
							id = words[i];
						}
						NodeName nn = shapeIdtoClassname.get(id);
						if (nn != null) {
							classname = nn.getClassname();
						}
						
						if (classname != null) {
						    String variableNumber =""; //$NON-NLS-1$
							if (formulaString.contains(words[i])) {
								Pattern pattern2 = Pattern.compile("(_)\\d$"); //$NON-NLS-1$
								Matcher matcher2 = pattern2.matcher(words[i]);
								while (matcher2.find()) {
									variableNumber = matcher2.group();
								}
							}
							Variable var = property.new Variable(classname + variableNumber, id);
							String shapename = shapeIdtoClassname.get(id).getName();
							if (shapename != null) {
								words[i] = "$" + shapename; //$NON-NLS-1$
							}else {
								words[i] = "$" + words[i]; //$NON-NLS-1$
							}
							vars.add(var);
						}else {
							words[i] = NLSupport.StatementView_ReplacementUnknownVariables1;
							vars.add(property.new Variable(NLSupport.StatementView_ReplacementUnknownVariables2));
						}
					}
					property.setVariables(vars);
				}
				
				StringBuilder builder = new StringBuilder();
				for(String s : words) {
				    builder.append(s);
				    builder.append(" "); //$NON-NLS-1$
				}
				property.setTemplateString(builder.toString().trim());
				formulaString = parts[1];
				property.setFormulaString(formulaString);
			}
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
				ShapeNodeEditPart editPart = (ShapeNodeEditPart) child;
				NodeImpl nodeImpl = (NodeImpl) editPart.getModel();
				EObject eObj = nodeImpl.getElement();
				XMLResource resource = (XMLResource) eObj.eResource();
				String id = resource.getID(eObj);
				String classname = child.getClass().getSimpleName().replace("EditPart", "").toLowerCase(); //$NON-NLS-1$ //$NON-NLS-2$
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
		this.diagramTitle = ""; //$NON-NLS-1$
		tableColumPropertyTemplate.setText(diagramTitle);
		this.diagramId = ""; //$NON-NLS-1$
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
	
	/**
	 * Returns the Id of the current opened diagram
	 * @return id of the current diagram
	 */
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
	
	/**
	 * Implements a ColumnLabelProvider.
	 */
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
						link.setToolTipText(NLSupport.StatementView_ToolTipText_PropertyVariableNotAssigned);
					}else {
						link.setToolTipText(NLSupport.StatementView_ToolTipText_PropertyVariableAssigned);
					}
					
					link.addListener(SWT.Selection, new Listener() {

						@Override
						public void handleEvent(Event event) {
							final int varId = Integer.parseInt(event.text);
							final String variablename = property.getVariable(varId).getClearName();
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
							String newLink = "<a href=\""+ event.text +"\">" + "["+ ">....<" + "]" + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
							String newLinkText = oldLinkText.replaceAll("<a href=\"" + event.text + "\">\\[(.*?)\\]</a>", newLink); //$NON-NLS-1$ //$NON-NLS-2$
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
											String classname = editPart.getClass().getSimpleName().replace("EditPart", ""); //$NON-NLS-1$ //$NON-NLS-2$
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
												
				                				link.setText(link.getText().replace(">....<", replacementString)); //$NON-NLS-1$
				                				link.setToolTipText(NLSupport.StatementView_ToolTipText_PropertyVariableAssigned);
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
						templatesArray[i] = propertyTemplates.get(i);
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
			return ""; //$NON-NLS-1$
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
				return NLSupport.StatementView_ToolTipText_PropertyIsComplete;
			}
			if (column == 0 && !prop.isValid()) {
				return NLSupport.StatementView_ToolTipText_PropertyIsDamaged;
			}
			if (column == 0 && !prop.isComplete()) {
				return NLSupport.StatementView_ToolTipText_PropertyNotComplete;
			}
			if (column == 1 && isLastProperty(prop)) {
				return null;
			}
			if (column == 1 && !isLastProperty(prop)) {
				return NLSupport.StatementView_ToolTipText_PropertyRemove;
			}
			return null;
		}
	}
}
