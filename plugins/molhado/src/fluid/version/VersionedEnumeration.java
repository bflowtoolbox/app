/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedEnumeration.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class VersionedEnumeration implements Enumeration {
  private Enumeration base;
  private Version version;

  public VersionedEnumeration(Enumeration base, Version version) {
    this.base = base;
    this.version = version;
  }
  public VersionedEnumeration(Enumeration base) {
    this(base, Version.getVersionLocal());
    version.mark();
  }

  public boolean hasMoreElements() {
    if (version == null)
      return false;
    Version current = Version.getVersionLocal();
    try {
      Version.setVersion(version);
      if (base.hasMoreElements()) {
        return true;
      } else {
        finish();
        return false;
      }
    } finally {
      Version.setVersion(current);
    }
  }

  public Object nextElement() throws NoSuchElementException {
    Version current = Version.getVersionLocal();
    try {
      Version.setVersion(version);
      return base.nextElement();
    } catch (NoSuchElementException ex) {
      finish();
      throw ex;
    } finally {
      Version.setVersion(current);
    }
  }

  public void finish() {
    if (version != null)
      version.release();
    version = null;
  }

  protected void finalize() throws Throwable {
    finish();
    super.finalize();
  }
}
