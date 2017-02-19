/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/EmptyIRSequence.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;

import fluid.util.EmptyEnumeration;

public class EmptyIRSequence implements IRSequence {
  static public EmptyIRSequence prototype = new EmptyIRSequence();

  public EmptyIRSequence() {
  };

  public int size() {
    return 0;
  }

  public boolean isVariable() {
    return false;
  }

  public boolean hasElements() {
    return false;
  }
  public Enumeration elements() {
    return EmptyEnumeration.prototype;
  }

  public boolean validAt(int i) {
    throw new IRSequenceException("empty sequence is empty");
  }
  public boolean validAt(IRLocation loc) {
    throw new IRSequenceException("empty sequence is empty");
  }
  public Object elementAt(int i) {
    throw new IRSequenceException("empty sequence is empty");
  }
  public Object elementAt(IRLocation loc) {
    throw new IRSequenceException("empty sequence is empty");
  }
  public void setElementAt(Object element, int i) {
    throw new IRSequenceException("empty sequence is empty");
  }
  public void setElementAt(Object element, IRLocation loc) {
    throw new IRSequenceException("empty sequence is empty");
  }

  public IRLocation insertElement(Object element) {
    throw new IRSequenceException("empty sequence cannot be added to");
  }
  public IRLocation appendElement(Object element) {
    throw new IRSequenceException("empty sequence cannot be added to");
  }
  public IRLocation insertElementAfter(Object element, IRLocation i) {
    throw new IRSequenceException("empty sequence cannot be added to");
  }
  public IRLocation insertElementBefore(Object element, IRLocation i) {
    throw new IRSequenceException("empty sequence cannot be added to");
  }
  public void removeElementAt(IRLocation i) {
    throw new IRSequenceException("empty sequence cannot be added to");
  }

  public IRLocation location(int i) {
    throw new IllegalArgumentException("empty sequence has no locations");
  }
  public int locationIndex(IRLocation loc) {
    throw new IllegalArgumentException("empty sequence has no locations");
  }

  public IRLocation firstLocation() {
    return null;
  }
  public IRLocation lastLocation() {
    return null;
  }
  public IRLocation nextLocation(IRLocation loc) {
    throw new IllegalArgumentException("empty sequence has no locations");
  }
  public IRLocation prevLocation(IRLocation loc) {
    throw new IllegalArgumentException("empty sequence has no locations");
  }

  public int compareLocations(IRLocation loc1, IRLocation loc2) {
    throw new IllegalArgumentException("empty sequence has no locations");
  }

  public void writeValue(IROutput out) {
  }

  public static IRSequence readValue(IRInput in, IRSequence current)
    throws IOException {
    if (current != null) {
      if (current.isVariable() || current.size() != 0)
        throw new IOException("re-reading sequence as empty!");
    }
    return prototype;
  }

  public void writeContents(IRCompoundType ty, IROutput out) {
  }
  public void readContents(IRCompoundType ty, IRInput in) {
  }

  public boolean isChanged() {
    return false;
  }
  public void writeChangedContents(IRCompoundType ty, IROutput out) {
  }
  public void readChangedContents(IRCompoundType ty, IRInput in) {
  }

  public void describe(PrintStream out) {
    out.println("EmptyIRSequence");
  }
}
