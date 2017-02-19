/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRSequenceEnumeration.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/** Enumeration of elements in an IRSequence.
 */
public class IRSequenceEnumeration implements Enumeration {
  private IRSequence seq;
  private IRLocation next;
  private boolean nextIsValid = false;

  public IRSequenceEnumeration(IRSequence s) {
    seq = s;
    next = s.firstLocation();
  }

  public boolean hasMoreElements() {
    return next != null;
  }

  public Object nextElement() throws NoSuchElementException {
    try {
      try {
        return seq.elementAt(next);
      } finally {
        next = seq.nextLocation(next);
      }
    } catch (NullPointerException ex) {
      throw new NoSuchElementException("end of sequence");
    }
  }
}