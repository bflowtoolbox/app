/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** The interface for descriptors of the types of (storable)
 * slots.
 * @see SlotInfo
 */
public interface IRType {
  boolean isValid(Object value);
  
  /**
   * Get the Comparator for elements of this type (if one exists). 
   * Reminder: Comparators are for <em>total</em> orders only.
   * @return The comparator for the type, if the type can be ordered;
   *         <code>null</code> if the type has no comparator.
   */
  Comparator getComparator();

  /** Write a value out. */
  void writeValue(Object value, IROutput out) throws IOException;
  
  /** Read a value in. */
  Object readValue(IRInput in) throws IOException;

  /** Write the type out (starting with a registered byte). */
  void writeType(IROutput out) throws IOException;

  /** Read a type in continuing after the registered byte. */
  IRType readType(IRInput in) throws IOException;

  /** make value (of some IRType) given in string */
  public Object fromString(String s);

  /** covert value o (of some IRType) to String */
  public String toString(Object o);
}
