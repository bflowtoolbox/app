/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SingletonEnumeration.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class SingletonEnumeration implements Enumeration {
  private boolean done = false;
  private final Object value;

  public SingletonEnumeration(Object v) {
    value = v;
  }

  public boolean hasMoreElements() {
    return !done;
  }

  public Object nextElement() {
    if (done) throw new NoSuchElementException("enumeration complete");
    done = true;
    return value;
  }
}

