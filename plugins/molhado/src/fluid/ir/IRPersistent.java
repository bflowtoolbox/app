/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRPersistent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;

import fluid.FluidError;
import fluid.FluidRuntimeException;
import fluid.util.FileLocator;
import fluid.util.PathFileLocator;
import fluid.util.QuickProperties;
import fluid.util.Trace;
import fluid.util.UniqueID;
import fluid.util.ZipFileLocator;

/** Persistent aspects of the internal representation of the ACT/Fluid project.
 * The contents have a "magic" number associated with it that distinguishes
 * the creator.  
 *
 * <p> A persistent entity goes through several states in core: <dl>
 * <dt>incomplete<dd> A newly created storage is incomplete,
 * the nodes in the storage are yet to be defined.
 * Only the owner of a persistent entity (new=true) can assert
 * that is complete.
 * An incomplete storage must first be made complete before it is stored.</dd>
 * <dt>complete<dd> A defined storage is immutable and
 * ready to store.  A complete persistenty entity cannot be loaded.</dd>
 * <dt>stored<dd> This storage is immutable and storage exists
 * for it somewhere (presumably).  It can be stored again, if desired.</dd>
 * <dt>undefined<dd> The storage needs to be loaded before it is used.
 * It is immutable. </dd> </dl>
 * A storage unit "remembers" whether it was/will be created in this
 * execution session ("new") or whether it was/will be loaded.
 * A ``new'' persistent entity cannot be loaded, because the creator
 * has at least as much information.
 * The ``new'' flag is true only for the original creator of
 * the entity: when a persistent structure is replicated in another
 * JVM, it is defined, not new.  In summary, the following combinations
 * are valid: <dl>
 *	<dt>new, defined, not complete, not stored</dt>
 *		<dd> Owned by this JVM.  Still under construction.</dd>
 *	<dt>new, defined, complete, not stored</dt>
 *		<dd> Owned by this JVM.  Construction complete, but
 *		not yet stored to a file.</dd>
 *	<dt>new, defined, complete, stored</dt>
 *		<dd> Owned by this JVM. Completed and stored. </dd>
 *	<dt>not new, not defined, not complete, not stored</dt>
 *		<dd> Referenced somehow but not loaded.</dd>
 *	<dt>not new, defined, complete, stored</dt>
 *		<dd> Loaded from persistent store.</dd>
 *	<dt>not new, defined, not complete, not stored</dt>
 *		<dd> Partially known through a reference.</dd>
 * </dl>
 * </p>
 *
 * <p> Each kind of persistent entity may define one or more
 * of the following static services: <dl>
 * <dt><tt>find</tt>XXX<tt>(</tt>...<tt>)</tt></dt>
 *     <dd>Return the persistent entity matching the arguments,
 *         or return null if none created thus far.</dd>
 * <dt><tt>get</tt>XXX<tt>(</tt>...<tt>)</tt></dt>
 *     <dd>Return the persistent entity matching the arguments,
 *         creating it if necessary. </dd>
 * <dt><tt>load</tt>XXX<tt>(</tt>...<tt>,FileLocator)</tt></dt>
 *     <dd>Return the persistent entity matching the arguments,
 *	   or create and load it in one step. </dd> </dl>
 * At least either a <tt>get</tt> or <tt>load</tt> method should be defined.
 * </p>
 *
 * <p> This class also includes various registration services for <ul>
 * <li> IR types
 * <li> Slot factories
 * <li> kinds of IR persistent objects </ul>
 * </p>
 *
 * <p> Each datafile is tagged with revision
 * information to permit old data files to be read. </p>
 * @see IRRegion
 * @see Bundle
 * @see IRChunk
 */
public abstract class IRPersistent implements Serializable {
  /* IRType registration */
  
  private static int numIRTypes = 0;
  private static Vector IRTypeBytes = new Vector();
  private static Vector IRTypes = new Vector();

  private static final int MaxType      = 255;
  private static final Byte[] byteTable = new Byte[MaxType+1];
  static {
    for(int i = 0; i <= MaxType; i++) {
      byteTable[i] = new Byte((byte) i);
    }
  }
  private static Byte getByte(int reg) {
    if (reg < 0 || reg > MaxType)
      throw new FluidError("IR Type registration too big.");
    return byteTable[(byte) reg];
  }
  // Also replaced 
  //   IRTypeBytes.elementAt(i).equals(r) 
  // with
  //   IRTypeBytes.elementAt(i) == r

  /** Give a byte to name a IR Type in storage.
   */
  public static void registerIRType(IRType ty, int reg) {
    Byte r = getByte(reg);
    for (int i=0; i<numIRTypes; ++i) {
      if (IRTypeBytes.elementAt(i) == r)
	throw new FluidError("IRType already registered with this code.");
    }
    IRTypes.addElement(ty);
    IRTypeBytes.addElement(r);
    ++numIRTypes;
  }

  protected static IRType getRegisteredIRType(int reg) {
    Byte r = getByte(reg);
    for (int i=0; i<numIRTypes; ++i) {
      if (IRTypeBytes.elementAt(i) == r)
	return (IRType)IRTypes.elementAt(i);
    }
    throw new FluidRuntimeException("No IRType registered for " + r);
  }

