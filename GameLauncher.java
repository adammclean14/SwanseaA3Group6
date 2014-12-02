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

public class GameLauncher {

	/* Store the GUI components */
	private static JFrame m_frame;
	private static JButton m_btnSnakesAndLadders;
	private static JButton m_btnTicTacToe;
	private static JLayeredPane m_layeredPane;

	/* Store the components within snakes and ladders panel */
	private static JLabel m_lblHowManyMovers;
	private static JSlider m_moverSlider;
	private static JTextField m_txtNumberOfMovers;
	private static JLabel m_lblHowManyPlayers;
	private static JSlider m_playerSlider;
	private static JTextField m_txtNumberOfPlayers;
	private static JButton m_btnPlayGame;

	/* Store dimensions of the frame */
	private static int FRAME_LOCATION = 100;
	private static int FRAME_HEIGHT = 265;
	private static int FRAME_WIDTH = 415;

	/* Store dimensions for the components */ 
	private static int COMPONENT_LOCATION_X = 10;
	private static int COMPONENT_LOCATION_Y = 10;
	private static int COMPONENT_WIDTH = 190;
	private static int COMPONENT_HEIGHT = 25;
	private static int PANEL_WIDTH = 200;
	private static int PANEL_HEIGHT = 170;
	private static int SLIDER_WIDTH = 100;
	private static int TXTBOX_WIDTH = 50;

	/* Store the spacing between components */
	private static int HORIZONAL_SPACING = 200;
	private static int VERTICAL_SPACING = 25;
	private static int BUTTON2PANEL_SPACING = 50;

	/* Set values for sliders */
	private static int MAX_NUMBER_OF_MOVERS = 10;
	private static int MAX_NUMBER_OF_PLAYERS = 4;
	private static int MIN_NUMBER_OF_MOVERS = 1;
	private static int MIN_NUMBER_OF_PLAYERS = 1;
	private static int DEFAULT_NUMBER_OF_MOVERS = 5;
	private static int DEFAULT_NUMBER_OF_PLAYERS = 2;

	/* Fields to store game settings */
	private static int m_numberOfMovers;
	private static int m_numberOfPlayers;

	/**
	 * Gets the number of snakes/ladders chosen by the player.
	 * @return integer - number of snakes/ladders
	 */
	public static int getNumberOfMovers() {
		return m_numberOfMovers;
	}

	/**
	 * Sets the number of snakes/ladders.
	 * @param numberOfMovers integer - number of snakes/ladders
	 */
	public static void setNumberOfMovers(int numberOfMovers) {
		GameLauncher.m_numberOfMovers = numberOfMovers;
	}

	/**
	 * Gets the number of players the user has chosen to enter the game.
	 * @return integer - number of players
	 */
	public static int getNumberOfPlayers() {
		return m_numberOfPlayers;
	}

