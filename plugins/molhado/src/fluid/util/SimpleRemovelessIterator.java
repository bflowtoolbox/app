// $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SimpleRemovelessIterator.java,v 1.1 2006/03/21 23:20:53 dig Exp $
package fluid.util;


/** An Iterator class with one way to get the next element.
 */
public abstract class SimpleRemovelessIterator extends SimpleIterator {
  public SimpleRemovelessIterator() { super(); }
  public void remove()
  {
    throw new UnsupportedOperationException( "remove() not supported" );
  }
}
