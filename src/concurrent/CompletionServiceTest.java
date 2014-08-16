package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(5);
		CompletionService<String> cs = new ExecutorCompletionService<String>(es);
		
		for (int i = 0; i < 5; i++) {
			cs.submit(new ShitProducer());
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println(cs.take().get());
		}
		
		es.shutdown();
	}
}

class ShitProducer implements Callable<String> {
	public String call() throws Exception {
		return Thread.currentThread().getName();
	}
}
