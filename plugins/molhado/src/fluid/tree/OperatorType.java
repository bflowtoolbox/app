package fluid.tree;

import java.io.IOException;
import java.util.Comparator;

import fluid.ir.IRInput;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRType;

/** Operators are storable.
 */
public class OperatorType implements IRType , Comparator {
  private OperatorType() {}
  public static OperatorType prototype = new OperatorType();
  static {
    IRPersistent.registerIRType(prototype,'o');
  }
  
  public boolean isValid(Object x) {
    return x instanceof Operator;
  }

  public int compare( final Object o1, final Object o2 )
  {
    final Operator f1 = (Operator)o1;
    final Operator f2 = (Operator)o2;
    // return f1.compareTo( f2 );
    return 0; // FIX
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    // FIX
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return null; // FIX
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('O');
  }
  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    return null;
    // return Float.valueOf(s);
  }

  public String toString(Object o) {
    // final Float f = (Float) o;
    // return f.toString();
    return null;
  }
}
