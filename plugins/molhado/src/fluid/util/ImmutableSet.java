package fluid.util;

import java.util.Set;

/** A set with fixed membership: a mathematical value.
 * This interface includes support for unions, intersections
 * differences and (optionally) inverses.
 * An inverse of a finite set is always infinite.
 * Request an iterator of an infinite set, or
 * asking the elements to be listed in an array will not
 * give useful results.
 */
public interface ImmutableSet extends Set {
  /** Returns true if the set is infinite.
   * In this case, it is usually unwise to ask for an iteration
   * of all the elements or to ask for an array
   * of all of the elements.
   */
  public boolean isInfinite();

  /** Return the set that includes the given element. */
  public ImmutableSet addCopy(Object element);

  /** Return the inverse of this set: which includes
   * everything this set does not include.
   * @exception UnsupportedOperationException
   * if this set does not support the inversion operation.
   */
  public ImmutableSet invertCopy() throws UnsupportedOperationException;

  /** Return the set that does not include the given element. */
  public ImmutableSet removeCopy(Object element);

  /** Return the mathematical union of two sets. */
  public ImmutableSet union(Set other);

  /** Return the mathematical union of two sets. */
  public ImmutableSet intersection(Set other);

  /** Return the mathematical difference of two sets:
   * The result has all the elements of this set that
   * are not contained in the argument set.
   */
  public ImmutableSet difference(Set other);

}
