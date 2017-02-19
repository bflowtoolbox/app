package fluid.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/* 8 February 2002
 * Changed implemenation of listener management to be more efficient for
 * event sending.  Tradeoff is more expensive removal and deletion.
 */

/* 28 January 2002 
 * Changed so that a Horizontal Scrollbar is used if there are lines of 
 * text longer than the width of the box.  This is needed because the
 * Java unparser sometimes cannot produce lines shorter than the width
 * of the box (e.g., because of variables names).
 *
 * Also fixed a problem with setFirstLine.  It didn't handle properly the 
 * case of a the last acceptable first line.
 */

/* 11 February 99
 * Implements Autoscroll
 *  - Only scrolls if it's parent is a JViewport (it's in a JScrollPane)
 */

/* Changed Week of 8 February 99
 * GraphicsArray now uses the Container classes of
 * java.util (specifically TreeMap).
 */

/* Changed on 99-01-28: 
 * TextBean is now implements Scrollable, and relies on a JScrollPane
 * to handle the scrollbar.
 *
 * Changes include:
 * - removing fields: showScroller, scroller, firstLine, visible, 
 *     reallocBuffers, useBuf1, drawAll, buffer1, buffer2, oldFirst
 * - removing anything having to do with scrollbars: 
 *     the scrollbar and key listeners
 *     updateScroller
 * - remove anything having to do with buffers
 *     realloacBuffers()
 *     replace calls to reallocBuffers() with call to calcNewWidth()
 * - preferred Height is now as tall as the entire text
 * - changed getFirstLine(), setFirstLine(), and makeLinesVisible() to
 *   work in the new regime
 * - implemented Scrollable interface
 */

/*
 * Changed on 98-01-06: fixed setText() so that it resets the first line
 * to zero.

 * Changed on 98-01-07: added setFirstLine() method

 * Changed on 98-01-19: fixed problems when using lines with 0 characters

 * Changed on 98-02-18: all methods that affect style (setDefaultStyle, 
 * setDefaultFont, setDefaultSize, applyStyle, !setText!) no longer perform a 
 * repaint.  The client code must perform the repaint itself.
 *
 * Also fixed a problem with applyStyles that caused it not apply a
 * style over the entire region when the region already had more than one
 * style in it.  This used to work correctly, but I broke it, and now
 * I've fixed it.
 *
 * Created the StyleManager interface.  This should make it easier to change
 * how TextBean.styles actually works.  Currently there are two versions:
 * StylesVector (The old Styles class) and StylesNodes (uses a linked list
 * instead of a vector).  On a Power Mac G3/266 under MRJ2.0, StylesVector
 * is 4 times faster than StylesNodes.  On the SPARCstation 4, it doesn't 
 * make a difference.

 * Changed 98 Jul 23
 * Ranges changed to be (inclusive, exclusive) instead of (inclusive, 
 * inclusive).  This eliminates the need for the class LinesOfText.
 * Also changed paint() so that if the a style goes beyond the end a line,
 * the whole line (to the end of the window) is painted

 * Changed 98 Jul 24
 * Rewrote paint() so that the outer loop is around the styles instead of 
 * the lines.  This makes the method easier to understand (IMO).  It has
 * also been changed so that styles can end after the last line of text
 * on a line, and if they do, the background color will extend to that 
 * point.  For example:
 *
 * Column 0123456789
 * line 0 This is    
 *      1 a 
 *      2 test
 *
 * If the style w/red text and green background goes from (0, 2)-->(1,5),
 * then "Th" on the first line will be in the default style,
 * "is is   " will be red on green, "a    " (to under the space between 's'
 * and 'i') will be red on green, and the rest of the text will be default
 * 
 * Added applyStyle( TextStyle, TextRegion );

 * Changed 98 July 27
 * Paint() now uses two buffers, and copies from visible portions from one to
 * the other.  Only text that was "off screen" is drawn in the loop.
 *
 * Fixed bug that caused the quick-search in applyStyle to loop forever if
 * the start of a style equaled the end of the last style.  I believe this
 * only happens in degenerate cases where the last line is "", so the
 * last style ends on (x,0), and someone tries to style it (the region would
 * then be (x,0)-->(x,0)).  The fix was to make applyStyle() return immediately
 * if begin >= end (before it was begin > end).
 */

/*
 * Changed 98 Sept 1 
 * In addition to listening for Component events, I changed the bean so that
 * it triggers TextResizeEvents on being validated.  This will catch
 * calls to setBounds() and other methods that manipulate the size and shape
 * of the bean.
 */

/* Changed 98 Sept 9
 * Added the ability to insert small graphics into the text.  The
 * graphics are sized to be the same size as the character in the text.
 */

/* Change 98 Sept 10
 * Added makeLineVisible() and makeLinesVisible()
 */

/* Changed 98 Sept 15
 * Fixed a bug: charWidth was not being updated when setDefaultSize() was
 * called.
 *
 * Added private method updateFontInfo() that updates ascent, descent, and
 * charWidth.  Called by setDefaultFont(), setDefaultStyle(), and
 * setDefaultSize().
 */

/* Changed 98 Sept 17
 * Officially changed to use JFC.  paint() renamed to paintComponent
 * Parent class changed to JComponent.  update() deleted.  had to switch
 * to using invalidate() and a flag instead of validate() to reallocate
 * the buffers because JFC doesn't use validate() in the same that AWT
 * does.  
 */

/* 3 November 1998
 * Added the textWidth property.  It is a bound property for which TextResizedEvent
 * is the PropertyChangeEvent.
 *
 * Added the textHeight property.
 *
 * getCharDimensions renamed to getTextDimensions; returns (textHeigh, textWidth)
 *
 * Identified properties:
 * - textWidth (readable, bound)
 * - textHeight (readable)
 * - textDimensions (readable; derived from textWidth and textHeight)
 * - text (readable, writable)
 * - firstLine (readable, writable)
 * - minHeight (readable, writable)
 * - minWidth (readable, writable)
 * - defaultStyle (readable, writable)
 * - defaultFont (readable, writable)
 * - defaultSize (readable, writable)
 * - scrollbarShown (readable, writable) [replaced showScrollbar]
 */

/* 4/5 November 1998
 * Made serializable
 *  - (Already implements java.io.Serializable from Component)
 *  - Made GraphicsInfo serializable (also moved it out of TextBean)
 *  - Made GraphicsArray serializable (also moved it out of TextBean)
 *  - Identified transient fields (those dealing with redraw information)
 *  - Listener vectors are also transient
 *  - need to write own writeObject() and readObject() to deal with transient
 *    fields
 *  - Need to make Internal event listeners serializable
 *    + ClickAdapter
 *    + ResizedAdapter
 *    + KeyboardAdapter
 *    + ScrollAdapter
 *  - Needed to create a parameterless constructor
 */

/**
 * <I>Need to fill this in some day</I>.
 *
 * <P>Note: The TextBean <em>must</em> have it's peer created before being
 * serialized.  This is because the addNotify() method be called or the
 * bean will throw a NullPointerException during serialization.
 *
 * <p>Except for managing event listeners, TextBean objects are not safe for
 * use from multiple threads.  Like anything else in the AWT, its methods
 * should only be invoked from within the AWT thread.
 *
 * <P>The following methods are only useful if the TextBean is inside
 * a JViewport:
 * <UL>
 * <LI><code>autoscroll()</code>
 * <LI><code>setFirstLine()</code>
 * <LI><code>makeLineVisible()</code>
 * <LI><code>makeLinesVisible()</code>
 * </UL>
 *
 * @author Aaron Greenhouse
 * @see TextClickedEvent
 * @see TextClickedListener
 */
