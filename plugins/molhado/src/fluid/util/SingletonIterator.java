package fluid.util;

import java.util.NoSuchElementException;

/**
 * An Iterator that contains a single element.
 */

public class SingletonIterator
extends AbstractRemovelessIterator
{
  /** element to wrap. */
  private final Object object;
  private boolean hasNext;

  /**
   * Create a new iterator wrapped around a single element.
   * @param item The element to wrap.
   */
  public SingletonIterator( final Object item )
  {
    super();
    object = item;
    hasNext = true;
  }

  public boolean hasNext()
  {
    return hasNext;
  }

  public Object next()
  {
    if( hasNext ) {
      hasNext = false;
      return object;
    } else {
      throw new NoSuchElementException( "No more elements" );
    }
  }
}