  protected static int getIRTypeRegistration(IRType ty) {
    for (int i=0; i<numIRTypes; ++i) {
      if (IRTypes.elementAt(i).equals(ty))
	return ((Number)IRTypeBytes.elementAt(i)).intValue();
    }
    throw new FluidRuntimeException("IR Type " + ty + " not registered!");
  }


  /* Slot factory registration. */

  private static int numSlotFactories = 0;
  private static Vector slotFactoryBytes = new Vector();
  private static Vector slotFactories = new Vector();

  /** Give a byte to name a slot factory in storage.
   */
  public static void registerSlotFactory(SlotFactory sf, int reg) {
    Byte r = getByte(reg);
    for (int i=0; i<numSlotFactories; ++i) {
      if (slotFactories.elementAt(i).equals(sf)) {
	throw new FluidError("This SlotFactory already registered.");
      }
      if (slotFactoryBytes.elementAt(i) == r)
	throw new FluidError("SlotFactory already registered with this code.");
    }
    slotFactories.addElement(sf);
    slotFactoryBytes.addElement(r);
    ++numSlotFactories;
  }

  protected static SlotFactory getRegisteredSlotFactory(int reg) {
    Byte r = getByte(reg);
    for (int i=0; i<numSlotFactories; ++i) {
      if (slotFactoryBytes.elementAt(i) == r)
	return (SlotFactory)slotFactories.elementAt(i);
    }
    throw new FluidRuntimeException("No SlotFactory registered for " + r);
  }

  protected static int getSlotFactoryRegistration(SlotFactory sf) {
    for (int i=0; i<numSlotFactories; ++i) {
      if (slotFactories.elementAt(i).equals(sf))
	return ((Number)slotFactoryBytes.elementAt(i)).intValue();
    }
    throw new FluidRuntimeException("Slot factory " + sf + " not registered!");
  }


  /* IR persistent kind registration */

  private static int numPersistentKinds = 0;
  private static Vector persistentKindBytes = new Vector();
  private static Vector persistentKinds = new Vector();

  /** Give a byte to name a persistent kind.
   */
  public static void registerPersistentKind(IRPersistentKind pk, int reg) {
	  Byte r = getByte(reg);
	  for (int i=0; i<numPersistentKinds; ++i) {
		  if (persistentKinds.elementAt(i).equals(pk)) {
			  throw new FluidError("This PersistentKind already registered.");
		  }
		  if (persistentKindBytes.elementAt(i) == r)
			  throw new FluidError("PersistentKind #" + r + " is registered for " +
					  persistentKinds.elementAt(i) +
					  " and cannot be used for " + pk);
	  }
	  persistentKinds.addElement(pk);
	  persistentKindBytes.addElement(r);
	  ++numPersistentKinds;
  }

  protected static IRPersistentKind getRegisteredPersistentKind(int reg) {
	  Byte r = getByte(reg);
	  //System.out.println(persistentKinds);
	  for (int i=0; i<numPersistentKinds; ++i) {
		  if (persistentKindBytes.elementAt(i) == r)
			  return (IRPersistentKind)persistentKinds.elementAt(i);
	  }
	  throw new FluidRuntimeException("No PersistentKind registered for " + r);
  }

  protected static int getPersistentKindRegistration(IRPersistentKind pk) {
	  for (int i=0; i<numPersistentKinds; ++i) {
		  if (persistentKinds.elementAt(i).equals(pk))
			  return ((Number)persistentKindBytes.elementAt(i)).intValue();
	  }
	  throw new FluidRuntimeException("Persistent kind " + pk +
	  " not registered!");
  }


  /** Return kind for this storage. */
  public abstract IRPersistentKind getKind();


  /* imports:
   * A persistent object may refer to nodes of (other) IR regions.
   * The import list gives all of these.
   */

  private Vector imports = null;
  private Hashtable importIndex = null;
  private Vector importOffsets = null;
  private int numImportedNodes = 0;

  private void clearImports() {
    imports = null;
    importOffsets = null;
    importIndex = null;
    numImportedNodes = 0;
  }

  private void prepareForImports() {
    if (imports == null) imports = new Vector();
    if (importIndex == null) importIndex = new Hashtable();
    if (importOffsets == null) importOffsets = new Vector();
  }

  private void importRegionNoCheck(IRRegion r) {
    // System.out.println(this + ".import[" + imports.size() + "] = " + r);
    r.complete();
    imports.addElement(r);
    Integer index = new Integer(numImportedNodes);
    numImportedNodes += r.getNumNodes(); // this may need to be changed later
    importIndex.put(r,index);
    importOffsets.addElement(index);
  }

  /* Make it possible to refer to nodes from this region
   * in this stored entity.  It doesn't really have any
   * effect on the semantics.
   */
  public synchronized void importRegion(IRRegion r) {
    prepareForImports();
    if (r == this) return;
    if (importsRegion(r)) return;
    importRegionNoCheck(r);
  }

  public synchronized boolean importsRegion(IRRegion r) {
    return (importIndex != null && importIndex.get(r) != null);
  }

  /** Return the import number of a node.
   * We locate the import in the list of imports.
   * If it is present, we add up the sizes of the preceding
   * imports and then the local index for this node.
   * @throws IOException if node has no region or if region
   * not already imported.  In particular, if region == this.
   */
  protected int getImportedIndex(IRNode node) throws IOException {
    IRRegion r = IRRegion.getOwner(node);
    int index = r.getIndex(node);
    Integer ii = (Integer)importIndex.get(r);
    if (ii == null) throw new IOException("not imported");
    return ii.intValue() + index;
  }


