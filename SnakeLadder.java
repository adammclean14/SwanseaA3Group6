/**
 * @file	SnakeLadder.java
 * @author	F.Akkad
 * @date	2 Dec '1
 * 
 * 
 */
import java.awt.Color;

public class SnakeLadder {

	public SnakeLadder(int numberOfMovers, int numberOfPlayers, String[] playerNames, Color[] playerColors){
		BoardTile board = new BoardTile();
		board.createSnakeLadderBoard(numberOfMovers, numberOfPlayers);
		
		
	}
}
