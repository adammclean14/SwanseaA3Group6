import java.awt.Color;
public class Human extends Player {
	public String m_name;
	public Color m_pieceColor;
	public Boolean m_hasWon;
	
	public Human(String name, Color pieceColor, Boolean hasWon){
		m_name = name;
		m_pieceColor = pieceColor;
		m_hasWon = hasWon;
	}
	
	public void setName(String name){
		m_name = name;
	}
	public void setColor(Color pieceColor){
		m_pieceColor = pieceColor;
	}
	public void setHasWon(Boolean hasWon){
		m_hasWon = hasWon;
	}
	
	public String getName(){
		return m_name;
	}
	public Color getColor(){
		return m_pieceColor;
	}
	public Boolean getWon(){
		return m_hasWon;
	}
	
	public int RollDie(){
		return 0;
	}
	
	public void TicTacToeMove(){
		
	}
}
