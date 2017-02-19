package fluid.util;

import java.util.Enumeration;

/**
 * An Enumeration wrapper that only filters out objects in the original Enumeration
 */

public abstract class FilterEnumeration extends SimpleEnumeration
{
  /** Enumeration to wrap. */
  protected final Enumeration iterator;

  /**
   * Create a new iterator wrapped around an
   * existing iterator.
   * @param iter The iterator to wrap.
   */ 
  public FilterEnumeration( final Enumeration iter ) {
    iterator = iter;
  }

  protected Object computeNext() {
    while(iterator.hasMoreElements()) {
      Object o = select(iterator.nextElement());
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
  protected Object select(Object o) { return o; }
  protected final Object notSelected = new Object();
}
