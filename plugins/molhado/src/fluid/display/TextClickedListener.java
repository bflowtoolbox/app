package fluid.display;

import java.util.EventListener;

/**
 * Event handler interface for the TextClickedEvent.
 * @author Aaron Greenhouse
 * @see TextClickedEvent
 * @see TextBean
 */

 /* 3 November 1998
  * Updated to make JavaBeans compliant
  * The only change to do this was to make it extend java.util.EventListener
  */
public interface TextClickedListener extends EventListener
{
  /**
   * Called when a TextClickedEvent is sent.
   */
  public void textClicked( TextClickedEvent e );
}
