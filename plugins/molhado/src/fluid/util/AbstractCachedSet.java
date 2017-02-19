/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/AbstractCachedSet.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Hashtable;

/** A cached set is only allocated once for each array of elements.
 * This way we can use == to test set equality.  It also saves
 * memory usage.  To save mempory, the table used can be changed.
 * Either a static table can be declard (in a subclass) which is
 * periodically left to float away, or else a new instance of empty
 * and universe can be declared with a shared new table.
 * (Better would be a static "weak" hashtable.)
 */
public abstract class AbstractCachedSet extends ImmutableHashOrderSet {
  protected AbstractCachedSet(Object[] elements, boolean inverse) {
    super(elements,inverse);
  }

  /** Return the shared table used to hold instances. */
  protected abstract Hashtable getTable();

  public int cacheSize() { return getTable().size(); }

  protected abstract AbstractCachedSet
      newSet(Object elements[], boolean inverse);

  public AbstractCachedSet cacheSet(ImmutableHashOrderSet s) {
    Hashtable t = getTable();
    AbstractCachedSet c = (AbstractCachedSet)(t.get(s));
    if (c == null) {
      c = newSet(s.elements,s.inverse);
      t.put(c,c);
    }
    return c;
  }

  /* we simulate parasitic methods */
  public boolean equals(ImmutableHashOrderSet other) {
    if (other instanceof AbstractCachedSet) {
      return equals((AbstractCachedSet)other);
    } else {
      return super.equals(other);
    }
  }
  public boolean equals(AbstractCachedSet other) {
    if (getTable() == other.getTable()) {
      return this == other;
    } else {
      return super.equals(other);
    }
  }

  // Now we redefine all the methods that create new sets to cache the results.
  //! Java does not permit the return type to be changed in overriding methods
  public ImmutableHashOrderSet invert() {
    return cacheSet(super.invert());
  }
  public ImmutableHashOrderSet addElement(Object elem) {
    return cacheSet(super.addElement(elem));
  }
  public ImmutableHashOrderSet removeElement(Object elem) {
    return cacheSet(super.removeElement(elem));
  }
  public ImmutableHashOrderSet union(ImmutableHashOrderSet other) {
    return cacheSet(super.union(other));
  }
  public ImmutableHashOrderSet intersect(ImmutableHashOrderSet other) {
    return cacheSet(super.intersect(other));
  }
  public ImmutableHashOrderSet difference(ImmutableHashOrderSet other) {
    return cacheSet(super.difference(other));
  }

  // useful methods:
  public AbstractCachedSet empty() { return cacheSet(ImmutableHashOrderSet.empty); }
  public AbstractCachedSet universe() { return cacheSet(ImmutableHashOrderSet.universe); }
}
