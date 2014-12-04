import java.awt.Color;

/**
 * @file Counter.java
 * @author Adam Mclean
 * Display colour and location of each counter 
 */

public class Counter {

	private Color color;
	private int locationX;
	private int locationY;

    /**
     * Constructor counter which sets the counters location and color
     * @param i
     * @param j
     */
	public Counter(int i, int j){
		setLocation(i,j);
	}

	/**
	 * Set X and Y location
	 * @param i
	 * @param j
	 */
	private void setLocation(int i, int j) {
		locationX = i;
		locationY = j;
	}
	
	/**
	 * Get X location
	 * @return X location
	 */
	public int getLocationX() {
		return locationX;
	}
	
	/**
	 * Get Y location
	 * @return Y location
	 */
	public int getLocationY() {
		return locationY;
	}

	/**
	 * Set colour for counter 
	 * @param color of piece
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 *  Get method to get colour for counter
	 * @return color of piece
	 */
	public Color getColor(){
		return color;
	}
}
