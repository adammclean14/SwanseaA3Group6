import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
	
    private JFrame window = new JFrame("Dans Crazy Tic Tac Toe!");
    private JButton buttons[] = new JButton[64];
    private int m_count = 0;
    private String m_letter = "";
    private boolean win = false;
    private String imageLocation;
    
    public TicTacToe()	{
    	
    //Create window
    window.setSize(800,800);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLayout(new GridLayout(8,8));
    
    //Add Buttons To The window
    for(int i=0; i<=63; i++)	{
        buttons[i] = new JButton();
        window.add(buttons[i]);
        buttons[i].addActionListener(this);
    }
    
    //Make The window Visible
    window.setVisible(true);
    }
    
    /**
     When an object is clicked, perform an action.
     @param a action event object
     */
    public void actionPerformed(ActionEvent a) {
        m_count++;
        
        
        //Calculate whose turn it is
        if(m_count % 2 == 0)	{
            //m_letter = "O";  
        	imageLocation = "O.jpg";
        } else {
            //m_letter = "X";
            imageLocation = "X.jpg";
        }

        //Write the m_letter to the button and deactivate it
         JButton pressedButton = (JButton)a.getSource(); 
		//pressedButton.setText(m_letter);
         Image image = new ImageIcon (this.getClass().getResource(imageLocation)).getImage();
         pressedButton.setIcon(new ImageIcon(image));
         pressedButton.setEnabled(false);
        
    }
    
    public static void main(String[] args)	{
        TicTacToe starter = new TicTacToe();
    }
}
