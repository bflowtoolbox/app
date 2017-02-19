/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/DigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRSequenceException;

/** Classes implementing this interface can be used
 * to traverse directed graphs made up of IRNodes.
 * Destructive operations are not available.
 * @see Digraph
 * @see EdgeDigraphInterface
 */
public interface DigraphInterface {
  /** Return true when the node has at first one child location.
   */
  public boolean hasChildren(IRNode node);

  /** Return the number of children, defined or undefined, null or nodes.
   */
  public int numChildren(IRNode node);

  /** Return the location for child #i */
  public IRLocation childLocation(IRNode node, int i);

  /** Return the numeric location of a location */
  public int childLocationIndex(IRNode node, IRLocation loc);

  /** Return the location of the first child. */
  public IRLocation firstChildLocation(IRNode node);

  /** Return the location of the last child. */
  public IRLocation lastChildLocation(IRNode node);

  /** Return the location of the next child (or null). */
  public IRLocation nextChildLocation(IRNode node, IRLocation loc);

  /** Return the location of the previous child (or null). */
  public IRLocation prevChildLocation(IRNode node, IRLocation loc);

  /** Return one of <dl>
   * <dt>&lt; 0<dd> if loc1 precedes loc2,
   * <dt>&gt; 0<dd> if loc1 follows loc2,
   * <dt>= 0<dd> if loc1 equals loc2.</dl>
   * These locations must be valid locations in the children of the node.
   */
  public int compareChildLocations(IRNode node,
				   IRLocation loc1, IRLocation loc2);

  /** Return true if the child is defined.
   * @exception IRSequenceException if the index is out of range
   */
  public boolean hasChild(IRNode node, int i);

  /** Return true if the child is defined.
   * @exception IRSequenceException if the location is invalid (or null).
   */
  public boolean hasChild(IRNode node, IRLocation loc);

  /** Return the i'th child of a node. */
  public IRNode getChild(IRNode node, int i);

  /** Return the child at location loc. */
  public IRNode getChild(IRNode node, IRLocation loc);

  /** Return the children of a node in order. */
  public Enumeration children(IRNode node);

  /** Attach a listener to the digraph.
   * If the digraph is mutated, the listener should be called.
   */
  public void addDigraphListener(DigraphListener dl);

  /** Detach a listener from the digraph. */
  public void removeDigraphListener(DigraphListener dl);
}
