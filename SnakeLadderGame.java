import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
public class SnakeLadderGame implements Runnable{


	//Ladder variables
	private final float m_ladderSmallScalar = (float) 0.2;
	private final float m_ladderLargeScalar = (float) 0.4;

	private final float m_ladderRotationRight = (float) Math.toRadians(45);
	private final float m_ladderRotationLeft = (float) Math.toRadians(135);
	private final float m_ladderRotationUpright = 0;
	
	private AffineTransform m_ladderOne;
	private AffineTransform m_ladderTwo;
	private AffineTransform m_ladderThree;
	private AffineTransform m_ladderFour;
	private AffineTransform m_ladderFive;
	private AffineTransform m_ladderSix;
	private AffineTransform m_ladderSeven;
	private AffineTransform m_ladderEight;
	private AffineTransform m_ladderNine;
	private AffineTransform m_ladderTen;
	
	private final int m_ladderOneTranslateX = 800;
	private final int m_ladderOneTranslateY = 80;
	private final int m_ladderTwoTranslateX = 2600;
	private	final int m_ladderTwoTranslateY = 1600;
	private final int m_ladderThreeTranslateX = 2650;
	private final int m_ladderThreeTranslateY = 1600;
	private	final int m_ladderFourTranslateX = 1525;
	private final int m_ladderFourTranslateY = 1000;
	private final int m_ladderFiveTranslateX = 1250;
	private final int m_ladderFiveTranslateY = 1150;
	private final int m_ladderSixTranslateX =1650;
	private final int m_ladderSixTranslateY =1550;
	private final int m_ladderSevenTranslateX =1150;
	private final int m_ladderSevenTranslateY =3050;
	private final int m_ladderEightTranslateX = 3050;
	private final int m_ladderEightTranslateY = 2700;
	private final int m_ladderNineTranslateX = 3050;
	private final int m_ladderNineTranslateY = 800;
	private final int m_ladderTenTranslateX = 1875;
	private final int m_ladderTenTranslateY = 875;

	// snake variables
	private AffineTransform m_SnakeOne;
	private AffineTransform m_SnakeTwo;
	private AffineTransform m_SnakeThree;
	private AffineTransform m_SnakeFour;
	private AffineTransform m_SnakeFive;
	private AffineTransform m_SnakeSix;			
	private AffineTransform m_SnakeSeven;
	private AffineTransform m_SnakeEight;
	private AffineTransform m_SnakeNine;
	private AffineTransform m_SnakeTen;


	private final float m_snakeSmallestScalar = (float) 0.25;
	private final float m_snakeSmallerScalar = (float) 0.5;
	private final float m_snakeSmallScalar = (float) 0.7;
	private final float m_snakeLargeScalar = (float) 0.75;
	private final float m_snakeLargerScalar = (float) 1.0;
	private final float m_snakeLargestScalar = (float) 1.2;
	
	private final float m_snakeRotationUpright = (float) Math.toRadians(270);
	private final float m_snakeRotationSlightestRight = (float) Math.toRadians(290);
	private final float m_snakeRotationSlightRight = (float) Math.toRadians(225);
	private final float m_snakeRotationHardRight = (float) Math.toRadians(-135);
	private final float m_snakeRotationHardLeft = (float) Math.toRadians(-45);

	private final int m_snakeOneTranslateX = 220;
	private final int m_snakeOneTranslateY = 975;
	private final int m_snakeTwoTranslateX = 1250 ;
	private final int m_snakeTwoTranslateY= 425;
	private final int m_snakeThreeTranslateX = 500 ;
	private final int m_snakeThreeTranslateY  = 650 ;
	private final int m_snakeFourTranslateX = 500;
	private final int m_snakeFourTranslateY  = 700;
	private final int m_snakeFiveTranslateX = 480 ;
	private final int m_snakeFiveTranslateY = 500;
	private final int m_snakeSixTranslateX = 15 ;
	private final int m_snakeSixTranslateY = 600;
	private final int m_snakeSevenTranslateX = 715 ;
	private final int m_snakeSevenTranslateY = 1025;
	private final int m_snakeEightTranslateX = 825;
	private final int m_snakeEightTranslateY = 425;
	private final int m_snakeNineTranslateX = 775;
	private final int m_snakeNineTranslateY = 1450;
	private final int m_snakeTenTranslateX = 205 ;
	private final int m_snakeTenTranslateY = 1450;
	




	
	//Other variables
	
	JFrame frame;
	JFrame frame2;
	DrawPanel drawPanel;
	JButton rollBtn;
	JLabel diceLbl;
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
	JLabel nameLbl;
	LabelTimer timeLbl;
	//gets all this info from game launcher
	public SnakeLadderGame(int numberOfMovers, ArrayList<Player> players){
		playersList = players;
		numberOfPlayers = playersList.size();
		Mover moverArray = new Mover(numberOfMovers);
		moverNumbers = moverArray.getMoverArrayList();
		go();

	}

