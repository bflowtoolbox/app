package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

/** Floats are storable.
 */
public class IRFloatType implements IRType , Comparator {
  private IRFloatType() {}
  public static IRFloatType prototype = new IRFloatType();
  static {
    IRPersistent.registerIRType(prototype,'F');
  }
  
  public boolean isValid(Object x) {
    return x instanceof Float;
  }

  public int compare( final Object o1, final Object o2 )
  {
    final Float f1 = (Float)o1;
    final Float f2 = (Float)o2;
    return f1.compareTo( f2 );
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    out.writeFloat(((Float)v).floatValue());
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return new Float(in.readFloat());
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('F');
  }
  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    return Float.valueOf(s);
  }

  public String toString(Object o) {
    final Float f = (Float) o;
    return f.toString();
  }
}
