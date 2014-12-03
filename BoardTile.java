import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class BoardTile extends JComponent{
               
                public void paintComponent(Graphics g) {
                super.paintComponent(g);
                }
               
                // Produces a 8 x 8 grid for Tic Tac Toe
                public void createTicTacBoard(){
                        JFrame ticTacBoard = new JFrame("Tic Tac Toe");
                        ticTacBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ticTacBoard.setLayout((new GridLayout(8, 8, 2, 2)));
                       
                        for (int m = 0; m < 64; m++){
                                JButton button = new JButton();
                                button.addActionListener(new ActionListener(){
                                         
                            public void actionPerformed(ActionEvent e)
                            {
                                //code for what happens when button is pressed
                            }
                        });
                                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                ticTacBoard.add(button);       
                        }
                        ticTacBoard.setSize(750, 750);
                        ticTacBoard.setVisible(true);
                }
                               
                // produces a 10 x 10 grid for snakes and ladders
                public void createSnakeLadderBoard(){
                        JFrame snakeLadderBoard = new JFrame("Snakes and Ladders");
                        snakeLadderBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        snakeLadderBoard.setLayout((new GridLayout(10, 10, 2, 2)));
                       
                        //try array of 1-100 with 10 rows incrementing properly
                       
                        int x = 100;
                        int[][] numbers = new int[10][10];
                        boolean ascend = false;
                        boolean swap = false;
                        for (int i = 9; i>=0; i--){
                                for (int j = 9; j>= 0; j--){
                                	if (x == 90 && swap == false) {
                                		x = x - 9;
                                		ascend = true;
                                		swap = true;
                                	} else if (x == 91 && swap == true) {
                                		x = x - 11;
                                		ascend = false;
                                		swap = false;
                                	} else if ( x == 70 && swap == false) {
                                		x = x - 9;
                                		ascend = true;
                                		swap = true;
                                	} else if (x == 71 && swap == true) {
                                		x = x - 11;
                                		ascend = false;
                                		swap = false;
                                	}  else if ( x == 50 && swap == false) {
                                		x = x - 9;
                                		ascend = true;
                                		swap = true;
                                	} else if (x == 51 && swap == true) {
                                		x = x - 11;
                                		ascend = false;
                                		swap = false;
                                	}  else if ( x == 30 && swap == false) {
                                		x = x - 9;
                                		ascend = true;
                                		swap = true;
                                	} else if (x == 31 && swap == true) {
                                		x = x - 11;
                                		ascend = false;
                                		swap = false;
                                	}  else if ( x == 10 && swap == false) {
                                		x = x - 9;
                                		ascend = true;
                                		swap = true;
                                	} else if (x == 11 && swap == true) {
                                		x = x - 11;
                                		ascend = false;
                                		swap = false;
                                	}
                                	if (ascend == false) {
                                        JLabel label2 = new JLabel("" + x);
                                        numbers[i][j] = x;
                                        x = x - 1;
                                        label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                        snakeLadderBoard.add(label2);
                                	} else {
                                            JLabel label2 = new JLabel("" + x);
                                            numbers[i][j] = x;
                                            x = x + 1;
                                            label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                            snakeLadderBoard.add(label2);
                                    	}
                                	}
                                }      
                        snakeLadderBoard.setSize(750,750);
                        snakeLadderBoard.setVisible(true);
        }
 
}
