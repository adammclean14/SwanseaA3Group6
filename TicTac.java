import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class TicTac extends GameLogic {
	public ArrayList<Player> m_order = new ArrayList<Player>();
	public Boolean m_gameWon;
	public Boolean m_tied;
	public int m_turnCount;
	public int m_player1LongestChain;
	public int m_player2LongestChain;
	public final int m_boardSizeX = 8;
	public final int m_boardSizeY = 8;
	public final int m_boardSize = m_boardSizeX * m_boardSizeY;
	private JFrame m_window = new JFrame("Dans Crazy Tic Tac Toe!");
    private JButton m_buttons[][] = new JButton[m_boardSizeX][m_boardSizeY];
    private String m_letter = "";
    
    
	
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
		}
	}
	public void CheckLongestChain(JButton pressedButton){
		for (int i = 0; i < m_boardSizeX; i++){
			for (int j = 0; j < m_boardSizeY; j++){
				if (m_buttons[i][j].getText() == "O"){
					m_player2LongestChain = 0;
					m_player1LongestChain++;
				}
				if (m_buttons[i][j].getText() == "X"){
					m_player1LongestChain = 0;
					m_player2LongestChain++;
				}
				if (m_player1LongestChain == 5){
					JOptionPane.showMessageDialog(null,  "Player 1 wins");
				}
				if (m_player2LongestChain == 5){
					JOptionPane.showMessageDialog(null,  "Player 2 Wins");
				}
			}
		}
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
		for (int j = 0; j < m_boardSizeX; j++){
			for (int i = 0; i < m_boardSizeY; i++){
				if (m_buttons[i][j].getText() == "O"){
					m_player2LongestChain = 0;
					m_player1LongestChain++;
				}
				if (m_buttons[i][j].getText() == "X"){
					m_player1LongestChain = 0;
					m_player2LongestChain++;
				}
				if (m_player1LongestChain == 5){
					JOptionPane.showMessageDialog(null,  "Player 1 wins");
				}
				if (m_player2LongestChain == 5){
					JOptionPane.showMessageDialog(null,  "Player 2 Wins");
				}
			}
		}
		m_player1LongestChain = 0;
		m_player2LongestChain = 0;
		for (int j = 0; j < m_boardSizeX; j++){
			for (int i = 0; i < m_boardSizeY; i++){
				if (i < m_boardSizeX - 5 && j < m_boardSizeY - 5){
					m_player1LongestChain = 0;
					m_player2LongestChain = 0;
					for (int k = i; k < 4; k++){
						if (m_buttons[i + k][j + k].getText() == "O"){
							m_player2LongestChain = 0;
							m_player1LongestChain++;
							if (m_player1LongestChain == 5){
							JOptionPane.showMessageDialog(null,  "Player 1 wins");
							}
						}
						if (m_buttons[i + k][j + k].getText() == "X"){
							m_player1LongestChain = 0;
							m_player2LongestChain++;
							if (m_player2LongestChain == 5){
								JOptionPane.showMessageDialog(null,  "Player 2 wins");
							}
							
						}
					}
				}
			}
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
		return "";
	}
	public void play(){
		
	}
	public void boardSetUp(){
		//Create m_m_window
		m_window.setSize(800,800);
		m_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_window.setLayout(new GridLayout(8,8));

		//Add m_buttons To The m_m_window
		for(int i = 0; i< 8; i++)	{
			for (int j = 0; j < 8; j++ ){
				m_buttons[i][j] = new JButton();
				m_window.add(m_buttons[i][j]);
				m_buttons[i][j].addActionListener(listener);
			}

		}

		//Make The m_m_window Visible
		m_window.setVisible(true);
	}
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent a) {
			m_turnCount++;
			//Calculate whose turn it is
			if(m_turnCount % 2 == 0)	{
				m_letter = "X";
			} else {
				m_letter = "O";
			}

			//Write the m_letter to the button and deactivate it
			JButton pressedButton = (JButton)a.getSource(); 
			pressedButton.setText(m_letter);
			pressedButton.setEnabled(false);
			CheckLongestChain(pressedButton);
			CheckTie();

		}
	};

}
