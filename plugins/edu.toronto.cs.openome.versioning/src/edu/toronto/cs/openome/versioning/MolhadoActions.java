package edu.toronto.cs.openome.versioning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;


import sc.document.Component;
import sc.document.Configuration;
import sc.document.GoalModel;
import sc.document.SCDirectory;

import edu.toronto.cs.openome_model.*;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.presentation.openome_modelEditor;

import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.version.Version;

/**
 * Groups common versioning actions and acts as interface to Molhado repository
 * Singleton using Java 1.5 memory model: see http://en.wikipedia.org/wiki/Singleton_pattern#Java_5_solution
 * @author Neil (moved and refactored)
 * @author Yijun (code originally in openome.editor classes)
 *
 */
public class MolhadoActions {
	
	//fields
	private static volatile MolhadoActions INSTANCE;

	Hashtable<GoalModel, Hashtable<String, IRNode>> tables = new Hashtable<GoalModel, Hashtable<String, IRNode>>();
	Hashtable<GoalModel, Hashtable<Intention, IRNode>> diagram_tables = new Hashtable<GoalModel, Hashtable<Intention, IRNode>>();
	
	private Hashtable<String, Integer> versions = new Hashtable<String, Integer>();
	Hashtable<String, Intention> nodes;
	private static final Logger LOG = Logger.getLogger("GoalmodelEditor"); //added field
	final static String MOLHADO_PLUGIN_NAME = "molhado";

	openome_modelPackage e = openome_modelPackage.eINSTANCE;
	openome_modelFactory f = e.getopenome_modelFactory();
	/** indexed on project_name e.g. 'Examples' */
	HashSet<IRNode> set;
	public Hashtable<String, Configuration> configurations = new Hashtable<String, Configuration>();
	
	/**
	 * Singleton constructor - prevent instantiation via 'new'
	 */
	private MolhadoActions() {	} 
	
	/**
	 * @author nernst
	 * singleton creator method
	 */
	public static MolhadoActions getInstance() {
	     if (INSTANCE == null)
	       synchronized(MolhadoActions.class) {
	         if (INSTANCE == null)
	           INSTANCE = new MolhadoActions();
	       }
	     return INSTANCE;
	   }
	
//*********************** Util classes **********/
	/**
	 * Get the URI of the file.
	 */
	public static URI fetchURI(String name) {
		File file = new File(name);
		URI uri = file.isFile() ? URI.createFileURI(file.getAbsolutePath())
				: URI.createURI(name);
		return uri;
	}

