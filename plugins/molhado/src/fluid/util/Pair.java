/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/Pair.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

/** A simple pair, defined so we can define equality and hashCode. */
public class Pair {
  private final Object elem1, elem2;
  public Pair(Object o1, Object o2) {
    elem1 = o1;
    elem2 = o2;
  }
  public Object first() { return elem1; }
  public Object second() { return elem2; }
  public boolean equals(Object other) {
    return other instanceof Pair &&
      elem1.equals(((Pair)other).elem1) &&
      elem2.equals(((Pair)other).elem2);
  }
  public int hashCode() {
    return elem1.hashCode() + elem2.hashCode();
  }
}
