/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/UnassignedVersionedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.IROutput;
import fluid.ir.IRType;

/** A versioned slot with only one value, the initial value.
 * This slot may be shared by multiple nodes.
 */
/*
 * $Log: UnassignedVersionedSlot.java,v $
 * Revision 1.1  2006/03/21 23:20:54  dig
 * first commit of sources
 *
 * Revision 1.2  2002/08/03 01:16:27  thallora
 * Log4j conversion of some files
 *
 * Revision 1.1  2001/09/18 18:39:25  boyland
 * Initial revision
 *
 * Revision 1.16  2000/07/17 21:08:27  boyland
 * Added printing and debugging.
 *
 * Revision 1.15  2000/02/21 22:31:00  boyland
 * *** empty log message ***
 *
 * Revision 1.14  2000/02/21 22:25:48  boyland
 * Added ability to detect missing deltas.
 *
 * Revision 1.13  1999/07/28 15:51:30  boyland
 * Now uses new ChangedVersionedSlot
 * Avoids redundant reads.
 *
 * Revision 1.12  1998/10/30 18:59:55  boyland
 * Removed hack for deltas.  (Now use isChanged().)
 *
 * Revision 1.11  98/10/20  15:51:43  boyland
 * Added persistence methods.
 * 
 */
public class UnassignedVersionedSlot extends DependentVersionedSlot {
  public UnassignedVersionedSlot() {
    super();
  }
  public UnassignedVersionedSlot(Object value) {
    super(value);
  }

  // VersionedSlot does most of our work for us,
  // we only need to define a few abstract methods:

  public Version getLatestChange(Version v) {
    return Version.getVersionLocal();
  }

  protected VersionedSlot setValue(Version v, Object newValue) {
    return new OnceAssignedVersionedSlot(initialValue, v, newValue);
  }

  public boolean isChanged(Era e) {
    return false;
  }

  public void writeValues(IRType ty, IROutput out, Era e) {
  }
}
