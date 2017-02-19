package fluid.util;

/** A lattice formed using a record of lattice values. */
public class RecordLattice implements Lattice {
  protected final Lattice[] values;
  protected final RecordLattice top, bottom;

  public RecordLattice(Lattice[] baseLattices) {
    int size = baseLattices.length;

    values = new Lattice[size];
    for (int i=0; i < size; ++i) {
      values[i] = baseLattices[i].top();
    }

    Lattice[] botValues = new Lattice[size];
    for (int i=0; i < size; ++i) {
      botValues[i] = baseLattices[i].bottom();
    }
    
    top = this;
    bottom = newLattice(botValues);
  }

  /** Create a new lattice value in an existing lattice.
   * @param b the bottom value, if null, then this is the bottom value.
   */
  protected RecordLattice(Lattice[] v, RecordLattice t, RecordLattice b) {
    values = v;
    top = t;
    bottom = (b == null) ? this : b;
  }

  /** Creation routine to be overridden in subclasses.
   * @param b the bottom value, if null, then result is the bottom value.
   */
  protected RecordLattice newLattice(Lattice[] newValues) {
    return new RecordLattice(newValues,top,bottom);
  }

  public Lattice top() { return top; }
  public Lattice bottom() { return bottom; }

  public int hashCode() {
    int h = 0;
    for (int i=0; i < values.length; ++i) {
      h += values[i].hashCode();
      h = (h << 1) | ((h < 0) ? 1 : 0);
    }
    return h;
  }
      
  public boolean equals(Object otherO) {
    RecordLattice other = (RecordLattice)otherO;
    if (this == other) return true;
    return equals(other.values);
  }

  public boolean equals(Lattice[] otherValues) {
    for (int i=0; i < values.length; ++i) {
      if (!values[i].equals(otherValues[i])) return false;
    }
    return true;
  }

  public boolean includes(Lattice otherL) {
    RecordLattice other = (RecordLattice) otherL;
    if (this == other) return true;
    for (int i=0; i < values.length; ++i) {
      if (!values[i].includes(other.values[i])) return false;
    }
    return true;
  }
  
  public Lattice meet(Lattice otherL) {
    RecordLattice other = (RecordLattice) otherL;
    if (this == bottom || this == other || other == top) return this;
    if (this == top || other == bottom) return other;
    if (includes(other)) return other;
    if (other.includes(this)) return this;

    int size = values.length;
    Lattice[] newValues = new Lattice[size];
    for (int i=0; i < size; ++i) {
      newValues[i] = values[i].meet(other.values[i]);
    }
    if (bottom.equals(newValues)) return bottom;

    return newLattice(newValues);
  }

  /*
  public Lattice join(Lattice otherL) {
    RecordLattice other = (RecordLattice) otherL;
    if (thsi == top || this == other || other == bottom) return this;
    if (this == bottom || other == top) return other;
    if (includes(other)) return this;
    if (other.includes(this)) return other;

    int size = values.length;
    Lattice[] newValues = new Lattice[size];
    for (int i=0; i < size; ++i) {
      newValues[i] = values[i].join(other.values[i]);
    }
    if (top.equals(newValues)) return top;

    return newLattice(newValues);
  }
  */

  public Lattice getValue(int i) {
    return values[i];
  }

  public RecordLattice replaceValues(Lattice[] newValues) {
    if (top.equals(newValues)) return top;
    if (bottom.equals(newValues)) return bottom;
    if (this.equals(newValues)) return this;
    return newLattice(newValues);
  }

  public RecordLattice replaceValue(int i, Lattice newValue) {
    if (values[i].equals(newValue)) return this;
    int size = values.length;
    Lattice[] newValues = new Lattice[size];
    for (int j=0; j < size; ++j)
      newValues[j] = values[j];
    newValues[i] = newValue;
    return replaceValues(newValues);
  }
}
