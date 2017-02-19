/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRIntegerType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** Integers are storable.
 */
public class IRIntegerType implements IRType , Comparator {
  private IRIntegerType() {}
  public static IRIntegerType prototype = new IRIntegerType();
  static {
    IRPersistent.registerIRType(prototype,'I');
  }
  
  public boolean isValid(Object x) {
    return x instanceof Integer;
  }

  public int compare( final Object o1, final Object o2 )
  {
    final Integer i1 = (Integer)o1;
    final Integer i2 = (Integer)o2;
    return i1.compareTo( i2 );
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    out.writeInt(((Integer)v).intValue());
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return new Integer(in.readInt());
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('I');
  }
  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    return Integer.valueOf(s);
  }

  public String toString(Object o) {
    final Integer i = (Integer) o;
    return i.toString();
  }
}
