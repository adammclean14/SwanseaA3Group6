import java.awt.Color;

/**
 * @file Player.java
 * @author Alan Woolley
 * @date 2 Dec 2014
 *
 * Abstract Class providing a layout for all a player object.
 */

public abstract class Player {
	private String m_name;
	private Color m_pieceColor;
	private Boolean m_hasWon;
	

	/** 
	  * @param name
	  * @param color
	  * @param hasWon
	  *
	  *
	  *
	  */
	Player(String name, Color color, Boolean hasWon){
		m_name = name;
		m_pieceColor = color;
		m_hasWon = hasWon;
	}
	 /** 
	  * This method returns the name stored in the variable m_name;
	  */
	public String getName(){
		return m_name;
	}
	public void setName(String name){
		name = m_name;
	}
	public Color getColor(){
		return m_pieceColor;
	}
	public void setColor(Color color){
		color = m_pieceColor;
	}

	public Boolean getWon(){
		return m_hasWon;

	}
	public void setHasWon(Boolean hasWon){
		hasWon = m_hasWon;

	}
	
}
