package org.bflow.toolbox.epc.templating.dialogs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.ModelBuilderAttendantRegistry;
import org.bflow.toolbox.hive.interchange.utils.ModelDigger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;



/**
 * This class represents a bflow-Template. 
 * It provides the connection points between base model and template.
 * 
 * @author Markus Schnaedelbach
 * 
 */
public abstract class BflowTemplate implements Comparable<BflowTemplate>{
	protected static final String DUMMY_FLAG = "dummy"; //$NON-NLS-1$
	protected static final String NORMAL_FLAG = "normal"; //$NON-NLS-1$
	private boolean local; // is a local template?
	private File file; //templatefile
	private String TemplateName;
	private String description = ""; //$NON-NLS-1$
	private HashMap<String, NamingVariable> namingVariables;
	protected HashSet<String> dummyIds = new HashSet<>();

	protected ArrayList<ColoredNodeEditPart> baseModelSources = new ArrayList<>();// base model connection points
	protected ArrayList<ColoredNodeEditPart> baseModelTargets = new ArrayList<>();
	protected HashMap<String,ConnectionType> templateEntries = new HashMap<String,ConnectionType>(); // actual template connection points
	protected HashMap<String,ConnectionType> templateExits = new HashMap<String,ConnectionType>();
	protected HashSet<String> possibleTemplateEntries = new HashSet<>(); // possible template connection points
	protected HashSet<String> possibleTemplateExits = new HashSet<>();
	
	private IModelBuilderAttendant modelBuilderAttendant;
	
	//elements of the template 
	protected ArrayList<IShape> tplShapes;
	protected ArrayList<IEdge> tplEdges;
	
	private IPath tmpProjectRessourceIPath; // Ressourcepath of the template
	private static final String TMP_IMPORT = ".tmp_importBtpl"; //$NON-NLS-1$

	public BflowTemplate(boolean local, File file, String templateName, ArrayList<ColoredNodeEditPart> baseModelSources, ArrayList<ColoredNodeEditPart> baseModelTargets) {
		super();
		this.local = local;
		this.file = file;
		TemplateName = templateName;
		this.baseModelSources = baseModelSources;
		this.baseModelTargets = baseModelTargets;
		initTemplate();		
	}
		