	public static String getPluginInstallPath() {		
		URL url = FileLocator.find(
				Platform.getBundle(MOLHADO_PLUGIN_NAME), new Path("repository"), null);
		try {
			url = FileLocator.resolve(url);
			return url.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
//*********************** These are common to both the Diagram and Editor commands

	public void checkout_last_version(Configuration config, String model_name, int version) {
		Integer last = versions.get(model_name);
		if(last == null) last = new Integer(version); //perhaps the versions map has not been initialized properly
		if (last != null) {
			String last_version = model_name + "-v" + last.intValue();
			checkout_version(config, last_version);
		} else {
			System.err.println("expecting to have an initial version number for the configuration "	+ config.getName());
		}
	}

	protected void checkout_version(Configuration config, String v1_name) {
		if(config == null) System.out.println("no config in checkout..");
		assert (v1_name != null);
		try {
			Version v1 = config.loadVersionByDelta(v1_name,	IRPersistent.fluidFileLocator);
			Version.setVersion(v1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// deprecated in favour of next method.
	@Deprecated
	 public void checkOutAllVersionsFromRepository(Configuration config) {
		System.err.println("Use checkoutallversionsfromrepository(name, config) instead");
		checkOutAllVersionsFromRepository(config, null);
	}

	 /** 
	  * @author yijun
	  * @param config
	  * @param modelName
	  * // check out all the available versions of a given model
	  */
	public void checkOutAllVersionsFromRepository(Configuration config, String modelName) {
		java.util.Enumeration<String> vs = config.getAllVersionNames();
		String model_name = modelName;
		while (vs.hasMoreElements()) {
			String v1_name = (String) vs.nextElement();					
			if (v1_name.substring(0, v1_name.lastIndexOf("-")).equals(model_name)) { //get all versions for the model of interest.
				checkout_version(config, v1_name);
				unparse_checkout_into_emf(config, v1_name);
			}
		}
	}
	
	public void checkin_current_version(Configuration config, String model_name) {
		Version v1 = Version.getVersion();
		Version.setVersion(v1);
		try {
			incrementVersion(config, v1, model_name);
			config.saveVersionByDelta(v1, IRPersistent.fluidFileLocator);
			config.storeASCII(new PrintWriter(new BufferedWriter(
					new FileWriter(System.getProperty("fluid.ir.path") + "/"
							+ config.getName() + ".cfg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkInGoalModel(String fileName, String modelName, Configuration config, ResourceSet res) {
		IRNode rootnode = config.getRoot();
		fluid.tree.Tree tree = Configuration.getTree();
		// Create a goal model
		//String model_name = modelName;
		GoalModel gm = new GoalModel();
		gm.setName(fileName); 
		IRNode gm_node = config.newComponent(gm);
		tree.appendChild(rootnode, gm_node);
		buildGoalModel(gm, res);
		checkin_current_version(config, modelName);
	}
	
	@Deprecated
	public void checkInGoalModel(Configuration config) {
		System.err.println("Use checkinGoalModel(name, config, ResourceSet) instead");
		//checkInGoalModel(null,config, null); //should generate error
	}
	
	public void incrementVersion(Configuration config, Version v1, String model_name) {
		if (versions.get(model_name) == null) {
			update_version(model_name, 0);
		}
		int versionNumber = versions.get(model_name).intValue() + 1;
		update_version(model_name, versionNumber);
		String v1_name = model_name + "-v" + versionNumber;
		config.assignVersionName(v1, v1_name);
		//System.err.println("Saving " + v1_name);
	}

	/**
	 * @param config
	 * @param v1_name
	 */
	public void unparse_checkout_into_emf(Configuration config, String v1_name) {
		fluid.tree.Tree project_tree = Configuration.getTree(); 
		IRNode project_root = config.getRoot();
		
		Enumeration<IRNode> en = project_tree.depthFirstSearch(project_root);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			Component comp = config.getComponent(node);
			if (comp instanceof GoalModel) {
				GoalModel gm = (GoalModel) comp;
				String name = gm.getName();
				v1_name = v1_name.substring(v1_name.lastIndexOf("-") + 1);
				String model_v1 = name + "-" + v1_name + ".oom";
				URI uri = fetchURI(model_v1);
				Resource resource = resourceSet.createResource(uri); //uri determines where file is stored
				Model m = f.createModel();
				try {
					IRNode root = gm.getRoot();
					nodes = new Hashtable<String, Intention>();
					traverseGMResourcesForNodes(gm, m, root, null);
					System.out.println("traversing");
					traverseGMResourcesForEdges(gm, m, root, null);
					resource.getContents().add(m);
					resource.save(Collections.EMPTY_MAP);
					resource.unload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @author Yijun Yu
	 * @param gm
	 * Build a mirror of the oom goal model into the Molhado GoalModel structure
	 */
	public void buildGoalModel(GoalModel gm, ResourceSet resourceSet) {
		Hashtable<String, IRNode> table = new Hashtable<String, IRNode>();
		tables.put(gm, table);
		IRNode root = gm.createAGoal("VIRTUAL_ROOT", true);
		gm.setRoot(root);
		XMIResourceImpl xmires = null;
		for(Resource tmp: resourceSet.getResources()) {
			if (tmp instanceof XMIResourceImpl) {
				xmires = (XMIResourceImpl) tmp;
			}
		}
		ModelImpl model = null;
		for(EObject tmp2: xmires.getContents()){ 
			if (tmp2 instanceof ModelImpl) 
				model = (ModelImpl) tmp2; 
		}
		// the first pass creates nodes
		for(EObject o: model.getIntentions()) {
			
			if (o instanceof Intention) {
				Intention i = (Intention) o;
				String name = i.getName();
				if(name == null) name = "undefined";
				IRNode g = gm.createAGoal(name, o instanceof Goal);
				if (i.getParentDecompositions().size() == 0) {
					IRNode e = gm.createEdge("virtual");
					gm.connect(root, g, e);
				}
				//System.out.println(name + " " + g);
				table.put(name, g);
			}
		}
		// the second pass creates edges
		for(EObject o: model.getContributions()) {
			if (o instanceof Contribution) {
				Contribution i = (Contribution) o;
				if (i.getSource()==null || i.getTarget()==null)
					continue;
				String source_name = i.getSource().getName();
				String target_name = i.getTarget().getName();
				String label = "";
				if (i instanceof HelpContribution)
					label = "+";
				if (i instanceof MakeContribution)
					label = "++";
				if (i instanceof HurtContribution)
					label = "-";
				if (i instanceof BreakContribution)
					label = "--";
				IRNode e = gm.createEdge(label);
				IRNode source = table.get(source_name);
				IRNode target = table.get(target_name);
				gm.connect(source, target, e);
				//System.out.println("Label: " + label);
			}
		}
		for(EObject o: model.getDecompositions()) {
			if (o instanceof Decomposition) {
				Decomposition i = (Decomposition) o;
				if (i.getSource()==null || i.getTarget()==null)
					continue;
				String source_name = i.getSource().getName();
				String target_name = i.getTarget().getName();
				String label = "";
				if (o instanceof AndDecomposition)
					label = "AND";
				if (o instanceof OrDecomposition)
					label = "OR";
				IRNode e = gm.createEdge(label);
				IRNode source = table.get(source_name);
				IRNode target = table.get(target_name);
				gm.connect(source, target, e);
				//System.out.println("Label: " + label);
			}
		}
	}

	/**
	 * We need to make sure after checkout, the mapping table between goal names
	 * and goals in the GoalModel is updated
	 * FIXME this seems a painfully slow way to do this
	 * 
	 * @param gm
	 */
	public void updateIndex(GoalModel gm, IRNode root) {
		if (root == null)
			return;
		String name = gm.getGMNodeName(root);
		name = name.substring(name.indexOf("_") + 1);
		Hashtable<String, IRNode> table = tables.get(gm);
		if (table == null) {
			table = new Hashtable<String, IRNode>();
			tables.put(gm, table);
		}
		table.put(name, root);
		int numChildren = GoalModel.graph.numChildren(root);
		for (int i = 0; i < numChildren; i++) {
			IRNode childnode = GoalModel.graph.getChild(root, i);
			updateIndex(gm, childnode);
		}
	}
	
	public void update_version(String model_name, int versionNumber) {
		versions.put(model_name, new Integer(versionNumber));
		//System.err.println(model_name + " = " + versionNumber);
	}
	
	public Configuration loadConfig(String project_name, String property) {
		try {
			Configuration config = Configuration.loadASCII(new FileReader(property + "/" + project_name + ".cfg"), IRPersistent.fluidFileLocator);
			return config;
		} catch (IOException e) {
			//file not found or summat
			return null;
		}
	}
	
	/**
	 * If the model is opened for the first time, it will be assigned version 0
	 * and will be checked in and then checked out before the doSave operation.
	 * Otherwise, the model is assigned a newer version and will then checked
	 * out before the doSave operation.
	 * 
	 * Tien, I don't know if that's correct though: one must assume that the
	 * model can be changed outside the editor! ). Alternatively we may
	 * implement a checkout for an existing goal model Vn rather than parse it
	 * from the EMF file, and such a goal model will be saved to override the v0
	 * model, but it does not work yet :-(
	 * ALGORITHM 3 from ICSE paper
	 */
	public  void checkLatestVersion(String model_name, String project_name, String file_name, ResourceSet res) { //must set properties in CH first.
		LOG.setLevel(Level.ERROR);		
		org.apache.log4j.Logger.getLogger("IR").setLevel(Level.ERROR);
		String property = getMolhadoProperty();	
		Configuration config = loadConfig(project_name, property);
		if(config != null) { //we found an existing 'repository' aka a file in the specified molhado directory			
			int versionNumber = getVersion(project_name, model_name, config);
			configurations.put(project_name, config);
			if (versionNumber == 0) {
				checkInGoalModel(file_name, model_name, config, res);
				update_version(model_name, 1);
			} else
				update_version(model_name, versionNumber);
		} else {
			SCDirectory project_root = connectToRepository(file_name);
			Configuration newConfig = new Configuration(project_name, project_root);
			// create a new goal model and check it in
			checkInGoalModel(file_name,model_name, newConfig, res);
			configurations.put(project_name, newConfig);
			update_version(model_name, 1);
		}
	}
	HashMap<String, Integer> exist = new HashMap<String,Integer>();
	/*
	 * nodes will be populated
	 */
	public void traverseGMResourcesForNodes(GoalModel gm, Model m, IRNode root, IRNode edgenode) {
		if (root == null)
			return;
		String name = gm.getGMNodeName(root);
		String prefix = name.substring(0, name.indexOf("_"));
		name = name.substring(name.indexOf("_") + 1);
		Intention g;
		//the above is a hacky way to make the checkout work properly, since it currently is duplicating nodes for some reason
		if (!name.equals("VIRTUAL_ROOT")) { //TODO given a virtual root first...
			if(exist.containsKey(name)) return; //we've already processed this node.
			if (prefix.equals("HardGoal"))
				g = f.createGoal();
			else 
				g = f.createSoftgoal();
			g.setName(name);
			exist.put(name, 1);
			System.out.println("traversed node: " + name);
			m.getIntentions().add(g);
			nodes.put(name, g);
		}
		int numChildren = GoalModel.graph.numChildren(root);
		for (int i = 0; i < numChildren; i++) {
			IRNode childedgenode = GoalModel.graph.getChildEdge(root, i);
			IRNode childnode = GoalModel.graph.getChild(root, i);
			traverseGMResourcesForNodes(gm, m, childnode, childedgenode);
		}
	}

	/**
	 * nodes will be used
	 * 
	 * @param gm
	 * @param f
	 * @param m
	 * @param root
	 * @param edgenode
	 */
	public void traverseGMResourcesForEdges(GoalModel gm, Model m, IRNode root, IRNode edgenode) {
		if (root == null)
			return;
		String name = gm.getGMNodeName(root);
		name = name.substring(name.indexOf("_") + 1);
		Intention g = null;
		if (!name.equals("VIRTUAL_ROOT")) {
			g = nodes.get(name);
		}
		int numChildren = GoalModel.graph.numChildren(root);
		for (int i = 0; i < numChildren; i++) {
			IRNode childedgenode = GoalModel.graph.getChildEdge(root, i);
			IRNode childnode = GoalModel.graph.getChild(root, i);
			String namec = gm.getGMNodeName(childnode);
			namec = namec.substring(namec.indexOf("_") + 1);
			Intention gc = nodes.get(namec);
			if (childedgenode != null) {
				String edgetype = gm.getGMNodeName(childedgenode);
				String pre = edgetype.substring(edgetype.indexOf("_") + 1);
				if (pre.equals("AND")) {
					AndDecomposition d = f.createAndDecomposition();
					d.setSource(g);
					d.setTarget(gc);
					m.getDecompositions().add(d);
				} else if (pre.equals("OR")) {
					OrDecomposition d = f.createOrDecomposition();
					d.setSource(g);
					d.setTarget(gc);
					m.getDecompositions().add(d);
				} else if (pre.equals("+")) {
					Contribution c = f.createHelpContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else if (pre.equals("++")) {
					Contribution c = f.createMakeContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else if (pre.equals("-")) {
					Contribution c = f.createHurtContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else if (pre.equals("--")) {
					Contribution c = f.createBreakContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else if (pre.equals("Some-")) {
					Contribution c = f.createSomeMinusContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else if (pre.equals("Some+")) {
					Contribution c = f.createSomePlusContribution();
					c.setSource(g);
					c.setTarget(gc);
					m.getContributions().add(c);
				} else /* VIRTUAL_EDGE */ {
					// do nothing
				}
			}
			traverseGMResourcesForEdges(gm, m, childnode, childedgenode);
		}
	}
	
	public SCDirectory connectToRepository(String model_name) {
		Configuration.ensureLoaded();
		SCDirectory root = new SCDirectory();
		root.setName(model_name);
		return root;
	}

	/**
	 * 
	 * @param file_name - the file name to look for
	 * @param config - the repository to search
	 * @return
	 */
	public GoalModel find_the_gm(String file_name, Configuration config) {
		GoalModel the_gm = null;
		fluid.tree.Tree tree = Configuration.getTree();
		IRNode rootnode = config.getRoot();
		Enumeration<IRNode> en = tree.depthFirstSearch(rootnode);
		while (en.hasMoreElements()) {
			IRNode node = en.nextElement();
			Component comp = config.getComponent(node);
			if (comp instanceof GoalModel) {
				GoalModel gm = (GoalModel) comp;
				// System.out.println(gm.getName());
				if (gm.getName().equals(file_name)) {
					the_gm = gm;
					break;
				}
			}
		}
		return the_gm;
	}
	
	/** 
	 * copied from DiagramActions
	 * @param gm
	 * @param resource
	 */
	public void modify_edited_diagram_goal_model(GoalModel gm, Resource resource) {
		Hashtable<Intention, IRNode> table = get_mapping_table(gm);
		IRNode root = mirror_intention_nodes(gm, resource, table);
		mirror_links_to_edges(gm, resource, table, root);
	}

	protected Hashtable<Intention, IRNode> get_mapping_table(GoalModel gm) {
		Hashtable<Intention, IRNode> table = diagram_tables.get(gm);
		if (table == null) {
			table = new Hashtable<Intention, IRNode>();
			diagram_tables.put(gm, table);
		}
		return table;
	}
	
	protected IRNode mirror_intention_nodes(GoalModel gm, Resource resource,
			Hashtable<Intention, IRNode> table) {
		HashSet<String> set = new HashSet<String>();
		IRNode root = gm.getRoot();
		// rename nodes
		HashSet<IRNode> renamed = new HashSet<IRNode>();
		HashSet<String> to_add = new HashSet<String>();
		Hashtable<String, Boolean> to_add_type = new Hashtable<String, Boolean>();
		rename_mirrored_nodes(gm, resource, table, set, root, renamed, to_add,
				to_add_type);
		add_mirrored_nodes(gm, root, to_add, to_add_type);
		delete_unmirrored_nodes(gm, table, set, root);
		return root;
	}
	
	protected void add_mirrored_nodes(GoalModel gm, IRNode root,
			HashSet<String> to_add, Hashtable<String, Boolean> to_add_type) {
		System.err.println("added size= " + to_add.size());
		for (String name : to_add) {
			Boolean is_hardgoal = to_add_type.get(name);
			IRNode g = gm.createAGoal(name, is_hardgoal);
			IRNode vedge = gm.createEdge("virtual");
			gm.connect(root, g, vedge);
			//System.out.println("add " + (is_hardgoal ? "Goal" : "Softgoal") + ":" + name);
			//			System.err.println("added name: " + name);
		}
	}
	
	protected void delete_unmirrored_nodes(GoalModel gm,
			Hashtable<Intention, IRNode> table, HashSet<String> set, IRNode root) {
		// really old nodes removed from table
		HashSet<IRNode> delete = new HashSet<IRNode>();
		//		System.err.println("renamed and added names: " + set.size());			
		//		for (String name: set) {
		//			System.err.println("name to compare: " + name);			
		//		}
		int numChildren = GoalModel.graph.numChildren(root);
		for (int i = 0; i < numChildren; i++) {
			IRNode g = (IRNode) GoalModel.graph.getChild(root, i);
			String name = get_goal_name(gm, g);
			//String type = get_goal_type(gm, g);
			//			System.err.println("compare name: " + name);
			if (!set.contains(name)) {
				//System.out.println("delete " + (type + ":" + name));
				delete.add(g);
			}
		}
		System.err.println("deleted size= " + delete.size());
		for (IRNode g : delete) {
			GoalModel.graph.removeNode(g);
			table.remove(g);
		}
	}
	
	protected void rename_mirrored_nodes(GoalModel gm, Resource resource,
			Hashtable<Intention, IRNode> table, HashSet<String> set,
			IRNode root, HashSet<IRNode> renamed, HashSet<String> to_add,
			Hashtable<String, Boolean> to_add_type) {
		for (TreeIterator<EObject> r = resource.getAllContents(); r.hasNext();) {
			Object o = r.next();
			if (o instanceof Intention) {
				Intention i = (Intention) o;
				// origin analysis first for renaming operation:
				// YY: if the table is still inside memory, chances are the element is still live in memory
				//     thus can be found by it's object id
				//boolean origin_found = false;
				IRNode g = table.get(i);
				if (g != null) {
					String name = get_goal_name(gm, g);
					set.add(name);
					if (!name.equals(i.getName())) {
						if (i instanceof Goal)
							gm.setHardGoalName(g, i.getName());
						else if (i instanceof Softgoal) {
							gm.setSoftGoalName(g, i.getName());
						}
						renamed.add(g);
						table.remove(name);
						table.put(i, g);
						continue;
					}
				} else {
					String name = i.getName();
					// fixing origin
					int numChildren = GoalModel.graph.numChildren(root);
					Boolean found = false;
					for (int j = 0; j < numChildren; j++) {
						IRNode g1 = (IRNode) GoalModel.graph.getChild(root, j);
						String name1 = get_goal_name(gm, g1);
						if (name.equals(name1)) {
							found = true;
							table.put(i, g1);
							break;
						}
					}
					if (!found) { // really new nodes 
						to_add.add(name);
						to_add_type.put(name, i instanceof Goal);
					}
					set.add(name);
				}
			}
		}
		System.err.println("renamed size= " + renamed.size());
	}
	
	protected String get_goal_name(GoalModel gm, IRNode g) {
		if (g == null)
			return null;
		String name = gm.getGMNodeName(g);
		//String prefix = name.substring(0, name.indexOf("_"));
		name = name.substring(name.indexOf("_") + 1);
		return name;
	}

	protected String get_goal_type(GoalModel gm, IRNode g) {
		if (g == null)
			return null;
		String name = gm.getGMNodeName(g);
		String prefix = name.substring(0, name.indexOf("_"));
		return prefix;
	}

	protected String get_edge_type(GoalModel gm, IRNode g) {
		String name = gm.getGMNodeName(g);
		String prefix = name.substring(name.indexOf("_") + 1);
		return prefix;
	}
	
	private void mirror_links_to_edges(GoalModel gm, Resource resource, Hashtable<Intention, IRNode> table, IRNode root) {
		//HashSet<IRNode> alledges = getAllEdges(gm, root); //TODO too friggin recursive!
		
		HashSet<IRNode> existing_edges = new HashSet<IRNode>();
		List<Decomposition> decomps = null;
		List<Contribution> contribs = null; //list of possible relations pulled from the EMF representation
		int count = 0;
		try {
			ModelImpl modelimpl = (ModelImpl) resource.getContents().get(0); //should be instance of "ModelImpl" as top level element
			decomps = (List<Decomposition>) modelimpl.getDecompositions();
			contribs = modelimpl.getContributions();
			int numedges = decomps.size() + contribs.size(); //hacky for ICSE 
			System.err.println("all edges = " + numedges);
		} catch (Exception e) { 
			System.out.println("empty Resource contents");
		}
		for (Contribution c : contribs) { //TODO does the foreach iterator check for null lists?
				Intention s = c.getSource();
				Intention t = c.getTarget();
				if(s == null || t == null) continue;
				IRNode n_s = table.get(s); //source's representation in Molhado
				IRNode n_t = table.get(t);
				if (n_s != null && n_t != null) {
					boolean found = false;
					int numChildren = GoalModel.graph.numChildren(n_s);
					for (int j = 0; j < numChildren; j++) {
						IRNode node = (IRNode) GoalModel.graph.getChild(n_s, j);
						IRNode edge = (IRNode) GoalModel.graph.getChildEdge(n_s, j);
						String type = gm.getGMNodeName(edge);
						if (node == n_t	
								&& (type.equals("+")&& c instanceof HelpContribution 
								|| type.equals("++")&& c instanceof MakeContribution
								|| type.equals("-") && c instanceof HurtContribution
								|| type.equals("--")&& c instanceof BreakContribution
								|| type.equals("Some-")&& c instanceof SomeMinusContribution
								|| type.equals("Some+")&& c instanceof SomePlusContribution)) { // existing edge
							//							System.out.println("FOUND!");
							found = true;
							existing_edges.add(edge);
							break;
						}
					}
					if (!found) {
						IRNode edge = insert_an_edge(gm, table, c, s, t);
						if (edge != null) existing_edges.add(edge);
						else	count++;
					}
				} else {
					IRNode edge = insert_an_edge(gm, table, c, s, t);
					if (edge != null) existing_edges.add(edge);
					else {
						System.out.println("Not inserted edge: " + s.getName() 	+ " " + t.getName());
						count++;
					}
				}
		}
		for( Decomposition c: decomps) {
			Intention s = c.getSource();
			Intention t = c.getTarget();
			if(s == null || t == null) continue;
			IRNode n_s = table.get(s);
			IRNode n_t = table.get(t);
			if (n_s != null && n_t != null) {
				boolean found = false;
				int numChildren = GoalModel.graph.numChildren(n_s);
				for (int j = 0; j < numChildren; j++) {
					IRNode node = (IRNode) GoalModel.graph.getChild(n_s, j);
					IRNode edge = (IRNode) GoalModel.graph.getChildEdge(n_s, j);
					String type = get_edge_type(gm, edge);
					if (node == n_t
							&& (type.equals("AND")
									&& c instanceof AndDecomposition || type
									.equals("OR")
									&& c instanceof OrDecomposition)) { // existing edge
						found = true;
						existing_edges.add(edge);
						break;
					}
				}
				if (!found) {
					IRNode edge = insert_an_edge(gm, table, c, s, t);
					if (edge != null) {
						existing_edges.add(edge);
					} else {
						count++;
					}
				}
			} else {
				IRNode edge = insert_an_edge(gm, table, c, s, t);
				if (edge != null) {
					existing_edges.add(edge);
				} else {
					count++;
				}
			}
		}
		System.err.println("inserted edges = " + count);
		count = 0;
		/*for (IRNode e : alledges) {
			if (!existing_edges.contains(e)) {
				GoalModel.graph.disconnect(e);
				count++;
			}
		}*/
		System.err.println("deleted edges = " + count);
	}
	//	private void insert_existing_edge(GoalModel gm, Hashtable<Intention, IRNode> table2, GoalModel gm2, Intention s, Intention t, String type) {
	//		IRNode new_s = table2.get(s);
	//		IRNode new_t = table2.get(t);
	//		IRNode new_e = gm.createEdge(type);
	//		gm2.graph.connect(new_e, new_s, new_t);
	//	}
	private IRNode insert_an_edge(GoalModel gm,
			Hashtable<Intention, IRNode> table, Contribution c, Intention s,
			Intention t) {
		String type = get_label_from_type(c);
		IRNode edge = find_an_edge(gm, table, type, s, t);
		if (edge == null) {
			IRNode new_e = gm.createEdge(type);
			if (table.get(s) != null && table.get(t) != null) {
				GoalModel.graph.connect(new_e, table.get(s), table.get(t));
				edge = new_e;
			}
		}
		return edge;
	}

	/**
	 * Returns the label for the given contribution.
	 * Labels can be ++, --, +, -, Some+, Some-, Unknown.
	 * 
	 * @param c the contribution object.
	 * @return the contribution label.
	 */
	private String get_label_from_type(Contribution c) {
		// FIXME: How about the other contribution types? Unknown, Some+, Some-?
		String type = "+"; //assume help
		if (c instanceof MakeContribution)
			type = "++";
		else if (c instanceof HurtContribution)
			type = "-";
		else if (c instanceof BreakContribution)
			type = "--";
		return type;
	}

	private IRNode insert_an_edge(GoalModel gm,
			Hashtable<Intention, IRNode> table, Decomposition c, Intention s,
			Intention t) {
		String type = get_label_from_type(c);
		IRNode edge = find_an_edge(gm, table, type, s, t);
		if (edge == null) {
			IRNode new_e = gm.createEdge(type);
			if (table.get(s) != null && table.get(t) != null) {
				GoalModel.graph.connect(new_e, table.get(s), table.get(t));
				//				System.out.println("+ edge: " + s.getName() + ", " + t.getName());
				edge = new_e;
			}
		}
		return edge;
	}

	private IRNode find_an_edge(GoalModel gm,
			Hashtable<Intention, IRNode> table, String type, Intention s,
			Intention t) {
		IRNode edge = null;
		IRNode new_s = table.get(s);
		IRNode new_t = table.get(t);
		if (new_s == null || new_t == null) {
			IRNode root = gm.getRoot();
			int n = GoalModel.graph.numChildren(root);
			for (int i = 0; i < n; i++) {
				IRNode g = GoalModel.graph.getChild(root, i);
				String name = get_goal_name(gm, g);
				if (name.equals(s.getName())) {
					table.put(s, g);
					new_s = g;
				}
				if (name.equals(t.getName())) {
					table.put(t, g);
					new_t = g;
				}
			}
		}
		if (new_s != null && new_t != null) {
			int n = GoalModel.graph.numChildren(new_s);
			for (int i = 0; i < n; i++) {
				IRNode g = GoalModel.graph.getChild(new_s, i);
				IRNode e = GoalModel.graph.getChildEdge(new_s, i);
				String lbl = get_edge_type(gm, e);
				if (g == new_t && type.equals(lbl)) {
					edge = e;
					break;
				}
			}
		}
		return edge;
	}

	private String get_label_from_type(Decomposition c) {
		String type = "AND";
		if (c instanceof OrDecomposition)
			type = "OR";
		return type;
	}
	
	
/** 8888888888888888888888888888888888888888888888888888888888 */	
/*** these are Editor methods **/
	
	/**
	 * Determines what commands were run in the dirty editor
	 * and stores them in an array (alg 4 in ICSE/RE paper)
	 * @param gm
	 */
	public void modify_edited_goal_model(GoalModel gm, openome_modelEditor gme) {
		//a map holding the number of operations for reporting
		Hashtable<String, Integer> elementCounts = new Hashtable<String, Integer>(); 
		elementCounts.put("delete-edge", new Integer(0));
		elementCounts.put("delete-node", new Integer(0));
		elementCounts.put("add-node", new Integer(0));
		elementCounts.put("add-edge", new Integer(0));
		elementCounts.put("modify-node", new Integer(0));
		elementCounts.put("modify-edge", new Integer(0));
		
		Hashtable<String, IRNode> table = tables.get(gm);
		ArrayList<Command> list = new ArrayList<Command>();
		while (gme.getEditingDomain().getCommandStack().canUndo()) {
			list.add(gme.getEditingDomain().getCommandStack().getMostRecentCommand());
			gme.getEditingDomain().getCommandStack().undo();
		}
		for (int i = list.size(); i > 0; i--) {
			Command c = (Command) list.get(i - 1);
			System.out.println(c.getClass());
			
			//switch on Command type
			if (c instanceof CompoundCommand || c.getClass().toString().equals("org.eclipse.emf.edit.command.SetCommand$4")) {
				System.out.println("compound command ");
				//CompoundCommand cc = (CompoundCommand) c;
				//FIXME hack for ICSE paper. This really needs to update molhado when a decomp. is changed.
				elementCounts.put("modify-edge", elementCounts.get("modify-edge") + 1);
				//for(Object x: cc.getCommandList()) {
//					x = (Command) x;
//					if (x instanceof AddCommand){}
//					else if (x instanceof RemoveCommand){}
				//}
			} else if (c instanceof SetCommand ) {
				SetCommand sc = (SetCommand) c;
				EStructuralFeature feature = sc.getFeature();
				rename_an_object(gm, table, sc, feature, elementCounts);
			} else if (c instanceof DeleteCommand) {
				DeleteCommand dc = (DeleteCommand) c;
				delete_an_object(gm, table, dc, elementCounts, gme);
			} else if (c instanceof CreateChildCommand) {
				CreateChildCommand dc = (CreateChildCommand) c;
				if(dc.getCommand() instanceof AddCommand) {
					add_an_object(gm, table, (AddCommand) dc.getCommand(), elementCounts);
				}
				if(dc.getCommand() instanceof SetCommand) {
					SetCommand sc = (SetCommand) dc.getCommand();
					EStructuralFeature feature = sc.getFeature();
					rename_an_object(gm, table, sc, feature, elementCounts);
				}
			} else if (c instanceof AddCommand) {
				AddCommand dc = (AddCommand) c;
				add_an_object(gm, table, dc, elementCounts);
			}
			if (gme.getEditingDomain().getCommandStack().canRedo()) {
				gme.getEditingDomain().getCommandStack().redo();						
			}
		}
		//System.out.println("|S| is " + list.size());
		//System.out.println("Report on save: " + elementCounts.toString());
		gme.getEditingDomain().getCommandStack().flush();
	}

	/**
	 * FIXME: When a new link is created, the source field must be filled
	 * in before the target field. Don't know why this behavior :(
	 * @param gm
	 * @param table
	 * @param sc
	 * @param feature
	 * @param elementCounts reporting hash 
	 */
	void rename_an_object(GoalModel gm, Hashtable<String, IRNode> table, SetCommand sc, EStructuralFeature feature, Hashtable<String, Integer> elementCounts) {
		if (sc.getOwner() instanceof Intention && sc.getValue() instanceof String) {
			Intention it = (Intention) sc.getOwner();
			String v = (String) sc.getValue();
			System.out.println("node is: " + sc.getValue());
			Object old = sc.getOldValue();
			if (old instanceof String) {
				IRNode root = table.get(old);
				if (root != null) {
					// we assume the hard goals and softgoals are
					// not using the same name
					if (it instanceof Goal) {
						gm.setHardGoalName(root, v);
					} else if (it instanceof Softgoal) {
						gm.setSoftGoalName(root, v);
					}
					table.put(v, root);
					table.remove(old);
					//System.out.println(elementCounts);
					//elementCounts.put("modify-node", elementCounts.get("modify-node") + 1);
				}
				elementCounts.put("modify-node", elementCounts.get("modify-node") + 1);
			} else { 
				System.out.println(old);
				//System.out.println("before creating the goal " + v + " size(table)=" + table.size());
				System.out.println("in strange else...");
				IRNode g = table.get(v);
				if (g==null) {
					try {
					g = gm.createAGoal(v, it instanceof Goal);
					elementCounts.put("add-node", elementCounts.get("add-node") + 1);
					} catch (Exception x) {
						x.printStackTrace();
					}
					table.put(v, g);
					if (it.getParentDecompositions().size()==0) {
						//System.out.println("after creating the goal " + v + " size(table)=" + table.size());
						IRNode root = gm.getRoot();
						gm.connect(root, g, gm.createEdge("virtual"));
						//elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
					}
					if (it instanceof Goal) {
						gm.setHardGoalName(g, v);
					} else if (it instanceof Softgoal) {
						gm.setSoftGoalName(g, v);
					}							
				}
			}
		} else if (sc.getOwner() instanceof Contribution && sc.getValue() instanceof Intention) {
			Contribution it = (Contribution) sc.getOwner();
			Intention v = (Intention) sc.getValue();
			Object old = sc.getOldValue();
			if (old instanceof Intention) { // modify edge
				Intention in = (Intention) old;
		    	IRNode s = table.get(in.getName());
		    	IRNode t = table.get(v.getName());
			    if (it.getSource() == in) {
					int numChildren = GoalModel.graph.numChildren(s);
					for (int i = 0; i < numChildren; i++) {
						IRNode childedgenode = GoalModel.graph.getChildEdge(s, i);
						IRNode childnode = GoalModel.graph.getChild(s, i);
						IRNode thechild = table.get(it.getTarget().getName());
						if (childnode == thechild) {
				    		GoalModel.graph.disconnect(childedgenode);
				    		GoalModel.graph.connect(childedgenode, t, childnode);									
							break;
						}
					}					    	
			    } else if (it.getTarget() == in) {
					int numParent = GoalModel.graph.numParents(s);
					for (int i = 0; i < numParent; i++) {
						IRNode parentedge = GoalModel.graph.getParentEdge(s, i);
						IRNode parent = GoalModel.graph.getParent(s, i);
						IRNode theparent = table.get(it.getSource().getName());								
						if (parent == theparent) {
				    		GoalModel.graph.disconnect(parentedge);
				    		GoalModel.graph.connect(parentedge, parent, t);									
							break;
						}
					}					    						    	
			    }
			    elementCounts.put("modify-edge", elementCounts.get("modify-edge") + 1);
			} else { // new edge
				String label = getContributionLabel(it);
				createEdge(gm, table, it, v, label);
				elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
			}
		} else if (sc.getOwner() instanceof Decomposition && sc.getValue() instanceof Intention) {
			Decomposition it = (Decomposition) sc.getOwner();
			Intention v = (Intention) sc.getValue();
			Object old = sc.getOldValue();
			if (old instanceof Intention) { // modify edge
				Intention in = (Intention) old;
		    	IRNode s = table.get(in.getName());
		    	IRNode t = table.get(v.getName());
			    if (it.getSource() == in) {
					int numChildren = GoalModel.graph.numChildren(s);
					for (int i = 0; i < numChildren; i++) {
						IRNode childedgenode = GoalModel.graph.getChildEdge(s, i);
						IRNode childnode = GoalModel.graph.getChild(s, i);
						IRNode thechild = table.get(it.getTarget().getName());
						if (childnode == thechild) {
				    		GoalModel.graph.disconnect(childedgenode);
				    		GoalModel.graph.connect(childedgenode, t, childnode);									
							break;
						}
					}					    	
			    } else if (it.getTarget() == in) {
					int numParent = GoalModel.graph.numParents(s);
					for (int i = 0; i < numParent; i++) {
						IRNode parentedge = GoalModel.graph.getParentEdge(s, i);
						IRNode parent = GoalModel.graph.getParent(s, i);
						IRNode theparent = table.get(it.getSource().getName());								
						if (parent == theparent) {
				    		GoalModel.graph.disconnect(parentedge);
				    		GoalModel.graph.connect(parentedge, parent, t);									
							break;
						}
					}					    						    	
			    }
			    elementCounts.put("modify-edge", elementCounts.get("modify-edge") + 1);
			} else { // new edge
				String label = getDecompositionLabel(it); 
				createEdge(gm, table, it, v, label);
				elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
			}
		}
	}

	void createEdge(GoalModel gm, Hashtable<String, IRNode> table, Contribution it, Intention v, String label) {
		String srcName = null, tgtName = null;
		if (it.getSource()!=null)
			srcName = it.getSource().getName();
		if (it.getTarget()!=null)
			tgtName = it.getTarget().getName();
		IRNode s = null, t= null;
		if (srcName!=null) {
			s = table.get(srcName);
			t = table.get(v.getName());
		}
		if (tgtName!=null) {
			s = table.get(v.getName());
			t = table.get(tgtName);
		}
		if (s!=null && t!=null) {
			IRNode edge = gm.createEdge(label);						
			GoalModel.graph.connect(edge, s, t);
		}
	}

	private String getContributionLabel(Contribution it) {
		return get_label_from_type (it);
	}

	private void createEdge(GoalModel gm, Hashtable<String, IRNode> table, Decomposition it, Intention v, String label) {
		String srcName = null, tgtName = null;
		if (it.getSource()!=null)
			srcName = it.getSource().getName();
		if (it.getTarget()!=null)
			tgtName = it.getTarget().getName();
		IRNode s = null, t= null;
		if (srcName!=null) {
			s = table.get(srcName);
			t = table.get(v.getName());
		}
		if (tgtName!=null) {
			s = table.get(v.getName());
			t = table.get(tgtName);
		}
		if (s!=null && t!=null) {
			IRNode edge = gm.createEdge(label);						
			GoalModel.graph.connect(edge, s, t);
		}
	}

	private String getDecompositionLabel(Decomposition it) {
		String label = "AND";	// AND
		if ((it instanceof AndDecomposition)) {
			label = "OR";
		}
		return label;
	}

	
	void delete_an_object(GoalModel gm, Hashtable<String, IRNode> table, DeleteCommand dc, Hashtable<String,Integer> elementCounts, openome_modelEditor gme) {
		Collection<?> objs = dc.getCollection();
						
		for (Object o : objs ) {
			if (o instanceof Intention) {
				Intention in = (Intention) o;
				IRNode root = table.get(in.getName());
				if (root!=null)
					gm.getGraph().removeNode(root);
				// enforce the containment semantics
				Model model = get_model(gme);
				if (model != null) {
					ArrayList<Intention> nodes = remove_sub_tree_node(in, model);
					ArrayList<Decomposition> edges = remove_sub_tree_edge(in, model);
					model.getDecompositions().removeAll(edges);
					EList<Contribution> l = model.getContributions();
					for (int j=0; j<l.size(); j++) {
						Contribution d = l.get(j);
						if (d.getSource()==in 
						 || d.getTarget()==in
						 || d.getSource()==null
						 || d.getTarget()==null
						 || d.getSource()!=null && nodes.contains(d.getSource())
						 ||	d.getTarget()!=null && nodes.contains(d.getTarget()))
							model.getContributions().remove(d);
					}
					model.getIntentions().removeAll(nodes);
					elementCounts.put("delete-edge", elementCounts.get("delete-edge") + (Integer) edges.size());
					elementCounts.put("delete-node", elementCounts.get("delete-node") + (Integer) nodes.size());
				}
			} else if (o instanceof Decomposition) {
				Decomposition in = (Decomposition) o;
				if (in.getSource()==null || in.getTarget()==null) {
					System.err.println("Warning: the link you deleted has empty end(s).");
					continue;
				}
				String sname = in.getSource().getName();
				String tname = in.getTarget().getName();								
				IRNode source = table.get(sname);
				IRNode target = table.get(tname);
				IRNode edge = find_edge(gm, source, target);
		        if (edge!=null)
		        	gm.getGraph().disconnect(edge);
		        elementCounts.put("delete-edge", elementCounts.get("delete-edge") + 1);
			} else if (o instanceof Contribution) {
				Contribution in = (Contribution) o;
				if (in.getSource()==null || in.getTarget()==null) {
					System.err.println("Warning: the link you deleted has empty end(s).");
					continue;
				}
				String sname = in.getSource().getName();
				String tname = in.getTarget().getName();								
				IRNode source = table.get(sname);
				IRNode target = table.get(tname);
				IRNode edge = find_edge(gm, source, target);
		        if (edge!=null)
		        	gm.getGraph().disconnect(edge);
		        elementCounts.put("delete-edge", elementCounts.get("delete-edge") + 1);
			}
		}
	}

	private void add_an_object(GoalModel gm, Hashtable<String, IRNode> table, AddCommand ac, Hashtable<String,Integer> elementCounts) {
		
		for (Object o: ac.getCollection()) {
			if (o instanceof Intention) {
				System.out.println("add an intention");
				Intention in = (Intention) o;								
				IRNode root = gm.createAGoal(in.getName(), in instanceof Goal);
				
				//FIXME this seems buggy - why just get the first owner?
				//FIXME there is a problem where adding a blank named node to a model, then saving, does not create the node in Molhado.
				// instead, the node is 'recreated' when the label is changed.  This should be a create then rename operation.
				Intention owner = null;
				if(in.getParentDecompositions().size() > 0) {
					owner = (Intention) in.getParentDecompositions().get(0);
				}
				if (owner!=null) {
					IRNode parent = table.get(owner.getName());
					EList<Decomposition> l = owner.getDecompositions();
					for (int j=0; j<l.size(); j++) {
						Decomposition decomp = (Decomposition) l.get(j);
						IRNode e;
						if (decomp instanceof AndDecomposition) {
							if (decomp.getTarget() == in) {
								e = gm.createEdge("AND");												
								gm.connect(parent, root, e);
							} 
						} else {
							if (decomp.getTarget() == in) {
								e = gm.createEdge("OR");												
								gm.connect(parent, root, e);
							} 									
						}
						elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
						elementCounts.put("add-node", elementCounts.get("add-node") + 1);
						break;
					}
				}				
			} else if (o instanceof Decomposition) {
				System.out.println("add decomp");
				Decomposition decomp = (Decomposition) o;	
				IRNode e;
				if (decomp instanceof AndDecomposition ) {
					e = gm.createEdge("AND");		
				} else {
					e = gm.createEdge("OR");												
				}
				elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
				//now connect the edge to its parent/child
				if(decomp.getSource() != null && decomp.getTarget() != null){
					Intention parent = decomp.getSource();
					Intention child = decomp.getTarget();
					IRNode p = table.get(parent.getName());
					IRNode c = table.get(child.getName());
					gm.connect(p, c, e);
				}
				//what the 'ell happens if not connected?
			} else if (o instanceof Contribution) {
				System.out.println("add contr");
				Contribution contrib = (Contribution) o;
				Intention from = contrib.getSource();
				Intention to = contrib.getTarget();
				IRNode f = table.get(from.getName());
				IRNode t = table.get(to.getName());
				IRNode e;
				e = gm.createEdge(get_label_from_type(contrib));
				gm.connect(f, t, e);
				elementCounts.put("add-edge", elementCounts.get("add-edge") + 1);
			}
		} 
	}


	/**
	 * Remove the subtree
	 * @param in, the root element
	 * @param model
	 */
	private ArrayList<Intention> remove_sub_tree_node(Intention in, Model model) {
		ArrayList<Intention> nodes = new ArrayList<Intention>();
		nodes.add(in);
		EList<Decomposition> l = model.getDecompositions();
		for (int j=0; j<l.size(); j++) {
			Decomposition d = l.get(j);
			if (d.getSource()==in) {
				if (d.getTarget()!=null) {
					ArrayList<Intention> sub = remove_sub_tree_node(d.getTarget(), model);
					nodes.addAll(sub);
				}
			}
		}
		return nodes;
	}
	
	private ArrayList<Decomposition> remove_sub_tree_edge(Intention in, Model model) {
		ArrayList<Decomposition> edges = new ArrayList<Decomposition>();
		EList<Decomposition> l = model.getDecompositions();
		for (int j=0; j<l.size(); j++) {
			Decomposition d = l.get(j);
		    if (d.getTarget() == in) {
		    	edges.add(d);
		    }
		}
		for (int j=0; j<l.size(); j++) {
			Decomposition d = l.get(j);
			if (d.getSource()==in) {
				if (d.getTarget()!=null) {
					ArrayList<Decomposition> sub = remove_sub_tree_edge(d.getTarget(), model);
					edges.addAll(sub);
				}
			}
		}
		return edges;
	}

	private Model get_model(openome_modelEditor gme) {
		Model model = null;
		for (Resource b: gme.getEditingDomain().getResourceSet().getResources()) {
			//Object b = ti.next();
			if (b instanceof Model) {
				model = (Model) b;
				break;
			}
		}
		return model;
	}

	private IRNode find_edge(GoalModel gm, IRNode source, IRNode target) {
		IRNode edge = null;
		if (source==null || target==null)
			return edge;
		int numChildren = gm.getGraph().numChildren(source);
		for (int j = 0; j < numChildren; j++) {   	
		  IRNode childnode = gm.getGraph().getChild(source, j);
		  if (childnode == target) {
			  edge = gm.getGraph().getChildEdge(source, j);
			  break;
		  }
		}
		return edge;
	}
	
	/**
	 * Causes stack overflow on large models
	 * due to recursive calls
	 * Neil moved outside loop
	 */	
	/*private HashSet<IRNode> getAllEdges(GoalModel gm, IRNode root) {
		HashSet<IRNode> alledges = new HashSet<IRNode>();
		set = new HashSet<IRNode>();
		int numChildren;
		numChildren = GoalModel.graph.numChildren(root);
		for (int j = 0; j < numChildren; j++) {
			IRNode g = GoalModel.graph.getChild(root, j);
			alledges.addAll(allEdges(gm, g));
			set.clear();
		}
		return alledges;
	}
	

	private HashSet<IRNode> allEdges(GoalModel gm, IRNode root) {
		int numChildren = GoalModel.graph.numChildren(root);
		for (int j = 0; j < numChildren; j++) {
			IRNode e = GoalModel.graph.getChildEdge(root, j);
			set.add(e);
			IRNode g = GoalModel.graph.getChild(root, j);
			set.addAll(allEdges(gm, g));
		}
		return set;
	} */

	/** 
	 * replaces old method that used recursion
	 * @param gm
	 * @param root
	 * @return
	 */
	/*TODO finish this 
	 * private HashSet<IRNode> getAllEdgesStack(GoalModel gm, IRNode root) {
		HashSet<IRNode> alledges = new HashSet<IRNode>();
		Stack<IRNode> st = new Stack<IRNode>(); //the stack
		int numChildren = GoalModel.graph.numChildren(root);
		for(int i = 0; i < numChildren; i++ ){
			st.push(GoalModel.graph.getChild(root,i)); //add all children to the stack
		}
		IRNode g = st.pop(); //get first child
		//this for loop is the non-base case operation ... collect this nodes child edges
		for (int j = 0; j < numChildren; j++) {
			alledges.add(GoalModel.graph.getChildEdge(g, j));
		}
		while( g != null) {
			if(GoalModel.graph.hasChildren(g)) {
				if(g has a next sibling)
					st.push(g's next sibling);
			} else {
				get next sibling
				if next sibling is null and stack is full
				g = st.pop(); 
			}
		}			
		return alledges;
	}*/
	
	/**
	 * @author nernst
	 * @return int representing current highest version
	 */
	public int getVersion(String project_name, String model_name, Configuration config) {
		int versionNumber = 0;
		Enumeration<String> vs = config.getAllVersionNames();
		while (vs.hasMoreElements()) {
			String v1_name = vs.nextElement();
			if (v1_name.substring(0, v1_name.lastIndexOf("-")).equals(model_name)) {
				versionNumber++;
			}
		}
		return versionNumber;
	}
	
	/**
	 * Finds the persistent storage location for this set of versions
	 * @return the java property (command line -D argument)
	 */
	public String getMolhadoProperty() {
		String property = System.getProperty("fluid.ir.path");
		if (property == null) {
			String toString = getPluginInstallPath();
			System.setProperty("fluid.ir.path", toString);
			property = System.getProperty("fluid.ir.path");			
		}
		return property;
	}
}


