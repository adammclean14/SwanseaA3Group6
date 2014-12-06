import java.util.ArrayList;

public class Mover {
	private ArrayList<Integer> m_moverArray = new ArrayList<>();
	private int m_numberofMovers;
	private static final int MIN = 1;
	private static final int MAX = 10;
	
	public Mover(int numberofMovers) {
		this.m_numberofMovers = numberofMovers;
		createMoverArray(this.m_numberofMovers);
	}
	
	private void createMoverArray(int numberofMovers){
		int n = numberofMovers;
		for(int i = 0; i < n;i++){
			m_moverArray.add(MIN + (int)(Math.random()*MAX));
		}
		
	}
	public ArrayList<Integer> getMoverArrayList(){
		return m_moverArray;
	}
	


}