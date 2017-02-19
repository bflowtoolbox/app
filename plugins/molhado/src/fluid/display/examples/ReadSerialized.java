package fluid.display.examples;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fluid.display.TextBean;

public class ReadSerialized extends JFrame
{
  public ReadSerialized( String name )
  {
    try
    {
    	FileInputStream f = new FileInputStream( name );
    	ObjectInput s = new ObjectInputStream( f );
    	TextBean tb = (TextBean)s.readObject();
      final JScrollPane sp = new JScrollPane( tb );
      getContentPane().add( BorderLayout.CENTER, sp );    
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
  }

  public static void main( String[] args )
  {
    JFrame f = new ReadSerialized( args[0] );
    f.pack();
    f.show();
  }
}