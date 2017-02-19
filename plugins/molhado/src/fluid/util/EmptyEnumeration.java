/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/EmptyEnumeration.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class EmptyEnumeration implements Enumeration {
  public static final EmptyEnumeration prototype = new EmptyEnumeration();

  public EmptyEnumeration() { }

  public boolean hasMoreElements() {
    return false;
  }

  public Object nextElement() {
    throw new NoSuchElementException("enumeration complete");
  }
}
