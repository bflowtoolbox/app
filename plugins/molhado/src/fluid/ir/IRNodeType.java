/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRNodeType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

import fluid.NotImplemented;

/** The type of IR nodes.  Needed for persistence.
 */
public class IRNodeType implements IRType {
  private IRNodeType() {}
  public static IRNodeType prototype = new IRNodeType();
  static {
    IRPersistent.registerIRType(prototype,'N');
  }
  
  public boolean isValid(Object x) {
    return x == null || x instanceof IRNode;
  }

  public Comparator getComparator() 
  {
    return null;
  }

  public void writeValue(Object v, IROutput out) 
     throws IOException
  {
    out.writeNode((IRNode)v);
  }
  public Object readValue(IRInput in)
     throws IOException
  {
    return in.readNode();
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('N');
  }
  public IRType readType(IRInput in) { return this; }

  /** @exception fluid.NotImplemented */
  public Object fromString(String s) {
    throw new NotImplemented("fluid.ir.IRNodeType.fromString()");
  }

  /** @exception fluid.NotImplemented */
  public String toString(Object o) {
    throw new NotImplemented("fluid.ir.IRNodeType.toString()");
  }
}
