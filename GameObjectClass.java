
public class GameObjectClass {

  private int xCoord;
  private int yCoord;
  private int width;
  private int height;
  
  //GETS X,Y COORDS, AND HEIGHT AND WIDTH.
	
	//Gets current X coordinate of the component's origin relative to the parent's upper left corner (measured in pixels)
	public int getX() {
		return xCoord;
	}
	
	//Gets current Y coordinate of the component's origin relative to the parent's upper left corner (measured in pixels)
	public int getY() {
		return yCoord;
	}	
	
	//Returns the current width of this component
	public int getWidth(){
		return width;
	}
	
	//Returns the current height of this component
	public int getHeight(){
		return height;
	}		
}
