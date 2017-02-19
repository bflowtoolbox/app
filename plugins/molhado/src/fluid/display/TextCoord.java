package fluid.display;

import java.io.Serializable;

/**
  * Class that represents the line and column coordinate of a character. 
  */

/* 98 Jul 23 -- Added before( int, int ), after( int, int ), 
 * equals( int, int ), and inRange( int, int, int, int )
 * and added the *Eq( ) and family of methods
 */

/* 4 November 1998
 * Made Serializable
 */

/* 8 January 1999
 * Made equals() correct by replacing use of instanceof with
 * a comparision of the objects .class fields.
 * Added hashCode() method.
 */

public class TextCoord
implements Serializable, Comparable
{
  private final int line;
  private final int offset;

  /**
   * Create a new TextCoord.
   * @param l The line number.  The first line is line 0.
   * @param c The character on the line.  The first character is 0.
   */
  public TextCoord( final int l, final int c )
  {
    line = l;
    offset = c;
  }

  /**
   * Get the hashcode of the object.
   * @return The hashcode, which is
   * <code>(getLine() << 16) | (getChar() & 0xFFFF)</code>.
   */
  public int hashCode()
  {
    return (line << 16) | (offset & 0x0000FFFF);
  }

  /**
   * Get the line.
   * @return the line component.
   */
  public int getLine() { return line; }

  /**
   * Get the character.
   * @return the character component.
   */
  public int getChar() { return offset; }

  /**
   * Determine if the position is between two other positions.
   * @param first Must be at or after this position.
   * @param last Must be before or at this position.
   * @return <code>true</code> if between <code>first</code> and <code>last</code>,
   * inclusive
   */
  public boolean inRangeIncl( final TextCoord first, final TextCoord last )
  {
    return this.afterEq( first ) && this.beforeEq( last );
  }

  /**
   * Determine if the position is between two other positions.
   * @param fl Line component of the first position
   * @param fc Character component of the first position
   * @param ll Line component of the last position
   * @param lc Character component of the last position
   * @return <code>true</code> if between <code>(fl, fc)</code> and <code>(ll, lc)</code>,
   * inclusive
   */
  public boolean inRangeIncl( final int fl, final int fc, final int ll, final int lc )
  {
    return this.afterEq( fl, fc ) && this.beforeEq( ll, lc );
  }

  /**
   * Determine if the position is between two other positions.
   * @param first Must be at or after this position.
   * @param last Must be before this position.
   * @return <code>true</code> if <code>first</code> <= <code>this</code> < <code>last</code>
   */
  public boolean inRange( final TextCoord first, final TextCoord last )
  {
    return this.afterEq( first ) && this.before( last );
  }

  /**
   * Determine if the position is between two other positions.
   * @param fl Line component of the first position
   * @param fc Character component of the first position
   * @param ll Line component of the last position
   * @param lc Character component of the last position
   * @return <code>true</code> if <code>(fl, fc)</code> <= <code>this</code> <
   *  <code>(ll, lc)</code>,
   */
  public boolean inRange( final int fl, final int fc, final int ll, final int lc )
  {
    return this.afterEq( fl, fc ) && this.before( ll, lc );
  }

  /**
   * Is this position before another one?
   * @param c coordinate to test against.
   * @return <code>true</code> iff <code>c</code> comes after this coordinate
   */
  public boolean before( final TextCoord c )
  {
    return (line < c.line) || ((line == c.line) && (offset < c.offset));
  }

  /**
   * Is this position before another one?
   * @param l The line component of the coordinate to test against
   * @param c the character component of the coordinate to test against
   * @return <code>true</code> iff <code>(1, c)</code> comes after this coordinate
   */
  public boolean before( final int l, final int c )
  {
    return (line < l) || ((line == l) && (offset < c));
  }
  
  /**
   * Is this position after another one?
   * @param c coordinate to test against.
   * @return <code>true</code> iff <code>c</code> comes before this coordinate
   */
  public boolean after( final TextCoord c )
  {
    return (c.line < line) || ((line == c.line) && (c.offset < offset));
  }

  /**
   * Is this position after another one?
   * @param l The line component of the coordinate to test against
   * @param c the character component of the coordinate to test against
   * @return <code>true</code> iff <code>(l, c)</code> comes before this coordinate
   */
  public boolean after( final int l, final int c )
  {
    return (l < line) || ((line == l) && (c < offset));
  }

  /**
   * Is this position before or equal to another one?
   * @param c coordinate to test against.
   * @return <code>true</code> iff <code>c</code> comes after this coordinate or
   * it is equal to it
   */
  public boolean beforeEq( final TextCoord c )
  {
    return (line < c.line) || ((line == c.line) && (offset <= c.offset));
  }

  /**
   * Is this position before or equal to another one?
   * @param l The line component of the coordinate to test against
   * @param c the character component of the coordinate to test against
   * @return <code>true</code> iff <code>(1, c)</code> comes after this coordinate or
   * it is equal to it
   */
  public boolean beforeEq( final int l, final int c )
  {
    return (line < l) || ((line == l) && (offset <= c));
  }
  
  /**
   * Is this position after or equal to another one?
   * @param c coordinate to test against.
   * @return <code>true</code> iff <code>c</code> comes before this coordinate or
   * is equal to it
   */
  public boolean afterEq( final TextCoord c )
  {
    return (c.line < line) || ((line == c.line) && (c.offset <= offset));
  }

  /**
   * Is this position after or equal to another one?
   * @param l The line component of the coordinate to test against
   * @param c the character component of the coordinate to test against
   * @return <code>true</code> iff <code>(l, c)</code> comes before this coordinate
   * or is equal to it
   */
  public boolean afterEq( final int l, final int c )
  {
    return (l < line) || ((line == l) && (c <= offset));
  }

  /**
   * Is this position equal to another one?
   * @param o Object to compare to
   * @return <code>true</code> iff <code>o</code> is a TextCoord and they refer
   * to the same position.
   */
  public boolean equals( final Object o )
  {
    if( o != null && o.getClass().equals( this.getClass() ) )
    {
      final TextCoord tc = (TextCoord)o;
      return (tc.line == line) && (tc.offset == offset);
    }
    return false;
  }

  /**
   * Compare two TextCoord objects.  Coordinates are ordered first by line,
   * then by character, e.g. (2,3), (4,5), (1,0), (2,2), (4,3) would have 
   * the ordering (1,0) < (2,2) < (2,3) < (4,3) < (4,5).  This ordering
   * <em>is consistent</em> with equals.
   * @param o Object to compare with the receiver.
   * @return <code>-1</code> if the receiver comes before <code>o</code>,
   * <code>0</code> if the receiver and <code>o</code> are equal, or
   * <code>1</code> if the receiver comes after <code>o</code>.
   * @exception ClassCastException Thrown if <code>o</code> is not
   * a <code>TextCoord</code>.
   */
  public int compareTo( final Object o )
  {
    try
    {
      final TextCoord tc = (TextCoord)o;
      if( (line < tc.line) || ((line == tc.line) && (offset < tc.offset)) ) {
        return -1;
      } else if( (line == tc.line) && (offset == tc.offset) ) {
        return 0;
      } else {
        return 1;
      }
    }
    catch( ClassCastException e )
    {
      throw new ClassCastException(
        "fluid.display.TextCoord can only be compared against another TextCoord" );
    }
  }

  /**
   * Is this position equal to another one?
   * @param l The line component of the coordinate to test against
   * @param c the character component of the coordinate to test against
   * @return <code>true</code> iff <code>(l, c)</code> and this object refer
   * to the same position.
   */
  public boolean equals( final int l, final int c )
  {
    return (line == l) && (offset == c);
  }

  /**
    * Convert to a String representation
    * @return A string of the form
    * "<code>(</code><i>l</i><code>, </code><i>c</i><code>)</code>", where <i>l</i> and
    * <i>c</i> are String representations of the line and character 
    * components, respectively
    */
  public String toString()
  {
    return "(" + line + ", " + offset + ")";
  }
}

