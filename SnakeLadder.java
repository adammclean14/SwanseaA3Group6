/**
 * @file	-SnakeLadder.java
 * @author	-F.Akkad
 * @date	-2 Dec '14
 * 
 * 
 */
package gamesproject;
import java.util.Random;

public class SnakeLadder {
	
	public static int m_POneCorner;
	public static int m_PTwoCorner;
	public static int m_PThreeCorner;
	public static int m_PFourCorner;
	public static int m_MoveNumber;
	public static int m_SetTail;
	public static int m_SetHead;
	

	public void getSnakes() {
		
	}
	
	public void getLadders() {
		
	}
	
	public void setCorner() {
		
	}
	
	public static int getMoves() {
		Dice dice = new Dice();
		return Dice.diceRoll();
	}
	
	public void move() {
		m_MoveNumber = getMoves();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMoves());

	}

}
