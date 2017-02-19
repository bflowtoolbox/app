/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRLocationType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

public class IRLocationType implements IRType {
  public IRLocationType() {
  }
  public static IRLocationType prototype = new IRLocationType();
  static {
    IRPersistent.registerIRType(prototype, 'L');
  }
  /** Return whether the argument is a value that can be stored
   * as an IRLocation.
   * @return true if an IRLocation or null.
   */
  public boolean isValid(Object x) {
    return x == null || x instanceof IRLocation;
  }

  public Comparator getComparator() {
    return null;
  }

  /** Write a valid location to an output stream.
   * @param v a valid location
   * @param out the output stream
   * @see #isValid
   * @throws IOException if error happens while writing
   */
  public void writeValue(Object v, IROutput out) throws IOException {
    IRLocation loc = (IRLocation) v;
    if (loc == null) {
      out.writeInt(-1);
    } else {
      out.writeInt(loc.getID());
    }
  }
  /** Read a location from the stream.
   * @return a valid location
   * @param in the input stream.
   * @see #isValid
   * @throws IOException if error happens while reading
   */
  public Object readValue(IRInput in) throws IOException {
    int id = in.readInt();
    if (id == -1) {
      return null;
    } else {
      return IRLocation.get(id);
    }
  }
  public void writeType(IROutput out) throws IOException {
    out.writeByte('L');
  }
  public IRType readType(IRInput in) {
    return this;
  }

  public Object fromString(String s) {
    return IRLocation.valueOf(s);
  }

  public String toString(Object o) {
    if (o == null)
      return "";

    final IRLocation irloc = (IRLocation) o;
    return irloc.toString();
  }
}