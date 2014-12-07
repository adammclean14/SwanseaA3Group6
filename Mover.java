import java.util.ArrayList;

public class Mover {
	private ArrayList<Integer> m_moverArray = new ArrayList<>();
	private int m_numberofMovers;
	private static final int MIN = 1;
	private static final int MAX = 10;
	
	public Mover(int numberofMovers) {
		this.m_numberofMovers = numberofMovers;
		createMoverArray(this.m_numberofMovers);
		print();
	}
	
	private void createMoverArray(int numberofMovers){
		do{
			int temp = MIN + (int)(Math.random()*MAX);
			if(numberofMovers > 10){
				break;
			}
			if(!m_moverArray.contains(temp)){
				m_moverArray.add(temp);
			}
			
			
		}while(m_moverArray.size() < numberofMovers );
		
	}
	public ArrayList<Integer> getMoverArrayList(){
		return m_moverArray;
	}
	
	public void print(){
		for(int s : m_moverArray){
			System.out.println(s);
		}
	}
}
