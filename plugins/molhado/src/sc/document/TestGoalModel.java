package sc.document;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.tree.Tree;
import fluid.util.FileLocator;
import fluid.version.Version;

public class TestGoalModel {
	  static final FileLocator floc = IRPersistent.fluidFileLocator;
	  static Configuration config;
	  static Tree tree;
	  static IRNode rootnode;
	  static IRNode gm_node;
	  static SCDirectory project_root;
	  static GoalModel gm;
	  
	  static IRNode root;
	  
	  static Version v1;
	  static Version v2;
	  
	  public static void main(String[] args) {
	    Configuration.ensureLoaded();
	    store();
	    load();
	    test_v2();
//	    if (args[0].equals("--store")) {
//	        // VersionedSlot.debugOn();
//	        // IRPersistent.setTraceIO(true);
//	        // IRPersistent.setDebugIO(true);
//	        store();
//	    } else if (args[0].equals("--load")){
//	        load();
//	    } else {
//	    	test_v2();
//	    }
	  }
	  
	  // Store
	  public static void store() {
	    try {      
	      project_root = new SCDirectory();
	      project_root.setName("GoalModel");
	      config = new Configuration("TestGoalModel", project_root);
	      
	      IRNode rootnode = config.getRoot();
	      tree = Configuration.getTree();
	      
	      // Create a goal model
	      gm = new GoalModel();
	      gm.setName("DrProject");
	      gm_node = config.newComponent(gm);
	      tree.appendChild(rootnode,gm_node);
	      
	      buildTracGM();
	      
	      // Get this version
	      v1 = Version.getVersion();
	      Version.setVersion(v1);
	                  
	      // Store the version v1
	      config.assignVersionName(v1,"v1");
	      System.out.println("SAVING VERSION \"v1\" ...");
	      config.saveVersionByDelta(v1,floc);
	      
	      // Store the configuration in ASCII
	      System.out.println("Store the configuration ...");
	      config.storeASCII(new PrintWriter(
	         new BufferedWriter(new FileWriter(
	        		 System.getProperty("fluid.ir.path") + "/drproject.cfg"))));
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }    
	  }
	  
	  public static void buildTracGM() {
		  root = gm.createAGoal("Support S/W Development Projects", true);
		  gm.setRoot(root);
		  
		  IRNode wgs = gm.createAGoal("Write Great Software", false);
		  IRNode e = gm.createEdge("+");
		  gm.connect(root, wgs, e);
		  
		  IRNode op = gm.createAGoal("Overview Project", true);
		  e = gm.createEdge("AND");
		  gm.connect(root, op, e);
		  
		  IRNode sedp = gm.createAGoal("Support Established Dev. Projects", true);
		  e = gm.createEdge("AND");
		  gm.connect(root, sedp, e);
		  
		  IRNode tp = gm.createAGoal("Track Progress", true);
		  e = gm.createEdge("AND");
		  gm.connect(op, tp, e);
		  
		  IRNode ew = gm.createAGoal("Enhanced Wiki", true);
		  e = gm.createEdge("AND");
		  gm.connect(op, ew, e);
		  
		  IRNode pa2SVN = gm.createAGoal("Provide Access to SVN", true);
		  e = gm.createEdge("AND");
		  gm.connect(sedp, pa2SVN, e);
		  
		  IRNode web_based = gm.createAGoal("Web-based", true);
		  e = gm.createEdge("AND");
		  gm.connect(sedp, web_based, e);
		  
		  IRNode it = gm.createAGoal("Issue Tracking", true);
		  e = gm.createEdge("AND");
		  gm.connect(sedp, it, e);
		  
		  IRNode sdeit = gm.createAGoal("Show Dev Events in Time", true);
		  e = gm.createEdge("OR");
		  gm.connect(tp, sdeit, e);
		  
		  IRNode crf = gm.createAGoal("Convenient Report Facilities", true);
		  e = gm.createEdge("OR");
		  gm.connect(tp, crf, e);
		  
		  IRNode linkbws = gm.createAGoal("Link Bug Wiki Source", true);
		  e = gm.createEdge("AND");
		  gm.connect(ew, linkbws, e);
		  
		  IRNode wmtf = gm.createAGoal("Allow Wiki Markup in Text Fields", true);
		  e = gm.createEdge("AND");
		  gm.connect(ew, wmtf, e);
		  
		  IRNode di = gm.createAGoal("Do not Interfere", false);
		  e = gm.createEdge("+");
		  gm.connect(pa2SVN, di, e);
		  
		  IRNode ma = gm.createAGoal("Minimalistic Approach", false);
		  e = gm.createEdge("+");
		  gm.connect(web_based, ma, e);
		  
		  IRNode usability = gm.createAGoal("Usability", false);
		  e = gm.createEdge("+");
		  gm.connect(crf, usability, e);
		  
		  e = gm.createEdge("+");
		  gm.connect(linkbws, usability, e);
		  
	  }
	  