	/**
	 * Parses the TemplateFile and initialized the fields.
	 */
	private void initTemplate() {
		
		findPossibleConnectionPoints();
		
		
		
		
		
		namingVariables = new HashMap<>();
		List<String> captions = new ArrayList<>();
		String epcId= ""; //$NON-NLS-1$
		HashSet<String> eventIds = new HashSet<String>();

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		try {
			InputStream in = new FileInputStream(file.getAbsolutePath());
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement start = (StartElement) event;
					if ("Epc".equals(start.getName().getLocalPart())) { //$NON-NLS-1$
						Attribute id = start.getAttributeByName(new QName("http://www.omg.org/XMI", "id", "xmi")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						if (id != null) {
							epcId = id.getValue();
						}
					}
					if ("elements".equals(start.getName().getLocalPart())) { //$NON-NLS-1$
						Attribute caption = start.getAttributeByName(new QName("name")); //$NON-NLS-1$
						if (caption != null) {
							captions.add(caption.getValue());
						}
						Attribute elementId = start.getAttributeByName(new QName("http://www.omg.org/XMI", "id", "xmi")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						Attribute elementType = start.getAttributeByName(new QName("http://www.omg.org/XMI", "type", "xmi")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						if (elementId != null && elementType != null) {
							String typeValue = elementType.getValue().toLowerCase();							
							if ("epc:event".equals(typeValue)) { //$NON-NLS-1$
								eventIds.add(elementId.getValue());
							}
						}
					}
					if ("attributes".equals(start.getName().getLocalPart())) { //$NON-NLS-1$
						String id = ""; //$NON-NLS-1$
						String name = ""; //$NON-NLS-1$
						String value = ""; //$NON-NLS-1$

						Attribute attriId = start.getAttributeByName(new QName("id")); //$NON-NLS-1$
						if (attriId != null) {
							id = attriId.getValue();
						}
												
						Attribute attriName = start.getAttributeByName(new QName("name")); //$NON-NLS-1$
						if (attriName != null) {
							name = attriName.getValue();
						}
						Attribute attriValue = start.getAttributeByName(new QName("value")); //$NON-NLS-1$
						if (attriValue != null) {
							value = attriValue.getValue();
						}

						if (eventIds.contains(id)) {
							value = initConnectionType(id, name, value);
						}
						
						if ("desc".equals(name.toLowerCase()) && id.equals(epcId)) { //$NON-NLS-1$
							this.description = value;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String caption : captions) {
			List<NamingVariable> variableNames = getVariableNamesFromCaption(caption);
			for (NamingVariable variable : variableNames) {
				namingVariables.put(variable.getName(), variable);
			}
		}
		initConnectionTypesWithoutFlag();
	}

	
	/**
	 * Returns if the current connection points are valid for the desired action
	 * @return boolean
	 */
	protected abstract boolean isValidForAction();
	
	/**
	 * Fills the HashMap-fields templateSources and templateTargets under consideration of flags.
	 * @param id
	 * @param name
	 * @param value
	 * @return
	 */
	protected abstract String initConnectionType(String id, String name, String value);
	
	
	/**
	 * Fills the HashMap-fields templateSources and templateTargets under consideration without flags.
	 */
	protected abstract void initConnectionTypesWithoutFlag();
	
	/**
	 * Finds possible Connectionspoints and init the relevant Templatefields
	 */
	protected abstract void findPossibleConnectionPoints() ;

	
    /**
     * Removes all non-connectable IShapes like participant from the given
     * ArrayList 
     * @param templateEntryElements
     */
    protected void removeNotConnectableElements(ArrayList<IShape> templateEntryElements) {
            for (Iterator<IShape> iterator = templateEntryElements.iterator(); iterator.hasNext();) {
                    IShape shape = iterator.next();
                    if (!isConnectable(shape.getType().toString())) {
                            iterator.remove();
                    }
            }
    }

    /**
     * Checks if the given typeid-string the id of Event, Function or a connector
     * @return boolean
     */
    private boolean isConnectable(String typeid) {
            if (typeid.equals("org.bflow.toolbox.epc.Function")) { //$NON-NLS-1$
                    return true;
            }

            if (typeid.equals("org.bflow.toolbox.epc.Event")) { //$NON-NLS-1$
                    return true;
            }

            if (typeid.equals("org.bflow.toolbox.epc.AND")) { //$NON-NLS-1$
                    return true;
            }

            if (typeid.equals("org.bflow.toolbox.epc.OR")) { //$NON-NLS-1$
                    return true;
            }

            if (typeid.equals("org.bflow.toolbox.epc.XOR")) { //$NON-NLS-1$
                    return true;
            }
            return false;
    }
			
	/**
	 * Gets a List of contained naming variables. 
	 * @param caption
	 * @return
	 */
	private List<NamingVariable> getVariableNamesFromCaption(String caption) {
		ArrayList<NamingVariable> variables = new ArrayList<>();
		
		if (caption.contains("$")) { //$NON-NLS-1$
			String[] words = caption.split(" "); //$NON-NLS-1$
			for (String word : words) {
				
				//Listenvariable
				if (word.startsWith("$") && word.endsWith("]")) { //$NON-NLS-1$ //$NON-NLS-2$
					NamingVariable nvar = new NamingVariable(word,word.substring(0, word.indexOf("[")), true); //$NON-NLS-1$
					String options = word.substring(word.indexOf("[")+1,word.indexOf("]")); //$NON-NLS-1$ //$NON-NLS-2$
					nvar.setOptions(options.split(",")); //$NON-NLS-1$
					variables.add(nvar);
				}
				//einfache Variable
				if (word.startsWith("$") && !word.endsWith("]")) { //$NON-NLS-1$ //$NON-NLS-2$
					variables.add(new NamingVariable(word,word, false));
				}
			}
		}
		return variables;
	}

	/**
	 * Sets a new value of a specified naming variables (id).
	 * @param id
	 * @param value
	 */
	protected void updateNamingVarEntry(String id, String value) {
		NamingVariable var = namingVariables.get(id);
		var.setValue(value);
	}
		
	/**
	 * Returns true if the order of event and function is right. (forwards in the epc tree)
	 * @param connector
	 * @param function - is start point a function?
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean isValidStartOrder(ColoredNodeEditPart connector, boolean function) {
		List<ColoredConnectionEditPart> targetConnections = connector.getTargetConnections();// eingehende
		if (countArcs(targetConnections) >= 1) {
			ColoredNodeEditPart source = (ColoredNodeEditPart) targetConnections.get(0).getSource();
			// Solange bis eine Function oder Ereignis gefunden ist
			while (true) {
				if (source instanceof FunctionEditPart && function) {
					return true;
				}
				if (source instanceof EventEditPart && !function) {
					return true;
				}
				if (source instanceof FunctionEditPart || source instanceof EventEditPart) {
					return false;
				}
				targetConnections = source.getTargetConnections();
				source = (ColoredNodeEditPart) targetConnections.get(0).getSource();
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the order of event and function is right. (backwards in the epc tree)
	 * @param connector
	 * @param function - is start point a function?
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean isValidEndOrder(ColoredNodeEditPart connector, boolean function) {
		List<ColoredConnectionEditPart> sourceConnections = connector.getSourceConnections();// ausgehende
		if (countArcs(sourceConnections) >= 1) {
			ColoredNodeEditPart target = (ColoredNodeEditPart) sourceConnections.get(0).getTarget();
			// Solange bis eine Function oder Ereignis gefunden ist
			while (true) {
				if (target instanceof FunctionEditPart && function) {
					return true;
				}
				if (target instanceof EventEditPart && !function) {
					return true;
				}
				if (target instanceof FunctionEditPart || target instanceof EventEditPart) {
					return false;
				}
				sourceConnections = target.getSourceConnections();
				target = (ColoredNodeEditPart) sourceConnections.get(0).getTarget();
			}

		}
		return false;
	}
	
	/**
	 * Returns the number of the contained connections.
	 * @param connections
	 * @return
	 */
	protected int countArcs(List<ColoredConnectionEditPart> connections) {
		int count = 0;
		for (ColoredConnectionEditPart arc : connections) {
			if(arc instanceof ArcEditPart){
				count++;
			}
		}
		return count;
	}

	/**
	 * Gets a new RessourcePath of the template.
	 * @return
	 */
	public IPath getTmpProjectRessourceIPath() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TMP_IMPORT);
		try {
			if (project.exists()) {

				project.delete(true, true, null);
			}
			project.create(null);
			project.open(null);
			File tempFile = project.getLocation().append("temp.epc").toFile(); //$NON-NLS-1$
			Files.copy(file.toPath(),tempFile.toPath(),  StandardCopyOption.REPLACE_EXISTING);
			
			IPath location = new Path(tempFile.getAbsolutePath());
			IFile templateProjectIFile = project.getFile(location.lastSegment());
			templateProjectIFile.createLink(location, IResource.NONE, null);
			tmpProjectRessourceIPath = project.getLocation().append(templateProjectIFile.getName());
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpProjectRessourceIPath;
	}
	
	/**
	 * Removes the temp ressource of the template.
	 * @return
	 */
	protected void removeTmpProjectRessource() {
		
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TMP_IMPORT);
			if (project.exists()) {
				try {
					project.delete(true, true, null);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		deleteDirectory(new File(workspacePath+"/"+TMP_IMPORT));
	}
	
	private static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	
	/**
	 * Parses all edges and shapes of the template and stores it in the tplShapes and tplEdges fields.
	 */
	protected void loadModelData() {
		String diagramType = "epc"; //$NON-NLS-1$
		IModelData modelData = ModelDigger.resolveModelData(getTmpProjectRessourceIPath().toFile());
		modelBuilderAttendant = ModelBuilderAttendantRegistry.getModelBuilderFor(diagramType);
		//Notizzettel werden derzeit nicht unterstützt!!!
		tplShapes = removeNoteShapes(new ArrayList<IShape>(Arrays.asList(modelData.getShapes())));
		tplEdges = removeNoteEdges(new ArrayList<IEdge>(Arrays.asList(modelData.getEdges())));
	}
	
	/**
	 * Removes all NoteEdges of the given list.
	 * Because currently NoteShapes will not supported by templating. 
	 * @param list with edges
	 * @return list without NoteEdges
	 */
	private ArrayList<IEdge> removeNoteEdges(ArrayList<IEdge> edges) {
		for (Iterator<IEdge> edgeIter = edges.iterator(); edgeIter.hasNext();) {
			IEdge edge = edgeIter.next();
			try {
				edge.getType();
			} catch (NullPointerException ne) {
				edgeIter.remove();
				System.err.println("Nicht unterstützer Notizzettel im Template wurde entfernet"); //$NON-NLS-1$
			}
		}
		return edges;
	}

	/**
	 * Removes all NoteShapes of the given list.
	 * Because currently NoteShapes will not supported by templating. 
	 * @param list with edges
	 * @return list without NoteShapes
	 */
	private ArrayList<IShape> removeNoteShapes(ArrayList<IShape> shapes) {
		for (Iterator<IShape> shapeIter = shapes.iterator(); shapeIter.hasNext();) {
			IShape shape = shapeIter.next();
			if (shape.getType().toString().equals("org.eclipse.gmf.runtime.notation.Shape+Note")) { //$NON-NLS-1$
				shapeIter.remove();
			}	
		}
		return shapes;
	}
	
	public boolean isLocal() {
		return local;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public String getFileName() {
		return file.getName();
	}
	
	public Collection<NamingVariable> getNamingVariables() {
			return namingVariables.values();		
	}
	
	protected ColoredNodeEditPart getFirstBaseModelSource(){
		for (ColoredNodeEditPart node : baseModelSources) {
			return node;
		}
		return null;
	}
	
	protected ColoredNodeEditPart getFirstBaseModelTarget(){
		for (ColoredNodeEditPart node : baseModelTargets) {
			return node;
		}
		return null;
	}

	protected String getDescription() {
		return description;
	}

	public ArrayList<IShape> getShapes() {
		if (tplShapes == null) {
			loadModelData();
		}
		return tplShapes;
	}

	public ArrayList<IEdge> getEdges() {
		if (tplEdges == null) {
			loadModelData();
		}
		return tplEdges;
	}
	
	public IModelBuilderAttendant getModelBuilderAttendant() {
		if (modelBuilderAttendant == null) {
			loadModelData();
		}
		return modelBuilderAttendant;
	}
	
	public TemplateAction getAction() {
		if (this instanceof BflowTemplateBefore) {
			return TemplateAction.before;
		}
		if (this instanceof BflowTemplateAfter) {
			return TemplateAction.after;
		}
		if (this instanceof BflowTemplateReplace) {
			return TemplateAction.insert;
		}
		return null;
	}

	protected ConnectionType getFirstEndPointType() {
		for (ConnectionType type : templateExits.values()) {
			return type;
		}
		return ConnectionType.unknown;
	}
	
	protected String getFirstEndPointId(){
		for (String id : templateExits.keySet()) {
			return id;
		}
		return ""; //$NON-NLS-1$
	}

	protected String getFirstStartPointId() {
		for (String id : templateEntries.keySet()) {
			return id;
		}
		return ""; //$NON-NLS-1$
	}

	protected ConnectionType getFirstStartPointType() {
		for (ConnectionType type : templateEntries.values()) {
			return type;
		}
		return ConnectionType.unknown;
	}
	
	protected boolean isDummyId(String id) {
		return this.dummyIds.contains(id);
	}
	
	protected boolean allNamingVariablesHaveValue() {
		
		for ( NamingVariable nvar : namingVariables.values()) {
			if ("".equals(nvar.getValue())) { //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(BflowTemplate t) {
		return this.getTemplateName().compareTo(t.getTemplateName());
	}
	
	
	/**
	 * Represents a NAmingvariable of a Template
	 * @author Markus Schnaedelbach
	 */
	protected class NamingVariable{
		private String id;
		private String value = ""; //$NON-NLS-1$
		private String[] options; 
		private String name;
		
		public NamingVariable(String id, String name, boolean isList) {
			super();
			this.name = name;
			this.id = id;			
		}
		
		public String getName() {
			return name;
		}

		public String getValue(){
			return value;
			
		}
		private void setValue(String value) {
			this.value = value;
		}

		public String[] getOptions() {
			return options;
		}

		public void setOptions(String[] options) {
			this.options = options;
		}

		public String getId() {
			return id;
		}
	}


	protected boolean isConnectNode(String nodeId) {
		if (this.templateEntries.containsKey(nodeId)) {
			return true;
		}
		if (this.templateExits.containsKey(nodeId)) {
			return true;
		}
		return false;
	}
}
