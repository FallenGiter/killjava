package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		
		Locker locker = new Locker();
		
		for (int i = 0; i < 5; i++) {
			es.submit(new LockerGame("" + i, locker));
		}
		
		es.shutdown();
	}
}

class LockerGame extends Thread {
	private Locker locker;
	private String name;
	
	public LockerGame(String name, Locker locker) {
		this.name = name;
		this.locker = locker;
	}

	@Override
	public void run() {
		this.locker.doJob(this.name);
	}
}

class Locker {
	private final ReentrantLock lock = new ReentrantLock(true);
	
	public void doJob(String name) {
		try {
			System.out.println("封锁者：" + name + "，还没有到你呢，先打酱油.");
			this.lock.lock();
			
			System.out.println("封锁者：" + name + "，到你了，Motherfucker.");
			Thread.sleep(30L * 60L * 1000L);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("封锁者：" + name + "走了，下一个，还有" + this.lock.getQueueLength() + "个Asshole等着.");
			this.lock.unlock();
		}
	}
}