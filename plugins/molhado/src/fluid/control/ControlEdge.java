/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ControlEdge.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Edges into the Java control-flow graph.
 * @author John Tang Boyland
 * @see ControlNode
 * @see SimpleControlEdge
 * @invariant isSane
 */

public abstract class ControlEdge extends Entity {
  /** Private invariant, that is something true after
   * any constructor or method completes.
   * @fluid invariant
   */
  private boolean isSane() {
    return (okSourceSink(getSource(),getSink())
	    && (!sourceIsSecondary() | (getSource() != null))
	    && (!sinkIsSecondary() | (getSink() != null)));
  }

  /** Return true if the given values are legal source and sinks
   * @fluid property
   */
  private static boolean okSourceSink(ControlNode source, ControlNode sink) {
    return (!(source == null ^ sink == null)
	    && (source == null ||
		source instanceof OneOutput ||
		source instanceof TwoOutput)
	    && (sink == null ||
		sink instanceof OneInput ||
		sink instanceof TwoInput));
  }
  
  /** Return the control node this edge starts from.
   * @pure
   */
  public abstract ControlNode getSource();

  /** Return the control node this edge goes to.
   * @pure
   */
  public abstract ControlNode getSink();

  /** Return whether this edge is the *second* edge coming out of
   * the source.  That is ((TwoOutput)getSource()).getOutput2() == this
   * @pure
   */
  public abstract boolean sourceIsSecondary();

  /** Return whether this edge is the *second* edge going into
   * the sink.  That is ((TwoInput)getSource()).getInput2() == this
   * @pure
   */
  public abstract boolean sinkIsSecondary();

  /** Set the source of an edge.
   * (Used only in initialization by attach and friends.)
   */
  protected abstract void setSource(ControlNode source, boolean secondary)
       throws EdgeLinkageError;

  /** Set the sink of an edge.
   * (Used only in initialization by attach and friends.)
   */
  protected abstract void setSink(ControlNode sink, boolean secondary) 
       throws EdgeLinkageError;

  /** Attach the edge to the input/output slots of the nodes.
   * If this edge is the second output of a node or a second
   * input to a node, record that it is such.
   * @exception EdgeLinkageError
   *            Edge already has sources and sinks
   *            Nodes already taken.
   * @precondition NonNull(source)
   * @precondition NonNull(sink)
   * @precondition okSourceSink(source,sink)
   */
  protected void attach(ControlNode source, ControlNode sink)
       throws EdgeLinkageError
  {
    attachSource(source);
    attachSink(sink);
  }

  /** Attach the edge to a source.
   * @exception EdgeLinkageError
   *    if source does not have outputs;
   *    if source outputs are full
   */
  protected void attachSource(ControlNode source)
       throws EdgeLinkageError
  {
    if (source instanceof OneOutput) {
      OneOutput ns = (OneOutput) source;
      if (ns.getOutput() != null) {
        throw new EdgeLinkageError("source already taken");
      } else {
	setSource(source,false);
	ns.setOutput(this);
      }
    } else if (source instanceof TwoOutput) {
      TwoOutput ns = (TwoOutput) source;
      if (ns.getOutput1() != null) {
	if (ns.getOutput2() != null) {
	  throw new EdgeLinkageError("source full");
	} else {
	  setSource(source,true);
	  ns.setOutput2(this);
	}
      } else {
	setSource(source,false);
	ns.setOutput1(this);
      }
    } else {
      throw new EdgeLinkageError("source does not have outputs");
    }
  }


  /** Attach the edge to a sink.
   * @exception EdgeLinkageError
   *    if sink does not have inputs;
   *    if sink inputs are full
   */
  protected void attachSink(ControlNode sink)
       throws EdgeLinkageError
  {
    if (sink instanceof OneInput) {
      OneInput ns = (OneInput) sink;
      if (ns.getInput() != null) {
        throw new EdgeLinkageError("sink already taken");
      } else {
	setSink(sink,false);
	ns.setInput(this);
      }
    } else if (sink instanceof TwoInput) {
      TwoInput ns = (TwoInput) sink;
      if (ns.getInput1() != null) {
	if (ns.getInput2() != null) {
	  throw new EdgeLinkageError("sink full");
	} else {
	  setSink(sink,true);
	  ns.setInput2(this);
	}
      } else {
	setSink(sink,false);
	ns.setInput1(this);
      }
    } else {
      throw new EdgeLinkageError("sink does not have inputs");
    }
  }

  /** A useful shortcut: connect two nodes
   * with a SimpleControlEdge (q.v.)
   * @see SimpleControlEdge
   */
  public static void connect(ControlNode source, ControlNode sink) {
    ControlEdge e = new SimpleControlEdge(source,sink);
  }
}

