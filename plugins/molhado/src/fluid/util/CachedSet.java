/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/CachedSet.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Hashtable;

/** A simple implementation of AbstractCachedSet with a static hashtable.
 */
public final class CachedSet extends AbstractCachedSet {
  private final int generation = current_generation;
  private static int current_generation = 0;

  protected CachedSet(Object[] elements, boolean inverse) {
    super(elements,inverse);
  }

  public boolean equals(AbstractCachedSet other) {
    /* The original definition (in AbstractCachedSet) doesn't
     * work because it assumes that the table storing an instance
     * can be reached from the instance.  Instead we must
     * use generations.
     */
    if (other instanceof CachedSet &&
	((CachedSet)other).generation == generation)
      return other == this;
    else // we need super^2 to implement without the new static method:
      return ImmutableHashOrderSet.equals(other,this);
  }

  /** Return the shared table used to hold instances. */
  private static Hashtable table = new Hashtable();

  protected Hashtable getTable() {
    return table;
  }

  public static void clearCache() {
    table = new Hashtable();
    ++current_generation;
  }

  protected AbstractCachedSet newSet(Object elements[], boolean inverse) {
    return new CachedSet(elements,inverse);
  }

  private static CachedSet prototype = new CachedSet(new Object[]{},false);
  public static CachedSet getEmpty() {
    return (CachedSet)(prototype.cacheSet(ImmutableHashOrderSet.empty));
  }

  public static CachedSet getUniverse() {
    return (CachedSet)(prototype.cacheSet(ImmutableHashOrderSet.universe));
  }
}
