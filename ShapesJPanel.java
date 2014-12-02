import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class shapesJPanel extends JPanel {
	
	public void paintComponent(Graphics graphics) {
		/* call superclass's paint method */
		super.paintComponent( graphics );
		this.setBackground( Color.WHITE );
		graphics.setColor( Color.BLACK );
		/* drawRect(int x, int y, int width, int height) */
		boolean snakesAndLadders = true;
		int boardNumber = 0;
		if (snakesAndLadders == true) {
			boardNumber = 11;
		} else {
			boardNumber = 9;
		}
		int widthSpacing = 0;
		int heightSpacing = 0;
		int squareCount = 1;
		int rowCount = 1;
		while (squareCount < boardNumber && rowCount < boardNumber) {
			graphics.drawRect( SPACING+(widthSpacing *SHAPE_WIDTH), 
	                 TOP_MARGIN+SPACING+(heightSpacing * SHAPE_HEIGHT),
	                 SHAPE_WIDTH, SHAPE_HEIGHT  );
			if (squareCount == boardNumber - 1) {
				widthSpacing = 0;
				squareCount = 1;
				heightSpacing ++;
				rowCount ++;
			} else {
			widthSpacing ++;
			squareCount++;
			}
		}
		
		graphics.drawRect(SPACING + (12 *SHAPE_WIDTH), TOP_MARGIN+SPACING+(5*SHAPE_HEIGHT), SHAPE_WIDTH, SHAPE_HEIGHT);
	} /* end paintComponent() */
		/* the space between the boundaries and shapes */
	private final int SPACING = 10;
	/* the space between the top boundary and a shape */
	private final int TOP_MARGIN = 2;
	/** the line ends x-coordinate */
	private final int LINE_END_X = 380;
	/** shape width */
	private final int SHAPE_WIDTH = 100;
	/** shape height */
	private final int SHAPE_HEIGHT = 100;
	/** a shape's arc width */
	private final int ARC_WIDTH = 20;
	/** a shape's arc height */
	private final int ARC_HEIGHT = 20;
} /* end class ShapesJPanel */
