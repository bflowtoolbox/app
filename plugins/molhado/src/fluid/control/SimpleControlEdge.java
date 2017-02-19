/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/SimpleControlEdge.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Class of edges that simply and directly refer to
 * sources and sinks.
 */

public class SimpleControlEdge extends ControlEdge {

  ControlNode source, sink;
  boolean source_secondary, sink_secondary;
  
  /** Create an edge between two nodes and attach immediately. */
  public SimpleControlEdge(ControlNode source, ControlNode sink) {
    super();
    attach(source,sink);
  }

  public ControlNode getSource() { return source; }
  public ControlNode getSink() { return sink; }
  public boolean sourceIsSecondary() { return source_secondary; }
  public boolean sinkIsSecondary() { return sink_secondary; }

  protected void setSource(ControlNode source, boolean secondary) {
    this.source = source;
    source_secondary = secondary;
  }

  protected void setSink(ControlNode sink, boolean secondary) {
    this.sink = sink;
    sink_secondary = secondary;
  }
}
