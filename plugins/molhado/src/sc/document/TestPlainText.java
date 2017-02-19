package sc.document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import sc.xml.NullDTD;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.tree.Tree;
import fluid.util.FileLocator;
import fluid.version.Version;

/**
 * @author Tien
 *
 */
public class TestPlainText {
  
  static final FileLocator floc = IRPersistent.fluidFileLocator;
  static Configuration config;
  static Tree tree;
  static IRNode rootnode;
  static IRNode doc1node;
  static IRNode doc2node;
  static SCDirectory root;
  static SCPlainTextDocument doc1;
  static SCPlainTextDocument doc2;
  
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
      config = new Configuration("TestConfiguration", root);
      
      IRNode rootnode = config.getRoot();
      tree = config.getTree();
      
      System.out.println("Reading from the disk ... ");
      
      // Add 1st document
      doc1 = new SCPlainTextDocument(NullDTD.prototype);
      doc1.importDocument(new 
        BufferedReader(new FileReader("C:/DOCS/sach.txt")));
      doc1.setName("sach");  
      doc1node = config.newComponent(doc1);
      tree.appendChild(rootnode,doc1node);

      // Add 2nd document
      doc2 = new SCPlainTextDocument(NullDTD.prototype);
      doc2.importDocument(new 
        BufferedReader(new FileReader("C:/jdk1.4/README.txt")));
      doc2.setName("README");
      doc2node = config.newComponent(doc2);
      tree.appendChild(rootnode,doc2node);      
  
      // Get this version
      Version v1 = Version.getVersion();
      Version.setVersion(v1);
      doc1.exportDocument(new PrintWriter(System.out),v1);
      System.out.println("Numchildren of root = " + doc1.numChildren(doc1.getRoot()));
      System.out.println("Element name of root = " + doc1.elementName(doc1.getRoot()));
      System.out.println("Get plain text = ");
      System.out.println(doc1.getPlainText());
      System.out.println("=================");
      System.out.println("Dump in XML format");
      System.out.println("=================");
      doc1.dumpInXmlFormat(new PrintWriter(System.out),v1);
      
      // Store the version v1
      config.assignVersionName(v1,"v1");
      System.out.println("SAVING VERSION \"v1\" ...");
      config.saveVersionByDelta(v1,floc);
      
      // Store the configuration in ASCII
      System.out.println("Store the configuration ...");
      config.storeASCII(new PrintWriter(
         new BufferedWriter(new FileWriter("c:/eclipse/data/TestPlainText.cfg"))));
      
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  // Load
  public static void load () {
    try {      
      // Load the configuration    
      config = Configuration.loadASCII(
          new FileReader("c:/eclipse/data/TestPlainText.cfg"),floc);
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
        if (comp instanceof SCPlainTextDocument) {
          doc1node = node;
          doc1 = (SCPlainTextDocument) comp; 
          printPlainText(doc1,v1);
        }         
      }    
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /** Printout */
  public static void printPlainText(SCPlainTextDocument doc1, Version v1) 
    throws IOException {
    System.out.println("=================");
    System.out.println("Export document: ");
    System.out.println("=================");
    doc1.exportDocument(new PrintWriter(System.out),v1);
    System.out.println("=================");
    System.out.println("Get plain text:  ");
    System.out.println("=================");
    System.out.println(doc1.getPlainText());
    System.out.println("=================");
    System.out.println("Dump in XML format");
    System.out.println("=================");
    doc1.dumpInXmlFormat(new PrintWriter(System.out),v1);
  }
}