  /** Return the node according to the given import number.
   * @see #getImportedIndex(IRNode)
   * @throws IOException if index out of bounds
   */
  protected IRNode getImportedNode(int index) throws IOException {
    /* needed when forgot to clear imports
    if (debugIO) {
      System.out.println("getImported(" + index + ")");
      for (int i=0; i < importOffsets.size(); ++i) {
	System.out.println(i + ": " + importOffsets.elementAt(i));
      }
    }
    */
    int low = 0, high = importOffsets.size();
    while (low < high-1) {
      int mid = (low + high) / 2;
      if (index <= ((Integer)importOffsets.elementAt(mid)).intValue())
	high = mid;
      else
	low = mid;
    }
    index -= ((Integer)importOffsets.elementAt(low)).intValue();
    return ((IRRegion)imports.elementAt(low)).getNode(index);
  }


  /* id's
   * Some persistent objects are named by a unique id.
   * We keep a table of persistent objects already created.
   */
  private final UniqueID id;

  public UniqueID getID() { return id; }
  
  private static final Hashtable created = new Hashtable();

  /** Return persistent object given by ID or null if none
   * known at this time.
   */
  public static IRPersistent find(UniqueID id) {
    return (IRPersistent)created.get(id);
  }


  /* state */

  private final boolean isNew;
  private boolean isDefined, isComplete, isStored;

  /** Return whether this was created this session or
   * whether it was or must be loaded or accepted from some external source
   */
  public boolean isNew() {
    return isNew;
  }

  /** Return whether things need to be added. */
  public boolean isComplete() {
    return isComplete;
  }

  /** Return when persistent is accessible, and can be used. */
  public boolean isDefined() {
    return isDefined;
  }

  /** Return whether it is (possibly) accessible outside this execution.
   */
  public boolean isStored() {
    return isStored;
  }

  /** Indicate that the entity can be used, but that it
   * is not necessarily complete.
   * @return true if change made.
   */
  public boolean define() {
    if (!isDefined) {
      isDefined = true;
      return true;
    }
    return false;
  }
    
  /** Indicate that no more information needs/may be added.
   * @return true if a change was made.
   *!//! Perhaps use isNew() and/or isDefined() and or isComplete()
   */
  public boolean complete() {
    if (isNew && isDefined && !isComplete) {
      isComplete = true;
      return true;
    }
    return false;
  }

  /** Force a persistent entity to be complete.
   * (no checks)
   */
  protected void forceComplete() {
    isComplete = true;
  }

  /** Indicate that the information is "dirty", must be
   * written out.
   * (This method is protected, because many persistent objects are immutable.)
   */
  protected void markDirty() {
    isStored = false;
  }


  /* magic numbers */
  
  public static final int magic1 = 0x4143540a; /* ACT\n -- complete */
  public static final int magic1a = 0x41435469; /* ACTi -- incomplete */
  public final int magic2;

  /** Return persistent specific magic number
   */
  public int getMagic() {
    return magic2;
  }

  /* Constructors */

  /** Create a new and defined persistent object.
   * @param magic number used to distinguish various IR files.
   * @param useID whether to create an id for it.
   */
  protected IRPersistent(int magic, boolean useID) {
    magic2 = magic;
    isNew = true;
    isComplete = false;
    isStored = false;
    define();
    if (useID) {
      id = new UniqueID();
      created.put(id,this);
    } else {
      id = null;
    }
  }
  

  /** Set up a persistent, undefined but not loaded persistent object.
   * @param magic number used to distinguish various IR files.
   * @param oldID id (or null) for object.
   */
  protected IRPersistent(int magic, UniqueID oldID) {
    magic2 = magic;
    isNew = false;
    isDefined = false;
    isComplete = false; // because we might need to load information
    isStored = false;
    id = oldID;
    if (id != null) {
      if (created.get(id) != null)
	throw new FluidRuntimeException("reusing " + id +
					" from " + created.get(id));
      created.put(id,this);
    }
  }


  /* nodes:
   * A persistent object may refer to IR nodes.
   * the following two routines are used to convert numbers to nodes
   * and vice versa.
   */

  /** Return an integer to refer to this node in an output file.
   * Except for IR regions, the node must have been (previously) imported.
   */
  protected int getIndex(IRNode node) throws IOException {
    return getImportedIndex(node);
  }

  /** Return the node that this integer in an input file refers to.
   * Except for IR regions, the node must have been imported.
   */
  protected IRNode getNode(int index) throws IOException {
    return getImportedNode(index);
  }
  

  /** The major version of written persistent objects.
   * Major versions are incompatible.
   */
  public static final byte version = 1;

  /** The minor version number of written persistent objects.
   * 1.0 started the new series of persistence.  Previously,
   * persistence didn't work with sequences, especially locations.
   * The following backward-compatible changes have been made:
   * <ol>
   * <li> Chunks created when a bundle had fewer attributes can be read.
   *      References to regions no longer include number of nodes;
   *      instead imports list number of nodes.
   * <li> Added importing on demand.
   * <li> Shadow Version nodes put into a region associated with era.
   * <li> Incomplete objects can be persisted; complete objects serialized.
   *      References to nodes in incomplete regions can be written.
   *	  Entities can be associated with eras; the association list being
   *	  definitive.
   *      Regions can be written/read, if desired.
   *      Versioned slots may hold versioned containers.
   *      Deltas and versioned are available for all sorts of chunks.
   * </ol>
   * Compatability means that a fluid program using a later revision can
   * work with files created in an earlier revision, but not vice-versa.
   */
  public static final byte revision = 4;

