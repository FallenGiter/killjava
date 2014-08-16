package designpattern.singleton;

public class ThreadLocalSingleton {
	private static ThreadLocal<ThreadLocalSingleton> sCtx = new ThreadLocal<ThreadLocalSingleton>();
	
	private ThreadLocalSingleton(){}
	
	public static ThreadLocalSingleton getInstance() {
		ThreadLocalSingleton instance = (ThreadLocalSingleton) sCtx.get();
		
		if (instance == null) {
			instance = new ThreadLocalSingleton();
			sCtx.set(instance);
		}
		
		return instance;
	}
}
