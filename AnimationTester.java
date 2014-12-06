import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JButton;
public class AnimationTester implements Runnable{

	JFrame frame;
	JFrame frame2;
	DrawPanel drawPanel;
	JButton rollBtn;
	JLabel diceLbl;
	private int oneX = 25;
	private int oneY = 700;
	boolean left = false;
	boolean right = true;
	boolean forward = true;
	static String imageLocation;
	boolean yes;
	public static void main(String[] args) {
		new AnimationTester().go();

		
		System.out.println("Main thread finished!");
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
					//THIS PROBABLY HAS TO BE A DIFFERENT FUNCTION

					rollBtn.setText("Start Roll");
					int diceRoll = getRandNum();
					String thing = diceRoll +"dice.gif";
					java.awt.Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
					diceLbl.setIcon(new ImageIcon(image));
					//System.out.println(thing);	
					moveIt(diceRoll);
					i = i -1;


				}

			}
		});




		moveIt(15);


	}

	public void run(){
		moveIt(15);
	}


	public int getRandNum(){
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		//Creates string for image location
		return randomNum;
	}



	class DrawPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			//paints oval   
			g.setColor(Color.GREEN);
			g.fillOval(oneX, oneY, 20, 20);

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




			//Creates a ladder
			Graphics2D g2d=(Graphics2D)g; 
			java.awt.Image image = new ImageIcon (this.getClass().getResource("/ladder.png")).getImage();
			java.awt.Image image2 = new ImageIcon (this.getClass().getResource("/snake.gif")).getImage();  

			AffineTransform trans = new AffineTransform();
			trans.scale(0.2, 0.2); 
			trans.translate(800, 80);
			trans.rotate( Math.toRadians(45) );
			g2d.drawImage(image, trans, this);
			
			//-----Drawing 2nd ladder
			AffineTransform trans2 = new AffineTransform();            	    	

			//The problem is transform functions go from bottom to top so ...
			trans2.scale(0.2, 0.2); 
			//So 
			trans2.translate(2600, 1600 );
			trans2.rotate( Math.toRadians(135) );

			g2d.drawImage(image, trans2, this);
			//------Done Drawing 2nd Ladder

			//-----Drawing 3rd ladder
			AffineTransform trans3 = new AffineTransform();            	    	

			trans3.scale(0.2, 0.2); 
			//So 
			trans3.translate(2650, 1600 );
			trans3.rotate( Math.toRadians(0) );

			g2d.drawImage(image, trans3, this);
			//------Done Drawing 3rd Ladder

			//-----Drawing 4th ladder
			AffineTransform trans4 = new AffineTransform();            	    	

			trans4.scale(0.2, 0.4); 
			//So 
			trans4.translate(1525, 1000 );
			trans4.rotate( Math.toRadians(0) );

			g2d.drawImage(image, trans4, this);
			//------Done Drawing 4th Ladder




			//------------------------------------
			//-----------SNAKE DRAWINGS-----------
			//------------------------------------

			AffineTransform trans5 = new AffineTransform();


			//creating transform variable for drawing image
			trans5.scale(0.75, 0.75); 
			trans5.translate(220, 975);
			trans5.rotate( Math.toRadians(270) );

			//drawing a SNAKE
			g2d.drawImage(image2, trans5, this);


			//-----Drawing 2nd SNAKE
			AffineTransform trans6 = new AffineTransform();            	    	

			//The problem is transform functions go from bottom to top so ...
			trans6.scale(0.5, 0.5); 

			trans6.translate(1250, 425 );
			trans6.rotate( Math.toRadians(225) );

			g2d.drawImage(image2, trans6, this);
			//------Done Drawing 2nd SNAKE

			//-----Drawing 3rd SNAKE
			AffineTransform trans7 = new AffineTransform();            	    	

			trans7.scale(0.75, 0.75); 
			trans7.translate(500, 650 );
			trans7.rotate( Math.toRadians(290) );

			g2d.drawImage(image2, trans7, this);
			//------Done Drawing 3rd SNAKE

			//-----Drawing 4th SNAKE
			AffineTransform trans8 = new AffineTransform();            	    	

			trans8.scale(0.5, 0.25); 
			//So 
			trans8.translate(500, 700 );
			trans8.rotate( Math.toRadians(-45) );

			g2d.drawImage(image2, trans8, this);
			//------Done Drawing 4th SNAKE


		}
	}



	private void moveIt(int diceRoll) {




		int pixelCount = 75 * diceRoll;

		

		while(pixelCount > 0){
			if(oneX == 700){
				right = false;
				left = true;
				forward = false;
				oneY--;
				pixelCount --;
				frame.repaint();

			}
			if(oneX == 24){
				right = false;
				left = true;
				forward = true;
				oneY--;
				pixelCount --;
				frame.repaint();

			}

			if ((oneY- 700) % 75 == 0) {
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
			
			//
			//if (pixelCount == 0 && oneX == 0 && oneX ==225)
		}
	}
}
