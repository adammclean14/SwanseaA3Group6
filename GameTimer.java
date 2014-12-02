import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jon
 *
 */
public class GameTimer{

	/*Fields to store seconds, minutes and hours*/
	private int seconds;
	private int minutes;
	private int hours;
	/*Constant which stores the interval in milliseconds between each interval*/
	private int updateInterval = 1000;
	/*Field which stores whether the timer is currently running*/
	private Boolean running;

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
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	/**
	 * Gets the current time.
	 * @return String showing hours, minutes and seconds the timer is currently on.
	 */
	public String getTime(){
		return (hours + ":" + minutes + ":" + seconds);
	}
	/**
	 * Checks if timer is currently running.
	 * @return Boolean True if timer is running. False if not.
	 */
	public Boolean isRunning(){
		return running;
	}
	/**
	 * Starts timer by setting running to true.
	 */
	public void startTime(){
		running = true;
		int delay = 0;   // delay for 0 second
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				
			}
		}, delay, updateInterval);
	}
	/**
	 * update the minutes and seconds when the timer is running.
	 */
	public void updateTime(){
		if (minutes > 60){
			hours++;
			minutes = 0;
		}
		else if (seconds > 60){
			minutes++;
			seconds = 0;
		}
		else {
			seconds++;
		}
	}
	/**
	 * Resets timer by setting timer fields to 0.
	 */
	public void resetTime(){
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	/**
	 * Stops timer by setting running to false.
	 */
	public void stopTime(){
		running = false;
	}
}
