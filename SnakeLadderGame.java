import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
public class SnakeLadderGame {


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

	JFrame m_snakeLadderBoard;
	JFrame m_snakeLadderMenu;
	DrawPanel m_snakeLadderGraphics;
	JButton m_diceRollBtn;
	JLabel m_diceRollLbl;
	JLabel m_currentPlayerNameLbl;
	LabelTimer m_timerLbl;

	//Counter position variables
	//All start at same value so they start at square 1
	private int m_counterOneXPos = 25;
	private int m_counterOneYPos = 700;
	boolean m_moveUpOne = false;
	boolean m_moveAcrossOne = true;
	boolean m_moveRightOne = true;

	private int m_counterTwoXPos = 25;
	private int m_counterTwoYPos = 700;
	private boolean m_moveUpTwo = false;
	private boolean m_moveAcrossTwo = true;
	private boolean m_moveRightTwo = true;

	private int m_counterThreeXPos = 25;
	private int m_counterThreeYPos = 700;
	private boolean m_moveUpThree = false;
	private boolean m_moveAcrossThree = true;
	private boolean m_moveRightThree = true;

	private int m_counterFourXPos = 25;
	private int m_counterFourYPos = 700;
	private boolean m_moveUpFour = false;
	private boolean m_moveAcrossFour = true;
	private boolean m_moveRightFour = true;


	boolean m_gameWon = false;

	private int m_playerTurn = 0;
	//private static String imageLocation;
	private int m_numberOfPlayers;
	Dice m_dice = new Dice();
	ArrayList<Player> m_listOfPlayers;
	ArrayList<Integer> m_moversSelected;

	//snakeLadderBoard dimensions and location
	private final int m_snakeLadderBoardSizeX = 755;
	private final int m_snakeLadderBoardSizeY = 775;
	private final int m_snakeLadderBoardLocX = 500;
	private final int m_snakeLadderBoardLocY = 0;

	private  int m_snakeLadderMenuSizeX = 300;
	private  int m_snakeLadderMenuSizeY = 100;
	private  int m_snakeLadderMenuLocX = 200;
	private  int m_snakeLadderMenuLocY = 350;

	private int m_diceRollBtnLocX =0;
	private int m_diceRollBtnLocY = 0;
	private int m_diceRollBtnWidth = 75;
	private int m_diceRollBtnHeight = 75;

	private int m_diceRollLblLocX =100;
	private int m_diceRollLblLocY = 0;
	private int m_diceRollLblWidth = 75;
	private int m_diceRollLblHeight = 75;

	private int m_currentPlayerLblLocX =180;
	private int m_currentPlayerLblLocY = 0;
	private int m_currentPlayerLblWidth = 200;
	private int m_currentPlayerLblHeight = 50;





	//gets all this info from game launcher
	public SnakeLadderGame(int numberOfMovers, ArrayList<Player> players){
		m_listOfPlayers = players;
		m_numberOfPlayers = m_listOfPlayers.size();
		Mover moverArray = new Mover(numberOfMovers);
		m_moversSelected = moverArray.getMoverArrayList();
		go();

	}

