package fluid.display;

import java.beans.PropertyChangeEvent;


/**
 * This event is sent by the TextBean object whenever the number of characters
 * it can display on a line changes.  This is a <CITE>PropertyChangeEvent</CITE>
 * for the TextBean property "textWidth".  The <TT>getNewValue()</TT> and get
 * <TT>getOldValue()</TT> methods return <TT>Integer</TT> objects holding the
 * width of the line in characters of the default font.  The class has two 
 * convienence methods <TT>getOldWidth()</TT> and <TT>getNewWidth()</TT> that
 * return the widths as Java <tt>int</TT> values.
 * @author Aaron Greenhouse
 * @see TextBean
 * @see TextResizedListener
 */

/* 3 November 1998 
 * Class is now a Property Change Event, on the property textWidth.
 * - Parent class changed to java.util.PropertyChangeEvent
 * - field for holding width (the old numChars) is removed
 * - getWidth is deprecated
 * - now has getNewWidth and getOldWidth which use get{New,Old}Value()
 */
public class TextResizedEvent extends PropertyChangeEvent
{
  /** Name of the property that this is a Change Event for */
  public static String TEXT_WIDTH = "textWidth";

  /**
   * Create a new TextClickedEvent Object.
   * @param tb The TextBean that was clicked on.
   * @param oldw The old width of a line in characters.
   * @param width The new width of a line in characters.
   */
  public TextResizedEvent( final TextBean tb, final int oldw, final int width )
  {
    super( tb, TEXT_WIDTH, new Integer( oldw ), new Integer( width ) );
  }

  /**
   * Create a new TextClickedEvent Object.
   * @deprecated Use TextResizedEvent( TextBean, int, int ) instead
   * @param tb The TextBean that was clicked on.
   * @param num The width of a line in characters.
   */
  public TextResizedEvent( final TextBean tb, final int num )
  {
    this( tb, 0, num );
  }

  /**
   * Get the TextBean that sent the event.  This returns the same object as
   * <TT>getSource()</TT>, but performs the type cast to <TT>TextBean</TT>
   * internally.
   * @return The TextBean that was resized
   */
  public TextBean getSender()
  {
    return (TextBean)getSource();
  }

  /**
   * Get the old line width.
   * @return The old line width in characters of the default font.
   */
  public int getOldWidth()
  {
    return ((Integer)getOldValue()).intValue();
  }

  /**
   * Get the new line width.
   * @return The new line width in characters of the default font.
   */
  public int getNewWidth()
  {
    return ((Integer)getNewValue()).intValue();
  }

  /**
   * Get the new width of a line.
   * @deprecated Use getNewWidth()
   * @return The width
   */
  public int getWidth()
  {
    return getNewWidth();
  }
}
