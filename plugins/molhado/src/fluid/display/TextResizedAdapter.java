package fluid.display;

import java.beans.PropertyChangeEvent;

/**
 * An adapter class for <TT>TextResizedListener</TT>.  This is a bit of an abuse 
 * of the adapter class, because it actually implements <TT>propertyChange()</tt> so
 * that it calls <TT>textResized()</TT>
 * 
 * @author Aaron Greenhouse
 * @see TextResizedListener
 * @see TextResizedAdapter
 * @see TextBean
 */

 /* 3 November 1998
  * Updated to make JavaBeans compliant
  * Changed to be an Property Change Event listener on the property textWidth
  */
public abstract class TextResizedAdapter implements TextResizedListener
{
  /**
   * Called when a TextResizedEvent is sent.
   * @param e The TextResizedEvent containing the old and new textWidth values.
   */
  public void textResized( final TextResizedEvent e ) {
    
  }

  /**
   * Called when a TextResizedEvent is sent.  This method should always call
   * textResized.
   * @param e The TextResizedEvent containing the old and new textWidth values.
   */
  public void propertyChange( final PropertyChangeEvent e )
  {
    textResized( (TextResizedEvent)e );
  }
}