public class TextBean
  extends JComponent
  implements Scrollable, java.awt.dnd.Autoscroll {
  // Used to get the LineMetrics of the font
  private static final FontRenderContext fontContext =
    new FontRenderContext(null, false, false);

  private Dimension requestedSize;

  private String defaultFont;
  private int defaultSize;
  private TextStyle defaultStyle;

  private StyleManager styles;
  private GraphicsArray garray;

  private String[] text;
  private int maxLineWidth;

  // Not all event listeners may be serializable, so these need
  // to be handled specially
  /*
   * private transient Vector clickListeners;
   * private transient Vector resizedListeners;
   */
  private transient TextClickedListener[] clickListeners;
  private transient TextResizedListener[] resizedListeners;

  private int lineWidth = -1;

  // Set these so that there sum != 0
  // They gon't get initialized for real until addNotify() is called,
  // so updateScrollBar crashes if it is called before the Bean's peers
  // are actually created
  private int ascent = 1;
  private int descent = 1;
  private int charWidth = 0;

  //----------------------------------------------------------------------
  // Special Style Factory
  // (Should probably get rid of this at some point)
  //----------------------------------------------------------------------

  private final StyleManager newStyleManager(
    final String[] lines,
    final TextStyle ts) {
    return new StylesVector(lines, ts);
  }

  //----------------------------------------------------------------------
  // Initializers
  //----------------------------------------------------------------------

  /**
   * Create a field with the specified initial text string, width, and height,
   * and font 
   * @param theText The intial text to display.
   * @param w The minimum width of the text field.
   * @param h The minimum height of the text field.
   * @param f The name of the default font
   * @param s The default text size
   */
  public TextBean(
    final String[] theText,
    final int w,
    final int h,
    final String f,
    final int s) {
    super();
    text = theText;
    updateMaxLineWidth();
    defaultStyle = null; // wait for window to be created to init this
    defaultFont = f;
    defaultSize = s;
    styles = newStyleManager(text, TextStyle.noStyle());

    requestedSize = new Dimension(w, h);
    garray = new GraphicsArray();
    clickListeners = new TextClickedListener[0];
    resizedListeners = new TextResizedListener[0];
    // clickListeners = new Vector();
    // resizedListeners = new Vector();
    addMouseListener(new ClickAdapter(this));
    final ResizingAdapter resizer = new ResizingAdapter();
    addComponentListener(resizer);
    addHierarchyBoundsListener(resizer);

    if (defaultStyle == null)
      defaultStyle = new TextStyle(false, false, Color.black, Color.white);
    setDefaultFont(defaultFont);
  }

  /**
   * Create a field with the specified initial text string, width, and height.
   * The Default font is Courier 12
   * @param theText The intial text to display.
   * @param w The minimum width of the text field.
   * @param h The minimum height of the text field.
   */
  public TextBean(final String[] theText, final int w, final int h) {
    this(theText, w, h, "Courier", 12);
  }

  /**
   * Create a field with the specified initial text string.
   * @param theText The intial text to display.
   */
  public TextBean(final String[] theText) {
    this(theText, 200, 200);
  }

  /**
   * Create a field with no initial text
   */
  public TextBean() {
    this(new String[] { "" });
  }

  /**
   * Override addNotify so that we can create an initial default style---
   * not bold, not italic, with the component's default Foreground and
   * background colors.
   */
  /*
    public void addNotify() {
      super.addNotify();
      if( defaultStyle == null )
        defaultStyle = new TextStyle( false, false,
  				    getForeground(), getBackground() );
      setDefaultFont( defaultFont );
    }
  */

  //----------------------------------------------------------------------
  // Methods needed to be a component
  //----------------------------------------------------------------------

  /**
   * Get the preferred size of the field.
   * @return The preferred size of the field, which is determined by the 
   * specified minWidth and minHeight attributes
   */
  public Dimension getPreferredSize() {
    return getMinimumSize();
  }

  /**
   * Get the minimum size of the field.
   * @return The minimum size, which is currently equal to the preferred size
   */
  public Dimension getMinimumSize() {
    final int h = text.length * (ascent + descent);
    final int w = charWidth * maxLineWidth;
    return new Dimension(w, h);
    // return new Dimension( requestedSize.width, h );
  }

  /**
   * Draw the text.
   * @param g The graphics context.
   */
  public void paintComponent(final Graphics graphics) {
    final Graphics g = graphics.create();
    final Color defaultBack = defaultStyle.getPrimaryBackground(defaultStyle);
    final Rectangle r = g.getClipBounds();
    final int firstLine = getLineUnderPoint(r.getLocation());
    final int visible = r.height / (ascent + descent);
    // Draw one more line than visible to catch any partial line
    final int lastLine = firstLine + visible + 1;

    // Erase everything
    g.setColor(defaultBack);
    g.fillRect(r.x, r.y, r.width, r.height);

    // Find the first style to use
    final Enumeration styleEnum = styles.getStyles();
    StyleRange currentRange = null;
    {
      final TextCoord begin = new TextCoord(firstLine, 0);
      while (styleEnum.hasMoreElements()) {
        currentRange = (StyleRange) styleEnum.nextElement();
        if (currentRange.inStyle(begin))
          break;
      }
    }

    int line = firstLine;
    int baseline = ascent + firstLine * (ascent + descent);
    int pos = 0;
    float xoffset = 0.0f;

    // Loop over the styles
    outer : while (true) {
      final TextStyle ts = currentRange.getStyle();
      final Color back = ts.getPrimaryBackground(defaultStyle);
      final int numSecond = ts.getNumSecondaryBackgrounds(defaultStyle);
      final Color fore = ts.getColor(defaultStyle);
      final Font font = ts.getFont(defaultStyle, defaultFont, defaultSize);
      g.setFont(font);
      g.setColor(fore);

      // Loop over the lines of text inside a style
      while (currentRange.getEnd().after(line, pos)) {
        final boolean restOfLine = line < currentRange.getEnd().getLine();
        final int newPos =
          restOfLine
            ? text[line].length()
            : Math.min(text[line].length(), currentRange.getEnd().getChar());
        final String s = text[line].substring(pos, newPos);
        final TextLayout tl =
          (s.length() == 0) ? null : new TextLayout(s, font, fontContext);
        final float strWidth = (tl == null) ? 0.0f : tl.getAdvance();

        // paint background
        if (!back.equals(defaultBack) || (numSecond != 0)) {
          final Iterator seconds = ts.getSecondaryBackground(defaultStyle);
          final int width =
            restOfLine ? (r.width - (int) xoffset) : (int) strWidth;
          drawBackground(
            back,
            numSecond,
            seconds,
            g,
            (int) xoffset,
            baseline - ascent,
            width,
            ascent + descent);
          g.setColor(fore);
        }

        // draw text
        if (tl != null) {
          tl.draw((Graphics2D) g, xoffset, baseline);
        }

        // update position
        pos = newPos;
        if (pos >= text[line].length()) {
          xoffset = 0.0f;
          pos = 0;
          line += 1;
          baseline += ascent + descent;
        } else {
          xoffset += strWidth;
        }

        // Done drawing if we fall of the visible portion or run out of text
        if (line > lastLine || line >= text.length)
          break outer;
      }

      // Move to the next style, quit if we run out of styles
      if (styleEnum.hasMoreElements())
        currentRange = (StyleRange) styleEnum.nextElement();
      else
        break outer;
    }

    // Draw graphics
    final Dimension dim = new Dimension(charWidth, ascent + descent);
    final Iterator coords = garray.getTextCoords(firstLine, lastLine);
    while (coords.hasNext()) {
      final TextCoord tc = (TextCoord) coords.next();
      final TextStyle ts = styles.getStyleAt(tc);
      final Color back = ts.getPrimaryBackground(defaultStyle);
      final int numSecond = ts.getNumSecondaryBackgrounds(defaultStyle);
      final Color fore = ts.getColor(defaultStyle);
      final Font font = ts.getFont(defaultStyle, defaultFont, defaultSize);
      final Graphics g2 =
        g.create(
          charWidth * tc.getChar(),
          dim.height * tc.getLine(),
          charWidth,
          dim.height);

      final Iterator igs = garray.getGraphicsAt(tc);
      while (igs.hasNext()) {
        final InsertableGraphic ig = (InsertableGraphic) igs.next();

        // Fill the cell with the background color to get rid of the
        // underlying character
        if (ig.clearCharacter()) {
          final Iterator seconds = ts.getSecondaryBackground(defaultStyle);
          drawBackground(
            back,
            numSecond,
            seconds,
            g2,
            0,
            0,
            dim.width,
            dim.height);
        }
        // Ask the graphic to draw itself
        ig.drawGraphic(g2, fore, font, dim);
      }
      g2.dispose();
    }
  }

  /**
   * Draw the background for a section of text.   Does work quite right
   * if there are too many secondary colors.
   * @param primary Primary background color
   * @param numSecond Number of secondary background colors
   * @param secondary Iterator over the secondary background colors
   * @param g The graphics context to use
   * @param sx The x position to start drawing the background
   * @param xy The y position to start drawing the background
   * @param width The width of the background
   * @param height The height of the background
   */
  private void drawBackground(
    final Color primary,
    final int numSecond,
    final Iterator secondary,
    final Graphics g,
    final int sx,
    final int sy,
    final int width,
    final int height) {
    final int secondaryHeight = 2;

    final int primaryHeight = height - (secondaryHeight * numSecond);
    g.setColor(primary);
    g.fillRect(sx, sy, width, primaryHeight);

    // final int x2 = sx + width - 1;
    int y = sy + primaryHeight;
    for (int i = 0; i < numSecond; i++) {
      final Color c = (Color) secondary.next();
      g.setColor(c);
      g.fillRect(sx, y, width, secondaryHeight);
      y += secondaryHeight;
    }
  }

  //----------------------------------------------------------------------
  // Methods for manipulating InsertableGraphics
  //----------------------------------------------------------------------

  /**
   * Add a graphic to the text.
   * @param pos The text position of the graphic
   * @param g The graphic to add
   */
  public void addGraphic(final TextCoord pos, final InsertableGraphic g) {
    garray.insert(pos, g);
  }

  /**
   * Add a graphic to the text.
   * @param line The line position of the graphic
   * @param pos The character position of the graphic
   * @param g The graphic to add
   */
  public void addGraphic(
    final int line,
    final int col,
    final InsertableGraphic g) {
    garray.insert(new TextCoord(line, col), g);
  }

  /**
   * Remove all the graphics from the given location.
   * @param pos The location of the graphic to remove
   */
  public void removeGraphic(final TextCoord pos) {
    garray.remove(pos);
  }

  /**
   * Remove a specific graphic from the given location
   * @param pos The location of the graphic to remove
   * @param g The graphic to remove.
   */
  public void removeGraphic(final TextCoord pos, final InsertableGraphic g) {
    garray.remove(pos, g);
  }

  /**
   * Remove all the graphics from the given location.
   * @param line The line position of the graphic
   * @param pos The character position of the graphic
   */
  public void removeGraphic(final int line, final int col) {
    garray.remove(new TextCoord(line, col));
  }

  /**
   * Remove a specific graphic from the given location
   * @param line The line position of the graphic
   * @param pos The character position of the graphic
   * @param g The graphic to remove.
   */
  public void removeGraphic(
    final int line,
    final int col,
    final InsertableGraphic g) {
    garray.remove(new TextCoord(line, col), g);
  }

  /**
   * Remove all the graphics from the text.
   */
  public void removeAllGraphics() {
    garray.clear();
  }

  //----------------------------------------------------------------------
  //----------------------------------------------------------------------
  // Methods for manipulationg Properties
  //----------------------------------------------------------------------
  //----------------------------------------------------------------------

  //----------------------------------------------------------------------
  // Property: text (readable, writable)
  //----------------------------------------------------------------------

  /**
   * Get the current string array
   * @return The text as an array of Strings.  Each String represents a complete
   * line of displayed text.
   */
  public String[] getText() {
    return text;
  }

  /**
   * Set the text and clear the styles and graphics.
   * Can update the scrollbar.  Causes a repaint.
   * @param newText the new string array
   */
  public void setText(final String[] newText) {
    text = newText;
    updateMaxLineWidth();
    removeStyles();
    removeAllGraphics();
    revalidate();
  }

  /**
   * Find the maximum width of a line of text in the current text being
   * displayed.
   */
  private void updateMaxLineWidth() {
    int width = 0;
    for (int i = 0; i < text.length; i++) {
      if (text[i].length() > width)
        width = text[i].length();
    }
    maxLineWidth = width;
  }

  //----------------------------------------------------------------------
  // Property: firstLine (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Get the line number of the first line of text shown in the view.
   * @return the line number of the first line of text
   */
  public int getFirstLine() {
    return getLineUnderPoint(getVisibleRect().getLocation());
  }

  /**
   * Set the first line of text to show in the window.
   * Can cause the scrollbar to move.
   * Causes a repaint.
   * @param line The line number of the first full line to display.
   * This does not mean that the line will be first line displayed.  If the
   * viewport allows for a partial line to be displayed, then the there may
   * be a partial line displayed above the first full line.
   * If <TT>line</TT> < 0 then the first line shown will be 0.
   * If <TT>line</TT> is greater than the last line that can be made the
   * first line and keep the entire window full, then the first line is
   * set to that line.
   */
  public void setFirstLine(final int line) {
    final Component parent = getParent();
    if (parent != null && parent instanceof JViewport) {
      final JViewport port = (JViewport) parent;
      final int visible = getVisibleLines();
      final int lastLine = text.length - visible;
      final int firstLine = Math.min(Math.max(0, line), lastLine);
      final int h = ascent + descent;

      if (firstLine == lastLine) {
        // special case: we may have to allow a partial line at top to keep 
        // from underflowing at the bottom of the viewport.
        final int extra = getVisibleRect().height % h;
        port.setViewPosition(new Point(0, (lastLine - 1) * h + extra));
      } else {
        // normal case
        port.setViewPosition(new Point(0, firstLine * h));
      }
    }
  }

  //----------------------------------------------------------------------
  // Property: minHeight (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Query the minimum height of the requested Viewport.
   * @return The minimum height of the viewport in pixels.
   */
  public int getMinHeight() {
    return requestedSize.height;
  }

  /**
   * Change the minimum height of the requested viewport.
   * @param mh The new minimum hieght of the viewport in pixels.
   */
  public void setMinHeight(final int mh) {
    requestedSize.height = mh;
    // setSize( getPreferredSize() );
  }

  //----------------------------------------------------------------------
  // Property: minWidth (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Query the minimum width of the requested viewport.  
   * @return The minimum width.
   */
  public int getMinWidth() {
    return requestedSize.width;
  }

  /**
   * Set the minimum width of the requested viewport.
   * @param mw The minimum width in pixels.
   */
  public void setMinWidth(final int mw) {
    requestedSize.width = mw;
  }

  //----------------------------------------------------------------------
  // Property: defaultStyle (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Get the default style.
   * @return The default style
   */
  public TextStyle getDefaultStyle() {
    return defaultStyle;
  }

  /**
   * Set the default style
   * Can cause the scrollbar to move.
   * Causes a repaint.
   * Updates the "textWidth" property and causes textResizedEvent.
   * @param d The new default style
   * @exception IllegalArgumentException thrown if all of d's attributes
   * are not set.
   */
  public void setDefaultStyle(final TextStyle d)
    throws IllegalArgumentException {
    if (!(d.isBoldSet()
      & d.isItalicSet()
      & d.isColorSet()
      & d.isBackgroundSet()))
      throw new IllegalArgumentException("default style has unset attrubutes");
    defaultStyle = d;

    final Font font =
      defaultStyle.getFont(defaultStyle, defaultFont, defaultSize);
    updateFontInfo(font);
    calcNewWidth();
    revalidate();
  }

  //----------------------------------------------------------------------
  // Property: defaultFont (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Get the default font.
   * @return The name of the default font.
   */
  public String getDefaultFont() {
    return defaultFont;
  }

  /**
   * Set the default font.
   * Can cause the scrollbar to move.
   * Causes a repaint.
   * Updates the "textWidth" property and causes textResizedEvent.
   * @param f The name of the new default font.
   * @exception IllegalArgumentException thrown if the font is not monospaced
   */
  public void setDefaultFont(final String f) throws IllegalArgumentException {
    final Font font = defaultStyle.getFont(defaultStyle, f, defaultSize);
    final Rectangle2D c1 = font.getStringBounds("i", fontContext);
    final Rectangle2D c2 = font.getStringBounds("M", fontContext);
    final boolean monospaced = c1.equals(c2);

    if (!monospaced) {
      // throw new IllegalArgumentException( f + " is not monospaced" );
      System.err.println(f + " is not monospaced");
    }
    defaultFont = f;

    updateFontInfo(font);
    calcNewWidth();
    revalidate();
  }

  //----------------------------------------------------------------------
  // Property: defaultSize (readable, writeable)
  //----------------------------------------------------------------------

  /**
   * Get the default font size.
   * @return The default font size.
   */
  public int getDefaultSize() {
    return defaultSize;
  }

  /**
   * Set the default font size.
   * Can cause the scrollbar to move.
   * Causes a repaint.
   * Updates the "textWidth" property and causes textResizedEvent.
   * @param s The new default font size.
   */
  public void setDefaultSize(final int s) {
    defaultSize = s;

    final Font font =
      defaultStyle.getFont(defaultStyle, defaultFont, defaultSize);
    updateFontInfo(font);
    calcNewWidth();
    revalidate();
  }

  //----------------------------------------------------------------------
  // Property: textWidth (readable)
  //----------------------------------------------------------------------

  /**
   * Get the width of the visable protion of text in characters.
   * @return the width of the view in characters of the default font.
   */
  public int getTextWidth() {
    return lineWidth;
  }

  //----------------------------------------------------------------------
  // Property: textHeight (readable)
  //----------------------------------------------------------------------

  /**
   * Get the height of the viewable portion of text in characters
   * @return the height of the view in characters of the default font.
   */
  public int getTextHeight() {
    final Component parent = getParent();
    int viewHeight = 0;
    if (parent instanceof JViewport) {
      viewHeight = ((JViewport) parent).getExtentSize().height;
    } else {
      viewHeight = getSize().height;
    }

    final Font font =
      defaultStyle.getFont(defaultStyle, defaultFont, defaultSize);
    final FontMetrics fm = getFontMetrics(font);
    final int height = viewHeight / (fm.getMaxAscent() + fm.getMaxDescent());
    return height;
  }

  //----------------------------------------------------------------------
  // Property: textDimensions (readable)
  //----------------------------------------------------------------------

  /**
   * Get the size of the view in characters.
   * @return The width and height of the view in characters of the default font
   */
  public Dimension getTextDimensions() {
    return new Dimension(getTextWidth(), getTextHeight());
  }

  //----------------------------------------------------------------------
  // Property: charSize (readable)
  //----------------------------------------------------------------------

  public Dimension getCharSize() {
    return new Dimension(charWidth, ascent + descent);
  }

  //----------------------------------------------------------------------
  // Property: visibleLines (readable)
  //----------------------------------------------------------------------

  /**
   * Get the number of completely visible lines in the viewport.
   * @return The number of lines visible
   */
  public int getVisibleLines() {
    return getVisibleRect().height / (ascent + descent);
  }

  //----------------------------------------------------------------------
  // Methods for controlling which text is visible
  //----------------------------------------------------------------------

  /**
   * Insure that the given line is in the visible portion of the window
   * by trying to center it.
   * @param line The line to make visible
   */
  public void makeLineVisible(final int line) {
    final Rectangle vr = getVisibleRect();
    final int visible = vr.height / (ascent + descent);
    setFirstLine(line - (visible >> 1));
  }

  /**
   * Insure that the given line and as many of the (<TT>count</tt>-1)
   * following lines are in the visible portion of the window.  If possible,
   * the group of lines is centered in the display.
   * @param first The first line to make visible
   * @param count The total number of lines to make visible
   */
  public void makeLinesVisible(final int first, int count) {
    // Sanity check
    if (first + count >= text.length) {
      count = text.length - first;
    }

    final Rectangle vr = getVisibleRect();
    final int visible = vr.height / (ascent + descent);

    if (count > visible) {
      setFirstLine(first);
    } else {
      setFirstLine(first + (count >> 1) - (visible >> 1));
    }
  }

  //----------------------------------------------------------------------
  // Methods for updating font sizes, etc
  //----------------------------------------------------------------------

  /**
   * Update the ascent, descent, and charWidth fields.  Called
   * after the font is changed.
   * @param fm The font metrics to use
   */
  private void updateFontInfo(final Font font) {
    final LineMetrics lm = font.getLineMetrics("foo", fontContext);

    descent = (int) (lm.getDescent() + lm.getLeading());
    ascent = (int) lm.getAscent();
    charWidth = (int) font.getStringBounds("M", fontContext).getWidth();
  }

  /**
   * Compute the number of characters that fit on a line of text.
   * Creates a TextResizedEvent if the number of characters per line
   * changed.  If the TextBean is not inside of a Scrollpane then the width
   * in characters is based on the width of the TextBean.  If the TextBean
   * is inside of a Scrollpane then the width in characters is based on the
   * width of the Viewport.
   *
   * @param width The width of the viewport in pixels.
   * @see TextResizedEvent
   * @see TextResizedListener
   */
  private void calcNewWidth() {
    final Component parent = getParent();
    if (parent instanceof JViewport) {
      calcNewWidth(((JViewport) parent).getExtentSize().width);
    } else {
      calcNewWidth(getSize().width);
    }
  }

  /**
   * Compute the number of characters that fit in the given number of pixels.
   * Creates a TextResizedEvent if the number of characters per line
   * changed.
   * @param widthInPixels The width of the viewport in pixels.
   * @see TextResizedEvent
   * @see TextResizedListener
   */
  private void calcNewWidth(final int widthInPixels) {
    // int width = getSize().width;
    final int width = widthInPixels / charWidth;
    // Don't announce negative widths...
    if (width != lineWidth && width > 0) {
      final int oldWidth = lineWidth;
      lineWidth = width;
      final TextResizedEvent tre =
        new TextResizedEvent(this, oldWidth, lineWidth);

      final TextResizedListener[] copy = resizedListeners;
      for (int i = 0; i < copy.length; i++) {
        copy[i].textResized(tre);
      }
    }
  }

  //----------------------------------------------------------------------
  // Methods for finding which character is under a point
  //----------------------------------------------------------------------

  /**
   * Get the index of the line of text under the given point.
   * @param p The point to test.
   * @return The index of the line under the point, or -1 if
   * the point is not over a line of text.
   */
  public int getLineUnderPoint(final Point p) {
    final int line = (p.y / (ascent + descent));
    return ((line >= 0) && (line < text.length)) ? line : -1;
  }

  /**
   * Given a point, return which character (line,char) the point is over.
   * @param p The point to query
   * @return The character position beneath the point.  If the point is
   * past the end of a line, or past the end of the text, then this
   * is <code>null</code>.
   */
  public TextCoord posUnderMouse(final Point p) {
    return posUnderMouse(p, false);
  }

  /**
   * Given a point, return which character (line,char) the point is over.
   * @param p The point to query
   * @param flag <code>true</code> if points past the end of a line 
   * or the end of text should be translated into to legal TextCoord values.
   * @return The character position beneath the point.  If <code>flag</code>
   * is <code>false</code>, and the point is past the end of a line
   * or the end of the text, this is <code>null</code>.  Otherwise, if the 
   * point is past the end of a line, the returned text position is the
   * position just after the last character on the line; if the point is
   * passed the end of text, the returned position is the position after 
   * the last character of the last line.
   */
  public TextCoord posUnderMouse(final Point p, final boolean flag) {
    // find the line the pointer is over
    // This should work correctly because division rounds towards 0.
    final int line = (p.y / (ascent + descent));

    if ((line >= 0) && (line < text.length)) {
      final Enumeration styleEnum = styles.getStyles();
      final TextCoord SOL = new TextCoord(line, 0);
      TextStyle current = null;
      TextCoord EOS = null;

      while (current == null) {
        final StyleRange sr = (StyleRange) styleEnum.nextElement();
        if (sr.inStyle(SOL)) {
          current = sr.getStyle();
          EOS = sr.getEnd();
        }
      }
      FontMetrics fm =
        getFontMetrics(current.getFont(defaultStyle, defaultFont, defaultSize));

      final Rectangle vr = getVisibleRect();
      boolean found = false;
      int ch = 0;
      int xoffset = 0;

      // find character over which the pointer is located
      while (ch < text[line].length() && !found) {
        xoffset += fm.charWidth(text[line].charAt(ch));
        if ((p.x < xoffset) && (p.x < vr.width)) {
          found = true;
        } else {
          ch += 1;
          if (EOS.before(line, ch) && styleEnum.hasMoreElements()) {
            final StyleRange sr = (StyleRange) styleEnum.nextElement();
            current = sr.getStyle();
            EOS = sr.getEnd();
            fm =
              getFontMetrics(
                current.getFont(defaultStyle, defaultFont, defaultSize));
          }
        }
      }

      if (found)
        return new TextCoord(line, ch);
      else {
        if (flag)
          return new TextCoord(line, text[line].length());
        else
          return null;
      }
    } else {
      if (flag) {
        final int row = text.length - 1;
        return new TextCoord(row, text[row].length());
      } else
        return null;
    }
  }

  //----------------------------------------------------------------------
  // Methods that deal with applying styles
  //----------------------------------------------------------------------

  /**
   * Apply a style to region of text.  The region goes from the start point
   * to the character before the end point.
   * @param ts The style to apply.
   * @param start The text location of the start of the region
   * @param end The text location of the end of the region.
   */
  public void applyStyle(
    final TextStyle ts,
    final TextCoord start,
    final TextCoord end) {
    styles.applyStyle(ts, start, end);
  }

  /**
   * Apply a style to region of text.  The region goes from the start point
   * to the character before the end point.
   * @param ts The style to apply.
   * @param l1 The line of the start of the region.
   * @param c1 The character of the start of the region.
   * @param l2 The line of the end of the region.
   * @param c2 The character of the end of the region.
   */
  public void applyStyle(
    final TextStyle ts,
    final int l1,
    final int c1,
    final int l2,
    final int c2) {
    styles.applyStyle(ts, new TextCoord(l1, c1), new TextCoord(l2, c2));
  }

  /** Apply a style over a region of text.  The region goes from the
   * start point to the character before the end point.  On lines
   * other than the first and last, the style is NOT applied
   * to characters outside the min & max bounds of the region.
   * @param ts The style to apply
   * @param tr The region to apply the style over
   */
  public void applyStyle(final TextStyle ts, final TextRegion tr) {
    if (tr.getMin() == 0 && tr.getMax() >= lineWidth) {
      // use more efficient technique
      styles.applyStyle(ts, tr.getBegin(), tr.getEnd());
    } else {
      final int startline = tr.getBegin().getLine();
      final int startchar = tr.getBegin().getChar();
      final int endline = tr.getEnd().getLine();
      final int endchar = tr.getEnd().getChar();
      final int min = tr.getMin();
      final int max = tr.getMax();
      for (int i = startline; i <= endline; ++i) {
        final int c1 = (i == startline) ? startchar : min;
        final int c2 = (i == endline) ? endchar : max;
        styles.applyStyle(ts, new TextCoord(i, c1), new TextCoord(i, c2));
      }
    }
  }

  /**
   * Remove all styles from the text-- make all text have default style.  */
  public void removeStyles() {
    styles.resetStyles(text, TextStyle.noStyle());
  }

  //----------------------------------------------------------------------
  // Mess around with events
  //----------------------------------------------------------------------

  /**
   * Add a listener of click events.
   * @param l The listener to add.
   */
  public void addTextClickedListener(final TextClickedListener l) {
    synchronized (this) {
      final int len = clickListeners.length;
      final TextClickedListener[] new_list = new TextClickedListener[len + 1];
      System.arraycopy(clickListeners, 0, new_list, 0, len);
      new_list[len] = l;
      // Atomic value assignment!!!
      clickListeners = new_list;
    }
  }

  /**
   * Remove a listener of click events.
   * @param l The listener to remove.
   */
  public void removeTextClickedListener(final TextClickedListener l) {
    synchronized (this) {
      final int len = clickListeners.length;
      final TextClickedListener[] new_list = new TextClickedListener[len - 1];
      int where = -1;
      for (int i = 0;(where == -1) && (i < len); i++) {
        if (l == clickListeners[i])
          where = i;
      }

      if (where != -1) {
        System.arraycopy(clickListeners, 0, new_list, 0, where);
        System.arraycopy(
          clickListeners,
          where + 1,
          new_list,
          where,
          len - where - 1);
        // Atomic value assignment!!!
        clickListeners = new_list;
      }
    }
  }

  /**
   * Add a listener of resized events.
   * @param l The listener to add.
   */
  public void addTextResizedListener(final TextResizedListener l) {
    synchronized (this) {
      final int len = resizedListeners.length;
      final TextResizedListener[] new_list = new TextResizedListener[len + 1];
      System.arraycopy(resizedListeners, 0, new_list, 0, len);
      new_list[len] = l;
      // Atomic value assignment!!!
      resizedListeners = new_list;
    }
  }

  /**
   * Remove a listener of resized events.
   * @param l The listener to remove.
   */
  public void removeTextResizedListener(final TextResizedListener l) {
    synchronized (this) {
      final int len = resizedListeners.length;
      final TextResizedListener[] new_list = new TextResizedListener[len - 1];
      int where = -1;
      for (int i = 0;(where == -1) && (i < len); i++) {
        if (l == resizedListeners[i])
          where = i;
      }

      if (where != -1) {
        System.arraycopy(resizedListeners, 0, new_list, 0, where);
        System.arraycopy(
          resizedListeners,
          where + 1,
          new_list,
          where,
          len - where - 1);
        // Atomic value assignment!!!
        resizedListeners = new_list;
      }
    }
  }

  /**
   * Inner class used to handle mouse clicks.  Checks to see what character
   * the click occured over, and creates a TextClickedEvent
   */
  private class ClickAdapter extends MouseAdapter implements Serializable {
    final private TextBean tb;
    public ClickAdapter(final TextBean t) {
      tb = t;
    }

    //    public void mouseClicked( final MouseEvent e )
    //    {
    //      final TextCoord where = posUnderMouse( e.getPoint() );
    //      if( where != null ) {
    //        final TextClickedEvent tce = new TextClickedEvent( tb, where, e );
    //        final TextClickedListener[] copy = clickListeners;
    //        for( int i = 0; i < copy.length; i ++ ) {
    //          copy[i].textClicked( tce );
    //        }
    //      }    
    //    }
    //
    //    public void mousePressed( final MouseEvent e )
    //    {
    //      final TextCoord where = posUnderMouse( e.getPoint() );
    //      if( where != null ) {
    //        final TextClickedEvent tce = new TextClickedEvent( tb, where, e );
    //        final TextClickedListener[] copy = clickListeners;
    //        for( int i = 0; i < copy.length; i ++ ) {
    //          copy[i].textClicked( tce );
    //        }
    //      }    
    //    }

    public void mouseReleased(final MouseEvent e) {
      final TextCoord where = posUnderMouse(e.getPoint());
      if (where != null) {
        final TextClickedEvent tce = new TextClickedEvent(tb, where, e);
        final TextClickedListener[] copy = clickListeners;
        for (int i = 0; i < copy.length; i++) {
          copy[i].textClicked(tce);
        }
      }
    }
  }

  /**
   * Inner class used to handle Resized events; calls calcNewWidth.
   */
  private class ResizingAdapter
    extends ComponentAdapter
    implements HierarchyBoundsListener, Serializable {
    public void componentResized(final ComponentEvent e) {
      if (!(getParent() instanceof JViewport)) {
        calcNewWidth(getSize().width);
      }
    }

    public void ancestorMoved(final HierarchyEvent hierarchyEvent) {
    }

    public void ancestorResized(final HierarchyEvent e) {
      final Component parent = getParent();
      if (parent instanceof JViewport) {
        calcNewWidth(((JViewport) parent).getExtentSize().width);
        revalidate();
      }
    }
  }

  //----------------------------------------------------------------------
  // Methods for serialization
  //----------------------------------------------------------------------

  /**
   * Control the object's serialization.  Writes
   * the non-<code>transient</code> fields normally.
   * Writes the listeners using <code>writeListeners()</code>.
   * @param s The output stream
   */
  private void writeObject(final ObjectOutputStream s) throws IOException {
    // Write normal fields
    s.defaultWriteObject();

    // Write any serializable TextClickedListeners
    final TextClickedListener[] clickedCopy = clickListeners;
    for (int i = 0; i < clickedCopy.length; i++) {
      if (clickedCopy[i] instanceof Serializable) {
        s.writeObject(clickedCopy[i]);
      }
    }
    s.writeObject(null);

    // Write any serializable TextResizedListeners
    final TextResizedListener[] resizedCopy = resizedListeners;
    for (int i = 0; i < resizedCopy.length; i++) {
      if (resizedCopy[i] instanceof Serializable) {
        s.writeObject(resizedCopy[i]);
      }
    }
    s.writeObject(null);
  }

  /**
   * Contol the object's deserialization.  Reads
   * The non-<code>transient</code> fields normally.
   * Reads the listeners using <code>readListeners()</code>.
   * Initializes the fields having to do with the text
   * size by calling <code>calcNewWidth()</code>.
   * Relies on <code>addNotify()</code> to intialize
   * some of the fields.
   */
  private void readObject(ObjectInputStream s) throws IOException {
    try {
      // Read normal fields
      s.defaultReadObject();

      // Read any event listeners
      clickListeners = new TextClickedListener[0];
      TextClickedListener tcl = null;
      while ((tcl = (TextClickedListener) s.readObject()) != null) {
        addTextClickedListener(tcl);
      }

      resizedListeners = new TextResizedListener[0];
      TextResizedListener trl = null;
      while ((trl = (TextResizedListener) s.readObject()) != null) {
        addTextResizedListener(trl);
      }

      // Initialize fields used in repainting
      calcNewWidth();
    } catch (ClassNotFoundException e) {
      throw new IOException(
        "ClassNotFoundException ("
          + e.getMessage()
          + ") occured while reading a TextBean");
    }
  }

  //----------------------------------------------------------------------
  // Methods to implement Scrollable
  //----------------------------------------------------------------------

  /**
   * For horizontal scrolling, return the width of the viewport minus the
   * width of one charachter.  For vertical scrolling, return the height of
   * the viewport minus the height of one line.
   * @return The distance to scroll to the next "block" of text.
   */
  public int getScrollableBlockIncrement(
    final Rectangle vr,
    final int o,
    final int dir) {
    if (o == SwingConstants.HORIZONTAL) {
      return vr.width - charWidth;
    } else {
      return vr.height - (ascent + descent);
    }
  }

  /**
   * For horizontal scrolling, return the distance to the next fully
   * displayable column.  for vertical scrolling returns the distance
   * to the next fully displayable line.
   */
  public int getScrollableUnitIncrement(
    final Rectangle vr,
    final int o,
    final int dir) {
    if (o == SwingConstants.HORIZONTAL) {
      final int diff = vr.getLocation().x % charWidth;
      if (dir > 0) {
        return charWidth - diff;
      } else {
        return (diff == 0) ? charWidth : diff;
      }
    } else {
      final int firstLine = getLineUnderPoint(vr.getLocation());
      final int firstY = (ascent + descent) * firstLine;
      if (dir > 0) {
        if (firstLine == -1) {
          return 0;
        } else {
          return (ascent + descent) - (vr.y - firstY);
        }
      } else {
        if (firstY == vr.y) {
          return (firstLine == 0) ? 0 : (ascent + descent);
        } else {
          return vr.y - firstY;
        }
      }
    }
  }

  /**
   * Get the size of the viewport used to view the 
   * text.
   * @return return the preferred width and height set
   * by the minWidth and minHeight properties.
   */
  public Dimension getPreferredScrollableViewportSize() {
    return requestedSize;
  }

  /**
   * The TextBean would like the viewport to handle
   * vertical scrolling.
   * @return Always returns <code>false</code>.
   */
  public boolean getScrollableTracksViewportHeight() {
    // track only if the number of lines is more than the number of displayable
    // lines
    // System.out.println( "text.length = " + text.length );
    // System.out.println( "text height = " + getTextHeight() );
    return text.length <= getTextHeight();
  }

  /**
   * The TextBean would like the viewport to handle
   * horizontal scrolling.
   * @return Always returns <code>false</code>.
   */
  public boolean getScrollableTracksViewportWidth() {
    // track only if the text is wider than the view
    return maxLineWidth <= lineWidth;
  }

  //----------------------------------------------------------------------
  // Methods to implement Autoscroll
  //----------------------------------------------------------------------

  private final int autoScrollOffset = 5;
  private transient int top, bottom;

  /**
   * Return the insets for auto scrolling.  If the TextBean is not
   * in a <Code>JViewport</Code>, then the Insets are (0, 0, 0, 0).
   * Otherwise, the TextBean is in a <code>JScrollPane</code>, 
   * so we set the top and bottom insets based on the location of the
   * TextBean in the viewport and the height of the viewport,
   * respectively.
   * @return The insets for auto scrolling.
   */
  public Insets getAutoscrollInsets() {
    final Component parent = getParent();
    if (parent != null && parent instanceof JViewport) {
      final JViewport view = (JViewport) parent;
      final int topY = view.getViewPosition().y;
      top = topY + autoScrollOffset;
      bottom = topY + view.getHeight() - autoScrollOffset - 1;
      return new Insets(top, 0, getHeight() - bottom, 0);
    } else {
      return new Insets(0, 0, 0, 0);
    }
  }

  /**
   * If the TextBean's parent is a <Code>JViewport</Code> (i.e. it is
   * in a <code>JScrollPane</code>), then autoscroll up or down one
   * line as needed.
   * @param loc The position of the pointer.
   */
  public void autoscroll(final Point loc) {
    // System.out.println( "Autoscoll: " + loc );
    final int firstLine = getFirstLine();
    // System.out.println( "Autoscoll: firstLine = " + firstLine );

    // getAutoscrollInsets() is always called before this method so
    // top and bottom will be initialized properly.
    if ((loc.y < top)) {
      setFirstLine(firstLine - 1);
    } else if ((loc.y >= bottom)) {
      // System.out.println( "Autoscroll: setting first line to " + firstLine + 1 );
      setFirstLine(firstLine + 1);
    }
  }

  //----------------------------------------------------------------------
  //----------------------------------------------------------------------
  //----------------------------------------------------------------------
  //----------------------------------------------------------------------

  public static void main(String args[]) {
    JFrame f = new JFrame("test");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    String[] text1 =
      {
        "This is line 1",
        "This is line 2",
        "foobar",
        "",
        "11111111222222333322222211111111",
        ".........1.........2.........3.........4.........5",
        "12345678901234567890123456789012345678901234567890",
        "line 1 (first line)",
        "line 2",
        "line 3",
        "line 4",
        "line 5",
        "line 6",
        "line 7",
        "line 8",
        "line 9",
        "line 10",
        "line 11",
        "line 12",
        "line 13",
        "line 14",
        "line 15",
        "line 16",
        "line 17",
        "line 18",
        "line 19",
        "line 20 (last line)" };
    String[] text2 =
      {
        "line 1 (first line)",
        "line 2",
        "line 3",
        "line 4",
        "line 5",
        "line 6",
        "line 7",
        "line 8",
        "line 9",
        "line 10",
        "line 11",
        "line 12",
        "line 13",
        "line 14",
        "line 15",
        "line 16",
        "line 17",
        "line 18",
        "line 19",
        "line 20 (last line)" };
    TextBean b1 = new TextBean(text1, 200, 200, "Courier", 12);
    TextBean b2 = new TextBean(text2);

    b1.addTextResizedListener(new MyAdapter());
    b2.addTextResizedListener(new MyAdapter());
    b2.addTextClickedListener(new MyAdapter());

    f.getContentPane().add(BorderLayout.NORTH, new JScrollPane(b1));
    f.getContentPane().add(BorderLayout.CENTER, new JScrollPane(b2));
    f.pack();
    f.setVisible(true);

    //    TextStyle one = new TextStyle( Color.red, Color.white );
    TextStyle two =
      new TextStyle(true, true, false, false, true, Color.blue, false, null);
    //    TextStyle three = new TextStyle( false, false, true, true, false, null,
    //                                     true, Color.green );

    TextStyle alpha = new TextStyle(Color.black, Color.red);
    TextStyle beta = new TextStyle(Color.black, Color.green);
    TextStyle gamma = new TextStyle(Color.black, Color.blue);

    /*
    b1.applyStyle( one, 0, 1, 1, 16 );
    b1.applyStyle( two, 0, 0, 0, 6 );
    b1.applyStyle( three, 2, 2, 2, 6 );
    */
    final TextRegion tr =
      new TextRegion(new TextCoord(0, 0), new TextCoord(2, 4), 2, 9);
    b1.applyStyle(two, tr);
    b1.addGraphic(0, 4, new InsertableCircle());
    b1.addGraphic(2, 5, new InsertableCircle());
    b1.addGraphic(0, 1, new LeftIBeam(Color.red));
    b1.addGraphic(0, 1, new RightIBeam(Color.green));
    b1.applyStyle(alpha, 4, 0, 4, 32);
    b1.applyStyle(beta, 4, 8, 4, 24);
    b1.applyStyle(gamma, 4, 14, 4, 18);
    b1.repaint();

    (new ScrollThread(b2, 20)).start();
  }
}

