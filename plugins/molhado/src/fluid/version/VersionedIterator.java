// $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedIterator.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.version;

import java.util.Iterator;

import fluid.util.AbstractRemovelessIterator;

/** A wrapper around an ietartor that sets the version correctly
 * before calling the iterator functions.  It does not support
 * remove. (It would not make sense to support remove,
 * since versioned state is immutable.)
 * @see VersionedEnumeration
 */
public final class VersionedIterator
  extends AbstractRemovelessIterator
  implements Iterator {
    /**
   * Log4j logger for this class
   */
  protected static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR.version");
    
  final Version v;
  final Iterator i;

  public VersionedIterator(Version v, Iterator i) {
    super();
    this.v = v;
    this.i = i;
  }

  public boolean hasNext() {
    try {
      Version.saveVersion();
      Version.setVersion(v);
      return i.hasNext();
    } finally {
      Version.restoreVersion();
    }
  }

  public Object next() {
    try {
      // LOG.info("Iterator from "+Version.getVersion()+" to "+v);
      Version.saveVersion(v);
      Object o = i.next();
      // LOG.info("Got value "+o);
      return o;
    } finally {
      Version v2 = Version.getVersion();
      Version.restoreVersion();
      // LOG.info("Iterator from "+v2+" back to "+Version.getVersion());
    }
  }
}
