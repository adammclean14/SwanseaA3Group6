import java.util.ArrayList;
public class TicTac extends GameLogic {
	public ArrayList<Player> m_order = new ArrayList<Player>();
	public Boolean m_gameWon;
	public Boolean m_tied;
	public int m_turnCount;
	public int m_player1LongestChain;
	public int m_player2LongestChain;
	public final int m_boardSize = 8 * 8;
	
	public TicTac (Human player_1, Human player_2){
		/*adds players to the array for each possible turn in the game
		 * when the end of the array is reached, there are no more moves
		 * able to be made, so if nobody has won the game ends in a 
		 * draw
		 */
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
		return m_turnCount;
	}
	public void ChangeTurnPlayer(){
		m_turnCount++;
	}
	public void CheckTie(){
		if (m_order.get(0).getHasWon() == false && m_order.get(1).getHasWon() == false) {
			if (m_turnCount == m_boardSize){
				m_tied = true;
			}
		}
	}
	public void CheckLongestChain(){
	}
	public void GetMove(){
	}
	public String getTurnPlayer(){
		return m_order.get(m_turnCount).getClass().getSimpleName();
	}
}
