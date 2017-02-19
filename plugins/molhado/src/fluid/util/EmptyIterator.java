/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/EmptyIterator.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator with no elements.
 */
public class EmptyIterator
implements Iterator
{
  public static final EmptyIterator prototype = new EmptyIterator();

  public EmptyIterator()
  {
  }

  public boolean hasNext()
  {
    return false;
  }

  public Object next()
  {
    throw new NoSuchElementException( "Iterator complete." );
  }

  public void remove()
  {
    throw new IllegalStateException( "Can't remove from an empty iterator." );
  }
}