	/**
	 * Sets the number of players playing the game.
	 * @param numberOfPlayers integer - number of players in the game.
	 */
	public static void setNumberOfPlayers(int numberOfPlayers) {
		GameLauncher.m_numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Main method. This is the first method called to begin the program.
	 * @param args
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

		/* Create snakes and ladders button. When pressed, show snakes
		 * and ladders game setup options */
		m_btnSnakesAndLadders = new JButton("Play Snakes and Ladders");
		m_btnSnakesAndLadders.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m_layeredPane.isVisible() == false){
					m_layeredPane.setVisible(true);
				}
			}
		});
		createPanel();
		m_btnSnakesAndLadders.setBounds(
				COMPONENT_LOCATION_X, 
				COMPONENT_LOCATION_Y, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_frame.getContentPane().add(m_btnSnakesAndLadders);

		/* Create TicTacToe button. When pressed loads TicTacToe game */		
		m_btnTicTacToe = new JButton("Play TicTacToe");
		m_btnTicTacToe.setBounds(
				COMPONENT_LOCATION_X + HORIZONAL_SPACING, 
				COMPONENT_LOCATION_Y, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_btnTicTacToe.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				LoadTicTacToe();
			}
		});
		m_frame.getContentPane().add(m_btnTicTacToe);
	}

	/**
	 * This method creates a panel with options to set up the game.
	 * e.g. number of snakes/ladders, number of players.
	 */
	private static void createPanel() {
		m_layeredPane = new JLayeredPane();
		m_layeredPane.setVisible(false);
		m_layeredPane.setBounds(
				0, 
				COMPONENT_LOCATION_Y + BUTTON2PANEL_SPACING, 
				PANEL_WIDTH, 
				PANEL_HEIGHT);
		m_frame.getContentPane().add(m_layeredPane);

		/* This commands call the GUI components which deal with 
		 * the number of players or movers. */
		askNumberOfMovers();
		askNumberOfPlayers();

		/* Play snake and ladders button is created. When pressed
		 * a snake and ladders game is loaded with the number
		 * of players and movers as parameters. */
		m_btnPlayGame = new JButton("Play Game");
		m_btnPlayGame.setBounds(
				COMPONENT_LOCATION_X, 
				m_txtNumberOfPlayers.getY() + VERTICAL_SPACING *2 , 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_btnPlayGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadSnakesAndLadders(m_numberOfMovers,m_numberOfPlayers);
			}
		});
		m_layeredPane.add(m_btnPlayGame);
	}

	/**
	 * This method initialises the slider and other components which
	 * allow the user to decide the number of movers in the game.
	 */
	private static void askNumberOfMovers(){
		m_lblHowManyMovers = new JLabel("How many Snake/Ladders?");
		m_lblHowManyMovers.setBounds(
				COMPONENT_LOCATION_X, 
				COMPONENT_LOCATION_Y, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_layeredPane.add(m_lblHowManyMovers);

		/* As the slider changes value, the field "m_numberOfMovers" is
		 * updated and the text box reflects the new value of the slider. */
		m_moverSlider = new JSlider();
		m_moverSlider.setMinimum(MIN_NUMBER_OF_MOVERS);
		m_moverSlider.setMaximum(MAX_NUMBER_OF_MOVERS);
		m_moverSlider.setValue(DEFAULT_NUMBER_OF_MOVERS);
		m_moverSlider.setSnapToTicks(true);
		m_moverSlider.setBounds(
				COMPONENT_LOCATION_X, 
				m_lblHowManyMovers.getY() + VERTICAL_SPACING, 
				SLIDER_WIDTH, 
				COMPONENT_HEIGHT);
		m_layeredPane.add(m_moverSlider);
		m_moverSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m_numberOfMovers = m_moverSlider.getValue();
				m_txtNumberOfMovers.setText("" + m_numberOfMovers);
			}
		});

		m_txtNumberOfMovers = new JTextField();
		m_txtNumberOfMovers.setBounds(
				m_moverSlider.getX() + SLIDER_WIDTH + VERTICAL_SPACING, 
				m_lblHowManyMovers.getY() + VERTICAL_SPACING, 
				TXTBOX_WIDTH, 
				COMPONENT_HEIGHT);
		m_txtNumberOfMovers.setEditable(false);
		m_txtNumberOfMovers.setText("" + m_moverSlider.getValue());
		m_layeredPane.add(m_txtNumberOfMovers);
	}

	/**
	 * This method initialises the slider and other components which
	 * allow the user to decide the number of players in the game.
	 */
	private static void askNumberOfPlayers(){
		m_lblHowManyPlayers = new JLabel("How many players?");
		m_lblHowManyPlayers.setBounds(
				COMPONENT_LOCATION_X, 
				m_txtNumberOfMovers.getY() + VERTICAL_SPACING, 
				COMPONENT_WIDTH, 
				COMPONENT_HEIGHT);
		m_layeredPane.add(m_lblHowManyPlayers);

		/* As the slider changes value, the field "m_numberOfPlayers" is
		 * updated and the text box reflects the new value of the slider. */
		m_playerSlider = new JSlider();
		m_playerSlider.setMinimum(MIN_NUMBER_OF_PLAYERS);
		m_playerSlider.setMaximum(MAX_NUMBER_OF_PLAYERS);
		m_playerSlider.setValue(DEFAULT_NUMBER_OF_PLAYERS);
		m_playerSlider.setSnapToTicks(true);
		m_playerSlider.setBounds(
				COMPONENT_LOCATION_X, 
				m_lblHowManyPlayers.getY() + VERTICAL_SPACING, 
				SLIDER_WIDTH, 
				COMPONENT_HEIGHT);
		m_layeredPane.add(m_playerSlider);
		m_playerSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m_numberOfMovers = m_playerSlider.getValue();
				m_txtNumberOfPlayers.setText("" + m_numberOfMovers);
			}
		});

		m_txtNumberOfPlayers = new JTextField();
		m_txtNumberOfPlayers.setBounds(
				m_playerSlider.getX() + SLIDER_WIDTH + VERTICAL_SPACING, 
				m_lblHowManyPlayers.getY() + VERTICAL_SPACING, 
				TXTBOX_WIDTH, 
				COMPONENT_HEIGHT);
		m_txtNumberOfPlayers.setEditable(false);
		m_txtNumberOfPlayers.setText("" + m_playerSlider.getValue());
		m_layeredPane.add(m_txtNumberOfPlayers);
	}

	/**
	 * This method loads a game of snakes and ladders.
	 * @param numberOfMovers number of snakes/ladders on the board.
	 * @param numberOfPlayers number of players playing snakes and ladders
	 */
	private static void loadSnakesAndLadders(int numberOfMovers, int numberOfPlayers) {

	}

	/**
	 * This method loads a game of TicTacToe.
	 */
	private static void LoadTicTacToe() {

	}

}

