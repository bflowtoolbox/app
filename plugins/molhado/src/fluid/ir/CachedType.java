/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/CachedType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** A mixin for IR types that use caching for storage.
 * The methods are implemented 
 * @see IRInput#readCachedObject
 * @see IROutput#writeCachedObject
 */
public abstract class CachedType implements IRType {
  public void writeValue(Object value, IROutput out)
       throws IOException
  {
    if (out.writeCachedObject(value)) return;
    writeValueInternal(value,out);
  }

  /** Write the contents of a cached object to the output stream. */
  protected void writeValueInternal(Object value, IROutput out)
       throws IOException
  {
    // override to write internal state.
  }

  public Object readValue(IRInput in)
       throws IOException
  {
    Object cached = in.readCachedObject();
    if (cached != null) return cached;
    cached = createValue(in);
    in.cacheReadObject(cached);
    initValue(cached,in);
    return cached;
  }

  /** Create the cached object.  The parameter stream
   * may be used to get information about the identity or
   * class of the object but must not be used to read
   * other cached objects.
   * @see #initValue
   */
  protected Object createValue(IRInput in)
       throws IOException
  {
    // override for creation
    return new Object();
  }

  /** Initialize the contents of the object.
   */
  protected void initValue(Object object, IRInput in)
       throws IOException
  {
    // override to read contents
  }
}
