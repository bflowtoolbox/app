/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedStructureFactory.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.Bundle;
import fluid.ir.IRChunk;
import fluid.ir.IRNode;
import fluid.ir.IRRegion;
import fluid.ir.SlotInfo;
import fluid.util.Hashtable2;
import fluid.util.ThreadGlobal;

/** Helper class which can get versioned structures on demand */
class VersionedStructureFactory {
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR.persist");

  private static Hashtable2 nodeStructures = new Hashtable2();

  private static int count = 0;

  static class NodeStructure extends VersionedStructureProxy {
    final IRNode node;
    final Bundle bundle;
    NodeStructure(IRNode n, Bundle b) {
      node = n;
      bundle = b;
      if ((++count & 1023) == 0)
        LOG.warn(
          "!!!!! Created " + count + " node versioned structure proxies");
    }

    protected VersionedStructure computeReplacement() {
      if (!IRRegion.hasOwner(node))
        return null;
      IRRegion r = IRRegion.getOwner(node);
      IRChunk ch = IRChunk.get(r, bundle);
      nodeStructures.remove(node, bundle);
      return IRChunkVersionedStructure.get(ch);
    }
  };

  private static IRRegion cachedRegion;
  private static Bundle cachedBundle;
  private static VersionedStructure cachedResult;

  public static VersionedStructure get(IRNode node, SlotInfo si) {
    Bundle b = si.getBundle();
    IRRegion r = null;
    if (VersionedRegion.hasOwner(node))
      r = VersionedRegion.getOwner(node);
    if (r == null || b == null) {
      if (b == null) {
        //!! Better would be to set in place some hook.
        LOG.info("no bundle for " + si);
        return null;
      } else {
        NodeStructure vs = (NodeStructure) nodeStructures.get(node, b);
        if (vs == null) {
          vs = new NodeStructure(node, b);
          nodeStructures.put(node, b, vs);
          IRRegion.whenOwned(node, vs);
        }
        return vs;
      }
    }
    if (r == cachedRegion && b == cachedBundle)
      return cachedResult;

    if (r instanceof VersionedRegion) {
      VersionedChunk vc = VersionedChunk.get((VersionedRegion) r, b);
      // System.out.println("returning VC!");
      cachedResult = vc.getVersionedStructure(node);
    } else {
      IRChunk c = IRChunk.get(r, b);
      // System.out.println("returning IRVS");
      cachedResult = IRChunkVersionedStructure.get(c);
    }
    cachedRegion = r;
    cachedBundle = b;
    return cachedResult;
  }

  /* back-channel communication
   * used to set the VersionedStructure while writing things
   */
  private static final ThreadGlobal versionedStructureVar =
    new ThreadGlobal(null);
  static VersionedStructure getVS() {
    return (VersionedStructure) versionedStructureVar.getValue();
  }
  static void setVS(VersionedStructure vs) {
    versionedStructureVar.setValue(vs);
  }
  static void pushVS(VersionedStructure vs) {
    versionedStructureVar.pushValue(vs);
  }
  static void popVS() {
    versionedStructureVar.popValue();
  }
}