	private void go() {

		frame = new JFrame("Snakes And Ladders Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel = new DrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setVisible(true);
		frame.setSize(755, 775);
		frame.setLocation(500,0);

		frame2 = new JFrame("MENU");
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

		java.awt.Image startImage = new ImageIcon (this.getClass().getResource("/1dice.gif")).getImage();
		diceLbl.setIcon(new ImageIcon(startImage));

		frame2.add(diceLbl);

		nameLbl = new JLabel();
		nameLbl.setBounds(180,0,200,50);
		//nameLbl.setText("Default");


		timeLbl = new LabelTimer();
		timeLbl.setBounds(180,10,80,100);
		timeLbl.timerStart();
		frame2.add(timeLbl);
		frame2.add(nameLbl);
		if(nameLbl.getText().equals("")){
			nameLbl.setText("Turn: " + playersList.get(0).getName());
			nameLbl.setForeground(playersList.get(0).getColor());
		}

		rollBtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){
				if (wonGame == false){
					if (i % 2 == 0){
						rollBtn.setText("End Roll");
						java.awt.Image gif = new ImageIcon (this.getClass().getResource("/diceBig.gif")).getImage();
						diceLbl.setIcon(new ImageIcon(gif));
						i = i + 1;

						if(turn < numberOfPlayers){
							nameLbl.setText("Turn: " + playersList.get(turn).getName());
							nameLbl.setForeground(playersList.get(turn).getColor());
						}
						else{
							nameLbl.setText("Turn: " + playersList.get(0).getName());
							nameLbl.setForeground(playersList.get(0).getColor());						
						}



					}
					else{

						rollBtn.setText("Start Roll");
						String thing = Dice.getNewRoll() +"dice.gif";
						java.awt.Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
						diceLbl.setIcon(new ImageIcon(image));

						i = i -1;

						//System.out.println("turn number" + turn);
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
									//Player 2
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
			}
		});


		//moveIt(79);
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

					String locm_SnakeTwo = Integer.toString(loc + 1);
					g.drawString(locm_SnakeTwo, 15 + (75*1), 700 + (-j * 75));

					String locm_SnakeThree = Integer.toString(loc + 2);
					g.drawString(locm_SnakeThree, 15 + (75*2), 700 + (-j * 75));

					String locm_SnakeFour = Integer.toString(loc + 3);
					g.drawString(locm_SnakeFour, 15 + (75*3), 700 + (-j * 75));

					String locm_SnakeFive = Integer.toString(loc + 4);
					g.drawString(locm_SnakeFive, 15 + (75*4), 700 + (-j * 75));

					String locm_SnakeSix = Integer.toString(loc + 5);
					g.drawString(locm_SnakeSix, 15 + (75*5), 700 + (-j * 75));

					String locm_SnakeSeven = Integer.toString(loc + 6);
					g.drawString(locm_SnakeSeven, 15 + (75*6), 700 + (-j * 75));

					String locm_SnakeEight = Integer.toString(loc + 7);
					g.drawString(locm_SnakeEight, 15 + (75*7), 700 + (-j * 75));

					String locm_SnakeNine = Integer.toString(loc + 8);
					g.drawString(locm_SnakeNine, 15 + (75*8), 700 + (-j * 75));

					String locm_SnakeTen = Integer.toString(loc + 9);
					g.drawString(locm_SnakeTen, 15 + (75*9), 700 + (-j * 75));

				}
				else{
					g.drawString(locS, 715, 700 + (-j * 75));

					String locm_SnakeTwo = Integer.toString(loc + 1);
					g.drawString(locm_SnakeTwo, 715 - (75*1), 700 + (-j * 75));

					String locm_SnakeThree = Integer.toString(loc + 2);
					g.drawString(locm_SnakeThree, 715 - (75*2), 700 + (-j * 75));

					String locm_SnakeFour = Integer.toString(loc + 3);
					g.drawString(locm_SnakeFour, 715 - (75*3), 700 + (-j * 75));

					String locm_SnakeFive = Integer.toString(loc + 4);
					g.drawString(locm_SnakeFive, 715 - (75*4), 700 + (-j * 75));

					String locm_SnakeSix = Integer.toString(loc + 5);
					g.drawString(locm_SnakeSix, 715 - (75*5), 700 + (-j * 75));

					String locm_SnakeSeven = Integer.toString(loc + 6);
					g.drawString(locm_SnakeSeven, 715 - (75*6), 700 + (-j * 75));

					String locm_SnakeEight = Integer.toString(loc + 7);
					g.drawString(locm_SnakeEight, 715 - (75*7), 700 + (-j * 75));

					String locm_SnakeNine = Integer.toString(loc + 8);
					g.drawString(locm_SnakeNine, 715 - (75*8), 700 + (-j * 75));

					String locm_SnakeTen = Integer.toString(loc + 9);
					g.drawString(locm_SnakeTen, 715 - (75*9), 700 + (-j * 75));
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
			java.awt.Image ladderImage = new ImageIcon (this.getClass().getResource("/ladder.png")).getImage();
			java.awt.Image snakeImage = new ImageIcon (this.getClass().getResource("/snake.gif")).getImage();  

			//Ladder creation
						
			
			//82 TO 98
			if (moverNumbers.contains(1))
			{
				m_ladderOne = new AffineTransform();
				m_ladderOne.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderOne.translate(m_ladderOneTranslateX, m_ladderOneTranslateY);
				m_ladderOne.rotate( m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderOne, this);
			}


			//54 to 66
			if (moverNumbers.contains(2)){
				m_ladderTwo = new AffineTransform();            	    	
				m_ladderTwo.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderTwo.translate(m_ladderTwoTranslateX, m_ladderTwoTranslateY );
				m_ladderTwo.rotate( m_ladderRotationLeft );
				g2d.drawImage(ladderImage, m_ladderTwo, this);
			}

			if (moverNumbers.contains(3)){

				//48 to 53
				m_ladderThree = new AffineTransform();            	    	
				m_ladderThree.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderThree.translate(m_ladderThreeTranslateX, m_ladderThreeTranslateY );
				m_ladderThree.rotate(m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderThree, this);
			}


			if (moverNumbers.contains(4)){
				//16 to 45
				m_ladderFour = new AffineTransform();            	    	
				m_ladderFour.scale(m_ladderSmallScalar, m_ladderLargeScalar); 
				m_ladderFour.translate(m_ladderFourTranslateX, m_ladderFourTranslateY );
				m_ladderFour.rotate(m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderFour, this);
			}

			if (moverNumbers.contains(5)){
				//58 to 64
				m_ladderFive = new AffineTransform();
				m_ladderFive.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderFive.translate(m_ladderFiveTranslateX, m_ladderFiveTranslateY );
				m_ladderFive.rotate( m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderFive, this);
			}

			if (moverNumbers.contains(6)){
				//44 TO 56
				m_ladderSix = new AffineTransform();
				m_ladderSix.scale(m_ladderSmallScalar,m_ladderSmallScalar); 
				m_ladderSix.translate(m_ladderSixTranslateX, m_ladderSixTranslateY );
				m_ladderSix.rotate(m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderSix, this);
			}

			if (moverNumbers.contains(7)){
				//4 to 17
				m_ladderSeven = new AffineTransform();
				m_ladderSeven.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderSeven.translate(m_ladderSevenTranslateX, m_ladderSevenTranslateY );
				m_ladderSeven.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderSeven, this);
			}

