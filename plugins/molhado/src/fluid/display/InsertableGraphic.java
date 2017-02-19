package fluid.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

/* 98 Oct 6
 * drawGraphic() is no longer passed the background color
 */

/** This interface is implemented by classes that draw small character
 * size graphics in the TextBean.  The drawing routine is passed the
 * foreground color and current font, which are
 * derived from the style active at the graphics position.  The graphic
 * should attempt to be consistent with the style.  The graphic's background
 * is filled with the background color prior to the drawing method being
 * invoked.  The graphics context passed to the drawing method has (0,0)
 * as its origin, and is clipped so that the graphic can not draw outside
 * of its cell.  The size of the drawing area is passed to the drawing
 * method.
 * @author Aaron Greenhouse
 * @see TextBean
 */
public interface InsertableGraphic extends Serializable {
  /** The drawing method described above.
   * @param g The graphics context to use.  The top left corner of the 
   * cell is (0,0), and the clipping rectangle is set to prevent the 
   * graphic from drawing outside of its cell.
   * @param fore The foreground color, derived from the appropriate
   * TextStyle
   * @param f The font, derived from the appropriate
   * TextStyle
   * @param d The dimensions of the cell
   */
  public void drawGraphic( Graphics g, Color fore, Font f, Dimension d );

  /** Should the TextBean clear the character under the graphic before
   * called drawGraphic()
   */
  public boolean clearCharacter();
}
