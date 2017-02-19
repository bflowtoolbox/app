/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/IRChunkVersionedStructure.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.IRChunk;

/** An adapter class for IRChunks to betreated as versioned structures.
 * It is light-weight.  The chunk should include versioned attributes.
 */
class IRChunkVersionedStructure implements VersionedStructure {
  private final IRChunk chunk;

  IRChunkVersionedStructure(IRChunk c) {
    chunk = c;
  }

  static VersionedStructure get(IRChunk c) {
    return new IRChunkVersionedStructure(c); // eventually could cache
  }

  private Version definedAt = null;

  /** Has the chunk been defined for this version?
   * @return true if the version has not yet been assigned to
   * an era, or if the corresponding snapshot or delta is defined.
   */
  public boolean isDefined(Version v) {
    if (v == definedAt)
      return true;
    Version savedV = v;
    Era e = v.getEra();
    while (e == null) {
      v = v.parent();
      e = v.getEra();
    }
    while (e.isNew() || e.isAssociated(chunk) < 0) {
      if (e == Era.getInitialEra()) {
        definedAt = savedV;
        return true;
      }
      v = e.getRoot();
      e = v.getEra();
    }
    //System.out.println(e + " is associated with " + chunk);

    VersionedDelta vd = VersionedDelta.find(chunk, e);
    if (vd != null && vd.isDefined(v)) {
      definedAt = savedV;
      return true;
    }

    VersionedSnapshot vs = VersionedSnapshot.find(chunk, v);
    if (vs != null && vs.isDefined()) {
      definedAt = savedV;
      return true;
    }

    // chunk.describe(System.out);
    // System.out.println("  not defined at " + v);
    return false;
  }

  /** Is the chunk defined for this era,
   * or does it need to be loaded first?
   */
  public boolean isDefined(Era e) {
    if (e == Era.getInitialEra())
      return true;
    if (e.isNew() || e.isAssociated(chunk) < 0) {
      boolean result = isDefined(e.getRoot());
      return result;
    }

    VersionedDelta vd = VersionedDelta.find(chunk, e);
    if (vd != null && vd.isDefined(e.getVersion(e.maxVersionOffset())))
      return true;
    return false;
  }

  private Version changeNoted = null;

  public void noteChange(Version v) {
    if (v == changeNoted)
      return;
    v.addAssociated(chunk);
    changeNoted = v;
  }
}
