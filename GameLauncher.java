import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @file GameLauncher.java
 * @author Jon Haddow
 * @date 03 Dec 2014
 *
 * This class launches the main menu to start a game
 * and choose the settings for the game.
 */

public class GameLauncher {

	/* Set values for the game. These are public so they can be accessed by other classes. */
	public final static int MAX_NUMBER_OF_MOVERS = 5;
	public final static int MIN_NUMBER_OF_MOVERS = 1;
	public final static int DEFAULT_NUMBER_OF_MOVERS = 3;
	public final static int MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS = 4;
	public final static int MIN_NUMBER_OF_SNAKESLADDERS_PLAYERS = 1;
	public final static int DEFAULT_NUMBER_OF_PLAYERS = 2;
	public final static int NUMBER_OF_TTT_PLAYERS = 2;	
	public final static Color[] PLAYER_COLORS = {Color.blue, Color.red, Color.orange, Color.black};

	/* Store dimensions of the frame */
	private final static int FRAME_LOCATION = 100;
	private final static int FRAME_HEIGHT = 400;
	private final static int FRAME_WIDTH = 415;

	/* Store dimensions for the components */ 
	private final static int COMPONENT_LOCATION = 10;
	private final static int COMPONENT_WIDTH = 190;
	private final static int COMPONENT_HEIGHT = 25;
	private final static int PANEL_WIDTH = 400;
	private final static int PANEL_HEIGHT = 270;
	private final static int SLIDER_WIDTH = 100;
	private final static int TXTBOX_WIDTH = 50;
	private final static int TXTBOX_HEIGHT = 20;

	/* Store the spacing between components */
	private final static int HORIZONAL_SPACING = 200;
	private final static int VERTICAL_SPACING = 25;
	private final static int BUTTON2PANEL_SPACING = 50;

	/* Store the GUI components */
	private static JFrame m_frame;
	private static JButton m_btnSnakesLadder;
	private static JButton m_btnTTT;
	private static JLayeredPane m_snakesLadderPanel;
	private static JLayeredPane m_TTTPanel;

	/* Store the components within snakes and ladders panel */
	private static JLabel m_lblHowManyMovers;
	private static JSlider m_moverSlider;
	private static JTextField m_txtNumberOfMovers;
	private static JLabel m_lblHowManyPlayers;
	private static JSlider m_playerSlider;
	private static JLabel[] m_lblSnakeLadderPlayerName;
	private static JLabel[] m_lblTTTPlayerName;
	private static JTextField [] m_txtSnakesLaddersPlayerName;
	private static JTextField [] m_txtTTTPlayerName;
	private static JButton m_btnPlaySnakeLadder;
	private static JButton m_btnPlayTTT;

	/* Fields to store game settings */
	private static int m_numberOfMovers;
	private static int m_numberOfPlayers;
	private static String [] m_playerNames = new String[MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS];

	/**
	 * Gets the number of snakes/ladders chosen by the player.
	 * @return number of snakes/ladders
	 */
	public static int getNumberOfMovers() {
		return m_numberOfMovers;
	}

	/**
	 * Sets the number of snakes/ladders.
	 * @param numberOfMovers number of snakes/ladders
	 */
	public static void setNumberOfMovers(int numberOfMovers) {
		GameLauncher.m_numberOfMovers = numberOfMovers;
	}

	/**
	 * Gets the number of players the user has chosen to enter the game.
	 * @return number of players
	 */
	public static int getNumberOfPlayers() {
		return m_numberOfPlayers;
	}

