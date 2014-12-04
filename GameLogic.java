import java.util.ArrayList;


abstract class GameLogic {

	private ArrayList<Player> m_order;
	private Boolean m_gameWon;


	
	abstract void boardSetUp();
	
	
	public ArrayList<Player> getOrderPlay(){
		//returns a player
		return m_order;
	}
	
	public void setGameWon(Boolean won){
		// Set the game to true if won
		m_gameWon = won;
	}
	public Boolean getGameWon(){

		if(m_gameWon){
			return true;
		}else{
			return false;
		}
		
	}
	abstract void play();
}