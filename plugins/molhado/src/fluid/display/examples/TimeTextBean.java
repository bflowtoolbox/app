package fluid.display.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fluid.display.TextBean;
import fluid.display.TextClickedEvent;
import fluid.display.TextClickedListener;
import fluid.display.TextResizedEvent;
import fluid.display.TextResizedListener;
import fluid.display.TextStyle;

/* A simple class that reports the time it takes to style and paint 
 * a lot of Styled text.
 */

public class TimeTextBean extends JFrame implements TextClickedListener, TextResizedListener {
  private String[] formatText( String text, int lineWidth ) {
    Vector lines = new Vector( 20 );
    int lastSpace = 0, firstChar = 0; //@ INVARIANT: lastSpace >= firstChar

    for( int c = 0; c < text.length(); ) {
      char ch = text.charAt( c );

      // did we hit a line break 
      if( ch == '\n' ) {
	lines.addElement( text.substring( firstChar, c ) );
        c += 1;
        lastSpace = c;
        firstChar = c;
      } else {
        // check if the current character is a space
        if( ch == ' ' ) lastSpace = c;
        
        // have we hit the end of the line?
        if( (c-firstChar+1) % lineWidth == 0 ) {
          /* Avoid breaking words: copy to the last seen space if possible,
           * otherwise we now hunt for the next space or newline
           */
          if( firstChar >= lastSpace ) {
            while( ch != ' ' && ch != '\n' && c < text.length() ) {
              ch = text.charAt( c );
              c += 1;
            }
            lastSpace = c;
          }
	  lines.addElement( text.substring( firstChar, lastSpace ) );
          firstChar = lastSpace + 1;
          lastSpace = firstChar;
        }
        
        c += 1;
      }
    }
    // capture last line of text if necessary
    if( firstChar < text.length() ) {
      lines.addElement( text.substring( firstChar, text.length() ) );
    }
    
    String[] lines2 = new String[lines.size()];
    for( int i = 0; i < lines.size(); i++ ) {
      lines2[i] = (String)lines.elementAt( i );
      System.err.println( lines2[i] );
      System.out.println( "Line #" + i + " has width " + lines2[i].length() );
    }
    return lines2;
  }

  private void styleText() {
    int count = 0;
    for( int i = 0; i < lines.length; i++ ) {
      int start = 0;
      for( int j = 0; j < lines[i].length(); ) {
	if( lines[i].charAt( j ) == ' ' ) {
	  if( count == 0 )
	    tb.applyStyle( bluestyle, i, start, i, j );
	  else if( count == 1 )
	    tb.applyStyle( redstyle, i, start, i, j );
	  else
	    tb.applyStyle( greenstyle, i, start, i, j );
	  count = (count+1) % 3;
	  while( j < lines[i].length() && lines[i].charAt( j ) == ' ' ) j++;
	  start = j;
	}
	else j++;
      }

      if( count == 0 )
	tb.applyStyle( bluestyle, i, start, i, lines[i].length() );
      else if( count == 1 )
	tb.applyStyle( redstyle, i, start, i, lines[i].length() );
      else
	tb.applyStyle( greenstyle, i, start, i, lines[i].length() );
      count = (count+1)%3;
    }
  }

  private static String getText( String filename ) {
    byte buffer[] = new byte[1024];
    StringBuffer input = new StringBuffer();
    FileInputStream instream = null;

    try {
      instream = new FileInputStream( filename );
      while( true ) {
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
      JFrame f = new TimeTextBean( text );
      f.pack();
      f.setVisible( true );
    }
  }


  private String[] lines;
  private String text;
  private TimedTB tb;
  private TextStyle ts, redstyle, greenstyle, bluestyle;

  public TimeTextBean( String txt ) {
    text = txt;
    lines = null;

    ts = new TextStyle( Color.orange, Color.black );
    redstyle = new TextStyle( Color.red, Color.white );
    greenstyle = new TextStyle( Color.green, Color.white );
    bluestyle = new TextStyle( Color.blue, Color.white );

    String[] dummy = { " " };
    tb = new TimedTB( dummy, 600, 400, "Courier", 9 );
    final JScrollPane sp = new JScrollPane( tb );
    getContentPane().add( BorderLayout.CENTER, sp );    
    tb.addTextClickedListener( this );
    tb.addTextResizedListener( this );
    addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent e ) {
	System.exit( 0 );
      }
    } );
  }

  public void textClicked( TextClickedEvent e ) {
    tb.removeStyles();
    
    long time1 = System.currentTimeMillis();
    styleText();
    long time2 = System.currentTimeMillis();
    System.out.println( "\nStyling took " + (time2-time1) + " milliseconds." );

    tb.repaint();
  }

  public void propertyChange( java.beans.PropertyChangeEvent e ) {
    textResized( (TextResizedEvent)e );
  }

  public void textResized( TextResizedEvent e ) {
    System.out.println( "reformatting: width = " + e.getNewWidth() );
    lines = formatText( text, e.getNewWidth() );        
    tb.setText( lines );
  }    
}


class TimedTB extends TextBean {
  public TimedTB( String[] s, int w, int h, String f, int p ) {
    super( s, w, h, f, p );
  }

  public void paint( Graphics g ) {
    long time1 = System.currentTimeMillis();
    super.paint( g );
    long time2 = System.currentTimeMillis();
    System.out.println( "Painting took " + (time2-time1) + " milliseconds." );
  }
}
