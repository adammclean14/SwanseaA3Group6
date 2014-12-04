import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

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


	public ArrayList<Player> m_order = new ArrayList<Player>();
	public ArrayList<JButton> m_winningButtons = new ArrayList<JButton>();
	public Boolean m_gameWon;
	public Boolean m_tied;
	public int m_turnCount;
	public int m_player1LongestChain;
	public int m_player2LongestChain;
	public final int m_boardSizeX = 8;
	public final int m_boardSizeY = 8;
	public final int m_boardSize = m_boardSizeX * m_boardSizeY;
	public JFrame m_window = new JFrame("Dans Crazy Tic Tac Toe!");
	public JButton m_buttons[][] = new JButton[m_boardSizeX][m_boardSizeY];
	public String m_letter = "";
	public Timer flashtimer = new Timer(500, timerAction);
	public Color m_oldBackground = Color.BLACK;

	//! The first constructor for the class
	/*!
	 * adds players to the array for each possible turn in the game
	 * when the end of the array is reached, there are no more moves
	 * able to be made, so if nobody has won the game ends in a 
	 * draw. All three constructors are the same
	 */
	public TicTac (Human player_1, Human player_2){
		for (int i = 0; i < m_boardSize -1; i++){
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
		if (m_turnCount == m_boardSize){
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
		if (changedX < m_boardSizeX){
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
	//	public void checkVerticalChain(int changedX, int changedY){
	//		m_player1LongestChain = 0;
	//		m_player2LongestChain = 0;
	//		for (int i = 0; i < m_boardSizeX; i++){
	//			m_player1LongestChain = 0;
	//			m_player2LongestChain = 0;
	//			for (int j = 0; j < m_boardSizeY; j++){
	//				if (m_buttons[i][j].getText() == "O"){
	//					m_player2LongestChain = 0;
	//					m_player1LongestChain++;
	//				}
	//				if (m_buttons[i][j].getText() == "X"){
	//					m_player1LongestChain = 0;
	//					m_player2LongestChain++;
	//				}
	//				if (m_player1LongestChain == 5){
	//					JOptionPane.showMessageDialog(null,  "Player 1 wins");
	//				}
	//				if (m_player2LongestChain == 5){
	//					JOptionPane.showMessageDialog(null,  "Player 2 Wins");
	//				}
	//			}
	//		}
	//	}
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
		if (changedY < m_boardSizeY){
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
	//	public void checkVerticalChain(int changedX, int changedY){
	//		m_player1LongestChain = 0;
	//		m_player2LongestChain = 0;
	//		for (int j = 0; j < m_boardSizeX; j++){
	//			m_player1LongestChain = 0;
	//			m_player2LongestChain = 0;
	//			for (int i = 0; i < m_boardSizeY; i++){
	//				if (m_buttons[i][j].getText() == "O"){
	//					m_player2LongestChain = 0;
	//					m_player1LongestChain++;
	//				}
	//				if (m_buttons[i][j].getText() == "X"){
	//					m_player1LongestChain = 0;
	//					m_player2LongestChain++;
	//				}
	//				if (m_player1LongestChain == 5){
	//					JOptionPane.showMessageDialog(null,  "Player 1 wins");
	//				}
	//				if (m_player2LongestChain == 5){
	//					JOptionPane.showMessageDialog(null,  "Player 2 Wins");
	//				}
	//			}
	//		}
	//	}
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
		if (changedX < m_boardSizeX && changedY < m_boardSizeY){
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
		if (changedX < m_boardSizeX && changedY >= 0){
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
		if (changedX >= 0 && changedY < m_boardSizeY){
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
	//	public void CheckLongestChain(int changedX, int changedY){
	//		
	//		
	//		
	//		
	//		if (m_buttons[changedX][changedY].getText() == "O"){
	//			m_player1LongestChain = 1;
	//		} else if (m_buttons[changedX][changedY].getText() == "X"){
	//			m_player2LongestChain = 1;
	//		}
	//		int count = 1;
	//		for (int i = changedX; i < i +4; i ++){
	//			if ((changedX + count) < (m_boardSizeX -1) && (changedY + count) < (m_boardSizeY - 1)){
	//				if (m_buttons[changedX + count][changedY + count].getText() == "O"){
	//					m_player1LongestChain++;
	//				} else if (m_buttons[changedX + count][changedY + count].getText() == "X"){
	//					m_player2LongestChain++;
	//				}
	//				count++;
	//			}
	//			
	//		}
	//		count = 1;
	//		for (int i = changedX; i < i - 4; i-- ){
	//			if (i < 0 && changedY < 0){
	//				if (m_buttons[changedX - count][changedY - count].getText() == "O"){
	//					m_player1LongestChain++;
	//				} else if (m_buttons[changedX - count][changedY - count].getText() == "X"){
	//					m_player2LongestChain++;
	//				}
	//				count++;
	//			}
	//		}
	//	}

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
		m_window.setSize(800,660);
		m_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_window.setLayout(null);
		JPanel panel = new JPanel();
		panel.setSize(600,600);
		panel.setLocation(10, 10);
		panel.setLayout(new GridLayout(8,8));
		m_window.add(panel);
		JButton reset = new JButton("Reset");
		reset.setSize(100,30);
		reset.setLocation(650, 300);
		m_window.add(reset);
		//Add m_buttons To The m_m_window
		reset.addActionListener(resetListener);
		for(int i = 0; i< m_boardSizeX; i++)	{
			for (int j = 0; j < m_boardSizeY; j++ ){
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
			for(int i = 0; i< m_boardSizeX; i++)	{
				for (int j = 0; j < m_boardSizeY; j++ ){
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
		}
	};

	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent turn) {

			//Calculate whose turn it is
			//			if(m_turnCount % 2 == 0)	{
			//				m_letter = "X";
			//			} else {
			//				m_letter = "O";
			//			}

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
			for(int i = 0; i< m_boardSizeX; i++)	{
				for (int j = 0; j < m_boardSizeY; j++ ){
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

			m_turnCount++;
			CheckTie();

		}
	};
	public void disableButtons(){
		for(int i = 0; i< m_boardSizeX; i++)	{
			for (int j = 0; j < m_boardSizeY; j++ ){
				m_buttons[i][j].setEnabled(false);
			}
		}
		m_oldBackground = m_winningButtons.get(0).getBackground();
		flashtimer.start();
		//run();
	}
	//	public void run(){
	//		for (int i = 0; i < m_winningButtons.size(); i++){
	//			m_winningButtons.get(i).setVisible(false);
	//		}
	//		try {
	//			Thread.sleep(200);
	//		} catch (InterruptedException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		for (int i = 0; i < m_winningButtons.size(); i++){
	//			m_winningButtons.get(i).setVisible(true);
	//		}
	//		try {
	//			Thread.sleep(200);
	//		} catch (InterruptedException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

}
