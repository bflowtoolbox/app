package fluid.util;

import java.util.Iterator;

/**
 * An Iterator wrapper that never supports the 
 * {@link Iterator#remove()} operation.
 */

public class RemovelessIterator
extends AbstractRemovelessIterator
{
  /** Iterator to wrap. */
  private final Iterator iterator;

  /**
   * Create a new iterator wrapped around an
   * existing iterator.
   * @param iter The iterator to wrap.
   */
  public RemovelessIterator( final Iterator iter )
  {
    super();
    iterator = iter;
  }

  public boolean hasNext()
  {
    return iterator.hasNext();
  }

  public Object next()
  {
    return iterator.next();
  }
}
