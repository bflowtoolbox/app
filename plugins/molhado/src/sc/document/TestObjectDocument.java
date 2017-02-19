package sc.document;

// import sc.xml.*;
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

/**
 * @author Tien
 */
public class TestObjectDocument {

  static final FileLocator floc = IRPersistent.fluidFileLocator;
  static Configuration config;
  static Tree tree;
  static IRNode rootnode;
  static IRNode doc1node;
  
  static SCDirectory root;
  static SCObjectDocument doc1;
    
  public static void main(String[] args) {
    Configuration.ensureLoaded();
    if (args[0].equals("--store")) {
        // VersionedSlot.debugOn();
        // IRPersistent.setTraceIO(true);
        // IRPersistent.setDebugIO(true);
        store();
     } else {
        load();
     }
  }
  
  // Store
  public static void store() {
    try {      
      root = new SCDirectory();
      root.setName("SC");
      config = new Configuration("TestObjectDocument", root);
      
      IRNode rootnode = config.getRoot();
      tree = config.getTree();
            
      // Add 1st document
      doc1 = new SCObjectDocument();      
      doc1.setName("main.obj");
      doc1node = config.newComponent(doc1);
      tree.appendChild(rootnode,doc1node);
      
      // Get this version
      Version v1 = Version.getVersion();
      Version.setVersion(v1);
      
      System.out.println("doc1's name = " + doc1.getName());     
      
      // Store the version v1
      config.assignVersionName(v1,"v1");
      System.out.println("SAVING VERSION \"v1\" ...");
      config.saveVersionByDelta(v1,floc);
      
      // Store the configuration in ASCII
      System.out.println("Store the configuration ...");
      config.storeASCII(new PrintWriter(
         new BufferedWriter(new FileWriter("c:/eclipse/data/TestSCObject.cfg"))));
      
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  // Load
  public static void load () {
    try {      
      // Load the configuration    
      config = Configuration.loadASCII(
          new FileReader("c:/eclipse/data/TestSCObject.cfg"),floc);
      java.util.Enumeration vs = config.getAllVersionNames();
      System.out.println("Here is a list of available versions :");
      while (vs.hasMoreElements())
        System.out.println("Version : " + (String) vs.nextElement());
      
      String v1_name = "v1";
      System.out.println("Loading " + v1_name + " ...");
      System.out.println("===========================");
      Version v1 = config.loadVersionByDelta(v1_name,floc);
      Version.setVersion(v1);
            
      tree = config.getTree();
      rootnode = config.getRoot();
      
      System.out.println("List of components");
      System.out.println("==================");
      java.util.Enumeration en = tree.depthFirstSearch(rootnode);
      while (en.hasMoreElements()) {
        IRNode node = (IRNode) en.nextElement();
        Component comp = config.getComponent(node);
        System.out.print("+ \"" + comp.getName(v1) + "\"");
        System.out.println(" ( " + node + " ).");
        if (comp instanceof SCObjectDocument) {
          doc1node = node;
          doc1 = (SCObjectDocument) comp; 
          System.out.println("doc1's name = " + doc1.getName());
          System.out.println("Root is = " + doc1.getRoot());
        }         
      }    
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
