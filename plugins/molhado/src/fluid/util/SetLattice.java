/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SetLattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Hashtable;

/** Abstract class for lattices over sets.
 * It is implemented using a cached set type, and a new cache
 * is created every time a SetLattice() is created, thus allowing
 * the cache to be garbage collected when none of the elements
 * of the lattice are referenced any more.
 * @see UnionLattice
 * @see IntersectionLattice
 */
public abstract class SetLattice extends AbstractCachedSet implements Lattice {
  private final SetCache cache;

  public SetLattice() {
    super(new Object[0],false);
    cache = createCache();
  }
  protected SetCache createCache() {
    return new SetCache(this);
  }
    
  /** Constructor to be called by newSet(Object[],boolean) */
  protected SetLattice(SetLattice old, Object[] elements, boolean inverse) {
    super(elements,inverse);
    cache = old.cache;
  }

  /** Constructor to be called by newSet(SetCache,Object[],boolean) */
  protected SetLattice(SetCache c, Object[] elements, boolean inverse) {
    super(elements,inverse);
    cache = c;
  }
  protected abstract SetLattice newSet(SetCache cache,
				       Object[] elements,
				       boolean inverse);

  protected Hashtable getTable() {
    return cache;
  }
}
