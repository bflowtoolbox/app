package sc.document;

import java.io.BufferedReader;
import java.io.FileReader;

import fluid.ir.Bundle;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentReferenceType;
import fluid.ir.IRStringType;
import fluid.ir.SlotInfo;
import fluid.util.UniqueID;
import fluid.version.VersionedSlotFactory;

/**
 * @author Tien N. Nguyen
 */
public class XmlAttrBundle {
  /** The bundle for XML Attributes in an XML Document */
  private static Bundle xmlattr = null;
  /** The SlotInfo for raw text attribute "info" */
  public static SlotInfo rawTextAttrSlotInfo = null;
  
  public static SlotInfo parentTable_si;
  
  /** Keep track of changes in multi(hyper-)media attributes of an XML node */
  public static MultimediaChanged multimediaChanged;
  
  private static void loadXmlAttrFile() {
    try {
      FileReader fr = new FileReader(
          IRPersistent.fluidFileLocator.locateFile("xmlattr.atl",true));
      BufferedReader buffer = new BufferedReader(fr);
      for (String line = buffer.readLine(); line != null; 
          line = buffer.readLine()) {
        SlotInfo si = VersionedSlotFactory.prototype.makeSlotInfo(line,
               IRStringType.prototype);
        // This line must be removed when getBundle() is called
        // xmlattr.saveAttribute(si);
        
        si.addDefineObserver(multimediaChanged);
        if (line.equals("info") == true)
          rawTextAttrSlotInfo = si;
      }
      fr.close();
      parentTable_si = VersionedSlotFactory.prototype.makeSlotInfo("parentTable",
              IRPersistentReferenceType.prototype);
      // These lines must be removed when getBundle() is called
      // xmlattr.saveAttribute(parentTable_si);
      // xmlattr.saveAttribute(multimediaChanged);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    
  public static Bundle getBundle() {
    if (xmlattr == null) {
      try {
        multimediaChanged = 
          new MultimediaChanged("scxml.multimedia", DocTreeBundle.tree);
        loadXmlAttrFile();
        xmlattr =
          Bundle.loadBundle(
            UniqueID.parseUniqueID("docattr"),
            IRPersistent.fluidFileLocator);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error in loading \"docattr\" bundle !");
      }
    }
    return xmlattr;
  }
  
  public static void main(String[] args) {
     /*  
    try {
      xmlattr = new Bundle();
      multimediaChanged = 
        new MultimediaChanged("scxml.multimedia", DocTreeBundle.tree);
      loadXmlAttrFile();
    
      xmlattr.store(IRPersistent.fluidFileLocator);
    } catch (Exception e) {
      e.printStackTrace();
    }
   
    if (xmlattr != null)
      xmlattr.describe(System.out);
    */ 
        
    xmlattr = getBundle();
    if (xmlattr != null) xmlattr.describe(System.out);
    
  }
}
