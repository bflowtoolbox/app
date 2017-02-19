// $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SimpleIterator.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** An Iterator class with one way to get the next element.
 */
public abstract class SimpleIterator implements Iterator {
  public SimpleIterator() { super(); }
  protected final Object noElement = this; // used to indicate no element
  private Object nextElement = noElement;

  public boolean hasNext() {
    if (nextElement != noElement) return true;
    nextElement = computeNext();
    return nextElement != noElement;
  }

  public Object next() {
    if (nextElement != noElement) {
      try {
	return nextElement;
      } finally {
	nextElement = noElement;
      }
    } else {
      Object next = computeNext();
      if (next == noElement) throwException();
      return next;
    }
  }

  /** Return the next element or return noElement */
  protected abstract Object computeNext();

  protected void throwException() {
    throw new NoSuchElementException("end of Iterator");
  }
}
