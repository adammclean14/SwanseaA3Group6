import java.util.ArrayList;


abstract class GameLogic {

	private ArrayList<Player> m_order;
	private Boolean m_gameWon;
	private int m_playerIndex = 0;


	
	abstract void boardSetUp();
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

		if(m_gameWon){
			return true;
		}else{
			return false;
		}
		
	}
	public Player nextMove() {
		Player temp = New Player();
		temp = m_order.get(m_playerIndex);
		m_playerIndex++;
		return temp;
		
	}
	abstract void play();
}