	/**
	 * Sets the number of players playing the game.
	 * @param numberOfPlayers number of players in the game.
	 */
	public static void setNumberOfPlayers(int numberOfPlayers) {
		GameLauncher.m_numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Main method. This is the first method called to begin the program.
	 * @param String [] of arguments
	 */
	public static void main(String[] args) {
		intialise();
		m_frame.setVisible(true);
	}

	/**
	 * This initialises a window with buttons to choose either snakes
	 * and ladders or tic tac toe. 
	 */
	private static void intialise() {

		/* Create a new form */
		m_frame = new JFrame();
		m_frame.setTitle("Group 6 Game");
		m_frame.setResizable(false);
		m_frame.setBounds(
				FRAME_LOCATION, 
				FRAME_LOCATION, 
				FRAME_WIDTH, 
				FRAME_HEIGHT);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.getContentPane().setLayout(null);

		/* Create panels */
		createSnakesLaddersPanel();
		createTTTPanel();

		/* Create snakes and ladders button. */
		m_btnSnakesLadder = new JButton("Play Snakes and Ladders");
		m_btnSnakesLadder.setBounds(
				COMPONENT_LOCATION, 
				COMPONENT_LOCATION, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_frame.getContentPane().add(m_btnSnakesLadder);

		/* Create TicTacToe button. */		
		m_btnTTT = new JButton("Play TicTacToe");
		m_btnTTT.setBounds(
				COMPONENT_LOCATION + HORIZONAL_SPACING, 
				COMPONENT_LOCATION, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_frame.getContentPane().add(m_btnTTT);

		/* When buttons are pressed, show panels to allow game customisation. */
		m_btnTTT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				createTTTPanel();
				m_TTTPanel.setVisible(true);
				m_snakesLadderPanel.setVisible(false);
			}
		});
		m_btnSnakesLadder.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				createSnakesLaddersPanel();
				m_snakesLadderPanel.setVisible(true);
				m_TTTPanel.setVisible(false);
			}
		});
	}

	/**
	 * This method creates a panel with options to set up and customise
	 * the game of snakes and ladders.
	 */
	private static void createSnakesLaddersPanel() {

		/* The panel is created */
		m_snakesLadderPanel = new JLayeredPane();
		m_snakesLadderPanel.setVisible(false);
		m_snakesLadderPanel.setBounds(
				0, 
				COMPONENT_LOCATION + BUTTON2PANEL_SPACING, 
				PANEL_WIDTH, 
				PANEL_HEIGHT);
		m_frame.getContentPane().add(m_snakesLadderPanel);

		/* The play game button is created. */
		m_btnPlaySnakeLadder = new JButton("Play Game");
		m_btnPlaySnakeLadder.setBounds(
				COMPONENT_LOCATION, 
				m_snakesLadderPanel.getX() + m_snakesLadderPanel.getHeight() - VERTICAL_SPACING, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_snakesLadderPanel.add(m_btnPlaySnakeLadder);

		/* Once the play game button is clicked, the info is gathered
		 * and sent to the Snakes and ladders game. 
		 */
		m_btnPlaySnakeLadder.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Collect the names of the players.
				for (int i = 0; i < getNumberOfPlayers(); i++){
					m_playerNames[i] = m_txtSnakesLaddersPlayerName[i].getText();
				}
				loadSnakesAndLadders(getNumberOfMovers(),getNumberOfPlayers(),m_playerNames,PLAYER_COLORS);
			}
		});

		/* These commands call the creation of the GUI components
		 *  which deal with the number of players or movers.
		 */
		askNumberOfMovers();
		askNumberOfPlayers();
	}

	/**
	 * This method initialises the slider and other components which
	 * allow the user to decide the number of movers in the game.
	 */
	private static void askNumberOfMovers(){

		/* The mover slider is created */
		m_moverSlider = new JSlider();
		m_moverSlider.setMinimum(MIN_NUMBER_OF_MOVERS);
		m_moverSlider.setMaximum(MAX_NUMBER_OF_MOVERS);
		m_moverSlider.setValue(DEFAULT_NUMBER_OF_MOVERS);
		m_moverSlider.setSnapToTicks(true);
		m_moverSlider.setBounds(
				COMPONENT_LOCATION, 
				m_btnPlaySnakeLadder.getY() - VERTICAL_SPACING, 
				SLIDER_WIDTH, 
				COMPONENT_HEIGHT);
		m_snakesLadderPanel.add(m_moverSlider);

		/* The label asking the number of movers is created. */
		m_lblHowManyMovers = new JLabel("How many Snake/Ladders?");
		m_lblHowManyMovers.setBounds(
				COMPONENT_LOCATION, 
				m_moverSlider.getY() - VERTICAL_SPACING, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_snakesLadderPanel.add(m_lblHowManyMovers);

		/* The text box is which displays the number of movers is created */
		m_txtNumberOfMovers = new JTextField();
		m_txtNumberOfMovers.setBounds(
				m_moverSlider.getX() + SLIDER_WIDTH + VERTICAL_SPACING, 
				m_moverSlider.getY(), 
				TXTBOX_WIDTH, 
				TXTBOX_HEIGHT);
		m_txtNumberOfMovers.setEditable(false);
		m_txtNumberOfMovers.setText("" + m_moverSlider.getValue());
		m_snakesLadderPanel.add(m_txtNumberOfMovers);

		/* As the slider changes value, the field "m_numberOfMovers" is
		 * updated and the text box reflects the new value of the slider. 
		 */
		m_moverSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setNumberOfMovers(m_moverSlider.getValue());
				m_txtNumberOfMovers.setText("" + getNumberOfMovers());
			}
		});
	}

	/**
	 * This method initialises the slider and other components which
	 * allow the user to decide the number of players in the game.
	 */
	private static void askNumberOfPlayers(){

		/* The label, asking how many players there are, is created. */
		m_lblHowManyPlayers = new JLabel("How many players?");
		m_lblHowManyPlayers.setBounds(
				COMPONENT_LOCATION, 
				COMPONENT_LOCATION, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_snakesLadderPanel.add(m_lblHowManyPlayers);

		/* The player slider is created. */
		m_playerSlider = new JSlider();
		m_playerSlider.setMinimum(MIN_NUMBER_OF_SNAKESLADDERS_PLAYERS);
		m_playerSlider.setMaximum(MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS);
		m_playerSlider.setValue(DEFAULT_NUMBER_OF_PLAYERS);
		m_playerSlider.setSnapToTicks(true);
		m_playerSlider.setBounds(
				COMPONENT_LOCATION, 
				m_lblHowManyPlayers.getY() + VERTICAL_SPACING, 
				SLIDER_WIDTH, 
				COMPONENT_HEIGHT);
		m_snakesLadderPanel.add(m_playerSlider);

		/* The array to store names boxes is declared. */
		m_lblSnakeLadderPlayerName = new JLabel[MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS];
		m_txtSnakesLaddersPlayerName = new JTextField[MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS];

		/* The array is created and draw. */
		for (int i = 0; i < MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS; i++) {			
			m_lblSnakeLadderPlayerName[i] = new JLabel();
			m_txtSnakesLaddersPlayerName[i] = new JTextField();
			//Each colour is assigned a text box.
			m_lblSnakeLadderPlayerName[i].setForeground(PLAYER_COLORS[i]);
			if (i == 0){
				m_lblSnakeLadderPlayerName[i].setBounds(
						COMPONENT_LOCATION, 
						m_playerSlider.getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
				m_txtSnakesLaddersPlayerName[i].setBounds(
						COMPONENT_LOCATION + COMPONENT_WIDTH/2, 
						m_playerSlider.getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
			} else {
				m_lblSnakeLadderPlayerName[i].setBounds(
						COMPONENT_LOCATION, 
						m_txtSnakesLaddersPlayerName[i-1].getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
				m_txtSnakesLaddersPlayerName[i].setBounds(
						COMPONENT_LOCATION + COMPONENT_WIDTH/2, 
						m_txtSnakesLaddersPlayerName[i-1].getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
			}
			m_lblSnakeLadderPlayerName[i].setText("Player " + (i+1) + ":");
			m_snakesLadderPanel.add(m_lblSnakeLadderPlayerName[i]);
			m_snakesLadderPanel.add(m_txtSnakesLaddersPlayerName[i]);
		}

		/* update the player boxes dependent on the number of players. */
		m_numberOfPlayers = m_playerSlider.getValue();
		updatePlayerBoxes();
		m_playerSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				setNumberOfPlayers(m_playerSlider.getValue());
				updatePlayerBoxes();
			}
		});
	}

	/**
	 * This method would update the number of player name boxes to
	 * depend upon the number of players selected.
	 */
	private static void updatePlayerBoxes(){
		for (int i = 0; i < MAX_NUMBER_OF_SNAKESLADDERS_PLAYERS; i++){
			if (i < m_playerSlider.getValue()){
				m_lblSnakeLadderPlayerName[i].setVisible(true);
				m_txtSnakesLaddersPlayerName[i].setVisible(true);
			} else {
				m_lblSnakeLadderPlayerName[i].setVisible(false);
				m_txtSnakesLaddersPlayerName[i].setVisible(false);
			}
		}
	}

	/**
	 * This method creates a panel with options to set up the game
	 * of TicTacToe.
	 */
	private static void createTTTPanel() {

		/* The panel is created. */
		m_TTTPanel = new JLayeredPane();
		m_TTTPanel.setVisible(false);
		m_TTTPanel.setBounds(
				HORIZONAL_SPACING, 
				COMPONENT_LOCATION + BUTTON2PANEL_SPACING, 
				PANEL_WIDTH, 
				PANEL_HEIGHT);
		m_frame.getContentPane().add(m_TTTPanel);

		/* The play game button is created. */
		m_btnPlayTTT = new JButton("Play Game");
		m_btnPlayTTT.setBounds(
				COMPONENT_LOCATION, 
				m_snakesLadderPanel.getX() + m_TTTPanel.getHeight() - VERTICAL_SPACING, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_TTTPanel.add(m_btnPlayTTT);

		/* Once the play game button is clicked, the info is gathered
		 * and sent to the TicTacToe game. */
		m_btnPlayTTT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Collect the names of the players.
				for (int i = 0; i < NUMBER_OF_TTT_PLAYERS; i++){
					m_playerNames[i] = m_txtTTTPlayerName[i].getText();
				}
				loadTicTacToe(m_playerNames);
			}
		});

		/* The player name text boxes are created */
		enterPlayerNames();
	}

	/**
	 * This method initialises the text boxes where the user can enter
	 * the player names.
	 */
	private static void enterPlayerNames() {

		/* Declare array to store names boxes */
		m_lblTTTPlayerName = new JLabel[NUMBER_OF_TTT_PLAYERS];
		m_txtTTTPlayerName = new JTextField[NUMBER_OF_TTT_PLAYERS];

		/* Draw array of name boxes */
		for (int i = 0; i < NUMBER_OF_TTT_PLAYERS; i++) {			
			m_lblTTTPlayerName[i] = new JLabel();
			m_txtTTTPlayerName[i] = new JTextField();
			if (i == 0){
				m_lblTTTPlayerName[i].setBounds(
						COMPONENT_LOCATION, 
						COMPONENT_LOCATION, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
				m_txtTTTPlayerName[i].setBounds(
						COMPONENT_LOCATION + COMPONENT_WIDTH/2, 
						COMPONENT_LOCATION, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
			} else {
				m_lblTTTPlayerName[i].setBounds(
						COMPONENT_LOCATION, 
						m_txtTTTPlayerName[i-1].getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
				m_txtTTTPlayerName[i].setBounds(
						COMPONENT_LOCATION + COMPONENT_WIDTH/2, 
						m_txtTTTPlayerName[i-1].getY() + VERTICAL_SPACING, 
						COMPONENT_WIDTH/2, 
						COMPONENT_HEIGHT);
			}
			m_lblTTTPlayerName[i].setText("Player " + (i+1) + ":");
			m_TTTPanel.add(m_lblTTTPlayerName[i]);
			m_TTTPanel.add(m_txtTTTPlayerName[i]);
		}
	}


	/**
	 * This method loads a game of snakes and ladders.
	 * @param numberOfMovers number of snakes/ladders on the board.
	 * @param numberOfPlayers number of players playing snakes and ladders.
	 * @param playerNames String array of player names.
	 * @param playerColors Color array of player colours.
	 */
	private static void loadSnakesAndLadders(int numberOfMovers, int numberOfPlayers, String[] playerNames, Color[] playerColors) {
		SnakeLadder newgame = new SnakeLadder(numberOfMovers,numberOfPlayers,playerNames,playerColors);
	}

	/**
	 * This method loads a game of TicTacToe.
	 * @param playerNames 
	 * @param playerNames String array of player names.
	 */
	private static void loadTicTacToe(String[] playerNames) {
		Human player1 = new Human(playerNames[0], PLAYER_COLORS[0],false);
		Human player2 = new Human(playerNames[1], PLAYER_COLORS[1],false);
		TicTac newGame = new TicTac(player1,player2);
		newGame.boardSetUp();
	}
}

