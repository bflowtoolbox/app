package fluid.display;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

import fluid.util.AbstractRemovelessIterator;

/* 
 * November 18, 1997 -- Changed so that font and point size is not specified
 *   because life is simpler if we only use a fixed-size proportional font.
 */

/*
 * January 16, 1998 -- Fixed hashing of fonts.
 */

/*
 * July 7, 1998 -- Added "final" where appropriate
 */

/* Changed 98 Aug 28
 * Moved AttributeNotSetException to be an inner class
 */

 /* Changed 98 Oct 6
  * Modified to support having multiple background colors.
  * background field is now a Vector of colors.  A TextStyle can only
  * be created with a single background color.  Additional Colors are
  * picked up as styles are merged through overlapping.
  */

/* 4 November 1998
 * Made Serializable 
 */

/* 8 January 1999
 * Made equals() correct by replacing use of instanceof with
 * a comparision of the objects .class fields.
 * Added hashCode() method.
 */

/* 12 July 1999
 * Changed background color to have a primary color, and an 
 * ordered list of secondary colors
 */

public class TextStyle
implements Serializable
{
  private static Hashtable fonts = new Hashtable( 20 );
  
  private static final int B_BIT = 0x4;
  private static final int I_BIT = 0x4000;

  // Would like to make all of these final, but the compiler is too stupid
  private boolean boldSet;
  private boolean italicSet;
  private boolean colorSet;
  private boolean backgroundSet;

  private boolean bold;
  private boolean italic;
  private Color color;
  private Color primaryBack;
  private Color[] secondaryBack;

  // Inner class
  public class AttributeNotSetException
  extends Exception
  {
    public AttributeNotSetException() { super();  }    
    public AttributeNotSetException( String s ) { super( s ); }
  }


  /**
   * Get a TextStyle with no attributes set.  Useful for use as the
   * standard style, so that text is default unless otherwise specified.
   *
   * @return A style such that all <TT>isXSet()</TT> methods return
   * <TT>false</TT>.
   */
  static public TextStyle noStyle()
  {
    return new TextStyle();
  }

  
  //----------------------------------------------------------------------


  /**
   * Create a new TextStyle with all attributes set.
   * Returns A new style where all the <TT>isXSet()</TT> methods return
   * <TT>true</TT> and the style's attributes are set using the given
   * parameters.
   *
   * @param b The style's bold flag; <TT>true</TT> if the text should be bold
   * @param i The style's italic flag; <TT>true</TT> if the text should be
   * italic
   * @param c The style's text color
   * @param back The style's background color (useful for highlighting)
   */
  public TextStyle( final boolean bsf, final boolean b,
                    final boolean isf, final boolean i,
                    final boolean csf, final Color c,
                    final boolean backsf, final Color back )
  {
    boldSet = bsf;
    italicSet = isf;
    colorSet = csf;
    backgroundSet = backsf;
    bold = b;
    italic = i;
    color = c;
    primaryBack = back;
    secondaryBack = null;
  }

  public TextStyle( final boolean b, final boolean i,
	                  final Color c, final Color back )
	{
    this( true, b, true, i, true, c, true, back );
  }

  /**
   * Create a new TextStyle with no attributes set.
   * A style such that all <TT>isXSet()</TT> methods return <TT>false</TT>.
   */
  public TextStyle()
  {
    this( false, false, false, false, false, null, false, null );
  }

  /**
   * Create a new TextStyle with a given text and background color.
   * @param f The text color.
   * @param b The background color.
   */
  public TextStyle( final Color f, final Color b )
  {
    this( false, false, false, false, true, f, true, b );
  }

  /**
   * Create a new TextStyle by merging two TextStyles.
   * @see #mergeWith
   */
  private TextStyle( final TextStyle oldStyle, final TextStyle newStyle )
  {
    if( newStyle.boldSet )
    {
      boldSet = true;
      bold = newStyle.bold;
    }
    else
    {
      boldSet = oldStyle.boldSet;
      bold = oldStyle.bold;
    }

    if( newStyle.italicSet )
    {
      italicSet = true;
      italic = newStyle.italic;
    }
    else
    {
      italicSet = oldStyle.italicSet;
      italic = oldStyle.italic;
    }

    if( newStyle.colorSet )
    {
      colorSet = true;
      color = newStyle.color;
    }
    else
    {
      colorSet = oldStyle.colorSet;
      color = oldStyle.color;
    }
		
    if( !oldStyle.backgroundSet )
    {
      if( newStyle.backgroundSet )
      {
        backgroundSet = true;
        primaryBack = newStyle.primaryBack;
        secondaryBack = newStyle.secondaryBack;
      }
      else
      {
        backgroundSet = false;
        primaryBack = null;
        secondaryBack = null;
      }
    }
    else
    {
      if( !newStyle.backgroundSet )
      {
        backgroundSet = true;
        primaryBack = oldStyle.primaryBack;
        secondaryBack = oldStyle.secondaryBack;
      }
      else
      {
        final int sb1 = oldStyle.getNumSecondaryBackgrounds();
        final int sb2 = newStyle.getNumSecondaryBackgrounds();

        backgroundSet = true;
        primaryBack = newStyle.primaryBack;
        secondaryBack = new Color[1 + sb1 + sb2];

        secondaryBack[0] = oldStyle.primaryBack;
        if( sb2 != 0 ) {
          System.arraycopy( newStyle.secondaryBack, 0, secondaryBack, 1, sb2 );
        }
        if( sb1 != 0 ) {
          System.arraycopy( oldStyle.secondaryBack, 0, secondaryBack, 1 + sb2, sb1 );
        }
      }
    }
  }

  
  //----------------------------------------------------------------------


  /**
   * Query if the bold attribute is set
   * @return <TT>True</TT> if the bold attribute is set.
   */
  public boolean isBoldSet() { return boldSet; }

  /**
   * Query if the italic attribute is set
   * @return <TT>True</TT> if the italic attribute is set.
   */
  public boolean isItalicSet() { return italicSet; }

  /**
   * Query if the text color attribute is set
   * @return <TT>True</TT> if the text color attribute is set.
   */
  public boolean isColorSet() { return colorSet; }

  /**
   * Query if the background color attribute is set
   * @return <TT>True</TT> if the background color attribute is set.
   */
  public boolean isBackgroundSet() { return backgroundSet; }


  //----------------------------------------------------------------------


  /**
   * Get the value of the bold attribute.  
   * @return The value of the bold attribute.
   * @exception AttributeNotSetException Thrown if the bold set flag is
   * <tt>false</TT>.
   */
  public boolean getBold() throws AttributeNotSetException
  {
    if( boldSet )
      return bold;
    else
      throw new AttributeNotSetException( "The bold attribute is not set" );
  }

  /**
   * Get the value of the italic attribute.  
   * @return The value of the italic attribute.
   * @exception AttributeNotSetException Thrown if the italic set flag is
   * <tt>false</TT>.
   */
  public boolean getItalic() throws AttributeNotSetException
  { 
    if( italicSet )
      return italic;
    else
      throw new AttributeNotSetException( "The italic attribute is not set" );
  }

  /**
   * Get the value of the text color attribute.  
   * @return The value of the text color attribute.
   * @exception AttributeNotSetException Thrown if the text color set flag is
   * <tt>false</TT>.
   */
  public Color getColor() throws AttributeNotSetException
  { 
    if( colorSet )
      return color;
    else
      throw new AttributeNotSetException( "The text color attribute is not set" );
  }

  /**
   * Get the value of the background color attribute.  
   * @return An iterator over the background colors.  The first color
   * is the primary background color.  The following colors are an
   * ordered list of secondary colors.
   * @exception AttributeNotSetException Thrown if the background color set
   * flag is <tt>false</TT>.
   */
  public Iterator getBackground() throws AttributeNotSetException
  {
    if( backgroundSet )
      return new BackgroundIterator( primaryBack, secondaryBack );
    else
      throw new AttributeNotSetException( "The background color attribute is not set" );
  }

  /**
   * Get the primary background color.  
   * @exception AttributeNotSetException Thrown if the background color set
   * flag is <tt>false</TT>.
   */
  public Color getPrimaryBackground() throws AttributeNotSetException
  {
    if( backgroundSet )
      return primaryBack;
    else
      throw new AttributeNotSetException( "The background color attribute is not set" );
  }

  /**
   * Get the secondary background colors. 
   * @return An iterator over the secondary background colors.
   * @exception AttributeNotSetException Thrown if the background color set
   * flag is <tt>false</TT>.
   */
  public Iterator getSecondaryBackground() throws AttributeNotSetException
  {
    if( backgroundSet )
      return new BackgroundIterator( secondaryBack );
    else
      throw new AttributeNotSetException( "The background color attribute is not set" );
  }

  /**
   * Get the number of secondary background colors.
   */
  public int getNumSecondaryBackgrounds()
  {
    return (secondaryBack == null) ? 0 : secondaryBack.length;
  }


  //----------------------------------------------------------------------


  /**
   * Get the bold attribute under a default style.
   * @param defaultStyle The default style to use.
   * @return If the bold attribute is set, then returns the bold attribute.
   * Otherwise the bold attribute from <tt>defaultStyle</tt> is returned.
   */
  public boolean getBold( final TextStyle defaultStyle )
  {
    return boldSet ? bold : defaultStyle.bold;
  }

  /**
   * Get the italic attribute under a default style.
   * @param defaultStyle The default style to use.
   * @return If the italic attribute is set, then returns the italic attribute.
   * Otherwise the italic attribute from <tt>defaultStyle</tt> is returned.
   */
  public boolean getItalic( final TextStyle defaultStyle )
  {
    return italicSet ? italic : defaultStyle.italic;
  }

  /**
   * Get the text color under a default style.
   * @param defaultStyle The default style to use.
   * @return If the text color attribute is set, then returns the text color.
   * Otherwise the text color from <tt>defaultStyle</tt> is returned.
   */
  public Color getColor( final TextStyle defaultStyle )
  {
    return colorSet ? color : defaultStyle.color;
  }

  /**
   * Get the background colors under a default style.
   * @param defaultStyle The default style to use.
   * @return An iterator over the background colors.  The first color
   * is the primary background color.  The following colors are an
   * ordered list of secondary colors.
   */
  public Iterator getBackground( final TextStyle defaultStyle )
  {
    if( backgroundSet ) {
      return new BackgroundIterator( primaryBack, secondaryBack );
    } else {
      return new BackgroundIterator( defaultStyle.primaryBack, defaultStyle.secondaryBack );
    }
  }

  /**
   * Get the primary background color under a default style.
   * @param defaultStyle The default style to use.
   */
  public Color getPrimaryBackground( final TextStyle defaultStyle )
  {
    if( backgroundSet )
      return primaryBack;
    else
      return defaultStyle.primaryBack;
  }

  /**
   * Get the secondary background colors under a default style.
   * @param defaultStyle The default style to use.
   */
  public Iterator getSecondaryBackground( final TextStyle defaultStyle )
  {
    if( backgroundSet )
      return new BackgroundIterator( secondaryBack );
    else
      return new BackgroundIterator( defaultStyle.secondaryBack );
  }

  /**
   * Get the number of secondary background colors under a default style.
   * @param defaultStyle The default style to use.
   */
  public int getNumSecondaryBackgrounds( final TextStyle defaultStyle )
  {
    if( backgroundSet ) {
      return getNumSecondaryBackgrounds();
    } else {
      return defaultStyle.getNumSecondaryBackgrounds();
    }
  }



  //----------------------------------------------------------------------


  /**
   * Merge a new style with the style. 
   * @param newStyle The style to merge with this one.
   * @return a new TextStyle created this style and <tt>newStyle</tt>.  If 
   * <tt>newStyle.isXSet()</tt> is <tt>true</tt> then the new style's 
   * <tt>X</tt> attribute will have the value of <tt>newStyle</tt>'s 
   * <tt>X</tt> attribute.  Otherwise, the new style's <TT>x</tt> attribute
   * will have the value of this value's <TT>X</tt> attribute, if it is set,
   * otherwise the attribute will not be set.
   */
  public TextStyle mergeWith( final TextStyle newStyle )
  {
    return new TextStyle( this, newStyle );
  }

  /**
   * Given a font name and font size, return the Font structure that should
   * be used under the current style.
   * @param defaults The defaultStyle to fall back on.
   * @param font The name of the font to use.
   * @param size The size of the font.
   * @return The Font structure to use
   */
  public Font getFont( final TextStyle defaults, final String font, final int size )
  {
    int style = Font.PLAIN;
    if( boldSet ? bold : defaults.bold ) style += Font.BOLD;
    if( italicSet ? italic : defaults.italic ) style += Font.ITALIC;

    final String key = style + font + size;
    Font f = (Font)fonts.get( key );
    if( f == null ) fonts.put( key, (f = new Font( font, style, size )) );
    return f;
  }


  //----------------------------------------------------------------------


  /**
   * Convert the style to a String.
   */
  public String toString()
  {
    return "[<" + boldSet + ", " + bold + ">, <" +
      italicSet + ", " + italic + ">, <" +
      colorSet + ", " + color + ">, <" +
      backgroundSet + ", " + primaryBack + ", " + secondaryBack + ">]";
  }

  /**
   * Determine if two style objects are equal.  Two styles are equal
   * iff they have the same set, and those attributes are equal.
   * @return <tt>True</tt> iff the two styles are equal.
   */
  public boolean equals( final Object x )
  {
    if( x != null && x.getClass().equals( this.getClass() ) )
    {
      final TextStyle y = (TextStyle)x;
      boolean eq = true;

      eq = boldSet == y.boldSet &&
      italicSet == y.italicSet &&
      colorSet == y.colorSet &&
      backgroundSet == y.backgroundSet;

      if( eq )
      {
        if( boldSet ) eq &= bold == y.bold;
        if( italicSet ) eq &= italic == y.italic;
        if( colorSet ) eq &= color.equals( y.color );
        if( backgroundSet ) {
          eq &= primaryBack.equals( y.primaryBack ); 
          eq &= getNumSecondaryBackgrounds() == y.getNumSecondaryBackgrounds();
          if( eq ) {
            final Iterator it1 = new BackgroundIterator( secondaryBack );
            final Iterator it2 = new BackgroundIterator( y.secondaryBack );
            while( it1.hasNext() && it2.hasNext() )
            {
              final Color c1 = (Color)it1.next();
              final Color c2 = (Color)it2.next();
              eq &= c1.equals( c2 );
            }
          }
        }
      }
      return eq;
    }
    else
    {
      return false;
    }
  }

  public int hashCode()
  {
    int hc = 0;

    if( colorSet ) hc += color.hashCode();
    if( backgroundSet )
    {
      final Iterator iter = new BackgroundIterator( primaryBack, secondaryBack );
      while( iter.hasNext() ) {
        hc += iter.next().hashCode();
      }
    }

    if( boldSet )
    {
      if( bold ) hc |= B_BIT; else hc &= ~B_BIT;
    }
    if( italicSet )
    {
      if( italic ) hc |= I_BIT; else hc &= ~I_BIT;
    }

    return hc;
  }



  //================================================================

  private static final Color[] empty = new Color[0];

  private class BackgroundIterator
  extends AbstractRemovelessIterator
  {

    private Color first;
    private Color[] rest;
    private int where;

    public BackgroundIterator( Color f, Color[] r )
    {
      first = f;
      rest = (r == null) ? empty : r;
      where = -1;
    }

    public BackgroundIterator( Color[] r )
    {
      first = null;
      rest = (r == null) ? empty : r;
      where = 0;
    }

    public Object next()
    {
      if( where == -1 ) {
        where = 0;
        return first;
      } else {
        return rest[where++];
      }
    }

    public boolean hasNext()
    {
      if( where == -1 ) {
        return true;
      } else {
        return where < rest.length;
      }
    }
  }
}
