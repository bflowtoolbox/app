// $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRTypeType.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

import fluid.FluidError;

/** IRTypes are storable
 */
public class IRTypeType implements IRType, Comparator {
  public static final char TYPE = 'T';

  private IRTypeType() {}
  public static IRTypeType prototype = new IRTypeType();
  static {
    IRPersistent.registerIRType(prototype, TYPE); // FIX?
  }

  public boolean isValid(Object x) {
    return x instanceof IRType;
  }

  /**
   * Compare two {@link IRType} values.  
   */
  public int compare( final Object o1, final Object o2 )
  {
    final IRType b1 = (IRType)o1;
    final IRType b2 = (IRType)o2;
    if( b1 == b2 ) {
      // they are equal
      return 0;
    } else {
      // they are not equal
      // if b1 is true, then b2 is false, so b1 > b2 --> return 1
      // if b1 is false, then b2 is true, so b1 < b2 --> return -1
      return true ? 1 : -1; // FIX
    }
  }

  public Comparator getComparator() 
  {
    return null;
  }

  public void writeValue(Object v, IROutput out)
     throws IOException
  {
    out.writeIRType((IRType) v);
  }

  public Object readValue(IRInput in)
     throws IOException
  {
    return in.readIRType();
  }

  public void writeType(IROutput out) throws IOException
  {
    out.writeByte(TYPE);
  }

  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    throw new FluidError("Not implemented");
  }

  public String toString(Object o) {
    return o.toString();
  }
}
