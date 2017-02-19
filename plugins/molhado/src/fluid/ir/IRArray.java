/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRArray.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class IRArray implements IRSequence {
  private final Slot[] contents;
  private final SlotFactory slotFactory;

  public IRArray(int size, SlotFactory sf) {
    slotFactory = sf;
    Slot emptySlot = sf.undefinedSlot();
    contents = new Slot[size];
    for (int i = 0; i < size; ++i) {
      contents[i] = emptySlot;
    }
  }

  public int size() {
    return contents.length;
  }

  public boolean isVariable() {
    return false;
  }

  public boolean hasElements() {
    return contents.length > 0;
  }

  public Enumeration elements() {
    return slotFactory.newEnumeration(new IRArrayEnumeration(this));
  }

  public boolean validAt(int i) {
    if (i < 0 || size() <= i)
      throw new IRSequenceException("index out of bounds");
    return contents[i].isValid();
  }
  public boolean validAt(IRLocation loc) {
    return validAt(loc.getID());
  }

  public Object elementAt(int i) {
    if (i < 0 || size() <= i)
      throw new IRSequenceException("index out of bounds");
    return contents[i].getValue();
  }
  public Object elementAt(IRLocation loc) {
    return elementAt(loc.getID());
  }

  public void setElementAt(Object element, int i) {
    if (i < 0 || size() <= i)
      throw new IRSequenceException("index out of bounds");
    contents[i] = contents[i].setValue(element);
  }
  public void setElementAt(Object element, IRLocation loc) {
    setElementAt(element, loc.getID());
  }

  // Arrays do not permit insertion or removal
  public IRLocation insertElement(Object element) {
    throw new IRSequenceException("arrays are fixed size");
  }
  public IRLocation appendElement(Object element) {
    throw new IRSequenceException("arrays are fixed size");
  }
  public IRLocation insertElementAfter(Object element, IRLocation i) {
    throw new IRSequenceException("arrays are fixed size");
  }
  public IRLocation insertElementBefore(Object element, IRLocation i) {
    throw new IRSequenceException("arrays are fixed size");
  }
  public void removeElementAt(IRLocation i) {
    throw new IRSequenceException("arrays are fixed size");
  }

  public IRLocation location(int i) {
    if (i < 0 || size() <= i)
      return null;
    return IRLocation.get(i);
  }
  public int locationIndex(IRLocation loc) {
    return loc.getID();
  }

  public IRLocation firstLocation() {
    return location(0);
  }
  public IRLocation lastLocation() {
    return location(size() - 1);
  }
  public IRLocation nextLocation(IRLocation loc) {
    return location(loc.getID() + 1);
  }
  public IRLocation prevLocation(IRLocation loc) {
    return location(loc.getID() - 1);
  }

  public int compareLocations(IRLocation loc1, IRLocation loc2) {
    return loc1.getID() - loc2.getID();
  }

  public void writeValue(IROutput out) throws IOException {
    int size = size();
    out.writeSlotFactory(slotFactory);
    out.writeInt(size);
  }

  public static IRSequence readValue(IRInput in, IRSequence current)
    throws IOException {
    SlotFactory sf = in.readSlotFactory();
    int size = in.readInt();
    if (current != null) {
      if (current.size() != size)
        throw new IOException(
          "re-reading array[" + current.size() + "] with length = " + size);
      return current;
    }
    //System.out.println("Array " + size);
    return sf.getOldFactory().newSequence(size);
  }

  public void writeContents(IRCompoundType t, IROutput out)
    throws IOException {
    int size = size();
    boolean allValid = true;
    boolean allInvalid = true;
    for (int i = 0; i < size; ++i) {
      if (contents[i].isValid()) {
        allInvalid = false;
      } else {
        allValid = false;
      }
    }
    out.writeByte(allValid ? '+' : (allInvalid ? '-' : '='));
    if (allValid) {
      for (int i = 0; i < size; ++i) {
        Slot s = contents[i];
        s.writeValue(t.getType(i), out);
      }
      return;
    }
    if (allInvalid)
      return;
    for (int i = 0; i < size; ++i) {
      Slot s = contents[i];
      if (s.isValid()) {
        out.writeInt(i);
        s.writeValue(t.getType(i), out);
      }
    }
    out.writeInt(-1);
  }

  public void readContents(IRCompoundType t, IRInput in) throws IOException {
    int size = size();
    byte kind = '+';
    if (in.getRevision() >= 4)
      kind = in.readByte();
    if (kind == '+') {
      for (int i = 0; i < size; ++i) {
        contents[i] = contents[i].readValue(t.getType(i), in);
      }
    } else if (kind != '-') {
      int i;
      while ((i = in.readInt()) >= 0) {
        contents[i] = contents[i].readValue(t.getType(i), in);
      }
    }
  }

  public boolean isChanged() {
    int size = size();
    for (int i = 0; i < size; ++i) {
      if (contents[i].isChanged())
        return true;
    }
    return false;
  }

  public void writeChangedContents(IRCompoundType t, IROutput out)
    throws IOException {
    int size = size();
    boolean allChanged = true;
    for (int i = 0; i < size; ++i) {
      Slot s = contents[i];
      if (!s.isChanged()) {
        allChanged = false;
        break;
      }
    }
    out.writeBoolean(allChanged);
    for (int i = 0; i < size; ++i) {
      Slot s = contents[i];
      if (allChanged) {
        s.writeValue(t.getType(i), out);
      } else if (s.isChanged()) {
        out.writeInt(i);
        s.writeValue(t.getType(i), out);
      }
    }
    if (!allChanged)
      out.writeInt(-1);
  }

  public void readChangedContents(IRCompoundType t, IRInput in)
    throws IOException {
    int size = size();
    boolean allChanged = in.readBoolean();
    if (allChanged) {
      for (int i = 0; i < size; ++i) {
        contents[i] = contents[i].readValue(t.getType(i), in);
      }
    } else {
      int i;
      while ((i = in.readInt()) != -1) {
        contents[i] = contents[i].readValue(t.getType(i), in);
      }
    }
  }

  public void describe(PrintStream out) {
    int n = size();
    out.println("IRArray[" + n + "]");
    for (int i = 0; i < n; ++i) {
      out.print(" " + i + " => ");
      contents[i].describe(out);
    }
  }
}

class IRArrayEnumeration implements Enumeration {
  IRArray arr;
  int index;
  int max;
  IRArrayEnumeration(IRArray seq) {
    arr = seq;
    index = 0;
    max = arr.size();
  }

  public boolean hasMoreElements() {
    return index < max;
  }

  public Object nextElement() throws NoSuchElementException {
    if (index < max) {
      Object element = arr.elementAt(index);
      ++index;
      return element;
    } else {
      throw new NoSuchElementException("at end of array");
    }
  }
}
