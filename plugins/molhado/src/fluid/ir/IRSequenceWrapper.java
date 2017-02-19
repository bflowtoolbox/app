/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRSequenceWrapper.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;

/** Wrap a sequence and delegate all methods to it.
 * To change the way mutations are done, it is only necessary to override <ul>
 * <li> {@link #setElementAt(Object,IRLocation)}
 * <li> {@link #insertElementAt(Object,InsertionPoint)}
 * <li> {@link #removeElementAt(IRLocation)}
 * </ul>
 * To additionally change all accesses of elements, one must also override <ul>
 * <li> {@link #elements()}
 * <li> {@link #elementAt(IRLocation)}
 * </ul>
 * To override all functionality (so that the wrapped sequence is not used
 * directly at all, one must override <ul>
 * <li> {@link #size()}
 * <li> {@link #isVariable()}
 * <li> {@link #hasElements()}
 * <li> {@link #validAt(IRLocation)}
 * <li> {@link #location(int)}
 * <li> {@link #locationIndex(IRLocation)}
 * <li> {@link #firstLocation()}
 * <li> {@link #lastLocation()}
 * <li> {@link #nextLocation(IRLocation)}
 * <li> {@link #prevLocation(IRLocation)}
 * <li> {@link #compareLocations(IRLocation,IRLocation)}
 * </ul>
 * To change the way I/O is done (currently, does nothing)
 * one must override <ul>
 * <li> {@link #writeValue(IROutput)}
 * <li> {@link #writeContents(IRCompoundType,IROutput)}
 * <li> {@link #readContents(IRCompoundType,IRInput)}
 * <li> {@link #isChanged()}
 * <li> {@link #writeChangedContents(IRCompoundType,IROutput)}
 * <li> {@link #readChangedContents(IRCompoundType,IRInput)}
 * </ul> 
 */ 
public abstract class IRSequenceWrapper implements IRSequence
{
  protected final IRSequence sequence;

  /** Create an IRSequence that delegates all requests to the parameter. */
  protected IRSequenceWrapper(IRSequence seq) {
    sequence = seq;
  }

  public int size() {
    return sequence.size();
  }

  public boolean isVariable() {
    return sequence.isVariable();
  }

  public boolean hasElements() {
    return sequence.hasElements();
  }

  public Enumeration elements() {
    return sequence.elements();
  }

  public boolean validAt(int i) {
    IRLocation loc = location(i);
    if (loc == null)
      throw new IRSequenceException("index out of bounds");
    return validAt(loc);
  }
  public boolean validAt(IRLocation loc) {
    return sequence.validAt(loc);
  }

  public Object elementAt(int i) {
    IRLocation loc = location(i);
    if (loc == null)
      throw new IRSequenceException("index out of bounds");
    return elementAt(loc);
  }
  public Object elementAt(IRLocation loc) {
    return sequence.elementAt(loc);
  }

  public void setElementAt(Object element, int i) {
    IRLocation loc = location(i);
    if (loc == null)
      throw new IRSequenceException("index out of bounds");
    setElementAt(element,loc);
  }
  public void setElementAt(Object element, IRLocation loc) {
    sequence.setElementAt(element,loc);
  }

  public IRLocation insertElement(Object element) {
    return insertElementAt(element,InsertionPoint.first);
  }

  public IRLocation appendElement(Object element) {
    return insertElementAt(element,InsertionPoint.last);
  }
  
  public IRLocation insertElementBefore(Object element, IRLocation i) {
    return insertElementAt(element,InsertionPoint.createBefore(i));
  }

  public IRLocation insertElementAfter(Object element, IRLocation i) {
    return insertElementAt(element,InsertionPoint.createAfter(i));
  }

  public IRLocation insertElementAt(Object element, InsertionPoint ip) {
    return ip.insert(sequence,element);
  }
  
  public void removeElementAt(IRLocation i) {
    sequence.removeElementAt(i);
  }
    
  public IRLocation location(int i) {
    return sequence.location(i);
  }

  public int locationIndex(IRLocation loc) {
    return sequence.locationIndex(loc);
  }

  public IRLocation firstLocation() {
    return sequence.firstLocation();
  }

  public IRLocation lastLocation() {
    return sequence.lastLocation();
  }

  public IRLocation nextLocation(IRLocation loc) {
    return sequence.nextLocation(loc);
  }
  
  public IRLocation prevLocation(IRLocation loc) {
    return sequence.prevLocation(loc);
  }

  public int compareLocations(IRLocation loc1, IRLocation loc2) {
    return sequence.compareLocations(loc1,loc2);
  }

  // I/O currently NOPs (sequences are "derived", not in storable form.

  public void writeValue(IROutput out) throws IOException {}

  public void writeContents(IRCompoundType t, IROutput out) throws IOException
  {}

  public void readContents(IRCompoundType t, IRInput in) throws IOException {}

  public boolean isChanged() { return false; }

  public void writeChangedContents(IRCompoundType t, IROutput out)
    throws IOException {}

  public void readChangedContents(IRCompoundType t, IRInput in)
    throws IOException {}

  public void describe(PrintStream out) {
    out.print("wrapped ");
    sequence.describe(out);
  }
}
