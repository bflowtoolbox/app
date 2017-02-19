/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRBooleanType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** Booleans are storable.
 */
public class IRBooleanType implements IRType, Comparator {
  private IRBooleanType() {}
  public static IRBooleanType prototype = new IRBooleanType();
  static {
    IRPersistent.registerIRType(prototype,'B');
  }

  public boolean isValid(Object x) {
    return x instanceof Boolean;
  }

  /**
   * Compare two {@link java.lang.Boolean} values.  Ordered 
   * with <code>true</code> being greater than <code>false</code>.
   */
  public int compare( final Object o1, final Object o2 )
  {
    final Boolean b1 = (Boolean)o1;
    final Boolean b2 = (Boolean)o2;
    if( b1.booleanValue() == b2.booleanValue() ) {
      // they are equal
      return 0;
    } else {
      // they are not equal
      // if b1 is true, then b2 is false, so b1 > b2 --> return 1
      // if b1 is false, then b2 is true, so b1 < b2 --> return -1
      return b1.booleanValue() ? 1 : -1;
    }
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out)
     throws IOException
  {
    out.writeBoolean(((Boolean)v).booleanValue());
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return in.readBoolean() ? Boolean.TRUE : Boolean.FALSE;
  }

  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('B');
  }
  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    //return (s.compareToIgnoreCase("true")==0) ? Boolean.TRUE : Boolean.FALSE;
    return Boolean.valueOf(s);
  }

  public String toString(Object o) {
    final Boolean b = (Boolean) o;
    return b.toString();
  }
}
