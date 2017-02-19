package fluid.util;

import java.util.Iterator;

/**
 * An Iterator wrapper that can both filter and expand objects (e.g. if they
 * are Iterators) in the original Iterator
 */

public abstract class ProcessIterator extends SimpleRemovelessIterator
{
  private final Iterator iterator;  /** Iterator to wrap. */

  // null means we're done
  private Iterator nestedIter = EmptyIterator.prototype; 

  /**
   * Create a new iterator wrapped around an
   * existing iterator.
   * @param iter The iterator to wrap.
   */
  public ProcessIterator( final Iterator iter ) {
    iterator = iter;
  }

  protected Object computeNext() {
    while (nestedIter != null) {
      while(nestedIter.hasNext()) {
	Object o = select(nestedIter.next());
	if (o == notSelected) {
	  // get more nodes to check 
	  continue;
	}
	return o;
      }
      if (!iterator.hasNext()) {
	nestedIter = null;
	return noElement;
      }
      nestedIter = getNextIter(iterator.next());
    }
    return noElement;
  }

  protected Iterator getNextIter(Object o) { return (Iterator) o; }

  protected Object select(Object o) { return o; }

  protected final Object notSelected = new Object();
}

