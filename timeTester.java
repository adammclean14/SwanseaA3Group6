import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class timeTester{
	public static void main( String args[] ) {
		JFrame win = new JFrame( "Timer" );
		win.addWindowListener(
				new WindowAdapter() {
					public void windowClosing( WindowEvent e ) {
						System.exit( 0 );
					}
				}
				);
		LabelTimer l = new LabelTimer();
		Container content = win.getContentPane();
		content.setLayout( new BorderLayout() );
		content.add( "Center" , l );	
		win.pack();
		win.show();
		l.timerStart();
	}
}