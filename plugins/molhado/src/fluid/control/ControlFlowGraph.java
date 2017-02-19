/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ControlFlowGraph.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRSequenceException;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotUndefinedException;
import fluid.tree.DigraphMixin;
import fluid.tree.EdgeDigraphConnections;
import fluid.tree.StructureException;
import fluid.tree.SymmetricEdgeDigraphInterface;
import fluid.util.EmptyEnumeration;
import fluid.util.PairEnumeration;
import fluid.util.SingletonEnumeration;

/** Relate a control-flow graph as a kind of symmetric edge directed
 * graph.  This is only for viewing; the way control nodes and edges
 * are defined precludes new nodes from being added.
 * @see ControlNode
 * @see ControlEdge
 */
public class ControlFlowGraph extends DigraphMixin
     implements SymmetricEdgeDigraphInterface 
{
  public static final ControlFlowGraph prototype = new ControlFlowGraph();

  public boolean hasChildren(IRNode node) {
    return numChildren(node) > 0;
  }
  public int numChildren(IRNode node) {
    if (node instanceof OutputPort) {
      node = ((OutputPort)node).getDual();
    }
    if (node instanceof OneOutput) {
      return 1;
    } else if (node instanceof TwoOutput) {
      return 2;
    }
    return 0;
  }

  public IRLocation childLocation(IRNode node, int i) {
    if (node instanceof OutputPort) {
      node = ((OutputPort)node).getDual();
    }
    if (node instanceof OneOutput) {
      if (i == 0) return OneOutputLocation.prototype;
    } else if (node instanceof TwoOutput) {
      if (i == 0) return TwoOutputFirstLocation.prototype;
      else if (i == 1) return TwoOutputLastLocation.prototype;
    }
    return null;
  }
  public int childLocationIndex(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).asInteger();
  }
  public IRLocation firstChildLocation(IRNode node) {
    return childLocation(node,0);
  }
  public IRLocation lastChildLocation(IRNode node) {
    if (node instanceof OutputPort) {
      node = ((OutputPort)node).getDual();
    }
    if (node instanceof OneOutput) {
      return OneOutputLocation.prototype;
    } else if (node instanceof TwoOutput) {
      return TwoOutputLastLocation.prototype;
    }
    return null;
  }
  public IRLocation nextChildLocation(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).next();
  }
  public IRLocation prevChildLocation(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).prev();
  }

  public int compareChildLocations(IRNode node,
				   IRLocation loc1, IRLocation loc2) {
    return loc1.getID() - loc2.getID();
  }

  public boolean hasChild(IRNode node, int i) {
    try {
      getChild(node,i);
      return true;
    } catch (SlotUndefinedException ex) { // should rarely if ever happen
      return false;
    }
  }
  public boolean hasChild(IRNode node, IRLocation loc) {
    try {
      getChild(node,loc);
      return true;
    } catch (SlotUndefinedException ex) { // should rarely if ever happen
      return false;
    }
  }

  public IRNode getChild(IRNode node, int i) {
    return getSink(getChildEdge(node,i));
  }
  public IRNode getChild(IRNode node, IRLocation loc) {
    return getSink(getChildEdge(node,loc));
  }

  public Enumeration children(IRNode node) {
    if (node instanceof OutputPort) {
      node = ((OutputPort)node).getDual();
    }
    if (node instanceof OneOutput) {
      OneOutput node1 = (OneOutput)node;
      return new SingletonEnumeration(node1.getOutput().getSink());
    } else if (node instanceof TwoOutput) {
      TwoOutput node1 = (TwoOutput)node;
      return new PairEnumeration(node1.getOutput1().getSink(),
				 node1.getOutput2().getSink());
    }
    return EmptyEnumeration.prototype;
  }

  public boolean hasParents(IRNode node) {
    return numParents(node) > 0;
  }
  public int numParents(IRNode node) {
    if (node instanceof InputPort) {
      node = ((InputPort)node).getDual();
    }
    if (node instanceof OneInput) {
      return 1;
    } else if (node instanceof TwoInput) {
      return 2;
    }
    return 0;
  }

  public IRLocation parentLocation(IRNode node, int i) {
    if (node instanceof InputPort) {
      node = ((InputPort)node).getDual();
    }
    if (node instanceof OneInput) {
      if (i == 0) return OneInputLocation.prototype;
    } else if (node instanceof TwoInput) {
      if (i == 0) return TwoInputFirstLocation.prototype;
      else if (i == 1) return TwoInputLastLocation.prototype;
    }
    return null;
  }
  public int parentLocationIndex(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).asInteger();
  }
  public IRLocation firstParentLocation(IRNode node) {
    return parentLocation(node,0);
  }
  public IRLocation lastParentLocation(IRNode node) {
    if (node instanceof InputPort) {
      node = ((InputPort)node).getDual();
    }
    if (node instanceof OneInput) {
      return OneInputLocation.prototype;
    } else if (node instanceof TwoInput) {
      return TwoInputLastLocation.prototype;
    }
    return null;
  }
  public IRLocation nextParentLocation(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).next();
  }
  public IRLocation prevParentLocation(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).prev();
  }

  public int compareParentLocations(IRNode node,
				    IRLocation loc1, IRLocation loc2) {
    return loc1.getID() - loc2.getID();
  }

  public IRNode getParent(IRNode node, int i) {
    return getSource(getParentEdge(node,i));
  }
  public IRNode getParent(IRNode node, IRLocation loc) {
    return getSource(getParentEdge(node,loc));
  }

  public Enumeration parents(IRNode node) {
    if (node instanceof InputPort) {
      node = ((InputPort)node).getDual();
    }
    if (node instanceof OneInput) {
      OneInput node1 = (OneInput)node;
      return new SingletonEnumeration(node1.getInput().getSource());
    } else if (node instanceof TwoInput) {
      TwoInput node1 = (TwoInput)node;
      return new PairEnumeration(node1.getInput1().getSource(),
 				 node1.getInput2().getSource());
    }
    return EmptyEnumeration.prototype;
  }

  public IRNode getSink(IRNode edge) {
    return ((ControlEdge)edge).getSink();
  }
  public IRNode getChildEdge(IRNode node, int i) {
    return getChildEdge(node,childLocation(node,i));
  }
  public IRNode getChildEdge(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).getControlEdge(node);
  }

  public Enumeration childEdges(IRNode node) {
    return ((ControlNode)node).getOutputs();
  }

  public IRNode getSource(IRNode edge) {
    return ((ControlEdge)edge).getSource();
  }

  public IRNode getParentEdge(IRNode node, int i) {
    return getParentEdge(node,parentLocation(node,i));
  }
  public IRNode getParentEdge(IRNode node, IRLocation loc) {
    return ((ControlEdgeLocation)loc).getControlEdge(node);
  }

  public Enumeration parentEdges(IRNode node) {
    return ((ControlNode)node).getInputs();
  }

  public Enumeration connections(IRNode n1, IRNode n2) {
    return new EdgeDigraphConnections(this,n1,n2);
  }

  public IRLocation insertChild(IRNode n, IRNode newChild, InsertionPoint ip)
    throws StructureException
  {
    throw new StructureException("control-flow graph is immutable");
  }
}

