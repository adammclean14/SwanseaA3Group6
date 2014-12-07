/**
 * @file Dice.java
 * @author Alan Woolley
 * @date 6 Dec 2014
 *
 * 
 */

import java.util.ArrayList;

public class Mover {
	
	//Class Variables
	private ArrayList<Integer> m_moverArray = new ArrayList<>();
	private int m_numberofMovers;
	
	//Finals to set upper and lower bounds for generating random numbers.
	private static final int MIN = 1;
	private static final int MAX = 10;
	
	
	/**
	 * The Constructor for the Class, takes a parameter of number of moves and asssigns to the class
	 * variable m_numberofMovers. Then calls the private method createMoverArray
	 * @param numberofMovers
	 */
	public Mover(int numberofMovers) {
		this.m_numberofMovers = numberofMovers;
		createMoverArray(this.m_numberofMovers);
	}
	/**
	 * Private method to create a array of random integers between the bounds set in the finals MIN and MAX
	 * This is called in the constructor as it only has to be executed once.
	 * @param m_moverArray 
	 */
	private void createMoverArray(int numberofMovers){
		do{
			int temp = MIN + (int)(Math.random()*MAX);
			// Needed a break statement otherwise if numberofMoves > 10 would start a infinite loop.
			if(numberofMovers > 10){
				break;
			}
			if(!m_moverArray.contains(temp)){
				m_moverArray.add(temp);
			}
		}while(m_moverArray.size() < numberofMovers );
		
	}
	
	/**
	 * This method will return the arrayList called m_moverArray for use elsewhere in the program.
	 * @return m_moverArray 
	 */
	public ArrayList<Integer> getMoverArrayList(){
		return m_moverArray;
	}
	
	/**
	 * Test method to output the contents of the array m_moverArray
	 */
	public void print(){
		for(int s : m_moverArray){
			System.out.println(s);
		}
	}
}