  /** Compute the file name for storing the information
   * in the persistent object in the persistent. store.
   * It should return null if there is nothing to store.
   */
  protected String getFileName() {
    // by default
    return null;
  }

  /* Output */

  /** Write the persistent to a file. */
  public synchronized void store(FileLocator floc) throws IOException {
    if (!isDefined) throw new IOException("Cannot write undefined object");
    String name = getFileName();
    if (name == null) return;
    isStored = false;
    OutputStream s = floc.openFileWrite(name);
    Writer tracer = traceIO ?
      new OutputStreamWriter(floc.openFileWrite(name+".tr")) : null;
    IROutput out = traceIO ?
      new IRTracingOutputStream(s,this,tracer) :
      new IRPersistentOutputStream(s,this);
    writeWrapper(out);
    //? ((OutputStream)out).close();
    // If we have completely stored the item, or if we
    // have an old item that we're storing again, we mark as stored.
    if (isComplete || !isNew) isStored = true;
  }

  /** Write a (possibly incomplete) persistent object to a stream. */
  protected void writeWrapper(IROutput out) throws IOException {
    out.debugBegin("magic");
    out.writeInt(magic2);
    out.debugEnd("magic");
    write(out);
    out.debugBegin("complete");
    out.writeBoolean(isComplete);
    out.debugEnd("complete");
    if (out instanceof OutputStream) ((OutputStream)out).flush();    
  }

  protected void write(IROutput out) throws IOException { }

  /** A routine for writing imports.
   * It also ensures all imported regions are complete.
   * The import list is the number of imports followed by
   * that many persistent IDs.
   */
  public void writeImports(IROutput out) throws IOException {
    prepareForImports();
    if (debugIO) System.out.println("writing " + imports.size() + " imports.");
    out.debugBegin("importSize");
    out.writeInt(imports.size());
    out.debugEnd("importSize");
    int index = 0;
    int n = imports.size();
    out.debugBegin("importList");
    for (int i=0; i < n; ++i) {
      IRRegion st = (IRRegion)imports.elementAt(i);
      st.complete();
      Integer indexInteger = new Integer(index);
      importIndex.put(st,indexInteger);
      importOffsets.setElementAt(indexInteger,i);
      out.debugBegin("import");
      out.writePersistentReference(st);
      out.writeInt(st.getNumNodes());
      out.debugEnd("import");
      index += st.getNumNodes();
    }
    out.debugEnd("importList");
    numImportedNodes = index;
  }
    

  /* Input */

  /** Read in a chunk of IR written earlier.
   * @throws IOException
   *  if version incompatible or magic numbers wrong.
   *  (We also propagate any IOExceptions from reading the file.)
   */
  public synchronized void load(FileLocator floc) throws IOException {
    // if (debugIO) System.out.println("Perhaps loading " + this);
    if (isNew || isComplete) return;
    // if (debugIO) System.out.println("Perhaps loading incomplete old " + this);
    String name = getFileName();
    if (name == null) return;
    InputStream s = floc.openFileRead(name);
    Writer trace = traceIO ? new OutputStreamWriter(floc.openFileWrite(name+".tri")) :   null;
    IRPersistentInputStream ps = traceIO ?  new IRTracingInputStream(s,this,trace) : new IRPersistentInputStream(s,this);
    try {
      if (trace != null) //System.out.println("Tracing this load");
      readWrapper(ps);
      if (ps.getRevision() < 4) forceComplete();
    } finally {
      ps.close();
    }
    isStored = true;
  }

  /** Load a (possibly incomplete) IRPersistent structure from a
   * IRInput stream.
   */
  protected void readWrapper(IRInput in) throws IOException
  {
    if (in.getVersion() != version)
      throw new IOException("can't handle version " + in.getVersion());
    if (in.getRevision() > revision)
      System.err.println("warning: reading IR created by a later revision");
    in.debugBegin("magic");
    int rmagic = in.readInt();
    in.debugEnd("magic");
    if (magic2 != rmagic)
      throw new IOException("Wrong magic number on input " +
			    rmagic + " (expected " + magic2 + ")");
    if (in.debug()) System.out.println("Reading entity " + this);
    //System.out.println("Reading entity " + this);
    read(in);
    isDefined = true;
    if (in.getRevision() >= 4) {
      in.debugBegin("complete");
      isComplete = in.readBoolean(); // currently no need to force complete
      in.debugEnd("complete");
    }
  }

  /** Read the contents of the persistent from persistent store.
   * @param in input stream to read from
   * @throws IOException if error on reading this or antecedent persistents.
   */
  protected void read(IRInput in) throws IOException { }

  /* A method for reading in an import list from a file. */
  public synchronized void readImports(IRInput in)
       throws IOException 
  {
    in.debugBegin("importSize");
    int n = in.readInt();
    in.debugEnd("importSize");
    clearImports();
    prepareForImports();
    in.debugBegin("importList");
    for (int i=0; i < n; ++i) {
      in.debugBegin("import");
      IRRegion reg = (IRRegion)in.readPersistentReference();
      if (in.getRevision() >= 1) reg.complete(in.readInt());
      in.debugEnd("import");
      importRegionNoCheck(reg);
    }
    in.debugEnd("importList");
  }

