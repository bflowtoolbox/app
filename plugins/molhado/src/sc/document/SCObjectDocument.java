package sc.document;

import java.io.DataInput;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import sc.xml.IRDTD;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.PlainIRNode;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;

/**
 * @author Tien
 */
public class SCObjectDocument extends SCDocument {

  private static final int           magic = 0x4F424A00; // 'OBJ\0'
    
  // Constructors  
  
  // A new document
  public SCObjectDocument() {
    super(magic);
    PlainIRNode root = new PlainIRNode(region);
    setRoot(root);
  }
  
  // Existing one
  protected SCObjectDocument(UniqueID id) {
    super(magic, id);    
  }
  
  // Access methods

  public ComponentFactory getFactory() {
    return Factory.prototype;
  }

  public IRDTD getDTD() { return null;}

  public String getFileName() {
    return this.getID()
            + ".obj";
  }

  // IO : read/write and import/export
  /** loadDelta */
  public void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
    super.loadDelta(era,floc);    
  }

  /** saveDelta */
  public void saveDelta(Era era, fluid.util.FileLocator floc)
    throws IOException {
    super.saveDelta(era,floc);    
  }

  /** Load the snapshot of this component for the given version. */
  public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    super.loadSnapshot(v,floc);    
  }
  
  /** Store a snapshot of this component for the given version. */
  public void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    super.saveSnapshot(v,floc);    
  }
  
  // Write/ReadContents and write/readChangedContents
  public void writeContents(IROutput out) 
    throws IOException
  {
    super.writeContents(out);    
  }
  
  public void readContents(IRInput in) 
    throws IOException
  {
    super.readContents(in);
  }
  
  public void writeChangedContents(IROutput out)
    throws IOException
  {
    super.writeChangedContents(out);
  }
  
  public void readChangedContents(IRInput in) 
    throws IOException
  {
    super.readChangedContents(in);
  }

  public boolean isChanged()
  {
    return super.isChanged();
  }
  
  public void readDocument(Reader r) throws SCDocumentParseException {    
  }

  public void writeDocument(Writer w, Version v) throws IOException {    
  }
  
  public void exportDocument(Writer w, Version v) throws IOException {
  }
    
  public void exportNode(Writer w, IRNode n, Version v) throws IOException {
  }
  
  public void importDocument(Reader r) throws SCDocumentParseException {
  }
  
  // FACTORY
  private static class Factory extends ComponentFactory 
  {
    public static final Factory prototype = new Factory();
    private Factory() {
      super();
    }

    /** Create an SCObjectDocument based on input in */
    public Component create(UniqueID id, DataInput in)
    {
      SCObjectDocument obj = new SCObjectDocument(id);
      return obj;
    }
  }
}
