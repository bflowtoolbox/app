/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SimpleEnumeration.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/** An enumeration class with one way to get the next element.
 */
public abstract class SimpleEnumeration implements Enumeration {
  public SimpleEnumeration() { super(); }
  protected final Object noElement = this; // used to indicate no element
  protected Object nextElement = noElement;

  public boolean hasMoreElements() {
    if (nextElement != noElement) return true;
    nextElement = computeNextElement();
    return nextElement != noElement;
  }

  public Object nextElement() {
    if (nextElement != noElement) {
      try {
	return nextElement;
      } finally {
	nextElement = noElement;
      }
    } else {
      Object element = computeNextElement();
      if (element == noElement) throwException();
      return element;
    }
  }

  /** Return the next element or return noElement */
  protected abstract Object computeNextElement();

  protected void throwException() {
    throw new NoSuchElementException("end of enumeration");
  }
}
