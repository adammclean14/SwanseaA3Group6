public class Test {
	public static void main(String[] args) throws InterruptedException{
		GameTimer timer = new GameTimer();
		timer.startTime();
		Thread.sleep(5000);
		System.out.println(timer.getTime());
	}
}
