package fluid.util;

import java.util.Iterator;

/**
 * An Iterator that never supports the 
 * {@link Iterator#remove()} operation.
 */

public abstract class AbstractRemovelessIterator
implements Iterator
{
  /**
   * Create a new iterator
   */
  public AbstractRemovelessIterator()
  {
    super();
  }

  /**
   * This operation is not supported.
   * @throws UnsupportedOperationException Always thrown.
   */
  public void remove()
  {
    throw new UnsupportedOperationException( "remove() not supported" );
  }
}
