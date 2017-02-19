/*
 * IRLongType.java
 *
 * Created on August 16, 2000, 2:52 PM
 */

package fluid.ir;

/**
 *
 * @author  smzia
 * @version 
 */
import java.io.IOException;
import java.util.Comparator;

/** Longs are storable.
 */
public class IRLongType implements IRType , Comparator {
  private IRLongType() {}
  public static IRLongType prototype = new IRLongType();
  static {
    IRPersistent.registerIRType(prototype,'o');
  }
  
  public boolean isValid(Object x) {
    return x instanceof Long;
  }

  public int compare( final Object o1, final Object o2 )
  {
    final Long i1 = (Long)o1;
    final Long i2 = (Long)o2;
    return i1.compareTo( i2 );
  }

  public Comparator getComparator() 
  {
    return this;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    out.writeLong(((Long)v).longValue());
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return new Long(in.readLong());
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('o');
  }
  public IRType readType(IRInput in) { return this; }

  public Object fromString(String s) {
    return Long.valueOf(s);
  }

  public String toString(Object o) {
    final Long i = (Long) o;
    return i.toString();
  }
}