import java.awt.Color;


public abstract class Player {
	private String m_name;
	private Color m_pieceColor;
	private Boolean m_hasWon;
	
	Player(String name, Color color, Boolean hasWon){
		m_name = name;
		m_pieceColor = color;
		m_hasWon = hasWon;
	}
	abstract String getName();
	abstract void setName(String name);
	abstract Color getColor();
	abstract void setColor(Color color);
	abstract Boolean getWon();
	abstract void setHasWon(Boolean hasWon);
	
}
