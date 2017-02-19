/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRStringType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** The type of slots storing strings.
 */

public class IRStringType implements IRType, Comparator {
  private IRStringType() {
  }
  public static IRStringType prototype = new IRStringType();
  static {
    IRPersistent.registerIRType(prototype, 'S');
  }
  public boolean isValid(Object x) {
    return x instanceof String;
  }

  public int compare(final Object o1, final Object o2) {
    final String s1 = (String) o1;
    final String s2 = (String) o2;
    return s1.compareTo(s2);
  }

  public Comparator getComparator() {
    return this;
  }

  public void writeValue(Object v, IROutput out) throws IOException {
    out.writeUTF((String) v);
  }
  public Object readValue(IRInput in) throws IOException {
    return in.readUTF();
  }
  public void writeType(IROutput out) throws IOException {
    out.writeByte('S');
  }
  public IRType readType(IRInput in) {
    return this;
  }

  public Object fromString(String s) {
    return s;
  }

  public String toString(Object o) {
    final String s = (String) o;
    return s;
  }
}