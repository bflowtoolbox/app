/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/PairEnumeration.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class PairEnumeration implements Enumeration {
  private int done = 0;
  private final Object value1, value2;

  public PairEnumeration(Object v1, Object v2) {
    value1 = v1;
    value2 = v2;
  }

  public boolean hasMoreElements() {
    return done < 2;
  }

  public Object nextElement() {
    switch (++done) {
    case 1: return value1;
    case 2: return value2;
    }
    throw new NoSuchElementException("enumeration complete");
  }
}