			if (moverNumbers.contains(8)){
				//12 TO 29
				m_ladderEight = new AffineTransform();
				m_ladderEight.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderEight.translate(m_ladderEightTranslateX, m_ladderEightTranslateY );
				m_ladderEight.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderEight, this);
			}

			if (moverNumbers.contains(9)){
				//69 TO 72
				m_ladderNine = new AffineTransform();
				m_ladderNine.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderNine.translate(m_ladderNineTranslateX, m_ladderNineTranslateY );
				m_ladderNine.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderNine, this);
			}

			if (moverNumbers.contains(10)){
				//76 TO 84
				m_ladderTen = new AffineTransform();
				m_ladderTen.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderTen.translate(m_ladderTenTranslateX, m_ladderTenTranslateY  );
				m_ladderTen.rotate( m_ladderRotationLeft);
				g2d.drawImage(ladderImage, m_ladderTen, this);
			}

			//snake creation


			
			
			if (moverNumbers.contains(1)){
				//43 to 3
				m_SnakeOne = new AffineTransform();
				m_SnakeOne.scale(m_snakeLargeScalar,m_snakeLargeScalar); 
				m_SnakeOne.translate(m_snakeOneTranslateX, m_snakeOneTranslateY);
				m_SnakeOne.rotate( m_snakeRotationUpright);
				g2d.drawImage(snakeImage, m_SnakeOne, this);
			}

			if (moverNumbers.contains(2)){
				//94 to 72
				m_SnakeTwo = new AffineTransform();            	    	
				m_SnakeTwo.scale(m_snakeSmallerScalar, m_snakeSmallerScalar); 
				m_SnakeTwo.translate(m_snakeTwoTranslateX, m_snakeTwoTranslateY );
				m_SnakeTwo.rotate(m_snakeRotationSlightRight);
				g2d.drawImage(snakeImage, m_SnakeTwo, this);
			}

			if (moverNumbers.contains(3)){
				//74 to 35
				m_SnakeThree = new AffineTransform();            	    	
				m_SnakeThree.scale(m_snakeLargeScalar, m_snakeLargeScalar); 
				m_SnakeThree.translate(m_snakeThreeTranslateX, m_snakeThreeTranslateY );
				m_SnakeThree.rotate(m_snakeRotationSlightestRight );
				g2d.drawImage(snakeImage, m_SnakeThree, this);
			}

			if (moverNumbers.contains(4)){
				//86 to 77
				m_SnakeFour = new AffineTransform();            	    	
				m_SnakeFour.scale(m_snakeSmallerScalar, m_snakeSmallestScalar);  
				m_SnakeFour.translate(m_snakeFourTranslateX, m_snakeFourTranslateY);
				m_SnakeFour.rotate( m_snakeRotationHardLeft );
				g2d.drawImage(snakeImage, m_SnakeFour, this);
			}

			if (moverNumbers.contains(5)){
				//78 to 27
				m_SnakeFive = new AffineTransform();            	    	
				m_SnakeFive.scale(m_snakeLargerScalar, m_snakeLargestScalar);  
				m_SnakeFive.translate(m_snakeFiveTranslateX, m_snakeFiveTranslateY );
				m_SnakeFive.rotate( m_snakeRotationSlightRight );
				g2d.drawImage(snakeImage, m_SnakeFive, this);
			}

			if (moverNumbers.contains(6)){
				//80 to 1
				m_SnakeSix = new AffineTransform();            	    	
				m_SnakeSix.scale(m_snakeLargerScalar, m_snakeLargestScalar);  
				m_SnakeSix.translate(m_snakeSixTranslateX, m_snakeSixTranslateY );
				m_SnakeSix.rotate( m_snakeRotationUpright );
				g2d.drawImage(snakeImage, m_SnakeSix, this);
			}

			if (moverNumbers.contains(7)){
				//55 to 31			
				m_SnakeSeven = new AffineTransform();            	    	
				m_SnakeSeven.scale(m_snakeLargerScalar, m_snakeSmallerScalar);  
				m_SnakeSeven.translate(m_snakeSevenTranslateX, m_snakeSevenTranslateY );
				m_SnakeSeven.rotate(m_snakeRotationHardRight);
				g2d.drawImage(snakeImage, m_SnakeSeven, this);
			}

			if (moverNumbers.contains(8)){
				//97 to 75
				m_SnakeEight = new AffineTransform();            	    	
				m_SnakeEight.scale(m_snakeSmallerScalar,m_snakeSmallerScalar); 
				m_SnakeEight.translate(m_snakeEightTranslateX, m_snakeEightTranslateY );
				m_SnakeEight.rotate( m_snakeRotationSlightRight );
				g2d.drawImage(snakeImage, m_SnakeEight, this);
			}

			if (moverNumbers.contains(9)){
				//33 to 8
				m_SnakeNine = new AffineTransform();            	    	
				m_SnakeNine.scale(m_snakeSmallScalar, m_snakeSmallerScalar); 
				m_SnakeNine.translate(m_snakeNineTranslateX, m_snakeNineTranslateY );
				m_SnakeNine.rotate( m_snakeRotationUpright );
				g2d.drawImage(snakeImage, m_SnakeNine, this);
			}

			if (moverNumbers.contains(10)){
				m_SnakeTen = new AffineTransform();            	    	
				m_SnakeTen.scale(m_snakeSmallerScalar,m_snakeSmallestScalar); 
				m_SnakeTen.translate(m_snakeTenTranslateX, m_snakeTenTranslateY );
				m_SnakeTen.rotate( m_snakeRotationUpright);
				g2d.drawImage(snakeImage, m_SnakeTen, this);
			}

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
				//System.out.println("Won Game");
				oneX = 25;
				//timeLbl.timerStop();
				wonGame = true;

				wonGameStuff(0);
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
			if ( oneX > 315 && oneX < 335 && oneY == 625 && moverNumbers.contains(4)){
				oneY = 400;
				forward1 = true;
			}
			//SNAKE FROM 43 TO 3
			if (oneX > 160 && oneX < 180 && oneY == 400 && moverNumbers.contains(1)) {
				while (oneY < 700){
					oneY++;
					forward1 = true;
					frame.repaint();
				}
				oneX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( oneX > 540 && oneX < 555 && oneY == 400 && moverNumbers.contains(3)){
				while (oneY > 325){
					oneY--;
				}
				oneX = 550;
				forward1 = false;
			}

			//LADDER FROM 54 TO 62
			if (oneX > 465 && oneX < 485 && oneY == 325 && moverNumbers.contains(2)){
				oneX =400;
				oneY = 250;
				forward1 = true;
			}

			//SNAKE FROM 74 TO 35
			if (oneX > 465 && oneX < 485 && oneY == 175 && moverNumbers.contains(3)){
				oneX = 400;
				oneY = oneY + (4*75);
				forward1 = false;
			}

			//SNAKE FROM 86 to 77
			if (oneX > 390 && oneX < 405 && oneY == 100 && moverNumbers.contains(4)){
				oneX = 250;
				oneY = 175;
				forward1 = false;
			}

			//LADDER FROM 82 TO 98
			if (oneX > 85 && oneX < 115 && oneY == 100 && moverNumbers.contains(1)){
				oneX = 175;
				oneY = 25;
				forward1 = false;
			}

			//SNAKE FROM 94 TO 72
			if (oneX > 475 && oneX < 490 && oneY == 25 && moverNumbers.contains(2)){
				oneX = 625;
				oneY = 175;
				forward1 = false;
			}

			//SNAKE FROM 80 TO 1
			if (oneX > 20 && oneX < 40 && oneY == 175 && moverNumbers.contains(6)){
				oneX = 25;
				oneY = 700;
				forward1 = true;
			}

			//LADDER FROM 58 to 64
			if (oneX > 160 && oneX < 180 && oneY == 325 && moverNumbers.contains(5)){
				oneX = 250;
				oneY = 250;
				forward1 = true;
			}

			//SNAKE FROM 78 TO 27
			if (oneX > 160 && oneX < 180 && oneY == 175 && moverNumbers.contains(5)){
				oneX = 475;
				oneY = 550;
				forward1 = true;
			}

			//SNAKE FROM 55 T0 31
			if (oneX > 390 && oneX < 410 && oneY == 325 && moverNumbers.contains(7)){
				oneX =699;
				oneY = 475;
				forward1 = false;
			}

			//SNAKE FROM 97 TO 75
			if (oneX > 240 && oneX < 260 && oneY == 25 && moverNumbers.contains(8)){
				oneX = 400;
				oneY = 175;
				forward1 = false;
			}

			//SNAKE FROM 33 TO 8
			if (oneX > 535 && oneX < 565 && oneY == 475 && moverNumbers.contains(9)){
				oneX = 545;
				oneY = 700;
				forward1 = true;
			}
			//SNAKE FROM 62 TO 59
			if (oneX > 90 && oneX < 110 && oneY == 250 && moverNumbers.contains(10)){
				oneX = 105;
				oneY = 325;
				forward1 = false;
			}
			//LADDER FROM 44 TO 56
			if (oneX > 240 && oneX < 260 && oneY == 400 && moverNumbers.contains(6)){
				oneX = 325;
				oneY = 325;
				forward1 = false;
			}

			//LADDER FROM 4 TO 17
			if (oneX > 240 && oneX < 260 && oneY == 700 && moverNumbers.contains(7)){
				oneX = 250;
				oneY = 625;
				forward1 = false;
			}

			//LADDER FROM 14 TO 29
			if (oneX > 615 && oneX < 635 && oneY == 625 && moverNumbers.contains(8)){
				oneX = 625;
				oneY = 550;
				forward1 = true;
			}

			//LADDER FROM 69 TO 72
			if (oneX > 615 && oneX < 635 && oneY == 250 && moverNumbers.contains(9)){
				oneX = 625;
				oneY = 175;
				forward1 = false;
			}

			//LADDER FROM 76 TO 84
			if (oneX > 315 && oneX < 335 && oneY == 175 && moverNumbers.contains(10)){
				oneX = 250;
				oneY = 100;
				forward1 = true;
			}


		}
		//System.out.println(oneX);
		//System.out.println(oneY);

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
				//System.out.println("Won Game");
				twoX = 25;
				wonGame = true;
				//timeLbl.timerStop();
				wonGameStuff(1);
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
			if ( twoX > 315 && twoX < 335 && twoY == 625 && moverNumbers.contains(4)){
				twoY = 400;
				forward2 = true;
			}
			//SNAKE FROM 43 TO 3
			if (twoX > 160 && twoX < 180 && twoY == 400 && moverNumbers.contains(1)) {
				while (twoY < 700){
					twoY++;
					forward2 = true;
					frame.repaint();
				}
				twoX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( twoX > 540 && twoX < 555 && twoY == 400 && moverNumbers.contains(3)){
				while (twoY > 325){
					twoY--;
				}
				twoX = 550;
				forward2 = false;
			}

			//LADDER FROM 54 TO 62
			if (twoX > 465 && twoX < 485 && twoY == 325 && moverNumbers.contains(2)){
				twoX =400;
				twoY = 250;
				forward2 = true;
			}

			//SNAKE FROM 74 TO 35
			if (twoX > 465 && twoX < 485 && twoY == 175 && moverNumbers.contains(3)){
				twoX = 400;
				twoY = twoY + (4*75);
				forward2 = false;
			}

			//SNAKE FROM 86 to 77
			if (twoX > 390 && twoX < 405 && twoY == 100 && moverNumbers.contains(4)){
				twoX = 250;
				twoY = 175;
				forward2 = false;
			}

			//LADDER FROM 82 TO 98
			if (twoX > 85 && twoX < 115 && twoY == 100 && moverNumbers.contains(1)){
				twoX = 175;
				twoY = 25;
				forward2 = false;
			}

			//SNAKE FROM 94 TO 72
			if (twoX > 475 && twoX < 490 && twoY == 25 && moverNumbers.contains(2)){
				twoX = 625;
				twoY = 175;
				forward2 = false;
			}

			//SNAKE FROM 80 TO 1
			if (twoX > 20 && twoX < 40 && twoY == 175 && moverNumbers.contains(6)){
				twoX = 25;
				twoY = 700;
				forward2 = true;
			}

			//LADDER FROM 58 to 64
			if (twoX > 160 && twoX < 180 && twoY == 325 && moverNumbers.contains(5)){
				twoX = 250;
				twoY = 250;
				forward2 = true;
			}

			//SNAKE FROM 78 TO 27
			if (twoX > 160 && twoX < 180 && twoY == 175 && moverNumbers.contains(5)){
				twoX = 475;
				twoY = 550;
				forward2 = true;
			}

			//SNAKE FROM 55 T0 31
			if (twoX > 390 && twoX < 410 && twoY == 325 && moverNumbers.contains(7)){
				twoX =699;
				twoY = 475;
				forward2 = false;
			}

			//SNAKE FROM 97 TO 75
			if (twoX > 240 && twoX < 260 && twoY == 25 && moverNumbers.contains(8)){
				twoX = 400;
				twoY = 175;
				forward2 = false;
			}

			//SNAKE FROM 33 TO 8
			if (twoX > 535 && twoX < 565 && twoY == 475 && moverNumbers.contains(9)){
				twoX = 545;
				twoY = 700;
				forward2 = true;
			}
			//SNAKE FROM 62 TO 59
			if (twoX > 90 && twoX < 110 && twoY == 250 && moverNumbers.contains(10)){
				twoX = 105;
				twoY = 325;
				forward2 = false;
			}

			//LADDER FROM 44 TO 56
			if (twoX > 240 && twoX < 260 && twoY == 400 && moverNumbers.contains(6)){
				twoX = 325;
				twoY = 325;
				forward2 = false;
			}

			//LADDER FROM 4 TO 17
			if (twoX > 240 && twoX < 260 && twoY == 700 && moverNumbers.contains(7)){
				twoX = 250;
				twoY = 625;
				forward2 = false;
			}

			//LADDER FROM 14 TO 29
			if (twoX > 615 && twoX < 635 && twoY == 625 && moverNumbers.contains(8)){
				twoX = 625;
				twoY = 550;
				forward2 = true;
			}

			//LADDER FROM 69 TO 72
			if (twoX > 615 && twoX < 635 && twoY == 250 && moverNumbers.contains(9)){
				twoX = 625;
				twoY = 175;
				forward2 = false;
			}

			//LADDER FROM 76 TO 84
			if (twoX > 315 && twoX < 335 && twoY == 175 && moverNumbers.contains(10)){
				twoX = 250;
				twoY = 100;
				forward2 = true;
			}



		}
		//System.out.println(twoX);
		//System.out.println(twoY);

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
				//System.out.println("Won Game");
				threeX = 25;
				wonGame = true;
				wonGameStuff(2);			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( threeX > 315 && threeX < 335 && threeY == 625 && moverNumbers.contains(4)){
				threeY = 400;
				forward3 = true;
			}
			//SNAKE FROM 43 TO 3
			if (threeX > 160 && threeX < 180 && threeY == 400 && moverNumbers.contains(1)) {
				while (threeY < 700){
					threeY++;
					forward3 = true;
					frame.repaint();
				}
				threeX = 175;
			}

			//LADDER FROM 48 TO 53
			if ( threeX > 540 && threeX < 555 && threeY == 400 && moverNumbers.contains(3)){
				while (threeY > 325){
					threeY--;
				}
				threeX = 550;
				forward3 = false;
			}

			//LADDER FROM 54 TO 62
			if (threeX > 465 && threeX < 485 && threeY == 325 && moverNumbers.contains(2)){
				threeX =400;
				threeY = 250;
				forward3 = true;
			}

			//SNAKE FROM 74 TO 35
			if (threeX > 465 && threeX < 485 && threeY == 175 && moverNumbers.contains(3)){
				threeX = 400;
				threeY = threeY + (4*75);
				forward3 = false;
			}

			//SNAKE FROM 86 to 77
			if (threeX > 390 && threeX < 405 && threeY == 100 && moverNumbers.contains(4)){
				threeX = 250;
				threeY = 175;
				forward3 = false;
			}

			//LADDER FROM 82 TO 98
			if (threeX > 85 && threeX < 115 && threeY == 100 && moverNumbers.contains(1)){
				threeX = 175;
				threeY = 25;
				forward3 = false;
			}

			//SNAKE FROM 94 TO 72
			if (threeX > 475 && threeX < 490 && threeY == 25 && moverNumbers.contains(2)){
				threeX = 625;
				threeY = 175;
				forward3 = false;
			}

			//SNAKE FROM 80 TO 1
			if (threeX > 20 && threeX < 40 && threeY == 175 && moverNumbers.contains(6)){
				threeX = 25;
				threeY = 700;
				forward3 = true;
			}

			//LADDER FROM 58 to 64
			if (threeX > 160 && threeX < 180 && threeY == 325 && moverNumbers.contains(5)){
				threeX = 250;
				threeY = 250;
				forward3 = true;
			}

			//SNAKE FROM 78 TO 27
			if (threeX > 160 && threeX < 180 && threeY == 175 && moverNumbers.contains(5)){
				threeX = 475;
				threeY = 550;
				forward3 = true;
			}

			//SNAKE FROM 55 T0 31
			if (threeX > 390 && threeX < 410 && threeY == 325 && moverNumbers.contains(7)){
				threeX =699;
				threeY = 475;
				forward3 = false;
			}

			//SNAKE FROM 97 TO 75
			if (threeX > 240 && threeX < 260 && threeY == 25 && moverNumbers.contains(8)){
				threeX = 400;
				threeY = 175;
				forward3 = false;
			}

			//SNAKE FROM 33 TO 8
			if (threeX > 535 && threeX < 565 && threeY == 475 && moverNumbers.contains(9)){
				threeX = 545;
				threeY = 700;
				forward3 = true;
			}
			//SNAKE FROM 62 TO 59
			if (threeX > 90 && threeX < 110 && threeY == 250 && moverNumbers.contains(10)){
				threeX = 105;
				threeY = 325;
				forward3 = false;
			}

			//LADDER FROM 44 TO 56
			if (threeX > 240 && threeX < 260 && threeY == 400 && moverNumbers.contains(6)){
				threeX = 325;
				threeY = 325;
				forward3 = false;
			}

			//LADDER FROM 4 TO 17
			if (threeX > 240 && threeX < 260 && threeY == 700 && moverNumbers.contains(7)){
				threeX = 250;
				threeY = 625;
				forward3 = false;
			}

			//LADDER FROM 14 TO 29
			if (threeX > 615 && threeX < 635 && threeY == 625 && moverNumbers.contains(8)){
				threeX = 625;
				threeY = 550;
				forward3 = true;
			}

			//LADDER FROM 69 TO 72
			if (threeX > 615 && threeX < 635 && threeY == 250 && moverNumbers.contains(9)){
				threeX = 625;
				threeY = 175;
				forward3 = false;
			}

			//LADDER FROM 76 TO 84
			if (threeX > 315 && threeX < 335 && threeY == 175 && moverNumbers.contains(10)){
				threeX = 250;
				threeY = 100;
				forward3 = true;
			}

		}
		//System.out.println(threeX);
		//System.out.println(threeY);

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
				//System.out.println("Won Game");
				fourX = 25;
				wonGame = true;
				wonGameStuff(3);	
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			frame.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			if (pixelCount == 0){



				//LADDER FROM 16 to 45
				if ( fourX > 315 && fourX < 335 && fourY == 625 && moverNumbers.contains(4)){
					fourY = 400;
					forward4 = true;
				}
				//SNAKE FROM 43 TO 3
				if (fourX > 160 && fourX < 180 && fourY == 400 && moverNumbers.contains(1)) {
					while (fourY < 700){
						fourY++;
						forward4 = true;
						frame.repaint();
					}
					fourX = 175;
				}

				//LADDER FROM 48 TO 53
				if ( fourX > 540 && fourX < 555 && fourY == 400 && moverNumbers.contains(3)){
					while (fourY > 325){
						fourY--;
					}
					fourX = 550;
					forward4 = false;
				}

				//LADDER FROM 54 TO 62
				if (fourX > 465 && fourX < 485 && fourY == 325 && moverNumbers.contains(2)){
					fourX =400;
					fourY = 250;
					forward4 = true;
				}

				//SNAKE FROM 74 TO 35
				if (fourX > 465 && fourX < 485 && fourY == 175 && moverNumbers.contains(3)){
					fourX = 400;
					fourY = fourY + (4*75);
					forward4 = false;
				}

				//SNAKE FROM 86 to 77
				if (fourX > 390 && fourX < 405 && fourY == 100 && moverNumbers.contains(4)){
					fourX = 250;
					fourY = 175;
					forward4 = false;
				}

				//LADDER FROM 82 TO 98
				if (fourX > 85 && fourX < 115 && fourY == 100 && moverNumbers.contains(1)){
					fourX = 175;
					fourY = 25;
					forward4 = false;
				}

				//SNAKE FROM 94 TO 72
				if (fourX > 475 && fourX < 490 && fourY == 25 && moverNumbers.contains(2)){
					fourX = 625;
					fourY = 175;
					forward4 = false;
				}

				//SNAKE FROM 80 TO 1
				if (fourX > 20 && fourX < 40 && fourY == 175 && moverNumbers.contains(6)){
					fourX = 25;
					fourY = 700;
					forward4 = true;
				}

				//LADDER FROM 58 to 64
				if (fourX > 160 && fourX < 180 && fourY == 325 && moverNumbers.contains(5)){
					fourX = 250;
					fourY = 250;
					forward4 = true;
				}

				//SNAKE FROM 78 TO 27
				if (fourX > 160 && fourX < 180 && fourY == 175 && moverNumbers.contains(5)){
					fourX = 475;
					fourY = 550;
					forward4 = true;
				}

				//SNAKE FROM 55 T0 31
				if (fourX > 390 && fourX < 410 && fourY == 325 && moverNumbers.contains(7)){
					fourX =699;
					fourY = 475;
					forward4 = false;
				}

				//SNAKE FROM 97 TO 75
				if (fourX > 240 && fourX < 260 && fourY == 25 && moverNumbers.contains(8)){
					fourX = 400;
					fourY = 175;
					forward4 = false;
				}

				//SNAKE FROM 33 TO 8
				if (fourX > 535 && fourX < 565 && fourY == 475 && moverNumbers.contains(9)){
					fourX = 545;
					fourY = 700;
					forward4 = true;
				}
				//SNAKE FROM 62 TO 59
				if (fourX > 90 && fourX < 110 && fourY == 250 && moverNumbers.contains(10)){
					fourX = 105;
					fourY = 325;
					forward4 = false;
				}

				//LADDER FROM 44 TO 56
				if (fourX > 240 && fourX < 260 && fourY == 400 && moverNumbers.contains(6)){
					fourX = 325;
					fourY = 325;
					forward4 = false;
				}
				//LADDER FROM 4 TO 17
				if (fourX > 240 && fourX < 260 && fourY == 700 && moverNumbers.contains(7)){
					fourX = 250;
					fourY = 625;
					forward4 = false;
				}

				//LADDER FROM 14 TO 29
				if (fourX > 615 && fourX < 635 && fourY == 625 && moverNumbers.contains(8)){
					fourX = 625;
					fourY = 550;
					forward4 = true;
				}

				//LADDER FROM 69 TO 72
				if (fourX > 615 && fourX < 635 && fourY == 250 && moverNumbers.contains(9)){
					fourX = 625;
					fourY = 175;
					forward4 = false;
				}

				//LADDER FROM 76 TO 84
				if (fourX > 315 && fourX < 335 && fourY == 175 && moverNumbers.contains(10)){
					fourX = 250;
					fourY = 100;
					forward4 = true;
				}


			}
			//System.out.println(fourX);
			//System.out.println(fourY);
		}


	}
	public void wonGameStuff(int PlayerInt){
		//Winning Stuff
		java.awt.Image winGif = new ImageIcon (this.getClass().getResource("/funnyWin.gif")).getImage();
		diceLbl.setIcon(new ImageIcon(winGif));
		diceLbl.setBounds(100,25,300,300);
		rollBtn.setVisible(false);
		nameLbl.setBounds(100,0,500,50);
		nameLbl.setText( playersList.get(PlayerInt).getName() + " WINS!!!");
		nameLbl.setFont(new Font(Font.SANS_SERIF, 32, 50));
		timeLbl.setVisible(false);
		frame2.setSize(500,350);
		frame2.setLocation(600, 250);
		frame2.setBackground(Color.LIGHT_GRAY);
	}

}
