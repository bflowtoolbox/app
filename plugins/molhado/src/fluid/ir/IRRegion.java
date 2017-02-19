/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRRegion.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import fluid.FluidRuntimeException;
import fluid.util.IntegerTable;
import fluid.util.UniqueID;

/** A set of persistent nodes in the fluid IR.  Each region
 * "owns" a certain number of IR nodes.
 * Each node has an index within the region.
 * Information about all the owned nodes is stored together. </p>
 * @see IndependentIRNode
 */
public class IRRegion extends IRPersistent {
  /* Node ownership */

  private static final Hashtable nodeOwnerTable = new Hashtable();
  private static final Hashtable nodeIndexTable = new Hashtable();
  // NB: the second table holds an array list of observers who want
  // to be informed when the node is assigned to a region.

  /** Return the region associated with this node
   * @throws OwnerUndefinedException if node has no owner
   */
  public static IRRegion getOwner(IRNode node) throws OwnerUndefinedException {
    IRRegion reg = (IRRegion)nodeOwnerTable.get(node);
    if (reg == null) {
      throw new OwnerUndefinedException(node);
    }
    return reg;
  }

  /** Return true if node has an owner already. */
  public static boolean hasOwner(IRNode node) {
    return null != nodeOwnerTable.get(node);
  }

  public synchronized static
  void whenOwned(IRNode node, IRPersistentObserver o) {
    Object obj = nodeIndexTable.get(node);
    if (obj == null) {
      obj = new ArrayList();
    } else if (obj instanceof Integer) {
      o.updatePersistent(getOwner(node),node);
      return;
    }
    ArrayList observers = (ArrayList)obj;
    if (!observers.contains(o))
      observers.add(o);
  }
      

  /** Return the region associated with this node
   * @throws OwnerUndefinedException if node has no owner
   */
  public static int getOwnerIndex(IRNode node) throws OwnerUndefinedException {
    try {
      return ((Integer)nodeIndexTable.get(node)).intValue();
    } catch (NullPointerException ex) {
      throw new OwnerUndefinedException(node);
    } catch (ClassCastException ex) {
      throw new OwnerUndefinedException(node);
    }
  }

  /** Set the owner and index for this node:
   * these values are always set together.
   */
  private static synchronized
  void setOwner(IRNode node, IRRegion region, int index) {
    nodeOwnerTable.put(node,region);
    ArrayList observers = (ArrayList)
      nodeIndexTable.put(node,IntegerTable.newInteger(index));
    if (observers != null) {
      for (Iterator it = observers.iterator(); it.hasNext(); )
	((IRPersistentObserver)it.next()).updatePersistent(region,node);
    }
  }


  /* A region never imports other nodes.
     (for simplicity)
   */
  public void importRegion(IRRegion other) {
    throw new FluidRuntimeException("cannot import one region into another");
  }


  /* ownership methods */

  private int numNodes = 0;
  /** Return number of nodes owned.
   * The result is valid if the region is complete.
   * Otherwise if it is ``new'' (created here),
   * it gives the number so far.
   * Otherwise the result is undefined.
   **/
  public int getNumNodes() {
    return numNodes;
  }

  private Vector nodes;

  public boolean define() { // NB called from super constructor
    return define(10);
  }

  /** Make the region defined, with
   * a hint to the number of nodes that will be added.
   */
  public boolean define(int numNodes) {
    if (super.define()) {
      nodes = new Vector(numNodes);
      return true;
    }
    return false;
  }

  /** Ensure that there are at least min nodes in the
   * region, creating new nodes if necessary.
   */
  protected void ensureNodes(int min) {
    define(min);
    nodes.ensureCapacity(min);
    for (int i=nodes.size(); i < min; ++i) {
      IRNode n = new PlainIRNode(null);
      setOwner(n,this,i+1);
      nodes.addElement(n);
    }
    if (numNodes < min) numNodes = min;
  }

  /** Return node given index (> 0). */
  public IRNode getNode(int index) throws IOException {
    if (index == 0) return null;
    if (index > numNodes) {
	if (isComplete() || isNew()) {
	    throw new IOException("index out of bounds for " + this);
	}
	// Incomplete, old regions with valid index.
	// We assume that the index is legal, and create a node.
	// This is needed for serialization, or whenever
	// persisting incomplete regions.
	ensureNodes(index);
    }
    return (IRNode)nodes.elementAt(index-1);
  }

