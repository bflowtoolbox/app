package fluid.display;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * This event is sent by the TextBean object whenever the mouse is clicked
 * while over a character.
 * @author Aaron Greenhouse
 * @see TextBean
 * @see TextClickedListener
 */
public class TextClickedEvent extends EventObject {
  /**
   * Mask out the ALT bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.ALT_MASK 
   */
  public static final int ALT_MASK = InputEvent.ALT_MASK;

  /**
   * Mask out the BUTTON1 bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.1BUTTON1_MASK 
   */
  public static final int BUTTON1_MASK = InputEvent.BUTTON1_MASK;

  /**
   * Mask out the BUTTON2 bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.BUTTON2_MASK 
   */
  public static final int BUTTON2_MASK = InputEvent.BUTTON2_MASK;

  /**
   * Mask out the BUTTON3 bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.BUTTON3_MASK 
   */
  public static final int BUTTON3_MASK = InputEvent.BUTTON3_MASK;

  /**
   * Mask out the CTRL bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.CTRL_MASK 
   */
  public static final int CTRL_MASK = InputEvent.CTRL_MASK;

  /**
   * Mask out the META bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.META_MASK 
   */
  public static final int META_MASK = InputEvent.META_MASK;

  /**
   * Mask out the SHIFT bit of the key modifiers.
   * Same value as java.awt.event.InputEvent.SHIFT_MASK 
   */
  public static final int SHIFT_MASK = InputEvent.SHIFT_MASK;

  private final MouseEvent event;
  private final TextCoord where;

  /**
   * Create a new TextClickedEvent Object.
   * @param tb The TextBean that was clicked on.
   * @param w The character location of the click.
   * @param num The number of times the button was pressed.
   * @param m The key modifer flags active at the time of the click.
   */
  public TextClickedEvent( final TextBean tb, final TextCoord w,
			   final MouseEvent e ) {
    super( tb );
    where = w;
    event = e;
  }

  /**
   * Get the TextBean that sent the event.  This returns the same object as
   * <TT>getSource()</TT>, but performs the type case to <TT>TextBean</TT>
   * internally.
   * @return The TextBean that was clicked on.
   */
  public TextBean getSender() {
    return (TextBean)getSource();
  }

  /**
   * Get the character location over which the click occured.
   * @return The location of the click.  
   */
  public TextCoord getTextCoord() {
    return where;
  }
  
  /** 
   * Get the point where the mouse was clicked.
   * @return The coordinate of the click.
   */
  public Point getPoint() {
    return event.getPoint();
  }

  /**
   * Get the number of times the mouse was clicked.
   * @return The number of clicks.
   */
  public int getNumClicks() {
    return event.getClickCount();
  }

  /**
   * Get the key modifiers that were active at the time of the click.
   * @return The modifers.
   */ 
  public int getModifiers() {
    return event.getModifiers();
  }

  /**
   * Was the ALT key down?
   * @return <TT>true</TT> iff teh Alt key was down.
   */
  public boolean isAltDown() {
    return (event.getModifiers() & ALT_MASK) != 0;
  }

  /**
   * Was the CTRL key down?
   * @return <TT>true</TT> iff the Control key was down.
   */
  public boolean isCtrlDown() {
    return (event.getModifiers() & CTRL_MASK) != 0;
  }

  /**
   * Was the SHIFT key down?
   * @return <TT>true</TT> iff the Shift key was down.
   */
  public boolean isShiftDown() {
    return (event.getModifiers() & SHIFT_MASK) != 0;
  }

  /**
   * Was the META key down?
   * @return <TT>true</TT> iff the Meta key was down.
   */
  public boolean isMetaDown() {
    return (event.getModifiers() & META_MASK) != 0;
  }

  /**
   * Was the triggering MouseEvent the Popup Menu trigger?
   * @return <tt>true</tt> iff the mouse event was the popup menu trigger.
   */
  public boolean isPopupTrigger() {
    return event.isPopupTrigger();
    // Bogus implementation for now... isPopupTrigger() seems to be broken?
    // return (event.getModifiers() & BUTTON3_MASK) != 0;
  }
}
