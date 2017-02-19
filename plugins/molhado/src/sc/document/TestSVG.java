package sc.document;

import java.io.BufferedWriter;
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
public class TestSVG {
  static final FileLocator floc = IRPersistent.fluidFileLocator;
  static Configuration config;
  static Tree tree;
  static IRNode rootnode;
  static IRNode doc1node;
  
  static SCDirectory root;  
    
  public static void main(String[] args) {
    Configuration.ensureLoaded();
    if (args[0].equals("--store")) {
        store();
    }
  }
  
  // Store
  public static void store() {
    try {      
      root = new SCDirectory();
      root.setName("Software Concordance");
      config = new Configuration("Sofware Concordance", root);
      
      // Get this version
      Version v1 = Version.getVersion();
      Version.setVersion(v1);
                  
      // Store the version v1
      config.assignVersionName(v1,"v1");
      System.out.println("SAVING VERSION \"v1\" ...");
      config.saveVersionByDelta(v1,floc);
      
      // Store the configuration in ASCII
      System.out.println("Store the configuration ...");
      config.storeASCII(new PrintWriter(
         new BufferedWriter(new FileWriter("c:/eclipse/data/SC.cfg"))));
      
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
}
