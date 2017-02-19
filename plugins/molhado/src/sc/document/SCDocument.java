/* $Header: /usr/local/refactoring/molhadoRef/src/sc/document/SCDocument.java,v 1.1 2006/03/21 23:20:57 dig Exp $ */

package sc.document;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Hashtable;

import sc.xml.IRDTD;
import fluid.FluidRuntimeException;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IROutput;
import fluid.ir.Slot;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
import fluid.version.VersionedRegion;
import fluid.version.VersionedSlot;
import fluid.version.VersionedSlotFactory;

/**
 * SC Document
 * Author : Tien N. Nguyen
 */
public abstract class SCDocument extends Component {

  private Slot                          rootSlot; // VersionedSlot
  
  /** Region for this document */
  protected /*final*/  VersionedRegion  region;
  
  // public static final Bundle          docTreeBundle = DocTreeBundle.getBundle();
  
  private static Hashtable             region2DocumentTable = new Hashtable();
  
  // Constant for write/readContents
  protected static final int          ROOTBIT = 1;
  
  // Constructors

  protected SCDocument(int magic, UniqueID id) {
    super(magic, id);
    region = null;
    rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
  }
  
  /** Total new document */
  protected SCDocument(int magic) {
    super (magic, true);
    region = new VersionedRegion();
    region2DocumentTable.put(region, this);
    rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
  }
  
  // Access methods

  /** get the DTD associated with the document
   *
   * @return dtd
   */
  public abstract IRDTD getDTD();

  /** get the root node
   *
   * @return root node
   */
  public IRNode getRoot(Version v) {
    // Version.saveVersion();
    // Version.setVersion(v);
    IRNode result;
    if (rootSlot.isValid()) {
      VersionedSlot vl = (VersionedSlot) rootSlot;
      result = (IRNode) vl.getValue(v);
    }
    else result = null;
    // Version.restoreVersion();
    return result;
  }

  public IRNode getRoot() {
    if (rootSlot.isValid()) {
      return (IRNode) rootSlot.getValue();
    }
    else return null;
  }

  /** get the region of the document */
  public VersionedRegion getRegion() {
    return region;
  }

  // public static abstract String elementName (IRNode n);
  
  // Mutators

  /** set the root node
   */
  public void setRoot(IRNode n) {
    if (n != null) {
      if (SCDocument.getDocument(n) != this)
        throw new SCDocumentException("root node must be in same document");
    }
    rootSlot = rootSlot.setValue(n);
  }
  
  // IO SERVICES : read/write and import/export
  // read/write are used for the native file formats for a document type in SC
  // import/export are used for the usual formats from user point of view

  /** NOT SURE: read (and parse) the document from given Reader
   *
   * @param r Reader
   */
  public abstract void readDocument(Reader r) throws SCDocumentParseException;

  /** NOT SURE: dump the document in given Writer
   *
   * @param w Writer
   */
  public abstract void writeDocument(Writer w, Version v) throws IOException;

  /** import (and parse) the document from given Reader
   *
   * @param r Reader
   */
  public abstract void importDocument(Reader r) throws SCDocumentParseException;

  /** export the document in given Writer
   *
   * @param w Writer
   */
  public abstract void exportDocument(Writer w, Version v) throws IOException;

  /** NOT SURE 
   * export a node to given Writer
   * @param w Writer
   */
  public abstract void exportNode(Writer w, IRNode n, Version v) throws IOException;

  // static utilities

  /** Get the document of the node n */
  public static SCDocument getDocument(IRNode n) {
    VersionedRegion vr = 
    	(VersionedRegion) VersionedRegion.getVersionedRegion(n);
      // (VersionedRegion) VersionedRegion.getOwner(n);
    
    return (SCDocument) region2DocumentTable.get(vr);    
  }

  /** NOT SURE
   *  All files "java,jml,xml" are "imported" now.
   
  public static SCDocument readFile(File file) throws FileNotFoundException {
    if (file == null)
      throw new SCDocumentException("Provided file is null.");

    if (file.isDirectory())
      throw new SCDocumentException("Provided file is a dirctory.");

    SCDocument doc   = null;
    String extension = null;
    String docName   = file.getName();
    int i = docName.lastIndexOf('.');

    if (i>=0) {
      extension = docName.substring(i+1);
      docName   = docName.substring(0, i);
    }

    if (extension == null) {
      throw new SCDocumentException(docName + " has no extension.");
    } else if (extension.equals("java")) {
      // it a java file and needs to be imported
      doc = new SCJavaDocument(docName);
      doc.importDocument(new BufferedReader(new FileReader(file)));
    } else if (extension.equals("jml")) {
      // it a java file in XML format: read it (NOW import it)
      doc = new SCJavaDocument(docName);
      doc.importDocument(new BufferedReader(new FileReader(file)));
    } else if (extension.equals("xml")) {
      // it is a xml file : read it (NOW import it)
      // doc = new SCXmlDocument(docName, NullDTD.prototype, true);
      doc.importDocument(new BufferedReader(new FileReader(file)));
    } else {
      throw new SCDocumentException(docName + " has invalid '" + extension
                        + "' extension. jml and java are valid extensions.");
    }
    return doc;
  }
  */
  
