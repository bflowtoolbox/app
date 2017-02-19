package fluid.display;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Event handler interface for the TextResizedEvent.  For historical reasons, the
 * <tt>propertyChange()</tt> method should call <tt>textResized()</TT>, which
 * is the preferred method.
 * 
 * @author Aaron Greenhouse
 * @see TextResizedEvent
 * @see TextResizedAdapter
 * @see TextBean
 */

 /* 3 November 1998
  * Updated to make JavaBeans compliant
  * Changed to be an Property Change Event listener on the property textWidth
  */
public interface TextResizedListener extends PropertyChangeListener
{
  /**
   * Called when a TextResizedEvent is sent.
   * @param e The TextResizedEvent containing the old and new textWidth values.
   */
  public void textResized( TextResizedEvent e );

  /**
   * Called when a TextResizedEvent is sent.  This method should always call
   * textResized.
   * @param e The TextResizedEvent containing the old and new textWidth values.
   */
  public void propertyChange( PropertyChangeEvent e );
}
