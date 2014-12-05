import javax.swing.*;

import java.awt.*;

import javax.swing.JButton;
final public class AnimationTester {

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 7;
    private int oneY = 250;
    private int twoX = 7;
    private int twoY = 250;
    
    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
    boolean forward = true;
    public static void main(String[] args) {
        new AnimationTester().go();
    }

    private void go() {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel boardPanel = new JPanel();
		boardPanel.setLayout((new GridLayout(10, 10, 2, 2)));
		boardPanel.setSize(300, 300);
		boardPanel.setBounds(0, 0, 300, 300);
		boardPanel.setBackground(new Color(0,0,155,100));
		//frame.getContentPane().add(boardPanel);
		
		
		
        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLocation(500,0);
        
        //drawBoard();
        moveIt();
        
        
        JButton moveBtn = new JButton("PRESS TO MOVE");
        moveBtn.setBounds(50,100,100,255);
        frame.add(moveBtn);

        
        
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
        	//JPanel panel = new JPanel();
        	//panel.setBackground(new Color(0,0,150,0));
            
            g.setColor(Color.RED);
            	int i = 0;
            	while (i < 10){
                g.drawLine(0, 50*(i-1), 500, 50*(i-1));
                g.drawLine(50*(i-1), 0, 50*(i-1), 500);

            	i++;
            	}
                
                g.setColor(Color.GREEN);

                g.fillOval(oneX, oneY, 20, 20);
            
        }
    }
    	
    
    private void moveIt() {
    	
    	
    	
    	
    	int pixelCount = 1500;
        while(pixelCount > 0){
            if(oneX == 283){
                right = false;
                left = true;
                forward = false;
                oneY--;
                pixelCount --;
            }
            if(oneX == 0){
                right = false;
                left = true;
                forward = true;
                oneY--;
                pixelCount --;
            }
           // if(oneX <= 7){
           //     right = true;
            //    left = false;
           // }
            if (oneY % 25 == 0) {
            	right = true;
            	left = false;
            	
            }
            
            if(left) {
                oneY--;
                pixelCount --;
                
            }  
            
            if(right && forward && left == false){
                oneX++;  
                pixelCount --;
                } 
            
            if(right && forward == false && left == false){
            	oneX--;
            	pixelCount --;
            }
            
            
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
}