  // UTILITIES
  /**
   * Load the versioned region of this document
   */
  protected void loadRegion(fluid.util.FileLocator floc) {
    // throws FluidRuntimeException {
    // if (region == null)
    //  throw new FluidRuntimeException("region is null");   
    try {      
      if (region == null) {
      	BufferedReader br = new BufferedReader(
      			new InputStreamReader(floc.openFileRead(this.getID().toString() + ".region")));
       	String region_name = br.readLine();
        UniqueID id = UniqueID.parseUniqueID(region_name);
        region = VersionedRegion.loadVersionedRegion(id,floc);
        // Update this table
        region2DocumentTable.put(region, this);
      }
      else
      	if ((region != null) && (!region.isDefined())) region.load(floc); 
    } catch (IOException e) {
      System.err.println("Exception occured: " + e);
      e.printStackTrace();
    }
  }
  
  /**
   * Save the region of the document
   */
  protected void saveRegion(fluid.util.FileLocator floc) {
    if (region.isStored() == false) {
//      System.out.println("Saving REGION ...");
      try {
        region.store(floc);
//        region.describe(System.out);
        // 
        DataOutputStream os = new DataOutputStream(
        		floc.openFileWrite(this.getID().toString() + ".region"));
        PrintWriter w = new PrintWriter(os);
        w.write(region.getID().toString());
     		w.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  // WRITE/READ CONTENTS and WRITE/READ CHANGED CONTENTS
  
  public void writeContents(IROutput out) 
    throws IOException
  {
    super.writeContents(out);
    
    int flags = 0;
    if (rootSlot.isValid()) flags |= ROOTBIT;
    out.writeByte(flags);
    
    if ((flags & ROOTBIT) != 0) 
      rootSlot.writeValue(IRNodeType.prototype,out);
    
    // Write the immutable field "region"
    region.writeReference(out);
  }
  
  public void readContents(IRInput in) 
    throws IOException
  {
    super.readContents(in);
    
    int flags = in.readByte();
    if ((flags & ROOTBIT) != 0) 
      rootSlot = rootSlot.readValue(IRNodeType.prototype,in);
    
    // Read the immutable field "region"
    region = (VersionedRegion) in.readPersistentReference();
    // Not load region yet
    if (region == null)
       throw new FluidRuntimeException("region is null");
    else
      // Update this table
      { 
        //?
        // PlainIRNode.setCurrentRegion(region); 
        region2DocumentTable.put(region, this);
      }
  }
  
  public void writeChangedContents(IROutput out)
    throws IOException
  {
    super.writeChangedContents(out);
    
    int flags = 0;
    if (rootSlot.isChanged()) flags |= ROOTBIT;
    out.writeByte(flags);
    if ((flags & ROOTBIT) != 0) rootSlot.writeValue(IRNodeType.prototype,out);
    
    // Hack
    // region.writeReference(out);
  }
  
  public void readChangedContents(IRInput in) 
    throws IOException
  {
    super.readChangedContents(in);

    int flags = in.readByte();
    if ((flags & ROOTBIT) != 0) 
      rootSlot = rootSlot.readValue(IRNodeType.prototype,in);
    
    // Hack
    // region = (VersionedRegion) in.readPersistentReference();
  }

  public boolean isChanged()
  {
    return super.isChanged() || rootSlot.isChanged();
  }  
  
  // LOAD/SAVE DELTAS and SNAPSHOTS
  /** loadDelta */
  public void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
//    System.out.println("Loading REGION ... ");
    loadRegion(floc);
    /*System.out.println("Loading delta for DOCUMENT TREE STRUCTURE ...");
    VersionedChunk vc = VersionedChunk.get(region,docTreeBundle);
    vc.getDelta(era).load(floc);
    vc.describe(System.out);*/
  }

  /** saveDelta */
  public void saveDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
    // IRPersistent.setTraceIO(true);
    // Save the REGION of this document if not yet saved
    saveRegion(floc);
    /*System.out.println("Saving chunk delta for docTreeBundle ...");
    VersionedChunk ch = VersionedChunk.get(region,docTreeBundle);
    IRPersistent vcd = ch.getDelta(era);
    vcd.store(floc);
    vcd.describe(System.out);*/
  }

  /** Load the snapshot of this component for the given version. */
  public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    System.out.println("Loading REGION ... ");
    loadRegion(floc);    
    /*System.out.println("Loading snapshot for DOCUMENT TREE STRUCTURE ...");
    VersionedChunk vc = VersionedChunk.get(region,docTreeBundle);
    ((IRPersistent) vc.getSnapshot(v)).load(floc); 
    vc.describe(System.out);*/
  }
  
  /** Store a snapshot of this component for the given version. */
  public void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    // IRPersistent.setTraceIO(true);
    // Save the REGION of this document if not yet saved
    saveRegion(floc);
    /*System.out.println("Saving snapshot for docTreeBundle ... ");
    VersionedChunk ch = VersionedChunk.get(region,docTreeBundle);
    IRPersistent vcs = ch.getSnapshot(v);
    vcs.store(floc);
    vcs.describe(System.out);*/
  }
}
