package fluid.display.examples;

import java.awt.BorderLayout;
import java.awt.Color;
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
import fluid.display.TextCoord;
import fluid.display.TextResizedEvent;
import fluid.display.TextResizedListener;
import fluid.display.TextStyle;

public class tbex extends JFrame implements TextClickedListener, TextResizedListener {
  private String[] formatText( String text, int lineWidth ) {
    Vector lines = new Vector( 20 );
    int lastSpace = 0, firstChar = 0;

    for( int c = 0; c < text.length(); ) {
      char ch = text.charAt( c );

      if( (c-firstChar+1) % lineWidth == 0 ) {
	if( ch == ' ' || ch == '\n' ) lastSpace = c;
	lines.addElement( text.substring( firstChar, lastSpace ) );
	firstChar = c = lastSpace + 1;
      }
      else if( ch == '\n' ) {
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
      JFrame f = new tbex( text );
      f.pack();
      f.setVisible( true );
    }
  }


  private String[] lines;
  private String text;
  private TextBean tb;
  private TextStyle ts;

  public tbex( String txt ) {
    text = txt;
    lines = null;

    ts = new TextStyle( Color.red, Color.black );

    String[] dummy = { " " };
    tb = new TextBean( dummy, 600, 400, "Courier", 9 );
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

  public void propertyChange( java.beans.PropertyChangeEvent e ) {
    textResized( (TextResizedEvent)e );
  }

  public void textClicked( TextClickedEvent e ) {
    TextCoord where = e.getTextCoord();
    TextCoord where2 = new TextCoord( where.getLine(), where.getChar()+1 );
    tb.applyStyle( ts, where, where2 );
    tb.repaint();
  }

  public void textResized( TextResizedEvent e ) {
    System.out.println( "reformatting..." + e.getNewWidth() );
    lines = formatText( text, e.getNewWidth() );
    tb.setText( lines );
  }  
}
