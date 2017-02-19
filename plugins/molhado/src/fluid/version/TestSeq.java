package fluid.version;

import java.io.IOException;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRChunk;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.ir.IRRegion;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.Digraph;
import fluid.util.FileLocator;
import fluid.util.PathFileLocator;
import fluid.util.UniqueID;

class TestSeq {
  static FileLocator fl = new PathFileLocator("test-seq");
  static IRRegion r;
  static IRNode n1, n2, n3;
  static Digraph dig;
  static {
    try {
      dig = new Digraph("TestSeq",VersionedSlotFactory.prototype);
    } catch (SlotAlreadyRegisteredException ex) {
      throw new FluidError(ex.toString());
    }
  }
  static Bundle b;
  static Era e12, e13, e123, e132;
  static Version[] versions;

  public static void main(String[] args) throws IOException {
    Era.ensureLoaded();
    VersionedRegion.ensureLoaded();
    VersionedChunk.ensureLoaded();
    Bundle.ensureLoaded();
    fluid.util.SimpleApp.configure("MyApp");
    VersionedSlot.debugOn();
    IRPersistent.setTraceIO(true);
    IRPersistent.setDebugIO(true);
    if (args.length <= 0) usage();
    if (args[0].equals("--store")) {
      create();
      print();
      store();
    } else {
      load(args);
      print();
    }
  }

  public static void create() {
    r = new IRRegion();
    b = new Bundle();
    dig.saveAttributes(b);
    n1 = new PlainIRNode(r);
    n2 = new PlainIRNode(r);
    n3 = new PlainIRNode(r);
    r.complete();

    e12 = new Era(Version.getVersion());
    Version.setDefaultEra(e12);
    dig.initNode(n1);
    dig.initNode(n2);
    dig.initNode(n3);
    dig.addChild(n1,n2);
    e12.complete();
//    e12.describe(System.out);

    e13 = new Era(Version.getVersion());
    Version.setDefaultEra(e13);
    dig.removeChild(n1,n2);
    dig.appendChild(n1,n3);
    // dig.setChild(n1,0,n3);
    e13.complete();
//    e13.describe(System.out);

    e132 = new Era(Version.getVersion());
    Version.setDefaultEra(e132);
    dig.appendChild(n1,n2);
    e132.complete();
    e132.describe(System.out);

    Version.setVersion(e13.getRoot());
    e123 = new Era(e13.getRoot());
    Version.setDefaultEra(e123);
    dig.addChild(n1,n3);
    e123.complete();
    e123.describe(System.out);

    versions = new Version[]{e12.getVersion(1),
			     e13.getVersion(1),
			     e132.getVersion(1),
			     e123.getVersion(1)};
  }

  static void print() {
    int nv = versions.length;
    System.out.println("r = " + r + ", n1 = " + n1 +
		       ", n2 = " + n2 + ", n3 = " + n3);
    for (int i=0; i < nv; ++i) {
      System.out.println("In versions[" + i + "] = " + versions[i]);
      System.out.println("The Era is ");
      versions[i].getEra().describe(System.out);
      Version.setVersion(versions[i]);
      System.out.println("  n1's structure is");
      dig.describeNode(n1,System.out);
      System.out.println();
      System.out.print("n1.children = {");
      for (java.util.Enumeration en = dig.children(n1); en.hasMoreElements();) {
	System.out.print(en.nextElement()+ " ");
      }
      System.out.println("}");
    }
  }

  static void store() throws IOException {
    e12.store(fl);
    e13.store(fl);
    e132.store(fl);
    e123.store(fl);
    b.store(fl);
    IRChunk ch = IRChunk.get(r,b);    
    VersionedDelta.get(ch,e12).store(fl);
    VersionedDelta.get(ch,e13).store(fl);
    VersionedDelta.get(ch,e132).store(fl);
    VersionedDelta.get(ch,e123).store(fl);
    System.out.println("gmake RUNARGS=\"--load " +
		       e12.getID() + " " +
		       e13.getID() + " " +
		       e132.getID() + " " +
		       e123.getID() + " " +
		       b.getID() + " " +
		       r.getID() + "\" TestSeq.run");
  }

  static void load_simple(String[] args) throws IOException {
    if (args.length != 7) usage();
    e12 = Era.loadEra(UniqueID.parseUniqueID(args[1]),fl);
    e13 = Era.loadEra(UniqueID.parseUniqueID(args[2]),fl);
    e132 = Era.loadEra(UniqueID.parseUniqueID(args[3]),fl);
    e123 = Era.loadEra(UniqueID.parseUniqueID(args[4]),fl);
    b = Bundle.loadBundle(UniqueID.parseUniqueID(args[5]),fl);
    r = IRRegion.getRegion(UniqueID.parseUniqueID(args[6]));
    IRChunk ch = IRChunk.get(r,b);    
    VersionedDelta.get(ch,e12).load(fl);
    VersionedDelta.get(ch,e13).load(fl);
    VersionedDelta.get(ch,e132).load(fl);
    VersionedDelta.get(ch,e123).load(fl);    
    versions = new Version[]{e12.getVersion(1),
			     e13.getVersion(1),
			     e132.getVersion(1),
			     e123.getVersion(1)};
    n1 = r.getNode(1);
    n2 = r.getNode(2);
    n3 = r.getNode(3);
  }

  static void load(String[] args) throws IOException {
    if (args.length != 7) usage();
    e12 = Era.loadEra(UniqueID.parseUniqueID(args[1]),fl);
    e13 = Era.loadEra(UniqueID.parseUniqueID(args[2]),fl);
    e132 = Era.loadEra(UniqueID.parseUniqueID(args[3]),fl);
    // e123 = Era.loadEra(UniqueID.parseUniqueID(args[4]),fl);
    b = Bundle.loadBundle(UniqueID.parseUniqueID(args[5]),fl);
    r = IRRegion.getRegion(UniqueID.parseUniqueID(args[6]));
    n1 = r.getNode(1);
    n2 = r.getNode(2);
    n3 = r.getNode(3);
    IRChunk ch = IRChunk.get(r,b);    
    VersionedDelta.get(ch,e12).load(fl);
    VersionedDelta.get(ch,e13).load(fl);
    VersionedDelta.get(ch,e132).load(fl);
    mutate();
    Version mutated = Version.getVersion();

    // load old branch
    e123 = Era.loadEra(UniqueID.parseUniqueID(args[4]),fl);
    VersionedDelta.get(ch,e123).load(fl);    

    versions = new Version[]{e12.getVersion(1),
			     e13.getVersion(1),
			     e132.getVersion(1),
			     mutated,
			     e123.getVersion(1)};
  }

  static void mutate() {
    Version v = e132.getVersion(1);
    Era enew = new Era(v);
    Version.setVersion(v);
    Version.setDefaultEra(enew);

    dig.removeChild(n1,n3);
  }

  static void usage() {
    System.err.println("usage: --store, or --load + 6 args");
    System.exit(1);
  }
}