  /** A descriptive, not necessarily unique name */
  private String name;

  /** Set the name used for output.  This name
   * need not be interesting or unique.  If null,
   * default techniques for identification are used.
   */
  public void setName(String n) {
    name = n;
  }

  /** If the persistent object has a name, use it, otherwise
   * if it has an ID print it, otherwise, defer to the default
   * toString method.
   */
  public String toString() {
    if (name != null) return name;
    if (id != null) return id.toString();
    return super.toString();
  }

  public void describe(PrintStream out) {
    if (id != null) {
      out.println("Name = " + id);
    } else {
      out.println(this);
    }
    out.println("new = " + isNew + ", defined = " + isDefined +
		", complete = " + isComplete + ", stored = " + isStored);
    if (imports != null && imports.size() > 0) {
      out.println("Imports");
      for (int i=0; i < imports.size(); ++i) {
	IRRegion r = (IRRegion)imports.elementAt(i);
	out.println("  " + r + " (" + r.getNumNodes() + " nodes)");
      }
    }
  }

  /** A useful file locator */
  public static final FileLocator fluidFileLocator = new PathFileLocator(
    QuickProperties.getInstance().getProperties().getProperty(
      "fluid.ir.path","."));

  public static final FileLocator fluidZipFileLocator =
    new ZipFileLocator(fluidFileLocator);

  private static boolean debugIO = false;
  public static boolean debugIO() { return debugIO; }
  public static void setDebugIO(boolean flag) { debugIO = flag; }
  private static boolean traceIO = false;
  public static boolean getTraceIO() { return traceIO; }
  public static void setTraceIO(boolean f) { traceIO = f; }

  /** Write a reference to a persistent object to a stream.
   */
  public void writeReference(DataOutput stream) throws IOException {
    IRPersistentKind pk = getKind();
    stream.writeByte(IRPersistent.getPersistentKindRegistration(pk));
    pk.writePersistentReference(this,stream);
  }

  /** Read a reference to a persistent object from a stream.
   * @return a persistent object that may or may not be defined.
   * @throws IOException if error during read
   * @throws FluidRuntimeException if reference uses unknown persistent kind.
   */
  public static IRPersistent readReference(DataInput stream)
       throws IOException
  {
    IRPersistentKind pk = getRegisteredPersistentKind(stream.readByte());
    return pk.readPersistentReference(stream);
  }

  public Object writeReplace() {
    return new IRPersistentWrapper(this);
  }
}

/** Writing internal representations to files. */
abstract class IROutputStream extends FilterOutputStream implements IROutput {
  private final Hashtable cache;
  private final boolean debug = IRPersistent.debugIO();
  protected final DataOutputStream out;
  protected final OutputStream underlying;

  public IROutputStream(OutputStream s, int magic)
       throws IOException
  {
    super(new DataOutputStream(s));
    cache = new Hashtable();
    out = (DataOutputStream)super.out;
    underlying = s;
    out.writeInt(magic);
    out.writeByte(IRPersistent.version);
    out.writeByte(IRPersistent.revision);
  }

  public void writeBoolean(boolean v) throws IOException {
    out.writeBoolean(v);
  }
  public void writeByte(int v) throws IOException {
    out.writeByte(v);
  }
  public void writeBytes(String s) throws IOException {
    out.writeBytes(s);
  }
  public void writeChar(int v) throws IOException {
    out.writeChar(v);
  }
  public void writeChars(String s) throws IOException {
    out.writeChars(s);
  }
  public void writeDouble(double v) throws IOException {
    out.writeDouble(v);
  }
  public void writeFloat(float v) throws IOException {
    out.writeFloat(v);
  }
  public void writeInt(int v) throws IOException {
    out.writeInt(v);
  }
  public void writeLong(long v) throws IOException {
    out.writeLong(v);
  }
  public void writeShort(int v) throws IOException {
    out.writeShort(v);
  }
  public void writeUTF(String str) throws IOException {
    out.writeUTF(str);
  }

  public boolean writeCachedObject(Object object)
       throws IOException
  {
    if (object == null) throw new FluidRuntimeException("do not cache null");
    Integer index = (Integer)cache.get(object);
    if (index == null) {
      writeBoolean(false);
      cache.put(object,new Integer(cache.size()));
      return false;
    } else {
      writeBoolean(true);
      writeInt(index.intValue());
      return true;
    }
  }

  public void writeIRType(IRType ty) throws IOException
  {
    ty.writeType(this);
  }

  public void writeSlotFactory(SlotFactory sf) throws IOException
  {
    writeByte(IRPersistent.getSlotFactoryRegistration(sf));
  }

  public void writePersistentReference(IRPersistent st) throws IOException
  {
    st.writeReference(this);
  }

  public boolean debug() {
    return debug;
  }
  public void debugBegin(String x) {
    if (debug) System.out.print("<" + x + ">");
  }
  public void debugEnd(String x) {
    if (debug) System.out.print("</" + x + ">");
  }
  public void debugMark(String x) {
    if (debug) System.out.print("<" + x + "/>");
  }
}

