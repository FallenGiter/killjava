package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 让一组线程相互等待
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		int[] ts1 = {8, 9, 10};
		int[] ts2 = {12, 4, 8};
		int[] ts3 = {11, 6, 9};
		String[] tourNames = {"旅游团1", "旅游团2", "旅游团3"};
		CyclicBarrier cb = new CyclicBarrier(3);
		
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		es.submit(new Walker(cb, tourNames[0], ts1));
		es.submit(new Walker(cb, tourNames[1], ts2));
		es.submit(new Walker(cb, tourNames[2], ts3));
		
		es.shutdown();
	}
}

class Walker implements Runnable {
	private CyclicBarrier cb;
	private String tourName;
	private int[] times;
	
	public Walker (CyclicBarrier cb, String tourName, int[] times) {
		this.cb = cb;
		this.tourName = tourName;
		this.times = times;
	}
	
	public void run() {
		try {
			Thread.sleep(this.times[0] * 1000);
			System.out.println("暴走者：" + this.tourName + "于" + now() + "到达广州.");
			this.cb.await();
			
			Thread.sleep(this.times[1] * 1000);
			System.out.println("暴走者：" + this.tourName + "于" + now() + "到达东莞.");
			this.cb.await();
			
			Thread.sleep(this.times[2] * 1000);
			System.out.println("暴走者：" + this.tourName + "于" + now() + "到达深圳.");
			this.cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String now() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}