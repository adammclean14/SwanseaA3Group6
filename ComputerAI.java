public class ComputerAI extends Player{
	private int m_delay;

	ComputerAI(){

	}

	ComputerAI(int delay){
		m_delay = delay;
	}

	Player(String name, Color color, Boolean hasWon, int delay){
		m_name = name;
		m_pieceColor = color;
		m_hasWon = hasWon;
		m_delay = delay;
	}

	public void setDelay(int seconds){
		m_delay = seconds;
	}

	public int getDelay(){
		return m_delay;
	}
	
}