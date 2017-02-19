package fluid.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A small circle that can inserted into the text.
 */
public class InsertableCircle implements InsertableGraphic
{
  /** Create a new circle. */
  public InsertableCircle()
  {
    super();
  }

  public void drawGraphic( final Graphics g, final Color fore,
                           final Font f, final Dimension dim )
  {
    final int size = Math.min( dim.width-1, dim.height-1 );
    final int x = ((dim.width - size) >> 1);
    final int y = ((dim.height - size) >> 1);
    g.setColor( fore );
    g.fillOval( x, y, size, size );
  }

  /**
   * Always returns true, meaning the circle overwrites the character at the 
   * same location.
   * @return <tt>false</tt>
   */
  public final boolean clearCharacter() { return true; }
}
