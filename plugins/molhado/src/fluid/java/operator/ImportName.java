// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ImportName.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.java.JavaOperator;
import fluid.tree.Operator;
public class ImportName extends JavaOperator implements ImportNameInterface { 
  protected ImportName() {}

  public static final ImportName prototype = new ImportName();


  /** Use the interface to determine what operators are legal:
   */
  public boolean includes(Operator other) {
    return (other instanceof ImportNameInterface);
  }
}


