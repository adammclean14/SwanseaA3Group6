import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JButton;
final public class AnimationTester {

    JFrame frame;
    JFrame frame2;
    DrawPanel drawPanel;
    JButton rollBtn;
    JLabel diceLbl;
    private int oneX = 7;
    private int oneY = 250;
    boolean left = false;
    boolean right = true;
    boolean forward = true;
    static String imageLocation;
    public static void main(String[] args) {
        new AnimationTester().go();
    }

    private void go() {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new DrawPanel();
        frame.getContentPane().add(drawPanel);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.setLocation(500,0);
        
        frame2 = new JFrame("MENU FRAME");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);
		frame2.setLayout(null);
		frame2.setSize(300, 100);
		frame2.setLocation(1000,0);
		
		rollBtn = new JButton("Start Roll");
		rollBtn.setBackground(Color.RED);
		rollBtn.setBounds(0, 0, 75, 75);
		frame2.add(rollBtn);
		
		diceLbl = new JLabel();
		diceLbl.setBounds(100,0,75,75);
		
		java.awt.Image startImage = new ImageIcon (this.getClass().getResource("/1dice.gif")).getImage();
		diceLbl.setIcon(new ImageIcon(startImage));
		
		frame2.add(diceLbl);
		
		rollBtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){
				if (i % 2 == 0){
					rollBtn.setText("End Roll");
					java.awt.Image gif = new ImageIcon (this.getClass().getResource("/diceBig.gif")).getImage();
					diceLbl.setIcon(new ImageIcon(gif));
					i = i + 1;
				}
				else{
					rollBtn.setText("Start Roll");
					String thing = getRandNum();
					java.awt.Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
					diceLbl.setIcon(new ImageIcon(image));
					System.out.println(thing);	
					i = i -1;	
					moveIt();

					
				}
			}
		});
		
		
		
		
	
        
        //moveIt();
        System.out.println("Done");
        
        
        
        
        
    }

    public String getRandNum(){
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		//Creates string for image location
		return imageLocation = randomNum + "dice.gif";
	}
    
    
    
    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            //paints board lines
        	g.setColor(Color.RED);
			int i = 0;
			int j = 0;
			
			//DRAWS NUMBERS
			
			while (j < 11){


				int loc = (j * 10) + 1;
				String locS = Integer.toString(loc);
				g.setColor(Color.BLUE);
				if ((loc + 19) % 20 == 0 ){
					g.drawString(locS, 15, 700 + (-j * 75));

					String locS2 = Integer.toString(loc + 1);
					g.drawString(locS2, 15 + (75*1), 700 + (-j * 75));

					String locS3 = Integer.toString(loc + 2);
					g.drawString(locS3, 15 + (75*2), 700 + (-j * 75));

					String locS4 = Integer.toString(loc + 3);
					g.drawString(locS4, 15 + (75*3), 700 + (-j * 75));

					String locS5 = Integer.toString(loc + 4);
					g.drawString(locS5, 15 + (75*4), 700 + (-j * 75));

					String locS6 = Integer.toString(loc + 5);
					g.drawString(locS6, 15 + (75*5), 700 + (-j * 75));

					String locS7 = Integer.toString(loc + 6);
					g.drawString(locS7, 15 + (75*6), 700 + (-j * 75));

					String locS8 = Integer.toString(loc + 7);
					g.drawString(locS8, 15 + (75*7), 700 + (-j * 75));

					String locS9 = Integer.toString(loc + 8);
					g.drawString(locS9, 15 + (75*8), 700 + (-j * 75));

					String locS10 = Integer.toString(loc + 9);
					g.drawString(locS10, 15 + (75*9), 700 + (-j * 75));

				}
				else{
					g.drawString(locS, 715, 700 + (-j * 75));

					String locS2 = Integer.toString(loc + 1);
					g.drawString(locS2, 715 - (75*1), 700 + (-j * 75));

					String locS3 = Integer.toString(loc + 2);
					g.drawString(locS3, 715 - (75*2), 700 + (-j * 75));

					String locS4 = Integer.toString(loc + 3);
					g.drawString(locS4, 715 - (75*3), 700 + (-j * 75));

					String locS5 = Integer.toString(loc + 4);
					g.drawString(locS5, 715 - (75*4), 700 + (-j * 75));

					String locS6 = Integer.toString(loc + 5);
					g.drawString(locS6, 715 - (75*5), 700 + (-j * 75));

					String locS7 = Integer.toString(loc + 6);
					g.drawString(locS7, 715 - (75*6), 700 + (-j * 75));

					String locS8 = Integer.toString(loc + 7);
					g.drawString(locS8, 715 - (75*7), 700 + (-j * 75));

					String locS9 = Integer.toString(loc + 8);
					g.drawString(locS9, 715 - (75*8), 700 + (-j * 75));

					String locS10 = Integer.toString(loc + 9);
					g.drawString(locS10, 715 - (75*9), 700 + (-j * 75));
				}
				j++;

			}

			//DRAWS GRID
			g.setColor(Color.RED);
			while (i < 12){
				g.drawLine(0, 75*(i-1), 750, 75*(i-1));
				g.drawLine(75*(i-1), 0, 75*(i-1), 750);

				i++;
			}
                
            	
            	
            	//paints oval   
                g.setColor(Color.GREEN);
                g.fillOval(oneX, oneY, 20, 20);
            
                //Creates a ladder
                Graphics2D g2d=(Graphics2D)g; 
    			java.awt.Image image = new ImageIcon (this.getClass().getResource("/ladder.png")).getImage();  
    			AffineTransform trans = new AffineTransform();
    			trans.scale(0.2, 0.2); 
    			trans.translate(800, 80);
    			trans.rotate( Math.toRadians(45) );
    			g2d.drawImage(image, trans, this);
                
                
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
                frame.repaint();

            }
            if(oneX == 0){
                right = false;
                left = true;
                forward = true;
                oneY--;
                pixelCount --;
                frame.repaint();

            }
          
            if (oneY % 25 == 0) {
            	right = true;
            	left = false;
            	
            }
            
            if(left) {
                oneY--;
                pixelCount --;
                frame.repaint();

            }  
            
            if(right && forward && left == false){
                oneX++;  
                pixelCount --;
                frame.repaint();

                } 
            
            if(right && forward == false && left == false){
            	oneX--;
            	pixelCount --;
                frame.repaint();

            }
            
            
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
}
