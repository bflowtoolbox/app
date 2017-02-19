/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRSequenceType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

import fluid.FluidError;
import fluid.NotImplemented;

/** The type of storable homogenous sequences.
 * Each element is typed too.
 */
public class IRSequenceType implements IRCompoundType {
  private IRType elementType;
  public IRSequenceType(IRType elemType) {
    elementType = elemType;
  }
  static {
  IRPersistent.registerIRType(new IRSequenceType(IRUnitType.prototype), '[');
  }

  public IRType getElementType() {
    return elementType;
  }
  public IRType getType(int ignored) {
    return elementType;
  }

  public boolean isValid(Object value) {
    if (value == null)
      return true;
    if (!(value instanceof IRSequence))
      return false;
    IRSequence seq = (IRSequence) value;
    for (IRLocation loc = seq.firstLocation();
      loc != null;
      loc = seq.nextLocation(loc)) {
      if (seq.validAt(loc))
        if (!elementType.isValid(seq.elementAt(loc)))
          return false;
    }
    return true;
  }

  public Comparator getComparator() {
    return null;
  }

  public void writeValue(Object v, IROutput out) throws IOException {
    IRSequence seq = (IRSequence) v;
    // sequences have identity:
    if (out.writeCachedObject(seq))
      return;
    //! Currently we only support arrays and lists
    //! (and empty sequences)
    if (seq.isVariable()) {
      out.writeByte('L'); // assume a list
    } else if (seq.size() == 0) {
      out.writeByte('E');
    } else {
      out.writeByte('A'); // assume an array
    }
    seq.writeValue(out);
    seq.writeContents(this, out);
  }

  public Object readValue(IRInput in) throws IOException {
    return readValueHelper(in, null);
  }

  public Object readValue(IRInput in, Object currentValue) throws IOException {
    if (currentValue == null)
      throw new NullPointerException("null is not a legal compound value");
    return readValueHelper(in, (IRSequence) currentValue);
  }

  private Object readValueHelper(IRInput in, IRSequence current)
    throws IOException {
    Object obj = in.readCachedObject();
    if (obj != null)
      return obj;
    byte b = in.readByte();
    IRSequence seq;
    if (b == 'A') {
      seq = IRArray.readValue(in, current);
    } else if (b == 'L') {
      seq = IRList.readValue(in, current);
    } else if (b == 'E') {
      seq = EmptyIRSequence.readValue(in, current);
    } else {
      throw new FluidError("Unknown sequence class " + b);
    }
    in.cacheReadObject(seq);
    seq.readContents(this, in);
    return seq;
  }

  public void writeType(IROutput out) throws IOException {
    out.writeByte('[');
    out.writeIRType(elementType);
  }
  public IRType readType(IRInput in) throws IOException {
    return new IRSequenceType(in.readIRType());
  }

  /** @exception fluid.NotImplemented */
  public Object fromString(String s) {
    throw new NotImplemented("fluid.ir.IRSequenceType.fromString()");
  }

  /** @exception fluid.NotImplemented */
  public String toString(Object o) {
    throw new NotImplemented("fluid.ir.IRSequenceType.toString()");
  }

  public boolean equals(Object o) {
    if (o instanceof IRSequenceType) {
      IRSequenceType t = (IRSequenceType) o;
      return this.getElementType().equals(t.getElementType());
    }
    return false;
  }
}