package fluid.util;

import java.util.Enumeration;

/**
 * An Iterator created around an underlying 
 * {@link Enumeration}.  The {@link java.util.Iterator#remove()}
 * operation is not supported.
 */

public class EnumerationIterator
extends AbstractRemovelessIterator
{
  /** Enumeration to wrap. */
  private final Enumeration enumeration;

  /**
   * Create a new iterator wrapped around an
   * existing enumeration.
   * @param en The enumeration to wrap.
   */
  public EnumerationIterator( final Enumeration en )
  {
    enumeration = en;
  }

  public boolean hasNext()
  {
    return enumeration.hasMoreElements();
  }

  public Object next()
  {
    return enumeration.nextElement();
  }
}
