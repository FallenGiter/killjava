package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 等待所有线程完成
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws Exception {
		int n = 10;
		ExecutorService es = Executors.newFixedThreadPool(n);
		CountDownLatch startCdl = new CountDownLatch(1);
		CountDownLatch endCdl = new CountDownLatch(n);
		
		for (int i = 0; i < n; i++) {
			es.submit(new Racer(startCdl, endCdl, i));
		}
		
		System.out.println("比赛游戏：开始.");
		startCdl.countDown(); // 教练响枪，比赛开始
		System.out.println("比赛游戏：选手开始拼命奔跑啦.");
		endCdl.await(); // 等最后一个人冲线
		System.out.println("比赛游戏：所有选手都冲线罗.");
		System.out.println("比赛游戏：结束.");
		
		es.shutdown();
	}
}

class Racer implements Runnable {
	private CountDownLatch startCdl, endCdl;
	private int no;
	
	public Racer (CountDownLatch startCdl, CountDownLatch endCdl, int no) {
		this.startCdl = startCdl;
		this.endCdl = endCdl;
		this.no = no;
	}
	
	public void run() {
		try {
			this.startCdl.await(); // 一开始所有选手在等待教练响枪
			Thread.sleep((long) (Math.random() * 10000)); // 选手在拼命奔跑
			
			System.out.println("比赛游戏：第" + no + "号冲线."); // 冲线
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.endCdl.countDown(); // 选手冲线后，在跑人数减1
		}
	}
}