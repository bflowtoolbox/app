package fluid.util;

import java.util.Iterator;

public abstract class IteratorWrapper
implements Iterator
{
  private final Iterator iter;
  
  public IteratorWrapper( final Iterator i ) {
    iter = i;
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
  
  public final boolean hasNext() {
    return iter.hasNext();
  }
  
  public final Object next() {
    final Object o = iter.next();
    return processObject( o );
  }
  
  protected abstract Object processObject( Object o );
}
