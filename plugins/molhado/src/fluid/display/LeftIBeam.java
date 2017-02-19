package fluid.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A graphic that is supposed to represent a bar between characters.  When placed
 * at position (line, char), it is between the characters at position (line, char-1)
 * and (line, char).
 */

public class LeftIBeam implements InsertableGraphic
{
  /** The color of the bar */
  private final Color color;
  /** The width of the bar in pixels */
  private final int width;

  /**
   * Create a new LeftIBeam.
   * @param c The color of the LeftIBeam
   * @param w The width of the LeftIBeam in pixels
   */
  public LeftIBeam( final Color c, final int w )
  {
    super();
    color = c;
    width = w;
  }

  /**
   * Create a new LeftIBeam with a width of 1 pixel.
   * @param c The color of the LeftIBeam
   */
  public LeftIBeam( final Color c )
  {
    super();
    color = c;
    width = 1;
  }

  /**
   * Create a new LeftIBeam with a width of 1 pixel that uses the foreground color of
   * TextStyle in force at the LeftIBeam's location.
   */
  public LeftIBeam()
  {
    color = null;
    width = 1;
  }

  /**
   * Draw the LeftIBeam.
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
    for( int x = 0; x < width; x++ ) 
      g.drawLine( x, 0, x, dim.height-1 );
  }

  /**
   * Always returns false, meaning that the character at the location of the LeftIBeam 
   * shows through.
   * @return <TT>false</tt>
   */
  public final boolean clearCharacter() { return false; }
}
