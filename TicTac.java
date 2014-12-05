import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;


/**
 * @file TicTac.java
 * @author Connor Wilkins
 * @date 4 Dec 2014
 *
 * Controls board set-up and game logic of the Tic Tac Toe game.
 */
public class TicTac extends GameLogic{
	
	/**
	 * This action listener is used at the end of the game to make the 5 winning board tiles flash
	 */
	ActionListener timerAction = new ActionListener() {
		public void actionPerformed(ActionEvent flash) {
			for (int i = 0; i < m_winningButtons.size(); i++){
				if (m_winningButtons.get(i).getBackground() == m_oldBackground){
					m_winningButtons.get(i).setBackground(Color.BLACK);
				} else {
					m_winningButtons.get(i).setBackground(m_oldBackground);
				}
			}
		}
	};
	
	/**
	 * Action listener to control the time displayed on the board
	 * Each tick of the timer the count increases, and the display on the board is updated with the new time
	 */
	ActionListener countAction = new ActionListener(){
		public void actionPerformed(ActionEvent count){
			m_timeCount++;
			//variable to store the number of seconds in a minute
			final int SECONDS_IN_MINUTE = 60;
			/**
			 * Variable to store the number 10. This is used for formatting, adding an extra 0 before the unit
			 * if the current number of seconds is below 10
			 */
			final int TEN = 10;
			if (m_timeCount == SECONDS_IN_MINUTE){
				m_minutes++;
				m_timeCount = 0;
			}
			if (m_timeCount < TEN){
				m_time.setText("Time: " + m_minutes + ":" + "0" + m_timeCount);
			} else {
				m_time.setText("Time: " + m_minutes + ":" + m_timeCount);
			}
		}
	};


	private ArrayList<Player> m_order = new ArrayList<Player>();
	private ArrayList<JButton> m_winningButtons = new ArrayList<JButton>();
	private Boolean m_gameWon;
	private Boolean m_tied;
	private int m_turnCount;
	private int m_player1LongestChain;
	private int m_player2LongestChain;
	private final int BOARDSIZEX = 8;
	private final int BOARDSIZEY = 8;
	private final int BOARDSIZE = BOARDSIZEX * BOARDSIZEY;
	private JFrame m_window = new JFrame("Dans Crazy Tic Tac Toe!");
	private JButton m_buttons[][] = new JButton[BOARDSIZEX][BOARDSIZEY];
	private String m_letter = "";
	private Timer flashtimer = new Timer(500, timerAction);
	private Color m_oldBackground = Color.BLACK;
	private JLabel m_time = new JLabel("0");
	private int m_timeCount = 0;
	private int m_minutes = 0;
	private Timer m_timerDisplay = new Timer(1000, countAction);
	private JLabel m_player1Name = new JLabel("");
	private JLabel m_player2Name = new JLabel("");
	private Font m_defaultFont = new Font("Serif", Font.PLAIN, m_time.getFont().getSize());
	private Font m_boldFont = new Font(m_defaultFont.getFontName(), Font.BOLD, m_defaultFont.getSize());
	private final int WINNING_CHAIN_LENGTH = 5;

