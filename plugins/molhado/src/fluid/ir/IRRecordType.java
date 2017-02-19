/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRRecordType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

import fluid.NotImplemented;

/** The type of fixed size arrays of Objects,
 * in which each position has a different type of object.
 * @see IRSequenceType
 */

public class IRRecordType implements IRType {
  private IRType[] elementTypes;

  public IRRecordType(IRType[] types) {
    elementTypes = types;
  }
  static {
    IRPersistent.registerIRType(new IRRecordType(new IRType[0]),'R');
  }

  public int size() { return elementTypes.length; }
  public IRType getElementType(int i) {
    return elementTypes[i];
  }
  public boolean isValid(Object value) {
    if (value == null) return true;
    if (!(value instanceof Object[])) return false;
    Object[] array = (Object[])value;
    if (array.length != elementTypes.length) return false;
    for (int i=0; i < array.length; ++i) {
      if (!elementTypes[i].isValid(array[i])) return false;
    }
    return true;
  }

  public Comparator getComparator() 
  {
    return null;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    Object[] array = (Object[])v;
    for (int i=0; i < elementTypes.length; ++i) {
      elementTypes[i].writeValue(array[i],out);
    }
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    Object[] array = new Object[elementTypes.length];
    for (int i=0; i < elementTypes.length; ++i) {
      array[i] = elementTypes[i].readValue(in);
    }
    return array;
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('R');
    out.writeInt(elementTypes.length);
    for (int i=0; i < elementTypes.length; ++i) {
      out.writeIRType(elementTypes[i]);
    }
  }
  public IRType readType(IRInput in) throws IOException {
    int len = in.readInt();
    IRType[] types = new IRType[len];
    for (int i=0; i < len; ++i) {
      types[i] = in.readIRType();
    }
    return new IRRecordType(types);
  }

  /** @exception fluid.NotImplemented */
  public Object fromString(String s) {
    throw new NotImplemented("fluid.ir.IRRecordType.fromString()");
  }

  public String toString(Object o) {
    if (o == null) {
      return "";
    }
    else if (!isValid(o)) {
      return null;
    } else { /* o is valid and not null */
      StringBuffer buf = new StringBuffer();
      Object[] array = (Object[]) o;
      for (int i=0; i < array.length; ++i) {
        buf.append(array[i]); buf.append(' ');
      }

      int len = buf.length();
      if (len>0) {
        buf.setLength(len-1); // truncate last blank
      }
      return buf.toString();
    }
  }
}
