public class Dice {
	
	private static int m_dieRoll;
	private static final int MIN_ROLL = 1;
	private static final int MAX_ROLL = 6;
	
	
	public Dice() {
		
	}
	
	public static int getNewRoll(){
		m_dieRoll =  MIN_ROLL + (int)(Math.random()*MAX_ROLL); 
		return m_dieRoll;
	}
	public static int getPrevValue(){
		return m_dieRoll;
	}


}
