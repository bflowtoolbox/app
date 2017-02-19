/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRSequence.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;

/** A collection of values in order.
 * Some sequences are fixed in size, others are variable.
 * The insert/append/remove methods are legal only for
 * the latter.
 * @see IRArray
 * @see IRList
 */
public interface IRSequence extends IRCompound {
  int size();
  boolean isVariable();

  /** Return true if has any children,
   * that is, if there exists a first location.
   */
  boolean hasElements();

  /** Return an enumeration of the elements in the sequence
   * <em>in order</em>.
   */
  Enumeration elements();

  /** Return whether element #i is defined. */
  boolean validAt(int i);
  boolean validAt(IRLocation loc);

  /** Return the i'th element (0 based) */
  Object elementAt(int i);
  Object elementAt(IRLocation loc);

  /** Set the i'th element (0 based) */
  void setElementAt(Object element, int i);
  void setElementAt(Object element, IRLocation loc);

  /** Insert an element at the beginning of the sequence.
   * @return the new location.
   */
  IRLocation insertElement(Object element);
  IRLocation appendElement(Object element);
  /** Insert a new element after an existing location.
   * @return new location inserted.
   */
  IRLocation insertElementBefore(Object element, IRLocation i);
  IRLocation insertElementAfter(Object element, IRLocation i);
  void removeElementAt(IRLocation i);

  /** Return i'th location (0 based) as location object. */
  IRLocation location(int i);
  /** Return integer (0 based) for given location. */
  int locationIndex(IRLocation loc);

  /** Return first location of sequence or null if sequence is empty. */
  IRLocation firstLocation();
  /** Return last location of sequence or null if sequence is empty. */
  IRLocation lastLocation();
  /** Return location after the given one or null if at end of sequence. */
  IRLocation nextLocation(IRLocation loc);
  /** Return location before the given one or null if at start of sequence. */
  IRLocation prevLocation(IRLocation loc);

  /** Return one of <dl>
   * <dt>&lt; 0<dd> if loc1 precedes loc2,
   * <dt>&gt; 0<dd> if loc1 follows loc2,
   * <dt>= 0<dd> if loc1 equals loc2.</dl>
   */
  int compareLocations(IRLocation loc1, IRLocation loc2);

  void writeValue(IROutput out) throws IOException;

  /** Describe for debugging purposes. */
  public void describe(PrintStream out);
}
