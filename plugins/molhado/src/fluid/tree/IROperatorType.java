/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/IROperatorType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.io.IOException;
import java.util.Comparator;

import fluid.ir.CachedType;
import fluid.ir.IRInput;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRType;

/** The type of operators of tree nodes.
 * @see Operator
 * @see Tree
 */

public class IROperatorType extends CachedType {
  private IROperatorType() {}
  public static IROperatorType prototype = new IROperatorType();
  static { IRPersistent.registerIRType(prototype,'O'); }

  public boolean isValid(Object x) {
    return x == null || x instanceof Operator;
  }

  public Comparator getComparator() 
  {
    return null;
  }

  protected void writeValueInternal(Object value, IROutput out)
       throws IOException
  {
    Operator op = (Operator)value;
    out.writeUTF(op.internalName());
    op.writeInstance(out);
  }

  protected Object createValue(IRInput in) throws IOException {
    Operator op = Operator.findOperatorInternal(in.readUTF());
    return op.readInstance(in);
  }
  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('O');
  }
  public IRType readType(IRInput in) { return this; }
    
  public Object fromString(String s) {
    return Operator.findOperatorInternal(s);
  }

  public String toString(Object o) {
    final Operator op = (Operator) o;
    return op.name();
  }
}
