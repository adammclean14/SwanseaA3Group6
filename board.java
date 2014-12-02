import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class board {
	/* execute application */
	public static void main( String args[] ) {
		/* create frame for ShapesJPanel */
		JFrame frame =
				new JFrame( "Its a game.. you play it, well done..." );
				frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				shapesJPanel shapesJPanel = new shapesJPanel();
				shapesJPanel.setBackground( Color.WHITE );
				/* add panel to frame */
				frame.add( shapesJPanel );
				/* set frame size */
				frame.setSize( WIDTH, HEIGHT );
				/* display frame */
				frame.setVisible( true );
	} /* end main */
	/** the width of the shapes panel */
	private final static int WIDTH = 1035;
	/** the height of the shapes panel */
	private final static int HEIGHT = 2500;
} /* end class LinesRectsOvals */
