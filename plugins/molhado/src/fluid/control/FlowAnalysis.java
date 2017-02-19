package fluid.control;

import java.util.Enumeration;

import fluid.FluidError;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.util.AssocList;
import fluid.util.Lattice;
import fluid.util.Queue;

/** A class for performing flow analysis over a control-flow graph
 * <p>
 * The technique used here is very general and naive: we
 * start with everything top and then iterate using a worklist
 * until a (greatest) fixpoint is reached.
 * There are currently no provisions for "widening" (narrowing
 * for our lattices would be a better term).
 * </p>
 * @see Component
 * @see ControlNode
 * @see ControlEdge
 * @see ForwardAnalysis
 * @see BackwardAnalysis
 */
public abstract class FlowAnalysis {
  public final String name;
  protected final Lattice lattice;
  private final Queue worklist;

  private boolean started = false;
  private int iterations = 0;

  /** Create a new instance of flow analysis.
   * @param l the lattice of values for the analysis.
   * @see #getInfo
   */
  protected FlowAnalysis(String n, Lattice l) {
    name = n;
    lattice = l;
    worklist = new Queue();
  }

  public String getName() {
    return name;
  }
  public Lattice getLattice() {
    return lattice;
  }

  /** Initialize the lattice value for this control edge
   * to the default "top" value.
   */
  public void initialize(ControlEdge edge) {
    initialize(edge,lattice.top());
  }
  /** Initialize the lattice value for this control edge
   * as specified.
   */
  public void initialize(ControlEdge edge, Lattice value) {
    setInfo(edge,LabelList.empty,value);
  }
  /** Initialize the lattice value for this control edge
   * and labellist as specified.
   */
  public void initialize(ControlEdge edge, LabelList ll, Lattice value) {
    setInfo(edge,ll,value);
  }

  private boolean debug = false;
  public void debug() {
    debug = true;
  }

  private final SlotInfo si = SimpleSlotFactory.makeSlotInfo();

  /** Perform the analysis as specified.
   * NB: If new initializations have been performed since
   * the last time analysis was done, they will be
   * now taken into account.
   */
  public void performAnalysis() {
    while (!worklist.isEmpty()) {
      /* should the worklist include label too ? */
      ControlEdge edge = (ControlEdge)worklist.dequeue();
      AssocList l = (AssocList)edge.getSlotValue(si);
      
      /* O(n^2) but hopefully n is very small */
      Enumeration keys = l.keys();
      while (keys.hasMoreElements()) {
	LabelList ll = (LabelList)keys.nextElement();
	++iterations;
	useInfo(edge,ll,(Lattice)l.get(ll));
      }
    }
  }

  /** Return an indication of how long the analysis took.
   * Currently it gives the total number of times an edge
   * in the control-flow graph was visited.
   */
  public int getIterations() {
    return iterations;
  }

  protected void setInfo(ControlEdge edge, LabelList ll, Lattice value) {
    if (edge == null) {
      throw new FluidError("setInfo got null edge");
    }
    if (value == null)
      return; // no information changed
    if (debug) {
      System.out.println("new value computed for label list " +
			 ll + ": " + value);
    }
    try {
      AssocList l = (AssocList)edge.getSlotValue(si);
      Lattice vold = (Lattice)l.get(ll);
      if (vold != null) value = value.meet(vold);
      if (vold == null || !vold.equals(value)) {
	l.put(ll,value);
	worklist.enqueue(edge);
      } else {
	if (debug) {
	  System.out.println("  (but it didn't add anything)");
	}
      }
    } catch (SlotUndefinedException e) {
      edge.setSlotValue(si,new AssocList(ll,value));
      worklist.enqueue(edge);
    }
  }

  protected abstract void useInfo(ControlEdge edge,
				  LabelList ll,
				  Lattice value);

  public Lattice getInfo(ControlEdge edge) {
    try {
      AssocList l = (AssocList)edge.getSlotValue(si);
      Enumeration values = l.elements();
      Lattice value = lattice.top();
      while (values.hasMoreElements()) {
	value = value.meet((Lattice)values.nextElement());
      }
      return value;
    } catch (SlotUndefinedException e) {
      return lattice.top();
    }
  }

  /** Get the control-information for a particular edge
   * that was built on a particular label list.
   */
  public Lattice getInfo(ControlEdge edge, LabelList ll) {
    try {
      AssocList l = (AssocList)edge.getSlotValue(si);
      //! We assume the enumerations run in parallel!
      Enumeration keys = l.keys();
      Enumeration values = l.elements();
      Lattice value = lattice.top();
      while (values.hasMoreElements()) {
	LabelList ll2 = (LabelList)keys.nextElement();
	Lattice val = (Lattice)values.nextElement();
	if (ll2.includes(ll))
	  value = value.meet(val);
      }
      return value;
    } catch (SlotUndefinedException e) {
      return lattice.top();
    }
  }
}
