package fluid.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A graphic that is supposed to represent a bar between characters.  When placed
 * at position (line, char), it is between the characters at position (line, char)
 * and (line, char+1).
 */

public class RightIBeam implements InsertableGraphic
{
  /** The color of the bar */
  private final Color color;
  /** The width of the bar in pixels */
  private final int width;

  /**
   * Create a new RightIBeam.
   * @param c The color of the RightIBeam
   * @param w The width of the RightIBeam in pixels
   */
  public RightIBeam( final Color c, final int w )
  {
    super();
    color = c;
    width = w;
  }

  /**
   * Create a new RightIBeam with a width of 1 pixel.
   * @param c The color of the RightIBeam
   */
  public RightIBeam( final Color c )
  {
    super();
    color = c;
    width = 1;
  }

  /**
   * Create a new RightIBeam with a width of 1 pixel that uses the foreground color of
   * TextStyle in force at the RightIBeam's location.
   */
  public RightIBeam()
  {
    color = null;
    width = 1;
  }

  /**
   * Draw the RightIBeam.
   * @param g The graphics context to use.  The top left corner of the 
   * cell is (0,0), and the clipping rectangle is set to prevent the 
   * graphic from drawing outside of its cell.
   * @param fore The foreground color, derived from the appropriate TextStyle
   * @param f The font, derived from the appropriate TextStyle
   * @param dim The dimensions of the cell
   */
  public void drawGraphic( final Graphics g, final Color fore,
                           final Font f, final Dimension dim )
  {
    final Color barColor = (color != null) ? color : fore;
    g.setColor( barColor );
    for( int i = 1; i <= width; i++ ) 
      g.drawLine( (dim.width-i), 0, (dim.width-i), dim.height-1 );
  }

  /**
   * Always returns false, meaning that the character at the location of the RightIBeam 
   * shows through.
   * @return <TT>false</tt>
   */
  public final boolean clearCharacter() { return false; }
}
