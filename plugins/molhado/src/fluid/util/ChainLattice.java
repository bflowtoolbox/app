/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/ChainLattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

/** A lattice that consists simple of a chain:
 * top > ... > bottom.
 */
public class ChainLattice implements Lattice {
  private final ChainLattice[] members;
  private final int val;

  public ChainLattice(int numMembers) {
    members = new ChainLattice[numMembers];
    val = 0;
    members[0] = this;
    for (int i=1; i < numMembers; ++i) {
      members[i] = new ChainLattice(members,i);
    }
  }
  private ChainLattice(ChainLattice[] members, int val) {
    this.members = members;
    this.val = val;
  }

  public boolean equals(Object other) {
    return other instanceof ChainLattice &&
      members == ((ChainLattice)other).members &&
      val == ((ChainLattice)other).val;
  }
  public int hashCode() {
    return val;
  }

  public Lattice top() {
    return members[0];
  }
  public Lattice bottom() {
    return members[members.length-1];
  }

  public Lattice meet(Lattice other) {
    ChainLattice cl = (ChainLattice)other;
    if (cl.val < val) return this;
    else return cl;
  }

  public boolean includes(Lattice other) {
    ChainLattice cl = (ChainLattice)other;
    return val <= cl.val;
  }
}