class ScrollThread extends Thread {
  private TextBean bean;
  private int count, max;

  public ScrollThread(TextBean b, int m) {
    super("Scroll Thread");
    bean = b;
    count = -10;
    max = m;
  }

  public void run() {
    while (true) {
      SwingUtilities.invokeLater(new MoveLine(count));
      if (++count == max + 5)
        count = -10;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }

  private class MoveLine implements Runnable {
    private final int line;

    public MoveLine(final int line) {
      this.line = line;
    }

    public void run() {
      bean.setFirstLine(line);
    }
  }
}

class MyAdapter extends TextResizedAdapter implements TextClickedListener {
  public void textClicked(TextClickedEvent e) {
    System.out.println(
      "Clicked "
        + e.getNumClicks()
        + " times on "
        + e.getTextCoord()
        + " <<"
        + e.getPoint()
        + ">> "
        + " in "
        + e.getSource());
  }

  public void textResized(TextResizedEvent e) {
    System.out.println(e.getSource() + " now has " + e.getNewWidth() + " cols");
  }
}

final class StyleRange implements Serializable {
  private final TextStyle style;
  private final TextCoord begin;
  private final TextCoord end;

  public StyleRange(final TextStyle s, final TextCoord b, final TextCoord e) {
    style = s;
    begin = b;
    end = e;
  }