/** Writing internal representations of persistent objects. */
class IRPersistentOutputStream extends IROutputStream {
  private final IRPersistent persistent;

  public IRPersistentOutputStream(OutputStream s, IRPersistent p)
       throws IOException
  {
    super(s,IRPersistent.magic1);
    persistent = p;
  }

  public void writeNode(IRNode node) throws IOException {
    if (node == null) {
      super.writeInt(0);
    } else
      try
      {
	IRRegion r = IRRegion.getOwner(node);
	if (!persistent.importsRegion(r)) {
	  if (r.isComplete()) {
	    persistent.importRegion(r);
	    debugMark("demandimport region="+r);
	    writeInt(~persistent.getIndex(node));
	    // code similar to r.write(this)
	    debugBegin("region");
	    writePersistentReference(r);
	    writeInt(r.getNumNodes());
	    debugEnd("region");
	  } else {
	    writeInt(~r.getIndex(node));
	    // code similar to r.write(this)
	    debugBegin("region");
	    writePersistentReference(r);
	    writeInt(~r.getNumNodes());
	    debugMark("incomplete");
	    debugEnd("region");
	  }
	} else {
	  writeInt(persistent.getIndex(node));
	}
      }
    catch (OwnerUndefinedException ex) {
      throw new IOException("cannot write reference to node not in a region");
    }
    catch (FluidRuntimeException ex) {
      throw new IOException(ex.getMessage());
    }
  }
}

class IRTracingOutputStream extends IRPersistentOutputStream {
  private final Trace trace;
  public IRTracingOutputStream(OutputStream s, IRPersistent p, Writer w)
    throws IOException
  {
    super(s,p);
    trace = new Trace(w);
  }

  // primitives

  public void write(byte[] b) throws IOException {
    trace.traceString("b\"" + new String(b) + "\"");
    super.write(b);
  }
  
  public void write(byte[] b, int off, int len) throws IOException {
    trace.traceString("b\"" + new String(b,off,len) + "\"");
    super.write(b,off,len);
  }

  public void write(int b) throws IOException {
    trace.traceHex(b,2);
    super.write(b);
  }

  public void writeBoolean(boolean v) throws IOException {
    trace.traceString(v ? "true" : "false");
    super.writeBoolean(v);
  }

  public void writeByte(int i) throws IOException {
    trace.traceHex(i,1);
    super.writeByte(i);
  }

  public void writeBytes(String s) throws IOException {
    trace.traceString("b\"" + s + "\"");
    super.writeBytes(s);
  }

  public void writeChar(int ch) throws IOException {
    trace.traceString("'" + (char)ch + "'");
    super.writeChar(ch);
  }

  public void writeChars(String s) throws IOException {
    trace.traceString("'" + s + "'");
    super.writeChars(s);
  }

  public void writeDouble(double d) throws IOException {
    trace.traceString(Double.toString(d));
    super.writeDouble(d);
  }
  
  public void writeFloat(float f) throws IOException {
    trace.traceString(Float.toString(f));
    super.writeFloat(f);
  }
  
  public void writeInt(int i) throws IOException {
    trace.traceHex(i,4);
    super.writeInt(i);
  }

  public void writeLong(long l) throws IOException {
    trace.trace32(l);
    super.writeLong(l);
  }
  
  public void writeShort(int i) throws IOException {
    trace.traceHex(i,2);
    super.writeShort(i);
  }
  
  public void writeUTF(String s) throws IOException {
    trace.traceString("\"" + s + "\"");
    super.writeUTF(s);
  }

  // the following are non-primitive

  public void writeNode(IRNode node) throws IOException {
    debugBegin("node");
    super.writeNode(node);
    debugEnd("node");
  }
  
  public boolean writeCachedObject(Object object) throws IOException {
    debugBegin("cache");
    boolean b = super.writeCachedObject(object);
    debugEnd("cache");
    if (!b) debugMark("cache_init");
    return b;
  }

  public void writeIRType(IRType ty) throws IOException {
    debugBegin("type");
    super.writeIRType(ty);
    debugEnd("type");
  }

  public void writeSlotFactory(SlotFactory sf) throws IOException {
    debugBegin("slotFactory");
    super.writeSlotFactory(sf);
    debugEnd("slotFactory");
  }

  public void writePersistentReference(IRPersistent p) throws IOException {
    debugBegin("reference");
    super.writePersistentReference(p);
    debugEnd("reference");
  }

  /** Return whether debugging is active */
  public boolean debug() {
    return super.debug();
  }

  /** Inform debugging that a structure is beginning */
  public void debugBegin(String x) {
    trace.traceBegin(x);
  }

  /** Inform debugging that a structure is ending */
  public void debugEnd(String x) {
    trace.traceEnd(x);
  }

  /** Inform debugging that a point is passing */
  public void debugMark(String x) {
    trace.traceString(":" + x);
  }

  public void flush() throws IOException {
    super.flush();
    trace.flush();
  }

  public void close() throws IOException {
    super.close();
    trace.close();
  }
}


/** Writing incomplete persistent objects of any kind. */
class IRObjectOutputStream extends IROutputStream implements ObjectOutput {
  public IRObjectOutputStream(ObjectOutputStream s)
       throws IOException
  {
    super(s,IRPersistent.magic1a);
  }

