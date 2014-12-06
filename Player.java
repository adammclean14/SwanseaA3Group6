import java.awt.Color;

/**
 * \file Player.java
 * \author Alan Woolley
 * \date 2 Dec 2014
 *
 * Abstract Class providing a layout for all a player object.
 */

public abstract class Player {

	/*
	 * subclass variables
	 */
	String m_name;
	Color m_pieceColor;
	Boolean m_hasWon;

	/** 
	 * Constructor for the player class
	 * @param name stores the name of the player
	 * @param color stores the color of the player
	 * @param hasWon stores a boolean depicting if you have won
	 */
	Player(String name, Color color, Boolean hasWon){
		m_name = name;
		m_pieceColor = color;
		m_hasWon = hasWon;
	}

	/**
	 * Empty constructor for the Player class
	 */
	Player(){
	}

	/** 
	 * This method returns the name stored in the variable m_name
	 * @return m_name
	 */
	public String getName(){
		return m_name;
	}

	/**
	 * Method to set the member variable m_name
	 * @param name
	 */
	public void setName(String name){
		name = m_name;
	}

	/**
	 * A Method to return piece color
	 * @return m_piceColor
	 */
	public Color getColor(){
		return m_pieceColor;
	}

	/**
	 * A method to set the color of a piece
	 * @param color
	 */
	public void setColor(Color color){
		color = m_pieceColor;
	}

	/**
	 * Returns a boolean on the state of the game if won
	 * @return m_hasWon Checks if player has won
	 */
	public Boolean getWon(){
		return m_hasWon;
	}

	/**
	 * A method to set the member variable m_hasWon
	 * @param hasWon Sets player hasWon to true
	 */
	public void setHasWon(Boolean hasWon){
		hasWon = m_hasWon;
	}
}