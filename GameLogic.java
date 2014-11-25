import java.util.ArrayList;


public class GameLogic {

	private ArrayList<Player>order;
	private Boolean gameWon;

	
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
		return order;
		
	}
	public void setGameWon(Boolean won){
		// Set the game to true if won
		gameWon = won;
	}
	public Boolean getGameWon(){
		//return if the game is won
		return gameWon;
		
	}
	public Player nextMove() {
		//return the player whos next turn it is
		return null;
		
	}
	public void play(){
		
		//method to play the game
		
	}
}
