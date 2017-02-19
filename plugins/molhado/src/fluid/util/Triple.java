/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/Triple.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.util;

/** A simple triple, defined so we can define equality and hashCode. */
public class Triple {
  private final Object elem1, elem2, elem3;
  public Triple(Object o1, Object o2, Object o3) {
    elem1 = o1;
    elem2 = o2;
    elem3 = o3;
  }
  public Object first() { return elem1; }
  public Object second() { return elem2; }
  public Object third() { return elem3; }
  public boolean equals(Object other) {
    return other instanceof Triple &&
      elem1.equals(((Triple)other).elem1) &&
      elem2.equals(((Triple)other).elem2) &&
      elem3.equals(((Triple)other).elem3);
  }
  public int hashCode() {
    return elem1.hashCode() + elem2.hashCode() + elem3.hashCode();
  }
}
