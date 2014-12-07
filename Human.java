import java.awt.Color;
import java.util.Random;
/**
 * @file Human.java
 * @author Connor Wilkins
 * @date 4 Dec 2014
 *
 * Controls board set-up and game logic of the Tic Tac Toe game.
 */
public class Human extends Player {
	public String m_name;
	public Color m_pieceColor;
	public Boolean m_hasWon;
	
	/**
	 * The constructor for the HUman class
	 * 
	 * \param name
	 * \param pieceColor
	 * \param hasWon
	 */
	public Human(String name, Color pieceColor, Boolean hasWon){
		m_name = name;
		m_pieceColor = pieceColor;
		m_hasWon = hasWon;
	}
	
	/**
	 * The setter method for m_name
	 * 
	 * \param name
	 */
	public void setName(String name){
		m_name = name;
	}
	
	/**
	 * The setter method for m_pieceColor
	 * 
	 * \param pieceColor
	 */
	public void setColor(Color pieceColor){
		m_pieceColor = pieceColor;
	}
	
	/**
	 * The setter method for m_hasWon
	 * 
	 * \param hasWon
	 */
	public void setHasWon(Boolean hasWon){
		m_hasWon = hasWon;
	}
	
	/**
	 * The getter method for m_name
	 * 
	 * \return m_name
	 */
	public String getName(){
		return m_name;
	}
	
	/**
	 * The getter method for m_pieceColor
	 * 
	 * \return m_pieceColor
	 */
	public Color getColor(){
		return m_pieceColor;
	}
	
	/**
	 * The getter method for m_hasWon
	 * 
	 * \return m_hasWon
	 */
	public Boolean getWon(){
		return m_hasWon;
	}
	
	/**
	 * Rolls a dice to determine player movement in Snakes and Ladders
	 * 
	 * \return random.nextInt
	 */
	public int RollDie(){
		/**
		 * Range for random number generator. Random number generator can generate 0, 
		 * so the number of sides is set to 5, and 1 is added to the result
		 */
		//final int diceSides = 5;
		//Random randomGenerator = new Random();
		//return(randomGenerator.nextInt(diceSides) + 1);
		
		return Dice.getNewRoll();
	}
}
