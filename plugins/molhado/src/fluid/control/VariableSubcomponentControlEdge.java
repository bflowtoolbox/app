package fluid.control;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.SlotInfo;

/** This abstract class covers all edges that cross
 * VariableSubcomponent boundaries.  Each edge must be registered
 * with the VariableSubcomponent in question at a particular index.
 * Each edge has two parts: one entering one component and one exiting
 * another (previous or next) component.  A special component is
 * placed around the whole sequence to keep the ends together.
 */

public class VariableSubcomponentControlEdge extends ControlEdge {
  protected VariableSubcomponent subcomponent;
  protected int index;
  protected boolean isEntry;
  protected ControlNode local;
  protected boolean isSecondary;
  
  public VariableSubcomponentControlEdge
      (VariableSubcomponent vs, int i, boolean entryp, ControlNode local) 
  {
    subcomponent = vs;
    index = i;
    isEntry = entryp;
    vs.registerVariableEdge(this,i,entryp);
    if (entryp) {
      attachSink(local);
    } else {
      attachSource(local);
    }
  }
  
  public VariableSubcomponent getVariableSubcomponent() {
    return subcomponent;
  }
  public int getIndex() {
    return index;
  }
  public boolean getIsEntry() {
    return isEntry;
  }
  
  /** The the other half of the edge.
   * It will be another variable edge of the opposite type.
   * (If the subcomponent has null loc, that means
   *  it is the special subcomponent that sits at the
   *  beginning and ending of the sequence).
   */
  public VariableSubcomponentControlEdge getDual() {
    IRLocation loc = subcomponent.getLocation();
    Component comp = subcomponent.getComponent();
    Subcomponent sub;
    IRNode parent = comp.getSyntax();
    if (loc == null) { // get first/last if existing
      if (isEntry) {
	loc = Subcomponent.tree.lastChildLocation(parent);
      } else {
	loc = Subcomponent.tree.firstChildLocation(parent);
      }
    } else {
      if (isEntry) {
	loc = Subcomponent.tree.prevChildLocation(parent,loc);
      } else {
	loc = Subcomponent.tree.nextChildLocation(parent,loc);
      }
    }
    if (loc != null) {
      sub = comp.getSubcomponent(loc);      
    } else {
      sub = comp.getVariableSubcomponent();
    }
    return sub.getVariableEdge(index,!isEntry);
  }

  /** The entry and exit edges are the "same" node
   * and so we defer all IR equality tests and slot values
   * from entry to exit edges.
   */
  public Object identity() {
    if (isEntry) {
      return getDual();
    } else {
      return this;
    }
  }

  public boolean equals(Object other) {
    if (isEntry) {
      return getDual().equals(other);
    } else {
      return super.equals(other);
    }
  }

  public int hashCode() {
    if (isEntry) {
      return getDual().hashCode();
    } else {
      return super.hashCode();
    }
  }

  /** Return the value of an attribute.
   * Values are always stored on the exit part of an edge.
   */
  public Object getSlotValue(SlotInfo si) {
    if (isEntry) {
      return getDual().getSlotValue(si);
    } else {
      return super.getSlotValue(si);
    }
  }
  
  /** Set the value of an attribute.
   * Values are always stored on the exit part of an edge.
   */
  public void setSlotValue(SlotInfo si, Object value) {
    if (isEntry) {
      getDual().setSlotValue(si,value);
    } else {
      super.setSlotValue(si,value);
    }
  }
  
  public ControlNode getSource() {
    if (isEntry) {
      return getDual().local;
    } else {
      return local;
    }
  }
  
  public ControlNode getSink() {
    if (isEntry) {
      return local;
    } else {
      return getDual().local;
    }
  }
  
  public boolean sourceIsSecondary() {
    if (isEntry) {
      return getDual().isSecondary;
    } else {
      return isSecondary;
    }
  }

  public boolean sinkIsSecondary() {
    if (isEntry) {
      return isSecondary;
    } else {
      return getDual().isSecondary;
    }
  }
  
  protected void setSource(ControlNode source, boolean second) 
      throws EdgeLinkageError
  {
    if (isEntry) {
      throw new EdgeLinkageError("no source explicit");
    } else {
      local = source;
      isSecondary = second;
    }
  }

  protected void setSink(ControlNode sink, boolean second) 
      throws EdgeLinkageError
  {
    if (isEntry) {
      local = sink;
      isSecondary = second;
    } else {
      throw new EdgeLinkageError("no sink explicit");
    }
  }
}