  public final TextStyle getStyle() {
    return style;
  }
  public final TextCoord getBegin() {
    return begin;
  }
  public final TextCoord getEnd() {
    return end;
  }

  public final boolean inStyle(final TextCoord c) {
    return c.inRange(begin, end);
  }

  public final String toString() {
    return "<" + style + ", " + begin + ", " + end + ">";
  }
}

interface StyleManager {
  public abstract void resetStyles(String[] lines, TextStyle ts);
  public abstract StyleRange styleOfCoord(TextCoord c);
  public abstract Enumeration getStyles();
  public abstract void applyStyle(
    TextStyle applyMe,
    TextCoord begin,
    TextCoord end);
  public abstract TextStyle getStyleAt(TextCoord c);
  public abstract String toString();
}

final class StylesVector implements StyleManager, Serializable {
  private final Vector list;

  public String toString() {
    return list.toString();
  }

  public StylesVector(final String[] lines, final TextStyle ts) {
    list = new Vector(10);
    final TextCoord begin = new TextCoord(0, 0),
      end = new TextCoord(lines.length - 1, lines[lines.length - 1].length());
    list.addElement(new StyleRange(ts, begin, end));
  }

  public final void resetStyles(final String[] lines, final TextStyle ts) {
    list.setSize(0);
    final TextCoord begin = new TextCoord(0, 0),
      // Add one to lenght of line so that things work with zero length last lines
  end = new TextCoord(lines.length - 1, lines[lines.length - 1].length() + 1);
    list.addElement(new StyleRange(ts, begin, end));
  }