	  public static void load() {
		  try {      
		      // Load the configuration    
		      config = Configuration.loadASCII(
		          new FileReader(System.getProperty("fluid.ir.path") 
		        		  + "/drproject.cfg"),floc);
		      java.util.Enumeration<String> vs = config.getAllVersionNames();
		      System.out.println("Here is a list of available versions :");
		      while (vs.hasMoreElements())
		        System.out.println("Version : " + (String) vs.nextElement());
		      
		      String v1_name = "v1";
		      System.out.println("Loading " + v1_name + " ...");
		      System.out.println("===========================");
		      v1 = config.loadVersionByDelta(v1_name,floc);
		      Version.setVersion(v1);
		            
		      tree = Configuration.getTree();
		      rootnode = config.getRoot();
		      
		      System.out.println("List of components");
		      System.out.println("==================");
		      java.util.Enumeration<IRNode> en = tree.depthFirstSearch(rootnode);
		      while (en.hasMoreElements()) {
		        IRNode node = (IRNode) en.nextElement();
		        Component comp = config.getComponent(node);
		        System.out.print("+ \"" + comp.getName(v1) + "\"");
		        System.out.println(" ( " + node + " ).");
		        if (comp instanceof GoalModel) {
		        	GoalModel gm = (GoalModel) comp;
		        	gm.traverseGM(gm.getRoot(), null);
		        	modify(gm);
		        }
		      }
		      
		      // Get this version
		      v2 = Version.getVersion();
		      Version.setVersion(v2);
		                  
		      // Store the version v2
		      config.assignVersionName(v2,"v2");
		      System.out.println("SAVING VERSION \"v2\" ...");
		      config.saveVersionByDelta(v2,floc);
		      
		      // Store the configuration in ASCII
		      System.out.println("Store the configuration ...");
		      config.storeASCII(new PrintWriter(
		         new BufferedWriter(new FileWriter(System.getProperty("fluid.ir.path")
		        		 + "/drproject_v2.cfg"))));
		      
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	  }
	  
	  public static void modify(GoalModel gm) {
		  root = gm.getRoot();
		  IRNode qual = gm.getIthChild(root, 0);
		  gm.setHardGoalName(qual, "Quality [Software]");
		  
		  IRNode pop = gm.getIthChild(root, 1);
		  
		  IRNode sedp = gm.getIthChild(root, 2);
		  
		  IRNode upas = createABranch(gm);
		  
		  IRNode pit = gm.getIthChild(pop, 0);
		  gm.setHardGoalName(pit, "Progress is tracked");
		  
		  
		  IRNode rfac = gm.getIthChild(pit, 1);
		  IRNode e = gm.getGraph().getChildEdge(pit, 1);
		  gm.setEdgeType(e, GoalModel.AND);
		  gm.setHardGoalName(rfac, "Reporting facilities are convenient");
		  
		  IRNode spid = gm.createAGoal("Student Progress is displayed", true);
		  e = gm.createEdge("AND");
		  gm.connect(pit, spid, e);
		  
		  IRNode tl = gm.createAGoal("Temporal list of Dev. Events supported", true);
		  e = gm.createEdge("AND");
		  gm.connect(pit, tl, e);
		  
		  IRNode sdeit = gm.getIthChild(pit,0);
		  gm.getGraph().removeNode(sdeit);
		  
		  IRNode pa2SVN = gm.getIthChild(sedp, 0);
		  IRNode wb = gm.getIthChild(sedp, 1);
		  IRNode it = gm.getIthChild(sedp, 2);
		  
		  IRNode pmi = gm.createAGoal("Provide Mailing Integration", true);
		  e = gm.createEdge("AND");
		  gm.connect(sedp, pmi, e);
		  
		  IRNode wa = gm.createAGoal("Web Admin is supported", true);
		  e = gm.createEdge("AND");
		  gm.connect(wb, wa, e);
		  
		  
	  }
	  
	  public static IRNode createABranch(GoalModel gm) {
		  if (gm == null) return null;
		  
		  IRNode upas = gm.createAGoal("Unix Practices are supported", true);
		  IRNode e = gm.createEdge("AND");
		  gm.connect(gm.getRoot(), upas, e);
		  
		  IRNode sca = gm.createAGoal("Support custom authentication", true);
		  e = gm.createEdge("AND");
		  gm.connect(upas, sca, e);
		  
		  IRNode secu = gm.createAGoal("Security", false);
		  e = gm.createEdge("+");
		  gm.connect(sca, secu, e);
		  
		  IRNode gws = gm.createAGoal("Group work supported", true);
		  e = gm.createEdge("AND");
		  gm.connect(upas, gws, e);
		  
		  IRNode tis = gm.createAGoal("Trac is simplified", true);
		  e = gm.createEdge("AND");
		  gm.connect(upas, tis, e);
		  
		  IRNode cis = gm.createAGoal("Code is simplified", true);
		  e = gm.createEdge("AND");
		  gm.connect(tis, cis, e);
		  
		  IRNode uis = gm.createAGoal("UI is simplified", true);
		  e = gm.createEdge("AND");
		  gm.connect(tis, uis, e);
		  
		  IRNode maintain = gm.createAGoal("Maintainability[Code]", false);
		  e = gm.createEdge("+");
		  gm.connect(cis, maintain, e);
		  
		  return upas;
		  
	  }
	  
	  public static void test_v2() {
		  try {      
		      // Load the configuration    
		      config = Configuration.loadASCII(
		          new FileReader(System.getProperty("fluid.ir.path") 
		        		  +"/drproject_v2.cfg"),floc);
		      java.util.Enumeration<String> vs = config.getAllVersionNames();
		      System.out.println("Here is a list of available versions :");
		      while (vs.hasMoreElements())
		        System.out.println("Version : " + (String) vs.nextElement());
		      
		      String v1_name = "v1";
		      System.out.println("Loading " + v1_name + " ...");
		      System.out.println("===========================");
		      v1 = config.loadVersionByDelta(v1_name,floc);
		      Version.setVersion(v1);
		      
		      String v2_name = "v2";
		      System.out.println("Loading " + v2_name + " ...");
		      System.out.println("===========================");
		      v2 = config.loadVersionByDelta(v2_name,floc);
		      Version.setVersion(v2);
		            
		      tree = Configuration.getTree();
		      rootnode = config.getRoot();
		      
		      //
		      
		      System.out.println("List of components");
		      System.out.println("==================");
		      java.util.Enumeration<IRNode> en = tree.depthFirstSearch(rootnode);
		      while (en.hasMoreElements()) {
		        IRNode node = (IRNode) en.nextElement();
		        Component comp = config.getComponent(node);
		        System.out.print("+ \"" + comp.getName(v2) + "\"");
		        System.out.println(" ( " + node + " ).");
		        if (comp instanceof GoalModel) {
		        	GoalModel gm = (GoalModel) comp;
		        	gm.traverseGM(gm.getRoot(), null);
		        	testChange(gm);
		        }
		      }
		      
		      
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	  }
	  
	  public static void testChange(GoalModel gm) {
		  root = gm.getRoot();
		  IRNode qual = gm.getIthChild(root, 0);		  
		  IRNode pop = gm.getIthChild(root, 1);		  
		  IRNode sedp = gm.getIthChild(root, 2);
		  IRNode upas = gm.getIthChild(root, 3);
		  
		  
		  System.out.println(GoalModelAttrBundle.goalmodelchanged.changed(root, v1, v2));
		  
		  // Test if structure at "qual" softgoal is changed
		  System.out.println(GoalModelAttrBundle.goalmodelchanged.changed(qual, v1, v2));
		  
		  System.out.println(GoalModelAttrBundle.goalmodelpropertychanged.changed(qual, v1, v2));
		  
		  Version.setVersion(v1);
		  System.out.println("[Version 1] #children of the root = " + gm.getGraph().numChildren(root));
		  
		  Version.setVersion(v2);
		  System.out.println("[Version 2] #children of the root = " + gm.getGraph().numChildren(root));
		  
		  IRNode pit = gm.getIthChild(pop, 0);
		  
		  IRNode edge = gm.getGraph().getChildEdge(pit, 0);
		  System.out.println(GoalModelAttrBundle.goalmodelpropertychanged.changed(edge, v1, v2));
		  		  
		  Version.setVersion(v1);
		  System.out.println("[Version 1]: " + gm.getGMNodeName(edge));
		  
		  Version.setVersion(v2);
		  System.out.println("[Version 2]: " + gm.getGMNodeName(edge));
		  
		  
		  IRNode spid = gm.getIthChild(pit, 1);
		  Version.setVersion(v1);
		  if (gm.getGMNodeName(spid) == null) System.out.println("Not yet at v1");
		  IRNode sdep = gm.getIthChild(pit, 0);
		  System.out.println(gm.getGMNodeName(sdep));
		  Version.setVersion(v2);
		  System.out.println("Name at v2 = " + gm.getGMNodeName(spid));
		  System.out.println(GoalModelAttrBundle.goalmodelchanged.changed(sdep, v1,v2));
		  
	  }
}
