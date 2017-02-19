/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/display/TextRegion.java,v 1.1 2006/03/21 23:20:56 dig Exp $ */
package fluid.display;

import java.io.Serializable;

/** A region of text has a start and stop coordindates.
 * It also has a min and max column for each line other than
 * the first and last respectively.
 */

/* 4 November 1998
 * Made Serializable 
 */

public class TextRegion implements Serializable {
  private final TextCoord begin, end;
  private final int min, max;

  private static final int DEFAULTMAX = Integer.MAX_VALUE;

  /** Create a text region with default beginning and ending columns.
   * @param begin The first coordinate of the region
   * @param end The last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   */
  public TextRegion(TextCoord begin, TextCoord end) {
    min = 0;
    max = DEFAULTMAX;
    this.begin = begin;
    this.end = end;
  }

  /** Create a text region with default beginning and ending columns.
   * @param bl The line of first coordinate of the region
   * @param bc The character of first coordinate of the region
   * @param el The line of last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   * @param ec The line of last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   */
  public TextRegion( int bl, int bc, int el, int ec ) {
    min = 0;
    max = DEFAULTMAX;
    this.begin = new TextCoord( bl, bc );
    this.end = new TextCoord( el, ec );
  }

  /** Create a text region within the window.
   * Don't highlight outside of the (min,max) window.
   * @param begin The first coordinate of the region
   * @param end The last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   * @param min On lines other than the first line, begin highlighting at
   * this character
   * @param max on lines other than the last line, highlight up to the
   * character before this one
   */
  public TextRegion(TextCoord begin, TextCoord end, int min, int max) {
    this.min = min;
    this.max = max;
    this.begin = begin;
    this.end = end;
  }

  /** Create a text region within the window.
   * Don't highlight outside of the (min,max) window.
   * @param bl The line of first coordinate of the region
   * @param bc The character of first coordinate of the region
   * @param el The line of last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   * @param ec The line of last coordinate of the region (highlighting ends at
   * the character before this coordinate)
   * @param min On lines other than the first line, begin highlighting at
   * this character
   * @param max on lines other than the last line, highlight up to the
   * character before this one
   */
  public TextRegion( int bl, int bc, int el, int ec, int min, int max ) {
    this.begin = new TextCoord( bl, bc );
    this.end = new TextCoord( el, ec );
    this.min = min;
    this.max = max;
  }

  public String toString() {
    return "[" + begin.getLine() + ":" + begin.getChar() + "," +
                 end.getLine() + ":" + end.getChar() + ")|<" + 
           min + "," + max + ">";
  }

  /** Get the start of the region.
   * @return The first coordinate of the region
   */
  public TextCoord getBegin() { return begin; }

  /** Get the end of the region.
   * @return The last coordinate of the region.
   */
  public TextCoord getEnd() { return end; }

  /** Get the min column of the region.
   * @return The min column of the region
   */
  public int getMin() { return min; }

  /**
   * Get the max column of the region.
   * @return The max column of the regin
   */
  public int getMax() { return max; }


  /** Does a coord fall in this region?
   * @param c The coordinate to test
   * @return <tt>true</tt> iff the start of the region is before <tt>c</tt>
   * and the end of the region is after <tt>c</tt>
   */
  public boolean inRegion( TextCoord c ) { return c.inRange( begin, end ); }

  /** Determine if a region is inside of this one.
   * @param r The region to test
   * @return <tt>true</tt> iff region <tt>r</tt> is completely inside this one
   */
  public boolean contains( TextRegion r ) {
    return begin.beforeEq( r.begin ) && end.afterEq( r.end );
  }

  /** Determine if a region contains this one.
   * @param r the region to test
   * @return <tt>true</tt> iff this region is completely inside of
   * region <tt>r</tt> 
   */
  public boolean insideOf( TextRegion r ) {
    return r.begin.beforeEq( begin ) && r.end.afterEq( end );
  }

  /** Determine if two regions overlap.
   * @param r The region to test
   * @return <tt>true</tt> iff this region and <tt>r</tt> overlap
   */
  public boolean overlap( TextRegion r ) {
    return r.begin.inRange( begin, end ) || r.end.inRange( begin, end );
  }
}