	private void go() {

		m_snakeLadderBoard = new JFrame("Snakes And Ladders Board");
		m_snakeLadderBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_snakeLadderGraphics = new DrawPanel();
		m_snakeLadderBoard.getContentPane().add(m_snakeLadderGraphics);
		m_snakeLadderBoard.setVisible(true);
		m_snakeLadderBoard.setSize(m_snakeLadderBoardSizeX, m_snakeLadderBoardSizeY);
		m_snakeLadderBoard.setLocation(m_snakeLadderBoardLocX,m_snakeLadderBoardLocY);
		m_snakeLadderMenu = new JFrame("MENU");
		m_snakeLadderMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_snakeLadderMenu.setVisible(true);
		m_snakeLadderMenu.setLayout(null);
		m_snakeLadderMenu.setSize(m_snakeLadderMenuSizeX, m_snakeLadderMenuSizeY);
		m_snakeLadderMenu.setLocation(m_snakeLadderMenuLocX,m_snakeLadderMenuLocY);
		m_diceRollBtn = new JButton("Start Roll");
		m_diceRollBtn.setBackground(Color.RED);
		m_diceRollBtn.setBounds(m_diceRollBtnLocX, m_diceRollBtnLocY, m_diceRollBtnWidth, m_diceRollBtnHeight);
		m_snakeLadderMenu.add(m_diceRollBtn);
		m_diceRollLbl = new JLabel();
		m_diceRollLbl.setBounds(m_diceRollLblLocX, m_diceRollLblLocY, m_diceRollLblWidth, m_diceRollLblHeight);
		java.awt.Image startDiceImage = new ImageIcon (this.getClass().getResource("/1dice.gif")).getImage();
		m_diceRollLbl.setIcon(new ImageIcon(startDiceImage));
		m_snakeLadderMenu.add(m_diceRollLbl);
		m_currentPlayerNameLbl = new JLabel();
		m_currentPlayerNameLbl.setBounds(m_currentPlayerLblLocX,m_currentPlayerLblLocY,m_currentPlayerLblWidth,m_currentPlayerLblHeight);
		m_timerLbl = new LabelTimer();
		m_timerLbl.setBounds(180,10,80,100);
		m_timerLbl.timerStart();
		m_snakeLadderMenu.add(m_timerLbl);
		m_snakeLadderMenu.add(m_currentPlayerNameLbl);
		if(m_currentPlayerNameLbl.getText().equals("")){
			m_currentPlayerNameLbl.setText("Turn: " + m_listOfPlayers.get(0).getName());
			m_currentPlayerNameLbl.setForeground(m_listOfPlayers.get(0).getColor());
		}

		m_diceRollBtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){
				if (m_gameWon == false){
					if (i % 2 == 0){
						m_diceRollBtn.setText("End Roll");
						java.awt.Image gif = new ImageIcon (this.getClass().getResource("/diceBig.gif")).getImage();
						m_diceRollLbl.setIcon(new ImageIcon(gif));
						i = i + 1;
						if(m_playerTurn < m_numberOfPlayers){
							m_currentPlayerNameLbl.setText("Turn: " + m_listOfPlayers.get(m_playerTurn).getName());
							m_currentPlayerNameLbl.setForeground(m_listOfPlayers.get(m_playerTurn).getColor());
						}
						else{
							m_currentPlayerNameLbl.setText("Turn: " + m_listOfPlayers.get(0).getName());
							m_currentPlayerNameLbl.setForeground(m_listOfPlayers.get(0).getColor());						
						}
					}else{
						m_diceRollBtn.setText("Start Roll");
						String diceRollImageName = Dice.getNewRoll() +"dice.gif";
						java.awt.Image diceRollImage = new ImageIcon (this.getClass().getResource(diceRollImageName)).getImage();
						m_diceRollLbl.setIcon(new ImageIcon(diceRollImage));
						i = i -1;
						Thread one = new Thread() {
							public void run() {
								if (m_numberOfPlayers == m_playerTurn){
									m_playerTurn = 0;
								}if (m_playerTurn == 3 && m_numberOfPlayers > m_playerTurn){
									moveIt4(Dice.getPrevValue());
									m_playerTurn = 0;
								}else if (m_playerTurn == 0 && m_numberOfPlayers > m_playerTurn){
									moveIt(Dice.getPrevValue());
									m_playerTurn ++;
								}else if (m_playerTurn == 2 && m_numberOfPlayers > m_playerTurn){
									moveIt3(Dice.getPrevValue());
									m_playerTurn++;
								}else if (m_playerTurn == 1 && m_numberOfPlayers > m_playerTurn){
									moveIt2(Dice.getPrevValue());
									m_playerTurn ++;
								}}};
								one.start();}}}});}

	public int getRandNum(){
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	class DrawPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			//paints oval   


			//player 2
			if (m_numberOfPlayers >= 2){
				g.setColor(m_listOfPlayers.get(1).getColor());
				g.fillOval(m_counterTwoXPos, m_counterTwoYPos, 20, 20);
			}
			//player 3
			if (m_numberOfPlayers >= 3){
				g.setColor(m_listOfPlayers.get(2).getColor());
				g.fillOval(m_counterThreeXPos, m_counterThreeYPos, 20, 20);
			}
			//player 4
			if (m_numberOfPlayers >= 4){
				g.setColor(m_listOfPlayers.get(3).getColor());
				g.fillOval(m_counterFourXPos, m_counterFourYPos, 20, 20);
				//paints board lines
			}

			//Player 1

			g.setColor(m_listOfPlayers.get(0).getColor());
			g.fillOval(m_counterOneXPos, m_counterOneYPos, 20, 20);

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
			if (m_moversSelected.contains(1))
			{
				m_ladderOne = new AffineTransform();
				m_ladderOne.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderOne.translate(m_ladderOneTranslateX, m_ladderOneTranslateY);
				m_ladderOne.rotate( m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderOne, this);
			}


			//54 to 66
			if (m_moversSelected.contains(2)){
				m_ladderTwo = new AffineTransform();            	    	
				m_ladderTwo.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderTwo.translate(m_ladderTwoTranslateX, m_ladderTwoTranslateY );
				m_ladderTwo.rotate( m_ladderRotationLeft );
				g2d.drawImage(ladderImage, m_ladderTwo, this);
			}

			if (m_moversSelected.contains(3)){

				//48 to 53
				m_ladderThree = new AffineTransform();            	    	
				m_ladderThree.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderThree.translate(m_ladderThreeTranslateX, m_ladderThreeTranslateY );
				m_ladderThree.rotate(m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderThree, this);
			}


			if (m_moversSelected.contains(4)){
				//16 to 45
				m_ladderFour = new AffineTransform();            	    	
				m_ladderFour.scale(m_ladderSmallScalar, m_ladderLargeScalar); 
				m_ladderFour.translate(m_ladderFourTranslateX, m_ladderFourTranslateY );
				m_ladderFour.rotate(m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderFour, this);
			}

			if (m_moversSelected.contains(5)){
				//58 to 64
				m_ladderFive = new AffineTransform();
				m_ladderFive.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderFive.translate(m_ladderFiveTranslateX, m_ladderFiveTranslateY );
				m_ladderFive.rotate( m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderFive, this);
			}

			if (m_moversSelected.contains(6)){
				//44 TO 56
				m_ladderSix = new AffineTransform();
				m_ladderSix.scale(m_ladderSmallScalar,m_ladderSmallScalar); 
				m_ladderSix.translate(m_ladderSixTranslateX, m_ladderSixTranslateY );
				m_ladderSix.rotate(m_ladderRotationRight);
				g2d.drawImage(ladderImage, m_ladderSix, this);
			}

			if (m_moversSelected.contains(7)){
				//4 to 17
				m_ladderSeven = new AffineTransform();
				m_ladderSeven.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderSeven.translate(m_ladderSevenTranslateX, m_ladderSevenTranslateY );
				m_ladderSeven.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderSeven, this);
			}

			if (m_moversSelected.contains(8)){
				//12 TO 29
				m_ladderEight = new AffineTransform();
				m_ladderEight.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderEight.translate(m_ladderEightTranslateX, m_ladderEightTranslateY );
				m_ladderEight.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderEight, this);
			}

			if (m_moversSelected.contains(9)){
				//69 TO 72
				m_ladderNine = new AffineTransform();
				m_ladderNine.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderNine.translate(m_ladderNineTranslateX, m_ladderNineTranslateY );
				m_ladderNine.rotate( m_ladderRotationUpright);
				g2d.drawImage(ladderImage, m_ladderNine, this);
			}

			if (m_moversSelected.contains(10)){
				//76 TO 84
				m_ladderTen = new AffineTransform();
				m_ladderTen.scale(m_ladderSmallScalar, m_ladderSmallScalar); 
				m_ladderTen.translate(m_ladderTenTranslateX, m_ladderTenTranslateY  );
				m_ladderTen.rotate( m_ladderRotationLeft);
				g2d.drawImage(ladderImage, m_ladderTen, this);
			}

			//snake creation




			if (m_moversSelected.contains(1)){
				//43 to 3
				m_SnakeOne = new AffineTransform();
				m_SnakeOne.scale(m_snakeLargeScalar,m_snakeLargeScalar); 
				m_SnakeOne.translate(m_snakeOneTranslateX, m_snakeOneTranslateY);
				m_SnakeOne.rotate( m_snakeRotationUpright);
				g2d.drawImage(snakeImage, m_SnakeOne, this);
			}

			if (m_moversSelected.contains(2)){
				//94 to 72
				m_SnakeTwo = new AffineTransform();            	    	
				m_SnakeTwo.scale(m_snakeSmallerScalar, m_snakeSmallerScalar); 
				m_SnakeTwo.translate(m_snakeTwoTranslateX, m_snakeTwoTranslateY );
				m_SnakeTwo.rotate(m_snakeRotationSlightRight);
				g2d.drawImage(snakeImage, m_SnakeTwo, this);
			}

			if (m_moversSelected.contains(3)){
				//74 to 35
				m_SnakeThree = new AffineTransform();            	    	
				m_SnakeThree.scale(m_snakeLargeScalar, m_snakeLargeScalar); 
				m_SnakeThree.translate(m_snakeThreeTranslateX, m_snakeThreeTranslateY );
				m_SnakeThree.rotate(m_snakeRotationSlightestRight );
				g2d.drawImage(snakeImage, m_SnakeThree, this);
			}

			if (m_moversSelected.contains(4)){
				//86 to 77
				m_SnakeFour = new AffineTransform();            	    	
				m_SnakeFour.scale(m_snakeSmallerScalar, m_snakeSmallestScalar);  
				m_SnakeFour.translate(m_snakeFourTranslateX, m_snakeFourTranslateY);
				m_SnakeFour.rotate( m_snakeRotationHardLeft );
				g2d.drawImage(snakeImage, m_SnakeFour, this);
			}

			if (m_moversSelected.contains(5)){
				//78 to 27
				m_SnakeFive = new AffineTransform();            	    	
				m_SnakeFive.scale(m_snakeLargerScalar, m_snakeLargestScalar);  
				m_SnakeFive.translate(m_snakeFiveTranslateX, m_snakeFiveTranslateY );
				m_SnakeFive.rotate( m_snakeRotationSlightRight );
				g2d.drawImage(snakeImage, m_SnakeFive, this);
			}

			if (m_moversSelected.contains(6)){
				//80 to 1
				m_SnakeSix = new AffineTransform();            	    	
				m_SnakeSix.scale(m_snakeLargerScalar, m_snakeLargestScalar);  
				m_SnakeSix.translate(m_snakeSixTranslateX, m_snakeSixTranslateY );
				m_SnakeSix.rotate( m_snakeRotationUpright );
				g2d.drawImage(snakeImage, m_SnakeSix, this);
			}

			if (m_moversSelected.contains(7)){
				//55 to 31			
				m_SnakeSeven = new AffineTransform();            	    	
				m_SnakeSeven.scale(m_snakeLargerScalar, m_snakeSmallerScalar);  
				m_SnakeSeven.translate(m_snakeSevenTranslateX, m_snakeSevenTranslateY );
				m_SnakeSeven.rotate(m_snakeRotationHardRight);
				g2d.drawImage(snakeImage, m_SnakeSeven, this);
			}

			if (m_moversSelected.contains(8)){
				//97 to 75
				m_SnakeEight = new AffineTransform();            	    	
				m_SnakeEight.scale(m_snakeSmallerScalar,m_snakeSmallerScalar); 
				m_SnakeEight.translate(m_snakeEightTranslateX, m_snakeEightTranslateY );
				m_SnakeEight.rotate( m_snakeRotationSlightRight );
				g2d.drawImage(snakeImage, m_SnakeEight, this);
			}

			if (m_moversSelected.contains(9)){
				//33 to 8
				m_SnakeNine = new AffineTransform();            	    	
				m_SnakeNine.scale(m_snakeSmallScalar, m_snakeSmallerScalar); 
				m_SnakeNine.translate(m_snakeNineTranslateX, m_snakeNineTranslateY );
				m_SnakeNine.rotate( m_snakeRotationUpright );
				g2d.drawImage(snakeImage, m_SnakeNine, this);
			}

			if (m_moversSelected.contains(10)){
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
		while(pixelCount > 0 && m_gameWon == false){
			if(m_counterOneXPos == 700){
				m_moveAcrossOne = false;
				m_moveUpOne = true;
				m_moveRightOne = false;
				m_counterOneYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();
			}
			if(m_counterOneXPos == 24){
				m_moveAcrossOne = false;
				m_moveUpOne = true;
				m_moveRightOne = true;
				m_counterOneYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();
			}
			if ((m_counterOneYPos- 700) % 75 == 0) {
				m_moveAcrossOne = true;
				m_moveUpOne = false;
			}
			if(m_moveUpOne) {
				m_counterOneYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();
			}  
			if(m_moveAcrossOne && m_moveRightOne && m_moveUpOne == false){
				m_counterOneXPos++;  
				pixelCount --;
				m_snakeLadderBoard.repaint();

			} 

			if(m_moveAcrossOne && m_moveRightOne == false && m_moveUpOne == false){
				m_counterOneXPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( m_counterOneXPos > 20 && m_counterOneXPos < 45 && m_counterOneYPos == 25 ){
				//System.out.println("Won Game");
				m_counterOneXPos = 25;
				//timeLbl.timerStop();
				m_gameWon = true;

				wonGameStuff(0);
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			m_snakeLadderBoard.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( m_counterOneXPos > 315 && m_counterOneXPos < 335 && m_counterOneYPos == 625 && m_moversSelected.contains(4)){
				m_counterOneYPos = 400;
				m_moveRightOne = true;
			}
			//SNAKE FROM 43 TO 3
			if (m_counterOneXPos > 160 && m_counterOneXPos < 180 && m_counterOneYPos == 400 && m_moversSelected.contains(1)) {
				while (m_counterOneYPos < 700){
					m_counterOneYPos++;
					m_moveRightOne = true;
					m_snakeLadderBoard.repaint();
				}
				m_counterOneXPos = 175;
			}

			//LADDER FROM 48 TO 53
			if ( m_counterOneXPos > 540 && m_counterOneXPos < 555 && m_counterOneYPos == 400 && m_moversSelected.contains(3)){
				while (m_counterOneYPos > 325){
					m_counterOneYPos--;
				}
				m_counterOneXPos = 550;
				m_moveRightOne = false;
			}

			//LADDER FROM 54 TO 62
			if (m_counterOneXPos > 465 && m_counterOneXPos < 485 && m_counterOneYPos == 325 && m_moversSelected.contains(2)){
				m_counterOneXPos =400;
				m_counterOneYPos = 250;
				m_moveRightOne = true;
			}

			//SNAKE FROM 74 TO 35
			if (m_counterOneXPos > 465 && m_counterOneXPos < 485 && m_counterOneYPos == 175 && m_moversSelected.contains(3)){
				m_counterOneXPos = 400;
				m_counterOneYPos = m_counterOneYPos + (4*75);
				m_moveRightOne = false;
			}

			//SNAKE FROM 86 to 77
			if (m_counterOneXPos > 390 && m_counterOneXPos < 405 && m_counterOneYPos == 100 && m_moversSelected.contains(4)){
				m_counterOneXPos = 250;
				m_counterOneYPos = 175;
				m_moveRightOne = false;
			}

			//LADDER FROM 82 TO 98
			if (m_counterOneXPos > 85 && m_counterOneXPos < 115 && m_counterOneYPos == 100 && m_moversSelected.contains(1)){
				m_counterOneXPos = 175;
				m_counterOneYPos = 25;
				m_moveRightOne = false;
			}

			//SNAKE FROM 94 TO 72
			if (m_counterOneXPos > 475 && m_counterOneXPos < 490 && m_counterOneYPos == 25 && m_moversSelected.contains(2)){
				m_counterOneXPos = 625;
				m_counterOneYPos = 175;
				m_moveRightOne = false;
			}

			//SNAKE FROM 80 TO 1
			if (m_counterOneXPos > 20 && m_counterOneXPos < 40 && m_counterOneYPos == 175 && m_moversSelected.contains(6)){
				m_counterOneXPos = 25;
				m_counterOneYPos = 700;
				m_moveRightOne = true;
			}

			//LADDER FROM 58 to 64
			if (m_counterOneXPos > 160 && m_counterOneXPos < 180 && m_counterOneYPos == 325 && m_moversSelected.contains(5)){
				m_counterOneXPos = 250;
				m_counterOneYPos = 250;
				m_moveRightOne = true;
			}

			//SNAKE FROM 78 TO 27
			if (m_counterOneXPos > 160 && m_counterOneXPos < 180 && m_counterOneYPos == 175 && m_moversSelected.contains(5)){
				m_counterOneXPos = 475;
				m_counterOneYPos = 550;
				m_moveRightOne = true;
			}

			//SNAKE FROM 55 T0 31
			if (m_counterOneXPos > 390 && m_counterOneXPos < 410 && m_counterOneYPos == 325 && m_moversSelected.contains(7)){
				m_counterOneXPos =699;
				m_counterOneYPos = 475;
				m_moveRightOne = false;
			}

			//SNAKE FROM 97 TO 75
			if (m_counterOneXPos > 240 && m_counterOneXPos < 260 && m_counterOneYPos == 25 && m_moversSelected.contains(8)){
				m_counterOneXPos = 400;
				m_counterOneYPos = 175;
				m_moveRightOne = false;
			}

			//SNAKE FROM 33 TO 8
			if (m_counterOneXPos > 535 && m_counterOneXPos < 565 && m_counterOneYPos == 475 && m_moversSelected.contains(9)){
				m_counterOneXPos = 545;
				m_counterOneYPos = 700;
				m_moveRightOne = true;
			}
			//SNAKE FROM 62 TO 59
			if (m_counterOneXPos > 90 && m_counterOneXPos < 110 && m_counterOneYPos == 250 && m_moversSelected.contains(10)){
				m_counterOneXPos = 105;
				m_counterOneYPos = 325;
				m_moveRightOne = false;
			}
			//LADDER FROM 44 TO 56
			if (m_counterOneXPos > 240 && m_counterOneXPos < 260 && m_counterOneYPos == 400 && m_moversSelected.contains(6)){
				m_counterOneXPos = 325;
				m_counterOneYPos = 325;
				m_moveRightOne = false;
			}

			//LADDER FROM 4 TO 17
			if (m_counterOneXPos > 240 && m_counterOneXPos < 260 && m_counterOneYPos == 700 && m_moversSelected.contains(7)){
				m_counterOneXPos = 250;
				m_counterOneYPos = 625;
				m_moveRightOne = false;
			}

			//LADDER FROM 14 TO 29
			if (m_counterOneXPos > 615 && m_counterOneXPos < 635 && m_counterOneYPos == 625 && m_moversSelected.contains(8)){
				m_counterOneXPos = 625;
				m_counterOneYPos = 550;
				m_moveRightOne = true;
			}

			//LADDER FROM 69 TO 72
			if (m_counterOneXPos > 615 && m_counterOneXPos < 635 && m_counterOneYPos == 250 && m_moversSelected.contains(9)){
				m_counterOneXPos = 625;
				m_counterOneYPos = 175;
				m_moveRightOne = false;
			}

			//LADDER FROM 76 TO 84
			if (m_counterOneXPos > 315 && m_counterOneXPos < 335 && m_counterOneYPos == 175 && m_moversSelected.contains(10)){
				m_counterOneXPos = 250;
				m_counterOneYPos = 100;
				m_moveRightOne = true;
			}


		}
		//System.out.println(oneX);
		//System.out.println(oneY);

	}

	private void moveIt2(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && m_gameWon == false){
			if(m_counterTwoXPos == 700){
				m_moveAcrossTwo = false;
				m_moveUpTwo = true;
				m_moveRightTwo = false;
				m_counterTwoYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}
			if(m_counterTwoXPos == 24){
				m_moveAcrossTwo = false;
				m_moveUpTwo = true;
				m_moveRightTwo = true;
				m_counterTwoYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			if ((m_counterTwoYPos- 700) % 75 == 0) {
				m_moveAcrossTwo = true;
				m_moveUpTwo = false;

			}

			if( m_moveUpTwo) {
				m_counterTwoYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}  

			if(m_moveAcrossTwo && m_moveRightTwo &&  m_moveUpTwo == false){
				m_counterTwoXPos++;  
				pixelCount --;
				m_snakeLadderBoard.repaint();

			} 

			if(m_moveAcrossTwo && m_moveRightTwo == false &&  m_moveUpTwo == false){
				m_counterTwoXPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( m_counterTwoXPos > 20 && m_counterTwoXPos < 45 && m_counterTwoYPos == 25 ){
				//System.out.println("Won Game");
				m_counterTwoXPos = 25;
				m_gameWon = true;
				//timeLbl.timerStop();
				wonGameStuff(1);
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			m_snakeLadderBoard.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( m_counterTwoXPos > 315 && m_counterTwoXPos < 335 && m_counterTwoYPos == 625 && m_moversSelected.contains(4)){
				m_counterTwoYPos = 400;
				m_moveRightTwo = true;
			}
			//SNAKE FROM 43 TO 3
			if (m_counterTwoXPos > 160 && m_counterTwoXPos < 180 && m_counterTwoYPos == 400 && m_moversSelected.contains(1)) {
				while (m_counterTwoYPos < 700){
					m_counterTwoYPos++;
					m_moveRightTwo = true;
					m_snakeLadderBoard.repaint();
				}
				m_counterTwoXPos = 175;
			}

			//LADDER FROM 48 TO 53
			if ( m_counterTwoXPos > 540 && m_counterTwoXPos < 555 && m_counterTwoYPos == 400 && m_moversSelected.contains(3)){
				while (m_counterTwoYPos > 325){
					m_counterTwoYPos--;
				}
				m_counterTwoXPos = 550;
				m_moveRightTwo = false;
			}

			//LADDER FROM 54 TO 62
			if (m_counterTwoXPos > 465 && m_counterTwoXPos < 485 && m_counterTwoYPos == 325 && m_moversSelected.contains(2)){
				m_counterTwoXPos =400;
				m_counterTwoYPos = 250;
				m_moveRightTwo = true;
			}

			//SNAKE FROM 74 TO 35
			if (m_counterTwoXPos > 465 && m_counterTwoXPos < 485 && m_counterTwoYPos == 175 && m_moversSelected.contains(3)){
				m_counterTwoXPos = 400;
				m_counterTwoYPos = m_counterTwoYPos + (4*75);
				m_moveRightTwo = false;
			}

			//SNAKE FROM 86 to 77
			if (m_counterTwoXPos > 390 && m_counterTwoXPos < 405 && m_counterTwoYPos == 100 && m_moversSelected.contains(4)){
				m_counterTwoXPos = 250;
				m_counterTwoYPos = 175;
				m_moveRightTwo = false;
			}

			//LADDER FROM 82 TO 98
			if (m_counterTwoXPos > 85 && m_counterTwoXPos < 115 && m_counterTwoYPos == 100 && m_moversSelected.contains(1)){
				m_counterTwoXPos = 175;
				m_counterTwoYPos = 25;
				m_moveRightTwo = false;
			}

			//SNAKE FROM 94 TO 72
			if (m_counterTwoXPos > 475 && m_counterTwoXPos < 490 && m_counterTwoYPos == 25 && m_moversSelected.contains(2)){
				m_counterTwoXPos = 625;
				m_counterTwoYPos = 175;
				m_moveRightTwo = false;
			}

			//SNAKE FROM 80 TO 1
			if (m_counterTwoXPos > 20 && m_counterTwoXPos < 40 && m_counterTwoYPos == 175 && m_moversSelected.contains(6)){
				m_counterTwoXPos = 25;
				m_counterTwoYPos = 700;
				m_moveRightTwo = true;
			}

			//LADDER FROM 58 to 64
			if (m_counterTwoXPos > 160 && m_counterTwoXPos < 180 && m_counterTwoYPos == 325 && m_moversSelected.contains(5)){
				m_counterTwoXPos = 250;
				m_counterTwoYPos = 250;
				m_moveRightTwo = true;
			}

			//SNAKE FROM 78 TO 27
			if (m_counterTwoXPos > 160 && m_counterTwoXPos < 180 && m_counterTwoYPos == 175 && m_moversSelected.contains(5)){
				m_counterTwoXPos = 475;
				m_counterTwoYPos = 550;
				m_moveRightTwo = true;
			}

			//SNAKE FROM 55 T0 31
			if (m_counterTwoXPos > 390 && m_counterTwoXPos < 410 && m_counterTwoYPos == 325 && m_moversSelected.contains(7)){
				m_counterTwoXPos =699;
				m_counterTwoYPos = 475;
				m_moveRightTwo = false;
			}

			//SNAKE FROM 97 TO 75
			if (m_counterTwoXPos > 240 && m_counterTwoXPos < 260 && m_counterTwoYPos == 25 && m_moversSelected.contains(8)){
				m_counterTwoXPos = 400;
				m_counterTwoYPos = 175;
				m_moveRightTwo = false;
			}

			//SNAKE FROM 33 TO 8
			if (m_counterTwoXPos > 535 && m_counterTwoXPos < 565 && m_counterTwoYPos == 475 && m_moversSelected.contains(9)){
				m_counterTwoXPos = 545;
				m_counterTwoYPos = 700;
				m_moveRightTwo = true;
			}
			//SNAKE FROM 62 TO 59
			if (m_counterTwoXPos > 90 && m_counterTwoXPos < 110 && m_counterTwoYPos == 250 && m_moversSelected.contains(10)){
				m_counterTwoXPos = 105;
				m_counterTwoYPos = 325;
				m_moveRightTwo = false;
			}

			//LADDER FROM 44 TO 56
			if (m_counterTwoXPos > 240 && m_counterTwoXPos < 260 && m_counterTwoYPos == 400 && m_moversSelected.contains(6)){
				m_counterTwoXPos = 325;
				m_counterTwoYPos = 325;
				m_moveRightTwo = false;
			}

			//LADDER FROM 4 TO 17
			if (m_counterTwoXPos > 240 && m_counterTwoXPos < 260 && m_counterTwoYPos == 700 && m_moversSelected.contains(7)){
				m_counterTwoXPos = 250;
				m_counterTwoYPos = 625;
				m_moveRightTwo = false;
			}

			//LADDER FROM 14 TO 29
			if (m_counterTwoXPos > 615 && m_counterTwoXPos < 635 && m_counterTwoYPos == 625 && m_moversSelected.contains(8)){
				m_counterTwoXPos = 625;
				m_counterTwoYPos = 550;
				m_moveRightTwo = true;
			}

			//LADDER FROM 69 TO 72
			if (m_counterTwoXPos > 615 && m_counterTwoXPos < 635 && m_counterTwoYPos == 250 && m_moversSelected.contains(9)){
				m_counterTwoXPos = 625;
				m_counterTwoYPos = 175;
				m_moveRightTwo = false;
			}

			//LADDER FROM 76 TO 84
			if (m_counterTwoXPos > 315 && m_counterTwoXPos < 335 && m_counterTwoYPos == 175 && m_moversSelected.contains(10)){
				m_counterTwoXPos = 250;
				m_counterTwoYPos = 100;
				m_moveRightTwo = true;
			}



		}
		//System.out.println(twoX);
		//System.out.println(twoY);

	}

	private void moveIt3(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && m_gameWon == false){
			if(m_counterThreeXPos == 700){
				m_moveAcrossThree = false;
				m_moveUpThree = true;
				m_moveRightThree = false;
				m_counterThreeYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}
			if(m_counterThreeXPos == 24){
				m_moveAcrossThree = false;
				m_moveUpThree = true;
				m_moveRightThree = true;
				m_counterThreeYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			if ((m_counterThreeYPos- 700) % 75 == 0) {
				m_moveAcrossThree = true;
				m_moveUpThree = false;

			}

			if(m_moveUpThree) {
				m_counterThreeYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}  

			if(m_moveAcrossThree && m_moveRightThree && m_moveUpThree == false){
				m_counterThreeXPos++;  
				pixelCount --;
				m_snakeLadderBoard.repaint();

			} 

			if(m_moveAcrossThree && m_moveRightThree == false && m_moveUpThree == false){
				m_counterThreeXPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( m_counterThreeXPos > 20 && m_counterThreeXPos < 45 && m_counterThreeYPos == 25 ){
				//System.out.println("Won Game");
				m_counterThreeXPos = 25;
				m_gameWon = true;
				wonGameStuff(2);			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			m_snakeLadderBoard.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			//LADDER FROM 16 to 45
			if ( m_counterThreeXPos > 315 && m_counterThreeXPos < 335 && m_counterThreeYPos == 625 && m_moversSelected.contains(4)){
				m_counterThreeYPos = 400;
				m_moveRightThree = true;
			}
			//SNAKE FROM 43 TO 3
			if (m_counterThreeXPos > 160 && m_counterThreeXPos < 180 && m_counterThreeYPos == 400 && m_moversSelected.contains(1)) {
				while (m_counterThreeYPos < 700){
					m_counterThreeYPos++;
					m_moveRightThree = true;
					m_snakeLadderBoard.repaint();
				}
				m_counterThreeXPos = 175;
			}

			//LADDER FROM 48 TO 53
			if ( m_counterThreeXPos > 540 && m_counterThreeXPos < 555 && m_counterThreeYPos == 400 && m_moversSelected.contains(3)){
				while (m_counterThreeYPos > 325){
					m_counterThreeYPos--;
				}
				m_counterThreeXPos = 550;
				m_moveRightThree = false;
			}

			//LADDER FROM 54 TO 62
			if (m_counterThreeXPos > 465 && m_counterThreeXPos < 485 && m_counterThreeYPos == 325 && m_moversSelected.contains(2)){
				m_counterThreeXPos =400;
				m_counterThreeYPos = 250;
				m_moveRightThree = true;
			}

			//SNAKE FROM 74 TO 35
			if (m_counterThreeXPos > 465 && m_counterThreeXPos < 485 && m_counterThreeYPos == 175 && m_moversSelected.contains(3)){
				m_counterThreeXPos = 400;
				m_counterThreeYPos = m_counterThreeYPos + (4*75);
				m_moveRightThree = false;
			}

			//SNAKE FROM 86 to 77
			if (m_counterThreeXPos > 390 && m_counterThreeXPos < 405 && m_counterThreeYPos == 100 && m_moversSelected.contains(4)){
				m_counterThreeXPos = 250;
				m_counterThreeYPos = 175;
				m_moveRightThree = false;
			}

			//LADDER FROM 82 TO 98
			if (m_counterThreeXPos > 85 && m_counterThreeXPos < 115 && m_counterThreeYPos == 100 && m_moversSelected.contains(1)){
				m_counterThreeXPos = 175;
				m_counterThreeYPos = 25;
				m_moveRightThree = false;
			}

			//SNAKE FROM 94 TO 72
			if (m_counterThreeXPos > 475 && m_counterThreeXPos < 490 && m_counterThreeYPos == 25 && m_moversSelected.contains(2)){
				m_counterThreeXPos = 625;
				m_counterThreeYPos = 175;
				m_moveRightThree = false;
			}

			//SNAKE FROM 80 TO 1
			if (m_counterThreeXPos > 20 && m_counterThreeXPos < 40 && m_counterThreeYPos == 175 && m_moversSelected.contains(6)){
				m_counterThreeXPos = 25;
				m_counterThreeYPos = 700;
				m_moveRightThree = true;
			}

			//LADDER FROM 58 to 64
			if (m_counterThreeXPos > 160 && m_counterThreeXPos < 180 && m_counterThreeYPos == 325 && m_moversSelected.contains(5)){
				m_counterThreeXPos = 250;
				m_counterThreeYPos = 250;
				m_moveRightThree = true;
			}

			//SNAKE FROM 78 TO 27
			if (m_counterThreeXPos > 160 && m_counterThreeXPos < 180 && m_counterThreeYPos == 175 && m_moversSelected.contains(5)){
				m_counterThreeXPos = 475;
				m_counterThreeYPos = 550;
				m_moveRightThree = true;
			}

			//SNAKE FROM 55 T0 31
			if (m_counterThreeXPos > 390 && m_counterThreeXPos < 410 && m_counterThreeYPos == 325 && m_moversSelected.contains(7)){
				m_counterThreeXPos =699;
				m_counterThreeYPos = 475;
				m_moveRightThree = false;
			}

			//SNAKE FROM 97 TO 75
			if (m_counterThreeXPos > 240 && m_counterThreeXPos < 260 && m_counterThreeYPos == 25 && m_moversSelected.contains(8)){
				m_counterThreeXPos = 400;
				m_counterThreeYPos = 175;
				m_moveRightThree = false;
			}

			//SNAKE FROM 33 TO 8
			if (m_counterThreeXPos > 535 && m_counterThreeXPos < 565 && m_counterThreeYPos == 475 && m_moversSelected.contains(9)){
				m_counterThreeXPos = 545;
				m_counterThreeYPos = 700;
				m_moveRightThree = true;
			}
			//SNAKE FROM 62 TO 59
			if (m_counterThreeXPos > 90 && m_counterThreeXPos < 110 && m_counterThreeYPos == 250 && m_moversSelected.contains(10)){
				m_counterThreeXPos = 105;
				m_counterThreeYPos = 325;
				m_moveRightThree = false;
			}

			//LADDER FROM 44 TO 56
			if (m_counterThreeXPos > 240 && m_counterThreeXPos < 260 && m_counterThreeYPos == 400 && m_moversSelected.contains(6)){
				m_counterThreeXPos = 325;
				m_counterThreeYPos = 325;
				m_moveRightThree = false;
			}

			//LADDER FROM 4 TO 17
			if (m_counterThreeXPos > 240 && m_counterThreeXPos < 260 && m_counterThreeYPos == 700 && m_moversSelected.contains(7)){
				m_counterThreeXPos = 250;
				m_counterThreeYPos = 625;
				m_moveRightThree = false;
			}

			//LADDER FROM 14 TO 29
			if (m_counterThreeXPos > 615 && m_counterThreeXPos < 635 && m_counterThreeYPos == 625 && m_moversSelected.contains(8)){
				m_counterThreeXPos = 625;
				m_counterThreeYPos = 550;
				m_moveRightThree = true;
			}

			//LADDER FROM 69 TO 72
			if (m_counterThreeXPos > 615 && m_counterThreeXPos < 635 && m_counterThreeYPos == 250 && m_moversSelected.contains(9)){
				m_counterThreeXPos = 625;
				m_counterThreeYPos = 175;
				m_moveRightThree = false;
			}

			//LADDER FROM 76 TO 84
			if (m_counterThreeXPos > 315 && m_counterThreeXPos < 335 && m_counterThreeYPos == 175 && m_moversSelected.contains(10)){
				m_counterThreeXPos = 250;
				m_counterThreeYPos = 100;
				m_moveRightThree = true;
			}

		}
		//System.out.println(threeX);
		//System.out.println(threeY);

	}


	private void moveIt4(int diceRoll) {



		int pixelCount = 75 * diceRoll;



		while(pixelCount > 0 && m_gameWon == false){
			if(m_counterFourXPos == 700){
				m_moveAcrossFour = false;
				m_moveUpFour = true;
				m_moveRightFour = false;
				m_counterFourYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}
			if(m_counterFourXPos == 24){
				m_moveAcrossFour = false;
				m_moveUpFour = true;
				m_moveRightFour = true;
				m_counterFourYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			if ((m_counterFourYPos- 700) % 75 == 0) {
				m_moveAcrossFour = true;
				m_moveUpFour = false;

			}

			if(m_moveUpFour) {
				m_counterFourYPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}  

			if(m_moveAcrossFour && m_moveRightFour && m_moveUpFour == false){
				m_counterFourXPos++;  
				pixelCount --;
				m_snakeLadderBoard.repaint();

			} 

			if(m_moveAcrossFour && m_moveRightFour == false && m_moveUpFour == false){
				m_counterFourXPos--;
				pixelCount --;
				m_snakeLadderBoard.repaint();

			}

			//CHECKS TO SEE IF GAME HAS BEEN WON
			if ( m_counterFourXPos > 20 && m_counterFourXPos < 45 && m_counterFourYPos == 25 ){
				//System.out.println("Won Game");
				m_counterFourXPos = 25;
				m_gameWon = true;
				wonGameStuff(3);	
			}


			try{
				Thread.sleep(3);
			} catch (Exception exc){}
			m_snakeLadderBoard.repaint();

			//

		}

		//CHECKS TO SEE IF COUNTER HAS LANDED ON ANY MOVER
		if (pixelCount == 0){



			if (pixelCount == 0){



				//LADDER FROM 16 to 45
				if ( m_counterFourXPos > 315 && m_counterFourXPos < 335 && m_counterFourYPos == 625 && m_moversSelected.contains(4)){
					m_counterFourYPos = 400;
					m_moveRightFour = true;
				}
				//SNAKE FROM 43 TO 3
				if (m_counterFourXPos > 160 && m_counterFourXPos < 180 && m_counterFourYPos == 400 && m_moversSelected.contains(1)) {
					while (m_counterFourYPos < 700){
						m_counterFourYPos++;
						m_moveRightFour = true;
						m_snakeLadderBoard.repaint();
					}
					m_counterFourXPos = 175;
				}

				//LADDER FROM 48 TO 53
				if ( m_counterFourXPos > 540 && m_counterFourXPos < 555 && m_counterFourYPos == 400 && m_moversSelected.contains(3)){
					while (m_counterFourYPos > 325){
						m_counterFourYPos--;
					}
					m_counterFourXPos = 550;
					m_moveRightFour = false;
				}

				//LADDER FROM 54 TO 62
				if (m_counterFourXPos > 465 && m_counterFourXPos < 485 && m_counterFourYPos == 325 && m_moversSelected.contains(2)){
					m_counterFourXPos =400;
					m_counterFourYPos = 250;
					m_moveRightFour = true;
				}

				//SNAKE FROM 74 TO 35
				if (m_counterFourXPos > 465 && m_counterFourXPos < 485 && m_counterFourYPos == 175 && m_moversSelected.contains(3)){
					m_counterFourXPos = 400;
					m_counterFourYPos = m_counterFourYPos + (4*75);
					m_moveRightFour = false;
				}

				//SNAKE FROM 86 to 77
				if (m_counterFourXPos > 390 && m_counterFourXPos < 405 && m_counterFourYPos == 100 && m_moversSelected.contains(4)){
					m_counterFourXPos = 250;
					m_counterFourYPos = 175;
					m_moveRightFour = false;
				}

				//LADDER FROM 82 TO 98
				if (m_counterFourXPos > 85 && m_counterFourXPos < 115 && m_counterFourYPos == 100 && m_moversSelected.contains(1)){
					m_counterFourXPos = 175;
					m_counterFourYPos = 25;
					m_moveRightFour = false;
				}

				//SNAKE FROM 94 TO 72
				if (m_counterFourXPos > 475 && m_counterFourXPos < 490 && m_counterFourYPos == 25 && m_moversSelected.contains(2)){
					m_counterFourXPos = 625;
					m_counterFourYPos = 175;
					m_moveRightFour = false;
				}

				//SNAKE FROM 80 TO 1
				if (m_counterFourXPos > 20 && m_counterFourXPos < 40 && m_counterFourYPos == 175 && m_moversSelected.contains(6)){
					m_counterFourXPos = 25;
					m_counterFourYPos = 700;
					m_moveRightFour = true;
				}

				//LADDER FROM 58 to 64
				if (m_counterFourXPos > 160 && m_counterFourXPos < 180 && m_counterFourYPos == 325 && m_moversSelected.contains(5)){
					m_counterFourXPos = 250;
					m_counterFourYPos = 250;
					m_moveRightFour = true;
				}

				//SNAKE FROM 78 TO 27
				if (m_counterFourXPos > 160 && m_counterFourXPos < 180 && m_counterFourYPos == 175 && m_moversSelected.contains(5)){
					m_counterFourXPos = 475;
					m_counterFourYPos = 550;
					m_moveRightFour = true;
				}

				//SNAKE FROM 55 T0 31
				if (m_counterFourXPos > 390 && m_counterFourXPos < 410 && m_counterFourYPos == 325 && m_moversSelected.contains(7)){
					m_counterFourXPos =699;
					m_counterFourYPos = 475;
					m_moveRightFour = false;
				}

				//SNAKE FROM 97 TO 75
				if (m_counterFourXPos > 240 && m_counterFourXPos < 260 && m_counterFourYPos == 25 && m_moversSelected.contains(8)){
					m_counterFourXPos = 400;
					m_counterFourYPos = 175;
					m_moveRightFour = false;
				}

				//SNAKE FROM 33 TO 8
				if (m_counterFourXPos > 535 && m_counterFourXPos < 565 && m_counterFourYPos == 475 && m_moversSelected.contains(9)){
					m_counterFourXPos = 545;
					m_counterFourYPos = 700;
					m_moveRightFour = true;
				}
				//SNAKE FROM 62 TO 59
				if (m_counterFourXPos > 90 && m_counterFourXPos < 110 && m_counterFourYPos == 250 && m_moversSelected.contains(10)){
					m_counterFourXPos = 105;
					m_counterFourYPos = 325;
					m_moveRightFour = false;
				}

				//LADDER FROM 44 TO 56
				if (m_counterFourXPos > 240 && m_counterFourXPos < 260 && m_counterFourYPos == 400 && m_moversSelected.contains(6)){
					m_counterFourXPos = 325;
					m_counterFourYPos = 325;
					m_moveRightFour = false;
				}
				//LADDER FROM 4 TO 17
				if (m_counterFourXPos > 240 && m_counterFourXPos < 260 && m_counterFourYPos == 700 && m_moversSelected.contains(7)){
					m_counterFourXPos = 250;
					m_counterFourYPos = 625;
					m_moveRightFour = false;
				}

				//LADDER FROM 14 TO 29
				if (m_counterFourXPos > 615 && m_counterFourXPos < 635 && m_counterFourYPos == 625 && m_moversSelected.contains(8)){
					m_counterFourXPos = 625;
					m_counterFourYPos = 550;
					m_moveRightFour = true;
				}

				//LADDER FROM 69 TO 72
				if (m_counterFourXPos > 615 && m_counterFourXPos < 635 && m_counterFourYPos == 250 && m_moversSelected.contains(9)){
					m_counterFourXPos = 625;
					m_counterFourYPos = 175;
					m_moveRightFour = false;
				}

				//LADDER FROM 76 TO 84
				if (m_counterFourXPos > 315 && m_counterFourXPos < 335 && m_counterFourYPos == 175 && m_moversSelected.contains(10)){
					m_counterFourXPos = 250;
					m_counterFourYPos = 100;
					m_moveRightFour = true;
				}


			}
			//System.out.println(fourX);
			//System.out.println(fourY);
		}


	}
	public void wonGameStuff(int PlayerInt){
		//Winning Stuff
		java.awt.Image winGif = new ImageIcon (this.getClass().getResource("/funnyWin.gif")).getImage();
		m_diceRollLbl.setIcon(new ImageIcon(winGif));
		m_diceRollLbl.setBounds(100,25,300,300);
		m_diceRollBtn.setVisible(false);
		m_currentPlayerNameLbl.setBounds(100,0,500,50);
		m_currentPlayerNameLbl.setText( m_listOfPlayers.get(PlayerInt).getName() + " WINS!!!");
		m_currentPlayerNameLbl.setFont(new Font(Font.SANS_SERIF, 32, 50));
		m_timerLbl.setVisible(false);
		m_snakeLadderMenu.setSize(500,350);
		m_snakeLadderMenu.setLocation(600, 250);
		m_snakeLadderMenu.setBackground(Color.LIGHT_GRAY);
	}

}