  public final StyleRange styleOfCoord(final TextCoord c) {
    for (int i = 0; i < list.size(); i++) {
      StyleRange sr = (StyleRange) list.elementAt(i);
      if (sr.inStyle(c))
        return sr;
    }
    return null;
  }

  public final Enumeration getStyles() {
    return list.elements();
  }

  public TextStyle getStyleAt(final TextCoord where) {
    int left = 0, right = list.size() - 1;
    int styleNum = (left + right) >> 1;
    StyleRange sr = (StyleRange) list.elementAt(styleNum);

    boolean mvLeft;
    while ((mvLeft = where.before(sr.getBegin()))
      || sr.getEnd().beforeEq(where)) {
      if (mvLeft)
        right = styleNum;
      else
        left = styleNum + 1;
      styleNum = (left + right) >> 1;
      sr = (StyleRange) list.elementAt(styleNum);
    }
    return sr.getStyle();
  }

  public final void applyStyle(
    final TextStyle applyMe,
    TextCoord begin,
    TextCoord end) {
    // Don't do anything if the region is empty or backwards
    if (begin.afterEq(end))
      return;

    /* Old linear search ...
    int styleNum = 0; // always holds the postion of sr in list
    StyleRange sr = null;
    
    for( ; styleNum < list.size(); styleNum++ ) {
      sr = (StyleRange)list.elementAt( styleNum );
      if( sr.inStyle( begin ) ) break;
    }
    */

    // New binary search
    int left = 0, right = list.size() - 1;
    int styleNum = (left + right) >> 1;
    StyleRange sr = (StyleRange) list.elementAt(styleNum);

    // Audit of inlining...
    //    while( !sr.inStyle( begin ) ) { --->
    //    while( !( begin.inRange( sr.getBegin(), sr.getEnd() ) ) {
    // public boolean inRange( final TextCoord first, final TextCoord last ) {
    //   return !this.before( first ) && !last.before( this ); } --->
    //    while( !( !begin.before( sr.getBegin() ) && 
    //              !sr.getEnd().before( begin ) ) ) { ---->
    // while( begin.before( sr.getBegin() ) || sr.getEnd().before( begin ) ) {

    boolean mvLeft;
    while ((mvLeft = begin.before(sr.getBegin()))
      || sr.getEnd().beforeEq(begin)) {
      if (mvLeft)
        right = styleNum;
      else
        left = styleNum + 1;
      styleNum = (left + right) >> 1;
      sr = (StyleRange) list.elementAt(styleNum);
    }

    // If we're starting in the middle of style, need to split the style
    if (sr.getBegin().before(begin)) {
      final StyleRange temp =
        new StyleRange(sr.getStyle(), sr.getBegin(), begin);
      // Insert the front half before the current style
      list.insertElementAt(temp, styleNum++);

      // Current style is now the back half of the split
      sr = new StyleRange(sr.getStyle(), begin, sr.getEnd());
    }

    while (sr.getBegin().before(end)) {
      final TextStyle newStyle = sr.getStyle().mergeWith(applyMe);

      // If the current style extends beyond the end of the new style
      // we need to split it, and apply the style to the front half
      if (end.before(sr.getEnd())) {
        final StyleRange front = new StyleRange(newStyle, sr.getBegin(), end);
        list.setElementAt(front, styleNum++);
        sr = new StyleRange(sr.getStyle(), end, sr.getEnd());
        list.insertElementAt(sr, styleNum);
      } else {
        sr = new StyleRange(newStyle, sr.getBegin(), sr.getEnd());
        list.setElementAt(sr, styleNum);
        if (++styleNum < list.size())
          sr = (StyleRange) list.elementAt(styleNum);
        else
          break;
      }
    }
  }
}

class GraphicsArray implements Serializable {
  private TreeMap graphicsMap;

  public GraphicsArray() {
    graphicsMap = new TreeMap();
  }

  public void clear() {
    graphicsMap.clear();
  }

  public void insert(final TextCoord tc, final InsertableGraphic g) {
    if (graphicsMap.containsKey(tc)) {
      final Vector v = (Vector) graphicsMap.get(tc);
      v.addElement(g);
    } else {
      final Vector v = new Vector();
      v.addElement(g);
      graphicsMap.put(tc, v);
    }
  }

  public void remove(final TextCoord tc) {
    graphicsMap.remove(tc);
  }

  public void remove(final TextCoord tc, final InsertableGraphic g) {
    if (graphicsMap.containsKey(tc)) {
      final Vector v = (Vector) graphicsMap.get(tc);
      v.removeElement(g);
    }
  }

  public Iterator getTextCoords(final int first, final int last) {
    final SortedMap tail = graphicsMap.tailMap(new TextCoord(first, 0));
    final SortedMap head = tail.headMap(new TextCoord(last + 1, 0));
    return head.keySet().iterator();
  }

  public Iterator getGraphicsAt(final TextCoord tc) {
    return ((Vector) graphicsMap.get(tc)).iterator();
  }
}