	/**
	 * The first constructor. It constructs an instance of the class for two human players
	 * \param player_1 the first human player in the game
	 * \param player_2 the second human player in the game
	 */
	public TicTac (Human player_1, Human player_2){
		for (int i = 0; i < BOARDSIZE -1; i++){
			m_order.add(player_1);
			m_order.add(player_2);
		}
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	/**
	 * The second constructor. It constructs an instance of the class for a human and a computer player
	 * \param player_1 the first human player in the game
	 * \param player_2 the first AI player in the game
	 */
	public TicTac (Human player_1, TicTacLogic player_2){
		for (int i = 0; i < BOARDSIZE -1; i++){
			m_order.add(player_1);
			m_order.add(player_2);
		}
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	/**
	 * The third constructor. It constructs an instance of the class for two computer players
	 * \param player_1 the first AI player in the game
	 * \param player_2 the second AI player in the game
	 */
	public TicTac (TicTacLogic player_1, TicTacLogic player_2){
		for (int i = 0; i < BOARDSIZE -1; i++){
			m_order.add(player_1);
			m_order.add(player_2);
		}
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	
	/**
	 * Getter method for the m_turnCount attribute
	 * \return m_turnCount the turn count
	 */
	public int getTurnNumber(){
		return m_turnCount;
	}
	
	/**
	 * Changes the current turn player
	 * \see m_turnCount
	 */
	public void changeTurnPlayer(){
		m_turnCount++;
	}
	
	/**
	 * Checks whether all possible moves have been made without a winner being found
	 * \see m_turnCount
	 * \see m_boardSize
	 * \see m_tied
	 */
	public void checkTie(){
		if (m_turnCount == BOARDSIZE){
			m_tied = true;
		}
		if (m_tied){
			JOptionPane.showMessageDialog(null, "Game is a tie");
			disableButtons();
		}
	}
	
	/**
	 * Checks the length of the vertical chain made by the last move, and if a chain of 5
	 * is found the turn player is set to have won
	 * 
	 * \see Player.hasWon()
	 * 
	 * \param changedX
	 * \param changedY
	 * 
	 */
	public void checkVerticalChain(int changedX, int changedY){
		int chainLength = 0;
		String checkValue = "";
		if (m_buttons[changedX][changedY].getText() == "X"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "X";
			chainLength = checkBottom(changedX + 1, changedY, checkValue, chainLength);
			chainLength = checkTop(changedX - 1, changedY, checkValue, chainLength);
		}else if (m_buttons[changedX][changedY].getText() == "O"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "O";
			chainLength = checkBottom(changedX + 1, changedY, checkValue, chainLength);
			chainLength = checkTop(changedX - 1, changedY, checkValue, chainLength);
		}
		if (chainLength == WINNING_CHAIN_LENGTH && checkValue == "O"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		} else if (chainLength == WINNING_CHAIN_LENGTH && checkValue == "X"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		}
	}
	
	/**
	 * Checks the space below the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkBottom(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX < BOARDSIZEX){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkBottomRight(changedX + 1, changedY, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the space above the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkTop(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX >= 0){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkTop(changedX - 1, changedY, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the length of the horizontal chain made by the last move, and if a chain of 5
	 * is found the turn player is set to have won
	 * 
	 * \see Player.hasWon()
	 * 
	 * @param changedX
	 * @param changedY
	 */
	public void checkHorizontalChain(int changedX, int changedY){
		int chainLength = 0;
		String checkValue = "";
		if (m_buttons[changedX][changedY].getText() == "X"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "X";
			chainLength = checkRight(changedX, changedY + 1, checkValue, chainLength);
			chainLength = checkLeft(changedX, changedY - 1, checkValue, chainLength);
		}else if (m_buttons[changedX][changedY].getText() == "O"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "O";
			chainLength = checkRight(changedX, changedY + 1, checkValue, chainLength);
			chainLength = checkLeft(changedX, changedY - 1, checkValue, chainLength);
		}
		if (chainLength == 5 && checkValue == "O"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		} else if (chainLength == 5 && checkValue == "X"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		}
	}
	
	/**
	 * Checks the space to the right of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkRight(int changedX, int changedY, String checkValue, int chainLength){
		if (changedY < BOARDSIZEY){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkRight(changedX, changedY + 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the space to the left of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkLeft(int changedX, int changedY, String checkValue, int chainLength){
		if (changedY >= 0){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkLeft(changedX, changedY - 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the length of the diagonal chains made by the last move, and if a chain of 5
	 * is found the turn player is set to have won
	 * 
	 * \see Player.hasWon()
	 * 
	 * \param changedX
	 * \param changedY
	 */
	public void checkDiagonalChain(int changedX, int changedY){
		int chainLength = 0;
		String checkValue = "";
		if (m_buttons[changedX][changedY].getText() == "X"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "X";
			chainLength = checkBottomRight(changedX + 1, changedY + 1, checkValue, chainLength);
			chainLength = checkTopLeft(changedX - 1, changedY - 1, checkValue, chainLength);
			if (chainLength < 5){
				m_winningButtons.clear();
				m_winningButtons.add(m_buttons[changedX][changedY]);
				chainLength = 1;
				chainLength = checkBottomLeft(changedX + 1, changedY - 1, checkValue, chainLength);
				chainLength = checkTopRight(changedX - 1, changedY + 1, checkValue, chainLength);
			}
		}else if (m_buttons[changedX][changedY].getText() == "O"){
			m_winningButtons.clear();
			m_winningButtons.add(m_buttons[changedX][changedY]);
			chainLength = 1;
			checkValue = "O";
			chainLength = checkBottomRight(changedX + 1, changedY + 1, checkValue, chainLength);
			chainLength = checkTopLeft(changedX - 1, changedY - 1, checkValue, chainLength);
			if (chainLength < 5){
				m_winningButtons.clear();
				m_winningButtons.add(m_buttons[changedX][changedY]);
				chainLength = 1;
				chainLength = checkBottomLeft(changedX + 1, changedY - 1, checkValue, chainLength);
				chainLength = checkTopRight(changedX - 1, changedY + 1, checkValue, chainLength);
			}
		}
		if (chainLength == 5 && checkValue == "O"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		} else if (chainLength == 5 && checkValue == "X"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		}
	}
	
	/**
	 * Checks the space to the lower right of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkBottomRight(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX < BOARDSIZEX && changedY < BOARDSIZEY){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkBottomRight(changedX + 1, changedY + 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the space to the upper left of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkTopLeft(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX >= 0 && changedY >= 0){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkTopLeft(changedX - 1, changedY - 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the space to the lower left of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkBottomLeft(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX < BOARDSIZEX && changedY >= 0){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkBottomLeft(changedX + 1, changedY - 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * Checks the space to the upper right of the square where a move has been made.
	 * If it is found to contain the same symbol, the function is called again
	 * until a square with a different symbol is found. The function then
	 * returns the length of the chain it found.
	 * 
	 * \param changedX
	 * \param changedY
	 * \param checkValue
	 * \param chainLength
	 * 
	 * \return chainLength
	 */
	public int checkTopRight(int changedX, int changedY, String checkValue, int chainLength){
		if (changedX >= 0 && changedY < BOARDSIZEY){
			if (m_buttons[changedX][changedY].getText() == checkValue){
				chainLength++;
				chainLength = checkTopRight(changedX - 1, changedY + 1, checkValue, chainLength);
				m_winningButtons.add(m_buttons[changedX][changedY]);
				return chainLength;
			} else{
				return chainLength;
			}
		} else {
			return chainLength;
		}
	}
	
	/**
	 * getter method for the name of the current turn player
	 * 
	 * @return Player.m_name()
	 */
	public String getTurnPlayer(){
		return m_order.get(m_turnCount).getName();
	}
	
	/**
	 * Same as getTurnPLayer() but has a count parameter to track the turn number seperately
	 * 
	 * @param count
	 * @return Player.m_name()
	 */
	public String getTurnPlayer(int count){
		return m_order.get(count).getName();
	}
	
	public void play(){
		
	}
	
	/**
	 * Sets up the board for the game. The grid size is defined as the attributes BOARDSIZEX and BOARDSIZEY
	 * 
	 */
	public void boardSetUp(){
		//Final variables used to control positioning of objects on the interface
		final int EDGE_BUFFER = 10;
		final int SIZE_X = 800;
		final int SIZE_Y = 660;
		final int PANEL_DIMENSIONS = 600;
		final int INTERFACE_X_POS = 650;
		final int INTERFACE_VERTICAL_SPACING = 10;
		final int INTERFACE_X_SIZE = 100;
		final int INTERFACE_Y_SIZE = 30;
		
		//Creates the window the game is displayed in
		m_window.setSize(SIZE_X, SIZE_Y);
		m_window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		m_window.setLayout(null);
		
		//Creates a panel that holds the grid the game is played on
		JPanel panel = new JPanel();
		panel.setSize(PANEL_DIMENSIONS, PANEL_DIMENSIONS);
		panel.setLocation(EDGE_BUFFER, EDGE_BUFFER);
		panel.setLayout(new GridLayout(BOARDSIZEX,BOARDSIZEY));
		m_window.add(panel);
		
		//creates and positions a reset button
		JButton reset = new JButton("Reset");
		reset.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		reset.setLocation(INTERFACE_X_POS, INTERFACE_VERTICAL_SPACING);
		m_window.add(reset);
		reset.addActionListener(resetListener);
		
		//creates and positions an exit button. It's location is relevant to the position of the reset button
		JButton exit = new JButton("Exit");
		exit.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		exit.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + reset.getHeight()) + reset.getY());
		m_window.add(exit);
		exit.addActionListener(exitListener);
		
		/**
		 * Creates labels to show the player's names.
		 * The current turn player's name is bolded to show that it is their turn.
		 * They are positioned relative to the exit button, and the other label, respectively
		 */
		m_player1Name.setText("P1: " + m_order.get(0).getName());
		m_player2Name.setText("P2: " + m_order.get(1).getName());
		m_player1Name.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		m_player2Name.setFont(m_defaultFont);
		m_player1Name.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + exit.getHeight()) + exit.getY());
		m_player2Name.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		m_player2Name.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + m_player1Name.getHeight()) + m_player1Name.getY());
		m_window.add(m_player1Name);
		m_window.add(m_player2Name);
		/**
		 * Adds the timer label defined in the class attributes to the board
		 */
		m_time.setSize(INTERFACE_X_SIZE, INTERFACE_Y_SIZE);
		m_time.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + m_player2Name.getHeight() + m_player2Name.getY()));
		m_window.add(m_time);
		//starts the timer
		m_timerDisplay.start();
		/**
		 *Loop to create the grid of buttons that the game is played on.
		 *These buttons are created in a two dimensional array.
		 *They are then added to the panel.
		 */
		for(int i = 0; i< BOARDSIZEX; i++)	{
			for (int j = 0; j < BOARDSIZEY; j++ ){
				m_buttons[i][j] = new JButton();
				panel.add(m_buttons[i][j]);
				m_buttons[i][j].addActionListener(listener);
			}
			
		}
		
		//Makes the game window visible
		m_window.setVisible(true);

	}
	
	/**
	 * This action listener is used to reset the game when the reset button is clicked
	 * It re-enables all of the buttons and resets all their text, and changes their backgrounds back to the default color
	 * It sets the "hasWon" status of both players to false
	 * It sets the turn count to 0
	 * It stops the timer that causes the winning chain to flash
	 * Finally, it resets the displayed timer's count to 0, and restarts it
	 */
	ActionListener resetListener = new ActionListener() {
		public void actionPerformed(ActionEvent reset) {
			for(int i = 0; i< BOARDSIZEX; i++)	{
				for (int j = 0; j < BOARDSIZEY; j++ ){
					m_buttons[i][j].setEnabled(true);
					m_buttons[i][j].setText("");
				}
			}
			m_order.get(0).setHasWon(false);
			m_order.get(1).setHasWon(false);
			m_turnCount = 0;
			flashtimer.stop();
			for (int i = 0; i < m_winningButtons.size(); i++){
					m_winningButtons.get(i).setBackground(m_oldBackground);
			}
			m_timerDisplay.start();
			m_timeCount = 0;
			m_minutes = 0;
		}
	};
	
	/**
	 * This action listener is called whenever a tile on the board is clicked.
	 * It checks the turn player, then changes the text on the clicked button to be that player's symbol
	 * It also disables the button, so that it cannot be clicked again
	 * It then calls the methods to check whether a chain of 5 has been made
	 * Finally, it swaps which player name is bolded, increases the turn counter and checks for a tie
	 */
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent turn) {
			
			

			if (getTurnPlayer() == getTurnPlayer(0)){
				m_letter = "X";
			} else {
				m_letter = "O";
			}

			//Write the m_letter to the button and deactivate it
			JButton pressedButton = (JButton)turn.getSource(); 
			pressedButton.setText(m_letter);
			pressedButton.setEnabled(false);
			int buttonX = 0;
			int buttonY = 0;
			for(int i = 0; i< BOARDSIZEX; i++)	{
				for (int j = 0; j < BOARDSIZEY; j++ ){
					if (m_buttons[i][j] == pressedButton){
						buttonX = i;
						buttonY = j;
					}
				}
			}
			if (m_order.get(m_turnCount).getWon() == false){
				checkHorizontalChain(buttonX, buttonY);
			} 
			if (m_order.get(m_turnCount).getWon() == false){
				checkVerticalChain(buttonX, buttonY);
			} 
			if (m_order.get(m_turnCount).getWon() == false){
				checkDiagonalChain(buttonX, buttonY);
			}
			if (m_order.get(m_turnCount).getWon() == true){
				JOptionPane.showMessageDialog(null,  m_order.get(m_turnCount).getName() + " has won!");
			}
			if (m_turnCount % 2 == 0){
				m_player2Name.setFont(m_boldFont);
				m_player1Name.setFont(m_defaultFont);
			} else {
				m_player1Name.setFont(m_boldFont);
				m_player2Name.setFont(m_defaultFont);	
			}
			m_turnCount++;
			checkTie();

		}
	};
	
	/**
	 * This action listener is triggered when the exit button is clicked.
	 * It hides the game window
	 */
	ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent exit) {
			m_window.hide();
		}	
	};
	
	/**
	 * This method disables all of the buttons
	 * It is called when either the game is tied or a player has won
	 * The buttons cannot be un-disabled until the game is reset or closed
	 * 
	 * \see checkTed
	 * \see m_winningButtons
	 * 
	 */
	public void disableButtons(){
		for(int i = 0; i< BOARDSIZEX; i++)	{
			for (int j = 0; j < BOARDSIZEY; j++ ){
				m_buttons[i][j].setEnabled(false);
			}
		}
		m_oldBackground = m_winningButtons.get(0).getBackground();
		m_player1Name.setFont(m_boldFont);
		m_player2Name.setFont(m_defaultFont);
		if (m_tied == false){
			flashtimer.start();
		}
		m_timerDisplay.stop();
	}

}
