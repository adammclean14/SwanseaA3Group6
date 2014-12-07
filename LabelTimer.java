import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * @file LabelTimer.java
 * @author Alan Woolley
 * @date 6 Dec 2014
 *
 * This Class creates a JLabel with the property of setting its text to be a counter
 */

public class LabelTimer extends JLabel implements ActionListener {
	
	//Member Variables
	private int m_hrs;          
	private int m_mins;         
	private int m_secs;         
	private int m_hsecs;   
	private String m_nums[];    
	private boolean m_running;  
	private boolean m_update;

	//Creates a new Timer Object
	private Timer m_alarm; 

	
	/**
	 * 
	 * Constructor for the LabelTimer Class, this calls the constructor for a JLabel and sets 2 properties. Its default Text, and its location.
	 * The Rest of the constructor fills a Array of strings with values from 0-99
	 */
	public LabelTimer() {
		super( "00:00:00.00", JLabel.CENTER );

		m_nums = new String[ 100 ];
		for ( int i = 0; i < 100; i++ ) {
			m_nums[ i ]= Integer.toString( i );
			if ( i < 10 ){ 
				m_nums[ i ] = "0" + m_nums[ i ];
			}
		}
		m_alarm = new Timer( 10, this );
		m_alarm.start();
		timerReset();
	}

	/**
	 * Action listener to update the time this gets called on each 'Tick'
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed( ActionEvent ev ) {
		if (m_running) {
			m_hsecs++;

			if (m_hsecs==100) {
				m_hsecs=0;
				m_secs++;

				if (m_secs==60) {
					m_secs=0;
					m_mins++;

					if (m_mins==60) {
						m_mins=0;
						m_hrs=(m_hrs+1)%100;
					}
				}
			}

			if (m_update) updateText();
		}

		m_alarm.restart();
	}
	/**
	 * 
	 * Method to reset the timer, resets all the member variables
	 */
	public void timerReset() {
		m_alarm.stop();
		m_hrs=0;
		m_mins=0;
		m_secs=0;
		m_hsecs=0;
		updateText();
		m_running=false;
		m_update=true;
		m_alarm.restart();
	}

	/**
	 * Starts the Timer
	 * 
	 */
	public void timerStart() {
		m_running = true;
		m_update = true;
	}

	/**
	 * Stops the timer
	 * 
	 */
	public void timerStop() {
		m_running = false;
		m_update = true;
	}

	/**
	 * 
	 * Updates the text from the array m_nums
	 */
	public void updateText() {
		setText( m_nums[ m_hrs ]+ ":" + m_nums[ m_mins ] + ":" + m_nums[ m_secs ] + "." + m_nums[m_hsecs] );
	}
	

}