import java.util.ArrayList;


public class GameLogic {

	private ArrayList<Player> m_order;
	private Boolean m_gameWon;

	
	GameLogic(){
		
	}
	
	public void boardSetUp(){
		//Set up the board
	}
	public void setOrderPlay(){
		//Set order of play
	}
	public ArrayList<Player> getOrderPlay(){
		//returns a player
		return m_order;
		
	}
	public void setGameWon(Boolean won){
		// Set the game to true if won
		m_gameWon = won;
	}
	public Boolean getGameWon(){
		//return if the game is won
		return m_gameWon;
		
	}
	public Player nextMove() {
		//return the player whos next turn it is
		return null;
		
	}
	public void play(){
		
		//method to play the game
		
	}
}
