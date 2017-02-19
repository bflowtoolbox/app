/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/BackwardAnalysis.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.FluidError;
import fluid.ir.IRNode;
import fluid.util.Lattice;

/** Backward control-flow analysis over control-flow graphs.
 * We specialize the control-flow analysis class to perform
 * analysis in the opposite direction as the control-flow.
 * The analysis is generic; it must be parameterized with a strategy
 * for how to transfer analysis values over specific node types.
 * 
 * @see BackwardTransfer
 * @see fluid.util.Lattice
 */
 
public class BackwardAnalysis extends FlowAnalysis {
  final BackwardTransfer trans;

  /** Create an instance of backward control-flow analysis.
   * @param t the transfer function for semantics-specific nodes.
   * @see FlowAnalysis#FlowAnalysis(String,Lattice)
   */
  public BackwardAnalysis(String name, Lattice l, BackwardTransfer t) 
  {
    super(name, l);
    trans = t;
  }

  protected void useInfo(ControlEdge edge,
			 LabelList ll,
			 Lattice value)
  {
    ControlNode n = edge.getSource();
    boolean secondary = edge.sourceIsSecondary();
    if (n instanceof Port) {
      ControlNode dual = ((Port)n).getDual();
      if (!(n instanceof InputPort) || !(dual instanceof OutputPort)) {
	throw new FluidError("port mixup " + n);
      }
      usePorts(secondary,(InputPort)n,(OutputPort)dual,ll,value);
    } else if (n instanceof Flow) {
      useFlow((Flow)n,ll,value);
    } else if (n instanceof Join) {
      useJoin((Join)n,ll,value);
    } else if (n instanceof Split) {
      useSplit(secondary,(Split)n,ll,value);
    } else if (n instanceof Source) {
      /* do nothing */
    } else {
      throw new FluidError("unknown control node type " + n);
    }
  }

  protected void usePorts(boolean secondary,
			  InputPort port,
			  OutputPort dual,
			  LabelList ll,
			  Lattice value)
  {
    if (port instanceof SimpleInputPort) {
      if (dual instanceof SimpleOutputPort) {
	setInfo(((SimpleOutputPort)dual).getInput(),ll,value);
      } else if (dual instanceof DoubleOutputPort) {
	setInfo(((DoubleOutputPort)dual).getInput1(),ll,value);
	setInfo(((DoubleOutputPort)dual).getInput2(),ll,value);
      } else if (dual instanceof BlankOutputPort) {
	/* do nothing */
      } else {
	throw new FluidError("unknown OutputPort " + dual);
      }
    } else if (port instanceof DoubleInputPort) {
      if (dual instanceof SimpleOutputPort) {
	// just merge the value in:
	setInfo(((SimpleOutputPort)dual).getInput(),ll,value);
      } else if (dual instanceof DoubleOutputPort) {
	if (secondary) {
	  setInfo(((DoubleOutputPort)dual).getInput2(),ll,value);
	} else {
	  setInfo(((DoubleOutputPort)dual).getInput1(),ll,value);
	}
      } else if (dual instanceof BlankOutputPort) {
	/* do nothing */
      } else {
	throw new FluidError("unknown OutputPort " + dual);
      }
    } else {
      throw new FluidError("unknown InputPort " + port);
    }
  }

  protected void useFlow(Flow node, LabelList ll, Lattice value) {
    ControlEdge in = node.getInput();
    if (node instanceof NoOperation) {
      setInfo(in,ll,value);
    } else if (node instanceof AddLabel) {
      ControlLabel matchLabel = ((AddLabel)node).getLabel();
      ControlLabel label = ll.firstLabel();
      if (label != null &&
	  (label instanceof UnknownLabel ||
	   trans.testAddLabel(matchLabel,label))) {
	setInfo(in,ll.dropLabel(),value);
      }
    } else if (node instanceof ComponentFlow) {
      ComponentFlow cf = (ComponentFlow)node;      
      Lattice v = trans.transferComponentFlow(cf.getComponent().getSyntax(),
					      cf.getInfo(),
					      value);
      setInfo(in,ll,v);
    } else if (node instanceof SubcomponentFlow) {
      SubcomponentFlow scf = (SubcomponentFlow)node;      
      Lattice v = trans.transferComponentFlow(scf.getSyntax(),
					      scf.getInfo(),
					      value);
      setInfo(in,ll,v);
    } else if (node instanceof PendingLabelStrip) {
      ControlLabel saved = ll.firstLabel();
      if (saved != null) {
	setInfo(in,
		ll.dropLabel()
		  .addLabel(TrackLabel.trueTrack)
		  .addLabel(saved),
		value);
	setInfo(in,
		ll.dropLabel()
		  .addLabel(UnknownLabel.prototype)
		  .addLabel(TrackLabel.falseTrack)
		  .addLabel(saved),
		value);
      }
    } else {
      throw new FluidError("unknown Flow " + node);
    }
  }

  protected void useJoin(Join node, LabelList ll, Lattice value)
  {
    ControlEdge in1 = node.getInput1();
    ControlEdge in2 = node.getInput2();
    if (node instanceof Merge) {
      setInfo(in1,ll,value);
      setInfo(in2,ll,value);
    } else if (node instanceof TrackedMerge) {
      ControlLabel l = ll.firstLabel();
      LabelList ll2 = ll.dropLabel();
      if (l == TrackLabel.trueTrack) {
	setInfo(in1,ll2,value);
      } else if (l == TrackLabel.falseTrack) {
	setInfo(in2,ll2,value);
      } else {
	throw new FluidError("unknown Label " + l);
      }
    } else {
      throw new FluidError("unknown Join " + node);
    }
  }

  protected void useSplit(boolean secondary, Split node,
			  LabelList ll, Lattice value) {
    ControlEdge in = node.getInput();
    if (node instanceof Fork) {
      setInfo(in,ll,value);
    } else if (node instanceof Choice) {
      Choice cc = (Choice)node;
      IRNode syntax = cc.getSyntax();
      Object info = cc.getInfo();
      LabelList ll2 = ll;
      Lattice v;
      if (node instanceof LabelTest) {
	v = value;
	if (!secondary) {
	  ll2 = ll.addLabel(((LabelTest)node).getTestLabel());
	}
      } else {
	v = trans.transferComponentChoice(syntax,info,!secondary, value);
      }
      setInfo(in,ll2,v);
    } else if (node instanceof DynamicSplit) {
      DynamicSplit ds = (DynamicSplit)node;
      if (ds.test(!secondary)) {
	setInfo(in,ll,value);
      }
    } else if (node instanceof TrackedDemerge) {
      setInfo(in,ll.addLabel(TrackLabel.getLabel(!secondary)),value);
    } else {
      throw new FluidError("unknown Split " + node);
    }
  }
}
