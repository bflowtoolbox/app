/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/Lattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

/** An interface to control-flow lattices.
 * In deference to traditional control-flow analyses,
 * the lattice is "upside-down":
 * <dl>
 * <dt>top<dd>    perfect information
 * <dt>bottom<dd> no information
 * <dt>meet<dd>   compute common information
 * </dl>
 * The <tt>equals</tt> method is used to compare elements
 * in the lattice.  
 * <p> The lack of proper binary methods in Java
 * makes this interface too general.  Any type given
 * as <tt>Lattice</tt> should be understood as <tt>ThisType</tt>.
 */
public interface Lattice {
  public Lattice top();
  public Lattice bottom();
  public Lattice meet(Lattice other);
  /** Return true if this information includes the information in the other.
   * @return other.equals(this.meet(other))
   */
  public boolean includes(Lattice other);
}
