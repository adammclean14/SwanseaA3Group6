public class ComputerAI extends Player{
	private int m_delay;

	ComputerAI(){

	}

	ComputerAI(int delay){
		m_delay = delay;
	}

	public void setDelay(int seconds){
		m_delay = seconds;
	}
	
	public int getDelay(){
		return m_delay;
	}
	
}