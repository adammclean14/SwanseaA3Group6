COMMENTS STILL NEED DOING
/**
 * @file	-Dice.java
 * @author	-F.Akkad
 * @date	-2 Dec '14
 * 
 * Operates the dice function of the snakes and ladders game
 * which generates a random number between 1 and 6 inclusively
 * to be used for the players move.
 */
package gamesproject;
import java.util.Random;

public class Dice {
	
	/* the lowest boundary used in generating a random number (inclusive) */
	final public static int LOW_DICEROLL = 1;
	/* the highest boundary used in generating a random number (exclusive) */
	final public static int HIGH_DICEROLL = 7;
	/* the number of times a number is generated before the final selection */
	final public static int SHUFFLE = 11;
	public static int m_Count = 0;
	public static int m_SelectedNumber = 0;
	
	public static void main(String args[]) {
		System.out.println(diceRoll());
	}
	
	/**
	 * Method to shuffle and then retrieve a randomly
	 * generated number between 1 and 6 inclusively.
	 */
	public static int diceRoll() {
		boolean activated = true;
		if (activated == true) {
			while (m_Count < SHUFFLE) {
				Random rand = new Random();
				m_SelectedNumber = rand.nextInt(HIGH_DICEROLL - LOW_DICEROLL) + LOW_DICEROLL;
				m_Count ++;
			}
		}
		return m_SelectedNumber;
	}

}
