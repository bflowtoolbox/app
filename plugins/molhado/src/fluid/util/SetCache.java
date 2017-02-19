/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SetCache.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.util;

import java.util.Hashtable;

/** Class which implements a mapping from sets to cached sets.
 * It also keeps track of the shared empty and universe sets.
 * @see SetLattice
 */
public class SetCache extends Hashtable {
  public final SetLattice empty, universe;

  public SetCache(SetLattice e) {
    super();
    put(e,e);
    SetLattice u = e.newSet(this,new Object[0],true);
    put(u,u);
    empty = e;
    universe = u;
  }
}
