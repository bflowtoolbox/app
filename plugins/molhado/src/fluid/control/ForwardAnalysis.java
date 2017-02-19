/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ForwardAnalysis.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.FluidError;
import fluid.ir.IRNode;
import fluid.util.Lattice;

/** Forward control-flow analysis over control-flow graphs.
 * We specialize the control-flow analysis class to perform
 * analysis in the same direction as the control-flow.
 * The analysis is generic; it must be parameterized with a strategy
 * for how to transfer analysis values over specific node types.
 * 
 * @see ForwardTransfer
 * @see fluid.util.Lattice
 */
 
public class ForwardAnalysis extends FlowAnalysis {
  final ForwardTransfer trans;

  /** Create an instance of forward control-flow analysis.
   * @param t the transfer functions for semantics-specific nodes.
   * @see FlowAnalysis#FlowAnalysis(String,Lattice)
   */
  public ForwardAnalysis(String name, Lattice l, ForwardTransfer t) 
  {
    super(name, l);
    trans = t;
  }

  protected void useInfo(ControlEdge edge,
			 LabelList ll,
			 Lattice value)
  {
    ControlNode n = edge.getSink();
    boolean secondary = edge.sinkIsSecondary();
    if (n instanceof Port) {
      ControlNode dual = ((Port)n).getDual();
      if (!(n instanceof OutputPort) || !(dual instanceof InputPort)) {
	throw new FluidError("port mixup " + n + " with dual " + dual);
      }
      usePorts(secondary,(OutputPort)n,(InputPort)dual,ll,value);
    } else if (n instanceof Flow) {
      useFlow((Flow)n,ll,value);
    } else if (n instanceof Join) {
      useJoin(secondary,(Join)n,ll,value);
    } else if (n instanceof Split) {
      useSplit((Split)n,ll,value);
    } else if (n instanceof Sink) {
      /* do nothing */
    } else {
      throw new FluidError("unknown control node type " + n);
    }
  }

  protected void usePorts(boolean secondary,
			  OutputPort port,
			  InputPort dual,
			  LabelList ll,
			  Lattice value)
  {
    if (port instanceof SimpleOutputPort) {
      if (dual instanceof SimpleInputPort) {
	setInfo(((SimpleInputPort)dual).getOutput(),ll,value);
      } else if (dual instanceof DoubleInputPort) {
        ControlEdge out1 = ((DoubleInputPort)dual).getOutput1();
        ControlEdge out2 = ((DoubleInputPort)dual).getOutput2();
	if (port instanceof ComponentPort) {
	  IRNode node = ((ComponentPort)port).getComponent().getSyntax();
	  Lattice v1 = trans.transferConditional(node,true,value);
	  Lattice v2 = trans.transferConditional(node,false,value);
	  setInfo(out1,ll,v1);
	  setInfo(out2,ll,v2);
	} else {
	  //? I do not think this happens:
	  setInfo(out1,ll,value);
	  setInfo(out2,ll,value);
	}
      } else if (dual instanceof BlankInputPort) {
	/* do nothing */
      } else {
	throw new FluidError("unknown InputPort " + dual);
      }
    } else if (port instanceof DoubleOutputPort) {
      if (dual instanceof SimpleInputPort) {
	// just merge the value in:
	setInfo(((SimpleInputPort)dual).getOutput(),ll,value);
      } else if (dual instanceof DoubleInputPort) {
	if (secondary) {
	  setInfo(((DoubleInputPort)dual).getOutput2(),ll,value);
	} else {
	  setInfo(((DoubleInputPort)dual).getOutput1(),ll,value);
	}
      } else if (dual instanceof BlankInputPort) {
	/* do nothing */
      } else {
	throw new FluidError("unknown InputPort " + dual);
      }
    } else {
      throw new FluidError("unknown OutputPort " + port);
    }
  }

  protected void useFlow(Flow node, LabelList ll, Lattice value) {
    ControlEdge out = node.getOutput();
    if (node instanceof NoOperation) {
      setInfo(out,ll,value);
    } else if (node instanceof AddLabel) {
      setInfo(out,ll.addLabel(((AddLabel)node).getLabel()),value);
    } else if (node instanceof ComponentFlow) {
      ComponentFlow cf = (ComponentFlow)node;      
      Lattice v = trans.transferComponentFlow(cf.getComponent().getSyntax(),
					      cf.getInfo(),
					      value);
      setInfo(out,ll,v);
    } else if (node instanceof SubcomponentFlow) {
      SubcomponentFlow scf = (SubcomponentFlow)node;      
      Lattice v = trans.transferComponentFlow(scf.getSyntax(),
					      scf.getInfo(),
					      value);
      setInfo(out,ll,v);
    } else if (node instanceof PendingLabelStrip) {
      ControlLabel saved = ll.firstLabel();
      LabelList ll2 = ll.dropLabel();
      ControlLabel track = ll2.firstLabel();
      LabelList ll3 = ll2.dropLabel();
      if (track == TrackLabel.trueTrack) {
	setInfo(out,ll3.addLabel(saved),value);
      } else if (track == TrackLabel.falseTrack) {
	setInfo(out,ll3.dropLabel().addLabel(saved),value);
      }
    } else {
      throw new FluidError("unknown Flow " + node);
    }
  }

  protected void useJoin(boolean secondary, Join node,
			 LabelList ll, Lattice value)
  {
    ControlEdge out = node.getOutput();
    if (node instanceof Merge) {
      setInfo(out,ll,value);
    } else if (node instanceof TrackedMerge) {
      setInfo(out,ll.addLabel(TrackLabel.getLabel(!secondary)),value);
    } else {
      throw new FluidError("unknown Join " + node);
    }
  }

  protected void useSplit(Split node, LabelList ll, Lattice value) {
    ControlEdge out1 = node.getOutput1();
    ControlEdge out2 = node.getOutput2();
    if (node instanceof Fork) {
      setInfo(out1,ll,value);
      setInfo(out2,ll,value);
    } else if (node instanceof Choice) {
      Choice c = (Choice)node;
      IRNode syntax = c.getSyntax();
      Object info = c.getInfo();
      Lattice v1,v2;
      LabelList ll2 = ll;
      if (node instanceof LabelTest) {
	// System.out.println("Found a label test! (labellist = " + ll + ")");
	ControlLabel label = ll.firstLabel();
	if (label == null) { // cannot strip anything!
	  v1 = null;
	  v2 = value;
	} else {
	  ll2 = ll.dropLabel();
	  v1 = trans.transferLabelTest(syntax,info,label,true,value);
	  v2 = trans.transferLabelTest(syntax,info,label,false,value);
	}
      } else {
	v1 = trans.transferComponentChoice(syntax,info,true,value);
	v2 = trans.transferComponentChoice(syntax,info,false,value);
      }
      setInfo(out1,ll2,v1);
      setInfo(out2,ll,v2);
    } else if (node instanceof DynamicSplit) {
      DynamicSplit ds = (DynamicSplit)node;
      if (ds.test(true)) {
	setInfo(out1,ll,value);
      }
      if (ds.test(false)) {
	setInfo(out2,ll,value);
      }
    } else if (node instanceof TrackedDemerge) {
      ControlLabel l = ll.firstLabel();
      LabelList ll2 = ll.dropLabel();
      if (l == TrackLabel.trueTrack) {
	setInfo(out1,ll2,value);
      } else if (l == TrackLabel.falseTrack) {
	setInfo(out2,ll2,value);
      } else {
	throw new FluidError("unknown Label " + l);
      }
    } else {
      throw new FluidError("unknown Split " + node);
    }
  }
}
