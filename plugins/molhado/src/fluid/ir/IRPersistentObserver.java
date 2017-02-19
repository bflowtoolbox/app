/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRPersistentObserver.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** Class which wants to be informed when elements get
 * assigned to IRPersistent entities, for instance, when
 * a node is assigned to a region, or a SlotInfo to a Bundle.
 */
public interface IRPersistentObserver {
  public void updatePersistent(IRPersistent p, Object o);
}