abstract class ControlEdgeLocation extends IRLocation {
  ControlEdgeLocation() { super(0); }
  ControlEdgeLocation(int i) { super(i); }

  public abstract ControlEdge getControlEdge(IRNode node);
  // some default definitions:
  public IRLocation prev() { 
    return null;
  }
  public IRLocation next() { 
    return null;
  }
  public int asInteger() {
    return getID();
  }
}

class OneInputLocation extends ControlEdgeLocation {
  public static OneInputLocation prototype = new OneInputLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof InputPort) node = ((InputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((OneInput)node).getInput();
  }
}

class OneOutputLocation extends ControlEdgeLocation {
  public static OneOutputLocation prototype = new OneOutputLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof OutputPort) node = ((OutputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((OneOutput)node).getOutput();
  }
}

class TwoInputFirstLocation extends ControlEdgeLocation {
  public static TwoInputFirstLocation prototype = new TwoInputFirstLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof InputPort) node = ((InputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((TwoInput)node).getInput1();
  }
  public IRLocation next() { return TwoInputLastLocation.prototype; }
}

class TwoInputLastLocation extends ControlEdgeLocation {
  TwoInputLastLocation() { super(1); }
  public static TwoInputLastLocation prototype = new TwoInputLastLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof InputPort) node = ((InputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((TwoInput)node).getInput2();
  }
  public IRLocation prev() { return TwoInputFirstLocation.prototype; }
}

class TwoOutputFirstLocation extends ControlEdgeLocation {
  public static TwoOutputFirstLocation prototype =
    new TwoOutputFirstLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof OutputPort) node = ((OutputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((TwoOutput)node).getOutput1();
  }
  public IRLocation next() { return TwoOutputLastLocation.prototype; }
}

class TwoOutputLastLocation extends ControlEdgeLocation {
  TwoOutputLastLocation() { super(1); }
  public static TwoOutputLastLocation prototype = new TwoOutputLastLocation();
  public ControlEdge getControlEdge(IRNode node) {
    if (node instanceof OutputPort) node = ((OutputPort)node).getDual();
    if (node == null) throw new IRSequenceException("no such element");
    return ((TwoOutput)node).getOutput2();
  }
  public IRLocation prev() { return TwoOutputFirstLocation.prototype; }
}
