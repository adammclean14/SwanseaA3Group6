import java.util.ArrayList;

/**
 * \file GameLogic.java
 * \author Alan Wooley
 * \date 7th Dec 14
 * 
 * This abstract class defines the way that the game methods should
 * be set up.
 */

abstract class GameLogic {

	/* This stores the order that the players are in. */
	private ArrayList<Player> m_order;

	/* This field stores whether the game has been won or not. */
	private Boolean m_gameWon;

	/**
	 * This method gets the order that the players are in.
	 * @return the order the players are in.
	 */
	public ArrayList<Player> getOrderPlay(){
		return m_order;
	}

	/**
	 * Set GameWon to true if the game has been won.
	 * @param won True if game is won. False otherwise.
	 */
	public void setGameWon(Boolean won){
		m_gameWon = won;
	}

	/**
	 * See if the game has been won.
	 * @return True if game has been won. False if not.
	 */
	public Boolean getGameWon(){
		return m_gameWon;		
	}

	/**
	 * This abstract class is going to be implemented by subclasses and called
	 * to start playing the game.
	 */
	abstract void play();

	/**
	 * This abstract class is going to be implemented by subclasses and called
	 * to set up the game board.
	 */
	abstract void boardSetUp();
}