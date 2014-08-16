package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.submit(new Producer());
		es.submit(new Consumer());
		es.submit(new Consumer());
		es.submit(new Consumer());
		
		es.shutdown();
	}
}

class Producer extends Thread {
	public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);
	
	public Producer() {
	}
	
	@Override
	public void run() {
		try {
			int i = 0;
			for (;;) {
				Thread.sleep(10*1000);
				queue.put("" + ++i);
				System.out.println("生产者：" + Thread.currentThread().getName() + "生产了" + i + ".");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer extends Thread {
	@Override
	public void run() {
		try {
//			for (;;) {
//				if (!Producer.queue.isEmpty()) {
					String product = Producer.queue.take();
					System.out.println("消费者：" + Thread.currentThread().getName() + "消费了" + product + ".");
//				} else {
//					System.out.println("消费者：" + Thread.currentThread().getName() + "想消费，但是没有货，等等吧.");
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}