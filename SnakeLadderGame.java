import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
public class SnakeLadderGame implements Runnable{

	JFrame frame;
	JFrame frame2;
	DrawPanel drawPanel;
	JButton rollBtn;
	JLabel diceLbl;
	JLabel nameLbl;
	private int oneX = 25;
	private int oneY = 700;
	private int twoX = 25;
	private int twoY = 700;
	private int threeX = 25;
	private int threeY = 700;
	private int fourX = 25;
	private int fourY = 700;
	boolean left1 = false;
	boolean right1 = true;
	boolean forward1 = true;
	boolean wonGame = false;
	boolean left2 = false;
	boolean right2 = true;
	boolean forward2 = true;

	boolean left3 = false;
	boolean right3 = true;
	boolean forward3 = true;
	boolean left4 = false;
	boolean right4 = true;
	boolean forward4 = true;
	static String imageLocation;
	int turn = 0;
	//This is actually #
	int numberOfPlayers = 4;
	Dice m_dice = new Dice();
	ArrayList<Player> playersList;
	ArrayList<Integer> moverNumbers;
	LabelTimer timerLbl;
	
	//gets all this info from game launcher
	public SnakeLadderGame(int numberOfMovers, ArrayList<Player> players){
		playersList = players;
		numberOfPlayers = playersList.size();
		Mover moverArray = new Mover(numberOfMovers);
		moverNumbers = moverArray.getMoverArrayList();
		go();
		//moverNumbers.contains(integer) returns boolean
	}
	
	private void go() {

		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel = new DrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setVisible(true);
		frame.setSize(755, 775);
		frame.setLocation(500,0);

		frame2 = new JFrame("MENU FRAME");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);
		frame2.setLayout(null);
		frame2.setSize(300, 100);
		frame2.setLocation(200,350);

		rollBtn = new JButton("Start Roll");
		rollBtn.setBackground(Color.RED);
		rollBtn.setBounds(0, 0, 75, 75);
		frame2.add(rollBtn);

		diceLbl = new JLabel();
		diceLbl.setBounds(100,0,75,75);
		
		timerLbl = new LabelTimer();
		
		nameLbl = new JLabel();
		timerLbl.setBounds(200, 0, 100, 75);
		timerLbl.timerStart();
		
		java.awt.Image startImage = new ImageIcon (this.getClass().getResource("/1dice.gif")).getImage();
		diceLbl.setIcon(new ImageIcon(startImage));

		frame2.add(diceLbl);
		
		frame2.add(nameLbl);
		
		frame2.add(timerLbl);
		




		rollBtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){

				if (i % 2 == 0){
					rollBtn.setText("End Roll");
					java.awt.Image gif = new ImageIcon (this.getClass().getResource("/diceBig.gif")).getImage();
					diceLbl.setIcon(new ImageIcon(gif));
					i = i + 1;


				}
				else{

					rollBtn.setText("Start Roll");

					String thing = Dice.getNewRoll() +"dice.gif";
					java.awt.Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
					diceLbl.setIcon(new ImageIcon(image));
					
					i = i -1;

					System.out.println("turn number" + turn);
					Thread one = new Thread() {
						public void run() {
							if (numberOfPlayers == turn){
								turn = 0;
							}
							if (turn == 3 && numberOfPlayers > turn){
								//Player 4
								//nameLbl.setText("Turn: " + playersList.get(3).getName());
								//nameLbl.setForeground(playersList.get(3).getColor());
								moveIt4(Dice.getPrevValue());
								
								turn = 0;

							}
							else if (turn == 0 && numberOfPlayers > turn){
								//Player 1
								//nameLbl.setText("Turn: " + playersList.get(0).getName());
								//nameLbl.setForeground(playersList.get(0).getColor());
								moveIt(Dice.getPrevValue());
							
								turn ++;
							}
							else if (turn == 2 && numberOfPlayers > turn){
								//Player 3
								//nameLbl.setText("Turn: " + playersList.get(2).getName());
								//nameLbl.setForeground(playersList.get(2).getColor());
								moveIt3(Dice.getPrevValue());
								
								turn++;
							}

							else if (turn == 1 && numberOfPlayers > turn){
								//player 2
								//nameLbl.setText("Turn: " + playersList.get(1).getName());
								//nameLbl.setForeground(playersList.get(1).getColor());
								moveIt2(Dice.getPrevValue());
							
								turn ++;
								//turn = -1;
							}
						}  
					};
					one.start();

				}

			}
		});
	}

	public void run(){
		//moveIt(15);
	}


	public int getRandNum(){
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		//Creates string for image location
		return randomNum;
	}



	class DrawPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			//paints oval   
			//Ellipse2D.Double shape = new Ellipse2D.Double(oneX, oneY, 20, 20);
			//((Graphics2D) g).draw(shape);

			//player 2
			if (numberOfPlayers >= 2){
				g.setColor(playersList.get(1).getColor());
				g.fillOval(twoX, twoY, 20, 20);
			}
			//player 3
			if (numberOfPlayers >= 3){
				g.setColor(playersList.get(2).getColor());
				g.fillOval(threeX, threeY, 20, 20);
			}
			//player 4
			if (numberOfPlayers >= 4){
				g.setColor(playersList.get(3).getColor());
				g.fillOval(fourX, fourY, 20, 20);
				//paints board lines
			}
			
			//Player 1
			
			g.setColor(playersList.get(0).getColor());
			g.fillOval(oneX, oneY, 20, 20);
			
			g.setColor(Color.RED);
			int i = 0;
			int j = 0;
			

			//DRAWS NUMBERS

			while (j < 11){


				int loc = (j * 10) + 1;
				String locS = Integer.toString(loc);
				g.setColor(Color.BLUE);
				if ((loc + 19) % 20 == 0 ){
					g.drawString(locS, 15, 700 + (-j * 75));

					String locS2 = Integer.toString(loc + 1);
					g.drawString(locS2, 15 + (75*1), 700 + (-j * 75));

					String locS3 = Integer.toString(loc + 2);
					g.drawString(locS3, 15 + (75*2), 700 + (-j * 75));

					String locS4 = Integer.toString(loc + 3);
					g.drawString(locS4, 15 + (75*3), 700 + (-j * 75));

					String locS5 = Integer.toString(loc + 4);
					g.drawString(locS5, 15 + (75*4), 700 + (-j * 75));

					String locS6 = Integer.toString(loc + 5);
					g.drawString(locS6, 15 + (75*5), 700 + (-j * 75));

					String locS7 = Integer.toString(loc + 6);
					g.drawString(locS7, 15 + (75*6), 700 + (-j * 75));

					String locS8 = Integer.toString(loc + 7);
					g.drawString(locS8, 15 + (75*7), 700 + (-j * 75));

					String locS9 = Integer.toString(loc + 8);
					g.drawString(locS9, 15 + (75*8), 700 + (-j * 75));

					String locS10 = Integer.toString(loc + 9);
					g.drawString(locS10, 15 + (75*9), 700 + (-j * 75));

				}
				else{
					g.drawString(locS, 715, 700 + (-j * 75));

					String locS2 = Integer.toString(loc + 1);
					g.drawString(locS2, 715 - (75*1), 700 + (-j * 75));

					String locS3 = Integer.toString(loc + 2);
					g.drawString(locS3, 715 - (75*2), 700 + (-j * 75));

					String locS4 = Integer.toString(loc + 3);
					g.drawString(locS4, 715 - (75*3), 700 + (-j * 75));

					String locS5 = Integer.toString(loc + 4);
					g.drawString(locS5, 715 - (75*4), 700 + (-j * 75));

					String locS6 = Integer.toString(loc + 5);
					g.drawString(locS6, 715 - (75*5), 700 + (-j * 75));

					String locS7 = Integer.toString(loc + 6);
					g.drawString(locS7, 715 - (75*6), 700 + (-j * 75));

					String locS8 = Integer.toString(loc + 7);
					g.drawString(locS8, 715 - (75*7), 700 + (-j * 75));

					String locS9 = Integer.toString(loc + 8);
					g.drawString(locS9, 715 - (75*8), 700 + (-j * 75));

					String locS10 = Integer.toString(loc + 9);
					g.drawString(locS10, 715 - (75*9), 700 + (-j * 75));
				}
				j++;

			}

			//DRAWS GRID
			g.setColor(Color.RED);
			while (i < 12){
				g.drawLine(0, 75*(i-1), 750, 75*(i-1));
				g.drawLine(75*(i-1), 0, 75*(i-1), 750);

				i++;
			}




			
			Graphics2D g2d=(Graphics2D)g; 
			java.awt.Image image = new ImageIcon (this.getClass().getResource("/ladder.png")).getImage();
			java.awt.Image image2 = new ImageIcon (this.getClass().getResource("/snake.gif")).getImage();  
			
			
			//Ladder creation
			//82 TO 98
			AffineTransform L1 = new AffineTransform();
			L1.scale(0.2, 0.2); 
			L1.translate(800, 80);
			L1.rotate( Math.toRadians(45) );
			g2d.drawImage(image, L1, this);

			//54 to 66
			AffineTransform L2 = new AffineTransform();            	    	
			L2.scale(0.2, 0.2); 
			L2.translate(2600, 1600 );
			L2.rotate( Math.toRadians(135) );
			g2d.drawImage(image, L2, this);
			
			//48 to 53
			AffineTransform L3 = new AffineTransform();            	    	
			L3.scale(0.2, 0.2); 
			L3.translate(2650, 1600 );
			L3.rotate( Math.toRadians(0) );
			g2d.drawImage(image, L3, this);

			//16 to 45
			AffineTransform L4 = new AffineTransform();            	    	
			L4.scale(0.2, 0.4); 
			L4.translate(1525, 1000 );
			L4.rotate( Math.toRadians(0) );
			//g2d.drawImage(image, L4, this);
			
			//58 to 64
			AffineTransform L5 = new AffineTransform();
			L5.scale(0.2, 0.2); 
			L5.translate(1250, 1150 );
			L5.rotate( Math.toRadians(45));
			g2d.drawImage(image, L5, this);

			
			//snake drawings

			AffineTransform S1 = new AffineTransform();
			S1.scale(0.75, 0.75); 
			S1.translate(220, 975);
			S1.rotate( Math.toRadians(270) );
			g2d.drawImage(image2, S1, this);


			//-----Drawing 2nd SNAKE
			AffineTransform S2 = new AffineTransform();            	    	
			S2.scale(0.5, 0.5); 
			S2.translate(1250, 425 );
			S2.rotate( Math.toRadians(225) );
			g2d.drawImage(image2, S2, this);
		
			//-----Drawing 3rd SNAKE
			AffineTransform S3 = new AffineTransform();            	    	
			S3.scale(0.75, 0.75); 
			S3.translate(500, 650 );
			S3.rotate( Math.toRadians(290) );
			g2d.drawImage(image2, S3, this);
			
			//-----Drawing 4th SNAKE
			AffineTransform S4 = new AffineTransform();            	    	
			S4.scale(0.5, 0.25);  
			S4.translate(500, 700 );
			S4.rotate( Math.toRadians(-45) );
			g2d.drawImage(image2, S4, this);

			//78 to 27
			AffineTransform S5 = new AffineTransform();            	    	
			S5.scale(1, 1.2);  
			S5.translate(480, 500 );
			S5.rotate( Math.toRadians(225) );
			g2d.drawImage(image2, S5, this);
		}
	}



	private void moveIt(int diceRoll) {


		int pixelCount = 75 * diceRoll;
		while(pixelCount > 0 && wonGame == false){
			if(oneX == 700){
				right1 = false;
				left1 = true;
				forward1 = false;
				oneY--;
				pixelCount --;
				frame.repaint();
			}
			if(oneX == 24){
				right1 = false;
				left1 = true;
				forward1 = true;
				oneY--;
				pixelCount --;
				frame.repaint();
			}
			if ((oneY- 700) % 75 == 0) {
				right1 = true;
				left1 = false;
			}
			if(left1) {
				oneY--;
				pixelCount --;
				frame.repaint();
			}  
			if(right1 && forward1 && left1 == false){
				oneX++;  
				pixelCount --;
				frame.repaint();

			} 

			if(right1 && forward1 == false && left1 == false){
				oneX--;
				pixelCount --;
				frame.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( oneX > 20 && oneX < 45 && oneY == 25 ){
				System.out.println("Won Game");
				timerLbl.timerStop();
				oneX = 25;
				wonGame = true;
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( oneX > 315 && oneX < 335 && oneY == 625 ){
				while (oneY > 400){
					oneY--;
				}
				forward1 = true;
			}
			//SNAKE FROM 43 TO 3
			if (oneX > 160 && oneX < 180 && oneY == (700 - 300)) {
				while (oneY < 700){
					oneY++;
					forward1 = true;
					frame.repaint();
				}
				oneX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( oneX > 540 && oneX < 555 && oneY == 400 ){
				while (oneY > 325){
					oneY--;
				}
				oneX = 550;
				forward1 = false;
			}

			//LADDER FROM 54 TO 62
			if (oneX > 465 && oneX < 485 && oneY == 325){
				oneX =400;
				oneY = 250;
				forward1 = true;
			}

			//SNAKE FROM 74 TO 35
			if (oneX > 465 && oneX < 485 && oneY == 175){
				oneX = 400;
				oneY = oneY + (4*75);
				forward1 = false;
			}

			//SNAKE FROM 86 to 77
			if (oneX > 390 && oneX < 405 && oneY == 100){
				oneX = 250;
				oneY = 175;
				forward1 = false;
			}

			//LADDER FROM 82 TO 98
			if (oneX > 85 && oneX < 115 && oneY == 100){
				oneX = 175;
				oneY = 25;
				forward1 = false;
			}

			//SNAKE FROM 94 TO 72
			if (oneX > 475 && oneX < 490 && oneY == 25){
				oneX = 625;
				oneY = 175;
				forward1 = false;
			}



		}
		System.out.println(oneX);
		System.out.println(oneY);

	}

	private void moveIt2(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && wonGame == false){
			if(twoX == 700){
				right2 = false;
				left2 = true;
				forward2 = false;
				twoY--;
				pixelCount --;
				frame.repaint();

			}
			if(twoX == 24){
				right2 = false;
				left2 = true;
				forward2 = true;
				twoY--;
				pixelCount --;
				frame.repaint();

			}

			if ((twoY- 700) % 75 == 0) {
				right2 = true;
				left2 = false;

			}

			if(left2) {
				twoY--;
				pixelCount --;
				frame.repaint();

			}  

			if(right2 && forward2 && left2 == false){
				twoX++;  
				pixelCount --;
				frame.repaint();

			} 

			if(right2 && forward2 == false && left2 == false){
				twoX--;
				pixelCount --;
				frame.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( twoX > 20 && twoX < 45 && twoY == 25 ){
				System.out.println("Won Game");
				timerLbl.timerStop();
				twoX = 25;
				wonGame = true;
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( twoX > 315 && twoX < 335 && twoY == 625 ){
				while (twoY > 400){
					twoY--;
				}
				forward2 = true;
			}
			//SNAKE FROM 43 TO 3
			if (twoX > 160 && twoX < 180 && twoY == (700 - 300)) {
				while (twoY < 700){
					twoY++;
					forward2 = true;
					frame.repaint();
				}
				twoX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( twoX > 540 && twoX < 555 && twoY == 400 ){
				while (twoY > 325){
					twoY--;
				}
				twoX = 550;
				forward2 = false;
			}

			//LADDER FROM 54 TO 62
			if (twoX > 465 && twoX < 485 && twoY == 325){
				twoX =400;
				twoY = 250;
				forward2 = true;
			}

			//SNAKE FROM 74 TO 35
			if (twoX > 465 && twoX < 485 && twoY == 175){
				twoX = 400;
				twoY = twoY + (4*75);
				forward2 = false;
			}

			//SNAKE FROM 86 to 77
			if (twoX > 390 && twoX < 405 && twoY == 100){
				twoX = 250;
				twoY = 175;
				forward2 = false;
			}

			//LADDER FROM 82 TO 98
			if (twoX > 85 && twoX < 115 && twoY == 100){
				twoX = 175;
				twoY = 25;
				forward2 = false;
			}

			//SNAKE FROM 94 TO 72
			if (twoX > 475 && twoX < 490 && twoY == 25){
				twoX = 625;
				twoY = 175;
				forward2 = false;
			}



		}
		System.out.println(twoX);
		System.out.println(twoY);

	}

	private void moveIt3(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && wonGame == false){
			if(threeX == 700){
				right3 = false;
				left3 = true;
				forward3 = false;
				threeY--;
				pixelCount --;
				frame.repaint();

			}
			if(threeX == 24){
				right3 = false;
				left3 = true;
				forward3 = true;
				threeY--;
				pixelCount --;
				frame.repaint();

			}

			if ((threeY- 700) % 75 == 0) {
				right3 = true;
				left3 = false;

			}

			if(left3) {
				threeY--;
				pixelCount --;
				frame.repaint();

			}  

			if(right3 && forward3 && left3 == false){
				threeX++;  
				pixelCount --;
				frame.repaint();

			} 

			if(right3 && forward3 == false && left3 == false){
				threeX--;
				pixelCount --;
				frame.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( threeX > 20 && threeX < 45 && threeY == 25 ){
				System.out.println("Won Game");
				timerLbl.timerStop();
				threeX = 25;
				wonGame = true;
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( threeX > 315 && threeX < 335 && threeY == 625 ){
				while (threeY > 400){
					threeY--;
				}
				forward3 = true;
			}
			//SNAKE FROM 43 TO 3
			if (threeX > 160 && threeX < 180 && threeY == (700 - 300)) {
				while (threeY < 700){
					threeY++;
					forward3 = true;
					frame.repaint();
				}
				threeX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( threeX > 540 && threeX < 555 && threeY == 400 ){
				while (threeY > 325){
					threeY--;
				}
				threeX = 550;
				forward3 = false;
			}

			//LADDER FROM 54 TO 62
			if (threeX > 465 && threeX < 485 && threeY == 325){
				threeX =400;
				threeY = 250;
				forward3 = true;
			}

			//SNAKE FROM 74 TO 35
			if (threeX > 465 && threeX < 485 && threeY == 175){
				threeX = 400;
				threeY = threeY + (4*75);
				forward3 = false;
			}

			//SNAKE FROM 86 to 77
			if (threeX > 390 && threeX < 405 && threeY == 100){
				threeX = 250;
				threeY = 175;
				forward3 = false;
			}

			//LADDER FROM 82 TO 98
			if (threeX > 85 && threeX < 115 && threeY == 100){
				threeX = 175;
				threeY = 25;
				forward3 = false;
			}

			//SNAKE FROM 94 TO 72
			if (threeX > 475 && threeX < 490 && threeY == 25){
				threeX = 625;
				threeY = 175;
				forward3 = false;
			}



		}
		System.out.println(threeX);
		System.out.println(threeY);

	}


	private void moveIt4(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && wonGame == false){
			if(fourX == 700){
				right4 = false;
				left4 = true;
				forward4 = false;
				fourY--;
				pixelCount --;
				frame.repaint();

			}
			if(fourX == 24){
				right4 = false;
				left4 = true;
				forward4 = true;
				fourY--;
				pixelCount --;
				frame.repaint();

			}

			if ((fourY- 700) % 75 == 0) {
				right4 = true;
				left4 = false;

			}

			if(left4) {
				fourY--;
				pixelCount --;
				frame.repaint();

			}  

			if(right4 && forward4 && left4 == false){
				fourX++;  
				pixelCount --;
				frame.repaint();

			} 

			if(right4 && forward4 == false && left4 == false){
				fourX--;
				pixelCount --;
				frame.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( fourX > 20 && fourX < 45 && fourY == 25 ){
				System.out.println("Won Game");
				timerLbl.timerStop();
				fourX = 25;
				wonGame = true;
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( fourX > 315 && fourX < 335 && fourY == 625 ){
				while (fourY > 400){
					fourY--;
				}
				forward4 = true;
			}
			//SNAKE FROM 43 TO 3
			if (fourX > 160 && fourX < 180 && fourY == (700 - 300)) {
				while (fourY < 700){
					fourY++;
					forward4 = true;
					frame.repaint();
				}
				fourX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( fourX > 540 && fourX < 555 && fourY == 400 ){
				while (fourY > 325){
					fourY--;
				}
				fourX = 550;
				forward4 = false;
			}

			//LADDER FROM 54 TO 62
			if (fourX > 465 && fourX < 485 && fourY == 325){
				fourX =400;
				fourY = 250;
				forward4 = true;
			}

			//SNAKE FROM 74 TO 35
			if (fourX > 465 && fourX < 485 && fourY == 175){
				fourX = 400;
				fourY = fourY + (4*75);
				forward4 = false;
			}

			//SNAKE FROM 86 to 77
			if (fourX > 390 && fourX < 405 && fourY == 100){
				fourX = 250;
				fourY = 175;
				forward4 = false;
			}

			//LADDER FROM 82 TO 98
			if (fourX > 85 && fourX < 115 && fourY == 100){
				fourX = 175;
				fourY = 25;
				forward4 = false;
			}

			//SNAKE FROM 94 TO 72
			if (fourX > 475 && fourX < 490 && fourY == 25){
				fourX = 625;
				fourY = 175;
				forward4 = false;
			}



		}
		System.out.println(fourX);
		System.out.println(fourY);

	}


}