  /** Return index within region
   * @throws IOException if not owned by this region.
   */
  public int getIndex(IRNode node) throws IOException {
    if (getOwner(node) == this) {
      return getOwnerIndex(node); // should not raise exception
    } else {
      throw new IOException("not owned");
    }
  }


  /* defining the number of nodes */

  /** Indicate that the storage has exactly this many nodes. */
  protected void complete(int numNodes) {
    if (isComplete() || this.numNodes > numNodes) {
      if (this.numNodes != numNodes)
	throw new FluidRuntimeException("Incompatible numbers of nodes for " +
					toString() + ": " +
					numNodes + " != " + this.numNodes);
    } else {
      ensureNodes(numNodes);
      forceComplete();
    }
  }

  /** Define the nodes in this storage. */
  protected void complete(IRNode[] nodeArray) {
    if (isComplete() || this.numNodes > 0) {
      throw new FluidRuntimeException("Incompatible completion");
    } else {
      define(numNodes);
      numNodes = nodeArray.length;
      for (int i=0; i < numNodes; ++i) {
	IRNode n = nodeArray[i];
	setOwner(n,this,i+1);
	nodes.addElement(n);
      }
      forceComplete();
    }
  }


  public static final int magic = 0x49525200; // "IRR\0"


  /* Constructors */

  /** Start defining a new region.  The state is defined, but incomplete,
   * and is being written.
   */
  public IRRegion() {
    this(magic);
  }

  /** Start defining a new region.  The state is defined, but incomplete,
   * and is being written.
   */
  protected IRRegion(int magic) {
    this(magic,true);
  }

  /** Start defining a region.
   * The state is defined but incomplete.
   * @param magic magic number associated with this region
   * (default: magic number of IRRegion)
   * @param hasID whether this region has identity
   * (default: true)
   */
  protected IRRegion(int magic, boolean hasID) {
    super(magic,hasID);
  }

  /** Set up a referred-to (undefined) region.
   */
  protected IRRegion(UniqueID id) {
    super(magic,id);
  }

  /** Set up a referred-to (undefined) region.
   */
  protected IRRegion(int magic, UniqueID id) {
    super(magic,id);
  }

  public static IRRegion getRegion(UniqueID id) {
    IRRegion reg = (IRRegion)find(id);
    if (reg == null) reg = new IRRegion(id);
    return reg;
  }


  /** Ensure this node is owned by this region unless
   * already owned.
   * @return true if node was added.
   */
  public boolean saveNode(IRNode node) {
    if (isComplete()) return false;
    define();
    if (!hasOwner(node)) {
      setOwner(node,this,++numNodes);
      nodes.addElement(node);
      return true;
    }
    return false;
  }


  /* output */
  protected void write(IROutput out) throws IOException {
    int n = numNodes;
    if (!isComplete()) n = ~n;
    out.writeInt(n);
  }

  /* Input */
  protected void read(IRInput in) throws IOException {
    if (in.getRevision() < 4) return;
    int n = in.readInt();
    if (n < 0) {
      n = ~n;
      define(n);
      ensureNodes(n);
    } else {
      complete(n);
    }
  }
      

  /* persistent kind */

  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException
    {
      //IRRegion r = (IRRegion)p;
      p.getID().write(out);
      // version 1.0:
      // out.writeInt(r.getNumNodes());
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException
    {
      UniqueID id = UniqueID.read(in);
      int numNodes = 0;
      if (in instanceof IRInput &&
	  ((IRInput)in).getRevision() < 1)  // backward compatability
	numNodes = in.readInt();
      IRRegion r = (IRRegion)find(id);
      if (r == null) {
	r = new IRRegion(id);
	r.define(numNodes);
	// version 1.0:
	// r.complete(numNodes);
      }
      return r;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind,0x52); // 'R'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    int numNodes = getNumNodes();
    if (numNodes > 0) {
      out.println("Region has " + numNodes +
		  ((numNodes > 1) ? " nodes" : " node"));
    }
  }
}
