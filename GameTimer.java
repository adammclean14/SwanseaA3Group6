import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jon Haddow
 *
 */
public class GameTimer{

	/*Fields to store seconds, minutes and hours*/
	private int m_seconds;
	private int m_minutes;
	private int m_hours;
	/*Constant which stores the interval in milliseconds between each interval*/
	private int m_updateInterval = 1000;
	/*Field which stores whether the timer is currently running*/
	private Boolean m_running;

	/**
	 * Constructor which automatically sets timer to zero and starts timer.
	 */
	public GameTimer() {
		setTime(0,0,0);
		startTime();
	}
	/**
	 * Sets the current time.
	 * @param hours as integer.
	 * @param minutes as integer.
	 * @param seconds as integer.
	 */
	public void setTime(int hours, int minutes, int seconds){
		this.m_hours = hours;
		this.m_minutes = minutes;
		this.m_seconds = seconds;
	}
	/**
	 * Gets the current time.
	 * @return String showing hours, minutes and seconds the timer is currently on.
	 */
	public String getTime(){
		return (m_hours + ":" + m_minutes + ":" + m_seconds);
	}
	/**
	 * Checks if timer is currently running.
	 * @return Boolean True if timer is running. False if not.
	 */
	public Boolean isRunning(){
		return m_running;
	}
	/**
	 * Starts timer by setting running to true.
	 */
	public void startTime(){
		m_running = true;
		int delay = 0;   // delay for 0 second
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				updateTime();
				getTime();
			}
		}, delay, m_updateInterval);
	}
	/**
	 * update the minutes and seconds when the timer is running.
	 */
	public void updateTime(){
		if (m_minutes == 59){
			m_hours++;
			m_minutes = 0;
		}
		else if (m_seconds == 59){
			m_minutes++;
			m_seconds = 0;
		}
		else {
			m_seconds++;
		}
	}
	/**
	 * Resets timer by setting timer fields to 0.
	 */
	public void resetTime(){
		m_hours = 0;
		m_minutes = 0;
		m_seconds = 0;
	}
	/**
	 * Stops timer by setting running to false.
	 */
	public void stopTime(){
		m_running = false;
	}
}
