/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRObservable.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/** A subclass of the standard observerable class where changes
 * are relative to an IRNode.  Normal observers are only notified
 * in the event that a value is changed.  But a special class of
 * observers are also notified when a value is defined that was
 * previously undefined.
 */
public class IRObservable extends Observable {
  private Vector defineObservers = new Vector();

  /** Add an observer that is also notified when a previously undefined
   * slot is given a value.
   */
  public synchronized void addDefineObserver(Observer o) {
    super.addObserver(o);
    defineObservers.addElement(o);
  }

  /** Remove an observer from getting either normal or defining events.
   */
  public synchronized void deleteDefineObserver(Observer o) {
    defineObservers.removeElement(o);
    super.deleteObserver(o);
  }

  /** Remove all defining and normal observers.
   */
  public synchronized void deleteDefineObservers() {
    defineObservers.removeAllElements();
    super.deleteObservers();
  }

  public void notifyIRObservers(IRNode node) {
    setChanged();
    if (countObservers() != 0) {
      notifyObservers(node);
    } else {
      // System.out.println("Skipping notifyObservers");
    }
  }

  public synchronized void notifyDefineObservers(IRNode node) {
    if (defineObservers.size() == 0) {
      return;
    }

    Enumeration e = defineObservers.elements();
    while (e.hasMoreElements()) {
      Observer o = (Observer)e.nextElement();
      o.update(this,node);
    }
  }

  /*
  public void addObserver(Observer o) {
    super.addObserver(o);
  }
  */
}
