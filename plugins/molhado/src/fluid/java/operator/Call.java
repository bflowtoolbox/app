/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/operator/Call.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */

package fluid.java.operator;

import fluid.ir.IRNode;

/** Each Call node has a binding to a method or constructor
 * and a list of actual parameters (in an Arguments node).
 */
public interface Call {
  public IRNode getActuals(IRNode node);
}
