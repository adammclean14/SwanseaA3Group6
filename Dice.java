/**
 * @file Dice.java
 * @author Alan Woolley
 * @date 6 Dec 2014
 *
 * A Class for generating a random Int between 1-6, also stores the previous value of the roll.
 */

public class Dice {
	
	// Member Variables
	private static int m_dieRoll;
	private static final int MIN_ROLL = 1;
	private static final int MAX_ROLL = 6;
	
	/**
	 * A Blank constructor as the Member variables do not need to have default values
	 * 
	 */
	public Dice() {
		
	}
	
	/**
	 * Method to generate a new random number between the finals MIN_ROLL & MAX_ROLL
	 * 
	 * \return m_dieRoll
	 * 
	 */
	public static int getNewRoll(){
		m_dieRoll =  MIN_ROLL + (int)(Math.random()*MAX_ROLL); 
		return m_dieRoll;
	}
	
	/**
	 * A Method to return the previous value stored, this method should not be called before a get
	 * 
	 * \return m_dieRoll
	 * 
	 */
	
	public static int getPrevValue(){
			return m_dieRoll;
	}
		

}
