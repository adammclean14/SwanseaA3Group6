import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LabelTimer extends JLabel implements ActionListener {
	private int m_hrs;          
	private int m_mins;         
	private int m_secs;         
	private int m_hsecs;   

	private String m_nums[];    
	private boolean m_running;  
	private boolean m_update;

	private Timer m_alarm; 

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


	public void timerStart() {
		m_running = true;
		m_update = true;
	}


	public void timerStop() {
		m_running = false;
		m_update = true;
	}

	public void timerNoUpdate() {
		m_update = false;
	}


	public void updateText() {
		setText( m_nums[ m_hrs ]+ ":" + m_nums[ m_mins ] + ":" + m_nums[ m_secs ] + "." + m_nums[m_hsecs] );
	}

}