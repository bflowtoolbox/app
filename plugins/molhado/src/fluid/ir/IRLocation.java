/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRLocation.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.Vector;

/** An abstract point within a sequence.
 * A location has no meaning apart from its sequence.
 * In particular do not suppose the ID has anything to do
 * with the location's interpretation as a numeric place
 * in the sequence.
 */
public class IRLocation {
  private final int id;
  protected IRLocation(int i) {
    id = i;
  }
  public int getID() {
    return id;
  }

  public boolean equals(Object other) {
    if (other instanceof IRLocation) {
      IRLocation loc = (IRLocation) other;
      return loc.id == id;
    } else {
      return false;
    }
  }
  public int hashCode() {
    return id;
  }

  private static final Vector locations = new Vector();

  protected static IRLocation get(int i) {
    while (locations.size() <= i) {
      locations.addElement(new IRLocation(locations.size()));
    }
    return (IRLocation) locations.elementAt(i);
  }

  public String toString() {
    return String.valueOf(id);
  }

  public static IRLocation valueOf(String s) {
    if (s.compareTo("") == 0) {
      return null;
    } else {
      return get(Integer.parseInt(s));
    }
  }
}