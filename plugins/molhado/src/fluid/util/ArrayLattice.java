package fluid.util;

/** A lattice formed using an array of lattices, all the same type. */
public class ArrayLattice extends RecordLattice {

  private static Lattice[] makeBaseLattices(Lattice baseLattice, int size) {
    Lattice[] baseLattices = new Lattice[size];
    for (int i=0; i < size; ++i) {
      baseLattices[i] = baseLattice;
    }
    return baseLattices;
  }
  
  public ArrayLattice(Lattice baseLattice, int size) {
    super(makeBaseLattices(baseLattice,size));
  }

  protected ArrayLattice(Lattice[] v, RecordLattice t, RecordLattice b) {
    super(v,t,b);
  }

  protected RecordLattice newLattice(Lattice[] values) {
    return new ArrayLattice(values,top,bottom);
  }
}
