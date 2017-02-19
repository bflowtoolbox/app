/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/DigraphListener.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.EventListener;

/** A class of event observers that are notified when 
 * something is changed in a directed graph.
 * @see DigraphEvent
 */
public interface DigraphListener extends EventListener {
  /** Called when structure is added or changed. */
  public void handleDigraphEvent(DigraphEvent e);
}
