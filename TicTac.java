import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;

public class TicTac extends GameLogic /*implements Runnable*/ {
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
	ActionListener countAction = new ActionListener(){
		public void actionPerformed(ActionEvent count){
			m_timeCount++;
			if (m_timeCount == 60){
				m_minutes++;
				m_timeCount = 0;
			}
			if (m_timeCount < 10){
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

	//! The first constructor for the class
	/*!
	 * adds players to the array for each possible turn in the game
	 * when the end of the array is reached, there are no more moves
	 * able to be made, so if nobody has won the game ends in a 
	 * draw. All three constructors are the same
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

	public TicTac (){
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	//! The second constructor for the class
	/*!
	 * adds players to the array for each possible turn in the game
	 * when the end of the array is reached, there are no more moves
	 * able to be made, so if nobody has won the game ends in a 
	 * draw. All three constructors are the same
	 */
	public TicTac (Human player_1, TicTacLogic player_2){
		for (int i = 0; i < 50; i++){
			m_order.add(player_1);
			m_order.add(player_2);
		}
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	//! The second constructor for the class
	/*!
	 * adds players to the array for each possible turn in the game
	 * when the end of the array is reached, there are no more moves
	 * able to be made, so if nobody has won the game ends in a 
	 * draw. All three constructors are the same
	 */
	public TicTac (TicTacLogic player_1, TicTacLogic player_2){
		for (int i = 0; i < 50; i++){
			m_order.add(player_1);
			m_order.add(player_2);
		}
		m_gameWon = false;
		m_turnCount = 0;
		m_tied = false;
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
	}
	public int GetTurnNumber(){
		/**
		 * returns the current turn number
		 */
		return m_turnCount;
	}
	public void ChangeTurnPlayer(){
		/**
		 * Adds one to the turn count.
		 * This changes the current turn
		 * player
		 */
		m_turnCount++;
	}
	public void CheckTie(){
		if (m_turnCount == BOARDSIZE){
			m_tied = true;
		}
		if (m_tied){
			JOptionPane.showMessageDialog(null, "Game is a tie");
			disableButtons();
		}
	}
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
		if (chainLength == 5 && checkValue == "O"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		} else if (chainLength == 5 && checkValue == "X"){
			m_order.get(m_turnCount).setHasWon(true);
			disableButtons();
		}
	}

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

	public void GetMove(){
	}
	/**
	 * returns the name of the object of the current player
	 * (either player_1 or player_2) allowing the program
	 * to know whether to place a O or an X
	 */
	public String getTurnPlayer(){
		return m_order.get(m_turnCount).getName();
	}
	public String getTurnPlayer(int count){
		return m_order.get(count).getName();
	}
	public void play(){

	}
	public void boardSetUp(){
		//Create m_m_window
		final int EDGE_BUFFER = 10;
		final int SIZE_X = 800;
		final int SIZE_Y = 660;
		final int PANEL_DIMENSIONS = 600;
		final int INTERFACE_X_POS = 650;
		final int INTERFACE_VERTICAL_SPACING = 10;
		final int INTERFACE_X_SIZE = 100;
		final int INTERFACE_Y_SIZE = 30;
		
		m_window.setSize(SIZE_X, SIZE_Y);
		m_window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		m_window.setLayout(null);
		JPanel panel = new JPanel();
		panel.setSize(PANEL_DIMENSIONS, PANEL_DIMENSIONS);
		panel.setLocation(EDGE_BUFFER, EDGE_BUFFER);
		panel.setLayout(new GridLayout(BOARDSIZEX,BOARDSIZEY));
		m_window.add(panel);
		JButton reset = new JButton("Reset");
		reset.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		reset.setLocation(INTERFACE_X_POS, INTERFACE_VERTICAL_SPACING);
		m_window.add(reset);
		//Add m_buttons To The m_m_window
		reset.addActionListener(resetListener);
		JButton exit = new JButton("Exit");
		exit.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		exit.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + reset.getHeight()) + reset.getY());
		m_window.add(exit);
		exit.addActionListener(exitListener);
		m_player1Name.setText("P1: " + m_order.get(0).getName());
		m_player2Name.setText("P2: " + m_order.get(1).getName());
		m_player1Name.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		m_player2Name.setFont(m_defaultFont);
		m_player1Name.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + exit.getHeight()) + exit.getY());
		m_player2Name.setSize(INTERFACE_X_SIZE,INTERFACE_Y_SIZE);
		m_player2Name.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + m_player1Name.getHeight()) + m_player1Name.getY());
		m_window.add(m_player1Name);
		m_window.add(m_player2Name);
		m_time.setSize(INTERFACE_X_SIZE, INTERFACE_Y_SIZE);
		m_time.setLocation(INTERFACE_X_POS,(INTERFACE_VERTICAL_SPACING + m_player2Name.getHeight() + m_player2Name.getY()));
		m_window.add(m_time);
		m_timerDisplay.start();
		for(int i = 0; i< BOARDSIZEX; i++)	{
			for (int j = 0; j < BOARDSIZEY; j++ ){
				m_buttons[i][j] = new JButton();
				panel.add(m_buttons[i][j]);
				m_buttons[i][j].addActionListener(listener);
			}
			m_window.setVisible(true);
		}

		//Make The m_m_window Visible

	}

	ActionListener resetListener = new ActionListener() {
		public void actionPerformed(ActionEvent reset) {
			for(int i = 0; i< BOARDSIZEX; i++)	{
				for (int j = 0; j < BOARDSIZEY; j++ ){
					m_buttons[i][j].setEnabled(true);
					m_buttons[i][j].setText("");
					m_buttons[i][j].setForeground(Color.WHITE);
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
			CheckTie();

		}
	};
	ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent exit) {
			m_window.hide();
		}	
	};
	public void disableButtons(){
		for(int i = 0; i< BOARDSIZEX; i++)	{
			for (int j = 0; j < BOARDSIZEY; j++ ){
				m_buttons[i][j].setEnabled(false);
			}
		}
		m_oldBackground = m_winningButtons.get(0).getBackground();
		m_player1Name.setFont(m_boldFont);
		m_player2Name.setFont(m_defaultFont);
		flashtimer.start();
		m_timerDisplay.stop();
		//run();
	}

}
