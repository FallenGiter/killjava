package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		
		Semaphore s = new Semaphore(2);
		
		for (int i = 0; i < 10; i++) {
			es.submit(new SeatTakenGame(i + 1, s));
		}
		
		es.shutdown();
	}

}

class SeatTakenGame extends Thread {
	private int no;
	private Semaphore seatCount;
	
	public SeatTakenGame(int no, Semaphore seatCount) {
		this.no = no;
		this.seatCount = seatCount;
	}
	
	@Override
	public void run() {
		if (this.seatCount != null && this.seatCount.availablePermits() > 0) {
			System.out.println("抢座游戏：目前有座位：" + this.seatCount.availablePermits() + "个.");
		} else {
			System.out.println("抢座游戏：目前没有座位罗，" + this.no + "你先打一会酱油吧.");
		}
		
		try {
			this.seatCount.acquire(1);
			
			System.out.println("抢座游戏：" + this.no + "抢到了座位，Lucky him.");

			Thread.sleep(10*1000);
			
			System.out.println("抢座游戏：" + this.no + "用完座位了.");
			
			this.seatCount.release(1);
			
			if (!this.seatCount.hasQueuedThreads() && this.seatCount.availablePermits() == 2) {
				System.out.println("抢座游戏：不玩了，回家洗洗睡.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
