/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRUnitType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** The only unit value is null.
 */
public class IRUnitType implements IRType, Comparator {
  private IRUnitType() {}
  public static IRUnitType prototype = new IRUnitType();
  static { IRPersistent.registerIRType(prototype,'U'); }
  public boolean isValid(Object x) {
    return x == null;
  }

  public int compare( final Object o1, final Object o2 )
  {
    if( (o1 == null) && (o2 == null) ) {
      return 0;
    } else {
      throw new ClassCastException();
    }
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out) {}
  public Object readValue(IRInput in){ return null; }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('U');
  }
  public IRType readType(IRInput in) { return this; }

  /* only valid IRUnitType is null. and it is represented by
   * null string ("")
   */

  /** @return <code>null</code> */
  public Object fromString(String s) {
    return null;
  }

  /** @return if o is <code>null</code> then returns <code>""</code>,
              else returns <code>null</code> */
  public String toString(Object o) {
    if (o == null) {
      return "";
    } else {
      return null;
    }
  }
}
