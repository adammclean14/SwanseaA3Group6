
public class GameObjectClass {

  private int m_xCoord;
  private int m_yCoord;
  private int m_width;
  private int m_height;
  
  //GETS X,Y COORDS, AND HEIGHT AND WIDTH.
	
	//Gets current X coordinate of the component's origin relative to the parent's upper left corner (measured in pixels)
	public int getX() {
		return m_xCoord;
	}
	
	//Gets current Y coordinate of the component's origin relative to the parent's upper left corner (measured in pixels)
	public int getY() {
		return m_yCoord;
	}	
	
	//Returns the current width of this component
	public int getWidth(){
		return m_width;
	}
	
	//Returns the current height of this component
	public int getHeight(){
		return m_height;
	}		
}
