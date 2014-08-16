package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledServiceTest {
	public static void main(String[] args) {
		final ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
		
		final ScheduledFuture<?> hd1 = ses.scheduleAtFixedRate(new Job(), 1, 1, TimeUnit.SECONDS);
		final ScheduledFuture<?> hd2 = ses.scheduleWithFixedDelay(new Job(), 2, 1, TimeUnit.SECONDS);
		ScheduledFuture<?> hd3 = ses.schedule(new Runnable() {
			public void run() {
				hd1.cancel(true);
				hd2.cancel(true);
				ses.shutdown();
			}
		}, 10, TimeUnit.SECONDS);
	}
}

class Job implements Runnable {
	int i = 0;
	public void run() {
		System.out.println("定时工作：" + now() + "\t" + ++i);
	}
	
	private String now() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}