//This Class gets the player info and from this the subclasses decide how to project this info


public class PieceClass {
	int gameType;
	int playerMark;
	//Gets game type
	public int getGame(){
		//Will call into gameLogic or some other class and find which game is being played
		//Can have S&L return 1 and TTT return 2
		//If gameType = 1 (S&L), then counter subclass will activate
		//If gameType = 2 (TTT), the NoughtCross subclass will activate
		//Really subclasses will check this value and if the value doesn't match the value they need, the class will end
		return gameType;
	}
	
	//This class could make the getGame function redundant
	//Gets player color/symbol (symbol being nought or cross)
	public int getPlayerID(){
		//Returns an int that tells class what color or symbol the player is using
		//i.e. 1,2,3,4 all return the different colors and 5,6 return X and O
		return playerMark;
	}
}
