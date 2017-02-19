/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/operator/Assignment.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.java.operator;

import fluid.ir.IRNode;

public interface Assignment extends StatementExpressionInterface 
{
  public IRNode getSource(IRNode node);
  public IRNode getTarget(IRNode node);
}
