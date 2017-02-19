package fluid.control;

import fluid.ir.IRLocation;

/** A subcomponent associated with a <em>variable location</em>
 * (that is, a location in an IRList).  Such subcomponents may have
 * nodes and edges.  They do not have Ports (q.v.) but do have
 * edges sticking out to be connected with the other subcomponents
 * in a chain.  An additional variable subcomponent connects the sequence
 * with the outer context.
 * @see Port
 * @see VariableSubcomponentControlEdge 
 */
public class VariableSubcomponent extends Subcomponent {
  private final int numEdges;
  private final VariableSubcomponentControlEdge[] entryVariableEdges;
  private final VariableSubcomponentControlEdge[] exitVariableEdges;
  
  public VariableSubcomponent(Component comp, IRLocation loc, int n) {
    super(comp,loc);
    numEdges = n;
    entryVariableEdges = new VariableSubcomponentControlEdge[n];
    exitVariableEdges = new VariableSubcomponentControlEdge[n];
  }

  public int getNumEdges() {
    return numEdges;
  }

  public VariableSubcomponentControlEdge getVariableEdge(int i,boolean isEntry)
  {
    if (isEntry) {
      return entryVariableEdges[i];
    } else {
      return exitVariableEdges[i];
    }
  }
  
  void registerVariableEdge(VariableSubcomponentControlEdge e, 
			    int i, 
			    boolean isEntry) {
    if (isEntry) {
      entryVariableEdges[i] = e;
    } else {
      exitVariableEdges[i] = e;
    }
  }
}
