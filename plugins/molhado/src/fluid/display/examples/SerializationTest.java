package fluid.display.examples;

import java.awt.Color;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fluid.display.TextBean;
import fluid.display.TextClickedEvent;
import fluid.display.TextClickedListener;
import fluid.display.TextCoord;
import fluid.display.TextResizedEvent;
import fluid.display.TextResizedListener;
import fluid.display.TextStyle;

public class SerializationTest
implements TextClickedListener, TextResizedListener, Serializable
{
  private String[] formatText( String text, int lineWidth )
  {
    Vector lines = new Vector( 20 );
    int lastSpace = 0, firstChar = 0;

    for( int c = 0; c < text.length(); )
    {
      char ch = text.charAt( c );

      if( (c-firstChar+1) % lineWidth == 0 )
      {
        if( ch == ' ' || ch == '\n' ) lastSpace = c;
        lines.addElement( text.substring( firstChar, lastSpace ) );
        firstChar = c = lastSpace + 1;
      }
      else if( ch == '\n' )
      {
        lines.addElement( text.substring( firstChar, c ) );
        lastSpace = firstChar = ++c;
      }
      else if( ch == ' ' )
        lastSpace = c++;
      else 
        c++;
    }
    lines.addElement( text.substring( firstChar, text.length() ) );
    
    String[] lines2 = new String[lines.size()];
    for( int i = 0; i < lines.size(); i++ )
      lines2[i] = (String)lines.elementAt( i );
    return lines2;
  }

  private void styleText()
  {
    int count = 0;
    for( int i = 0; i < lines.length; i++ )
    {
      int start = 0;
      for( int j = 0; j < lines[i].length(); )
      {
        if( lines[i].charAt( j ) == ' ' )
        {
          if( count == 0 )
            tb.applyStyle( bluestyle, i, start, i, j-1 );
          else if( count == 1 )
            tb.applyStyle( redstyle, i, start, i, j-1 );
          else
            tb.applyStyle( greenstyle, i, start, i, j-1 );
          count = (count+1) % 3;
          while( j < lines[i].length() && lines[i].charAt( j ) == ' ' ) j++;
          start = j;
        }
        else j++;
      }

      if( count == 0 )
        tb.applyStyle( bluestyle, i, start, i, lines[i].length()-1 );
      else if( count == 1 )
        tb.applyStyle( redstyle, i, start, i, lines[i].length()-1 );
      else
        tb.applyStyle( greenstyle, i, start, i, lines[i].length()-1 );
      count = (count+1)%3;
    }
    tb.repaint();
  }

  private static String getText( String filename )
  {
    byte buffer[] = new byte[1024];
    StringBuffer input = new StringBuffer();
    FileInputStream instream = null;

    try
    {
      instream = new FileInputStream( filename );
      while( true )
      {
        int n = instream.read( buffer );
        if( n == -1 ) break;
        input.append( new String( buffer, 0, n ) );
      }
    }
    catch( EOFException e ) {
    }
    catch( IOException e ) {
      System.err.println( "Error reading file!  " + e.getMessage() );
      e.printStackTrace();
    }

    return input.toString();
  }

  public static void main( String[] args ) {
    if( args.length < 1 ) {
      System.out.println( "Need file to read!" );
    }
    else {
      String text = getText( args[0] );
      new SerializationTest( text );
    }
    System.exit( 1 );
  }


  private String[] lines;
  private String text;
  private TextBean tb;
  private TextStyle ts, redstyle, greenstyle, bluestyle;

  public SerializationTest( String txt ) {
    text = txt;
    lines = null;

    ts = new TextStyle( Color.orange, Color.black );
    redstyle = new TextStyle( Color.red, Color.white );
    greenstyle = new TextStyle( Color.green, Color.white );
    bluestyle = new TextStyle( Color.blue, Color.white );

    String[] dummy = { " " };
    tb = new TextBean( dummy, 600, 400, "Courier", 9 );
    tb.addTextClickedListener( this );
    tb.addTextResizedListener( this );

    JFrame fr = new JFrame( "Text" );
    fr.getContentPane().add( new JScrollPane( tb ) );
    fr.pack();
    fr.setVisible( true );

    try
    {
      FileOutputStream f = new FileOutputStream( "pickle.ser" );
      ObjectOutput s = new ObjectOutputStream(f);
      s.writeObject( tb );
      s.flush();
      s.close();
    }
    catch( Exception e )
    {
      System.out.println( e );
    }

    System.exit( 0 );
  }

  public void textClicked( TextClickedEvent e ) {
    TextCoord where = e.getTextCoord();
    TextCoord where2 = new TextCoord( where.getLine(), where.getChar() );
    tb.applyStyle( ts, where, where2 );
    tb.repaint();
  }

  public void propertyChange( java.beans.PropertyChangeEvent e ) {
    textResized( (TextResizedEvent)e );
  }

  public void textResized( TextResizedEvent e ) {
    System.out.println( "reformatting..." );
    lines = formatText( text, e.getNewWidth() );        
    tb.setText( lines );
    System.out.println( "Styling ..." );
    styleText();
    System.out.println( "Done!\n" );
  }    
}
