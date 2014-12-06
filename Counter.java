import java.awt.Color;

/**
 * @file Counter.java
 * @author Adam Mclean
 * Display colour and location of each counter 
 */

public class Counter {

	private Color m_color;
	private int m_locationX;
	private int m_locationY;

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
		m_locationX = i;
		m_locationY = j;
	}
	
	/**
	 * Get X location
	 * @return X location
	 */
	public int getLocationX() {
		return m_locationX;
	}
	
	/**
	 * Get Y location
	 * @return Y location
	 */
	public int getLocationY() {
		return m_locationY;
	}

	/**
	 * Set colour for counter 
	 * @param color of piece
	 */
	public void setColor(Color color){
		this.m_color = color;
	}

	/**
	 *  Get method to get colour for counter
	 * @return color of piece
	 */
	public Color getColor(){
		return m_color;
	}
}
