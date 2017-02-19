/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/IRVersionType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.util.Comparator;

import fluid.NotImplemented;
import fluid.ir.IRInput;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRType;

public class IRVersionType implements IRType {
  public static final IRVersionType prototype = new IRVersionType();

  static {
    IRPersistent.registerIRType(prototype,'V');
  }

  /** Return true if a legally obtainable version. */
  public boolean isValid(Object value) {
    return value instanceof Version && ((Version)value).isFrozen();
  }

  public Comparator getComparator() 
  {
    return null;
  }

  /** Write a version to an IR stream. (We handle null as a special case) */
  public void writeValue(Object value, IROutput out)
       throws IOException
  {
    writeVersion((Version)value,out);
  }

  /** Write a version to an IR stream. (We handle null as a special case) */
  public static void writeVersion(Version v, IROutput out)
    throws IOException 
  {
    out.debugBegin("version");
    if (v == null) {
      out.writeInt(-1);
    } else if (v == Version.getInitialVersion()) {
      out.writeInt(0);
    } else {
      out.writeInt(v.getEraOffset());
      out.writePersistentReference(v.getEra());
    }
    out.debugEnd("version");
  }

  /** Read a version from an IR stream. */
  public Object readValue(IRInput in)
       throws IOException
  {
    return readVersion(in);
  }

  /** Read a version from an IR stream. */
  public static Version readVersion(IRInput in)
       throws IOException
  {
    in.debugBegin("version");
    int offset = in.readInt();
    Version v;
    if (offset == 0) {
      v = Version.getInitialVersion();
    } else if (offset == -1) {
      v = null;
    } else {
      Era e = (Era)in.readPersistentReference();
      v = e.getVersion(offset);
    }
    in.debugEnd("version");
    return v;
  }

  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('V');
  }

  public IRType readType(IRInput in) { return this; }

  /** @exception fluid.NotImplemented */
  public Object fromString(String s) {
    throw new NotImplemented("fluid.version.IRVersionType.fromString()");
  }

  /** @exception fluid.NotImplemented */
  public String toString(Object o) {
    throw new NotImplemented("fluid.version.IRVersionType.toString()");
  }

  public static void ensureLoaded() {
//    System.out.println("IRVersionType loaded");
  }
}