  public void writeNode(IRNode node) throws IOException {
    if (node == null)
      super.writeInt(0);
    else {
      IRRegion reg = IRRegion.getOwner(node);
      super.writeInt(reg.getIndex(node));
      reg.writeReference(this);
    }
  }

  public void writeObject(Object o) throws IOException {
    flush();
    ((ObjectOutput)underlying).writeObject(o);
  }
}

/** Abstract mixin class implementing most of {@link IRInput}.
 * Subclasses must implement <tt>readNode</tt>.
 * @see IROutputStream
 */
abstract class IRInputStream extends FilterInputStream implements IRInput {
  private final Vector cache;
  private final int version, revision;
  private final boolean debug = IRPersistent.debugIO();
  protected final DataInputStream in;
  protected final InputStream underlying;

  protected IRInputStream(InputStream s, int magic) throws IOException {
    super(new DataInputStream(s));
    cache = new Vector();
    in = (DataInputStream)super.in;
    underlying = s;
    if (in.readInt() != magic)
      throw new IOException("not an IR Input Stream");
    version = in.readByte();
    revision = in.readByte();
  }

  public int getVersion() { return version; }
  public int getRevision() { return revision; }


  public boolean readBoolean() throws IOException {
    return in.readBoolean(); 
  }

  public byte readByte() throws IOException {
    return in.readByte(); 
  }

  public char readChar() throws IOException {
    return in.readChar(); 
  }

  public double readDouble() throws IOException {
    return in.readDouble(); 
  }

  public float readFloat() throws IOException {
    return in.readFloat(); 
  }

  public void readFully(byte[] b) throws IOException {
    in.readFully(b); 
  }

  public void readFully(byte[] b, int off, int len) throws IOException {
    in.readFully(b,off,len);
  }

  public int readInt() throws IOException {
    return in.readInt(); 
  }

  /** Read aline.
   * @deprecated since JDK 1.1
   */
  public String readLine() throws IOException {
    throw new IOException("readLine not supported any more");
  }

  public long readLong() throws IOException {
    return in.readLong(); 
  }

  public short readShort() throws IOException {
    return in.readShort(); 
  }

  public int readUnsignedByte() throws IOException {
    return in.readUnsignedByte(); 
  }

  public int readUnsignedShort() throws IOException {
    return in.readUnsignedShort(); 
  }

  public String readUTF() throws IOException {
    return in.readUTF(); 
  }

  public int skipBytes(int n)  throws IOException {
    return in.skipBytes(n);
  }
  
  public Object readCachedObject() throws IOException {
    if (readBoolean()) {
      int index = readInt();
      if (index < 0 || index > cache.size()) {
	throw new IOException("cache index out of range");
      }
      Object value = cache.elementAt(index);
      if (value == null) {
	throw new IOException("No cached value #" + index);
      }
      return value;
    } else {
      return null;
    }
  }

  public void cacheReadObject(Object obj) {
    if (obj == null) throw new FluidRuntimeException("do not cache null");
    cache.addElement(obj);
  }

  public IRType readIRType() throws IOException {
    IRType ty = IRPersistent.getRegisteredIRType(readByte());
    return ty.readType(this);
  }

  public SlotFactory readSlotFactory() throws IOException {
    return IRPersistent.getRegisteredSlotFactory(readByte());
  }

  public IRPersistent readPersistentReference() throws IOException {
    return IRPersistent.readReference(this);
  }

  public boolean debug() { return debug; }
  public void debugBegin(String x) {
    if (debug) System.out.print("<" + x + ">");
  }
  public void debugEnd(String x) {
    if (debug) System.out.print("</" + x + ">");
  }
  public void debugMark(String x) {
    if (debug) System.out.print("<" + x + "/>");
  }
}  

/** Implementation of IRInput for loading persistent objects. */
class IRPersistentInputStream extends IRInputStream implements IRInput {
  private final IRPersistent persistent;

  /** Create an IRInput stream around the given input stream.
   * @param p a persistent object.
   */
  public IRPersistentInputStream(InputStream s, IRPersistent p)
       throws IOException
  {
    super(s,IRPersistent.magic1);
    persistent = p;
  }

  /** Read a node reference relative to the persistent object being stored. */
  public IRNode readNode() throws IOException {
    int i = readInt();
    if (i == 0) return null;
    if (i < 0) {
      if (getRevision() < 4) {
	IRRegion reg = (IRRegion)readPersistentReference();
	reg.complete(readInt());
	if (debug()) System.out.println("demand importing " + reg);
	persistent.importRegion(reg);
	i = readInt();
      } else { /* revision >= 4 */
	i = ~i;
	debugBegin("region");
	IRRegion reg = (IRRegion)readPersistentReference();
	// we can't use reg.read(this) because
	// then we wouldn't know whether the region was complete or
	// incomplete *in the JVM of the writer* which makes a difference
	// for whether the region is imported or not.  Imported regions
	// are cheaper to refer to (1 rather than 4 words or more).
	int n = readInt();
	if (n < 0) {
	  debugMark("incomplete");
	  debugEnd("region");
	  n = ~n;
	  // i is a region index
	  // n is the currently known size (incomplete)
	  reg.define(n);
	  reg.ensureNodes(n);
	  return reg.getNode(i);
	} else {
	  // i is an import index
	  // n is the complete size
	  debugEnd("region");
	  reg.complete(n);
	  debugMark("demandimport region="+reg);
	  persistent.importRegion(reg);
	}
      }
    }
    return persistent.getNode(i);
  }
}


