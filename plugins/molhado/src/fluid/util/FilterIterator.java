package fluid.util;

import java.util.Iterator;

/**
 * An Iterator wrapper that only filters out objects in the original Iterator
 */

public abstract class FilterIterator extends SimpleIterator
{
  /** Iterator to wrap. */
  private final Iterator iterator;

  /**
   * Create a new iterator wrapped around an
   * existing iterator.
   * @param iter The iterator to wrap.
   */
  public FilterIterator( final Iterator iter ) {
    iterator = iter;
  }

  public void remove() {
    iterator.remove();
  }

  protected Object computeNext() {
    while(iterator.hasNext()) {
      Object o = select(iterator.next());
      if (o == notSelected) {
	// get more nodes to check 
	continue;
      }
      return o;
    }
    return noElement;
  }

  /** 
   * Returns the transformed object, or notSelected
   */
  protected abstract Object select(Object o);
  protected final Object notSelected = new Object();
}
