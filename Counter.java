import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Counter /*extends Piece*/ {
	
	/*
	 * Display colour and location of each piece (SnakeLadder)
	 * movement animations for the following:
	 * 		Up a ladder
	 * 		Down a snake
	 * 		Across the board
	 * 		Diagonally across the board
	 * 		Animation for winning the game (display message and counter blinks)
	 */
	
	private Color color;
	private String pieceImage;
	private String counterColour;
	
	// Displays snakes image on the board based on location
	
	public void displaySnake(){
		pieceImage = "snakeImage.jpeg";
		JLabel snake = new JLabel();
		Image image = new ImageIcon(this.getClass().getResource(pieceImage)).getImage();
		snake.setIcon(new ImageIcon(image));
	}
	
	// Displays ladders image on board based on location
	public void displayLadder(){
		pieceImage = "ladderImage.png";
		JLabel ladder = new JLabel();
		Image image = new ImageIcon(this.getClass().getResource(pieceImage)).getImage();
		ladder.setIcon(new ImageIcon(image));
	}
	
	// Animation for when counter moves up a ladder
	public void upLadder(){
		
	}
	
	// Animation for when counter moves down a snake
	public void downSnake(){
		
	}
	
	//Animation for the counter to move sideways
	public void moveAcross(){
		
	}
	
	//Animation for the counter to move diagonally
	public void moveDiagonal(){
		
	}
	
	// Set colour for counter 
	public void setColor(Color color){
		
	}
	
	// Get method to get colour for counter
	public Color getColor(){
		return null;
	}
	
	// Animation for winning the game (display message and counter blinks)
	public void gameWon(){
		JOptionPane.showMessageDialog(null, "Game over!");
		//Code for counter blinking
	}

}
