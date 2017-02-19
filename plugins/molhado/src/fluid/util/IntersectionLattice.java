/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/IntersectionLattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;


/** A lattice that uses intersections to work down the lattice.
 * Use the public constructor IntersectionLattice() to create
 * a new (separately garbage-collected) lattice.
 */
public final class IntersectionLattice extends SetLattice {
  public IntersectionLattice() {
    super();
  }
  private IntersectionLattice(IntersectionLattice l,
			      Object[] elements, boolean inverse)
  {
    super(l,elements,inverse);
  }
  private IntersectionLattice(SetCache c, Object[] elements, boolean inverse)
  {
    super(c,elements,inverse);
  }

  protected AbstractCachedSet newSet(Object[] elements, boolean inverse) {
    return new IntersectionLattice(this,elements,inverse);
  }
  protected SetLattice newSet(SetCache c, Object[] elements, boolean inverse) {
    return new IntersectionLattice(c,elements,inverse);
  }

  public Lattice top() {
    return ((SetCache)getTable()).universe;
  }

  public Lattice bottom() {
    return ((SetCache)getTable()).empty;
  }

  public Lattice meet(Lattice other) {
    return (Lattice)intersect((ImmutableHashOrderSet)other);
  }

  public Lattice join(Lattice other) {
    return (Lattice)union((ImmutableHashOrderSet)other);
  }

  public boolean includes(Lattice other) {
    return includes((ImmutableHashOrderSet)other);
  }
}
