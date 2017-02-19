package fluid.java.operator;

import fluid.control.Sink;
import fluid.control.Source;
import fluid.ir.IRNode;

/** An IRNode that contains an intraprocedural flow-graph.
 * the source node and sink nodes can be computed.
 */
public interface FlowUnit {
  /** Return the special source node that starts the graph. */
  public Source getSource(IRNode node);
  /** Return the special sink node that ends normal execution. */
  public Sink getNormalSink(IRNode node);
  /** Return the special sink node that ends abrupt termination. */
  public Sink getAbruptSink(IRNode node);
}
