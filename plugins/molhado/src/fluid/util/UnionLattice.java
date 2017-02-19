/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/UnionLattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;


/** A lattice that uses unions to work down the lattice.
 * To create a new (separately garbage-collectable) lattice,
 * use the public constructor UnionLattice.
 */
public final class UnionLattice extends SetLattice {
  public UnionLattice() {
    super();
  }
  private UnionLattice(UnionLattice l, Object[] elements, boolean inverse) {
    super(l,elements,inverse);
  }
  private UnionLattice(SetCache c, Object[] elements, boolean inverse) {
    super(c,elements,inverse);
  }

  protected AbstractCachedSet newSet(Object[] elements, boolean inverse) {
    return new UnionLattice(this,elements,inverse);
  }
  protected SetLattice newSet(SetCache c, Object[] elements, boolean inverse) {
    return new UnionLattice(c,elements,inverse);
  }

  public Lattice top() {
    return ((SetCache)getTable()).empty;
  }

  public Lattice bottom() {
    return ((SetCache)getTable()).universe;
  }

  public Lattice meet(Lattice other) {
    return (Lattice)union((ImmutableHashOrderSet)other);
  }

  public Lattice join(Lattice other) {
    return (Lattice)intersect((ImmutableHashOrderSet)other);
  }

  public boolean includes(Lattice other) {
    return ((ImmutableHashOrderSet)other).includes(this);
  }
}
