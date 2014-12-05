import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Timer;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;


public class BoardTile extends JPanel{
	private JButton diceRollbtn;
	private JFrame snakeLadderBoard;
	static String imageLocation;

	public static void main (String[] args){

	}
	
	
	//Number of movers, number of players passed in
	// produces a 10 x 10 grid for snakes and ladders
	public void createSnakeLadderBoard(int numberOfMovers, int numberOfPlayers){

		//GridLayout experimentLayout = new GridLayout(10,10);
		JFrame snakeLadderBoard = new JFrame("Snakes and Ladders");
		snakeLadderBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//try array of 1-100 with 10 rows incrementing properly
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout((new GridLayout(10, 10, 2, 2)));
		boardPanel.setSize(750, 750);
		boardPanel.setBounds(0, 0, 750, 750);


		snakeLadderBoard.getContentPane().add(boardPanel);


		int x = 100;
		int[][] numbers = new int[10][10];
		boolean ascend = false;
		boolean swap = false;
		for (int i = 9; i>=0; i--){
			for (int j = 9; j>= 0; j--){
				if (x == 90 && swap == false) {
					x = x - 9;
					ascend = true;
					swap = true;
				} else if (x == 91 && swap == true) {
					x = x - 11;
					ascend = false;
					swap = false;
				} else if ( x == 70 && swap == false) {
					x = x - 9;
					ascend = true;
					swap = true;
				} else if (x == 71 && swap == true) {
					x = x - 11;
					ascend = false;
					swap = false;
				}  else if ( x == 50 && swap == false) {
					x = x - 9;
					ascend = true;
					swap = true;
				} else if (x == 51 && swap == true) {
					x = x - 11;
					ascend = false;
					swap = false;
				}  else if ( x == 30 && swap == false) {
					x = x - 9;
					ascend = true;
					swap = true;
				} else if (x == 31 && swap == true) {
					x = x - 11;
					ascend = false;
					swap = false;
				}  else if ( x == 10 && swap == false) {
					x = x - 9;
					ascend = true;
					swap = true;
				} else if (x == 11 && swap == true) {
					x = x - 11;
					ascend = false;
					swap = false;
				}
				if (ascend == false) {
					JLabel label2 = new JLabel("" + x);
					numbers[i][j] = x;
					x = x - 1;
					label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					boardPanel.add(label2, BorderLayout.NORTH);
				} else {
					JLabel label2 = new JLabel("" + x);
					numbers[i][j] = x;
					x = x + 1;
					label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					boardPanel.add(label2, BorderLayout.NORTH);
				}
			}
		}   





		String imageLocation = getRandNum();
		final JLabel diceLbl = new JLabel("");
		java.awt.Image image = new ImageIcon (this.getClass().getResource(imageLocation)).getImage();
		diceLbl.setIcon(new ImageIcon(image));

		final JButton diceRollbtn = new JButton("Roll Die");

		diceRollbtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){
				if (i % 2 == 0){
					diceRollbtn.setText("End Roll");
					java.awt.Image gif = new ImageIcon (this.getClass().getResource("/diceBig.gif")).getImage();
					diceLbl.setIcon(new ImageIcon(gif));
					i = i + 1;
				}
				else{
					diceRollbtn.setText("Start Roll");
					String thing = getRandNum();
					java.awt.Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
					diceLbl.setIcon(new ImageIcon(image));
					System.out.println(thing);	
					i = i -1;
				}}});


		//Dice Stuff Panel
		//The panel can't be moved to the right side so just move the components inside the panel
		JPanel menuPanel = new JPanel();
		//menuPanel.setLayout((new GridLayout(10, 10, 2, 2)));
		menuPanel.setSize(150, 150);
		menuPanel.setBounds(0, 0, 150, 300);
		menuPanel.setLayout(null);
		menuPanel.setBackground(Color.LIGHT_GRAY);
		//added roll die btn   
		diceRollbtn.setBounds(755, 350, 150, 65);
		menuPanel.add(diceRollbtn);
		//added dice animation/image
		diceLbl.setBounds(925, 350, 65, 65);
		menuPanel.add(diceLbl);

		//Timer Title Label
		JLabel timerTitle = new JLabel("TIMER");
		timerTitle.setBounds(815, 590, 150, 150);
		timerTitle.setFont((new Font(timerTitle.getName(), Font.PLAIN, 35)));
		menuPanel.add(timerTitle);

		//Timer Number just for demonstration of placement...not functional
		JLabel timerFakeLbl = new JLabel("0:23:15");
		timerFakeLbl.setBounds(800, 635, 150, 150);
		timerFakeLbl.setFont((new Font(timerFakeLbl.getName(), Font.PLAIN, 35)));
		timerFakeLbl.setForeground(Color.BLUE);
		menuPanel.add(timerFakeLbl);

		//Label to make a border for the timer labels
		JLabel timerBox = new JLabel("");
		timerBox.setBounds(790,625,150,125);
		Border timerBdr = BorderFactory.createLineBorder(Color.BLUE, 5);
		timerBox.setBorder(timerBdr);
		menuPanel.add(timerBox);

		//Player Turn Lbl
		JLabel playerLbl = new JLabel("YOUR TURN:");
		playerLbl.setBounds(775, 150, 250, 150);
		playerLbl.setFont((new Font(playerLbl.getName(), Font.PLAIN, 35)));
		menuPanel.add(playerLbl);

		//Player Turn Name Lbl
		JLabel turnLbl = new JLabel("Jon");
		turnLbl.setBounds(850, 175, 250, 200);
		turnLbl.setFont((new Font(turnLbl.getName(), Font.PLAIN, 35)));
		turnLbl.setForeground(Color.RED);
		menuPanel.add(turnLbl);

		//Label to make a border for the player labels
		JLabel playerBox = new JLabel("");
		playerBox.setBounds(765,175,225,150);
		Border playerBdr = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
		playerBox.setBorder(playerBdr);
		menuPanel.add(playerBox);

		snakeLadderBoard.getContentPane().add(menuPanel);

		//CREATING COUNTERS
		//IDEALLY COUNTERS WILL BE JCOMPONENTS BECAUSE THEY ARE EASIER TO MOVE/TRACK AS SUCH
		//HOWEVER LAYERING A JCOMP OVER THE GRID IS HARD.
		//JLabel counter1 = new JLabel();
		//java.awt.Image image3 = new ImageIcon (this.getClass().getResource("5dice.gif")).getImage();
		//counter1.setIcon(new ImageIcon(image3));
		//counter1.setBounds(735,250,225,150);
		//menuPanel.add(counter1);


		//Each square is roughly 75 pixels wide/high
		snakeLadderBoard.setSize(1000,800);
		snakeLadderBoard.setVisible(true);
		snakeLadderBoard.setResizable(false);

		snakeLadderBoard.add(new PaintSurface(), BorderLayout.CENTER);    
		//snakeLadderBoard.add(new PaintCounter(), BorderLayout.CENTER);


	}

	public String getRandNum(){
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		//Creates string for image location
		return imageLocation = randomNum + "dice.gif";
	}


	private class PaintSurface extends JPanel {
		public PaintSurface() {
			repaint();

		}
		private void paintBackground(Graphics2D g2){

			g2.setPaint(Color.LIGHT_GRAY);
		}
		//THIS FUNCTION PAINTS THE SNAKES, LADDERS, AND COUNTERS ONTO THE BOARD...NEED TO SPLIT THIS UP PROBABLY
		public void paint(Graphics g) {


			//Creating graphics variable and getting image
			Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
			java.awt.Image image = new ImageIcon (this.getClass().getResource("/ladder.png")).getImage();  
			java.awt.Image image2 = new ImageIcon (this.getClass().getResource("/snake.gif")).getImage(); 
			//for rotational transforming
			//AffineTransform identity = new AffineTransform();




			//------------------------------------
			//-----------LADDER DRAWINGS----------
			//------------------------------------



			AffineTransform trans = new AffineTransform();


			//creating transform variable for drawing image
			trans.scale(0.2, 0.2); 
			trans.translate(800, 80);
			trans.rotate( Math.toRadians(45) );

			//drawing a ladder
			g2d.drawImage(image, trans, this);


			//-----Drawing 2nd ladder
			AffineTransform trans2 = new AffineTransform();            	    	

			//The problem is transform functions go from bottom to top so ...
			trans2.scale(0.2, 0.2); 
			//So 
			trans2.translate(2600, 1600 );
			trans2.rotate( Math.toRadians(135) );

			g2d.drawImage(image, trans2, this);
			//------Done Drawing 2nd Ladder

			//-----Drawing 3rd ladder
			AffineTransform trans3 = new AffineTransform();            	    	

			trans3.scale(0.2, 0.2); 
			//So 
			trans3.translate(2650, 1600 );
			trans3.rotate( Math.toRadians(0) );

			g2d.drawImage(image, trans3, this);
			//------Done Drawing 3rd Ladder

			//-----Drawing 4th ladder
			AffineTransform trans4 = new AffineTransform();            	    	

			trans4.scale(0.2, 0.4); 
			//So 
			trans4.translate(1525, 1000 );
			trans4.rotate( Math.toRadians(0) );

			g2d.drawImage(image, trans4, this);
			//------Done Drawing 4th Ladder




			//------------------------------------
			//-----------SNAKE DRAWINGS-----------
			//------------------------------------

			AffineTransform trans5 = new AffineTransform();


			//creating transform variable for drawing image
			trans5.scale(0.75, 0.75); 
			trans5.translate(220, 975);
			trans5.rotate( Math.toRadians(270) );

			//drawing a SNAKE
			g2d.drawImage(image2, trans5, this);


			//-----Drawing 2nd SNAKE
			AffineTransform trans6 = new AffineTransform();            	    	

			//The problem is transform functions go from bottom to top so ...
			trans6.scale(0.5, 0.5); 

			trans6.translate(1250, 425 );
			trans6.rotate( Math.toRadians(225) );

			g2d.drawImage(image2, trans6, this);
			//------Done Drawing 2nd SNAKE

			//-----Drawing 3rd SNAKE
			AffineTransform trans7 = new AffineTransform();            	    	

			trans7.scale(0.75, 0.75); 
			trans7.translate(500, 650 );
			trans7.rotate( Math.toRadians(290) );

			g2d.drawImage(image2, trans7, this);
			//------Done Drawing 3rd SNAKE

			//-----Drawing 4th SNAKE
			AffineTransform trans8 = new AffineTransform();            	    	

			trans8.scale(0.5, 0.25); 
			//So 
			trans8.translate(500, 700 );
			trans8.rotate( Math.toRadians(-45) );

			g2d.drawImage(image2, trans8, this);
			//------Done Drawing 4th SNAKE

			
			g2d.setColor(Color.RED);
			g2d.fillOval(703,703,20,20);
			g2d.setColor(Color.BLUE);
			g2d.fillOval(703 - 75,703 - (75 * 2),20,20);
			g2d.setColor(Color.ORANGE);
			g2d.fillOval(703 - (75 * 4), 703 - 75, 20, 20);
			g2d.setColor(Color.BLACK);

			int i = 0;
			while (i < 5){
				g2d.fillOval(703 - (75 * 8) + i, 703 - 75, 20, 20);
				g2d.translate(1, 1);
				g2d.clearRect(703 - (75 * 8) + i, 703 - 75, 20, 20);
				//g2d.
				i = i + 1;
			}
			}
	}



	//+++++++
	private class PaintCounter extends Canvas {
		public PaintCounter() {
		

		}
		private void paintBackground(Graphics2D g2){

			g2.setPaint(Color.LIGHT_GRAY);
		}
		//THIS FUNCTION PAINTS THE SNAKES, LADDERS, AND COUNTERS ONTO THE BOARD...NEED TO SPLIT THIS UP PROBABLY
		public void paint(Graphics g) {


			//Creating graphics variable and getting image
			Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.

			//------------------------------------
			//-----------COUNTER DRAWINGS---------
			//------------------------------------
			//PROBABLY SHOULD MAKE COUNTERS AS JLABELS WITH AN IMAGE
			//MUCH EASIER TO MOVE AROUND AND CAN STORE AS VARIABLE
			
		}


	}
}
