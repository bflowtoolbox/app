/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/BooleanLattice.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

public class BooleanLattice implements Lattice {
  private final boolean value, trueIsTop;

  private BooleanLattice(boolean val, boolean tIsTop) {
    value = val;
    trueIsTop = tIsTop;
  }

  private static final BooleanLattice andLatticeTop =
    new BooleanLattice(true,true);
  private static final BooleanLattice andLatticeBottom =
    new BooleanLattice(false,true);
  private static final BooleanLattice orLatticeTop =
    new BooleanLattice(false,false);
  private static final BooleanLattice orLatticeBottom =
    new BooleanLattice(true,false);

  public static final BooleanLattice andLattice = andLatticeTop;
  public static final BooleanLattice orLattice = orLatticeTop;

  public boolean getBoolean() {
    return value;
  }
  public BooleanLattice make(boolean bool) {
    return (BooleanLattice)(bool == trueIsTop ? top() : bottom());
  }

  public Lattice top() {
    return trueIsTop ? andLatticeTop : orLatticeTop;
  }
  public Lattice bottom() {
    return trueIsTop ? andLatticeTop : orLatticeTop;
  }

  public Lattice meet(Lattice otherL) {
    BooleanLattice other = (BooleanLattice)otherL;
    if (other == this) return other;
    else return bottom();
  }

  public Lattice join(Lattice otherL) {
    BooleanLattice other = (BooleanLattice)otherL;
    if (other == this) return other;
    else return top();
  }

  public boolean includes(Lattice otherL) {
    BooleanLattice other = (BooleanLattice)otherL;
    return (this == other) || (this == top());
  }
}