class IRTracingInputStream extends IRPersistentInputStream {
  private final Trace trace;
  public IRTracingInputStream(InputStream s, IRPersistent p, Writer w)
    throws IOException
  {
    super(s,p);
    trace = new Trace(w);
  }

  // primitives

  public boolean readBoolean() throws IOException {
    boolean v = super.readBoolean();
    trace.traceString(v ? "true" : "false");
    return v;
  }

  public byte readByte() throws IOException {
    byte i = super.readByte();
    trace.traceHex(i,1);
    return i;
  }

  public int readUnsignedByte() throws IOException {
    int i = super.readUnsignedByte();
    trace.traceHex(i,1);
    return i;
  }

  public void readFully(byte[] b) throws IOException {
    super.readFully(b);
    String s = new String(b);
    trace.traceString("b\"" + s + "\"");
  }

  public void readFully(byte[] b, int off, int len) throws IOException {
    super.readFully(b,off,len);
    String s = new String(b,off,len);
    trace.traceString("b\"" + s + "\"");
  }

  public int skipBytes(int n) throws IOException {
    n = super.skipBytes(n);
    trace.traceString("skip " + n);
    return n;
  }

  public char readChar() throws IOException {
    char ch = super.readChar();
    trace.traceString("'" + ch + "'");
    return ch;
  }

  public double readDouble() throws IOException {
    double d = super.readDouble();
    trace.traceString(Double.toString(d));
    return d;
  }
  
  public float readFloat() throws IOException {
    float f = super.readFloat();
    trace.traceString(Float.toString(f));
    return f;
  }
  
  public int readInt() throws IOException {
    int i = super.readInt();
    trace.traceHex(i,4);
    return i;
  }

  public long readLong() throws IOException {
    long l = super.readLong();
    trace.trace32(l);
    return l;
  }
  
  public short readShort() throws IOException {
    short i = super.readShort();
    trace.traceHex(i,2);
    return i;
  }
  
  public int readUnsignedShort() throws IOException {
    int i = super.readUnsignedShort();
    trace.traceHex(i,2);
    return i;
  }
  
  public String readUTF() throws IOException {
    String s = super.readUTF();
    trace.traceString("\"" + s + "\"");
    return s;
  }

  // the following are non-primitive

  public IRNode readNode() throws IOException {
    debugBegin("node");
    IRNode node = super.readNode();
    debugEnd("node");
    return node;
  }
  
  public Object readCachedObject() throws IOException {
    debugBegin("cache");
    Object o = super.readCachedObject();
    debugEnd("cache");
    if (o == null) debugMark("cache_init");
    return o;
  }

  public IRType readIRType() throws IOException {
    debugBegin("type");
    IRType ty = super.readIRType();
    debugEnd("type");
    return ty;
  }

  public SlotFactory readSlotFactory() throws IOException {
    debugBegin("slotFactory");
    SlotFactory sf = super.readSlotFactory();
    debugEnd("slotFactory");
    return sf;
  }

  public IRPersistent readPersistentReference() throws IOException {
    debugBegin("reference");
    IRPersistent p = super.readPersistentReference();
    debugEnd("reference");
    return p;
  }

  /** Return whether debugging is active */
  public boolean debug() {
    return super.debug();
  }

  /** Inform debugging that a structure is beginning */
  public void debugBegin(String x) {
    trace.traceBegin(x);
  }

  /** Inform debugging that a structure is ending */
  public void debugEnd(String x) {
    trace.traceEnd(x);
  }

  /** Inform debugging that a point is passing */
  public void debugMark(String x) {
    trace.traceString(":" + x);
  }

  public void close() throws IOException {
    super.close();
    trace.close();
  }
}

/** Implementatation of IRInput for serialization of persistent objects. */
class IRObjectInputStream extends IRInputStream implements ObjectInput {

  /** Create an IRInput stream around an existing serialization stream.
   */
  public IRObjectInputStream(ObjectInputStream s) throws IOException {
    super(s,IRPersistent.magic1a);
  }

  public Object readObject() throws IOException, ClassNotFoundException {
    return ((ObjectInput)underlying).readObject();
  }

  public IRNode readNode() throws IOException {
    int i = super.readInt();
    if (i == 0) return null;
    IRPersistent st = IRPersistent.readReference(this);
    return st.getNode(i);
  }
}

class IRPersistentWrapper implements Serializable {
  transient IRPersistent persistent;

  IRPersistentWrapper(IRPersistent p) {
    persistent = p;
  }
  
  private void writeObject(ObjectOutputStream stream) throws IOException
  {
    persistent.writeReference(stream);
    if (stream instanceof IROutput) {
      persistent.write((IROutput)stream);
    } else if (stream instanceof ObjectOutputStream) {
      persistent.writeWrapper(new IRObjectOutputStream(stream));
    } else {
      throw new IOException("unknown stream type");
    }
  }

  private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
  {
    persistent = IRPersistent.readReference(stream);
    if (stream instanceof IRInput) {
      persistent.read((IRInput)stream);
    } else if (stream instanceof ObjectInputStream) {
      persistent.readWrapper(new IRObjectInputStream(stream));
    } else {
      throw new IOException("unknown stream type");
    }
  }

  public Object readResolve() {
    return persistent;
  }
}
