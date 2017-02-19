package sc.document;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import fluid.FluidError;
import fluid.ir.IRInput;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentKind;
import fluid.ir.IRStringType;
import fluid.ir.Slot;
import fluid.util.Hashtable2;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
import fluid.version.VersionedSlot;
import fluid.version.VersionedSlotFactory;
import fluid.version.VersionedStructure;

/** A piece of versioned state that can be saved.
 * A component is namned and lives within a version
 * space defined by a {@link Configuration}.
 * @see Configuration.
 */

public abstract class Component extends IRPersistent 
	implements VersionedStructure {
  
  private static final int    magic = 0x434F4D50; // 'COMP'
  
  private               Slot    nameSlot; // VersionedSlot

  protected static final int  NAMEBIT = 1;
    
  // Constructor
  protected Component(int magic, boolean hasID) {
    super(magic,hasID);
    setDefaultNameValue();
  }
  
  protected Component(int magic, UniqueID id) {
    super(magic,id);
    setDefaultNameValue();
  }
  
  private void setDefaultNameValue() {
    nameSlot = 
        VersionedSlotFactory.prototype.predefinedSlot("#component#", this);
  }
  
  /** Set the name for this component */
  public void setName(String n) {
    nameSlot = nameSlot.setValue(n);
  }
  
  /** Return the name of this Component */
  public String getName() {
    return (String) nameSlot.getValue();
  }
  
  public String getName(Version v) {
    if (nameSlot.isValid()) {
      VersionedSlot vl = (VersionedSlot) nameSlot;
      return (String) vl.getValue(v);
    }
    else return null;
  }
  
  /** Return the factory of this component */
  public abstract ComponentFactory getFactory();
  
  public boolean isChanged() {
    return nameSlot.isChanged();
  }
  
  /** save/loadDelta and save/loadSnapshot for VersionedChunkDelta */
  public abstract void saveDelta(Era era,fluid.util.FileLocator floc) 
    throws IOException;

  public abstract void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException;

  public abstract void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException;

  public abstract void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException;
    
  /** write/readContents and write/readCC */
  public void writeContents(IROutput out) 
    throws IOException {
    int flags = 0;
    if (nameSlot.isValid()) flags |= NAMEBIT;
    out.writeByte(flags);
    
    if ((flags & NAMEBIT) != 0) 
      nameSlot.writeValue(IRStringType.prototype,out); // nameSlot is of type IRStringType
  }
  
  public void writeChangedContents(IROutput out) 
    throws IOException {
    int flags = 0;
    if (nameSlot.isChanged()) 
      flags |= NAMEBIT;
    out.writeByte(flags);
    if ((flags & NAMEBIT) != 0) 
      nameSlot.writeValue(IRStringType.prototype,out);
  }
  
  public void readContents(IRInput in) 
    throws IOException {
    int flags = in.readByte();
    if ((flags & NAMEBIT) != 0) 
      nameSlot = nameSlot.readValue(IRStringType.prototype,in);
  }
  
  public void readChangedContents(IRInput in) 
    throws IOException {
    int flags = in.readByte();
    if ((flags & NAMEBIT) != 0) 
      nameSlot = nameSlot.readValue(IRStringType.prototype,in);
  }
  
  protected void write(IROutput out) throws IOException {
    writeContents(out);
  }
  
  protected void read(IRInput in) throws IOException {
    readContents(in);
  }

  // Utilities
  
  public Delta getDelta(Era era) {
    return Delta.get(this,era);
  }
  
  public Snapshot getSnapshot(Version v) {
    return Snapshot.get(this,v);
  }
  
  /* persistent kind */
  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException
    {
      p.getID().write(out);
      // Write out the factory's name of the component
      Component comp = (Component) p;
      out.writeUTF(comp.getFactory().getName());
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException
    {
      UniqueID id = UniqueID.read(in);      
      Component comp = (Component) find(id);
      if (comp == null) {
        ComponentFactory 
          factory = ComponentFactory.getComponentFactory(in.readUTF());
        comp = factory.create(id,in);
      }
      else in.readUTF();
      return comp;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind,0x63); // 'c'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  public static void ensureLoaded() {
  }

  public void describe(PrintStream out) {
    super.describe(out);
    out.print((String) nameSlot.getValue());
  }

  public abstract String getFileName();
  
  public boolean isDefined(Version v) {
    
    Era e = v.getEra();
    while (e == null) {
      v = v.parent();
      e = v.getEra();
    }
    
    // System.out.println("e = " + e.toString());
    
    while (e.isNew() || e.isAssociated(this) < 0) {
      if (e == Era.getInitialEra()) {
        return isDefined();
      }
      v = e.getRoot();
      e = v.getEra();
    }

    // System.out.println(e.getID().toString());
    
    Delta d = Delta.find(this, e);
    if (d != null && d.isDefined(v)) {
      return true;
    }

    Snapshot s = Snapshot.find(this, v);
    if (s != null && s.isDefined()) {
      return true;
    }

    return false;
  }

  public boolean isDefined(Era e) {
  	if (e == Era.getInitialEra())
      return isDefined();
    if (e.isNew() || e.isAssociated(this) < 0) {
      boolean result = isDefined(e.getRoot());
      return result;
    }

    Delta d = Delta.find(this, e);
    if (d != null && d.isDefined(e.getVersion(e.maxVersionOffset())))
      return true;
    return false;
  }
  
  public void noteChange(Version v) {
  	v.addAssociated(this);
  }
  
  /** Private class Delta */
  
  public static class Delta extends IRPersistent {
    static final int magic = 0x43504400; // 'CPD\0'

    private final Era era;
    private final Component comp;

    private static Hashtable2 deltas = new Hashtable2();
    
    /** new one */
    Delta(Component c, Era e) {
      super(magic,false);
      era = e;
      if (!e.isNew()) 
        throw new FluidError("Creating new delta from old eras");
      comp = c;
    }
  
    /** Existing one */
    Delta(Component c, Era e, boolean unused) {
      super(magic,null);
      era = e;
      comp = c;
    }

    public Era getEra() { return era;}
    
    public Component getComponent() { return comp;}
    
    public static Delta find(Component c, Era e) {
      return (Delta) deltas.get(c,e);
    }
    
    private static void add(Delta d) {
      deltas.put(d.getComponent(),d.getEra(),d);
    }

    public static Delta get(Component c, Era e) {
      Delta d = (Delta) find(c, e);
      if (d == null) {
        if (e.isNew())
          d = new Delta(c, e);
        else
          d = new Delta(c, e, false);
        add(d);
      }
      return d;
    }

    public String getFileName() {
      return era.getID().toString()
            + File.separator
            + comp.getID()
            + ".cpd";
    }

    public IRPersistentKind getKind() { return null;}

    public boolean isDefined(Version v) {
    	if (!isDefined()) return false;
    	if (v.getEra() != era) return false;
    	if (isComplete() || isNew()) return true;
    	return (v.getEraOffset() <= era.maxVersionOffset());
    }

    // WRITE/READ
    
    public void write(IROutput out) throws IOException {
      VersionedSlot.pushEra(era); // ERROR : The class is not visible !!!
      comp.writeChangedContents(out);
    }

    protected void read(IRInput in) throws IOException {
      VersionedSlot.pushEra(era); // ERROR : The class is not visible !!!
      comp.readChangedContents(in);
    }

    public String toString() {
      return "CPD("
        + getComponent().getID()
        + ","
        + getEra()
        + ")";
    }

    public static void ensureLoaded() {
    }
  }

  // SNAPSHOT

  public static class Snapshot extends IRPersistent {
    static final int magic = 0x43505300; // 'CPS\0'

    private final Version version;
    private final Component comp;

    private static Hashtable2 snapshots = new Hashtable2();

    /** new one */
    Snapshot(Component c, Version v) {
      super(magic,false);
      version = v;
      comp = c;
    }
    
    /** existing one */
    Snapshot(Component c, Version v, boolean unused) {
      super(magic,null);
      version = v;
      comp = c;
    }
    
    public Version getVersion() { return version;}
    
    public Component getComponent() { return comp;}
    
    public static Snapshot find(Component c, Version v) {
      return (Snapshot) snapshots.get(c,v);
    }
    
    private static void add(Snapshot s) {
      snapshots.put(s.getComponent(),s.getVersion(),s);
    }

    /** Find or create a snapshot */
    public static Snapshot get(Component c, Version v) {
      Snapshot s = (Snapshot) find(c, v);
      if (s == null) {
        if (v.getEra() == null || v.getEra().isNew())
          s = new Snapshot(c,v);
        else s = new Snapshot(c,v,false);
        add(s);
      }
      return s;
    }

    public void write(IROutput out) throws IOException {
      comp.writeReference(out);
      comp.writeContents(out);
    }

    public void read(IRInput in) throws IOException {
      comp.readReference(in);
      comp.readContents(in);
    }

    public String toString() {
      Version version = getVersion();
      return "CPS("
        + getComponent().getName()
        + ","
        + version.getEra()
        + ","
        + version.getEraOffset()
        + ")";
    }

    public IRPersistentKind getKind() { return null;}

    public static void ensureLoaded() {
    }
  }
}
