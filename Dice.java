import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public  class ImageTesting extends JFrame{
	private JButton diceRollbtn;
	private JFrame frame;
	static String imageLocation;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageTesting window = new ImageTesting();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	//Calls different object creations
	//Make the parameters a string (for image, i.e. "/Ladder.jpg"), then start and end points as floats (for JLabel bounds)
	//Figure out how to stretch image
	public ImageTesting(){
		//Calls create image with the location of the image as a parameter
		//Will work on adding bounds and shifting size of image
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createImage(getDiceNumber());
		
	}
	
	//private  void showNumber(){
	//	createImage(getDiceNumber());
		
	//}
	
	public  String getDiceNumber (){
		//Creates rand number from 1 to 6
		int max = 6;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		//Creates string for image location
		return imageLocation = randomNum + "dice.gif";
		
	}
	
	
	public void  createImage (String imageLocation){
		//Should this be in its own function called from like game logic or main whenever the game starts so the frame doesn't keep getting remade as new
		
		
		
		final JLabel label1 = new JLabel("");
		Image image = new ImageIcon (this.getClass().getResource(imageLocation)).getImage();
		label1.setIcon(new ImageIcon(image));
		label1.setBounds(40, 78, 40, 40);
		//final label2 = label1;
		
		diceRollbtn = new JButton("Start Roll");
		diceRollbtn.setBounds(20, 118, 80, 40);
		diceRollbtn.addMouseListener(new MouseAdapter() {
			int i = 0;
			public void mousePressed(MouseEvent e){
				if (i % 2 == 0){
					diceRollbtn.setText("End Roll");
				Image gif = new ImageIcon (this.getClass().getResource("/diceFast.gif")).getImage();
				label1.setIcon(new ImageIcon(gif));
				i = i + 1;
				}
				else{
					diceRollbtn.setText("Start Roll");
			
				String thing = getDiceNumber();
				Image image = new ImageIcon (this.getClass().getResource(thing)).getImage();
				label1.setIcon(new ImageIcon(image));
				System.out.println(thing);
					
				i = i -1;
				 
				
				}	
				
				
		}
		});
		frame.getContentPane().add(diceRollbtn);
		frame.getContentPane().add(label1);


				
	}

}
